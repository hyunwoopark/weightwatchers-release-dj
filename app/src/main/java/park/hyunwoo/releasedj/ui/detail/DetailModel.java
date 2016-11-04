package park.hyunwoo.releasedj.ui.detail;

import javax.inject.Inject;

import park.hyunwoo.releasedj.ReleaseDJApplication;
import park.hyunwoo.releasedj.api.SpotifyApi;
import park.hyunwoo.releasedj.api.model.Album;
import rx.Observable;

public class DetailModel implements DetailContract.Model {

    @Inject
    SpotifyApi spotifyApi;

    public DetailModel() {
        ReleaseDJApplication.getApp().getComponent().inject(this);
    }

    @Override
    public Observable<Album> getAlbum(String id) {
        return spotifyApi.getAlbum(id);
    }
}
