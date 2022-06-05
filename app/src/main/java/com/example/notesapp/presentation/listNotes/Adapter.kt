package com.example.notesapp.presentation.listNotes

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.data.models.Notes
import com.example.notesapp.databinding.ItemNotesBinding

class Adapter : ListAdapter<Notes, Adapter.ViewHolder>(ItemComparator()) {
    var notesList = emptyList<Notes>()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    inner class ViewHolder(
        val binding: ItemNotesBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(notesList: List<Notes>) {
            val notes = notesList[absoluteAdapterPosition]
            binding.apply {

                tvTitle.text = notes.title
                tvSubTitle.text = notes.subTitle
                tvNotes.text = notes.notesText

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
        holder.bind(notesList)
    }
    override fun getItemCount(): Int {
        return notesList.size
    }

}