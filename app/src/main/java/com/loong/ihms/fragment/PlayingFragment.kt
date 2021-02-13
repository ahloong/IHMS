package com.loong.ihms.fragment

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.loong.ihms.R
import com.loong.ihms.databinding.FragmentPlayingBinding
import com.loong.ihms.utils.UserRelatedUtil

class PlayingFragment : Fragment(R.layout.fragment_playing) {
    private lateinit var binding: FragmentPlayingBinding
    private var mp: MediaPlayer? = null
    private val baseUrl = UserRelatedUtil.getMainApiUrl()
    private val auth = UserRelatedUtil.getUserApiAuth()
    val track : String = "Burn The Witch"
    val album : String = "Moon Shaped Pool (2016)"
    val artist : String = "Radiohead"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPlayingBinding.bind(view)

        binding.play.setOnClickListener() {
            mp = MediaPlayer.create(this@PlayingFragment.requireActivity(), Uri.parse("${baseUrl}/server/json.server.php?action=stream&auth=${auth}&id=30&type=song"))
            Glide.with(this@PlayingFragment)
                    .load("${baseUrl}/server/json.server.php?action=get_art&auth=${auth}&id=30&type=song")
                    .into(binding.nowplayingart)
            mp?.start()
            binding.tracktitle.setText(track)
            binding.albumtitle.setText(album)
            binding.artisttitle.setText(artist)
        }

        binding.stop.setOnClickListener() {
            mp?.pause()
        }
    }

}