package uk.co.theappexperts.shawn.loginapp.contract;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import uk.co.theappexperts.shawn.loginapp.LoginActivity;
import uk.co.theappexperts.shawn.loginapp.model.artist.Performer;
import uk.co.theappexperts.shawn.loginapp.model.artist.Performers;
import uk.co.theappexperts.shawn.loginapp.model.artist.Search;

import static uk.co.theappexperts.shawn.loginapp.connect.Constants.API_KEY;

/**
 * Created by TheAppExperts on 27/10/2016.
 */

public class ArtistPresenter extends IContract.IPresenter implements   Observer<Search> {



    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        trackException(e);
    }

    @Override
    public void onNext(Search artists) {
        Performers performers = artists.getPerformers();
        if (performers != null)
            ((LoginActivity) context).passDataAdapter(performers.getPerformer(), Integer.parseInt(artists.getPageCount()), Integer.parseInt(artists.getPageNumber()));
        else
            ((LoginActivity)context).passDataAdapter(null, 0, 0);
    }
    public void query() {
        api.queryArtist(API_KEY, query, sortOrder, sortDirection, pageSize, pageNumber).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    public ArtistPresenter(Context context) {
        this.context = context;
        this.sortOrder = "name";
        this.sortDirection = "ascending";
        this.pageSize = 20;
        this.pageNumber = 1;
    }

}
