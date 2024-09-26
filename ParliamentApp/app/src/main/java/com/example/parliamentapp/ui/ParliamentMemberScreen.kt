package com.example.parliamentapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.SubcomposeAsyncImage
import com.example.parliamentapp.R
import com.example.parliamentapp.viewmodel.ParliamentViewModel

const val IMAGE_URL_PREFIX = "https://avoindata.eduskunta.fi/"

@Composable
fun ParliamentMemberScreen(viewModel: ParliamentViewModel = viewModel()) {
    val currentMember by viewModel.currentMember.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        currentMember?.let { member ->
            Spacer(modifier = Modifier.height(32.dp))
            SubcomposeAsyncImage(
                model = "${IMAGE_URL_PREFIX}${member.pictureUrl}",
                contentDescription = "Parliament member image",
                modifier = Modifier.size(200.dp),
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier.size(50.dp)
                    )
                },
                error = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "Default member image",
                        modifier = Modifier.fillMaxSize()
                    )
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${member.firstname} ${member.lastname}, ${member.party}",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Seat: ${member.seatNumber}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Heteka ID: ${member.hetekaId}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = if (member.minister) "A minister" else "Not a minister",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(32.dp))
        }

        Button(onClick = { viewModel.selectRandomMember() }) {
            Text("Select Random Member")
        }

        Spacer(modifier = Modifier.height(32.dp))
        PartyInfo(viewModel = viewModel)
    }
}