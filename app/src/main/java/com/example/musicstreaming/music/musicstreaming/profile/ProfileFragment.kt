package com.example.musicstreaming.music.musicstreaming.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.musicstreaming.R
import com.example.musicstreaming.databinding.ProfileFragmentBinding
import com.example.musicstreaming.music.authentification.WelcomeFragment
import com.example.musicstreaming.music.musicstreaming.MainFragment
import com.example.musicstreaming.utils.saveStringIntoSharedPreferences

class ProfileFragment: Fragment() {
    private lateinit var binding: ProfileFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logoutButton.setOnClickListener {
            context?.let { context -> saveStringIntoSharedPreferences(context,"Token","") }
            redirectToWelcomeScreen()
        }
    }

    private fun redirectToWelcomeScreen() {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.main_fragment_container, WelcomeFragment())
            remove(MainFragment())
        }
    }
}