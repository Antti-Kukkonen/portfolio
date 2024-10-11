package com.example.parliamentapp.network

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.parliamentapp.data.AppContainer
import com.example.parliamentapp.data.DefaultAppContainer
import com.example.parliamentapp.data.database.ParliamentMemberDatabase
import com.example.parliamentapp.model.ParliamentMember
import com.example.parliamentapp.model.ParliamentMemberExtra

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Worker class for fetching data from network and inserting it to local database.
 * Worker is used with WorkManager to fetch data in background.
 */
class FetchWorker(
    context: Context, workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    private val appContainer: AppContainer = DefaultAppContainer(context)

    override suspend fun doWork(): Result {
        return try {
            Log.i("FetchWorker", "Fetching data from network")
            val repository = appContainer.parliamentRepository
            // Fetch data from network
            var memberList: List<ParliamentMember> = emptyList()
            repository.getParliamentMembers().collect { memberList = it }

            var extraList: List<ParliamentMemberExtra> = emptyList()
            repository.getParliamentMemberExtra().collect { extraList = it }

            // Insert data to local database
            val db = ParliamentMemberDatabase.getDatabase(applicationContext)
            db.parliamentMemberDao().insertAllMembers(memberList)
            db.parliamentMemberDao().insertAllExtras(extraList)

            Log.d("FetchWorker", "Data fetched and inserted to database")
            Result.success()
        } catch (e: Exception) {
            Log.e("FetchWorker", "Error fetching data from network", e)
            Result.failure()
        }
    }
}