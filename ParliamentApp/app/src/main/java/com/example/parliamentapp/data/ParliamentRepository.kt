package com.example.parliamentapp.data

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.parliamentapp.data.database.ParliamentMemberDao
import com.example.parliamentapp.model.ParliamentMember
import com.example.parliamentapp.model.ParliamentMemberExtra
import com.example.parliamentapp.model.ParliamentMemberNote
import com.example.parliamentapp.network.FetchWorker
import com.example.parliamentapp.network.ParliamentApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * ParliamentRepository is an interface that defines the methods for fetching parliament members,
 * parliament member extras and parliament member notes.
 * DefaultParliamentRepository is a class that implements the ParliamentRepository interface.
 * DefaultParliamentRepository uses a work manager to schedule a worker that fetches data
 * from the network to keep the local database up to date.
 */
interface ParliamentRepository {
    suspend fun getParliamentMembers(): Flow<List<ParliamentMember>>
    suspend fun getParliamentMemberExtra(): Flow<List<ParliamentMemberExtra>>
    suspend fun getParliamentMemberNotes(): Flow<List<ParliamentMemberNote>>
    suspend fun insertNote(note: ParliamentMemberNote)
    suspend fun deleteNote(hetekaId: Int)
}

class DefaultParliamentRepository(
    private val parliamentApiService: ParliamentApiService,
    private val parliamentMemberDao: ParliamentMemberDao,
    context: Context
) : ParliamentRepository {

    private val workManager = WorkManager.getInstance(context)

    private fun scheduleFetchWorker() {
        val fetchRequest = PeriodicWorkRequestBuilder<FetchWorker>(15, TimeUnit.MINUTES)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(androidx.work.NetworkType.CONNECTED)
                    .build()
            )
            .build()
        workManager.enqueueUniquePeriodicWork(
            "FetchWorker",
            ExistingPeriodicWorkPolicy.KEEP,
            fetchRequest
        )
    }

    init {
        scheduleFetchWorker()
    }

    override suspend fun getParliamentMembers(): Flow<List<ParliamentMember>> {
        // Use local db if available, otherwise populate db from network and use it
        return flow {
            withContext(Dispatchers.IO) {
                val databaseData = parliamentMemberDao.getParliamentMembers().firstOrNull()
                if (databaseData.isNullOrEmpty()) {
                    val networkData = parliamentApiService.getParliamentMembers()
                    parliamentMemberDao.insertAllMembers(networkData)
                }
            }
            emitAll(parliamentMemberDao.getParliamentMembers())
        }
    }

    override suspend fun getParliamentMemberExtra(): Flow<List<ParliamentMemberExtra>> {
        // Use local db if available, otherwise populate db from network and use it
        return flow {
            withContext(Dispatchers.IO) {
                val databaseData =
                    parliamentMemberDao.getParliamentMemberExtras().firstOrNull()
                if (databaseData.isNullOrEmpty()) {
                    val networkData = parliamentApiService.getParliamentMemberExtras()
                    parliamentMemberDao.insertAllExtras(networkData)
                }
            }
            emitAll(parliamentMemberDao.getParliamentMemberExtras())
        }
    }


    override suspend fun getParliamentMemberNotes(): Flow<List<ParliamentMemberNote>> {
        return parliamentMemberDao.getParliamentMemberNotes()
    }

    override suspend fun insertNote(note: ParliamentMemberNote) {
        withContext(Dispatchers.IO) {
            parliamentMemberDao.insert(note)
        }
    }

    override suspend fun deleteNote(hetekaId: Int) {
        withContext(Dispatchers.IO) {
            parliamentMemberDao.deleteNote(hetekaId)
        }
    }
}
