package park.hyunwoo.releasedj.ui.album;

import park.hyunwoo.releasedj.adapter.AlbumAdapter;
import park.hyunwoo.releasedj.api.model.Albums;
import park.hyunwoo.releasedj.api.model.AlbumsResponse;
import park.hyunwoo.releasedj.ui.BasePresenter;
import park.hyunwoo.releasedj.ui.BaseView;
import rx.Observable;

public final class AlbumContract {

    public interface Model {

        Observable<AlbumsResponse> getAlbums(String accessToken, int limit, int offset);
    }

    public interface View extends BaseView {

        void addImages(Albums albums);

        void setRecyclerAdapter(AlbumAdapter albumAdapter);
    }

    public interface Presenter extends BasePresenter {

        void loadAlbum(String accessToken);
    }
}