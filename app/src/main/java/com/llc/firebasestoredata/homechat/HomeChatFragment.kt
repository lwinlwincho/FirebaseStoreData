package com.llc.firebasestoredata.homechat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.llc.firebasestoredata.ChatItemAdapter
import com.llc.firebasestoredata.OnItemClickListener
import com.llc.firebasestoredata.databinding.FragmentHomeChatBinding
import com.llc.firebasestoredata.model.Chat

class HomeChatFragment : Fragment(), OnItemClickListener {

    private var _binding: FragmentHomeChatBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeChatViewModel by viewModels()

    private val chatItemAdapter = ChatItemAdapter()

    /*private val chatItemAdapter: ChatItemAdapter by lazy {
        ChatItemAdapter()
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeChatBinding.inflate(inflater, container, false)
        return binding.root

        viewModel.chatListLiveData.observe(viewLifecycleOwner) { chatList ->
            chatItemAdapter.submitList(chatList)
        }

        binding.rvChat.apply {
            adapter = chatItemAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }

        binding.btnSend.setOnClickListener {
            val title = binding.etSender.text.toString()
            val task = binding.etMessage.text.toString()
            if (title != null && task != null) {
                viewModel.sendMessage(title, task)
            } else {
                Toast.makeText(requireContext(), "Please fill", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCompleteTask(taskEntity: Chat) {
        TODO("Not yet implemented")
    }

    override fun openDetails(taskEntity: Chat) {
        TODO("Not yet implemented")
    }
}