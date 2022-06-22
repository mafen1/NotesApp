package com.example.notesapp.data.todo.cache.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notesapp.data.todo.models.Todo

interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNotes(todo: Todo)

    @Query("SELECT * FROM 'todo_table' WHERE id =:id")
    suspend fun getCurrentNote(id:Int) : Todo

    @Update
    suspend fun updateNotes(todo: Todo)

    @Query("SELECT * FROM 'todo_table'")
    suspend fun readAllData(): List<Todo>

    @Query("DELETE FROM 'todo_table' ")
    suspend fun deleteDataBase()
}