package com.example.musicstreaming.music.musicstreaming.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicstreaming.R
import com.example.musicstreaming.databinding.FavoritesFragmentBinding
import com.example.musicstreaming.music.DialogShowerError
import com.example.musicstreaming.music.musicstreaming.MainFragment
import com.example.musicstreaming.music.musicstreaming.profile.ProfileFragment
import com.example.musicstreaming.music.musicstreaming.search.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private lateinit var binding: FavoritesFragmentBinding
    private val viewModel by activityViewModels<FavoritesViewModel>()
    private val favoritesAdapter: FavoritesAdapter = FavoritesAdapter(emptyList(), SearchAdapter.OnClickListener { track ->
        if (viewModel.favoritesList.value?.contains(track) == true) {
            viewModel.removeFavorite(track)
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FavoritesFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favoritesRecyclerview.adapter = favoritesAdapter
        viewModel.getFavorites()

        binding.favoritesRecyclerview.addItemDecoration(
            DividerItemDecoration(
                binding.favoritesRecyclerview.context,
                LinearLayoutManager.VERTICAL
            ).also { dividerItemDecoration ->
                dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.bg_divider))
            })

        viewModel.favoritesList.observe(viewLifecycleOwner) {
            if(it.isNullOrEmpty()){
                DialogShowerError("Oops!", "Seems like you don't have any favorite song...", resources.getDrawable(R.drawable.ic_music_celebrate)).show(parentFragmentManager, DialogShowerError.TAG)
            }
            else {
                favoritesAdapter.updateTrackList(it)
            }
        }

        viewModel.favoriteRemoved.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.getFavorites()
            }
        }

        binding.favoritesBackButton.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.main_music_container,ProfileFragment())
                remove(FavoritesFragment())
            }
        }
    }

}