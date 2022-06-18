package com.example.notesapp.data.cache.database

import androidx.room.*
import com.example.notesapp.data.models.Notes

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNotes(notes: Notes)

    @Query("SELECT * FROM 'user_table' WHERE id =:id")
    suspend fun getCurrentNote(id:Int) : Notes

    @Update
    suspend fun updateNotes(notes: Notes)

    @Query("SELECT * FROM 'user_table'")
    suspend fun readAllData(): List<Notes>

    @Query("DELETE FROM 'user_table' ")
    suspend fun deleteDataBase()
}

