package com.example.carenest


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.carenest.Chating.user_list
import com.example.carenest.Health_Education.Fragments.Health_Education
import com.example.carenest.Medican_Market.Medican_Shop
import com.example.carenest.Symptom_Checker.SymtomChecker
import com.example.carenest.VideoCallwithDoctor.VideoCallwithDoctor
import com.example.carenest.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class HomeFragment : Fragment() {

    private  var _binding: FragmentHomeBinding?= null
    private val binding get() = _binding!!
    private lateinit var userReference: DatabaseReference
    private val currentUser = FirebaseAuth.getInstance().currentUser
    private val senderId = currentUser?.uid ?: "Unknown"




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment\
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        binding.HealthEducation.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace(
                    R.id.fragment_container,
                    Health_Education::class.java,
                    null
                )
                addToBackStack(null)
            }
            Toast.makeText(this@HomeFragment.requireActivity(),"Recommendation", Toast.LENGTH_SHORT).show()
        }

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
        binding.SympytomsCheacker.setOnClickListener {



            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace(
                    R.id.fragment_container,
                    SymtomChecker::class.java,
                    null
                ) // Replace with your FragmentContainerView's ID and the new Fragment class
                addToBackStack(null)

            }

            Toast.makeText(this@HomeFragment.requireActivity(),"In Symptom Checker", Toast.LENGTH_SHORT).show()
        }
        binding.ChatWithDoctor.setOnClickListener {

            fetchUserRole()

            parentFragmentManager.commit {

                setReorderingAllowed(true)
                replace(
                    R.id.fragment_container,
                    user_list::class.java,
                    null
                ) // Replace with your FragmentContainerView's ID and the new Fragment class
                addToBackStack(null)

            }

            Toast.makeText(this@HomeFragment.requireActivity(),"In Chating", Toast.LENGTH_SHORT).show()
        }

        binding.VideoCall.setOnClickListener {

            fetchUserRole()

//            parentFragmentManager.commit {
//
//                setReorderingAllowed(true)
//                replace(
//                    R.id.fragment_container,
//                    VideoCallwithDoctor::class.java,
//                    null
//                ) // Replace with your FragmentContainerView's ID and the new Fragment class
//                addToBackStack(null)
//
//            }

            Toast.makeText(this@HomeFragment.requireActivity(),"In video call", Toast.LENGTH_SHORT).show()
        }



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null


    }

    private fun fetchUserRole() {
        userReference = FirebaseDatabase.getInstance().getReference("users").child(senderId)
        userReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                if (user != null) {
                    val userRole = user.role ?: "patient"
                    openUserListFragment(userRole)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("HomeFragment", "Error fetching user role: ${error.message}")
            }
        })
    }

    private fun openUserListFragment(userRole: String) {
        val userListFragment = user_list().apply {
            arguments = Bundle().apply {
                putString("role", userRole)
            }
        }

        fragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, userListFragment)
            ?.addToBackStack(null)
            ?.commit()
    }
}
