package com.example.notesapp.presentation.notes.addNotes

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
class AddNotesViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {

    private var _note: MutableLiveData<Notes> = MutableLiveData()
    var note: LiveData<Notes> = _note


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