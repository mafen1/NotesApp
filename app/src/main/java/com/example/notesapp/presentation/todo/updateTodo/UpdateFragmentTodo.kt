package com.example.notesapp.presentation.todo.updateTodo

import android.content.Context
import android.content.res.ColorStateList
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
                updateDataToDataBase(viewModel.idCurrentFragmentTodo.value!!)
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
                id, title, description, dataTime, viewModel.selectedColorTodo.value!!, viewModel.currentPriority.value!!
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
            val color = bundle.getString("color").toString()
            val data = bundle.getString("data")

            viewModel.changeIdCurrentFragment(id)
            viewModel.changeSelectedColor(color)
            viewModel.getCurrentTodo(id)

            binding.textView2.text = data
            binding.edTitleTodo.setText(title)
            binding.edDescriptionTodo.setText(description)

            when(color){
                "red" -> {
                    binding.imFlagPriority.setImageResource(R.drawable.ic_baseline_flag_24)
                    binding.checkBoxTodo.buttonTintList = ColorStateList.valueOf(resources.getColor(R.color.red, null))
                }
                "yellow" -> {
                    binding.imFlagPriority.setImageResource(R.drawable.ic_baseline_flag_2422)
                    binding.checkBoxTodo.buttonTintList = ColorStateList.valueOf(resources.getColor(R.color.yellow, null))
                }
                "blue" -> {
                    binding.imFlagPriority.setImageResource(R.drawable.ic_baseline_flag_24222)
                    binding.checkBoxTodo.buttonTintList = ColorStateList.valueOf(resources.getColor(R.color.blue, null))
                }
                "grey" -> {
                    binding.imFlagPriority.setImageResource(R.drawable.ic_baseline_flag_grey)
                    binding.checkBoxTodo.buttonTintList = ColorStateList.valueOf(resources.getColor(R.color.Grey, null))

                }
            }
        }
    }
    private fun createPopupMenu() {
        val wrapper: Context = ContextThemeWrapper(requireContext(), R.style.PopupMenu)
        val popup = CustomPopupMenu(wrapper, binding.imFlagPriority)
        popup.menu.apply {
            add(Menu.NONE, 0, Menu.NONE, "Высокий").apply {
                setIcon(R.drawable.ic_baseline_flag_24)
            }
            add(Menu.NONE, 1, Menu.NONE, "Средний").apply {
                setIcon(R.drawable.ic_baseline_flag_2422)
            }
            add(Menu.NONE, 2, Menu.NONE, "Низкий").apply {
                setIcon(R.drawable.ic_baseline_flag_24222)
            }
            add(Menu.NONE, 3, Menu.NONE, "Нет").apply {
                setIcon(R.drawable.ic_baseline_flag_grey)
            }
        }
        popup.setOnMenuItemClickListener {
            when (it.itemId) {
                0 -> {
                    viewModel.changeSelectedColor("red")
                    viewModel.changeCurrentPriority(3)

                    binding.checkBoxTodo.buttonTintList = ColorStateList.valueOf(resources.getColor(R.color.red, null))
                    binding.imFlagPriority.setImageResource(R.drawable.ic_baseline_flag_24)
                }
                1 -> {
                    viewModel.changeSelectedColor("yellow")
                    viewModel.changeCurrentPriority(2)
                    binding.checkBoxTodo.buttonTintList = ColorStateList.valueOf(resources.getColor(R.color.yellow, null))
                    binding.imFlagPriority.setImageResource(R.drawable.ic_baseline_flag_2422)
                }
                2 -> {
                    viewModel.changeSelectedColor("blue")
                    viewModel.changeCurrentPriority(1)
                    binding.imFlagPriority.setImageResource(R.drawable.ic_baseline_flag_24222)
                    binding.checkBoxTodo.buttonTintList = ColorStateList.valueOf(resources.getColor(R.color.blue, null))
                }
                3 -> {
                    viewModel.changeSelectedColor("grey")
                    viewModel.changeCurrentPriority(0)
                    binding.checkBoxTodo.buttonTintList = ColorStateList.valueOf(resources.getColor(R.color.Grey, null))
                    binding.imFlagPriority.setImageResource(R.drawable.ic_baseline_flag_grey)
                }
            }
            return@setOnMenuItemClickListener true
        }
        popup.show()
    }
}


