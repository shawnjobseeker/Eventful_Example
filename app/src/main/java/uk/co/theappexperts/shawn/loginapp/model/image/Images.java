package uk.co.theappexperts.shawn.loginapp.model.image;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("image")
    @Expose
    private Image image;

    /**
     *
     * @return
     * The image
     */
    public Image getImage() {
        return image;
    }

    /**
     *
     * @param image
     * The image
     */
    public void setImage(Image image) {
        this.image = image;
    }

}