package uk.co.theappexperts.shawn.loginapp.model.artist;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import uk.co.theappexperts.shawn.loginapp.model.IData;
import uk.co.theappexperts.shawn.loginapp.model.event.Events;
import uk.co.theappexperts.shawn.loginapp.model.image.Image;
import uk.co.theappexperts.shawn.loginapp.model.image.Images;


public class Performer implements IData {

    @SerializedName("withdrawn")
    @Expose
    private String withdrawn;
    @SerializedName("creator")
    @Expose
    private String creator;
    @SerializedName("event_count")
    @Expose
    private String eventCount;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("images")
    @Expose
    private Images images;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("is_human")
    @Expose
    private String isHuman;
    @SerializedName("withdrawn_note")
    @Expose
    private Object withdrawnNote;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String desc;


    @SerializedName("short_bio")
    @Expose
    private String shortBio;
    @SerializedName("modified")
    @Expose
    private String modified;


    @SerializedName("events")
    @Expose
    private Events events;
    @SerializedName("long_bio")
    @Expose
    private Object longBio;
    @SerializedName("demand_count")
    @Expose
    private String demandCount;

    /**
     * @return The withdrawn
     */
    public String getWithdrawn() {
        return withdrawn;
    }

    /**
     * @param withdrawn The withdrawn
     */
    public void setWithdrawn(String withdrawn) {
        this.withdrawn = withdrawn;
    }

    /**
     * @return The creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param creator The creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * @return The eventCount
     */
    public String getEventCount() {
        return eventCount;
    }

    /**
     * @param eventCount The event_count
     */
    public void setEventCount(String eventCount) {
        this.eventCount = eventCount;
    }

    /**
     * @return The comments
     */


    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The trackbacks
     */


    /**
     * @return The images
     */
    public Images getImages() {
        return images;
    }

    /**
     * @param images The images
     */
    public void setImages(Images images) {
        this.images = images;
    }

    /**
     * @return The isHuman
     */
    public String getIsHuman() {
        return isHuman;
    }

    /**
     * @param isHuman The is_human
     */
    public void setIsHuman(String isHuman) {
        this.isHuman = isHuman;
    }

    /**
     * @return The withdrawnNote
     */
    public Object getWithdrawnNote() {
        return withdrawnNote;
    }

    /**
     * @param withdrawnNote The withdrawn_note
     */
    public void setWithdrawnNote(Object withdrawnNote) {
        this.withdrawnNote = withdrawnNote;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The demandMemberCount
     */


    /**
     * @return The vanityUrl
     */


    /**
     * @return The shortBio
     */
    public String getShortBio() {
        return shortBio;
    }

    /**
     * @param shortBio The short_bio
     */
    public void setShortBio(String shortBio) {
        this.shortBio = shortBio;
    }

    /**
     * @return The tags
     */


    /**
     * @return The modified
     */
    public String getModified() {
        return modified;
    }

    /**
     * @param modified The modified
     */
    public void setModified(String modified) {
        this.modified = modified;
    }

    /**
     * @return The created
     */


    /**
     * @return The demands
     */

    /**
     * @return The events
     */
    public Events getEvents() {
        return events;
    }

    /**
     * @param events The events
     */
    public void setEvents(Events events) {
        this.events = events;
    }

    /**
     * @return The longBio
     */
    public Object getLongBio() {
        return longBio;
    }

    /**
     * @param longBio The long_bio
     */
    public void setLongBio(Object longBio) {
        this.longBio = longBio;
    }

    /**
     * @return The demandCount
     */
    public String getDemandCount() {
        return demandCount;
    }

    /**
     * @param demandCount The demand_count
     */
    public void setDemandCount(String demandCount) {
        this.demandCount = demandCount;
    }

    /**
     * @return The userId
     */

    public String toString() {
        return  name;
    }

    @Override
    public String getDesc() {
        if (desc != null)
        return desc;
        else
            return shortBio;
    }

    @Override
    public String getImageUrl(boolean large) {
        if (large && images != null) {
            List<Image> imageList = ((Images) images).getImage();
            if (imageList != null && !imageList.isEmpty())
                return imageList.get(0).getLarge().getUrl();
        }
        else if (image != null)
            return image.getMedium().getUrl();
        return "";
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}