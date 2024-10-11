package com.example.parliamentapp

import com.example.parliamentapp.fake.FakeDataSource
import com.example.parliamentapp.fake.FakeDefaultParliamentRepository
import com.example.parliamentapp.fake.FakeParliamentApiService
import com.example.parliamentapp.model.ParliamentMember
import org.junit.Test
import org.junit.Assert.assertEquals
import kotlinx.coroutines.test.runTest

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Test class for the DefaultParliamentMemberRepository.
 * Tests the getParliamentMembers function.
 */
class DefaultParliamentMemberRepositoryTest {

    @Test
    fun defaultParliamentRepository_getParliamentMembers_verifyFlow() =
        runTest {
            val repository = FakeDefaultParliamentRepository(
                FakeParliamentApiService()
            )

            var parliamentMembers: List<ParliamentMember> = emptyList()
            repository.getParliamentMembers().collect {
                parliamentMembers = it
            }

            assertEquals(FakeDataSource.fakeParliamentMembers, parliamentMembers)
        }
}

