package park.hyunwoo.releasedj.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by Hyunwoo Park on 11/3/2016.
 */

public class DetailedAlbum {

    List<Artists> artists = new ArrayList<>();
    List<Images> images = new ArrayList<>();
    List<Tracks> tracks = new ArrayList<>();
    String name;

    @SerializedName("release_date")
    @Expose
    String releaseDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Artists> getArtists() {
        return artists;
    }

    public void setArtists(List<Artists> artists) {
        this.artists = artists;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public List<Tracks> getTracks() {
        return tracks;
    }

    public void setTracks(List<Tracks> tracks) {
        this.tracks = tracks;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Observable<String> getArtistsString() {
        return Observable.from(artists)
                .map(Artists::getName)
                .reduce((s, s2) -> s + ", " + s2);
    }

    public Observable<String> getTracksString() {
        return Observable.from(tracks)
                .map(Tracks::getName)
                .reduce((s, s2) -> s + ", " + s2);
    }
}
