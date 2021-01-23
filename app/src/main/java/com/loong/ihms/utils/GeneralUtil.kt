package com.loong.ihms.utils

import java.security.MessageDigest

object GeneralUtil {
    fun hashString(input: String, algorithm: String): String {
        return MessageDigest
            .getInstance(algorithm)
            .digest(input.toByteArray())
            .fold("", { str, it -> str + "%02x".format(it) })
    }
}

fun String.hashSha256(): String {
    return GeneralUtil.hashString(this, "SHA-256")
}