package com.example.notesapp.data.Repository

import com.example.notesapp.data.dao.NotesDao
import com.example.notesapp.data.models.Notes

// repository?
class UserRepository(private val notesDao: NotesDao) {

//    val readAllData: LiveData<List<Notes>> = notesDao.readAllData()

    suspend fun addNotes(notes: Notes) = notesDao.addNotes(notes)
}