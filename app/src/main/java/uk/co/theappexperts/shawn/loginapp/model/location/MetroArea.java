package uk.co.theappexperts.shawn.loginapp.model.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import uk.co.theappexperts.shawn.loginapp.model.location.City;

/**
 * Created by TheAppExperts on 26/10/2016.
 */
public class MetroArea extends City {

    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("id")
    @Expose
    private Integer id;

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


}
