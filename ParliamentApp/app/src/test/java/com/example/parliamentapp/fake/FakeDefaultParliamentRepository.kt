package com.example.parliamentapp.fake

import com.example.parliamentapp.data.ParliamentRepository
import com.example.parliamentapp.fake.FakeDataSource.fakeParliamentMemberNotes
import com.example.parliamentapp.model.ParliamentMember
import com.example.parliamentapp.model.ParliamentMemberExtra
import com.example.parliamentapp.model.ParliamentMemberNote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Fake Parliament repository for testing purposes.
 * Returns fake data from the FakeParliamentApiService.
 * Has an option to return an error.
 */
class FakeDefaultParliamentRepository(
    private val parliamentApiService: FakeParliamentApiService,
    private val shouldReturnError: Boolean = false
) : ParliamentRepository {

    override suspend fun getParliamentMembers(): Flow<List<ParliamentMember>> {
        if (shouldReturnError) {
            throw Exception("Fake exception")
        }
        return flow {
            emit(parliamentApiService.getParliamentMembers())
        }
    }

    override suspend fun getParliamentMemberExtra(): Flow<List<ParliamentMemberExtra>> {
        if (shouldReturnError) {
            throw Exception("Fake exception")
        }
        return flow {
            emit(parliamentApiService.getParliamentMemberExtras())
        }
    }

    override suspend fun getParliamentMemberNotes(): Flow<List<ParliamentMemberNote>> {
        if (shouldReturnError) {
            throw Exception("Fake exception")
        }
        return flow {
            emit(fakeParliamentMemberNotes)
        }
    }

    override suspend fun insertNote(note: ParliamentMemberNote) {
        if (shouldReturnError) {
            throw Exception("Fake exception")
        }
        fakeParliamentMemberNotes.add(note)
    }

    override suspend fun deleteNote(hetekaId: Int) {
        if (shouldReturnError) {
            throw Exception("Fake exception")
        }
        fakeParliamentMemberNotes.removeIf { it.hetekaId == hetekaId }
    }
}