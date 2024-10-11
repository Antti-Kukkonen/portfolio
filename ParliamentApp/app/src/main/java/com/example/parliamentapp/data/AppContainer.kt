package com.example.parliamentapp.data

import android.content.Context
import com.example.parliamentapp.data.database.ParliamentMemberDatabase
import com.example.parliamentapp.network.ParliamentApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * AppContainer is an interface that defines the dependencies of the app.
 * DefaultAppContainer is a class that implements the AppContainer interface.
 * DefaultAppContainer uses Retrofit and a Room DB to implement the repository.
 */
interface AppContainer {
    val parliamentRepository: ParliamentRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {
    private val baseUrl = "https://users.metropolia.fi/~peterh/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: ParliamentApiService by lazy {
        retrofit.create(ParliamentApiService::class.java)
    }


    override val parliamentRepository: ParliamentRepository by lazy {
        val db = ParliamentMemberDatabase.getDatabase(context)
        DefaultParliamentRepository(
            parliamentApiService = retrofitService,
            parliamentMemberDao = db.parliamentMemberDao(),
            context = context
        )
    }
}