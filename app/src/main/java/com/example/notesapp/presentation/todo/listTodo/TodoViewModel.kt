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
    private val todoRepositoryImpl: TodoRepositoryImpl
) : ViewModel() {

    private var _readAllData: MutableLiveData<MutableList<Todo>> = MutableLiveData()
    var readAllData: LiveData<MutableList<Todo>> = _readAllData

//    private var _sort = MutableLiveData(SortType.PRIORITY)
//    val sort: LiveData<SortType> = _sort

    init {
        fetchTodo()
    }

    private fun fetchTodo() {
        viewModelScope.launch(Dispatchers.IO) {
            _readAllData.postValue(todoRepositoryImpl.readAllData())
        }
    }


    fun deleteDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepositoryImpl.deleteDataBase()
        }
    }

//    fun updateSort(sortType: SortType) {
//        _sort.value = sortType
//    }
    //    fun getData(value: SortType) {
//        viewModelScope.launch(Dispatchers.IO) {
//            when (value) {
//                SortType.PRIORITY -> {
//                    val dataFromDataBase = todoRepositoryImpl.readAllData()
//                    dataFromDataBase.sortedBy { it.priority }
//                    _readAllData.postValue(dataFromDataBase)
//                }
//            }
//        }
//    }
}

