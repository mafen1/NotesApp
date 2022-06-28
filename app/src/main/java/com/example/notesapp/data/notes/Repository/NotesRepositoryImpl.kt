package com.example.notesapp.data.notes.Repository

import com.example.notesapp.data.notes.cache.database.NotesDao
import com.example.notesapp.data.notes.models.Notes
import com.example.notesapp.domain.notes.repository.RepositoryNotes
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(private val notesDao: NotesDao): RepositoryNotes {

    override suspend fun readAllData(): List<Notes> = notesDao.readAllData()

    override suspend fun addNotes(notes: Notes) = notesDao.addNotes(notes)

    override suspend fun deleteDataBase() = notesDao.deleteDataBase()

    override suspend fun updateNotes(notes: Notes) = notesDao.updateNotes(notes)

    override suspend fun getCurrentNotes(id: Int): Notes = notesDao.getCurrentNote(id)

}