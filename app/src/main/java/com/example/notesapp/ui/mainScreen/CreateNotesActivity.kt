package com.example.notesapp.ui.mainScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.ui.secondScreen.NotesActivity
import dagger.hilt.android.AndroidEntryPoint

class CreateNotesActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initObserves()
    }
    private fun initView(){
        binding.floatingActionButton.setOnClickListener {
            val i = Intent(this, NotesActivity::class.java)
            startActivity(i)
        }
    }
    private fun initObserves(){

    }
}