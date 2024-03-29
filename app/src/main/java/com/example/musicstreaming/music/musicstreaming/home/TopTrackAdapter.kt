package com.example.musicstreaming.music.musicstreaming.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.musicstreaming.R
import com.example.musicstreaming.music.musicstreaming.search.SearchAdapter
import com.example.musicstreaming.services.dtos.Track
import com.example.musicstreaming.utils.PictureHandler

class TopTrackAdapter(private var trackList: List<Track>, private val onClickListener: SearchAdapter.OnClickListener, private val onLongPressListener: SearchAdapter.OnLongPressListener) : RecyclerView.Adapter<TopTrackAdapter.HomeTopTrackViewHolder>() {
    private var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeTopTrackViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.top_song_item, parent, false)
        context = parent.context
        return HomeTopTrackViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeTopTrackViewHolder, position: Int) {
        val item = trackList[position]
        context?.let { PictureHandler.loadTrackImage(context = it, item.albumId, holder.topTrackImage) }
        holder.topTrackName.text = item.name
        holder.container.setOnClickListener {
            onClickListener.clickListener(item)
        }

        holder.container.setOnLongClickListener {
            onLongPressListener.onLongPress(item)
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return trackList.size
    }

    class HomeTopTrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val topTrackImage: ImageView = itemView.findViewById(R.id.top_track_image)
        val topTrackName: TextView = itemView.findViewById(R.id.top_track_name)
        val container: ConstraintLayout = itemView.findViewById(R.id.top_track_container)
    }

}