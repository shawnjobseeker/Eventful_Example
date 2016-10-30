package uk.co.theappexperts.shawn.loginapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheAppExperts on 30/10/2016.
 */
public class Comments {

    @SerializedName("comment")
    @Expose
    private List<Comment> comment = new ArrayList<Comment>();

    /**
     *
     * @return
     * The comment
     */
    public List<Comment> getComment() {
        return comment;
    }

    /**
     *
     * @param comment
     * The comment
     */
    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

}
