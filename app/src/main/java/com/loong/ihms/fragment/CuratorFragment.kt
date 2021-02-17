package com.loong.ihms.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.loong.ihms.R
import com.loong.ihms.databinding.FragmentCuratorBinding

class CuratorFragment : Fragment(R.layout.fragment_curator) {
    private lateinit var binding: FragmentCuratorBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCuratorBinding.bind(view)
    }
}