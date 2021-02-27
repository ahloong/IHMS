package com.loong.ihms.utils

import com.google.gson.Gson
import com.loong.ihms.model.Song

object UserRelatedUtil {
    // Main API Url

    fun getMainApiUrl(): String {
        return LocalStorageUtil.getInstance().readString(LocalStorageUtil.MAIN_API_URL)
    }

    fun saveMainApiUrl(data: String) {
        LocalStorageUtil.getInstance().writeString(LocalStorageUtil.MAIN_API_URL, data)
    }

    // User API Auth

    fun getUserApiAuth(): String {
        return LocalStorageUtil.getInstance().readString(LocalStorageUtil.USER_API_AUTH)
    }

    fun saveUserApiAuth(data: String) {
        LocalStorageUtil.getInstance().writeString(LocalStorageUtil.USER_API_AUTH, data)
    }

    // Local Song List

    fun getAllSongList(): ArrayList<Song> {
        val songListJsonStr = LocalStorageUtil.readString(LocalStorageUtil.USER_SONG_LIST)

        return if (songListJsonStr.isNotEmpty()) {
            Gson().fromJson(songListJsonStr)
        } else {
            ArrayList()
        }
    }

    fun saveAllSongList(songList: ArrayList<Song>) {
        val songListJsonStr = Gson().toJson(songList)
        LocalStorageUtil.writeString(LocalStorageUtil.USER_SONG_LIST, songListJsonStr)
    }
}