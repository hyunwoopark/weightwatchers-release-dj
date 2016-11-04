package park.hyunwoo.releasedj.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public final class Album {

    private String name;
    private String id;
    private String date;

    @SerializedName("images")
    @Expose
    private List<Images> images = new ArrayList<>();

    public Album() {
    }

    public Album(final String name, final List<Images> image) {
        this.name = name;
        this.images = images;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(final List<Images> images) {
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