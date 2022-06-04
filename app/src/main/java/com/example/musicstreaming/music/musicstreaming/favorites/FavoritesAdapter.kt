package com.example.musicstreaming.music.musicstreaming.favorites

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

class FavoritesAdapter(private var favoritesTrackList: List<Track>,
                       private val favoriteClickListener: SearchAdapter.OnClickListener

): RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    private var context: Context? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favorites_item, parent, false)
        context = parent.context
        return FavoritesViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val item = favoritesTrackList[position]
        context?.let { PictureHandler.loadTrackImage(context = it, item.albumId, holder.trackImage) }
        holder.trackTitle.text = item.name
        holder.trackArtist.text = item.artistName

        holder.favoriteRemove.setOnClickListener {
            favoriteClickListener.onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return favoritesTrackList.size
    }

    class FavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val trackImage: ImageView = itemView.findViewById(R.id.favorites_track_image)
        val trackTitle: TextView = itemView.findViewById(R.id.favorites_track_title)
        val trackArtist: TextView = itemView.findViewById(R.id.favorites_artist_name)
        val favoriteRemove: ImageView = itemView.findViewById(R.id.favorites_favorite_remove)
        val container: ConstraintLayout = itemView.findViewById(R.id.favorites_item_container)
    }

    fun updateTrackList(newFavorites: List<Track>) {
        this.favoritesTrackList = newFavorites
        notifyDataSetChanged()
    }
}