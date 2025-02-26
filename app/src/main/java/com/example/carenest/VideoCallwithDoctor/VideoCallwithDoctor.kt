//package com.example.carenest.VideoCallwithDoctor
//
//import android.R.attr.fragment
//import android.content.pm.PackageManager
//import android.os.Bundle
//import android.util.Log
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.commit
//import com.example.carenest.R
//import com.example.carenest.Symptom_Checker.SymtomChecker
//import com.example.carenest.databinding.FragmentVideoCallBinding
//import com.example.carenest.databinding.FragmentVideoCallwithDoctorBinding
//import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallConfig
//import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallFragment
//
//class VideoCallwithDoctor : Fragment() {
//
//    private var _binding: FragmentVideoCallwithDoctorBinding? = null
//    private val binding get() = _binding!!
//
//    val appID: Long = 1771106387
//    val appSign: String = "4f8ea52519d80babb2365418e5d572637e7670e6425d9eb30fce8d3c77a06a5c"
//    private val callID: String = "rakshit" // Example call ID
//    private val userID: String = "dhruva" // Example user ID
//    private val userName: String = "Dhruva" // Example user name
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
////        return inflater.inflate(R.layout.fragment_video_callwith_doctor, container, false)
//        _binding = FragmentVideoCallwithDoctorBinding.inflate(inflater, container, false)
//
//
//
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        addCallFragment()
//    }
//
//    private fun addCallFragment() {
//        try {
//            val config = ZegoUIKitPrebuiltCallConfig.oneOnOneVideoCall()
//            val fragment = ZegoUIKitPrebuiltCallFragment.newInstance(
//                appID, appSign, userID, userName, callID, config
//            )
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.fragment_container, fragment)
//                .commitNow()
//        } catch (e: Exception) {
//            Log.e("VideoCallFragment", "Error adding call fragment", e)
//        }
//    }
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            addCallFragment()
//        } else {
//            // Handle the case where permissions were not granted
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//
//}

package com.example.carenest.VideoCallwithDoctor

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.carenest.R
import com.example.carenest.Symptom_Checker.SymtomChecker
import com.example.carenest.databinding.FragmentVideoCallwithDoctorBinding
import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallConfig
import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallFragment

class VideoCallwithDoctor : Fragment() {

    private var _binding: FragmentVideoCallwithDoctorBinding? = null
    private val binding get() = _binding!!

    val appID: Long = 1771106387
    val appSign: String = "4f8ea52519d80babb2365418e5d572637e7670e6425d9eb30fce8d3c77a06a5c"
    private val callID: String = "rakshit" // Example call ID
    private val userID: String = "dhruva" // Example user ID
    private val userName: String = "Dhruva" // Example user name

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions[Manifest.permission.CAMERA] == true &&
                permissions[Manifest.permission.RECORD_AUDIO] == true
            ) {
                addCallFragment()
            } else {
                // Handle the case where permissions were not granted
                Log.e("VideoCallFragment", "Permissions not granted")
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoCallwithDoctorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermissions()
    }

    private fun requestPermissions() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            addCallFragment()
        } else {
            requestPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO
                )
            )
        }
    }

    private fun addCallFragment() {
        try {
            val config = ZegoUIKitPrebuiltCallConfig.oneOnOneVideoCall()
            val fragment = ZegoUIKitPrebuiltCallFragment.newInstance(
                appID, appSign, userID, userName, callID, config)
//            childFragmentManager.beginTransaction()
//                .replace(R.id.fragment_container1, fragment)
//                .commit() // Changed to commit()
            parentFragmentManager.commit {

                setReorderingAllowed(true)
                replace(
                    R.id.fragment_container1,
                    fragment::class.java,
                    null
                ) // Replace with your FragmentContainerView's ID and the new Fragment class
                addToBackStack(null)

            }
        } catch (e: Exception) {
            Log.e("VideoCallFragment", "Error adding call fragment", e)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}