package uk.co.example.shawn.loginapp.model.artist;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Demands {

    @SerializedName("demand")
    @Expose
    private Demand demand;

    /**
     *
     * @return
     * The demand
     */
    public Demand getDemand() {
        return demand;
    }

    /**
     *
     * @param demand
     * The demand
     */
    public void setDemand(Demand demand) {
        this.demand = demand;
    }

}