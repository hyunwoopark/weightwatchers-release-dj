package park.hyunwoo.releasedj.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiProvider {

    private static final String API_BASE_URL = "https://api.spotify.com/v1/";
    private static final String ACCESS_TOKEN = "Bearer " + "BQBSUw96a95rpOjXkCThw1HbLmLryFPPzFhpp-L0VsCRKgR9iFOc_xB9cwOP_wZaJu-b7CPQhpdR9N9A7_46YpZMRU5NJwYihvFW1hT3IDezV1xF";

    public static SpotifyApi getSpotifyApi() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

//        httpClient.addInterceptor(chain -> {
//            Request original = chain.request();
//            Request.Builder requestBuilder = original.newBuilder()
//                    .header("Authorization", "Bearer " + ACCESS_TOKEN)
//                    .method(original.method(), original.body());
//            Request request = requestBuilder.build();
//            return chain.proceed(request);
//        });

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create());

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(SpotifyApi.class);
    }
}