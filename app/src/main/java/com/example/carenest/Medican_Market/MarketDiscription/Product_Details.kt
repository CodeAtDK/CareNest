package com.example.carenest.Medican_Market.MarketDiscription

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.carenest.HomeFragment
import com.example.carenest.Medican_Market.Medicine_Shop_ViewModel
import com.example.carenest.Order_And_Address_Confermation.Address_Confirmation_In_Buying_Process
import com.example.carenest.R
import com.example.carenest.databinding.FragmentProductDetailsBinding



class Product_Details : Fragment() {

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var ViewModel_Prodduct : Medicine_Shop_ViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_product__details, container, false)

        _binding = FragmentProductDetailsBinding.inflate(inflater,container,false)

        ViewModel_Prodduct = ViewModelProvider(requireActivity()).get(Medicine_Shop_ViewModel::class.java)

        ViewModel_Prodduct.getName().observe(viewLifecycleOwner) {
            binding.ProductMarketName.setText(it)
        }
        ViewModel_Prodduct.getDisc().observe(viewLifecycleOwner) {
            binding.ProductMarketDescription.setText(it)
        }
        ViewModel_Prodduct.getImg().observe(viewLifecycleOwner) {

//            val storageReference = FirebaseStorage.getInstance().reference.child(it)

//            storageReference.downloadUrl.addOnSuccessListener { uri ->
//                val imageUrl = uri.toString()
//                Glide.with(this)
//                    .load(imageUrl)
//                    .placeholder(R.drawable.loding)
//                    .error(R.drawable.error)
//                    .into(binding.ProductMarketImage)
//            }.addOnFailureListener { exception ->
//
//                Toast.makeText(thiscom.example.agriconnect.Farmer_Market.MarketDiscription.Product_Details.requireContext(), "Failed to load image", Toast.LENGTH_SHORT).show()
//            }


        }
        ViewModel_Prodduct.getPrice().observe(viewLifecycleOwner) {
            val n = it.toString()
            binding.ProductPrice.setText("$"+n)
        }

        binding.BuyNow.setOnClickListener(){

            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace(
                    R.id.fragment_container,
                    Address_Confirmation_In_Buying_Process::class.java,
                    null
                ) // Replace with your FragmentContainerView's ID and the new Fragment class
                addToBackStack(null)

            }
//            Toast.makeText(this@Product_Details,"done", Toast.LENGTH_SHORT).show()

        }

        binding.RentTheProduct.setOnClickListener(){

           // Toast.makeText(thiscom.example.agriconnect.Farmer_Market.MarketDiscription.Product_Details.requireContext(), "Rent The Product Is Comming Soon", Toast.LENGTH_LONG).show()
        }
        binding.ADDToCart.setOnClickListener(){

            //Toast.makeText(thiscom.example.agriconnect.Farmer_Market.MarketDiscription.Product_Details.requireContext(),  "Cart Is Comming Soon", Toast.LENGTH_LONG).show()
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this,object : OnBackPressedCallback(true){

            override fun handleOnBackPressed() {

//                if(parentFragmentManager.findFragmentById(R.id.fragment_container) is Home) {
                Log.d("Tag","${parentFragmentManager.findFragmentById(R.id.fragment_container)}")
//
//
//                }

//                parentFragmentManager.commit {
//                    setReorderingAllowed(true)
//                    replace(
//                        R.id.fragment_container,
//                        Market_Place_Layout::class.java,
//                        null
//                    ) // Replace with your FragmentContainerView's ID and the new Fragment class
//                    addToBackStack(null)
//
//                }





            }
        })
    }


}