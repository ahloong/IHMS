package com.loong.ihms.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.loong.ihms.R
import com.loong.ihms.databinding.FragmentHomePlaylistBinding

class HomePlaylistFragment : Fragment(R.layout.fragment_home_playlist) {
    private lateinit var binding: FragmentHomePlaylistBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomePlaylistBinding.bind(view)
    }
}