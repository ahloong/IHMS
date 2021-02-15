package com.loong.ihms.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.loong.ihms.model.AlbumItem
import com.loong.ihms.model.UserProfile
import com.loong.ihms.utils.ConstantDataUtil
import com.loong.ihms.utils.UserRelatedUtil
import com.loong.ihms.utils.hashSha256
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRepository {
    private var retrofit: Retrofit? = null

    fun refreshRetrofitObj() {
        retrofit = null
    }

    // Retrofit, for compiling the URL before send out
    private fun getRetrofitInstance(): Retrofit {
        if (retrofit == null) {
            val baseUrl = UserRelatedUtil.getMainApiUrl()
            val apiBaseUrl = "${baseUrl}/server/json.server.php/"

            val gson = GsonBuilder()
                .serializeNulls()
                .create()

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(LogJsonInterceptor())

            retrofit = Retrofit.Builder()
                .baseUrl(apiBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()
        }

        return retrofit!!
    }

    fun fromObjToRequestBody(requestModel: Any): RequestBody {
        val jsonString: String = Gson().toJson(requestModel)
        return jsonString.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
    }

    fun getApiService(): ApiService {
        return getRetrofitInstance().create(ApiService::class.java)
    }
}

// Tapau link
object ApiRepositoryFunction {
    fun getUserLogin(
        username: String,
        password: String,
        callback: ApiResponseCallback<UserProfile>
    ) {
        val timestamp = (System.currentTimeMillis() / 1000).toString()
        val passwordKeyHarsh = password.hashSha256()
        val passphrase = "${timestamp}${passwordKeyHarsh}".hashSha256()

        val call = ApiRepository.getApiService().getUserLogin(
            ConstantDataUtil.ACTION_HANDSHAKE,
            passphrase,
            timestamp,
            ConstantDataUtil.API_VERSION,
            username
        )

        call.enqueue(object : Callback<UserProfile> {
            override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {
                if (response.code() == 200) {
                    response.body()?.let {
                        if (it.error == null) {
                            callback.onSuccess(it)
                        } else {
                            callback.onFailed()
                        }
                    }
                } else {
                    callback.onFailed()
                }
            }

            override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                callback.onFailed()
            }
        })
    }

    fun getAlbumList(
        callback: ApiResponseCallback<ArrayList<AlbumItem>>
    ) {
        val auth = UserRelatedUtil.getUserApiAuth()

        val call = ApiRepository.getApiService().getAlbumList(
            auth,
            ConstantDataUtil.ACTION_ALBUMS
        )

        call.enqueue(object : Callback<ArrayList<AlbumItem>> {
            override fun onResponse(call: Call<ArrayList<AlbumItem>>, response: Response<ArrayList<AlbumItem>>) {
                if (response.code() == 200) {
                    response.body()?.let {
                        callback.onSuccess(it)
                    }
                } else {
                    callback.onFailed()
                }
            }

            override fun onFailure(call: Call<ArrayList<AlbumItem>>, t: Throwable) {
                callback.onFailed()
            }
        })
    }

    fun getAlbumDetails(
        albumId: Int,
        callback: ApiResponseCallback<AlbumItem>
    ) {
        val auth = UserRelatedUtil.getUserApiAuth()

        val call = ApiRepository.getApiService().getAlbumDetails(
            auth,
            ConstantDataUtil.ACTION_ALBUMS,
            albumId.toString()
        )

        call.enqueue(object : Callback<ArrayList<AlbumItem>> {
            override fun onResponse(call: Call<ArrayList<AlbumItem>>, response: Response<ArrayList<AlbumItem>>) {
                if (response.code() == 200) {
                    response.body()?.let {
                        if (it.size > 0) {
                            callback.onSuccess(it[0])
                        }
                    }
                } else {
                    callback.onFailed()
                }
            }

            override fun onFailure(call: Call<ArrayList<AlbumItem>>, t: Throwable) {
                callback.onFailed()
            }
        })
    }
}

interface ApiResponseCallback<T> {
    fun onSuccess(responseData: T)
    fun onFailed() {}
}