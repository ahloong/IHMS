package com.loong.ihms.network

import com.loong.ihms.model.AlbumItem
import com.loong.ihms.model.Song
import com.loong.ihms.model.UserProfile
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(".")
    fun getUserLogin(
        @Query("action") action: String,
        @Query("auth") auth: String,
        @Query("timestamp") timestamp: String,
        @Query("version") version: String,
        @Query("user") user: String
    ): Call<UserProfile>

    @GET(".")
    fun getSongList(
        @Query("action") action: String,
        @Query("auth") auth: String,
        @Query("type") type: String,
    ): Call<ArrayList<Song>>

    @GET(".")
    fun getAlbumsList(
        @Query("action") action: String,
        @Query("auth") auth: String,
        @Query("type") type: String,
    ): Call<ArrayList<AlbumItem>>
}