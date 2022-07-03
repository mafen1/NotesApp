package com.example.notesapp.presentation.todo.updateTodo

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.CustomPopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.core.ConstVariables
import com.example.notesapp.core.snackbar
import com.example.notesapp.data.todo.models.Todo
import com.example.notesapp.databinding.FragmentUpdateTodoBinding
import com.example.notesapp.presentation.CreateNotesActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateFragmentTodo : Fragment() {

    lateinit var binding: FragmentUpdateTodoBinding
    private val viewModel by viewModels<UpdateTodoViewModel>()
    private var idCurrentFragmentTodo = 0
    private var currentPriority = 0
    private var selectedColorTodo = "grey"

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
        initView()
    }

    private fun initView() {
        val supportActionBar = (activity as CreateNotesActivity?)?.supportActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        setHasOptionsMenu(true)

        binding.imFlagPriority.setOnClickListener {
            createPopupMenu()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_save, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_btn -> {
                updateDataToDataBase(idCurrentFragmentTodo)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun updateDataToDataBase(id: Int) {
        val title = binding.edTitleTodo.text.toString()
        val description = binding.edDescriptionTodo.text.toString()
        val dataTime = binding.textView2.text.toString()

        if (title.isEmpty()) {
            snackbar(binding.root, "fill in all the fields")
        } else {
            val todo = Todo(
                id, title, description, dataTime, selectedColorTodo, currentPriority
            )
            viewModel.updateTodo(todo)
            snackbar(binding.root, "Successful")

            findNavController().navigate(R.id.action_updateFragmentTodo_to_todoFragment)
        }
    }

    private fun initData() {
        setFragmentResultListener(ConstVariables.keyForUpdateTodo) { _, bundle ->
            val id = bundle.getInt("id")
            val title = bundle.getString("title1").toString()
            val description = bundle.getString("description").toString()
            val data = bundle.getString("data")

            idCurrentFragmentTodo = id
            viewModel.getCurrentTodo(id)
            binding.textView2.text = data
            binding.edTitleTodo.setText(title)
            binding.edDescriptionTodo.setText(description)
        }
    }
    private fun createPopupMenu() {
        val wrapper: Context = ContextThemeWrapper(requireContext(), R.style.PopupMenu)
        val popup = CustomPopupMenu(wrapper, binding.imFlagPriority)

        popup.menu.add(Menu.NONE, 0, Menu.NONE, "Высокий").apply {
            setIcon(R.drawable.ic_baseline_flag_24)
        }
        popup.menu.add(Menu.NONE, 1, Menu.NONE, "Средний").apply {
            setIcon(R.drawable.ic_baseline_flag_2422)
        }
        popup.menu.add(Menu.NONE, 2, Menu.NONE, "Низкий").apply {
            setIcon(R.drawable.ic_baseline_flag_24222)
        }
        popup.menu.add(Menu.NONE, 3, Menu.NONE, "Нет").apply {
            setIcon(R.drawable.ic_baseline_flag_grey)
        }
        popup.setOnMenuItemClickListener {
            when (it.itemId) {
                0 -> {
                    selectedColorTodo = "red"
                    currentPriority = 3
                }
                1 -> {
                    selectedColorTodo = "yellow"
                    currentPriority = 2
                }
                2 -> {
                    selectedColorTodo = "blue"
                    currentPriority = 1
                }
                3 -> {
                    selectedColorTodo = "grey"
                    currentPriority = 0
                }
            }
            return@setOnMenuItemClickListener true
        }
        popup.show()
    }
}


