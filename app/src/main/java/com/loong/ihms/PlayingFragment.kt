package com.loong.ihms

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.loong.ihms.databinding.FragmentPlayingBinding

class PlayingFragment : Fragment(R.layout.fragment_playing) {
    private lateinit var binding: FragmentPlayingBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPlayingBinding.bind(view)
    }
}