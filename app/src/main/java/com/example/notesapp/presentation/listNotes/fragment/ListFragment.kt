package com.example.notesapp.presentation.listNotes.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notesapp.R
import com.example.notesapp.data.Repository.UserRepository
import com.example.notesapp.data.cache.database.UserDatabase
import com.example.notesapp.databinding.FragmentListBinding
import com.example.notesapp.presentation.listNotes.viewModel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private val listAdapter = ListAdapter()
    private val viewModel: ListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        initView()
        initObserves()
        initData()
    }

    private fun initView() {
        binding.recyclerView.adapter = listAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(activity, 3)

        //TODO сделать норм ViewModel без lateinit
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }



    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete -> viewModel.deleteDataBase()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun initObserves(){
        viewModel.readAllData.observe(viewLifecycleOwner, Observer {
            if (it != null) { listAdapter.notesList = it }
            listAdapter.notifyDataSetChanged()
        })
    }

    private fun initData(){
        // отдаем id другому фрагменту
        listAdapter.callBackPosition ={ id ->
            setFragmentResult("key", bundleOf("id" to id))
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
    }
}