package com.example.carenest

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import android.util.Log
import com.example.carenest.databinding.FragmentHomeBinding
import com.example.carenest.databinding.FragmentVideoCallBinding

import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallConfig
import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallFragment


class VideoCallFragment : Fragment() {

    private var _binding: FragmentVideoCallBinding? = null
    private val binding get() = _binding!!


    private val callID: String = "dhruva112003gmailcom" // Example call ID
    private val userID: String = "dhruva112gmailcom" // Example user ID
    private val userName: String = "Dhruva" // Example user name

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_video_call, container, false)

        _binding = FragmentVideoCallBinding.inflate(inflater, container, false)


        return binding.root
    }

    private fun setupVideoCall() {


    }


}


