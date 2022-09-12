package kr.co.tapplace.network

import androidx.lifecycle.MutableLiveData
import kr.co.ganeg.introducemarvelapp.BuildConfig
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class TestRetrofitClient {
    private val BASE_URL = "http://localhost:3001/"

    private val CONNECTION_TIMEOUT = 10
    private val READ_TIMEOUT = 30
    private val WRITE_TIMEOUT = 30

    private var retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    fun getRetrofit() = retrofit

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        val connectionPool = ConnectionPool()
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .connectionPool(connectionPool)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    internal fun <T> callBack(
        call: Call<T>,
        liveData: MutableLiveData<Result<T>>
    ) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                println("onResponse ${call.request()}")

                if (response.isSuccessful) {
                    if (response.body() != null)
                        liveData.value = (Result.success(response.body()!!))
                    else
                        liveData.value = null
                } else {
                    liveData.value = (
                            Result.failure(
                                Exception("response is not successful, status: ${response.code()}")
                            )
                            )
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                println("onFailure")
                liveData.value = (Result.failure(Exception("network error : onFailure")))
            }
        })
    }
}
