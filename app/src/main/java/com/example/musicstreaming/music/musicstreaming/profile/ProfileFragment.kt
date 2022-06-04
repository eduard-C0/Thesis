package com.example.musicstreaming.music.musicstreaming.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.example.musicstreaming.R
import com.example.musicstreaming.databinding.ProfileFragmentBinding
import com.example.musicstreaming.music.authentification.WelcomeFragment
import com.example.musicstreaming.music.musicstreaming.MainFragment
import com.example.musicstreaming.music.musicstreaming.favorites.FavoritesViewModel
import com.example.musicstreaming.music.musicstreaming.player.PlayerViewModel
import com.example.musicstreaming.music.musicstreaming.search.SearchViewModel
import com.example.musicstreaming.utils.saveStringIntoSharedPreferences
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment: Fragment() {

    private val viewModel by viewModels<ProfileViewModel>()
    private val favoritesViewModel by activityViewModels<FavoritesViewModel>()
    private val playerViewModel by activityViewModels<PlayerViewModel>()


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
        viewModel.getUserInformation()
        favoritesViewModel.getFavorites()

        viewModel.loadingProgressBar.observe(viewLifecycleOwner){
            if (!it){
                binding.profileLoading.visibility = View.GONE
                binding.profileFullName.visibility = View.VISIBLE
                binding.nickName.visibility = View.VISIBLE
                binding.queueInformation.visibility = View.VISIBLE
                binding.favoritesInformation.visibility = View.VISIBLE
                binding.favoritesContainer.visibility = View.VISIBLE
                binding.playlists.visibility = View.VISIBLE
            }else{
                binding.profileLoading.visibility = View.VISIBLE
                binding.profileFullName.visibility = View.GONE
                binding.nickName.visibility = View.GONE
                binding.queueInformation.visibility = View.GONE
                binding.favoritesInformation.visibility = View.GONE
                binding.favoritesContainer.visibility = View.GONE
                binding.playlists.visibility = View.GONE
            }
        }

        viewModel.userInformation.observe(viewLifecycleOwner){
            if(it != null){
                binding.profileFullName.text = it.firstName + " " + it.lastName
                binding.nickName.text = "@${it.firstName}${it.lastName}".lowercase()
            }
        }

        favoritesViewModel.favoritesList.observe(viewLifecycleOwner){
            if (!it.isNullOrEmpty()){
                binding.numberOfSongsInFavorites.text = it.size.toString()
            }
        }

        playerViewModel.queueTrackList.observe(viewLifecycleOwner){
            if(it != null){
                binding.numberOfSongsInQueue.text = it.size.toString()
            }
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