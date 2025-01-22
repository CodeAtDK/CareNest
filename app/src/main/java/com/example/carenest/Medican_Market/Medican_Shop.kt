package com.example.carenest.Medican_Market

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.carenest.HomeFragment
import com.example.carenest.Medican_Market.MarketPlace.Market_Place_Layout
import com.example.carenest.R
import com.example.carenest.databinding.FragmentMedicanShopBinding

class Medican_Shop : Fragment() {


    private var _binding: FragmentMedicanShopBinding? = null
    private val binding get() = _binding!!

    private lateinit var MyViewModel: Medicine_Shop_ViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment return inflater.inflate(R.layout.fragment_medican__shop, container, false)

        _binding = FragmentMedicanShopBinding.inflate(inflater, container, false)


        MyViewModel = ViewModelProvider(requireActivity()).get(Medicine_Shop_ViewModel::class.java)

        binding.EquipmentMarket.setOnClickListener {

            MyViewModel.setData("Equipments Market")


            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace(
                    R.id.fragment_container,
                    Market_Place_Layout::class.java,
                    null
                ) // Replace with your FragmentContainerView's ID and the new Fragment class
                addToBackStack(null)

                Toast.makeText(this@Medican_Shop.requireActivity(),"IN MARKET PLACE", Toast.LENGTH_SHORT).show()

            }


        }
        binding.SeedsFarmer.setOnClickListener {

            MyViewModel.setData("Seed Market")


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

        return binding.root
    }
}