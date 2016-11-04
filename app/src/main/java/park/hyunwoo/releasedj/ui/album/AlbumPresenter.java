package park.hyunwoo.releasedj.ui.album;

import park.hyunwoo.releasedj.ui.BaseView;
import park.hyunwoo.releasedj.util.RxUtil;
import rx.subscriptions.CompositeSubscription;

public class AlbumPresenter implements AlbumContract.Presenter {

    private static final int LIMIT = 20;

    private CompositeSubscription subscriptions = new CompositeSubscription();
    private final AlbumContract.Model albumModel;
    private int offset = 0;
    private AlbumContract.View albumView;

    public AlbumPresenter(AlbumContract.Model albumModel) {
        this.albumModel = albumModel;
    }

    @Override
    public void loadAlbum(String accessToken) {
        offset += LIMIT;
        subscriptions.add(albumModel.getAlbums(accessToken, LIMIT, offset)
                .compose(RxUtil.ioThreadToMainThread())
                .subscribe(albumsResponse -> albumView.addImages(albumsResponse.getAlbums()),
                        throwable -> albumView.showSnackbarError(throwable)));
    }

    @Override
    public void setView(BaseView view) {
        this.albumView = (AlbumContract.View) view;
//        loadAlbum();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        subscriptions.clear();
    }
}
