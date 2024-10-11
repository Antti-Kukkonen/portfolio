package com.example.parliamentapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.parliamentapp.ui.ParliamentApp
import com.example.parliamentapp.ui.theme.ParliamentAppTheme
import com.example.parliamentapp.viewmodel.AppViewModelProvider

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Main activity for the application.
 * Creates and passes the viewModel down.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ParliamentAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    ParliamentApp(
                        viewModel = viewModel(factory = AppViewModelProvider.Factory)
                    )
                }
            }
        }
    }
}
