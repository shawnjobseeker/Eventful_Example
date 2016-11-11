package uk.co.theappexperts.shawn.loginapp.contract;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import uk.co.theappexperts.shawn.loginapp.LoginActivity;
import uk.co.theappexperts.shawn.loginapp.PresenterParams.Columns;
import uk.co.theappexperts.shawn.loginapp.model.event.Event;
import uk.co.theappexperts.shawn.loginapp.model.event.Events;
import uk.co.theappexperts.shawn.loginapp.model.event.Search;

import static uk.co.theappexperts.shawn.loginapp.connect.Constants.API_KEY;

/**
 * Created by TheAppExperts on 27/10/2016.
 */

public class EventPresenter extends IContract.IPresenter implements Observer<Search> {


    private static SimpleDateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat toDateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");
    private static SimpleDateFormat toTimeFormat = new SimpleDateFormat("HH:mm");
    // query fields
    private String location;
    private int within;
    private String units;
    private String date;
    private String maturity;
    private String includeCategories;
    private String excludeCategories;
    @Override
    public void onNext(Search search) {
        Events events = search.getEvents();
        if (events != null)
        ((LoginActivity)context).passDataAdapter(events.getEvent(), Integer.parseInt(search.getPageCount()), Integer.parseInt(search.getPageNumber()));
        else
            ((LoginActivity)context).passDataAdapter(null, 0, 0);
    }

    @Override
    public void onError(Throwable e) {
        trackException(e);
    }

    @Override
    public void onCompleted() {

    }
    public void query() {
        api.queryEvent(API_KEY, query, location, date, includeCategories, excludeCategories, within, units, sortOrder, sortDirection, pageSize, pageNumber).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }
    public EventPresenter(Context context) {
        this.context = context;
        this.sortOrder = "relevance";
        this.sortDirection = "descending";
        this.within = 10;
        this.units = "mi";
        this.pageSize = 20;
        this.pageNumber = 1;
    }

    public void setLocation(String location) {
        if (location != null && location.length() == 0)
            this.location = null;
        else
        this.location = location;
    }
    public void setMaturity(String maturity) {
        this.maturity = maturity;
    }


    public static String getDateString(String startTime, Object stopTime) {
        try {
            Date start = fromFormat.parse(startTime);
            String parsedStart = toDateFormat.format(start);
            if (stopTime != null) {
                Date stop = fromFormat.parse(stopTime.toString());
                String parsedStop = toDateFormat.format(stop);
                if (parsedStart.equals(parsedStop))
                    return toTimeFormat.format(start) + " - " + toTimeFormat.format(stop) + " " + parsedStart;
                else
                    return toTimeFormat.format(start) + " " + parsedStart + " - " + toTimeFormat.format(stop) + " " + parsedStop;
            }
            else
                return toTimeFormat.format(start) + " " + parsedStart;

        }
        catch (ParseException e) {
            return e.getMessage();
        }
    }


    @Override
    public void setValues(Cursor cursor) {
        super.setValues(cursor);
        this.location = cursor.getString(cursor.getColumnIndex(Columns.LOCATION));
        this.within = cursor.getInt(cursor.getColumnIndex(Columns.WITHIN));
        this.units  = cursor.getString(cursor.getColumnIndex(Columns.UNITS));
        this.includeCategories = cursor.getString(cursor.getColumnIndex(Columns.INCLUDE_CATEGORIES));
        this.excludeCategories = cursor.getString(cursor.getColumnIndex(Columns.EXCLUDE_CATEGORIES));
        this.date = cursor.getString(cursor.getColumnIndex(Columns.DATE));

    }

    @Override
    public void setValues(ContentValues values) {
        super.setValues(values);
        this.location = values.getAsString(Columns.LOCATION);
        Integer within = values.getAsInteger(Columns.WITHIN);
            this.within = (within == null) ? 0 : within;
        this.units = values.getAsString(Columns.UNITS);
        this.includeCategories = values.getAsString(Columns.INCLUDE_CATEGORIES);
        this.excludeCategories = values.getAsString(Columns.EXCLUDE_CATEGORIES);
        this.date = values.getAsString(Columns.DATE);
    }

}
