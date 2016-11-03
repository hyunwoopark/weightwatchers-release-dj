package park.hyunwoo.releasedj.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class Album {

    private String name;
    private String id;

    @SerializedName("images")
    @Expose
    private Images images;

    public Album() {
    }

    public Album(final String name, final Images image) {
        this.name = name;
        this.images = images;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(final Images images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}