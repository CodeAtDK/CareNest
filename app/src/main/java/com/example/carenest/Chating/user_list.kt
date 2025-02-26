package com.example.carenest.Chating

import User
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carenest.R
import com.example.carenest.UserAdapter
import com.example.carenest.databinding.FragmentUserListBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class user_list : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    private lateinit var userReference: DatabaseReference
    private lateinit var userList: MutableList<User>
    private lateinit var userAdapter: UserAdapter
    private val currentUser = FirebaseAuth.getInstance().currentUser
    private val senderId = currentUser?.uid ?: "Unknown"
    private lateinit var userRole: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_user_list, container, false)
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.root

        userRole = arguments?.getString("role") ?: "patient"

        userReference = FirebaseDatabase.getInstance().getReference("users")
        userList = mutableListOf()
        userAdapter = UserAdapter(userList) { user -> onUserClick(user) }
        binding.recyclerViewUsers.adapter = userAdapter
        binding.recyclerViewUsers.layoutManager = LinearLayoutManager(requireContext())

        fetchUsers()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fetchUsers() {
        userReference.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val user = snapshot.getValue(User::class.java)
                if (user != null && user.role != userRole) {
                    userList.add(user)
                    userAdapter.notifyDataSetChanged()
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onChildRemoved(snapshot: DataSnapshot) {}
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onCancelled(error: DatabaseError) {
                Log.e("UserListFragment", "Error fetching users: ${error.message}")
            }
        })
    }

    private fun onUserClick(user: User) {
        val chatId = getChatId(senderId, user.userId ?: return)
        val bundle = Bundle().apply {
            putString("chatId", chatId)
            putString("userId", user.userId)
            putString("userName", user.userName)
        }
        val chatFragment = chat().apply {
            arguments = bundle
        }
        fragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, chatFragment)
            ?.addToBackStack(null)
            ?.commit()

        // Remove user from the list when chatting
        userList.remove(user)
        userAdapter.notifyDataSetChanged()
    }

    private fun getChatId(userId1: String, userId2: String): String {
        return if (userId1 < userId2) {
            "${userId1}_${userId2}"
        } else {
            "${userId2}_${userId1}"
        }
    }

    fun addUser(user: User) {
        userList.add(user)
        userAdapter.notifyDataSetChanged()
    }

}


