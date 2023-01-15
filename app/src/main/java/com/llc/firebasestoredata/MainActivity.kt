package com.llc.firebasestoredata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.llc.firebasestoredata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

   private val chatRecyclerViewAdapter = ChatItemAdapter()

    private lateinit var navController: NavController

    //private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)

       /* viewModel.chatListLiveData.observe(this, Observer { chatList ->
            chatRecyclerViewAdapter.submitList(chatList)
        })

        binding.rvChat.apply {
            adapter = chatRecyclerViewAdapter
            layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        }

        binding.btnSend.setOnClickListener {
            val title = binding.etSender.text.toString()
            val task = binding.etMessage.text.toString()
            if (title != null && task != null) {
                viewModel.sendMessage(title, task)
            } else {
                Toast.makeText(this, "Please fill", Toast.LENGTH_SHORT).show()
            }
        }*/
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}