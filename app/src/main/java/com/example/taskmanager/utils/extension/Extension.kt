package com.example.taskmanager.utils.extension

import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.taskmanager.model.Task


fun ImageView.loadImage(imageUrl: String) {
        Glide.with(this)
            .load(imageUrl)
            .into(this)


}

fun Fragment.showToas(msg: String){
    Toast.makeText(requireContext(),msg,Toast.LENGTH_SHORT).show()
}
