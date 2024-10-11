package com.example.parliamentapp

import android.app.Application
import com.example.parliamentapp.data.AppContainer
import com.example.parliamentapp.data.DefaultAppContainer

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Application class for the Parliament application.
 * Contains the AppContainer for dependency injection.
 * The container is used to provide the dependencies for the application.
 */
class ParliamentApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(applicationContext)
    }
}