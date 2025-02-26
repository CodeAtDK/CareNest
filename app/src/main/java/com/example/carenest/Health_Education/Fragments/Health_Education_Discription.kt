package com.example.carenest.Health_Education.Fragments

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.carenest.Health_Education.ViewModel.Health_Education_ViewModel
import com.example.carenest.R
import com.example.carenest.databinding.FragmentHealthEducationBinding
import com.example.carenest.databinding.FragmentHealthEducationDiscriptionBinding
import com.google.firebase.storage.FirebaseStorage

class Health_Education_Discription : Fragment() {

    private var _binding: FragmentHealthEducationDiscriptionBinding? = null
    private val binding get() = _binding!!


    private lateinit var MyViewModel: Health_Education_ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_health__education__discription, container, false)

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_government__scheme__discription, container, false)
        _binding = FragmentHealthEducationDiscriptionBinding.inflate(inflater, container, false)

        MyViewModel = ViewModelProvider(requireActivity()).get(Health_Education_ViewModel::class.java)

        MyViewModel.getData1().observe(viewLifecycleOwner){
            binding.GovernmentSchemeDiscriptionName.setText(it)
        }
        MyViewModel.getData2().observe(viewLifecycleOwner){
            binding.GovernmentSchemeDiscriptionDiscription.setText(it)
        }
        MyViewModel.getData3().observe(viewLifecycleOwner){
            binding.BlogDiscription.setText(it)
        }
        MyViewModel.getData6().observe(viewLifecycleOwner) {
            val storageReference = FirebaseStorage.getInstance().reference.child(it)

            storageReference.downloadUrl.addOnSuccessListener { uri ->
                val imageUrl = uri.toString()
                Glide.with(this)
                    .load(imageUrl)
                    .placeholder(R.drawable.download)
                    .error(R.drawable.download)
                    .into(binding.ImageOfBlog)
            }.addOnFailureListener { exception ->

                Toast.makeText(
                    this@Health_Education_Discription.requireContext(),
                    "Failed to load image",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
//        MyViewModel.getData3().observe(viewLifecycleOwner){
//            binding.GovernmentSchemeBenefits.setText(it)
//        }
//        MyViewModel.getData4().observe(viewLifecycleOwner) {
//            binding.GovenmentSchemeEligibility.setText(it)
//        }
//        MyViewModel.getData5().observe(viewLifecycleOwner){
//            binding.GovernmentSchemeDocumentsRequired.setText(it)
//        }
//        MyViewModel.getData5().observe(viewLifecycleOwner){
//            binding.VideoLink.setText(it)
//
//        }





        binding.VideoLink.setOnClickListener {

            val url = binding.VideoLink.text.toString().trim()

            if (url.isNotEmpty()) {
                val fullUrl = if (url.startsWith("http://") || url.startsWith("https://")) {
                    url
                } else {
                    "http://$url"
                }

                Toast.makeText(requireContext(), fullUrl, Toast.LENGTH_SHORT).show()
                Log.d("URL", fullUrl)

                try {
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse(fullUrl)
                    }
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(requireContext(), "No application can handle this request. Please install a web browser.", Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                }
            } else {
                Toast.makeText(requireContext(), "URL cannot be empty", Toast.LENGTH_SHORT).show()
            }

        }








        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//
//        requireActivity().onBackPressedDispatcher.addCallback(this,object : OnBackPressedCallback(true){
//
//            override fun handleOnBackPressed() {
//
////                if(parentFragmentManager.findFragmentById(R.id.fragment_container) is Home) {
//                Log.d("Tag","${parentFragmentManager.findFragmentById(R.id.fragment_container)}")
////
////
////                }

//                parentFragmentManager.commit {
//                    setReorderingAllowed(true)
//                    replace(
//                        R.id.fragment_container,
//                        Government_Schemes::class.java,
//                        null
//                    ) // Replace with your FragmentContainerView's ID and the new Fragment class
//                    addToBackStack(null)
//
//                }





//            }
//        })
    }

}