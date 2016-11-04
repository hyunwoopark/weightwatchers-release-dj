package park.hyunwoo.releasedj.ui.detail;

import park.hyunwoo.releasedj.api.model.Album;
import park.hyunwoo.releasedj.ui.BasePresenter;
import park.hyunwoo.releasedj.ui.BaseView;
import rx.Observable;

public class DetailContract {

    public interface Model {

        Observable<Album> getAlbum(String id);
    }

    public interface View extends BaseView {

        void addDetails(Album album);
    }

    public interface Presenter extends BasePresenter {

        void loadAlbum(String id);
    }
}
