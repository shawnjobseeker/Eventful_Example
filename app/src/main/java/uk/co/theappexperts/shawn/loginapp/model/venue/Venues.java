package uk.co.theappexperts.shawn.loginapp.model.venue;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Venues {

    @SerializedName("venue")
    @Expose
    private List<Venue> venue = new ArrayList<Venue>();

    /**
     * @return The venue
     */
    public List<Venue> getVenue() {
        return venue;
    }

    /**
     * @param venue The venue
     */
    public void setVenue(List<Venue> venue) {
        this.venue = venue;
    }
}