package com.example.carenest.Chating

import Message
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carenest.MessageAdapter
import com.example.carenest.R
import com.example.carenest.databinding.FragmentChatBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class chat : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private lateinit var databaseReference: DatabaseReference
    private lateinit var messageList: MutableList<Message>
    private lateinit var messageAdapter: MessageAdapter
    private val currentUser = FirebaseAuth.getInstance().currentUser
    private val senderId = currentUser?.uid ?: "Unknown"
    private var chatEnded = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        val view = binding.root

        val userId = arguments?.getString("userId") ?: return view
        val chatId = getChatId(senderId, userId)

        databaseReference = FirebaseDatabase.getInstance().getReference("chats").child(chatId).child("messages")
        messageList = mutableListOf()
        messageAdapter = MessageAdapter(messageList, senderId)
        binding.recyclerViewMessages.adapter = messageAdapter
        binding.recyclerViewMessages.layoutManager = LinearLayoutManager(requireContext())

        binding.sendButton.setOnClickListener {
            sendMessage()
        }

        receiveMessages()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        if (chatEnded) {
            // Re-add user to the list when chat ends
            val userReference = FirebaseDatabase.getInstance().getReference("users").child(arguments?.getString("userId") ?: "")
            userReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(User::class.java)
                    if (user != null) {
                        // Add user back to the list
                        val userListFragment = fragmentManager?.findFragmentById(R.id.fragment_container) as? user_list
                        userListFragment?.addUser(user)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("ChatFragment", "Error re-adding user: ${error.message}")
                }
            })
        }
    }

    private fun sendMessage() {
        val messageText = binding.messageInput.text.toString()
        if (messageText.isNotEmpty()) {
            val messageObject = Message(senderId, messageText, System.currentTimeMillis(), false)
            databaseReference.push().setValue(messageObject)
            binding.messageInput.text.clear()
        }
    }

    private fun receiveMessages() {
        databaseReference.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val message = snapshot.getValue(Message::class.java)
                if (message != null) {
                    messageList.add(message)
                    messageAdapter.notifyItemInserted(messageList.size - 1)
                    binding.recyclerViewMessages.scrollToPosition(messageList.size - 1)
                    markMessageAsSeen(snapshot.key ?: "")
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onChildRemoved(snapshot: DataSnapshot) {}
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onCancelled(error: DatabaseError) {
                Log.e("ChatFragment", "Error fetching messages: ${error.message}")
            }
        })
    }

    private fun markMessageAsSeen(messageId: String) {
        databaseReference.child(messageId).child("seen").setValue(true)
    }

    private fun getChatId(userId1: String, userId2: String): String {
        return if (userId1 < userId2) {
            "${userId1}_${userId2}"
        } else {
            "${userId2}_${userId1}"
        }
    }
}
