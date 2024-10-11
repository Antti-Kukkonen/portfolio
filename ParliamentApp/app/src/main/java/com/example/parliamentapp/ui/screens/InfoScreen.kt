package com.example.parliamentapp.ui.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.parliamentapp.R
import com.example.parliamentapp.ui.TopBarWithBackButton
import com.example.parliamentapp.ui.composables.PartyIndicator
import com.example.parliamentapp.utils.partyRawToDisplay
import com.example.parliamentapp.viewmodel.ParliamentViewModel

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Screen for displaying information about the parliament.
 * Contains information about the parties by size and possible governments.
 */
@Composable
fun InfoScreen(
    navController: NavController,
    viewModel: ParliamentViewModel,
    modifier: Modifier = Modifier,
) {
    val selectedParties = remember { mutableStateOf(setOf<String>()) }
    val possibleGovernments by viewModel.governmentSets.observeAsState(setOf(setOf()))

    // Fetch the governments when the selected parties change
    // This is done with a LaunchedEffect because it's computationally expensive
    LaunchedEffect(selectedParties.value) {
        viewModel.governments(selectedParties.value)
    }

    Scaffold(
        topBar = {
            TopBarWithBackButton(
                title = stringResource(R.string.analysis_screen_card_title),
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.popBackStack() }
            )
        },
    ) { innerPadding ->

        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
            ) {
                Text(
                    text = stringResource(R.string.parties_by_size),
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Column {
                    viewModel.partiesBySize().forEach {
                        Row(
                            modifier = Modifier.padding(vertical = 1.dp)
                        ) {
                            Text(
                                text = it.second.toString(),
                                style = MaterialTheme.typography.titleMedium,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.widthIn(24.dp)
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            // Proportional width of the party indicator, with a spacer to fill the rest
                            PartyIndicator(
                                party = it.first,
                                modifier = Modifier.weight(
                                    it.second.toFloat() / viewModel
                                        .partiesBySize()
                                        .first()
                                        .second
                                        .toFloat()
                                )
                            )
                            Spacer(
                                modifier = Modifier.weight(
                                    1 - (it.second.toFloat() / (viewModel
                                        .partiesBySize()
                                        .first()
                                        .second
                                        .toFloat() + 1))
                                )
                            )
                        }

                    }
                }

                Spacer(modifier = Modifier.size(16.dp))

                Text(
                    text = stringResource(R.string.possible_governments, possibleGovernments.size),
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Column {
                    possibleGovernments.forEach {
                        Row(
                            modifier = Modifier
                                .padding(vertical = 1.dp)
                                .horizontalScroll(rememberScrollState())
                        ) {
                            Text(
                                text = viewModel.seats(it).toString(),
                                style = MaterialTheme.typography.titleMedium,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.widthIn(24.dp)
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            it.forEach { party ->
                                PartyIndicator(
                                    party = party,
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.size(142.dp))

            }

            Surface(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(142.dp)
                    .padding(4.dp)
            ) {
                // Creates chips for selecting parties
                FilterOptions(
                    label = stringResource(R.string.select_parties),
                    values = viewModel.getParties(),
                    selectedValues = selectedParties.value,
                    onValueToggle = {
                        selectedParties.value = toggleSetValue(selectedParties.value, it)
                    },
                    valueToDisplay = { partyRawToDisplay(it) },
                )
            }
        }
    }
}