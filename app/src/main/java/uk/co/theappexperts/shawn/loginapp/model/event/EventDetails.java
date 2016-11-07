package uk.co.theappexperts.shawn.loginapp.model.event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import uk.co.theappexperts.shawn.loginapp.model.IData;
import uk.co.theappexperts.shawn.loginapp.model.Links;
import uk.co.theappexperts.shawn.loginapp.model.image.Image;
import uk.co.theappexperts.shawn.loginapp.model.image.Images;

/**
 * Created by TheAppExperts on 04/11/2016.
 */

public class EventDetails  implements IData{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("images")
    @Expose
    private Images images;
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
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    /*@SerializedName("performers")
    @Expose
    private Performers performers;*/
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("country")
    @Expose
    private String country;
    /*@SerializedName("categories")
    @Expose
    private Categories categories;*/
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("postal_code")
    @Expose
    private String postalCode;
    @SerializedName("links")
    @Expose
    private Links links;


    public Image getImage() {
        return image;
    }


    public Images getImages() {
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

    public void setImages(Images images) {
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }



    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }



    public void setRegion(String region) {
        this.region = region;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String getDesc() {
        return description;
    }

    @Override
    public String getImageUrl(boolean large) {
        if (large && images != null) {
                List<Image> imageList = images.getImage();
                if (imageList != null && !imageList.isEmpty())
                    return imageList.get(0).getLarge().getUrl();
            }
        else if (image != null)
                return image.getMedium().getUrl();
            return "";

    }

    @Override
    public String getName() {
        return title;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }
}
