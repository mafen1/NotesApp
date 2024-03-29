package com.example.notesapp.presentation.notes.listNotes

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentListBinding
import com.example.notesapp.presentation.CreateNotesActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NotesFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val listAdapter = NotesAdapter()
    private val viewModel: NotesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserves()
        initData()
    }

    private fun initView() {
        binding.rvNotes.adapter = listAdapter
        binding.rvNotes.layoutManager = GridLayoutManager(activity, 3)

        setHasOptionsMenu(true)

        editActionBar()
        binding.btnCreateNotes.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        binding.bnvNotes.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.todo -> findNavController().navigate(R.id.action_listFragment_to_todoFragment)
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete -> {
                viewModel.deleteDataBase()
                listAdapter.notesList = emptyList()
                listAdapter.notifyDataSetChanged()
            }

        }
        return super.onOptionsItemSelected(item)
    }


    private fun initObserves() {
        viewModel.readAllData.observe(viewLifecycleOwner) {
            if (it != null) {
                listAdapter.notesList = it
            }
            listAdapter.notifyDataSetChanged()
        }
    }

    private fun initData() {
        // отдаем id другому фрагменту
        listAdapter.callBackPosition = { id ->
            setFragmentResult("key", bundleOf("id" to id))
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
    }

    private fun editActionBar() {
        val supportActionBar = (activity as CreateNotesActivity?)?.supportActionBar
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#10141C")))
    }

}