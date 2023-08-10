package com.example.taskmanager.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.taskmanager.databinding.FragmentNotificationsBinding
import com.example.taskmanager.model.Car
import com.example.taskmanager.ui.notifications.adapter.CarAdapter
import com.example.taskmanager.utils.extension.showToas
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    private val db : FirebaseFirestore by lazy{
        FirebaseFirestore.getInstance()
    }
    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val adapter = CarAdapter()


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewNotification.adapter = adapter
        db.collection(auth.currentUser?.uid.toString())
            .get().addOnSuccessListener {
                val data = it.toObjects(Car::class.java)
                adapter.addCars(data)
                Log.d("the", "onV:" + data)

            }.addOnFailureListener{
                showToas(it.message.toString())
            }
    }
}