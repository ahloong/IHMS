package com.loong.ihms.base

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.loong.ihms.R

open class BaseActivity : AppCompatActivity() {
    fun setupToolbar(toolbar: Toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.title = ""
        toolbar.navigationIcon?.setTint(Color.WHITE)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}