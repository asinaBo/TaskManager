package com.example.taskmanager.ui.onBoarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ItemOnBoardingBinding
import com.example.taskmanager.utils.extension.loadImage
import com.example.taskmanager.model.OnBoarding

class OnBoardingAdapter(private val onClick:()->Unit) : Adapter<OnBoardingAdapter.OnBoardingViewHolder>(){


    private val data = arrayListOf(

        OnBoarding("Ready to travel ", "Choose your destination,plan your tour,select the tours","https://www.pngall.com/wp-content/uploads/8/World-Travel-PNG-Clipart.png"),
        OnBoarding("Shcedule your trip", "Select the data of trip and make task to do according the schedule", "https://i.pinimg.com/originals/47/38/f4/4738f4e1f2a0217f26495b7a8de5d564.png"),
        OnBoarding("Enjoy your trip ", "Lets enjoy your trip with ready tasks", "https://e7.pngegg.com/pngimages/463/327/png-clipart-car-travel-road-trip-illustration-travel-traveling-by-car-car-accident-backpack-thumbnail.png")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnBoardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
       holder.bind(data.get(position))
    }
    override fun getItemCount(): Int {
        return data.size
    }
    inner class OnBoardingViewHolder(private val binding:ItemOnBoardingBinding):ViewHolder(binding.root){

        fun bind(onBoarding: OnBoarding){
            binding.tvTitle.text = onBoarding.title
            binding.tvDesc.text = onBoarding.desc
            onBoarding.image?.let { binding.ivBoard.loadImage(it)}
            binding.btnStart.isVisible= adapterPosition==data.lastIndex
            binding.skip.isVisible= adapterPosition!=data.lastIndex
            binding.btnStart.setOnClickListener {
                onClick()
            }
            binding.skip.setOnClickListener {
                onClick()
            }
        }
    }
}