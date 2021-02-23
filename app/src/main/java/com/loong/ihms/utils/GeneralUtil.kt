package com.loong.ihms.utils

import android.content.res.Resources
import android.util.TypedValue
import androidx.fragment.app.Fragment
import com.loong.ihms.base.BaseActivity
import java.security.MessageDigest
import kotlin.math.roundToInt

// General functions

object GeneralUtil {
    fun hashString(input: String, algorithm: String): String {
        return MessageDigest
            .getInstance(algorithm)
            .digest(input.toByteArray())
            .fold("", { str, it -> str + "%02x".format(it) })
    }

    fun fromSecToMinSec(seconds: Int): String {
        val min: Int = seconds / 60
        val sec: Int = seconds % 60

        return String.format("%02d:%02d", min, sec)
    }
}

// Int / Float extensions

val Int.dp
    get() = toFloat().dp.roundToInt()

val Float.dp
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics)

val Int.sp
    get() = toFloat().sp.roundToInt()

val Float.sp
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this, Resources.getSystem().displayMetrics)

// String extensions

fun String.hashSha256(): String {
    return GeneralUtil.hashString(this, "SHA-256")
}

// Fragment extensions

fun Fragment.getBaseActivity(): BaseActivity {
    return requireActivity() as BaseActivity
}