package park.hyunwoo.releasedj.ui.album;

import javax.inject.Inject;

import park.hyunwoo.releasedj.ReleaseDJApplication;
import park.hyunwoo.releasedj.api.SpotifyApi;
import park.hyunwoo.releasedj.api.model.AlbumsResponse;
import rx.Observable;

public class AlbumModel implements AlbumContract.Model {

    @Inject
    SpotifyApi spotifyApi;

    public AlbumModel() {
        ReleaseDJApplication.getApp().getComponent().inject(this);
    }

    @Override
    public Observable<AlbumsResponse> getAlbums(String accessToken, int limit, int offset) {
        return spotifyApi.getAlbums("Bearer " + accessToken, limit, offset);
    }
}
