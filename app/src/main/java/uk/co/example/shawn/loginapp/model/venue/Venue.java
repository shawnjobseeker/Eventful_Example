package uk.co.example.shawn.loginapp.model.venue;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import uk.co.example.shawn.loginapp.model.Comments;
import uk.co.example.shawn.loginapp.model.IData;
import uk.co.example.shawn.loginapp.model.Links;
import uk.co.example.shawn.loginapp.model.Tags;
import uk.co.example.shawn.loginapp.model.event.Events;
import uk.co.example.shawn.loginapp.model.image.Image;
import uk.co.example.shawn.loginapp.model.image.Images;

public class Venue implements IData {

    @SerializedName("withdrawn")
    @Expose
    private String withdrawn;
    @SerializedName("children")
    @Expose
    private Object children;
    @SerializedName("comments")
    @Expose
    private Comments comments;
    @SerializedName("region_abbr")
    @Expose
    private String regionAbbr;
    @SerializedName("postal_code")
    @Expose
    private String postalCode;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("metro")
    @Expose
    private String metro;
    @SerializedName("links")
    @Expose
    private Links links;
    @SerializedName("images")
    @Expose
    private Images images;
    @SerializedName("withdrawn_note")
    @Expose
    private Object withdrawnNote;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("country_abbr")
    @Expose
    private String countryAbbr;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("properties")
    @Expose
    private Object properties;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("venue_display")
    @Expose
    private String venueDisplay;
    @SerializedName("parents")
    @Expose
    private Object parents;
    @SerializedName("geocode_type")
    @Expose
    private String geocodeType;
    @SerializedName("tz_olson_path")
    @Expose
    private String tzOlsonPath;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("trackbacks")
    @Expose
    private Object trackbacks;
    @SerializedName("country_name")
    @Expose
    private String country;
    @SerializedName("country")
    @Expose
    private String country_;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("country_abbr2")
    @Expose
    private String countryAbbr2;
    @SerializedName("venue_type")
    @Expose
    private String venueType;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("events")
    @Expose
    private Events events;



    /**
     *
     * @return
     * The withdrawn
     */
    public String getWithdrawn() {
        return withdrawn;
    }

    /**
     *
     * @param withdrawn
     * The withdrawn
     */
    public void setWithdrawn(String withdrawn) {
        this.withdrawn = withdrawn;
    }

    /**
     *
     * @return
     * The children
     */
    public Object getChildren() {
        return children;
    }

    /**
     *
     * @param children
     * The children
     */
    public void setChildren(Object children) {
        this.children = children;
    }

    /**
     *
     * @return
     * The comments
     */
    public Comments getComments() {
        return comments;
    }

    /**
     *
     * @param comments
     * The comments
     */
    public void setComments(Comments comments) {
        this.comments = comments;
    }

    /**
     *
     * @return
     * The regionAbbr
     */
    public String getRegionAbbr() {
        return regionAbbr;
    }

    /**
     *
     * @param regionAbbr
     * The region_abbr
     */
    public void setRegionAbbr(String regionAbbr) {
        this.regionAbbr = regionAbbr;
    }

    /**
     *
     * @return
     * The postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     *
     * @param postalCode
     * The postal_code
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     *
     * @return
     * The latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     * The latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
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

    /**
     *
     * @return
     * The address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     * The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     * The metro
     */
    public String getMetro() {
        return metro;
    }

    /**
     *
     * @param metro
     * The metro
     */
    public void setMetro(String metro) {
        this.metro = metro;
    }

    /**
     *
     * @return
     * The links
     */
    public Links getLinks() {
        return links;
    }

    /**
     *
     * @param links
     * The links
     */
    public void setLinks(Links links) {
        this.links = links;
    }

    /**
     *
     * @return
     * The images
     */
    public Images getImages() {
        return images;
    }

    /**
     *
     * @param images
     * The images
     */
    public void setImages(Images images) {
        this.images = images;
    }

    /**
     *
     * @return
     * The withdrawnNote
     */
    public Object getWithdrawnNote() {
        return withdrawnNote;
    }

