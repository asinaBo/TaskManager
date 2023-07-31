package com.example.taskmanager.ui.onBoarding

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.data.local.Pref
import com.example.taskmanager.databinding.FragmentOnBoardingBinding
import com.example.taskmanager.ui.onBoarding.adapter.OnBoardingAdapter


class OnBoardingFragment : Fragment() {
    private lateinit var binding:FragmentOnBoardingBinding
    private val adapter = OnBoardingAdapter(this::onClick)


    private val pref: Pref by lazy {
        Pref(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewpager.adapter=adapter
        binding.indicator.setViewPager(binding.viewpager)

    }
    private fun onClick(){
        pref.onBoardingShowed()
        findNavController().navigateUp()
    }

}
