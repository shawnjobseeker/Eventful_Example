package uk.co.theappexperts.shawn.loginapp.model;

/**
 * Created by TheAppExperts on 26/10/2016.
 */

public interface IData {

    public String getId();
    public String getName();
    public String getDesc();
    public String getUrl();
    public String getImageUrl(boolean large);
    public Links getLinks();
}
