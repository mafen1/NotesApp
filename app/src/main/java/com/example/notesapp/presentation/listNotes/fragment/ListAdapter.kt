package com.example.notesapp.presentation.listNotes.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.data.models.Notes
import com.example.notesapp.databinding.ItemNotesBinding

class ListAdapter :
    ListAdapter<Notes, com.example.notesapp.presentation.listNotes.fragment.ListAdapter.ViewHolder>(
        ItemComparator()
    ) {

    var callBackPosition: ((position: Int) -> Unit)? = null

    var notesList = listOf<Notes>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    inner class ViewHolder(
        private val binding: ItemNotesBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(notesList: Notes) {
            binding.apply {
                tvTitle.text = notesList.title
                tvSubTitle.text = notesList.subTitle
                tvNotes.text = notesList.notesText
            }
            binding.rowLayout.setOnClickListener {
                callBackPosition?.invoke(notesList.id)
            }

        }
    }

    class ItemComparator : DiffUtil.ItemCallback<Notes>() {
        override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notesList[position])
    }

    override fun getItemCount(): Int = notesList.size


}