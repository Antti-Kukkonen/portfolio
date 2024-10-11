package com.example.parliamentapp.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.parliamentapp.utils.partyRawToColor
import com.example.parliamentapp.utils.partyRawToDisplay
import com.example.parliamentapp.utils.partyRawToTextColor

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Composable for displaying a party indicator.
 * Displays the party abbreviation in the appropriately colored box.
 */
@Composable
fun PartyIndicator(party: String, modifier: Modifier = Modifier) {
    Text(
        text = partyRawToDisplay(party),
        style = MaterialTheme.typography.titleMedium,
        color = partyRawToTextColor(party),
        textAlign = TextAlign.Center,
        modifier = modifier
            .background(color = partyRawToColor(party), shape = MaterialTheme.shapes.small)
            .padding(4.dp),
        softWrap = false,
    )
}