package com.example.parliamentapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * ParliamentMemberNote is a data class that represents a note about a parliament member.
 * It is not @Serializable because notes are completely local.
 * It is @Entity for Room to be able to store ParliamentMemberNote objects in a database.
 */

@Entity(tableName = "parliament_member_notes")
data class ParliamentMemberNote(
    @PrimaryKey val hetekaId: Int,
    val comment: String,
    val rating: Int,
)