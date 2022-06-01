package com.example.musicstreaming.music.musicstreaming.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicstreaming.R
import com.example.musicstreaming.services.dtos.Track
import com.example.musicstreaming.utils.PictureHandler

class SearchAdapter(private var trackList: List<Track>) : RecyclerView.Adapter<SearchAdapter.SearchResultViewHolder>() {
    private var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        context = parent.context
        return SearchResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val item = trackList[position]
        context?.let { PictureHandler.loadTrackImage(context = it, item.albumId, holder.trackImage) }
        holder.trackTitle.text = item.name
        holder.trackArtist.text = item.artistName
    }

    override fun getItemCount(): Int {
        return trackList.size
    }

    class SearchResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val trackImage: ImageView = itemView.findViewById(R.id.search_track_image)
        val trackTitle: TextView = itemView.findViewById(R.id.track_title)
        val trackArtist: TextView = itemView.findViewById(R.id.artist_name)
        val favorite: ImageView = itemView.findViewById(R.id.search_favorite)
    }

    fun updateTrackList(newList : List<Track>){
        this.trackList = newList
        notifyDataSetChanged()
    }
}