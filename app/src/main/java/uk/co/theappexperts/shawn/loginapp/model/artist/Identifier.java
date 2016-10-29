package uk.co.theappexperts.shawn.loginapp.model.artist;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Identifier {

    @SerializedName("eventsHref")
    @Expose
    private String eventsHref;
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("mbid")
    @Expose
    private String mbid;
    @SerializedName("setlistsHref")
    @Expose
    private String setlistsHref;

    /**
     *
     * @return
     * The eventsHref
     */
    public String getEventsHref() {
        return eventsHref;
    }

    /**
     *
     * @param eventsHref
     * The eventsHref
     */
    public void setEventsHref(String eventsHref) {
        this.eventsHref = eventsHref;
    }

    /**
     *
     * @return
     * The href
     */
    public String getHref() {
        return href;
    }

    /**
     *
     * @param href
     * The href
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     *
     * @return
     * The mbid
     */
    public String getMbid() {
        return mbid;
    }

    /**
     *
     * @param mbid
     * The mbid
     */
    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    /**
     *
     * @return
     * The setlistsHref
     */
    public String getSetlistsHref() {
        return setlistsHref;
    }

    /**
     *
     * @param setlistsHref
     * The setlistsHref
     */
    public void setSetlistsHref(String setlistsHref) {
        this.setlistsHref = setlistsHref;
    }

}