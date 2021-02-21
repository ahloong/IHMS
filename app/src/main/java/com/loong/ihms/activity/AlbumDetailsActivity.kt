package com.loong.ihms.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.loong.ihms.R
import com.loong.ihms.base.BaseActivity
import com.loong.ihms.databinding.ActivityAlbumDetailsBinding
import com.loong.ihms.utils.ConstantDataUtil

class AlbumDetailsActivity : BaseActivity() {
    private lateinit var binding: ActivityAlbumDetailsBinding
    private var albumId: Int = 0 // 0 = local playlist | > 0 = online album

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_album_details)
        albumId = intent.getIntExtra(ConstantDataUtil.ALBUM_DETAILS_ID_PARAMS, 0)

        setupView()
    }

    private fun setupView() {
        if (albumId == 0) {

        } else {

        }
    }
}