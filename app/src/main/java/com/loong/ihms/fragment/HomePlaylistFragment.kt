package com.loong.ihms.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.loong.ihms.R
import com.loong.ihms.adapter.AlbumPlaylistListAdapter
import com.loong.ihms.databinding.FragmentHomePlaylistBinding
import com.loong.ihms.model.CuratorAlbum
import com.loong.ihms.utils.SpaceItemDecoration
import com.loong.ihms.utils.UserRelatedUtil
import com.loong.ihms.utils.dp
import com.loong.ihms.utils.getBaseActivity

class HomePlaylistFragment : Fragment(R.layout.fragment_home_playlist) {
    private lateinit var binding: FragmentHomePlaylistBinding
    private lateinit var adapter: AlbumPlaylistListAdapter
    private var dataItemList: ArrayList<CuratorAlbum> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomePlaylistBinding.bind(view)

        binding.playlistContentRv.layoutManager = GridLayoutManager(getBaseActivity(), 2)
        binding.playlistContentRv.addItemDecoration(SpaceItemDecoration(16.dp))

        binding.playlistContentSrl.setOnRefreshListener {
            getDataList()
        }

        binding.playlistCreateMb.setOnClickListener {
            
        }

        getDataList()
    }

    private fun getDataList() {
        dataItemList = UserRelatedUtil.getCuratorAlbumList()
        setupList()
    }

    private fun setupList() {
        adapter = AlbumPlaylistListAdapter(getBaseActivity(), dataItemList)
        binding.playlistContentRv.adapter = adapter
    }
}