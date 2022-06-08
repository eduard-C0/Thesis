package com.example.musicstreaming.music.authentification.register

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.musicstreaming.R
import com.example.musicstreaming.commonVO.User
import com.example.musicstreaming.databinding.RegisterFragmentBinding
import com.example.musicstreaming.music.DialogShowerError
import com.example.musicstreaming.music.DialogShowerRegister
import com.example.musicstreaming.utils.afterTextChanged
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private val viewModel by viewModels<RegisterViewModel>()
    private lateinit var binding: RegisterFragmentBinding
    private var validFirst = false
    private var validLast = false
    private var validPassword = false
    private var validConfirmation = false
    private var validEmail = false
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

        validateRegisterInput()
        binding.registerButtonFragment.isEnabled = validLast && validFirst && validEmail && validPassword && validConfirmation

        binding.registerButtonFragment.setOnClickListener {
            if(validLast && validFirst && validEmail && validPassword && validConfirmation) {
                val lastName = binding.registerLast.text.toString()
                val firstName = binding.registerFirst.text.toString()
                val email = binding.registerEmail.text.toString()
                val password = binding.registerPassword.text.toString()
                val user = User(firstName, lastName, email, password)
                viewModel.register(user)
            }
        }

        viewModel.loadingProgressBar.observe(viewLifecycleOwner) {
            if (it) {
                binding.registerLoading.visibility = View.VISIBLE
                binding.registerText.visibility = View.GONE
                binding.registerButtonFragment.isEnabled = false
            } else {
                binding.registerLoading.visibility = View.GONE
                binding.registerText.visibility = View.VISIBLE
                binding.registerButtonFragment.isEnabled = true
            }
        }
        displayStatusMessage()
    }

    private fun displayStatusMessage() {
        viewModel.registerStatus.observe(viewLifecycleOwner) {
            if (it) {
                DialogShowerRegister().show(parentFragmentManager, DialogShowerRegister.TAG)
            } else {
                DialogShowerError("Oops!", "Somebody already used this email or the server could not be reached.", resources.getDrawable(R.drawable.ic_register_error)).show(parentFragmentManager,DialogShowerError.TAG)
            }
        }
    }


    private fun validateRegisterInput() {
        binding.registerLast.afterTextChanged {
            if (binding.registerLast.text.length < 2) {
                binding.registerLast.error = "Last name must have at least 2 characters!"
                validLast = false
            } else {
                validLast = true
            }
        }
        binding.registerFirst.afterTextChanged {
            if (binding.registerFirst.text.length < 2) {
                binding.registerFirst.error = "First name must have at least 2 characters!"
                validFirst = false
            } else {
                validFirst = true
            }
        }

        binding.registerPassword.afterTextChanged {
            if (binding.registerPassword.text.length < 6) {
                binding.registerPassword.error = "Password must have at least 6 characters!"
                validPassword = false
            } else {
                validPassword = true
            }
        }

        binding.registerConfirmPassword.afterTextChanged {
            if (binding.registerConfirmPassword.text.toString() != binding.registerPassword.text.toString()) {
                binding.registerConfirmPassword.error = "Password does not match!"
                validConfirmation = false
            } else {
                binding.registerConfirmPassword.error = null
                validConfirmation = true
            }
        }

        binding.registerEmail.afterTextChanged {
            if (!Patterns.EMAIL_ADDRESS.matcher(binding.registerEmail.text).matches()) {
                binding.registerEmail.error = "Invalid email address!"
                validEmail = false
            } else {
                validEmail = true
            }
        }
    }

}