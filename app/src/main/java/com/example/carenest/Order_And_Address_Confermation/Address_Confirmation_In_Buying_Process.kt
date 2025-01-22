package com.example.carenest.Order_And_Address_Confermation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.example.carenest.R
import com.example.carenest.databinding.FragmentAddressConfirmationInBuyingProcessBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlin.random.Random
import kotlin.toString


class Address_Confirmation_In_Buying_Process : Fragment() {


    private var _binding: FragmentAddressConfirmationInBuyingProcessBinding? = null
    private val binding get() = _binding!!
    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_address__confirmation__in__buying__process, container, false)

        _binding = FragmentAddressConfirmationInBuyingProcessBinding.inflate(inflater,container,false)

        val random = Random.nextInt(0, 1000000000)
        val orderid = Random.nextInt(0, 1000000000)
        val productid = Random.nextInt(0, 1000000000)

        binding.BuyNowButtonOnAddressPage.setOnClickListener(){


            val city = orderdetails(
                binding.nameEditText.text.toString(),
                binding.address.text.toString(),
                binding.Surname.text.toString(),
                binding.Phoneno.text.toString(),
                binding.city.text.toString(),
                binding.State.text.toString(),
                binding.pincode.text.toString(),
                binding.locality.text.toString(),
                orderid.toString(),
                productid.toString()
            )
            db.collection("order details").document("$random").set(city)


            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace(
                    R.id.fragment_container,
                    payment_option::class.java,
                    null
                ) // Replace with your FragmentContainerView's ID and the new Fragment class
                addToBackStack(null)

            }
        }

        return binding.root
    }


}