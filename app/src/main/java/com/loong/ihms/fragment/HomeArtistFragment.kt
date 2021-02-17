package com.loong.ihms.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.loong.ihms.R
import com.loong.ihms.adapter.RecyclerViewBaseAdapter
import com.loong.ihms.databinding.FragmentHomeArtistBinding
import com.loong.ihms.databinding.ViewArtistItemBinding
import com.loong.ihms.model.Artist
import com.loong.ihms.network.ApiRepositoryFunction
import com.loong.ihms.network.ApiResponseCallback
import com.loong.ihms.utils.SpaceItemDecoration
import com.loong.ihms.utils.dp
import com.loong.ihms.utils.getBaseActivity

class HomeArtistFragment : Fragment(R.layout.fragment_home_artist) {
    private lateinit var binding: FragmentHomeArtistBinding
    private lateinit var adapter: ArtistListAdapter
    private var dataItemList: ArrayList<Artist> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeArtistBinding.bind(view)

        binding.artistContentSrl.setOnRefreshListener {
            getDataList()
        }

        binding.artistContentRv.layoutManager = LinearLayoutManager(getBaseActivity())
        binding.artistContentRv.addItemDecoration(SpaceItemDecoration(16.dp))

        getDataList()
    }

    private fun getDataList() {
        binding.artistContentSrl.isRefreshing = true

        ApiRepositoryFunction.getArtistList(object : ApiResponseCallback<ArrayList<Artist>> {
            override fun onSuccess(responseData: ArrayList<Artist>) {
                binding.artistContentSrl.isRefreshing = false
                dataItemList = responseData
                setupList()
            }

            override fun onFailed() {
                binding.artistContentSrl.isRefreshing = false
                dataItemList = ArrayList()
                setupList()
            }
        })
    }

    private fun setupList() {
        adapter = ArtistListAdapter(dataItemList)
        binding.artistContentRv.adapter = adapter
    }

    private class ArtistListAdapter(val itemList: ArrayList<Artist>) : RecyclerViewBaseAdapter() {
        override fun setBindViewHolder(viewHolder: MyViewHolder, position: Int) {
            val binding: ViewArtistItemBinding = viewHolder.binding as ViewArtistItemBinding
            val itemData: Artist = itemList[position]

            binding.albumItemTitleTv.text = itemData.name
        }

        override fun getLayoutIdForPosition(position: Int): Int {
            return R.layout.view_artist_item
        }

        override fun getItemTotalCount(): Int {
            return itemList.size
        }
    }
}