package com.loong.ihms.utils

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
}