package com.loong.ihms.model

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Album(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)