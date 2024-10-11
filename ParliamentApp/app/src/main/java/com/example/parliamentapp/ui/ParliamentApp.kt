package com.example.parliamentapp.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.parliamentapp.R
import com.example.parliamentapp.ui.screens.HomeScreen
import com.example.parliamentapp.ui.screens.InfoScreen
import com.example.parliamentapp.ui.screens.ParliamentMemberScreen
import com.example.parliamentapp.viewmodel.ParliamentViewModel

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Screen class to represent all the different screens in the application.
 */
sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object ParliamentMembers : Screen("members")
    data object Analysis : Screen("analysis")
}

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Main application composable.
 * Contains the navigation logic for the application.
 */
@Composable
fun ParliamentApp(
    modifier: Modifier = Modifier,
    viewModel: ParliamentViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {
            HomeScreen(
                navController = navController,
                modifier = modifier,
            )
        }

        composable(Screen.ParliamentMembers.route) {
            ParliamentMemberScreen(
                navController = navController,
                viewModel = viewModel,
                modifier = modifier,
            )
        }

        composable(Screen.Analysis.route) {
            InfoScreen(
                navController = navController,
                viewModel = viewModel,
                modifier = modifier,
            )
        }

    }
}

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Top bar with back button for navigation and a title.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithBackButton(
    title: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.cd_back)
                    )
                }
            }
        }
    )
}
