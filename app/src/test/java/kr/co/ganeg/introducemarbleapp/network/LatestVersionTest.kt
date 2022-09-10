package kr.co.tapplace.network

import kr.co.ganeg.introducemarbleapp.network.api.LatestVersion
import kr.co.ganeg.introducemarbleapp.network.api.TestService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.kotlin.any
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(JUnit4::class)
class LatestVersionTest {
    private lateinit var testLatestVersionService: TestService

    private lateinit var mockedApiInterface: TestService
    private lateinit var mockedCall: Call<LatestVersion>

    @Test
    fun `getLatestVersion success test api execute`() {
        val result = testLatestVersionService.getLatestVersion().execute()
        println("result.isSuccessful: ${result.body()}")
    }

    @Test
    fun `getLatestVersion success test api enqueue`() {
        Mockito.`when`(mockedApiInterface.getLatestVersion()).thenReturn(mockedCall)

        Mockito.doAnswer {
            println("callback")
            val callback = it.arguments[0] as Callback<LatestVersion>
            callback.onResponse(mockedCall, Response.success(LatestVersion("1.2.2")))

            null
        }.`when`(mockedCall).enqueue(any())
    }

    @Before
    fun setUp() {
        testLatestVersionService =
            TestRetrofitClient().getRetrofit().create(TestService::class.java)

        mockedApiInterface = Mockito.mock(TestService::class.java)
        mockedCall = Mockito.mock(Call::class.java) as Call<LatestVersion>
    }
}

