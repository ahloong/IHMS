package com.loong.ihms.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.loong.ihms.R
import com.loong.ihms.databinding.FragmentMainHomeBinding

class MainHomeFragment : Fragment(R.layout.fragment_main_home) {
    private lateinit var binding: FragmentMainHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainHomeBinding.bind(view)
    }
}