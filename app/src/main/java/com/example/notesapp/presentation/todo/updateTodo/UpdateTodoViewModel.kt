package com.example.notesapp.presentation.todo.updateTodo

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
class UpdateTodoViewModel @Inject constructor(
   private val todoRepositoryImpl: TodoRepositoryImpl
) : ViewModel() {

    private var _todo: MutableLiveData<Todo> = MutableLiveData()
    var todo: LiveData<Todo> = _todo

    private var _selectedColorTodo: MutableLiveData<String> = MutableLiveData("grey")
    var selectedColorTodo: LiveData<String> = _selectedColorTodo

    private var _currentPriority: MutableLiveData<Int> = MutableLiveData(0)
    var currentPriority: LiveData<Int> = _currentPriority

    private var _idCurrentFragmentTodo: MutableLiveData<Int> = MutableLiveData(0)
    var idCurrentFragmentTodo: LiveData<Int> = _idCurrentFragmentTodo

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
    fun changeSelectedColor(selectedColor: String){
        _selectedColorTodo.value = selectedColor

    }
    fun changeCurrentPriority(currentPriority: Int){
        _currentPriority.value = currentPriority
    }
    fun changeIdCurrentFragment(idCurrentFragment: Int){
        _idCurrentFragmentTodo.value = idCurrentFragment
    }
}