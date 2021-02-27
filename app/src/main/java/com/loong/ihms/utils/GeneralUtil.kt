package com.loong.ihms.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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

// Gson

inline fun <reified T> Gson.fromJson(json: String): T = fromJson(json, object: TypeToken<T>() {}.type)

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

// Screen width and height

val Context.windowManager: WindowManager
    get() = getSystemService(Context.WINDOW_SERVICE) as WindowManager

@Suppress("DEPRECATION")
val Context.screenWidth: Int
    get() {
        var width = 0

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            display?.apply {
                val size = Point()
                this.getRealSize(size)

                width = size.x
            }
        } else {
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)

            width = displayMetrics.widthPixels
        }

        return width
    }

@Suppress("DEPRECATION")
val Context.screenHeight: Int
    get() {
        var height = 0

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            display?.apply {
                val size = Point()
                this.getRealSize(size)

                height = size.y
            }
        } else {
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)

            height = displayMetrics.heightPixels
        }

        return height
    }