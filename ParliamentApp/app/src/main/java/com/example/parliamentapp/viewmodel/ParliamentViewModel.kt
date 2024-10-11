package com.example.parliamentapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parliamentapp.data.ParliamentRepository
import com.example.parliamentapp.model.ParliamentMember
import com.example.parliamentapp.model.ParliamentMemberExtra
import com.example.parliamentapp.model.ParliamentMemberNote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Sealed class for handling the UI state of the application.
 */
sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * ViewModel for the Parliament application.
 * Contains the logic for fetching and handling the parliament data.
 */
class ParliamentViewModel(
    private val repository: ParliamentRepository
) : ViewModel() {
    private val _members = MutableStateFlow<UiState<List<ParliamentMember>>>(UiState.Loading)
    val members: StateFlow<UiState<List<ParliamentMember>>> = _members.asStateFlow()

    private val _extras = MutableStateFlow<UiState<List<ParliamentMemberExtra>>>(UiState.Loading)
    val extras: StateFlow<UiState<List<ParliamentMemberExtra>>> = _extras.asStateFlow()

    private val _notes = MutableStateFlow<UiState<List<ParliamentMemberNote>>>(UiState.Loading)
    val notes: StateFlow<UiState<List<ParliamentMemberNote>>> = _notes.asStateFlow()

    private val _governmentSets = MutableLiveData<Set<Set<String>>>()
    val governmentSets: LiveData<Set<Set<String>>> get() = _governmentSets

    init {
        fetchParliamentMembers()
        fetchParliamentMemberExtras()
        fetchParliamentMemberNotes()
    }

    private fun fetchParliamentMembers() {
        viewModelScope.launch {
            _members.value = UiState.Loading
            try {
                repository.getParliamentMembers().collect { memberList ->
                    _members.value = UiState.Success(memberList.sortedBy { it.seatNumber })
                }
            } catch (e: HttpException) {
                _members.value = UiState.Error("Error: ${e.message()}")

            } catch (e: Exception) {
                _members.value =
                    UiState.Error("Error: ${e.message ?: "Unknown error"}")
            }
        }
    }

    private fun fetchParliamentMemberExtras() {
        viewModelScope.launch {
            _extras.value = UiState.Loading
            try {
                repository.getParliamentMemberExtra().collect { extraList ->
                    _extras.value = UiState.Success(extraList)
                }
            } catch (e: HttpException) {
                _members.value =
                    UiState.Error("Error: ${e.message()}")

            } catch (e: Exception) {
                _extras.value =
                    UiState.Error("Error: ${e.message ?: "Unknown error"}")
            }
        }
    }

    private fun fetchParliamentMemberNotes() {
        viewModelScope.launch {
            _notes.value = UiState.Loading
            try {
                repository.getParliamentMemberNotes().collect { noteList ->
                    _notes.value = UiState.Success(noteList)
                }
            } catch (e: Exception) {
                _notes.value =
                    UiState.Error("Error: ${e.message ?: "Unknown error"}")
            }
        }
    }

    fun insertNote(note: ParliamentMemberNote) {
        viewModelScope.launch {
            try {
                repository.insertNote(note)
                fetchParliamentMemberNotes()  // Refresh the notes after insertion
            } catch (e: Exception) {
                Log.e("ParliamentViewModel", "Failed to insert note: ${e.message}")
            }
        }
    }

    fun deleteNote(hetekaInt: Int) {
        viewModelScope.launch {
            repository.deleteNote(hetekaInt)
        }
    }

    fun getParties(): List<String> {
        val currentMembers = (members.value as? UiState.Success)?.data
        return currentMembers?.map { it.party }?.distinct() ?: emptyList()
    }

    fun getConstituencies(): List<String> {
        val currentMembers = (extras.value as? UiState.Success)?.data
        return currentMembers?.map { it.constituency }?.distinct() ?: emptyList()
    }

    fun seats(partyNames: Set<String>): Int {
        val currentMembers = (members.value as? UiState.Success)?.data
        return currentMembers?.filter { partyNames.contains(it.party) }?.size ?: 0
    }

    // Return a list of pairs of party names and the number of members in the party
    fun partiesBySize(): List<Pair<String, Int>> {
        val currentMembers = (members.value as? UiState.Success)?.data
        return currentMembers
            ?.groupBy { it.party }
            ?.map { it.key to it.value.size }
            ?.sortedByDescending { it.second }
            ?: emptyList()
    }

    // Form all possible governments from the parties in the parliament
    // Filter out the governments that have less than 101 seats
    // Return the set of parties in the governments
    fun governments(partyNames: Set<String>) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.Default) {
                partyNames
                    .fold(setOf(setOf())) { subsets: Set<Set<String>>, party: String ->
                        subsets + subsets.map { it + party }
                    }
                    .filter { seats(it) >= 101 }
                    .toSet()

            }
            _governmentSets.postValue(result)
        }
    }
}