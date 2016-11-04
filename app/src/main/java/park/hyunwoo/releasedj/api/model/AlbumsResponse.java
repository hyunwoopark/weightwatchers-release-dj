package park.hyunwoo.releasedj.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlbumsResponse {

    @SerializedName("albums")
    @Expose
    private Albums albums;

    public AlbumsResponse() {
    }

    public AlbumsResponse(Albums albums) {
        this.albums = albums;
    }

    public Albums getAlbums() {
        return albums;
    }

    public void setAlbums(final Albums albums) {
        this.albums = albums;
    }
}
