package park.hyunwoo.releasedj.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import park.hyunwoo.releasedj.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hyunwoo Park on 10/30/2016.
 */

public class ApiProvider {

    private static final String API_BASE_URL = "https://api.spotify.com/v1/";

    private static final String CLIENT_ID = BuildConfig.CLIENT_ID;
    private static final String CLIENT_SECRET = BuildConfig.CLIENT_SECRET;
    private Retrofit retrofit;
    private Retrofit retrofitAuthenticated;
    private static boolean tokenExpired = true;

    public static SpotifyApi getSpotifyApi() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", BuildConfig.OAUTH_TOKEN); // <-- this is the important line

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(SpotifyApi.class);
    }

//
//    public static SpotifyApi getSpotifyApi() {
//        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
//        OkHttpClient clientNormal;
//        OkHttpClient clientAuthenticated;
//        builder.interceptors().add(new Interceptor() {
//            @Override
//            public okhttp3.Response intercept(Chain chain) throws IOException {
//                Request originalRequest = chain.request();
//                Request.Builder builder = originalRequest.newBuilder().header("Authorization:Bearer ", accessToken).
//                        method(originalRequest.method(), originalRequest.body());
//                okhttp3.Response response = chain.proceed(builder.build());
//                /*
//                implies that the token has expired
//                or was never initialized
//                 */
//                if (response.code() == 401) {
//                    tokenExpired = true;
//                    getAuthenticationToken();
//                    builder = originalRequest.newBuilder().header("Authorization:Bearer ", accessToken).
//                            method(originalRequest.method(), originalRequest.body());
//                    response = chain.proceed(builder.build());
//                }
//                return response;
//            }
//        });
//
//        clientAuthenticated = builder.build();
//        Retrofit retrofitAuthenticated = new Retrofit.Builder().client(clientAuthenticated)
//                .baseUrl(API_BASE_URL)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        OkHttpClient.Builder builder1 = new OkHttpClient().newBuilder();
//        builder1.authenticator(new Authenticator() {
//            @Override
//            public Request authenticate(Route route, okhttp3.Response response) throws IOException {
//                String authentication = Credentials.basic(CLIENT_ID, CLIENT_SECRET);
//                Request.Builder builder = response.request().newBuilder().addHeader("Authorization", authentication);
//                return builder.build();
//            }
//        });
////        clientNormal = builder1.build();
////        retrofit = new Retrofit.Builder().client(clientNormal).
////                baseUrl(API_ENDPOINT).
////                addConverterFactory(GsonConverterFactory.create()).build();
//
//        return retrofitAuthenticated.create(SpotifyApi.class);
//    }
//
//    private void getAuthenticationToken() {
//        LyftService lyftService = this.retrofit.create(LyftService.class);
//        Call<OAuthResponse> authRequestCall = lyftService.getAccessToken(oAuthRequest);
//        Response<OAuthResponse> response = null;
//        try {
//            response = authRequestCall.execute();
//            if (response.isSuccessful()) {
//                accessToken = response.body().getAccessToken();
//            }
//        } catch (IOException e) {
//            Timber.e("Exception occurred due to ", e);
//        }

//    }
}