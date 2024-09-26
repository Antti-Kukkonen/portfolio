package com.example.parliamentapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.parliamentapp.viewmodel.ParliamentViewModel


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PartyInfo(
    viewModel: ParliamentViewModel = viewModel()
) {
    // Mutable state list to hold the currently active toggles
    var selectedOptions by remember { mutableStateOf(listOf<String>()) }

    // Display toggle buttons for each string in the list
    FlowRow(
        horizontalArrangement = Arrangement.Center,
    ) {
        viewModel.parties().forEach { party ->
            val isSelected = party in selectedOptions

            // Toggle Button for each option
            Button(
                onClick = {
                    selectedOptions = if (isSelected) {
                        selectedOptions - party
                    } else {
                        selectedOptions + party
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(start = 4.dp, end = 4.dp)
            ) {
                Text(text = party)
            }
        }
    }
    Spacer(modifier = Modifier.height(32.dp))
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()

    ) {
        Text(text = "Selected: ${selectedOptions.sorted().joinToString(", ")}")
        Text(text = "Number of seats: ${viewModel.seats(selectedOptions.toSet())}")
        Text(
            text = "Possible governments: ${viewModel.getPossibleGovernments(selectedOptions.toSet())}",
            modifier = Modifier.verticalScroll(rememberScrollState())
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}