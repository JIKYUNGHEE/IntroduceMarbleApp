package kr.co.ganeg.introducemarvelapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kr.co.ganeg.introducemarvelapp.api.MarvelResult
import kr.co.ganeg.introducemarvelapp.model.CharacterModel
import kr.co.ganeg.introducemarvelapp.repository.CharacterListRepository

class CharacterListViewModel(application: Application): AndroidViewModel(application) {

    private var characterListRepository: CharacterListRepository = CharacterListRepository()
    private var characterResponseLiveData: LiveData<MarvelResult<CharacterModel>> =
        characterListRepository.getCharacterListLiveData()

    fun getCharacterList(query: String?, page: Int) {
        characterListRepository.getCharacterList(query, page, 20)
    }

    fun getCharacterResponseLiveData(): LiveData<MarvelResult<CharacterModel>> {
        return characterResponseLiveData
    }
}
