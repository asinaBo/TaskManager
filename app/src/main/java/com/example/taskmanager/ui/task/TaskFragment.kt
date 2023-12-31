package com.example.taskmanager.ui.task

import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentTaskBinding
import com.example.taskmanager.model.Task
import com.example.taskmanager.ui.home.HomeFragment.Companion.TASK_KEY


class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        task = arguments?.getSerializable(TASK_KEY) as Task?
        if (task != null) {
            etTitle.setText(task?.title.toString())
            etDesc.setText(task?.desc.toString())
            btnSave.setText(getString(R.string.update))
        }

        btnSave.setOnClickListener {
            if (etTitle.length() == 0 && etDesc.length() == 0) { //isNullorEmpty
                etTitle.error = getString(R.string.empty_field)
                etDesc.error = getString(R.string.empty_desc)
                return@setOnClickListener
            }

            if (task == null) {
                save()
            } else {
                update()
            }
            findNavController().navigateUp()
        }

    }

    private fun update() {
        val data = task?.copy(
            title = binding.etTitle.text.toString(),
            desc = binding.etDesc.text.toString()
        )
        if (data != null) {
            App.db.taskDao().update(data)
        }
    }

    private fun save() {
        val data = Task(
            title = binding.etTitle.text.toString(),
            desc = binding.etDesc.text.toString()
        )
        App.db.taskDao().insert(data)
    }

}