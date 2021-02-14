package com.loong.ihms.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.loong.ihms.R
import com.loong.ihms.databinding.FragmentHomeAlbumBinding
import com.loong.ihms.model.AlbumItem
import com.loong.ihms.network.ApiRepositoryFunction
import com.loong.ihms.network.ApiResponseCallback

class HomeAlbumFragment : Fragment(R.layout.fragment_home_album) {
    private lateinit var binding: FragmentHomeAlbumBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeAlbumBinding.bind(view)
        getAlbumList()
    }

    private fun getAlbumList() {
        ApiRepositoryFunction.getAlbumList(object : ApiResponseCallback<ArrayList<AlbumItem>> {
            override fun onSuccess(responseData: ArrayList<AlbumItem>) {
                val sss = ""
            }

            override fun onFailed() {

            }

        })
    }
}