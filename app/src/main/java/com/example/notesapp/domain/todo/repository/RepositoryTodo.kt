package com.example.notesapp.domain.todo.repository

import com.example.notesapp.data.todo.models.Todo

interface RepositoryTodo {

    suspend fun readAllData(): MutableList<Todo>

    suspend fun addTodo(todo: Todo)

    suspend fun deleteDataBase()

    suspend fun updateTodo(todo: Todo)

    suspend fun getCurrentTodo(id: Int): Todo

}