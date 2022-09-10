package kr.co.ganeg.introducemarbleapp.network.api

import kr.co.tapplace.network.TestRetrofitClient
import retrofit2.Call
import retrofit2.http.GET

interface TestService {
    @GET("latestVersion")
    fun getLatestVersion(): Call<LatestVersion>

    companion object {
        private val testService: TestService =
            TestRetrofitClient().getRetrofit().create(TestService::class.java)
        fun getService() = testService
    }
}

data class LatestVersion(val appVersion: String)
