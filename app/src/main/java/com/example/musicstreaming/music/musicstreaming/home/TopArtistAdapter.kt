package com.example.musicstreaming.music.musicstreaming.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicstreaming.R
import com.example.musicstreaming.services.dtos.Artist
import com.example.musicstreaming.services.dtos.Track
import com.example.musicstreaming.utils.PictureHandler

class TopArtistAdapter (private var artistList: List<Artist>) : RecyclerView.Adapter<TopArtistAdapter.HomeArtistsViewHolder>() {
    private var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeArtistsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.top_artist_item, parent, false)
        context = parent.context
        return HomeArtistsViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeArtistsViewHolder, position: Int) {
        val item = artistList[position]
        context?.let { PictureHandler.loadArtistImage(context = it, item.id, holder.topArtistImage) }

        holder.topArtistName.text = item.name

        val layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        val topTrackAdapter = item.topTracks?.let { TopTrackAdapter(it) }

        holder.topTrackRecyclerView.layoutManager = layoutManager
        holder.topTrackRecyclerView.adapter = topTrackAdapter
    }

    override fun getItemCount(): Int {
        return artistList.size
    }

    class HomeArtistsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val topArtistImage: ImageView = itemView.findViewById(R.id.top_artist_image)
        val topArtistName: TextView = itemView.findViewById(R.id.top_artist_name)
        val topTrackRecyclerView: RecyclerView = itemView.findViewById(R.id.top_songs)
    }

    fun updateTrackList(newList : List<Artist>){
        this.artistList = newList
        notifyDataSetChanged()
    }
}