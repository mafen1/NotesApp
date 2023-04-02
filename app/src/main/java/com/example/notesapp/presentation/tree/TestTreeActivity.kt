package com.example.notesapp.presentation.tree

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityTestTreeBinding
import java.lang.String
import kotlin.Int
import kotlin.NumberFormatException

class TestTreeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestTreeBinding
    /*private var adapter: TreeAdapterJava
        get() {
            TODO()
        }
        set(value) {}*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestTreeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //val arrayList = ArrayList(Arrays.asList(*Catalog.values()))
        //adapter = TreeAdapterJava(arrayList, this)
        //binding.list.adapter = adapter
    }

    private var stage: Int = 0

    fun onClick(view: View) {
        val step: Int = try {
            String.valueOf(binding.message.text).toInt()
        } catch (e: NumberFormatException) {
            0
        }
        when (view.id) {
            R.id.button1 -> stage -= step
            R.id.button2 -> stage = step
            R.id.button3 -> stage += step
        }
        binding.arcProgress.progress = stage.toFloat()
    }
}