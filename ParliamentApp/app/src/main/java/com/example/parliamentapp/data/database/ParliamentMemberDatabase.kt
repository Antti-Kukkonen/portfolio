package com.example.parliamentapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.parliamentapp.model.ParliamentMember
import com.example.parliamentapp.model.ParliamentMemberExtra
import com.example.parliamentapp.model.ParliamentMemberNote

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * A Room database for storing ParliamentMember objects.
 * Implements the ParliamentMemberDao interface.
 * Singleton.
 */
@Database(
    entities = [ParliamentMember::class, ParliamentMemberExtra::class, ParliamentMemberNote::class],
    version = 1,
    exportSchema = false
)
abstract class ParliamentMemberDatabase : RoomDatabase() {

    abstract fun parliamentMemberDao(): ParliamentMemberDao

    companion object {
        @Volatile
        private var Instance: ParliamentMemberDatabase? = null

        fun getDatabase(context: Context): ParliamentMemberDatabase {
            // Singleton pattern to prevent multiple instances of the database opening
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    ParliamentMemberDatabase::class.java,
                    "parliament_member_database"
                ).fallbackToDestructiveMigration().build().also { Instance = it }
            }
        }
    }
}