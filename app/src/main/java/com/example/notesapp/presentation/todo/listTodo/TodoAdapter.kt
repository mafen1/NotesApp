package com.example.notesapp.presentation.todo.listTodo


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.data.todo.models.Todo
import com.example.notesapp.databinding.ItemTodoBinding

class TodoAdapter : ListAdapter<Todo, TodoAdapter.ViewHolder>(ItemComparator()) {

    var todoList = listOf<Todo>()

    inner class ViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            binding.checkBox.text = todo.title
            binding.textView.text = todo.dataTime
        }
    }

    class ItemComparator : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(todoList[position])

    override fun getItemCount(): Int = todoList.size
}