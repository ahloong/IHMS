package com.loong.ihms.network

import com.loong.ihms.model.AlbumItem
import com.loong.ihms.model.UserProfile
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    // Login using username and password
    @GET(".")
    fun getUserLogin(
        @Query("action") action: String,
        @Query("auth") auth: String,
        @Query("timestamp") timestamp: String,
        @Query("version") version: String,
        @Query("user") user: String
    ): Call<UserProfile>

    // Get a list of albums
    @GET(".")
    fun getAlbumList(
        @Query("auth") auth: String,
        @Query("action") action: String
    ): Call<ArrayList<AlbumItem>>

    // Get an album's details
    @GET(".")
    fun getAlbumDetails(
        @Query("auth") auth: String,
        @Query("action") action: String,
        @Query("filter") filter: String
    ): Call<ArrayList<AlbumItem>>
}