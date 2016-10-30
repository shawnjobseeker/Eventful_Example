package uk.co.theappexperts.shawn.loginapp.model.artist;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import uk.co.theappexperts.shawn.loginapp.model.IData;
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
    @SerializedName("comments")
    @Expose
    private Object comments;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("trackbacks")
    @Expose
    private Object trackbacks;
    @SerializedName("links")
    @Expose
    private Object links;
    @SerializedName("images")
    @Expose
    private Object images;
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
    @SerializedName("demand_member_count")
    @Expose
    private String demandMemberCount;
    @SerializedName("vanity_url")
    @Expose
    private Object vanityUrl;
    @SerializedName("categories")
    @Expose
    private Object categories;
    @SerializedName("short_bio")
    @Expose
    private String shortBio;
    @SerializedName("tags")
    @Expose
    private Object tags;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("demands")
    @Expose
    private Demands demands;
    @SerializedName("events")
    @Expose
    private Object events;
    @SerializedName("long_bio")
    @Expose
    private Object longBio;
    @SerializedName("demand_count")
    @Expose
    private String demandCount;
    @SerializedName("user_id")
    @Expose
    private Object userId;

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
    public Object getComments() {
        return comments;
    }

    /**
     * @param comments The comments
     */
    public void setComments(Object comments) {
        this.comments = comments;
    }

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
    public Object getTrackbacks() {
        return trackbacks;
    }

    /**
     * @param trackbacks The trackbacks
     */
    public void setTrackbacks(Object trackbacks) {
        this.trackbacks = trackbacks;
    }

    /**
     * @return The links
     */
    public Object getLinks() {
        return links;
    }

    /**
     * @param links The links
     */
    public void setLinks(Object links) {
        this.links = links;
    }

    /**
     * @return The images
     */
    public Object getImages() {
        return images;
    }

    /**
     * @param images The images
     */
    public void setImages(Object images) {
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
    public String getDemandMemberCount() {
        return demandMemberCount;
    }

    /**
     * @param demandMemberCount The demand_member_count
     */
    public void setDemandMemberCount(String demandMemberCount) {
        this.demandMemberCount = demandMemberCount;
    }

    /**
     * @return The vanityUrl
     */
    public Object getVanityUrl() {
        return vanityUrl;
    }

    /**
     * @param vanityUrl The vanity_url
     */
    public void setVanityUrl(Object vanityUrl) {
        this.vanityUrl = vanityUrl;
    }

    /**
     * @return The categories
     */
    public Object getCategories() {
        return categories;
    }

    /**
     * @param categories The categories
     */
    public void setCategories(Object categories) {
        this.categories = categories;
    }

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
    public Object getTags() {
        return tags;
    }

    /**
     * @param tags The tags
     */
    public void setTags(Object tags) {
        this.tags = tags;
    }

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
    public String getCreated() {
        return created;
    }

    /**
     * @param created The created
     */
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     * @return The demands
     */
    public Demands getDemands() {
        return demands;
    }

    /**
     * @param demands The demands
     */
    public void setDemands(Demands demands) {
        this.demands = demands;
    }

    /**
     * @return The events
     */
    public Object getEvents() {
        return events;
    }

    /**
     * @param events The events
     */
    public void setEvents(Object events) {
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
    public Object getUserId() {
        return userId;
    }

    /**
     * @param userId The user_id
     */
    public void setUserId(Object userId) {
        this.userId = userId;
    }
    public String toString() {
        return  name;
    }

    @Override
    public String getDesc() {
        return shortBio;
    }

    @Override
    public String getImageUrl() {
            return image.getMedium().getUrl();
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}