package uk.co.theappexperts.shawn.loginapp.model.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import uk.co.theappexperts.shawn.loginapp.model.IData;

/**
 * Created by TheAppExperts on 26/10/2016.
 */
public class Location implements IData {

    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("metroArea")
    @Expose
    private MetroArea metroArea;

    /**
     *
     * @return
     * The city
     */
    public City getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     *
     * @return
     * The metroArea
     */
    public MetroArea getMetroArea() {
        return metroArea;
    }

    /**
     *
     * @param metroArea
     * The metroArea
     */
    public void setMetroArea(MetroArea metroArea) {
        this.metroArea = metroArea;
    }

}
