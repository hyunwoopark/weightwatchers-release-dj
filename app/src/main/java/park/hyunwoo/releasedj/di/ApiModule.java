package park.hyunwoo.releasedj.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import park.hyunwoo.releasedj.api.ApiProvider;
import park.hyunwoo.releasedj.api.SpotifyApi;

@Module
public class ApiModule {

    @Provides
    @Singleton
    public SpotifyApi provideSpotifyApi() {
        return ApiProvider.getSpotifyApi();
    }

}