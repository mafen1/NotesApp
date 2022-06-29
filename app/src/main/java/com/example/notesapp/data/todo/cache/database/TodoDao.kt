package com.example.notesapp.data.todo.cache.database

import androidx.room.*
import com.example.notesapp.data.todo.models.Todo

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTodo(todo: Todo)

    @Query("SELECT * FROM 'todo_table' WHERE id =:id")
    suspend fun getCurrentTodo(id: Int): Todo

    @Update
    suspend fun updateTodo(todo: Todo)

    @Query("SELECT * FROM 'todo_table'")
    suspend fun readAllDataTodo(): List<Todo>

    @Query("DELETE FROM 'todo_table' ")
    suspend fun deleteDataBaseTodo()
}