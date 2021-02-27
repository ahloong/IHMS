package com.loong.ihms.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.loong.ihms.R
import com.loong.ihms.databinding.FragmentMainCuratorBinding
import com.loong.ihms.model.Song
import com.loong.ihms.utils.ConstantDataUtil
import com.loong.ihms.utils.UserRelatedUtil
import com.loong.ihms.utils.getBaseActivity

class MainCuratorFragment : Fragment(R.layout.fragment_main_curator) {
    private lateinit var binding: FragmentMainCuratorBinding

    private var allSongList: ArrayList<Song> = ArrayList()
    private var curatorSongList: ArrayList<Song> = ArrayList()
    private var currentPosition: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainCuratorBinding.bind(view)

        allSongList = UserRelatedUtil.getAllSongList()
        curatorSongList = UserRelatedUtil.getCuratorSongList()

        allSongList = ArrayList(
            allSongList.filter { item ->
                curatorSongList.contains(item)
            }
        )

        setupView()
        setupCuratorView()
    }

    private fun setupView() {
        binding.curatorSongPreviousFab.setOnClickListener {
            if (currentPosition > 0) {
                currentPosition -= 1
            }

            setupCuratorView()
        }

        binding.curatorSongNextFab.setOnClickListener {
            if (currentPosition < (allSongList.size - 1)) {
                currentPosition += 1
            }

            setupCuratorView()
        }

        binding.curatorSongPlayFab.setOnClickListener {
            if (allSongList.size > 0) {
                val currentItem = allSongList[currentPosition]
                val songList = arrayListOf(currentItem)
                val songListJsonStr = Gson().toJson(songList)

                val localBroadcastIntent = Intent(ConstantDataUtil.START_PLAYING_INTENT)
                localBroadcastIntent.putExtra(ConstantDataUtil.START_PLAYING_SONG_LIST_EXTRA, songListJsonStr)
                localBroadcastIntent.putExtra(ConstantDataUtil.START_PLAYING_SONG_POSITION_EXTRA, 0)
                LocalBroadcastManager.getInstance(getBaseActivity()).sendBroadcast(localBroadcastIntent)
            }
        }
    }

    private fun setupCuratorView() {
        val currentItem = allSongList[currentPosition]

        Glide.with(getBaseActivity())
            .load(currentItem.art)
            .into(binding.curatorSongImg)

        binding.curatorSongTitleTv.text = currentItem.name
        binding.curatorSongAlbumTv.text = currentItem.album.name
        binding.curatorSongArtistTv.text = currentItem.artist.name
    }
}