package uk.co.theappexperts.shawn.loginapp.model.event;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import uk.co.theappexperts.shawn.loginapp.model.IData;
import uk.co.theappexperts.shawn.loginapp.model.location.Location;
import uk.co.theappexperts.shawn.loginapp.model.venue.Venue;

public class Event implements IData {

    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("start")
    @Expose
    private Start start;
    @SerializedName("ageRestriction")
    @Expose
    private String ageRestriction;
    @SerializedName("performance")
    @Expose
    private List<Performance> performance = new ArrayList<Performance>();
    @SerializedName("venue")
    @Expose
    private Venue venue;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     *
     * @return
     * The location
     */
    public Location getLocation() {
        return location;
    }

    /**
     *
     * @param location
     * The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     *
     * @return
     * The popularity
     */
    public Double getPopularity() {
        return popularity;
    }

    /**
     *
     * @param popularity
     * The popularity
     */
    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    /**
     *
     * @return
     * The uri
     */
    public String getUri() {
        return uri;
    }

    /**
     *
     * @param uri
     * The uri
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     *
     * @return
     * The displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     *
     * @param displayName
     * The displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The start
     */
    public Start getStart() {
        return start;
    }

    /**
     *
     * @param start
     * The start
     */
    public void setStart(Start start) {
        this.start = start;
    }

    /**
     *
     * @return
     * The ageRestriction
     */
    public String getAgeRestriction() {
        return ageRestriction;
    }

    /**
     *
     * @param ageRestriction
     * The ageRestriction
     */
    public void setAgeRestriction(String ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    /**
     *
     * @return
     * The performance
     */
    public List<Performance> getPerformance() {
        return performance;
    }

    /**
     *
     * @param performance
     * The performance
     */
    public void setPerformance(List<Performance> performance) {
        this.performance = performance;
    }

    /**
     *
     * @return
     * The venue
     */
    public Venue getVenue() {
        return venue;
    }

    /**
     *
     * @param venue
     * The venue
     */
    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}