package com.loong.ihms.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.loong.ihms.R
import com.loong.ihms.activity.AlbumDetailsActivity
import com.loong.ihms.adapter.RecyclerViewBaseAdapter
import com.loong.ihms.databinding.FragmentHomeAlbumBinding
import com.loong.ihms.databinding.ViewAlbumItemBinding
import com.loong.ihms.model.Album
import com.loong.ihms.network.ApiRepositoryFunction
import com.loong.ihms.network.ApiResponseCallback
import com.loong.ihms.utils.ConstantDataUtil
import com.loong.ihms.utils.SpaceItemDecoration
import com.loong.ihms.utils.dp
import com.loong.ihms.utils.getBaseActivity

class HomeAlbumFragment : Fragment(R.layout.fragment_home_album) {
    private lateinit var binding: FragmentHomeAlbumBinding
    private lateinit var adapter: AlbumListAdapter
    private var dataItemList: ArrayList<Album> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeAlbumBinding.bind(view)

        binding.albumContentRv.layoutManager = GridLayoutManager(getBaseActivity(), 2)
        binding.albumContentRv.addItemDecoration(SpaceItemDecoration(16.dp))

        binding.albumContentSrl.setOnRefreshListener {
            getDataList()
        }

        getDataList()
    }

    private fun getDataList() {
        binding.albumContentSrl.isRefreshing = true

        ApiRepositoryFunction.getAlbumList(object : ApiResponseCallback<ArrayList<Album>> {
            override fun onSuccess(responseData: ArrayList<Album>) {
                binding.albumContentSrl.isRefreshing = false
                dataItemList = responseData
                setupList()
            }

            override fun onFailed() {
                binding.albumContentSrl.isRefreshing = false
                dataItemList = ArrayList()
                setupList()
            }
        })
    }

    private fun setupList() {
        adapter = AlbumListAdapter(getBaseActivity(), dataItemList)
        binding.albumContentRv.adapter = adapter
    }

    private class AlbumListAdapter(val context: Context, val itemList: ArrayList<Album>) : RecyclerViewBaseAdapter() {
        override fun setBindViewHolder(viewHolder: MyViewHolder, position: Int) {
            val binding: ViewAlbumItemBinding = viewHolder.binding as ViewAlbumItemBinding
            val itemData: Album = itemList[position]

            Glide.with(context)
                .load(itemData.art)
                .into(binding.albumItemImg)

            binding.albumItemTitleTv.text = itemData.name
            binding.albumItemDescTv.text = itemData.artist.name

            binding.albumItemCv.setOnClickListener {
                val intent = Intent(context, AlbumDetailsActivity::class.java)
                intent.putExtra(ConstantDataUtil.ALBUM_DETAILS_ID_PARAMS, itemData.id.toInt())
                context.startActivity(intent)
            }
        }

        override fun getLayoutIdForPosition(position: Int): Int {
            return R.layout.view_album_item
        }

        override fun getItemTotalCount(): Int {
            return itemList.size
        }
    }
}