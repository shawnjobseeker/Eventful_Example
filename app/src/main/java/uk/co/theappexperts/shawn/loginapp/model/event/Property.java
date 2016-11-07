package uk.co.theappexperts.shawn.loginapp.model.event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TheAppExperts on 30/10/2016.
 */
public class Property {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private String id;

    /**
     *
     * @return
     * The value
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @param value
     * The value
     */
    public void setValue(String value) {
        this.value = value;
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

}
