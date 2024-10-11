package com.example.parliamentapp.fake

import com.example.parliamentapp.fake.FakeDataSource.fakeParliamentMemberExtras
import com.example.parliamentapp.fake.FakeDataSource.fakeParliamentMembers
import com.example.parliamentapp.model.ParliamentMember
import com.example.parliamentapp.model.ParliamentMemberExtra
import com.example.parliamentapp.network.ParliamentApiService

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Fake Parliament API service for testing purposes.
 * Returns fake data from the FakeDataSource.
 */
class FakeParliamentApiService : ParliamentApiService {
    override suspend fun getParliamentMembers(): List<ParliamentMember> {
        return fakeParliamentMembers
    }

    override suspend fun getParliamentMemberExtras(): List<ParliamentMemberExtra> {
        return fakeParliamentMemberExtras
    }
}