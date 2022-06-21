package com.example.notesapp.data.notes.Repository

import com.example.notesapp.data.notes.cache.database.NotesDao
import com.example.notesapp.data.notes.models.Notes
import javax.inject.Inject

class UserRepository @Inject constructor(private val notesDao: NotesDao) {

    suspend fun readAllData(): List<Notes> = notesDao.readAllData()

    suspend fun addNotes(notes: Notes) = notesDao.addNotes(notes)

    suspend fun deleteDataBase() = notesDao.deleteDataBase()

    suspend fun updateNotes(notes: Notes) = notesDao.updateNotes(notes)

    suspend fun getCurrentNotes(id:Int): Notes = notesDao.getCurrentNote(id)

}