package com.example.notesapp.presentation.todo.listTodo

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.R
import com.example.notesapp.core.ConstVariables
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
    ): View {
        binding = FragmentTodoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserves()
        initView()
        initData()

    }

    private fun initView() {
        binding.rvTodo.adapter = todoAdapter
        binding.rvTodo.layoutManager = LinearLayoutManager(requireContext())


        setHasOptionsMenu(true)

        binding.bnvTodo.menu.findItem(R.id.todo).isChecked = true

        binding.btnCreateTodo.setOnClickListener {
            findNavController().navigate(R.id.action_todoFragment_to_addTodoFragment)

        }
        binding.bnvTodo.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.notes -> findNavController().navigate(R.id.action_todoFragment_to_listFragment)
            }
            true
        }
    }

    private fun initObserves() {
        viewModel.readAllData.observe(viewLifecycleOwner) {
            if (it != null) {
                todoAdapter.todoList = it
            }
            todoAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete -> {
                viewModel.deleteDatabase()
//                todoAdapter.todoList = emptyList()
                todoAdapter.notifyDataSetChanged()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun initData() {
        todoAdapter.callBackTodo = { id, title, description, color, dataTime, priority, _ ->

            setFragmentResult(ConstVariables.keyForUpdateTodo, bundleOf(
                "id" to id,
                "title1" to title,
                "description" to description,
                "color" to color,
                "data" to dataTime,
                "priority" to priority
            ))
            findNavController().navigate(R.id.action_todoFragment_to_updateFragmentTodo)
        }
    }

}



