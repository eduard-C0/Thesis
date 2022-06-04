package com.example.musicstreaming.services

import androidx.core.os.trace
import com.example.musicstreaming.commonVO.User
import com.example.musicstreaming.services.dtos.FavoritesResponse
import com.example.musicstreaming.services.dtos.Track
import com.example.musicstreaming.services.dtos.UserDto

internal fun User.toUserDto() = UserDto(
    firstName = firstName,
    lastName = lastName,
    email = email,
    password = password
)

internal fun FavoritesResponse.toTracks() = this.favoritesList.map { track -> track.toTrack() }
internal fun Track.toTrack() = Track(
    id = id,
    name = name,
    albumId = albumId,
    albumName = albumName,
    artistId = artistId,
    artistName = artistName,
    isStreamable = isStreamable,
    type = type,
    playbackSeconds = playbackSeconds,
    previewURL = previewURL
)