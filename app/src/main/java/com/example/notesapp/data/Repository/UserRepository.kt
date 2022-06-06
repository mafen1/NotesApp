package com.example.notesapp.data.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notesapp.data.cache.database.NotesDao
import com.example.notesapp.data.models.Notes

// repository?
class UserRepository(private val notesDao: NotesDao) {

    val readAllData: LiveData<List<Notes>> = notesDao.readAllData()

    suspend fun addNotes(notes: Notes) = notesDao.addNotes(notes)

    suspend fun deleteDataBase() = notesDao.deleteDataBase()

    suspend fun updateNotes(notes: Notes) = notesDao.updateNotes(notes)

    suspend fun getCurrentNotes(id:Int): Notes = notesDao.getCurrentNote(id)
}