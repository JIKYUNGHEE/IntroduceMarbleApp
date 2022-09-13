package kr.co.ganeg.introducemarvelapp.service

import kr.co.ganeg.introducemarvelapp.data.CharacterDataWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("characters")
    fun listCharacters(
        @Query("nameStartsWith") query: String?,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<CharacterDataWrapper>
}