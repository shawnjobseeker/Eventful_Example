package uk.co.example.shawn.loginapp.model;

/**
 * Created by Shawn Li on 26/10/2016.
 */

public interface IData {

     String getId();
     String getName();
     String getDesc();
     String getUrl();
     String getImageUrl(boolean large);
     Links getLinks();
}
