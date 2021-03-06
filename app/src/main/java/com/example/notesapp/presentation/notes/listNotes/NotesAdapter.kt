package com.example.notesapp.presentation.notes.listNotes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.data.notes.models.Notes
import com.example.notesapp.databinding.ItemNotesBinding

class NotesAdapter :
    ListAdapter<Notes, NotesAdapter.ViewHolder>(
        ItemComparator()
    ) {

    var callBackPosition: ((position: Int) -> Unit)? = null

    var notesList = listOf<Notes>()


    inner class ViewHolder(
        private val binding: ItemNotesBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(notes: Notes) {
            binding.apply {
                tvTitle.text = notes.title
                tvSubTitle.text = notes.subTitle
                tvNotes.text = notes.notesText
            }
            binding.rowLayout.setOnClickListener {
                callBackPosition?.invoke(notes.id)
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(notesList[position])


    override fun getItemCount(): Int = notesList.size


}