package com.loong.ihms.utils

import androidx.fragment.app.Fragment
import com.loong.ihms.base.BaseActivity
import java.security.MessageDigest

// General functions

object GeneralUtil {
    fun hashString(input: String, algorithm: String): String {
        return MessageDigest
            .getInstance(algorithm)
            .digest(input.toByteArray())
            .fold("", { str, it -> str + "%02x".format(it) })
    }
}

// String extensions

fun String.hashSha256(): String {
    return GeneralUtil.hashString(this, "SHA-256")
}

// Fragment extensions

fun Fragment.getBaseActivity(): BaseActivity {
    return requireActivity() as BaseActivity
}