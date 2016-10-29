package uk.co.theappexperts.shawn.loginapp.model.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import uk.co.theappexperts.shawn.loginapp.model.event.State;

/**
 * Created by TheAppExperts on 26/10/2016.
 */
public class City {

    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("country")
    @Expose
    private Country country;
    @SerializedName("lng")
    @Expose
    private Object lng;
    @SerializedName("lat")
    @Expose
    private Object lat;
    @SerializedName("state")
    @Expose
    private State state;

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
     * The country
     */
    public Country getCountry() {
        return country;
    }

    /**
     *
     * @param country
     * The country
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     *
     * @return
     * The lng
     */
    public Object getLng() {
        return lng;
    }

    /**
     *
     * @param lng
     * The lng
     */
    public void setLng(Object lng) {
        this.lng = lng;
    }

    /**
     *
     * @return
     * The lat
     */
    public Object getLat() {
        return lat;
    }

    /**
     *
     * @param lat
     * The lat
     */
    public void setLat(Object lat) {
        this.lat = lat;
    }

    /**
     *
     * @return
     * The state
     */
    public State getState() {
        return state;
    }

    /**
     *
     * @param state
     * The state
     */
    public void setState(State state) {
        this.state = state;
    }

}
