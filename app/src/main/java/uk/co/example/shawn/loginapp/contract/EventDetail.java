package uk.co.example.shawn.loginapp.contract;

import android.util.Log;

import java.util.Arrays;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import uk.co.example.shawn.loginapp.DetailedEntryFragment;
import uk.co.example.shawn.loginapp.model.event.Event;
import uk.co.example.shawn.loginapp.model.event.EventDetails;

import static uk.co.example.shawn.loginapp.connect.Constants.API_KEY;

/**
 * Created by Shawn Li on 04/11/2016.
 */

public class EventDetail extends IContract.IDetailPresenter implements Observer<EventDetails> {
    @Override
    public void get() {
        api.getEvent(API_KEY, id, "large").subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        trackException(e);
    }

    @Override
    public void onNext(EventDetails eventDetails) {
        Log.d("GET", eventDetails.getName());
        fragment.passDetails(eventDetails);
    }

    public EventDetail(DetailedEntryFragment fragment) {
        super(fragment);
    }
}
