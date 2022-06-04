package com.example.musicstreaming.music.musicstreaming.player

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.musicstreaming.R
import com.example.musicstreaming.databinding.MusicPlayerFragmentBinding
import com.example.musicstreaming.music.musicstreaming.home.HomeViewModel
import com.example.musicstreaming.utils.PictureHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayerFragment : Fragment() {
    private lateinit var binding: MusicPlayerFragmentBinding
    private val viewModel by activityViewModels<PlayerViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MusicPlayerFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setFinishListener()
        viewModel.isPlaying.observe(viewLifecycleOwner) {
            if (it) {
                binding.musicPlayerPlayButton.setImageDrawable(resources.getDrawable(R.drawable.ic_pause))
            } else {
                binding.musicPlayerPlayButton.setImageDrawable(resources.getDrawable(R.drawable.ic_play))
            }
        }

        viewModel.currentTrack.observe(viewLifecycleOwner) { track ->
            binding.musicPlayerTrackTitle.text = track?.name
            binding.musicPlayerTrackArtistName.text = track?.artistName
            context?.let { track?.albumId?.let { it1 -> PictureHandler.loadTrackImage(context = it, it1, binding.playerTrackImage) } }
        }

        binding.musicPlayerPlayButton.setOnClickListener {
            if (viewModel.isPlaying.value == true) {
                viewModel.pause()
            } else if (viewModel.isPlaying.value == false && viewModel.isCompleted.value == false) {
                viewModel.start()
            } else {
                viewModel.currentTrack.value?.let { track -> viewModel.play(track) }
            }
        }

        viewModel.queueTrackList.observe(viewLifecycleOwner){
            if(it.isEmpty()){
                binding.musicPlayerNextButton.isEnabled = false
                viewModel.setEmptyQueue()
            }
        }

        viewModel.emptyQueue.observe(viewLifecycleOwner){
            binding.musicPlayerNextButton.isEnabled = !it
        }

        viewModel.stackTrackList.observe(viewLifecycleOwner){
            if(it.isEmpty()){
                binding.musicPlayerPreviousButton.isEnabled = false
                viewModel.setEmptyStack()
            }
        }

        viewModel.emptyStack.observe(viewLifecycleOwner){
            binding.musicPlayerPreviousButton.isEnabled = !it
        }

        binding.musicPlayerNextButton.setOnClickListener {
            viewModel.stop()
            viewModel.next()
        }

        binding.musicPlayerPreviousButton.setOnClickListener {
            viewModel.stop()
            viewModel.previous()
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.pause()
    }
}