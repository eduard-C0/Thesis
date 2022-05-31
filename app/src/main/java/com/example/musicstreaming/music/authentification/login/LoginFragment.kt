package com.example.musicstreaming.music.authentification.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.example.musicstreaming.R
import com.example.musicstreaming.commonVO.User
import com.example.musicstreaming.databinding.LoginFragmentBinding
import com.example.musicstreaming.music.authentification.WelcomeFragment
import com.example.musicstreaming.music.authentification.register.RegisterFragment
import com.example.musicstreaming.music.musicstreaming.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    companion object {
        private const val TAG = "LoginFragment"
    }

    private val viewModel by viewModels<LoginViewModel>()
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButtonFragment.setOnClickListener {
            val email = binding.loginEmail.text.toString()
            val password = binding.loginPassword.text.toString()
            val user = User("", "", email, password)
            viewModel.login(user)
        }

        viewModel.loadingProgressBar.observe(viewLifecycleOwner) {
            if (it) {
                binding.loginLoading.visibility = View.VISIBLE
                binding.loginText.visibility = View.GONE
                binding.loginButtonFragment.isEnabled = false
            } else {
                binding.loginLoading.visibility = View.GONE
                binding.loginText.visibility = View.VISIBLE
                binding.loginButtonFragment.isEnabled = true
            }
        }
        displayStatusMessage()
    }

    private fun displayStatusMessage() {
        viewModel.loginStatus.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(context, "Successfully signed in", Toast.LENGTH_SHORT).show()
                redirectToMainFragment()
            } else {
                Toast.makeText(context, "Invalid account", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun redirectToMainFragment() {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.main_fragment_container, MainFragment())
            remove(LoginFragment())
        }
    }

}