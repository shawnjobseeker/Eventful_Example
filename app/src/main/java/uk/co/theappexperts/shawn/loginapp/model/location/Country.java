package uk.co.theappexperts.shawn.loginapp.model.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TheAppExperts on 26/10/2016.
 */
public class Country {

    @SerializedName("displayName")
    @Expose
    private String displayName;

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

}
