package com.loong.ihms.base

import android.graphics.Color
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.loong.ihms.R
import com.loong.ihms.model.Song
import com.loong.ihms.network.ApiRepositoryFunction
import com.loong.ihms.network.ApiResponseCallback
import com.loong.ihms.utils.LoadingDialog
import com.loong.ihms.utils.UserRelatedUtil

open class BaseActivity : AppCompatActivity() {
    fun setupToolbar(toolbar: Toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.title = ""
        toolbar.navigationIcon?.setTint(Color.WHITE)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private val loadingDialog by lazy {
        LoadingDialog(this)
    }

    override fun onStop() {
        showHideLoadingDialog(false)
        super.onStop()
    }

    override fun onDestroy() {
        showHideLoadingDialog(false)
        super.onDestroy()
    }

    fun showHideLoadingDialog(isShow: Boolean) {
        if (isShow) {
            // Dismiss first then show
            loadingDialog.dismiss()
            loadingDialog.show()
        } else {
            Handler(Looper.getMainLooper()).postDelayed({
                loadingDialog.dismiss()
            }, 300)
        }
    }

    fun getAllSongs(proceedCall: () -> Unit, failedCall: () -> Unit) {
        showHideLoadingDialog(true)

        ApiRepositoryFunction.getAllSongs(object : ApiResponseCallback<ArrayList<Song>> {
            override fun onSuccess(responseData: ArrayList<Song>) {
                val curatorSongList = UserRelatedUtil.getCuratorSongList()

                if (responseData.size > 0 && curatorSongList.size > 0) {
                    responseData.forEach { oriSongItem ->
                        val curatedSong = curatorSongList.find { it.id == oriSongItem.id }

                        curatedSong?.let {
                            oriSongItem.energyPoint = it.energyPoint
                            oriSongItem.danceabilityPoint = it.danceabilityPoint
                            oriSongItem.valancePoint = it.valancePoint
                            oriSongItem.acousticPoint = it.acousticPoint
                            oriSongItem.isCurated = it.isCurated
                        }
                    }
                }

                showHideLoadingDialog(false)
                UserRelatedUtil.saveAllSongList(responseData)
                proceedCall()
            }

            override fun onFailed() {
                showHideLoadingDialog(true)
                failedCall()
            }
        })
    }
}