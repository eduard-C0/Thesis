package com.example.musicstreaming.music.authentification.register

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.example.musicstreaming.R
import com.example.musicstreaming.commonVO.User
import com.example.musicstreaming.databinding.RegisterFragmentBinding
import com.example.musicstreaming.music.DialogShower
import com.example.musicstreaming.music.authentification.login.LoginFragment
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
        viewModel.loadingProgressBar.observe(viewLifecycleOwner){
            if(it){
                binding.registerLoading.visibility = View.VISIBLE
                binding.registerText.visibility = View.GONE
                binding.loginButton.isEnabled = false
            }
            else{
                binding.registerLoading.visibility = View.GONE
                binding.registerText.visibility = View.VISIBLE
                binding.loginButton.isEnabled = true
            }
        }
        displayStatusMessage()
    }

    private fun displayStatusMessage() {
        viewModel.registerStatus.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(context, "Registered successfully", Toast.LENGTH_SHORT).show()
                showDialogFragment()
            } else {
                Toast.makeText(context, "Already created an account with this email", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDialogFragment(){
        DialogShower().show(parentFragmentManager,DialogShower.TAG)
    }
}