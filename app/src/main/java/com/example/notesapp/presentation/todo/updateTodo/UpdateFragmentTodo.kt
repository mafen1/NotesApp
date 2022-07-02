package com.example.notesapp.presentation.todo.updateTodo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.example.notesapp.core.ConstVariables
import com.example.notesapp.databinding.FragmentUpdateTodoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateFragmentTodo : Fragment() {

    lateinit var binding: FragmentUpdateTodoBinding
    private val viewModel by viewModels<UpdateTodoViewModel>()
    private var idCurrentFragmentTodo = 0
//    private var title: String? = ""
//    private var description = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateTodoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
//        initObserves()
    }
//    private fun initObserves(){
//
//    }
    private fun initData() {
        setFragmentResultListener(ConstVariables.keyForUpdateTodo) { _, bundle ->
            val id = bundle.getInt("id")
            val title = bundle.getString("title1").toString()
            val description = bundle.getString("description").toString()
            idCurrentFragmentTodo = id
            viewModel.getCurrentTodo(id)

            binding.editTextTextPersonName3.setText(title)
            binding.editTextTextPersonName4.setText(description)
        }
    }
}