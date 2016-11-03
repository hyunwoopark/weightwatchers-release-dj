package park.hyunwoo.releasedj.ui.album;

import android.util.Log;

import park.hyunwoo.releasedj.api.model.Albums;
import park.hyunwoo.releasedj.ui.BaseView;
import park.hyunwoo.releasedj.util.RxUtil;
import rx.functions.Action1;
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
    public void loadAlbum() {
        offset += LIMIT;
        subscriptions.add(albumModel.getAlbums(LIMIT, offset)
                .compose(RxUtil.ioThreadToMainThread())
                .subscribe(new Action1<Albums>() {
                    @Override
                    public void call(Albums albums) {
                        albumView.addImages(albums);
                        Log.e("WTF", "call: shit happened");
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("WTF", "call: " + throwable.getMessage());
                    }
                }));
    }

    @Override
    public void setView(BaseView view) {
        this.albumView = (AlbumContract.View) view;
        loadAlbum();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        subscriptions.clear();
    }
}
