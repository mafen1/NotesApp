package com.example.notesapp.presentation.todo.listTodo


import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.core.snackbar
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
        priority: Int,
        done: Boolean
    ) -> Unit)? = null

    inner class ViewHolder(private val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            binding.checkBox.text = todo.title
            binding.textView.text = todo.dataTime
            binding.colorView.setBackgroundColor(Color.parseColor(todo.color))
            
            when (todo.color) {
                "red" -> {
                    binding.checkBox.buttonTintList = ColorStateList.valueOf(
                        binding.root.context.resources.getColor(R.color.red, null)
                    )
                }
                "yellow" -> {
                    binding.checkBox.buttonTintList = ColorStateList.valueOf(
                        binding.root.context.resources.getColor(R.color.yellow, null)
                    )
                }
                "blue" -> {
                    binding.checkBox.buttonTintList = ColorStateList.valueOf(
                        binding.root.context.resources.getColor(R.color.blue, null)
                    )
                }
                "grey" -> {
                    binding.checkBox.buttonTintList = ColorStateList.valueOf(
                        binding.root.context.resources.getColor(R.color.Grey, null)
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
                    todo.priority,
                    todo.done
                )
            }
            binding.checkBox.setOnClickListener {
                if (binding.checkBox.isChecked){
                    snackbar(binding.root, todo.color)
                    binding.checkBox.setTextColor((binding.root.context.resources.getColor(R.color.Grey, null)))
                    binding.checkBox.buttonTintList = ColorStateList.valueOf(binding.root.context.resources.getColor(R.color.Grey, null))

                    binding.colorView.setBackgroundColor((binding.root.context.resources.getColor(R.color.Grey, null)))
                    binding.textView.setTextColor((binding.root.context.resources.getColor(R.color.Grey, null)))
                    notifyItemMoved(absoluteAdapterPosition,todoList.size - 1)
                }else{

                    when (todo.color) {
                        "red" -> {
                            binding.checkBox.buttonTintList = ColorStateList.valueOf(
                                binding.root.context.resources.getColor(R.color.red, null)
                            )
                        }
                        "yellow" -> {
                            binding.checkBox.buttonTintList = ColorStateList.valueOf(
                                binding.root.context.resources.getColor(R.color.yellow, null)
                            )
                        }
                        "blue" -> {
                            binding.checkBox.buttonTintList = ColorStateList.valueOf(
                                binding.root.context.resources.getColor(R.color.blue, null)
                            )
                        }
                        "grey" -> {
                            binding.checkBox.buttonTintList = ColorStateList.valueOf(
                                binding.root.context.resources.getColor(R.color.Grey, null)
                            )
                        }
                    }
                    binding.colorView.setBackgroundColor(Color.parseColor(todo.color))
                    binding.checkBox.setTextColor((binding.root.context.resources.getColor(R.color.white, null)))
                    binding.textView.setTextColor((binding.root.context.resources.getColor(R.color.white, null)))
                    notifyItemMoved(absoluteAdapterPosition,0)
                }
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
        holder.bind(todoList[position])

    override fun getItemCount(): Int = todoList.size

}