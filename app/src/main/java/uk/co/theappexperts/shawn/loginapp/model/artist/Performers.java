package uk.co.theappexperts.shawn.loginapp.model.artist;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Performers {

    @SerializedName("performer")
    @Expose
    private List<Performer> performer = new ArrayList<Performer>();

    /**
     *
     * @return
     * The performer
     */
    public List<Performer> getPerformer() {
        return performer;
    }

    /**
     *
     * @param performer
     * The performer
     */
    public void setPerformer(List<Performer> performer) {
        this.performer = performer;
    }

}