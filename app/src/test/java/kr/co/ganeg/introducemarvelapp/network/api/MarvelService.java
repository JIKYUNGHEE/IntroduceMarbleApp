package kr.co.ganeg.introducemarvelapp.network.api;

import kr.co.ganeg.introducemarvelapp.data.CharacterDataWrapper;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelService {

    @GET("characters")
    Call<CharacterDataWrapper> listCharacters(
            @Query("nameStartsWith") String query,
            @Query("offset") int offset,
            @Query("limit") int limit);

}
