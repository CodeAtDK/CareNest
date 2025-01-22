package com.example.carenest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import com.example.carenest.Medican_Market.Medican_Shop
import com.example.carenest.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private  var _binding: FragmentHomeBinding?= null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment\
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.MedicineShop.setOnClickListener {

            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace(
                    R.id.fragment_container,
                    Medican_Shop::class.java,
                    null
                ) // Replace with your FragmentContainerView's ID and the new Fragment class
                addToBackStack(null)

            }

            Toast.makeText(this@HomeFragment.requireActivity(),"IN MARKET PLACE", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null


    }

}