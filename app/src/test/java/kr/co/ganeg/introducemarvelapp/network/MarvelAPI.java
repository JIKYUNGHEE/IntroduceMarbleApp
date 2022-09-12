package kr.co.ganeg.introducemarbleapp.network;

import java.io.IOException;

import kr.co.ganeg.introducemarbleapp.BuildConfig;
import kr.co.ganeg.introducemarbleapp.api.auth.AuthenticatorInterceptor;
import kr.co.ganeg.introducemarbleapp.api.data.CharacterDataWrapper;
import kr.co.ganeg.introducemarbleapp.network.api.MarvelService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MarvelAPI {
    //pagination set to 20. list of 20 will be fected in one request
    private static final int MAX_FETCH_LIMIT = 20;

    private static final String BASE_URL = "http://gateway.marvel.com/v1/public/";
    private static MarvelAPI sMarvelApi;
    private final MarvelService mService;

    private MarvelAPI() {
        AuthenticatorInterceptor authenticator = new AuthenticatorInterceptor();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? Level.BODY : Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(authenticator)
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mService = retrofit.create(MarvelService.class);
    }

    public static MarvelAPI getInstance() {
        if (sMarvelApi == null) {
            sMarvelApi = new MarvelAPI();
        }
        return sMarvelApi;
    }


    public void listCharacters(int offset, MarvelCallback<CharacterDataWrapper> callback) {
        mService.listCharacters(null, offset, MAX_FETCH_LIMIT).enqueue(callback);
    }

    public Response<CharacterDataWrapper> listCharactersSync(int offset) throws IOException {
        return mService.listCharacters(null, offset, MAX_FETCH_LIMIT).execute();
    }
}
