package uk.co.theappexperts.shawn.loginapp.connect;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import uk.co.theappexperts.shawn.loginapp.model.artist.Performer;
import uk.co.theappexperts.shawn.loginapp.model.event.Event;
import uk.co.theappexperts.shawn.loginapp.model.event.EventDetails;
import uk.co.theappexperts.shawn.loginapp.model.event.Search;
import uk.co.theappexperts.shawn.loginapp.model.venue.Venue;

/**
 * Created by TheAppExperts on 27/10/2016.
 */
// Gugeul Tracking ID UA-86416333-1
public interface ApiInterface {

    @GET("json/events/search")
    Observable<Search> queryEvent(@Query("app_key") String appKey, @Query("keywords") String query, @Query("location") String location,
                                  @Query("date") String date, @Query("category") String include, @Query("ex_category") String exclude,
                                  @Query("within") Integer within, @Query("units") String units,
                                  @Query("sort_order") String sortOrder, @Query("sort_direction") String sortDirection, @Query("page_size") int pageSize,
                                  @Query("page_number") int pageNumber);
    @GET("json/venues/search")
    Observable<uk.co.theappexperts.shawn.loginapp.model.venue.Search> queryVenue(@Query("app_key") String appKey, @Query("keywords") String query,
                                                                                 @Query("location") String location, @Query("within") Integer within,
                                                                                 @Query("units") String units,
                                                                                 @Query("sort_order") String sortOrder, @Query("sort_direction") String sortDirection,
                                                                                 @Query("page_size") int pageSize, @Query("page_number") int pageNumber);

    @GET("json/performers/search")
    Observable<uk.co.theappexperts.shawn.loginapp.model.artist.Search> queryArtist(@Query("app_key") String appKey, @Query("keywords") String query,
                                                                                    @Query("sort_order") String sortOrder,
                                                                                   @Query("sort_direction") String sortDirection,
                                                                                   @Query("page_size") int pageSize, @Query("page_number") int pageNumber);

    @GET("json/performers/get")
    Observable<Performer> getPerformer(@Query("app_key") String appKey, @Query("id") String performerID, @Query("show_events") boolean showEvents,
                                       @Query("image_sizes") String imageSizes);

    @GET("json/events/get")
    Observable<EventDetails> getEvent(@Query("app_key") String appKey, @Query("id") String eventID,
                                      @Query("image_sizes") String imageSizes);

    @GET("json/venues/get")
    Observable<Venue> getVenue(@Query("app_key") String appKey, @Query("id") String venueID,
                               @Query("mature") String mature);

    /*@GET("events.json") //event search: must refine by either artist name or location, can set date bounds
    Observable<Search> queryEvent(@Query("apikey") String apiKey, @Nullable @Query("artist_name") String artistName, @Nullable @Query("location") String location, @Nullable @Query("min_date") String minDate, @Nullable @Query("max_date") String maxDate);

    @GET("search/artists.json")
    Observable<uk.co.theappexperts.shawn.loginapp.model.artist.Search> queryArtist(@Query("apikey") String apiKey, @Query("query") String query);

    @GET("search/venues.json")
    Observable<uk.co.theappexperts.shawn.loginapp.model.venue.Search> queryVenue(@Query("apikey") String apiKey, @Query("query") String query);

    @GET("search/locations.json")
    Observable<uk.co.theappexperts.shawn.loginapp.model.location.Search> queryLocationByText(@Query("apikey") String apiKey, @Query("query") String query);

    @GET("search/locations.json")
    Observable<uk.co.theappexperts.shawn.loginapp.model.location.Search> queryLocationByGeo(@Query("apikey") String apiKey, @Query("location") String latLngStr);

    @GET("artists/{artist_id}/calendar.json")
    Observable<Search> getFutureEventsByArtist(@Query("apikey") String apiKey, @Path("artist_id") int artistId, @Nullable @Query("min_date") String minDate, @Nullable @Query("max_date") String maxDate);

    @GET("artists/{artist_id}/gigography.json")
    Observable<Search> getPastEventsByArtist(@Query("apikey") String apiKey, @Path("artist_id") int artistId, @Nullable @Query("min_date") String minDate, @Nullable @Query("max_date") String maxDate);

    @GET("venues/{venue_id}/calendar.json")
    Observable<Search> getEventsByVenue(@Query("apikey") String apiKey, @Path("venue_id") int venueId);

    @GET("metro_areas/{metro_area_id}/calendar.json")
    Observable<Search> getEventsByMetroArea(@Query("apikey") String apiKey, @Path("metro_area_id") int metroId);

    @GET("users/{username}/events.json")
    Observable<Search> getFutureUserEvents(@Query("apikey") String apiKey, @Path("username") String username);

    @GET("users/{username}/gigography.json")
    Observable<Search> getPastUserEvents(@Query("apikey") String apiKey, @Path("username") String username);

    @GET("events/{event_id}.json")
    Observable<Search> getEventDetails(@Query("apikey") String apiKey, @Path("event_id") int eventId);

    @GET("venues/{venue_id}.json")
    Observable<uk.co.theappexperts.shawn.loginapp.model.venue.Search> getVenueDetails(@Query("apikey") String apiKey, @Path("venue_id") int venueId);

    @GET("artists/{artist_id}/similar_artists.json")
    Observable<uk.co.theappexperts.shawn.loginapp.model.artist.Search> similarArtists(@Query("apikey") String apiKey, @Path("artist_id") int artistId);*/
}
