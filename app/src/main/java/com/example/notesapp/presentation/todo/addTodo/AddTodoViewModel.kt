package com.example.notesapp.presentation.todo.addTodo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.todo.Repository.TodoRepositoryImpl
import com.example.notesapp.data.todo.models.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTodoViewModel @Inject constructor(
    private val todoRepositoryImpl: TodoRepositoryImpl
) : ViewModel() {

    private var _todo: MutableLiveData<Todo> = MutableLiveData()
    var todo: LiveData<Todo> = _todo

    fun createTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepositoryImpl.addTodo(todo)
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepositoryImpl.updateTodo(todo)
        }
    }

    fun getCurrentTodo(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val tempTodo = todoRepositoryImpl.getCurrentTodo(id)
            _todo.postValue(tempTodo)
        }
    }
}