package com.example.musicstreaming.music.authentification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.musicstreaming.R
import com.example.musicstreaming.databinding.WelcomeFragmentBinding
import com.example.musicstreaming.music.authentification.login.LoginFragment
import com.example.musicstreaming.music.authentification.register.RegisterFragment
import com.example.musicstreaming.music.musicstreaming.home.HomeFragment

class WelcomeFragment : Fragment() {

    private lateinit var binding: WelcomeFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WelcomeFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButton.setOnClickListener {
            goToRegisterFragment()
        }

        binding.skipForNowButton.setOnClickListener {
            goToMusicHomeScreen()
        }

        binding.signInButton.setOnClickListener {
            goToLoginFragment()
        }
    }

    private fun goToRegisterFragment() {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.main_fragment_container, RegisterFragment())
            addToBackStack("RegisterFragment")
        }
    }

    private fun goToLoginFragment() {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.main_fragment_container, LoginFragment())
            addToBackStack("LoginFragment")
        }
    }

    private fun goToMusicHomeScreen() {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.main_fragment_container, HomeFragment())
            addToBackStack("HomeScreen")
        }
    }
}