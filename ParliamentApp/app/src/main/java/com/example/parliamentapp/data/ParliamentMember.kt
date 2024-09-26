package com.example.parliamentapp.data

data class ParliamentMember(
    val hetekaId: Int,
    val seatNumber: Int,
    val lastname: String,
    val firstname: String,
    val party: String,
    val minister: Boolean,
    val pictureUrl: String,
)