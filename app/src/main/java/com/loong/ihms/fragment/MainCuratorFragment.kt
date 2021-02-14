package com.loong.ihms.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.loong.ihms.R
import com.loong.ihms.databinding.FragmentMainCuratorBinding

class MainCuratorFragment : Fragment(R.layout.fragment_main_curator) {
    private lateinit var binding: FragmentMainCuratorBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainCuratorBinding.bind(view)
    }
}