package com.example.musicstreaming.music.musicstreaming.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicstreaming.services.dtos.Track
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val addFavoritesUseCase: AddFavoritesUseCase,
    private val removeFavoritesUseCase: RemoveFavoritesUseCase
) : ViewModel() {

    val favoritesList: MutableLiveData<List<Track>> = MutableLiveData(emptyList())
    val favoriteAdded: MutableLiveData<Boolean> = MutableLiveData(false)
    val favoriteRemoved: MutableLiveData<Boolean> = MutableLiveData(false)

    fun addFavorite(track: Track) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = addFavoritesUseCase.invoke(track)
            favoriteAdded.postValue(true)
        }
    }

    fun getFavorites(){
        CoroutineScope(Dispatchers.IO).launch {
            val result = getFavoritesUseCase.invoke()
            favoritesList.postValue(result)
            favoriteAdded.postValue(false)
            favoriteRemoved.postValue(false)
        }
    }

    fun removeFavorite(track: Track){
        CoroutineScope(Dispatchers.IO).launch {
            val result = removeFavoritesUseCase.invoke(track)
            favoriteRemoved.postValue(true)
        }
    }
}