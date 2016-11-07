package uk.co.theappexperts.shawn.loginapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.theappexperts.shawn.loginapp.contract.ArtistDetail;
import uk.co.theappexperts.shawn.loginapp.contract.EventDetail;
import uk.co.theappexperts.shawn.loginapp.contract.EventPresenter;
import uk.co.theappexperts.shawn.loginapp.contract.IContract;
import uk.co.theappexperts.shawn.loginapp.contract.VenueDetail;
import uk.co.theappexperts.shawn.loginapp.model.IData;
import uk.co.theappexperts.shawn.loginapp.model.Links;
import uk.co.theappexperts.shawn.loginapp.model.artist.Performer;
import uk.co.theappexperts.shawn.loginapp.model.event.Event;
import uk.co.theappexperts.shawn.loginapp.model.event.EventDetails;
import uk.co.theappexperts.shawn.loginapp.model.event.Events;
import uk.co.theappexperts.shawn.loginapp.model.venue.Venue;

/**
 * Created by TheAppExperts on 04/11/2016.
 */

public class DetailedEntryFragment extends Fragment implements OnMapReadyCallback {
// fragment to be displayed to one side in tablets, and over existing fragments in phones
    IContract.IDetailPresenter presenter;
    @BindView(R.id.close_button)
    ImageButton closeButton;
    @BindView(R.id.detail_title)
    TextView titleText;
    @BindView(R.id.full_image)
    ImageView image;
    @BindView(R.id.detail_description)
    TextView description;
    @BindView(R.id.will_be_held_at)
    TextView willBeHeldAt;
    @BindView(R.id.upcoming_events)
    RecyclerView eventView;
    @BindView(R.id.map_view)
    MapView mapView;
    @BindView(R.id.url_button)
    Button urlButton;
    @BindView(R.id.im_going)
    Button imGoing;
    @BindView(R.id.links)
    RecyclerView linkView;

    private Unbinder unbinder;
    private String iDataClass;
    private String id;
    private String url;
    private double latitude;
    private double longitude;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.iDataClass = savedInstanceState.getString("IDataClass");
            this.id = savedInstanceState.getString("id");
        }
        else {
            this.iDataClass = getArguments().getString("IDataClass");
            this.id = getArguments().getString("id");
        }
        if (iDataClass.equals(Performer.class.getName()))
            presenter = new ArtistDetail(this);
        else if (iDataClass.equals(Event.class.getName()))
            presenter = new EventDetail(this);
        else if (iDataClass.equals(Venue.class.getName()))
            presenter = new VenueDetail(this);
        presenter.setId(this.id);
        presenter.get();
        View v = inflater.inflate(R.layout.detail_view, null, false);
        unbinder = ButterKnife.bind(this, v);
        final Bundle mapViewSavedInstanceState = savedInstanceState != null ? savedInstanceState.getBundle("mapViewSaveState") : null;
        mapView.onCreate(mapViewSavedInstanceState);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        if (((float)displaymetrics.widthPixels / displaymetrics.density) < 620.0f)
            closeButton.setVisibility(View.VISIBLE);
        urlButton.setVisibility(View.VISIBLE);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFragment();
            }
        });
        urlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tracker tracker = ((LoginActivity)getActivity()).tracker;
                tracker.send(new HitBuilders.EventBuilder().setAction(Intent.ACTION_VIEW).build());
                Uri page = Uri.parse(DetailedEntryFragment.this.url);
                Intent intent = new Intent(Intent.ACTION_VIEW, page);
                getActivity().startActivity(intent);
            }
        });
        return v;
    }
    public void passDetails(IData data) {
        this.url = data.getUrl();
        titleText.setText(Html.fromHtml(nullIsEmpty(data.getName())));
        description.setText(Html.fromHtml(nullIsEmpty(data.getDesc())));
        if (data.getLinks() != null)
        setUpLinks(data.getLinks());
        if (data instanceof Performer)
            displayArtist((Performer)data);
        else if (data instanceof EventDetails)
            displayEvent((EventDetails)data);
        else if (data instanceof Venue)
            displayVenue((Venue)data);
        if (data.getImageUrl(true).length() > 0)
        Picasso.with(getContext()).load(data.getImageUrl(true)).into(image);
    }
    private void setUpLinks(Links links) {
        linkView.setLayoutManager(new LinearLayoutManager(getContext()));
        linkView.setAdapter(new LinkAdapter(links.getLink(), getContext()));
    }

    private void displayArtist(Performer performer) {

        if (performer.getLongBio() != null && description.getText().length() < 100)
            description.setText(Html.fromHtml(nullIsEmpty(performer.getLongBio().toString())));
        eventView.setLayoutManager(new LinearLayoutManager(getContext()));
        Events events = (Events)performer.getEvents();
        if (events != null)
        eventView.setAdapter(new Adapter(events.getEvent(), R.layout.row, getContext()));
    }
    private void displayEvent(EventDetails event) {
        mapView.setVisibility(View.VISIBLE);
        String address = nullIsEmpty(event.getAddress()) + "\n" + nullIsEmpty(event.getCity()) + "\n" + nullIsEmpty(event.getRegion()) + "\n" + nullIsEmpty(event.getCountry()) + "\n" + nullIsEmpty(event.getPostalCode());
        willBeHeldAt.setText(EventPresenter.getDateString(event.getStartTime(), event.getStopTime()) + "\n" + address + "\n" + nullIsEmpty(event.getPrice()));
        this.latitude = Double.parseDouble(event.getLatitude());
        this.longitude = Double.parseDouble(event.getLongitude());
        this.imGoing.setVisibility(View.VISIBLE);
        mapView.getMapAsync(this);
    }
    private void displayVenue(Venue venue) {
        mapView.setVisibility(View.VISIBLE);
        String address = nullIsEmpty(venue.getAddress()) + "\n" + nullIsEmpty(venue.getCity()) + "\n" + nullIsEmpty(venue.getRegion()) + "\n" + nullIsEmpty(venue.getCountry()) + "\n" + nullIsEmpty(venue.getPostalCode());
        willBeHeldAt.setText(address);
        this.latitude = Double.parseDouble(venue.getLatitude());
        this.longitude = Double.parseDouble(venue.getLongitude());
        mapView.getMapAsync(this);
    }
    private String nullIsEmpty(String str) {
        return (str == null) ? "" : str;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    private void closeFragment() {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.remove(this);
        transaction.commit();
    }

    // MapView instance state solution https://code.google.com/p/gmaps-api-issues/issues/detail?id=6237#c9
    @Override
    public void onSaveInstanceState(Bundle outState) {
        final Bundle mapViewSaveState = new Bundle(outState);
        mapView.onSaveInstanceState(mapViewSaveState);
        outState.putBundle("mapViewSaveState", mapViewSaveState);
        outState.putString("IDataClass", iDataClass);
        outState.putString("id", id);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng mark = new LatLng(this.latitude, this.longitude);
        googleMap.addMarker(new MarkerOptions().position(mark));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mark, 11));
    }




    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        Tracker tracker = ((LoginActivity)getActivity()).tracker;
        tracker.setScreenName("Detail");
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mapView != null)
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