    /**
     *
     * @param withdrawnNote
     * The withdrawn_note
     */
    public void setWithdrawnNote(Object withdrawnNote) {
        this.withdrawnNote = withdrawnNote;
    }

    /**
     *
     * @return
     * The longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     * The longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     * The countryAbbr
     */
    public String getCountryAbbr() {
        return countryAbbr;
    }

    /**
     *
     * @param countryAbbr
     * The country_abbr
     */
    public void setCountryAbbr(String countryAbbr) {
        this.countryAbbr = countryAbbr;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The region
     */
    public String getRegion() {
        return region;
    }

    /**
     *
     * @param region
     * The region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The properties
     */
    public Object getProperties() {
        return properties;
    }

    /**
     *
     * @param properties
     * The properties
     */
    public void setProperties(Object properties) {
        this.properties = properties;
    }

    /**
     *
     * @return
     * The modified
     */
    public String getModified() {
        return modified;
    }

    /**
     *
     * @param modified
     * The modified
     */
    public void setModified(String modified) {
        this.modified = modified;
    }

    /**
     *
     * @return
     * The venueDisplay
     */
    public String getVenueDisplay() {
        return venueDisplay;
    }

    /**
     *
     * @param venueDisplay
     * The venue_display
     */
    public void setVenueDisplay(String venueDisplay) {
        this.venueDisplay = venueDisplay;
    }

    /**
     *
     * @return
     * The parents
     */
    public Object getParents() {
        return parents;
    }

    /**
     *
     * @param parents
     * The parents
     */
    public void setParents(Object parents) {
        this.parents = parents;
    }

    /**
     *
     * @return
     * The geocodeType
     */
    public String getGeocodeType() {
        return geocodeType;
    }

    /**
     *
     * @param geocodeType
     * The geocode_type
     */
    public void setGeocodeType(String geocodeType) {
        this.geocodeType = geocodeType;
    }

    /**
     *
     * @return
     * The tzOlsonPath
     */
    public String getTzOlsonPath() {
        return tzOlsonPath;
    }

    /**
     *
     * @param tzOlsonPath
     * The tz_olson_path
     */
    public void setTzOlsonPath(String tzOlsonPath) {
        this.tzOlsonPath = tzOlsonPath;
    }

    /**
     *
     * @return
     * The city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     * The trackbacks
     */
    public Object getTrackbacks() {
        return trackbacks;
    }

    /**
     *
     * @param trackbacks
     * The trackbacks
     */
    public void setTrackbacks(Object trackbacks) {
        this.trackbacks = trackbacks;
    }

    /**
     *
     * @return
     * The country
     */
    public String getCountry() {

        return (country != null) ? country : country_;
    }

    /**
     *
     * @param country
     * The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     * The owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     *
     * @param owner
     * The owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     *
     * @return
     * The countryAbbr2
     */
    public String getCountryAbbr2() {
        return countryAbbr2;
    }

    /**
     *
     * @param countryAbbr2
     * The country_abbr2
     */
    public void setCountryAbbr2(String countryAbbr2) {
        this.countryAbbr2 = countryAbbr2;
    }

    /**
     *
     * @return
     * The tags
     */


    /**
     *
     * @return
     * The venueType
     */
    public String getVenueType() {
        return venueType;
    }

    /**
     *
     * @param venueType
     * The venue_type
     */
    public void setVenueType(String venueType) {
        this.venueType = venueType;
    }

    /**
     *
     * @return
     * The created
     */
    public String getCreated() {
        return created;
    }

    /**
     *
     * @param created
     * The created
     */
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     *
     * @return
     * The events
     */
    public Events getEvents() {
        return events;
    }

    /**
     *
     * @param events
     * The events
     */
    public void setEvents(Events events) {
        this.events = events;
    }

    @Override
    public String getDesc() {
        return description;
    }

    @Override
        public String getImageUrl(boolean large) {
        if (large && images != null) {
            List<Image> imageList = ((Images) images).getImage();
            if (imageList != null && !imageList.isEmpty())
                return imageList.get(0).getLarge().getUrl();
        }
        return "";
        }

}