package uk.co.theappexperts.shawn.loginapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheAppExperts on 30/10/2016.
 */
public class Links {

    @SerializedName("link")
    @Expose
    private List<Link> link = new ArrayList<Link>();

    /**
     *
     * @return
     * The link
     */
    public List<Link> getLink() {
        return link;
    }

    /**
     *
     * @param link
     * The link
     */
    public void setLink(List<Link> link) {
        this.link = link;
    }

}
