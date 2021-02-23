package com.loong.ihms.activity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.loong.ihms.R
import com.loong.ihms.adapter.RecyclerViewBaseAdapter
import com.loong.ihms.base.BaseActivity
import com.loong.ihms.databinding.ActivityAlbumDetailsBinding
import com.loong.ihms.databinding.ViewTrackItemBinding
import com.loong.ihms.model.Album
import com.loong.ihms.model.Song
import com.loong.ihms.network.ApiRepositoryFunction
import com.loong.ihms.network.ApiResponseCallback
import com.loong.ihms.utils.ConstantDataUtil
import com.loong.ihms.utils.GeneralUtil
import com.loong.ihms.utils.SpaceItemDecoration
import com.loong.ihms.utils.dp

class AlbumDetailsActivity : BaseActivity() {
    private lateinit var binding: ActivityAlbumDetailsBinding

    private lateinit var adapter: SongListAdapter
    private var albumId: Int = 0 // 0 = local playlist | > 0 = online album
    private var albumItem: Album? = null
    private var songList: ArrayList<Song> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_album_details)
        albumId = intent.getIntExtra(ConstantDataUtil.ALBUM_DETAILS_ID_PARAMS, 0)

        setupToolbar(binding.albumDetailsToolbar)

        binding.albumDetailsRv.layoutManager = LinearLayoutManager(this)
        binding.albumDetailsRv.addItemDecoration(SpaceItemDecoration(8.dp))

        if (albumId == 0) {

        } else {
            getAlbumData()
        }
    }

    private fun getAlbumData() {
        ApiRepositoryFunction.getAlbumDetails(albumId, object : ApiResponseCallback<Album> {
            override fun onSuccess(responseData: Album) {
                albumItem = responseData
                getAlbumSongList()
            }
        })
    }

    private fun getAlbumSongList() {
        ApiRepositoryFunction.getAlbumSongList(albumId, object : ApiResponseCallback<ArrayList<Song>> {
            override fun onSuccess(responseData: ArrayList<Song>) {
                songList = responseData
                setupView()
            }
        })
    }

    private fun setupView() {
        binding.albumDetailsToolbar.title = albumItem?.name ?: ""

        Glide.with(this)
            .load(albumItem?.art ?: "")
            .into(binding.albumDetailsArtImg)

        adapter = SongListAdapter(this, songList)
        binding.albumDetailsRv.adapter = adapter

        binding.albumDetailsPlayFab.setOnClickListener {

        }
    }

    private class SongListAdapter(val context: Context, val itemList: ArrayList<Song>) : RecyclerViewBaseAdapter() {
        override fun setBindViewHolder(viewHolder: MyViewHolder, position: Int) {
            val binding: ViewTrackItemBinding = viewHolder.binding as ViewTrackItemBinding
            val itemData: Song = itemList[position]

            binding.trackTitleTv.text = itemData.title
            binding.trackDurationTv.text = GeneralUtil.fromSecToMinSec(itemData.time)
            binding.trackArtistTv.text = itemData.artist.name

            if (position % 2 == 0) {
                binding.trackMainLl.background = ContextCompat.getDrawable(context, R.drawable.bg_item_gray_corner)
            } else {
                binding.trackMainLl.background = ContextCompat.getDrawable(context, R.drawable.bg_item_white_corner)
            }

            binding.trackPlayImg.setOnClickListener {

            }
        }

        override fun getLayoutIdForPosition(position: Int): Int {
            return R.layout.view_track_item
        }

        override fun getItemTotalCount(): Int {
            return itemList.size
        }
    }
}