package com.example.notesapp.ui.mainScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityMainBinding

class CreateNotesActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        initView()
        initObserves()
    }
//    private fun initView(){
//        setupActionBarWithNavController(findNavController(R.id.fragment))
//    }
    private fun initObserves(){

    }
}