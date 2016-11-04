package park.hyunwoo.releasedj.ui.detail;

import park.hyunwoo.releasedj.ui.BaseView;
import park.hyunwoo.releasedj.util.RxUtil;
import rx.subscriptions.CompositeSubscription;

public class DetailPresenter implements DetailContract.Presenter {

    private CompositeSubscription subscriptions = new CompositeSubscription();
    private final DetailContract.Model detailModel;
    private DetailContract.View detailView;

    public DetailPresenter(DetailContract.Model detailModel) {
        this.detailModel = detailModel;
    }

    @Override
    public void loadAlbum(String id) {
        subscriptions.add(detailModel.getAlbum(id)
                .compose(RxUtil.ioThreadToMainThread())
                .subscribe(album -> detailView.addDetails(album),
                        throwable -> detailView.showSnackbarError(throwable)));
    }

    @Override
    public void setView(BaseView view) {
        this.detailView = (DetailContract.View) view;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        subscriptions.clear();
    }
}
