package com.llc.firebasestoredata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.llc.firebasestoredata.model.Chat

class MainViewModel : ViewModel() {
    val chatListLiveData = MutableLiveData<List<Chat>>()

    private val chatNoteReference: DatabaseReference = Firebase.database.reference.child("task")

    init {
        chatNoteReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val chatList = mutableListOf<Chat>()
                snapshot.children.forEach {
                    //if setvalue() doesn't complete,it doesn't return
                    val title = it.child("title").getValue<String>() ?: return@forEach
                    val task = it.child("task").getValue<String>() ?: return@forEach
                    val chatId = it.key ?: return@forEach
                    val chat = Chat(
                        chatId = chatId,
                        sender = title,
                        message = task
                    )
                    chatList.add(chat)
                }
                chatListLiveData.postValue(chatList)
            }

            override fun onCancelled(error: DatabaseError) {
                error.toException().printStackTrace()
            }
        })
    }

    fun sendMessage(sender:String,message: String) {
        chatNoteReference.push().apply {
            child("title").setValue(sender)
            child("task").setValue(message)
        }
    }
}