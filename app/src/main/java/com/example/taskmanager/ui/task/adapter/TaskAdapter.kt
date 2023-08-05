package com.example.taskmanager.ui.task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ItemTaskBinding
import com.example.taskmanager.model.Task


class TaskAdapter(private val listener: Listener) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private val list = arrayListOf<Task>()
    var changecolor: Boolean = false



    fun addTask(task: Task){

        list.add(0,task)
        notifyItemChanged(0)
    }

    fun addTasks(tasks: List<Task>){
        list.clear()
        list.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }



    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
       holder.bind(list[position],listener)

    }
    override fun getItemCount(): Int {
        return list.size
    }
    inner class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {


        fun bind(task: Task, listener: Listener) {
            binding.tvTitle.text = task.title
            binding.tvDesc.text = task.desc

            if (changecolor){
                if (position % 2 == 0) {
                    binding.mainC.setBackgroundColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.white
                        )
                    )
                } else {
                    binding.mainC.setBackgroundColor(
                        ContextCompat.getColor(
                            itemView.context,R.color.gray)
                    )

                }
                }

            binding.root.setOnClickListener {
                listener.onClickItem(task)
            }
            itemView.setOnLongClickListener {
               listener.onLongClickItem(task)
                true
            }
        }
    }
        interface Listener{
            fun  onLongClickItem(task : Task)
            fun onClickItem(task: Task)
    }
}