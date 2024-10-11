package com.example.parliamentapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * ParliamentMember is a data class that represents a parliament member.
 * It is @Serializable for Retrofit to be able to convert JSON to a ParliamentMember object.
 * It is @Entity for Room to be able to store ParliamentMember objects in a database.
 */
@Serializable
@Entity(tableName = "parliament_members")
data class ParliamentMember(
    @PrimaryKey val hetekaId: Int,
    val seatNumber: Int,
    val lastname: String,
    val firstname: String,
    val party: String,
    val minister: Boolean,
    val pictureUrl: String,
)

