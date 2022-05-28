package com.example.musicstreaming.music.authentification.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.musicstreaming.commonVO.User
import com.example.musicstreaming.databinding.RegisterFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private val viewModel by viewModels<RegisterViewModel>()
    private lateinit var binding: RegisterFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RegisterFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginButton.setOnClickListener {
            val lastName = binding.registerLast.text.toString()
            val firstName = binding.registerFirst.text.toString()
            val email = binding.registerEmail.text.toString()
            val password = binding.registerPassword.text.toString()
            val user = User(firstName, lastName, email, password)
            viewModel.register(user)
        }
        displayStatusMessage()
    }

    private fun displayStatusMessage() {
        viewModel.registerStatus.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(context, "Registered", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}