package uk.co.theappexperts.shawn.loginapp.model.event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import uk.co.theappexperts.shawn.loginapp.model.IData;
import uk.co.theappexperts.shawn.loginapp.model.image.Image;

/**
 * Created by TheAppExperts on 30/10/2016.
 */

public class Event implements IData {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("images")
    @Expose
    private Object images;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("stop_time")
    @Expose
    private Object stopTime;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("title")
    @Expose
    private String title;

    public Image getImage() {
        return image;
    }

    public Object getImages() {
        return images;
    }

    public Object getStopTime() {
        return stopTime;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getTitle() {
        return title;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImages(Object images) {
        this.images = images;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setStopTime(Object stopTime) {
        this.stopTime = stopTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDesc() {
        return description;
    }

    @Override
    public String getImageUrl() {
        return image.getMedium().getUrl();
    }

    @Override
    public String getName() {
        return title;
    }
}
