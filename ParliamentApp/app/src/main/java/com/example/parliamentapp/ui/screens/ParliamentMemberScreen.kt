package com.example.parliamentapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.parliamentapp.R
import com.example.parliamentapp.model.ParliamentMember
import com.example.parliamentapp.model.ParliamentMemberExtra
import com.example.parliamentapp.model.ParliamentMemberNote
import com.example.parliamentapp.ui.TopBarWithBackButton
import com.example.parliamentapp.ui.composables.ParliamentMemberCard
import com.example.parliamentapp.utils.partyRawToDisplay
import com.example.parliamentapp.viewmodel.ParliamentViewModel
import com.example.parliamentapp.viewmodel.UiState

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Screen for displaying the parliament members.
 * Contains a list of parliament members and a filter button.
 * Members can be filtered by party, rating, constituency and name.
 * Users can add notes and ratings to members.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParliamentMemberScreen(
    navController: NavController,
    viewModel: ParliamentViewModel,
    modifier: Modifier = Modifier,
) {
    val membersState by viewModel.members.collectAsState()
    val extrasState by viewModel.extras.collectAsState()
    val notesState by viewModel.notes.collectAsState()

    val selectedParties = remember { mutableStateOf(setOf<String>()) }
    val selectedRatings = remember { mutableStateOf(setOf<Int>()) }
    val selectedConstituencies = remember { mutableStateOf(setOf<String>()) }
    val search = remember { mutableStateOf("") }

    var filterSheetOpen by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    val filteredMembers = filterMembers(
        members = (membersState as? UiState.Success)?.data,
        notes = (notesState as? UiState.Success)?.data,
        extras = (extrasState as? UiState.Success)?.data,
        selectedParties = selectedParties.value,
        selectedRatings = selectedRatings.value,
        selectedConstituencies = selectedConstituencies.value,
        search = search.value
    )
    Scaffold(
        topBar = {
            TopBarWithBackButton(
                title = stringResource(R.string.members_screen_card_title),
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        when (membersState) {
            is UiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is UiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = stringResource(
                        R.string.failed_to_fetch_members,
                        (membersState as UiState.Error).message
                    ))
                }
            }

            is UiState.Success -> {
                ParliamentMemberContent(
                    modifier = modifier,
                    innerPadding = innerPadding,
                    members = filteredMembers,
                    extras = (extrasState as UiState.Success).data,
                    viewModel = viewModel,
                    onFilterClick = { filterSheetOpen = true }
                )
            }
        }

        if (filterSheetOpen) {
            FilterBottomSheet(
                sheetState = sheetState,
                filteredMembersCount = filteredMembers.size,
                totalMembersCount = (membersState as? UiState.Success)?.data?.size ?: 0,
                selectedParties = selectedParties,
                selectedRatings = selectedRatings,
                selectedConstituencies = selectedConstituencies,
                search = search,
                onDismiss = { filterSheetOpen = false },
                viewModel = viewModel,
            )
        }
    }
}

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Content of the ParliamentMemberScreen.
 * Contains the main list of parliament members and the filter button.
 */
@Composable
fun ParliamentMemberContent(
    modifier: Modifier,
    innerPadding: PaddingValues,
    members: List<ParliamentMember>?,
    extras: List<ParliamentMemberExtra>?,
    viewModel: ParliamentViewModel,
    onFilterClick: () -> Unit
) {
    Box(
        Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(members ?: listOf()) { member ->
                val extra = extras?.find { it.hetekaId == member.hetekaId }
                ParliamentMemberCard(
                    member = member,
                    extra = extra,
                    viewModel = viewModel
                )
            }
        }

        ExtendedFloatingActionButton(
            onClick = onFilterClick,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 32.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(Icons.Default.Search, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(R.string.filter))
        }
    }
}

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Openable bottom sheet for filtering the parliament members.
 * Contains filter options.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    sheetState: SheetState,
    filteredMembersCount: Int,
    totalMembersCount: Int,
    selectedParties: MutableState<Set<String>>,
    selectedRatings: MutableState<Set<Int>>,
    selectedConstituencies: MutableState<Set<String>>,
    search: MutableState<String>,
    onDismiss: () -> Unit,
    viewModel: ParliamentViewModel
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .padding(16.dp)
        ) {
            FilterHeader(filteredMembersCount, totalMembersCount)
            OutlinedTextField(
                value = search.value,
                onValueChange = { search.value = it },
                label = { Text(stringResource(R.string.filter_by_name)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
            FilterOptions(
                label = stringResource(R.string.by_party),
                values = viewModel.getParties(),
                selectedValues = selectedParties.value,
                onValueToggle = {
                    selectedParties.value = toggleSetValue(selectedParties.value, it)
                },
                valueToDisplay = { partyRawToDisplay(it) }
            )
            FilterOptions(
                label = stringResource(R.string.by_your_rating),
                values = (1..5).toList(),
                selectedValues = selectedRatings.value,
                onValueToggle = {
                    selectedRatings.value = toggleSetValue(selectedRatings.value, it)
                }
            )
            FilterOptions(
                label = stringResource(R.string.by_constituency),
                values = viewModel.getConstituencies(),
                selectedValues = selectedConstituencies.value,
                onValueToggle = {
                    selectedConstituencies.value = toggleSetValue(selectedConstituencies.value, it)
                }
            )
        }
    }
}

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Header for the filter bottom sheet.
 */
@Composable
fun FilterHeader(filteredMembersCount: Int, totalMembersCount: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = stringResource(R.string.filter))
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(R.string.showing, filteredMembersCount, totalMembersCount),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Generic filter options composable.
 * Contains a label and a row of filter chips.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun <T> FilterOptions(
    label: String,
    values: List<T>,
    selectedValues: Set<T>,
    onValueToggle: (T) -> Unit,
    valueToDisplay: (T) -> String = { it.toString() }
) where T : Any {
    Text(text = label, style = MaterialTheme.typography.bodyMedium)
    FlowRow(
        horizontalArrangement = Arrangement.Start,
        verticalArrangement = Arrangement.Center,
    ) {
        values.forEach { value ->
            FilterChip(
                selected = selectedValues.contains(value),
                onClick = { onValueToggle(value) },
                label = { Text(valueToDisplay(value)) },
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
            )
        }
    }
}

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Toggles a value in a set.
 */
fun <T> toggleSetValue(set: Set<T>, value: T): Set<T> {
    return if (set.contains(value)) set - value else set + value
}

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Filters the parliament members based on selected filter options.
 * Returns a list of filtered members.
 */
fun filterMembers(
    members: List<ParliamentMember>?,
    notes: List<ParliamentMemberNote>?,
    extras: List<ParliamentMemberExtra>?,
    selectedParties: Set<String>,
    selectedRatings: Set<Int>,
    selectedConstituencies: Set<String>,
    search: String
): List<ParliamentMember> {
    return members?.filter { member ->
        val note = notes?.find { it.hetekaId == member.hetekaId }
        val extra = extras?.find { it.hetekaId == member.hetekaId }
        val memberName = member.firstname + " " + member.lastname

        val partyMatches = selectedParties.isEmpty() || selectedParties.contains(member.party)
        val ratingMatches = selectedRatings.isEmpty() || selectedRatings.contains(note?.rating)
        val constituencyMatches =
            selectedConstituencies.isEmpty() || selectedConstituencies.contains(extra?.constituency)
        val searchMatches = search.isEmpty() || memberName.contains(search, ignoreCase = true)

        partyMatches && ratingMatches && constituencyMatches && searchMatches
    } ?: emptyList()
}
