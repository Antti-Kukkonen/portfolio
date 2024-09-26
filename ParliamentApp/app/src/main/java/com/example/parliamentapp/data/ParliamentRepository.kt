package com.example.parliamentapp.data

class ParliamentRepository {
    private val parliamentMembers = ParliamentMembersData.members

    fun getRandomParliamentMember(): ParliamentMember {
        return parliamentMembers.random()
    }

    fun parties(): List<String> {
        return parliamentMembers
            .map { it.party }
            .distinct()
            .sorted()
    }

    fun partiesBySize(): List<String> {
        return parliamentMembers
            .groupBy { it.party }
            .map { it.key to it.value.size }
            .sortedByDescending { it.second }
            .map { it.first }
    }

    fun partyMembers(party: String): List<ParliamentMember> {
        return parliamentMembers
            .filter { it.party == party }
            .sortedWith(compareBy({ it.lastname }, { it.firstname }))
    }

    fun governmentParties(): Set<String> {
        return parliamentMembers
            .filter { it.minister }
            .map { it.party }
            .toSet()
    }

    fun governmentPartiesFromLargestPartyToSmallest(): List<String> {
        return parliamentMembers
            .asSequence()
            .filter { it.minister }
            .groupBy { it.party }
            .map { it.key to it.value.size }
            .sortedByDescending { it.second }
            .map { it.first }
            .toList()
    }

    fun governmentSeats(): Int {
        return parliamentMembers
            .map { it.party }
            .filter { governmentParties().contains(it) }
            .size
    }

    fun seats(partyNames: Set<String>): Int {
        return parliamentMembers
            .filter { partyNames.contains(it.party) }
            .size
    }

    // form all possible governments from the parties in the parliament
    // filter out the governments that have less than 101 seats
    // return the set of parties in the governments
    fun governments(partyNames: Set<String>): Set<Set<String>> {
        return partyNames
            .fold(setOf(setOf())) { subsets: Set<Set<String>>, element: String ->
                subsets + subsets.map { it + element }
            }
            .filter { seats(it) >= 101 }
            .toSet()
    }
}