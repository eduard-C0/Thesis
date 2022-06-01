package com.example.musicstreaming.music.musicstreaming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.musicstreaming.R
import com.example.musicstreaming.databinding.MainFragmentBinding
import com.example.musicstreaming.music.authentification.login.LoginFragment
import com.example.musicstreaming.music.musicstreaming.home.HomeFragment
import com.example.musicstreaming.music.musicstreaming.profile.ProfileFragment
import com.example.musicstreaming.music.musicstreaming.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        redirectToHomeScree()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabListener()

    }

    private fun redirectToHomeScree() {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.main_music_container, HomeFragment())
            remove(LoginFragment())
        }
    }

    private fun tabListener() {
        binding.navigationBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_search -> {
                    // Write your code here
                    redirectToSearch()
                    return@setOnItemSelectedListener true

                }
                R.id.action_home -> {
                    // Write your code here
                    redirectToHome()
                    return@setOnItemSelectedListener true

                }
                R.id.action_profile -> {
                    // Write code
                    redirectToProfile()
                    return@setOnItemSelectedListener true

                }
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun redirectToSearch(){
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.main_music_container, SearchFragment())
        }
    }

    private fun redirectToProfile(){
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.main_music_container, ProfileFragment())
        }
    }

    private fun redirectToHome(){
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.main_music_container, HomeFragment())
        }
    }

}