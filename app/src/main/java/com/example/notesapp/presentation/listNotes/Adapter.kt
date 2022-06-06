package com.example.notesapp.presentation.listNotes

import android.app.DirectAction
import android.content.Intent
import android.graphics.Path
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.data.models.Notes
import com.example.notesapp.databinding.ItemNotesBinding
import com.example.notesapp.presentation.addNotes.AddFragment

class Adapter : ListAdapter<Notes, Adapter.ViewHolder>(ItemComparator()) {

    var callBackPosition: ((position: Int) -> Unit)? = null

    var notesList = emptyList<Notes>()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    inner class ViewHolder(
        private val binding: ItemNotesBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(notesList: List<Notes>) {
            val notes = notesList[absoluteAdapterPosition]
            binding.apply {

                tvTitle.text = notes.title
                tvSubTitle.text = notes.subTitle
                tvNotes.text = notes.notesText
            }
            binding.rowLayout.setOnClickListener{
                callBackPosition?.invoke(absoluteAdapterPosition)
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