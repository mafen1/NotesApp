package com.example.notesapp.ui.secondScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.database.UserDatabase
import com.example.notesapp.data.models.Notes
import com.example.notesapp.data.Repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SecondViewModel(application: Application): AndroidViewModel(application) {

    //    private val readAllData: LiveData<List<Notes>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
//        readAllData = userDao.readAllData()
    }

    fun addNotes(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNotes(notes)
        }
    }
}