package uk.co.example.shawn.loginapp.model.image;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Image {

    @SerializedName("small")
    @Expose
    private Small small;
    @SerializedName("width")
    @Expose
    private String width;
    @SerializedName("creator")
    @Expose
    private Object creator;
    @SerializedName("thumb")
    @Expose
    private Thumb thumb;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("medium")
    @Expose
    private Medium medium;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("large")
    @Expose
    private Large large;
    // custom deserialised array
    private List<Image> image;

    /**
     *
     * @return
     * The small
     */
    public Small getSmall() {
        return small;
    }

    /**
     *
     * @param small
     * The small
     */
    public void setSmall(Small small) {
        this.small = small;
    }

    /**
     *
     * @return
     * The width
     */
    public String getWidth() {
        return width;
    }

    /**
     *
     * @param width
     * The width
     */
    public void setWidth(String width) {
        this.width = width;
    }

    /**
     *
     * @return
     * The creator
     */
    public Object getCreator() {
        return creator;
    }

    /**
     *
     * @param creator
     * The creator
     */
    public void setCreator(Object creator) {
        this.creator = creator;
    }

    /**
     *
     * @return
     * The thumb
     */
    public Thumb getThumb() {
        return thumb;
    }

    /**
     *
     * @param thumb
     * The thumb
     */
    public void setThumb(Thumb thumb) {
        this.thumb = thumb;
    }

    /**
     *
     * @return
     * The height
     */
    public String getHeight() {
        return height;
    }

    /**
     *
     * @param height
     * The height
     */
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The medium
     */
    public Medium getMedium() {
        return medium;
    }

    /**
     *
     * @param medium
     * The medium
     */
    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    public Large getLarge() {
        return large;
    }

    public void setLarge(Large large) {
        this.large = large;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }
}