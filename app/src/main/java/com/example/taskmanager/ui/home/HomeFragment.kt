package com.example.taskmanager.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentHomeBinding
import com.example.taskmanager.model.Task
import com.example.taskmanager.ui.task.adapter.TaskAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val adapter = TaskAdapter(this::onClickItem)
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        val data = App.db.taskDao().getAll()
        adapter.addTasks(data)

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    private fun onClickItem(task: Task) {
        deleteItem(task)
    }

    private fun deleteItem(task: Task) {
        val alertBuilder = AlertDialog.Builder(requireContext())
        alertBuilder.setTitle(getString(R.string.delete))
        alertBuilder.setMessage(getString(R.string.delete_message))
        alertBuilder.setPositiveButton(getString(R.string.yes)) { _, _ ->
            App.db.taskDao().delete(task)
            val data = App.db.taskDao().getAll()
            adapter.addTasks(data)

            Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show()
        }
        alertBuilder.setNeutralButton("Cancel")
        { _, _ ->
        }
        alertBuilder.show()
    }

}




