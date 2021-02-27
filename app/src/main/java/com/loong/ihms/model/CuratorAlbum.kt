package com.loong.ihms.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CuratorAlbum(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("songlist") val songList: ArrayList<Song>,
    @SerializedName("art") val art: String
)