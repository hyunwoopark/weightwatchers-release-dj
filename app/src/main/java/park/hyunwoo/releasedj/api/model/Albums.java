package park.hyunwoo.releasedj.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import nz.bradcampbell.paperparcel.PaperParcel;

@PaperParcel
public final class Albums {

    @SerializedName("items")
    @Expose
    private List<Album> albums = new ArrayList<>();

    public Albums() {
    }

    public Albums(final List<Album> albums) {
        this.albums = albums;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(final List<Album> albums) {
        this.albums = albums;
    }
}
