package com.example.musicstreaming.music.musicstreaming.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.example.musicstreaming.databinding.HomeFragmentBinding
import com.example.musicstreaming.databinding.SearchFragmentBinding
import com.example.musicstreaming.music.musicstreaming.search.SearchAdapter
import com.example.musicstreaming.music.musicstreaming.search.SearchViewModel
import com.example.musicstreaming.services.dtos.Artist
import com.example.musicstreaming.services.dtos.TopArtist
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding
    private val viewModel by viewModels<HomeViewModel>()
    private val topArtistAdapter: TopArtistAdapter = TopArtistAdapter(emptyList())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeFragmentRecyclerview.adapter = topArtistAdapter

        viewModel.getTopArtists()

        viewModel.topArtists.observe(viewLifecycleOwner) {
            topArtistAdapter.updateTrackList(it)
        }

        viewModel.loadingProgressBar.observe(viewLifecycleOwner) {
            if (it) {
                binding.homeFragmentRecyclerview.visibility = View.GONE
                binding.homeLoading.visibility = View.VISIBLE
            } else {
                binding.homeLoading.visibility = View.GONE
                binding.homeFragmentRecyclerview.visibility = View.VISIBLE
            }
        }
    }
}
