package uk.co.theappexperts.shawn.loginapp.model.event;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Events {

    @SerializedName("event")
    @Expose
    private List<Event> event = new ArrayList<Event>();

    /**
     *
     * @return
     * The event
     */
    public List<Event> getEvent() {
        return event;
    }

    /**
     *
     * @param event
     * The event
     */
    public void setEvent(List<Event> event) {
        this.event = event;
    }

}