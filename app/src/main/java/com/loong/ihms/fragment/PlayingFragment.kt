package com.loong.ihms.fragment

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.loong.ihms.R
import com.loong.ihms.databinding.FragmentPlayingBinding
import com.loong.ihms.utils.LocalStorageUtil

class PlayingFragment : Fragment(R.layout.fragment_playing) {
    private lateinit var binding: FragmentPlayingBinding
    private var mp: MediaPlayer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPlayingBinding.bind(view)

        fun play(view: View) {
            val baseUrl = LocalStorageUtil.getInstance().readString(LocalStorageUtil.MAIN_API_URL)
            val auth = LocalStorageUtil.getInstance().readString(LocalStorageUtil.USER_API_AUTH)
            mp = MediaPlayer.create(this@PlayingFragment.requireActivity(), Uri.parse("${baseUrl}/server/json.server.php?action=stream&auth=${auth}&id=9&type=song"))
            mp?.start()
        }

        binding.play.setOnClickListener() {
            val baseUrl = LocalStorageUtil.getInstance().readString(LocalStorageUtil.MAIN_API_URL)
            val auth = LocalStorageUtil.getInstance().readString(LocalStorageUtil.USER_API_AUTH)
            mp = MediaPlayer.create(this@PlayingFragment.requireActivity(), Uri.parse("${baseUrl}/server/json.server.php?action=stream&auth=${auth}&id=23&type=song"))
            mp?.start()
        }
    }

}