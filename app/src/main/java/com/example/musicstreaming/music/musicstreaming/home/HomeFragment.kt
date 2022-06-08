package com.example.musicstreaming.music.musicstreaming.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.example.musicstreaming.R
import com.example.musicstreaming.databinding.HomeFragmentBinding
import com.example.musicstreaming.databinding.SearchFragmentBinding
import com.example.musicstreaming.music.DialogShowerError
import com.example.musicstreaming.music.musicstreaming.player.PlayerViewModel
import com.example.musicstreaming.music.musicstreaming.search.SearchAdapter
import com.example.musicstreaming.music.musicstreaming.search.SearchViewModel
import com.example.musicstreaming.services.dtos.Artist
import com.example.musicstreaming.services.dtos.TopArtist
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding
    private val viewModel by activityViewModels<HomeViewModel>()


    private val playerViewModel by activityViewModels<PlayerViewModel>()

    private val topArtistAdapter: TopArtistAdapter = TopArtistAdapter(emptyList(),
        SearchAdapter.OnClickListener { track ->
            if (playerViewModel.isPlaying.value == true) {
                playerViewModel.stop()
            }
            playerViewModel.play(track)
        }, SearchAdapter.OnLongPressListener { track ->
            playerViewModel.addToQueue(track)
            Toast.makeText(context, "${track.name} added to the Queue", Toast.LENGTH_SHORT).show()
        })

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
        if(viewModel.topArtists.value.isNullOrEmpty()) {
            viewModel.getTopArtists()
        }

        viewModel.topArtists.observe(viewLifecycleOwner) {
            if(it != null) {
                topArtistAdapter.updateTrackList(it)
            }else{
                DialogShowerError("Oops!","It seems that is a network error", resources.getDrawable(R.drawable.ic_error_rip)).show(parentFragmentManager, DialogShowerError.TAG)
            }
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
