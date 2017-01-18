package uk.co.example.shawn.loginapp.model.image;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Images {

    @SerializedName("image")
    @Expose
    // custom serialization
    private List<Image> image = new ArrayList<Image>();
    /**
     *
     * @return
     * The image
     */
    public List<Image> getImage() {
        return image;
    }

    /**
     *
     * @param image
     * The image
     */
    public void setImage(List<Image> image) {
        this.image = image;
    }


}