package com.loong.ihms.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.loong.ihms.R
import com.loong.ihms.databinding.FragmentMainCuratorBinding
import com.loong.ihms.model.Song
import com.loong.ihms.utils.ConstantDataUtil
import com.loong.ihms.utils.UserRelatedUtil
import com.loong.ihms.utils.getBaseActivity

class MainCuratorFragment : Fragment(R.layout.fragment_main_curator) {
    companion object {
        const val CATEGORY_ENERGY = 0
        const val CATEGORY_DANCEABILITY = 1
        const val CATEGORY_VALANCE = 2
        const val CATEGORY_ACOUSTIC = 3
    }

    private lateinit var binding: FragmentMainCuratorBinding

    private var allSongList: ArrayList<Song> = ArrayList()
    private var curatorSongList: ArrayList<Song> = ArrayList()

    private var currentSongItem: Song? = null
    private var currentPosition: Int = 0
    private var currentCategoryPosition: Int = CATEGORY_ENERGY

    private val categoryList: ArrayList<CuratorCategory> by lazy {
        arrayListOf(
            CuratorCategory("Energy", "This is Energy"),
            CuratorCategory("Danceability", "This is Danceability"),
            CuratorCategory("Valance", "This is Valance"),
            CuratorCategory("Acoustic", "This is Acoustic")
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainCuratorBinding.bind(view)

        allSongList = UserRelatedUtil.getAllSongList()
        curatorSongList = UserRelatedUtil.getCuratorSongList()

        if (allSongList.size > 0) {
            binding.curatorMainLl.visibility = View.VISIBLE

            if (curatorSongList.size > 0) {
                val tempPosition = allSongList.indexOfFirst { it.id == curatorSongList[0].id }
                currentPosition = if (tempPosition >= 0) (tempPosition + 1) else 0
            }

            setupView()
            setupSongView()
        } else {
            binding.curatorMainLl.visibility = View.INVISIBLE
        }
    }

    private fun setupView() {
        // Song

        binding.curatorSongPreviousFab.setOnClickListener {
            if (currentPosition > 0) {
                currentPosition -= 1
            }

            setupSongView()
        }

        binding.curatorSongNextFab.setOnClickListener {
            if (currentPosition < (allSongList.size - 1)) {
                currentPosition += 1
            }

            setupSongView()
        }

        binding.curatorSongPlayFab.setOnClickListener {
            val currentItem = allSongList[currentPosition]
            val songList = arrayListOf(currentItem)
            val songListJsonStr = Gson().toJson(songList)

            val localBroadcastIntent = Intent(ConstantDataUtil.START_PLAYING_INTENT)
            localBroadcastIntent.putExtra(ConstantDataUtil.START_PLAYING_SONG_LIST_EXTRA, songListJsonStr)
            localBroadcastIntent.putExtra(ConstantDataUtil.START_PLAYING_SONG_POSITION_EXTRA, 0)
            LocalBroadcastManager.getInstance(getBaseActivity()).sendBroadcast(localBroadcastIntent)
        }

        // Curator category

        binding.curatorSongCatPreviousMb.setOnClickListener {
            if (currentCategoryPosition > CATEGORY_ENERGY) {
                currentCategoryPosition -= 1
            }

            setupCuratorView()
        }

        binding.curatorSongCatNextMb.setOnClickListener {
            if (currentCategoryPosition < CATEGORY_ACOUSTIC) {
                currentCategoryPosition += 1
            }

            setupCuratorView()
        }

        binding.curatorSongCatDoneMb.setOnClickListener {
            currentSongItem?.let { currentSong ->
                val curatedSong = curatorSongList.find { curated ->
                    curated.id == currentSong.id
                }
            }
        }

        binding.curatorCategorySlider.addOnChangeListener { _, value, _ ->
            currentSongItem?.let {
                val tempValue = value.toInt()

                when (currentCategoryPosition) {
                    CATEGORY_ENERGY -> it.energyPoint = tempValue
                    CATEGORY_DANCEABILITY -> it.danceabilityPoint = tempValue
                    CATEGORY_VALANCE -> it.valancePoint = tempValue
                    CATEGORY_ACOUSTIC -> it.acousticPoint = tempValue
                }
            }
        }
    }

    private fun setupSongView() {
        currentSongItem = allSongList[currentPosition]

        currentSongItem?.let { currentItem ->
            Glide.with(getBaseActivity())
                .load(currentItem.art)
                .into(binding.curatorSongImg)

            binding.curatorSongTitleTv.text = currentItem.name
            binding.curatorSongAlbumTv.text = currentItem.album.name
            binding.curatorSongArtistTv.text = currentItem.artist.name
        }
    }

    private fun setupCuratorView() {
        val currentCategoryItem = categoryList[currentCategoryPosition]

        binding.curatorCategoryTitleTv.text = currentCategoryItem.name
        binding.curatorCategoryDescTv.text = currentCategoryItem.desc

        currentSongItem?.let { currentItem ->
            val point: Int = when (currentCategoryPosition) {
                CATEGORY_ENERGY -> currentItem.energyPoint
                CATEGORY_DANCEABILITY -> currentItem.danceabilityPoint
                CATEGORY_VALANCE -> currentItem.valancePoint
                CATEGORY_ACOUSTIC -> currentItem.acousticPoint
                else -> ConstantDataUtil.DEFAULT_CATEGORY_VALUE
            }

            binding.curatorCategorySlider.value = point.toFloat()
        }
    }
}

data class CuratorCategory(
    val name: String,
    val desc: String
)