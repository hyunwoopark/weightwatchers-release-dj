package park.hyunwoo.releasedj.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by Hyunwoo Park on 11/3/2016.
 */

public class Tracks {

    @SerializedName("items")
    @Expose
    private List<Track> tracks = new ArrayList<>();

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public Observable<String> getTracksString() {
        return Observable.from(tracks)
                .map(Track::getName)
                .reduce((s, s2) -> s + ", " + s2);
    }
}
