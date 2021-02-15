package com.loong.ihms.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.loong.ihms.R
import com.loong.ihms.adapter.RecyclerViewBaseAdapter
import com.loong.ihms.databinding.FragmentHomeAlbumBinding
import com.loong.ihms.databinding.ViewAlbumItemBinding
import com.loong.ihms.model.AlbumItem
import com.loong.ihms.network.ApiRepositoryFunction
import com.loong.ihms.network.ApiResponseCallback
import com.loong.ihms.utils.getBaseActivity

class HomeAlbumFragment : Fragment(R.layout.fragment_home_album) {
    private lateinit var binding: FragmentHomeAlbumBinding
    private var albumList: ArrayList<AlbumItem> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeAlbumBinding.bind(view)

        binding.albumContentSrl.setOnRefreshListener {
            getAlbumList()
        }

        getAlbumList()
    }

    private fun getAlbumList() {
        binding.albumContentSrl.isRefreshing = true

        ApiRepositoryFunction.getAlbumList(object : ApiResponseCallback<ArrayList<AlbumItem>> {
            override fun onSuccess(responseData: ArrayList<AlbumItem>) {
                binding.albumContentSrl.isRefreshing = false
                albumList = responseData
                setupList()
            }

            override fun onFailed() {
                binding.albumContentSrl.isRefreshing = false
                albumList = ArrayList()
                setupList()
            }
        })
    }

    private fun setupList() {
        val adapter = AlbumListAdapter(getBaseActivity(), albumList)
        binding.albumContentRv.layoutManager = GridLayoutManager(getBaseActivity(), 2)
        binding.albumContentRv.adapter = adapter
    }

    private class AlbumListAdapter(val context: Context, val itemList: ArrayList<AlbumItem>) : RecyclerViewBaseAdapter() {
        override fun setBindViewHolder(viewHolder: MyViewHolder, position: Int) {
            val binding: ViewAlbumItemBinding = viewHolder.binding as ViewAlbumItemBinding
            val itemData: AlbumItem = itemList[position]

            Glide.with(context)
                .load(itemData.art)
                .into(binding.albumItemImg)

            binding.albumItemTitleTv.text = itemData.name
            binding.albumItemDescTv.text = itemData.artist.name
        }

        override fun getLayoutIdForPosition(position: Int): Int {
            return R.layout.view_album_item
        }

        override fun getItemTotalCount(): Int {
            return itemList.size
        }
    }
}