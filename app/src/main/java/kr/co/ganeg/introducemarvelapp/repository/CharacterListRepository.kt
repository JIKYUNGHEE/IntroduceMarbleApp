package kr.co.ganeg.introducemarvelapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import kr.co.ganeg.introducemarvelapp.api.MarvelResult
import kr.co.ganeg.introducemarvelapp.api.auth.AuthenticatorInterceptor
import kr.co.ganeg.introducemarvelapp.data.CharacterDataContainer
import kr.co.ganeg.introducemarvelapp.data.CharacterDataWrapper
import kr.co.ganeg.introducemarvelapp.model.CharacterModel
import kr.co.ganeg.introducemarvelapp.service.CharacterService
import kr.co.ganeg.introducemarvelapp.util.DataParser
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharacterListRepository {
    companion object {
        private const val TAG = "CharacterListRepository"
        private const val BASE_URL = "http://gateway.marvel.com/v1/public/"
    }

    private var characterService: CharacterService
    private var characterInfoMutableLiveData: MutableLiveData<MarvelResult<CharacterModel>> =
        MutableLiveData()

    init {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
        val authenticator = AuthenticatorInterceptor()

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor(authenticator)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        characterService = retrofit.create(CharacterService::class.java)
    }

    fun getCharacterList(query: String?, offset: Int, limit: Int) {
        characterService.listCharacters(query, offset, limit).enqueue(
            object : Callback<CharacterDataWrapper> {
                override fun onResponse(
                    call: Call<CharacterDataWrapper>,
                    response: Response<CharacterDataWrapper>
                ) {
                    val responseBody = response.body() as CharacterDataWrapper
                    val data = responseBody.data as CharacterDataContainer
                    Log.i(
                        TAG,
                        "onResponse: offset : ${data.offset}, limit: ${data.limit}, total: ${data.total}, count: ${data.count}"
                    )
                    Log.d(
                        TAG,
                        "firstData --- ${
                            GsonBuilder().setPrettyPrinting().create()
                                .toJson(data.results[0])
                        }"
                    )

                    val result: MarvelResult<CharacterModel> = DataParser.parse(responseBody)
                    characterInfoMutableLiveData.postValue(result)
                }

                override fun onFailure(call: Call<CharacterDataWrapper>, t: Throwable) {
                    Log.e(TAG, "onFailure: error. cause: ${t.message}")
                    //characterInfoMutableLiveData.postValue(null) //FIXME. non-null
                }
            }
        )
    }

    fun getCharacterListLiveData(): LiveData<MarvelResult<CharacterModel>> {
        return this.characterInfoMutableLiveData
    }
}
