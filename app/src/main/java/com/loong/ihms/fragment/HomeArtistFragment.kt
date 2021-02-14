package com.loong.ihms.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.loong.ihms.R
import com.loong.ihms.databinding.FragmentHomeArtistBinding

class HomeArtistFragment : Fragment(R.layout.fragment_home_artist) {
    private lateinit var binding: FragmentHomeArtistBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeArtistBinding.bind(view)
    }
}