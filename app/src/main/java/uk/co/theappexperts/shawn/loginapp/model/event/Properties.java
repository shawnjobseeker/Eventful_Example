package uk.co.theappexperts.shawn.loginapp.model.event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheAppExperts on 30/10/2016.
 */
public class Properties {

    @SerializedName("property")
    @Expose
    private List<Property> property = new ArrayList<Property>();

    /**
     *
     * @return
     * The property
     */
    public List<Property> getProperty() {
        return property;
    }

    /**
     *
     * @param property
     * The property
     */
    public void setProperty(List<Property> property) {
        this.property = property;
    }

}
