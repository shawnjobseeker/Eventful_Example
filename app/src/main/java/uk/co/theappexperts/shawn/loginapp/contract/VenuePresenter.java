package uk.co.theappexperts.shawn.loginapp.contract;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import uk.co.theappexperts.shawn.loginapp.LoginActivity;
import uk.co.theappexperts.shawn.loginapp.PresenterParams;
import uk.co.theappexperts.shawn.loginapp.model.venue.Search;
import uk.co.theappexperts.shawn.loginapp.model.venue.Venue;

import static uk.co.theappexperts.shawn.loginapp.connect.Constants.API_KEY;

/**
 * Created by TheAppExperts on 27/10/2016.
 */

public class VenuePresenter extends IContract.IPresenter implements  Observer<Search>{


    // query fields
    private String location;
    private int within;
    private String units;
    @Override
    public void onNext(Search venue) {
        if (venue.getVenues() == null)
            ((LoginActivity)context).passDataAdapter(null, 0, 0);
        else {
            List<Venue> performers = venue.getVenues().getVenue();
            ((LoginActivity) context).passDataAdapter(performers, Integer.parseInt(venue.getPageCount()), Integer.parseInt(venue.getPageNumber()));
        }
    }

    @Override
    public void onError(Throwable e) {
        trackException(e);
    }

    @Override
    public void onCompleted() {
    }
    public void query() {
        api.queryVenue(API_KEY, query, location, within, units, sortOrder, sortDirection, pageSize, pageNumber).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(this);
    }
    public VenuePresenter(Context context) {
        this.context = context;
        this.sortOrder = "relevance";
        this.sortDirection = "ascending";
        this.pageSize = 20;
        this.pageNumber = 1;
        this.within = 10;
        this.units = "mi";
    }

    @Override
    public void setValues(Cursor cursor) {
        super.setValues(cursor);
        this.location = cursor.getString(cursor.getColumnIndex(PresenterParams.Columns.LOCATION));
        this.within = cursor.getInt(cursor.getColumnIndex(PresenterParams.Columns.WITHIN));
        this.units  = cursor.getString(cursor.getColumnIndex(PresenterParams.Columns.UNITS));
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void setValues(ContentValues values) {
        super.setValues(values);
        this.location = values.getAsString(PresenterParams.Columns.LOCATION);
        Integer within = values.getAsInteger(PresenterParams.Columns.WITHIN);
        this.within = (within == null) ? 0 : within;
        this.units = values.getAsString(PresenterParams.Columns.UNITS);
    }
}
