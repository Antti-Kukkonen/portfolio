package com.example.parliamentapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.parliamentapp.R
import com.example.parliamentapp.ui.TopBarWithBackButton
import com.example.parliamentapp.ui.composables.ScreenCard

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Home screen of the application.
 * Contains two cards for navigating to Members and Analysis screens.
 */
@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    Scaffold(
        topBar = {
            TopBarWithBackButton(
                title = stringResource(R.string.app_name),
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScreenCard(
                title = stringResource(R.string.members_screen_card_title),
                description = stringResource(R.string.member_screen_card_desc),
                image = Icons.Default.AccountBox,
                onClick = { navController.navigate("members") },
            )
            ScreenCard(
                title = stringResource(R.string.analysis_screen_card_title),
                description = stringResource(R.string.analysis_screen_card_desc),
                image = Icons.Default.Search,
                onClick = { navController.navigate("analysis") },
            )

            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}
