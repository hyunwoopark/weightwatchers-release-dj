package park.hyunwoo.releasedj.ui;


public interface BasePresenter {

    void setView(BaseView view);

    void subscribe();

    void unsubscribe();
}