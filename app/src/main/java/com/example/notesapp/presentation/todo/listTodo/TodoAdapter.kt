package com.example.notesapp.presentation.todo.listTodo


import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.data.todo.models.Todo
import com.example.notesapp.databinding.ItemTodoBinding


class TodoAdapter : ListAdapter<Todo, TodoAdapter.ViewHolder>(ItemComparator()) {

    var todoList = listOf<Todo>()

    var callBackTodo: ((
        position: Int,
        title: String,
        description: String,
        color: String,
        dataTime: String,
        priority: Int
    ) -> Unit)? = null

    inner class ViewHolder(private val binding: ItemTodoBinding) :

        RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo, context: Context) {

            binding.checkBox.text = todo.title
            binding.textView.text = todo.dataTime
            binding.colorView.setBackgroundColor(Color.parseColor(todo.color))

            when (todo.color) {
                "red" -> {
                    binding.checkBox.buttonTintList = ColorStateList.valueOf(
                        context.resources.getColor(
                            com.example.notesapp.R.color.red, null
                        )
                    )
                }
                "yellow" -> {
                    binding.checkBox.buttonTintList = ColorStateList.valueOf(
                        context.resources.getColor(
                            com.example.notesapp.R.color.yellow, null
                        )
                    )
                }
                "blue" -> {
                    binding.checkBox.buttonTintList = ColorStateList.valueOf(
                        context.resources.getColor(
                            com.example.notesapp.R.color.blue, null
                        )
                    )
                }
                "grey" -> {
                    binding.checkBox.buttonTintList = ColorStateList.valueOf(
                        context.resources.getColor(
                            com.example.notesapp.R.color.Grey, null
                        )
                    )
                }
            }

                binding.rowLayout.setOnClickListener {
                callBackTodo?.invoke(
                    todo.id,
                    todo.title,
                    todo.description,
                    todo.color,
                    todo.dataTime,
                    todo.priority
                )

            }

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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(todoList[position], holder.itemView.context)

    override fun getItemCount(): Int = todoList.size
}