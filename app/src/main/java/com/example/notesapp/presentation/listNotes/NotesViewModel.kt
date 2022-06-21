package com.example.notesapp.presentation.listNotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.notes.Repository.UserRepository
import com.example.notesapp.data.notes.models.Notes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
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