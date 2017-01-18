package uk.co.example.shawn.loginapp.contract;

import android.util.Log;

import java.util.Arrays;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import uk.co.example.shawn.loginapp.DetailedEntryFragment;
import uk.co.example.shawn.loginapp.model.artist.Performer;

import static uk.co.example.shawn.loginapp.connect.Constants.API_KEY;

/**
 * Created by Shawn Li on 04/11/2016.
 */

public class ArtistDetail extends IContract.IDetailPresenter implements Observer<Performer> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        trackException(e);
    }

    @Override
    public void onNext(Performer performer) {
        fragment.passDetails(performer);
    }

    public void get() {
        api.getPerformer(API_KEY, id, true, "large").subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    public ArtistDetail(DetailedEntryFragment fragment) {
        super(fragment);
    }
}
