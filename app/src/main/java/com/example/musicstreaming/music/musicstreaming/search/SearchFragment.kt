package com.example.musicstreaming.music.musicstreaming.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicstreaming.R
import com.example.musicstreaming.databinding.SearchFragmentBinding
import com.example.musicstreaming.music.DialogShowerError
import com.example.musicstreaming.music.musicstreaming.favorites.FavoritesViewModel
import com.example.musicstreaming.music.musicstreaming.home.HomeFragment
import com.example.musicstreaming.music.musicstreaming.player.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel by viewModels<SearchViewModel>()
    private val playerViewModel by activityViewModels<PlayerViewModel>()
    private val favoritesViewModel by activityViewModels<FavoritesViewModel>()
    private lateinit var binding: SearchFragmentBinding
    private val searchAdapter: SearchAdapter = SearchAdapter(emptyList(),
        emptyList(),
        SearchAdapter.OnClickListener { track ->
            if (playerViewModel.isPlaying.value == true) {
                playerViewModel.stop()
            }
            playerViewModel.play(track)
        }, SearchAdapter.OnLongPressListener { track ->
            playerViewModel.addToQueue(track)
            Toast.makeText(context, "${track.name} added to the Queue", Toast.LENGTH_SHORT).show()
        }, SearchAdapter.OnClickListener { track ->
            if (favoritesViewModel.favoritesList.value?.contains(track) == true) {
                favoritesViewModel.removeFavorite(track)
            } else {
                favoritesViewModel.addFavorite(track)
            }
        })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = searchAdapter
        binding.searchContainer.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(name: String?): Boolean {
                name?.let { viewModel.search(it) }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })

        binding.recyclerView.addItemDecoration(DividerItemDecoration(
            binding.recyclerView.context,
            LinearLayoutManager.VERTICAL
        ).also { dividerItemDecoration ->
            dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.bg_divider))
        })
        favoritesViewModel.getFavorites()
        viewModel.tracksList.observe(viewLifecycleOwner) {
            if(it != null) {
                searchAdapter.updateTrackList(it, favoritesViewModel.favoritesList.value ?: emptyList())
            }
            else{
                DialogShowerError("Oops!","There was an error searching for your song :(", resources.getDrawable(R.drawable.ic_error_rip)).show(parentFragmentManager, DialogShowerError.TAG)
            }
        }

        viewModel.loadingProgressBar.observe(viewLifecycleOwner) {
            if (it) {
                binding.searchLoading.visibility = View.VISIBLE
            } else {
                binding.searchLoading.visibility = View.GONE
            }
        }

        favoritesViewModel.favoriteAdded.observe(viewLifecycleOwner) {
            if (it) {
                favoritesViewModel.getFavorites()
            }
        }

        favoritesViewModel.favoriteRemoved.observe(viewLifecycleOwner) {
            if (it) {
                favoritesViewModel.getFavorites()
            }
        }
    }

    private fun redirectToHomeScreen() {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.main_music_container, HomeFragment())
        }
    }
}