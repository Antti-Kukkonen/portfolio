package com.example.parliamentapp.network

import com.example.parliamentapp.model.ParliamentMember
import com.example.parliamentapp.model.ParliamentMemberExtra
import retrofit2.http.GET

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Retrofit interface for fetching data from Parliament API.
 */
interface ParliamentApiService {
    @GET("seating.json")
    suspend fun getParliamentMembers(): List<ParliamentMember>

    @GET("extras.json")
    suspend fun getParliamentMemberExtras(): List<ParliamentMemberExtra>
}