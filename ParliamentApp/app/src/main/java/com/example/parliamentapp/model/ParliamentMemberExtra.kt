package com.example.parliamentapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * ParliamentMemberExtra is a data class that represents extra information about a parliament member.
 * It is @Serializable for Retrofit to be able to convert JSON to a ParliamentMemberExtra object.
 * It is @Entity for Room to be able to store ParliamentMemberExtra objects in a database.
 */
@Serializable
@Entity(tableName = "parliament_member_extras")
data class ParliamentMemberExtra(
    @PrimaryKey val hetekaId: Int,
    val twitter: String,
    val bornYear: Int,
    val constituency: String,
)