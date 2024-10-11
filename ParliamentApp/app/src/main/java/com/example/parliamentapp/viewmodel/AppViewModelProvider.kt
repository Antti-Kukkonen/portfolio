package com.example.parliamentapp.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.parliamentapp.ParliamentApplication

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * ViewModel provider for the application.
 * Used for creating and providing the ParliamentViewModel.
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ParliamentApplication)
            val repository = application.container.parliamentRepository
            ParliamentViewModel(repository)
        }
    }
}