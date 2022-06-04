package com.example.musicstreaming.services.retrofit

import com.example.musicstreaming.commonVO.User
import com.example.musicstreaming.services.dtos.FavoritesResponse
import com.example.musicstreaming.services.dtos.ResponseMessage
import com.example.musicstreaming.services.dtos.Track
import com.example.musicstreaming.services.dtos.UserDto
import retrofit2.http.*

internal const val USER_URL = "/user"
internal const val FAVORITE_URL = "/favorite"

internal interface RetrofitBackendServiceApi {
    @POST("$USER_URL/createUser")
    suspend fun register(@Body userDto: UserDto): ResponseMessage

    @POST("$USER_URL/login")
    suspend fun login(@Body userDto: UserDto): ResponseMessage

    @POST("$FAVORITE_URL/addToFavorite")
    suspend fun addToFavorites(@Body track: Track): ResponseMessage?

    @GET("$FAVORITE_URL/getAllFavorites")
    suspend fun getAllTracksFromFavorites():List<Track>?

    @POST("$FAVORITE_URL/removeFromFavorites")
    suspend fun removeTrackFromFavorites(@Body track: Track):ResponseMessage?

    @GET("$USER_URL/getUser")
    suspend fun getUser(): User?

}