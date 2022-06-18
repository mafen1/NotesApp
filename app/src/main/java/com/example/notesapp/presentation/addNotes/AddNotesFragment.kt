package com.example.notesapp.presentation.addNotes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.core.snackbar
import com.example.notesapp.data.models.Notes
import com.example.notesapp.databinding.FragmentAddBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNotesFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private val viewModel: AddNotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        initObserves()

    }

    private fun initView() {
        binding.btnSave.setOnClickListener {
            insertDataToDataBase()
        }
        binding.btnBack.setOnClickListener {
            navigation()
        }
    }

    private fun insertDataToDataBase() {
        val title = binding.edTitle.text.toString()
        val subTitle = binding.edSubTitle.text.toString()
        val notes = binding.edNotes.text.toString()

        if (title.isEmpty() || subTitle.isEmpty() || notes.isEmpty()) {
            snackbar(binding.root, "fill in all the fields")
        } else {
            val notes = Notes(
                0, title, subTitle, notes
            )
            viewModel.addNotes(notes)
            snackbar(binding.root, "Successful")
            navigation()
        }
    }

    private fun updateDataToDataBase(id: Int) {
        val title = binding.edTitle.text.toString()
        val subTitle = binding.edSubTitle.text.toString()
        val notes = binding.edNotes.text.toString()

        if (title.isEmpty() || subTitle.isEmpty() || notes.isEmpty()) {
            snackbar(binding.root, "fill in all the fields")
        } else {
            val notes = Notes(
                id, title, subTitle, notes
            )
            viewModel.updateNotes(notes)
            snackbar(binding.root, "Successful")
            navigation()
        }
    }

    private fun initData() {
        setFragmentResultListener("key") { _, bundle ->
            val id = bundle.getInt("id")
            viewModel.getCurrentNotes(id)
            Log.d("TAG", id.toString())
        }
    }

    private fun initObserves() {
        viewModel.note.observe(viewLifecycleOwner) { notes ->

            binding.edTitle.setText(notes.title)
            binding.edSubTitle.setText(notes.subTitle)
            binding.edNotes.setText(notes.notesText)
            binding.btnSave.text = "Update Text"

            binding.btnSave.setOnClickListener {
                updateDataToDataBase(notes.id)
            }

        }
    }

    private fun navigation() = findNavController().navigate(R.id.action_addFragment_to_listFragment)

}

