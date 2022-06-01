package com.example.musicstreaming.music.musicstreaming.search

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicstreaming.R
import com.example.musicstreaming.databinding.SearchFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel by viewModels<SearchViewModel>()
    private lateinit var binding: SearchFragmentBinding
    private val searchAdapter: SearchAdapter = SearchAdapter(emptyList())

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

        viewModel.tracksList.observe(viewLifecycleOwner){
            searchAdapter.updateTrackList(it)
        }

        viewModel.loadingProgressBar.observe(viewLifecycleOwner){
            if (it) {
                binding.searchLoading.visibility = View.VISIBLE
            }
            else{
                binding.searchLoading.visibility = View.GONE
            }
        }
    }
}