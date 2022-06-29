package com.example.notesapp.data.todo.Repository

import com.example.notesapp.data.todo.cache.database.TodoDao
import com.example.notesapp.data.todo.models.Todo
import com.example.notesapp.domain.todo.repository.RepositoryTodo
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(private val todoDao: TodoDao) : RepositoryTodo {

    override suspend fun readAllData(): List<Todo> = todoDao.readAllDataTodo()

    override suspend fun addTodo(todo: Todo) = todoDao.addTodo(todo)

    override suspend fun deleteDataBase() = todoDao.deleteDataBaseTodo()

    override suspend fun updateTodo(todo: Todo) = todoDao.updateTodo(todo)

    override suspend fun getCurrentTodo(id: Int): Todo = todoDao.getCurrentTodo(id)


}