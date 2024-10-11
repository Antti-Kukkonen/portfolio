package com.example.parliamentapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.parliamentapp.model.ParliamentMember
import com.example.parliamentapp.model.ParliamentMemberExtra
import com.example.parliamentapp.model.ParliamentMemberNote
import kotlinx.coroutines.flow.Flow

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * A Data Access Object for the ParliamentMember class.
 * Contains methods for accessing the following tables:
 * - parliament_members
 * - parliament_member_extras
 * - parliament_member_notes
 */
@Dao
interface ParliamentMemberDao {
    @Query("SELECT * FROM parliament_members")
    fun getParliamentMembers(): Flow<List<ParliamentMember>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMembers(parliamentMembers: List<ParliamentMember>)

    @Query("SELECT * FROM parliament_member_extras")
    fun getParliamentMemberExtras(): Flow<List<ParliamentMemberExtra>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllExtras(extras: List<ParliamentMemberExtra>)

    @Query("SELECT * FROM parliament_member_notes")
    fun getParliamentMemberNotes(): Flow<List<ParliamentMemberNote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: ParliamentMemberNote)

    @Query("DELETE FROM parliament_member_notes WHERE hetekaId = :hetekaId")
    fun deleteNote(hetekaId: Int)
}