package com.example.notesapp.presentation.todo.listTodo

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
class TodoViewModel @Inject constructor(
//    private val todoRepositoryImpl: TodoRepositoryImpl
) : ViewModel() {

//    private var _readAllData: MutableLiveData<List<Todo>> = MutableLiveData()
//    var readAllData: LiveData<List<Todo>> = _readAllData
//
//    init {
//        fetchTodo()
//    }
//
//    private fun fetchTodo(){
//        viewModelScope.launch(Dispatchers.IO) {
//            todoRepositoryImpl.readAllData()
//        }
//    }
//    private fun deleteDatabase() {
//        viewModelScope.launch(Dispatchers.IO) {
//            todoRepositoryImpl.deleteDataBase()
//        }
//    }
}