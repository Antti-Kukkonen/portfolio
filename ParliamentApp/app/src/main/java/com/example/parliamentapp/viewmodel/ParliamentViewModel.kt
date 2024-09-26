package com.example.parliamentapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.parliamentapp.data.ParliamentMember
import com.example.parliamentapp.data.ParliamentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ParliamentViewModel : ViewModel() {
    private val repository = ParliamentRepository()
    private val _currentMember = MutableStateFlow<ParliamentMember?>(null)
    val currentMember: StateFlow<ParliamentMember?> = _currentMember.asStateFlow()

    init {
        selectRandomMember()
    }

    fun selectRandomMember() {
        _currentMember.value = repository.getRandomParliamentMember()
    }

    fun parties(): List<String> {
        return repository.parties()
    }

    fun partiesBySize(): List<String> {
        return repository.partiesBySize()
    }

    fun partyMembers(party: String): List<ParliamentMember> {
        return repository.partyMembers(party)
    }

    fun governmentParties(): Set<String> {
        return repository.governmentParties()
    }

    fun governmentPartiesFromLargestPartyToSmallest(): List<String> {
        return repository.governmentPartiesFromLargestPartyToSmallest()
    }

    fun governmentSeats(): Int {
        return repository.governmentSeats()
    }

    fun seats(partyNames: Set<String>): Int {
        return repository.seats(partyNames)
    }

    fun getPossibleGovernments(partyNames: Set<String>): Set<Set<String>> {
        return repository.governments(partyNames)
    }
}