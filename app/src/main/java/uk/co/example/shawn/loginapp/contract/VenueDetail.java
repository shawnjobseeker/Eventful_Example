package uk.co.example.shawn.loginapp.contract;

import android.util.Log;

import java.util.Arrays;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import uk.co.example.shawn.loginapp.DetailedEntryFragment;
import uk.co.example.shawn.loginapp.model.venue.Venue;

import static uk.co.example.shawn.loginapp.connect.Constants.API_KEY;

/**
 * Created by Shawn Li on 04/11/2016.
 */

public class VenueDetail extends IContract.IDetailPresenter implements Observer<Venue> {
    @Override
    public void onNext(Venue venue) {
        fragment.passDetails(venue);
    }

    @Override
    public void onError(Throwable e) {
        trackException(e);
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void get() {
        api.getVenue(API_KEY, id, "large").subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    public VenueDetail(DetailedEntryFragment fragment) {
        super(fragment);
    }
}
