package com.example.notesapp.presentation.todo.addTodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.core.snackbar
import com.example.notesapp.data.todo.models.Todo
import com.example.notesapp.databinding.FragmentAddTodoBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddTodoFragment : BottomSheetDialogFragment() {
    lateinit var binding: FragmentAddTodoBinding

    private val viewModel by viewModels<AddTodoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()


    }

    private fun initView() {
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        binding.imageView.setOnClickListener {
            createTodo()
        }
    }

    private fun createTodo() {
        val title = binding.editTextTextPersonName2.text.toString()
        val description = binding.editTextTextPersonName.text.toString()
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())

        if (title.isEmpty()) {
            snackbar(binding.root, "Введите текст")
        } else {
            val todo = Todo(
                0, title, description, currentDate, "grey"
            )
            viewModel.createTodo(todo)
            findNavController().navigate(R.id.action_addTodoFragment_to_todoFragment)
        }
    }

}