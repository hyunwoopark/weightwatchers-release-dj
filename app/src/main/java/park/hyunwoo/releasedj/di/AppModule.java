package park.hyunwoo.releasedj.di;

import dagger.Module;
import dagger.Provides;
import park.hyunwoo.releasedj.ui.album.AlbumContract;
import park.hyunwoo.releasedj.ui.album.AlbumModel;
import park.hyunwoo.releasedj.ui.album.AlbumPresenter;

@Module
public class AppModule {

    @Provides
    public AlbumContract.Model provideAlbumModel() {
        return new AlbumModel();
    }

    @Provides
    public AlbumContract.Presenter provideAlbumPresenter(AlbumContract.Model albumModel) {
        return new AlbumPresenter(albumModel);
    }
}