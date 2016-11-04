package park.hyunwoo.releasedj.di;

import javax.inject.Singleton;

import dagger.Component;
import park.hyunwoo.releasedj.api.ApiProvider;
import park.hyunwoo.releasedj.ui.album.AlbumActivity;
import park.hyunwoo.releasedj.ui.album.AlbumModel;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {

    void inject(AlbumModel albumModel);

    void inject(AlbumActivity albumActivity);

    void inject(ApiProvider apiProvider);
}