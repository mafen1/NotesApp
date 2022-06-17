package com.example.notesapp.data.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notesapp.data.cache.database.NotesDao
import com.example.notesapp.data.models.Notes
import javax.inject.Inject

// repository?
class UserRepository @Inject constructor(private val notesDao: NotesDao) {

    suspend fun readAllData(): List<Notes> = notesDao.readAllData()

    suspend fun addNotes(notes: Notes) = notesDao.addNotes(notes)

    suspend fun deleteDataBase() = notesDao.deleteDataBase()

    suspend fun updateNotes(notes: Notes) = notesDao.updateNotes(notes)

    suspend fun getCurrentNotes(id:Int): Notes = notesDao.getCurrentNote(id)
}