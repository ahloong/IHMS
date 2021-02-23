package com.loong.ihms.fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.loong.ihms.R
import com.loong.ihms.databinding.FragmentMainNowPlayingBinding

class MainNowPlayingFragment : Fragment(R.layout.fragment_main_now_playing) {
    private lateinit var binding: FragmentMainNowPlayingBinding
    private var mediaPlayer: MediaPlayer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainNowPlayingBinding.bind(view)

        /*binding.play.setOnClickListener() {
            mp = MediaPlayer.create(this@MainNowPlayingFragment.requireActivity(), Uri.parse("${baseUrl}/server/json.server.php?action=stream&auth=${auth}&id=30&type=song"))
            Glide.with(this@MainNowPlayingFragment)
                .load("${baseUrl}/server/json.server.php?action=get_art&auth=${auth}&id=30&type=song")
                .into(binding.playSongImg)
            mp?.start()
            binding.playSongTitleTv.setText(track)
            binding.playSongAlbumTv.setText(album)
            binding.playSongArtistTv.setText(artist)
        }

        binding.stop.setOnClickListener() {
            mp?.pause()
        }*/
    }
}