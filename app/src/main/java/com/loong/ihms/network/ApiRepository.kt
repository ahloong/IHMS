package com.loong.ihms.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.loong.ihms.model.Song
import com.loong.ihms.model.UserProfile
import com.loong.ihms.utils.ConstantDataUtil
import com.loong.ihms.utils.LocalStorageUtil
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

    //Retrofit, for compiling the URL before send out
    private fun getRetrofitInstance(): Retrofit {
        if (retrofit == null) {
            val baseUrl = LocalStorageUtil.getInstance().readString(LocalStorageUtil.MAIN_API_URL)
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
        val keyHash = password.hashSha256()
        val passphrase = "${timestamp}${keyHash}".hashSha256()

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

    fun getSongList(
        callback: ApiResponseCallback<ArrayList<Song>>
    ) {
        val auth = LocalStorageUtil.getInstance().readString(LocalStorageUtil.USER_API_AUTH)

        val call = ApiRepository.getApiService().getSongList(
            ConstantDataUtil.ACTION_GET_INDEXES,
            auth,
            ConstantDataUtil.TYPE_SONG
        )

        call.enqueue(object : Callback<ArrayList<Song>> {
            override fun onResponse(call: Call<ArrayList<Song>>, response: Response<ArrayList<Song>>) {
                if (response.code() == 200) {
                    response.body()?.let {
                        callback.onSuccess(it)
                    }
                } else {
                    callback.onFailed()
                }
            }

            override fun onFailure(call: Call<ArrayList<Song>>, t: Throwable) {
                callback.onFailed()
            }
        })
    }

    fun playSomething() {
        val auth = LocalStorageUtil.getInstance().readString(LocalStorageUtil.USER_API_AUTH)

    }
}

interface ApiResponseCallback<T> {
    fun onSuccess(responseData: T)
    fun onFailed()
}