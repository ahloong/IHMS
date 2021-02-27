package com.loong.ihms.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.loong.ihms.R
import com.loong.ihms.base.BaseActivity
import com.loong.ihms.databinding.ActivityCreatePlaylistBinding

class CreatePlaylistActivity : BaseActivity() {
    private lateinit var binding: ActivityCreatePlaylistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_playlist)
    }
}