package com.loong.ihms.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.loong.ihms.R
import com.loong.ihms.base.BaseActivity
import com.loong.ihms.databinding.ActivityArtistAlbumListBinding
import com.loong.ihms.utils.ConstantDataUtil

class ArtistAlbumListActivity : BaseActivity() {
    private lateinit var binding: ActivityArtistAlbumListBinding
    private var artistId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_artist_album_list)
        artistId = intent.getIntExtra(ConstantDataUtil.ARTIST_ALBUM_ID_PARAMS, 0)

        setupView()
    }

    private fun setupView() {

    }
}