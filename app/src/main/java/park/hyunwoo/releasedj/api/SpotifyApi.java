package park.hyunwoo.releasedj.api;


import park.hyunwoo.releasedj.api.model.Album;
import park.hyunwoo.releasedj.api.model.Albums;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface SpotifyApi {

    @GET("browse/new-releases")
    Observable<Albums> getAlbums(@Query("limit") final int limit,
                                 @Query("offset") final int offset);

    @GET("albums/{id}")
    Observable<Album> getAlbum(@Path("id") String id);
}