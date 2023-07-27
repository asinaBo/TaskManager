package com.example.taskmanager.ui.onBoarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ItemOnBoardingBinding
import com.example.taskmanager.model.OnBoarding

class OnBoardingAdapter(private val onClick:()->Unit) : Adapter<OnBoardingAdapter.OnBoardingViewHolder>(){


    private val data = arrayListOf<OnBoarding>(

        OnBoarding("test 1 ", "Desc 1",""),
        OnBoarding("test 2 ", "Desc 2", ""),
        OnBoarding("test 3 ", "Desc 3", "")
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
           //var url = ""
          // Glide.with(this)
              // .load(url)
              // .fitCenter()
              // .into(binding.ivBoard)


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