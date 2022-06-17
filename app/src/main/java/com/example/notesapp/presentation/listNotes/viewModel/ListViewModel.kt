package com.example.notesapp.presentation.listNotes.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.notesapp.data.Repository.UserRepository
import com.example.notesapp.data.cache.database.UserDatabase
import com.example.notesapp.data.models.Notes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {

    private var _readAllData : MutableLiveData<List<Notes>> = MutableLiveData()
    val readAllData : LiveData<List<Notes>> = _readAllData

    init {
        fetchNotes()
    }

    fun deleteDataBase(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteDataBase()
        }
    }
    private fun fetchNotes(){
        viewModelScope.launch(Dispatchers.IO){
            val notes = repository.readAllData()
            _readAllData.postValue(notes)
        }
    }
}