package kr.co.ganeg.introducemarvelapp.api.auth;

import java.io.IOException;

import kr.co.ganeg.introducemarvelapp.BuildConfig;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticatorInterceptor implements Interceptor {

    private static final String QUERY_NAME_TIMESTAMP = "ts";
    private static final String QUERY_NAME_APIKEY = "apikey";
    private static final String QUERY_NAME_HASH = "hash";

    @Override
    public Response intercept(Chain chain) throws IOException {

        String timeStamp = String.valueOf(System.currentTimeMillis());
        // encrypted data received
        String hash = HashHelper.generate(timeStamp + BuildConfig.MARVEL_PRIVATE_KEY + BuildConfig.MARVEL_PUBLIC_KEY);

        Request currentRequest = chain.request();

        HttpUrl url = currentRequest.url().newBuilder()
                .addQueryParameter(QUERY_NAME_TIMESTAMP, timeStamp)
                .addQueryParameter(QUERY_NAME_APIKEY, BuildConfig.MARVEL_PUBLIC_KEY)
                .addQueryParameter(QUERY_NAME_HASH, hash).build();

        Request newRequest = currentRequest.newBuilder().url(url).build();

        return chain.proceed(newRequest);
    }
}
