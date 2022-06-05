package com.example.notesapp.presentation.listNotes

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.Repository.UserRepository
import com.example.notesapp.data.cache.database.UserDatabase
import com.example.notesapp.data.models.Notes
import com.example.notesapp.presentation.listNotes.Adapter.Companion.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application){

    val readAllData: LiveData<List<Notes>>
    private val repository: UserRepository

    init {

        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun deleteDataBase(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteDataBase()
        }
    }
}