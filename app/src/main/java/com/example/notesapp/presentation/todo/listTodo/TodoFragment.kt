package com.example.notesapp.presentation.todo.listTodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.notesapp.databinding.FragmentTodoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoFragment : Fragment() {

    private lateinit var binding: FragmentTodoBinding

    private val viewModel by viewModels<TodoViewModel>()
    private val todoAdapter = TodoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserves()
    }

    private fun initObserves(){
        viewModel.readAllData.observe(viewLifecycleOwner, Observer {
            if (it != null){
                todoAdapter.todoList = it
            }
            todoAdapter.notifyDataSetChanged()
        })
    }


}