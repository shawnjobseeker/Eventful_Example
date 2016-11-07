package uk.co.theappexperts.shawn.loginapp.model.event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheAppExperts on 30/10/2016.
 */
public class Categories {

    @SerializedName("category")
    @Expose
    private List<Category> category = new ArrayList<Category>();

    /**
     *
     * @return
     * The category
     */
    public List<Category> getCategory() {
        return category;
    }

    /**
     *
     * @param category
     * The category
     */
    public void setCategory(List<Category> category) {
        this.category = category;
    }

}
