package com.example.notesapp.presentation.addNotes.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.data.models.Notes
import com.example.notesapp.databinding.FragmentAddBinding

import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private val viewModel: AddViewModel by viewModels()

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
        setFragmentResultListener("key"){ _, bundle ->
            val id = bundle.getInt("id")
            viewModel.getCurrentNotes(id)
            Log.d("TAG", id.toString())
        }
    }
    private fun initObserves(){
        viewModel.note.observe(viewLifecycleOwner){
            binding.edTitle.setText(it.title)
            binding.edSubTitle.setText(it.subTitle)
            binding.edNotes.setText(it.notesText)
            binding.btnSave.visibility = View.GONE
        }
    }
}

