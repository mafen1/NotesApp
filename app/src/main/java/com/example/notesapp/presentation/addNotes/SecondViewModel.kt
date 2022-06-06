package com.example.notesapp.presentation.addNotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.cache.database.UserDatabase
import com.example.notesapp.data.models.Notes
import com.example.notesapp.data.Repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SecondViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Notes>>
     lateinit var getCurrentNotes: Notes
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = userDao.readAllData()
    }
    fun getCurrentNotes(id:Int): Notes{
        viewModelScope.launch(Dispatchers.IO) {
           repository.getCurrentNotes(id)
        }
        return repository.getCurrentNotes(id)
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