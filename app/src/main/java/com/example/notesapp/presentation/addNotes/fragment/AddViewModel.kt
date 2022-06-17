package com.example.notesapp.presentation.addNotes.fragment

import android.app.Application
import androidx.lifecycle.*
import com.example.notesapp.data.cache.database.UserDatabase
import com.example.notesapp.data.models.Notes
import com.example.notesapp.data.Repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {

    private var _note: MutableLiveData<Notes> = MutableLiveData()
    var note: LiveData<Notes> = _note

//    private var _newNote: MutableLiveData<Notes> = MutableLiveData()
//    var  newNote: LiveData<Notes> =  _newNote

    fun getCurrentNotes(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            val noteTemp = repository.getCurrentNotes(id)
            _note.postValue(noteTemp)
        }
    }

    fun updateNotes(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNotes(notes)
        }
    }
    fun addNotes(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNotes(notes)
        }
    }
}