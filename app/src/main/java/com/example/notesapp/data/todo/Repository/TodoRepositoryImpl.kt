package com.example.notesapp.data.todo.Repository

import com.example.notesapp.data.notes.models.Notes
import com.example.notesapp.data.todo.cache.database.TodoDao
import com.example.notesapp.data.todo.models.Todo import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(private val todoDao: TodoDao) {

    suspend fun readAllData(): List<Todo> = todoDao.readAllData()

    suspend fun addTodo(todo: Todo) = todoDao.addNotes(todo)

    suspend fun deleteDataBase() = todoDao.deleteDataBase()

    suspend fun updateTodo(todo: Todo) = todoDao.updateNotes(todo)

    suspend fun getCurrentTodo(id: Int): Todo = todoDao.getCurrentNote(id)


}