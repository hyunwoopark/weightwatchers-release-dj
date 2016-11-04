package park.hyunwoo.releasedj.di;

import dagger.Module;
import dagger.Provides;
import park.hyunwoo.releasedj.ui.album.AlbumContract;
import park.hyunwoo.releasedj.ui.album.AlbumModel;
import park.hyunwoo.releasedj.ui.album.AlbumPresenter;
import park.hyunwoo.releasedj.ui.detail.DetailContract;
import park.hyunwoo.releasedj.ui.detail.DetailModel;
import park.hyunwoo.releasedj.ui.detail.DetailPresenter;

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

    @Provides
    public DetailContract.Model provideDetailModel() {
        return new DetailModel();
    }

    @Provides
    public DetailContract.Presenter provideDetailPresenter(DetailContract.Model detailModel) {
        return new DetailPresenter(detailModel);
    }
}