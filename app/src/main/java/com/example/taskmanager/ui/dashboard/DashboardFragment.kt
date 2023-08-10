package com.example.taskmanager.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.taskmanager.databinding.FragmentDashboardBinding
import com.example.taskmanager.model.Car
import com.example.taskmanager.utils.extension.showToas
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    private val db : FirebaseFirestore by lazy{
        FirebaseFirestore.getInstance()
    }
    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            val data = Car(
                brand = binding.etMarka.text.toString(),
                model = binding.etModel.text.toString()
            )
            db.collection(FirebaseAuth.getInstance().currentUser?.uid.toString())
                .add(data).addOnSuccessListener {
                    binding.etMarka.text?.clear()
                    binding.etModel.text?.clear()
                    showToas("sucsessy")

                }.addOnFailureListener{
                    showToas(it.message.toString())
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}