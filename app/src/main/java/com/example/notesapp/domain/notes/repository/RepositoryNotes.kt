package com.example.notesapp.domain.notes.repository

import com.example.notesapp.data.notes.models.Notes

// TODO переписать с дженериками
interface RepositoryNotes {

    suspend fun readAllData(): List<Notes>

    suspend fun addNotes(notes: Notes)

    suspend fun deleteDataBase()

    suspend fun updateNotes(notes: Notes)

    suspend fun getCurrentNotes(id: Int): Notes
}