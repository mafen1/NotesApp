package com.example.notesapp.presentation.todo.listTodo

import android.os.Bundle
import android.util.Log
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
        //todo дать норм название view
        binding.recyclerView.adapter = todoAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        setHasOptionsMenu(true)

        binding.bottomNavigationView.menu.findItem(R.id.todo).isChecked = true

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_todoFragment_to_addTodoFragment)

        }
        binding.bottomNavigationView.setOnItemSelectedListener {
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
//        viewModel.sort.observe(viewLifecycleOwner) {
//            viewModel.getData(it)
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete -> {
                viewModel.deleteDatabase()
                todoAdapter.todoList = emptyList()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun initData() {
        todoAdapter.callBackPosition = { id, title, description, color, priority ->
            // todo вынести в переменную ConstVariables.keyForUpdateTodo
            setFragmentResult(ConstVariables.keyForUpdateTodo, bundleOf(
                "id" to id,
                "title1" to title,
                "description" to description,
                "color" to color,
                "priority" to priority
            ))

            findNavController().navigate(R.id.action_todoFragment_to_updateFragmentTodo)
        }
    }

}



