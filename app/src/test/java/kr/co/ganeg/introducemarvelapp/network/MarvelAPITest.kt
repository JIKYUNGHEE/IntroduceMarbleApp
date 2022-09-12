package kr.co.ganeg.introducemarvelapp.network

import org.junit.Before
import org.junit.Test

class MarvelAPITest {
    private lateinit var marvelAPI: MarvelAPI

    @Test
    fun `getCharacterList success test api execute`() {
        val result = marvelAPI.listCharactersSync(0)
        println("result.isSuccessful: ${result.body()}")
    }

    @Before
    fun setUp() {
        marvelAPI = MarvelAPI.getInstance()
    }
}