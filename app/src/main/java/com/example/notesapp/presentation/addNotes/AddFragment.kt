package com.example.notesapp.presentation.addNotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.data.models.Notes
import com.example.notesapp.databinding.FragmentAddBinding
import com.example.notesapp.presentation.listNotes.Adapter
import com.google.android.material.snackbar.Snackbar

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private val viewModel: SecondViewModel by viewModels<SecondViewModel>()
    private val adapter = Adapter()
    var i = 2
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
    }

    private fun initView() {
        binding.btnSave.setOnClickListener {
            insertDataToDataBase()
        }
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
    }

    private fun insertDataToDataBase() {
        val title = binding.edTitle.text.toString()
        val subTitle = binding.edSubTitle.text.toString()
        val notes = binding.edNotes.text.toString()

        if (title.isEmpty() || subTitle.isEmpty() || notes.isEmpty()) {
            Snackbar.make(
                binding.root,
                "fill in all the fields",
                Snackbar.LENGTH_LONG
            ).show()
        } else {
            val notes = Notes(
                0, title, subTitle, notes
            )
            viewModel.addNotes(notes)
            Snackbar.make(
                binding.root,
                "Successful",
                Snackbar.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
    }

    private fun initData() {
        adapter.callBackPosition = {
            binding.edTitle.setText(viewModel.getCurrentNotes(it).title)
            binding.edSubTitle.setText(viewModel.getCurrentNotes(it).subTitle)
            binding.edNotes.setText(viewModel.getCurrentNotes(it).notesText)
        }
        viewModel.updateNotes(viewModel.getCurrentNotes)
    }
}

