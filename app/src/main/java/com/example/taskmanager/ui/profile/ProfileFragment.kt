package com.example.taskmanager.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.widget.addTextChangedListener
import com.example.taskmanager.R
import com.example.taskmanager.data.local.Pref
import com.example.taskmanager.databinding.FragmentProfileBinding
import com.github.dhaval2404.imagepicker.ImagePicker


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    private val pref: Pref by lazy {
        Pref(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etName.setText(pref.getName())
        binding.etName.addTextChangedListener {
            pref.saveName(binding.etName.text.toString())
        }

        binding.profileImage.setImageURI(pref.getImage().toString().toUri())
        binding.profileImage.setOnClickListener {
            ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()

        }

        //binding.btnSave.setOnClickListener {

        //}


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        binding.profileImage.setImageURI(data?.data)
        pref.saveImage(data?.data.toString())
    }


}