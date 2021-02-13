package com.loong.ihms.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.loong.ihms.R
import com.loong.ihms.databinding.FragmentAlbumBinding
import com.loong.ihms.model.AlbumItem
import com.loong.ihms.network.ApiRepositoryFunction
import com.loong.ihms.network.ApiResponseCallback

class AlbumFragment : Fragment(R.layout.fragment_album){
    private lateinit var binding: FragmentAlbumBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAlbumBinding.bind(view)
        getAlbumList()
    }

    private fun getAlbumList() {
        ApiRepositoryFunction.getAlbumList(object: ApiResponseCallback<ArrayList<AlbumItem>> {
            override fun onSuccess(responseData: ArrayList<AlbumItem>) {
                val sss = ""
            }

            override fun onFailed() {

            }

        })
    }

}