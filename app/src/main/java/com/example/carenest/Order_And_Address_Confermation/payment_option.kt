package com.example.carenest.Order_And_Address_Confermation

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.commit
import com.example.carenest.Medican_Market.MarketPlace.Market_Place_Layout
import com.example.carenest.R
import com.example.carenest.databinding.FragmentPaymentOptionBinding


class payment_option : Fragment() {

    private var _binding: FragmentPaymentOptionBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_payment_option, container, false)

        _binding = FragmentPaymentOptionBinding.inflate(inflater,container,false)

//        Checkout.preload(context)
//        val co = Checkout()
//        co.setKeyID("")
        var option = 0
        binding.radioButton2.setOnClickListener(){

            option = 2

            //  Toast.makeText(context,"Payment option not available",Toast.LENGTH_SHORT).show()

            //initPayment()

        }
        binding.radioButton3.visibility = View.INVISIBLE
        binding.radioButton4.visibility = View.INVISIBLE
        binding.radioButton3.setOnClickListener(){

            Toast.makeText(context,"Payment option not available",Toast.LENGTH_SHORT).show()
        }
        binding.radioButton4.setOnClickListener(){

            Toast.makeText(context,"Payment option not available",Toast.LENGTH_SHORT).show()
        }

        binding.radioButton1.setOnClickListener(){

            option = 1
            //  Toast.makeText(context,"Order Confirm",Toast.LENGTH_SHORT).show()

//            parentFragmentManager.commit {
//                setReorderingAllowed(true)
//                replace(
//                    R.id.fragment_container,
//                    Home::class.java,
//                    null
//                ) // Replace with your FragmentContainerView's ID and the new Fragment class
//                addToBackStack(null)
//
//            }
        }

        binding.buttontocnfirmyourorder.setOnClickListener(){

            if(option == 1){

                Toast.makeText(context,"Your Order is Succesfully placed",Toast.LENGTH_SHORT).show()

                parentFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace(
                        R.id.fragment_container,
                        Market_Place_Layout::class.java,
                        null
                    ) // Replace with your FragmentContainerView's ID and the new Fragment class
                    addToBackStack(null)

                }


            }
            else if(option == 2) {
                val upiId = "8830448351@ybl"
                val name = "Agri CONNECT"
                val note = "Product Name"
                val amount = "10.00"
                val uri = Uri.parse("upi://pay")
                    .buildUpon()
                    .appendQueryParameter("pa", upiId)
                    .appendQueryParameter("pn", name)
                    .appendQueryParameter("tn", note)
                    .appendQueryParameter("am", amount)
                    .appendQueryParameter("cu", "INR").build()
                val intent = Intent(Intent.ACTION_VIEW, uri)
                val chooser = Intent.createChooser(intent, "Pay with")
                if (chooser.resolveActivity(requireActivity().packageManager) != null) {
                    upiPaymentResultLauncher.launch(chooser)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "No UPI app found, please install one to continue",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        return binding.root
    }

    private val upiPaymentResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result -> if (result.resultCode == Activity.RESULT_OK || result.resultCode == 11) {
        result.data?.getStringExtra("response")?.let {
            if (it.contains("success")) {
                Toast.makeText(requireContext(), "Transaction successful", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(requireContext(), "Transaction failed", Toast.LENGTH_SHORT).show()
            }
        } ?: run {
            Toast.makeText(requireContext(), "Transaction cancelled", Toast.LENGTH_SHORT).show()
        }
    }
    else {
        Toast.makeText(requireContext(), "Transaction failed or cancelled", Toast.LENGTH_SHORT).show()
    }
    }


}