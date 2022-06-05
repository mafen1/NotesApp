package com.example.notesapp.data.cache.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notesapp.data.models.Notes

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNotes(notes: Notes)


    @Query("SELECT * FROM 'user_table' ORDER BY id ASC")
    fun readAllData(): LiveData<List<Notes>>

    @Query("DELETE FROM 'user_table' ")
    suspend fun deleteDataBase()
}

