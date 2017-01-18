package uk.co.example.shawn.loginapp;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.google.android.gms.analytics.ExceptionReporter;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.laimiux.rxnetwork.RxNetwork;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.example.shawn.loginapp.contract.ArtistPresenter;
import uk.co.example.shawn.loginapp.contract.EventPresenter;
import uk.co.example.shawn.loginapp.contract.IContract;
import uk.co.example.shawn.loginapp.contract.VenuePresenter;
import uk.co.example.shawn.loginapp.model.IData;
import uk.co.example.shawn.loginapp.model.event.Event;
import uk.co.example.shawn.loginapp.model.venue.Venue;
import static uk.co.example.shawn.loginapp.PresenterParams.Columns.IDATA_CLASS;
import static uk.co.example.shawn.loginapp.PresenterParams.TABLE_NAME;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, OnMapReadyCallback {


    // UI references.
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_choice)
    Spinner spinner;
    @BindView(R.id.search_edit)
    EditText editText;
    @BindView(R.id.search_image)
    ImageButton button;
    @BindView(R.id.nav_button)
    ImageButton locationButton;
    @BindView(R.id.floating_action_button)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.page_toggle_view)
    LinearLayout pageToggleView;
    @BindView(R.id.search_bar_view)
    RelativeLayout searchBarView;
    @BindView(R.id.detail_view)
    RelativeLayout detailView;
    private NavHeaderHolder holder;
    private Profile currentProfile;
    private GoogleApiClient google;
     Location currentLocation;
    private boolean mapEnabled;
    private Drawable listIcon;
    private GoogleMap map;
    private Bundle savedInstanceState;
    private SupportMapFragment fragment;
    ProgressDialog dialog;
    private List<? extends IData> list;
    private int searchPageNumber;
    boolean locationButtonClicked = false;
    private boolean queryPersisted = false;
    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken token = loginResult.getAccessToken();
             LoginActivity.this.currentProfile = Profile.getCurrentProfile();
            updateLogin();
            Toast.makeText(getApplicationContext(), getString(R.string.login_successful), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {
            Toast.makeText(getApplicationContext(), getString(R.string.login_failure), Toast.LENGTH_SHORT).show();
        }
    };
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
     IContract.IPresenter presenter;
    PresenterDBHelper helper;
    Tracker tracker;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Analytics application = (Analytics) getApplication();
        tracker = application.getDefaultTracker();
        if (savedInstanceState == null)
            this.savedInstanceState = new Bundle();
        else
            this.savedInstanceState = savedInstanceState;
        // initialize Facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            }
        };
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                LoginActivity.this.currentProfile = currentProfile;
                updateLogin();
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // initialize navigation drawer
         holder = new NavHeaderHolder(navigationView.getHeaderView(0));
        holder.login.setReadPermissions("user_friends");
        holder.login.registerCallback(callbackManager, callback);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);

        toolbar.setLogo(R.drawable.eventful_logo);
        toggle.syncState();
        toolbar.setNavigationIcon((resize(ContextCompat.getDrawable(this, R.drawable.menu), 48, 48)));
        navigationView.setNavigationItemSelectedListener(this);
        // initialize search view
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        if (((float)displaymetrics.widthPixels / displaymetrics.density) < 620.0f)
            detailView.setVisibility(View.GONE);
        button.setImageDrawable(resize(ContextCompat.getDrawable(this, android.R.drawable.ic_menu_search), 25, 25));
        locationButton.setImageDrawable(resize(ContextCompat.getDrawable(this, android.R.drawable.ic_menu_mylocation), 25, 25));
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.search_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals(getString(R.string.artist))) {
                    locationButton.setVisibility(View.INVISIBLE);
                    floatingActionButton.setVisibility(View.INVISIBLE);
                } else {

                    locationButton.setVisibility(View.VISIBLE);
                    floatingActionButton.setVisibility(View.VISIBLE);
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                locationButton.setVisibility(View.INVISIBLE);
            }
        });
        button.setOnClickListener(new SearchItemClick());
        locationButton.setOnClickListener(new LocationClick());
        listIcon = ContextCompat.getDrawable(this, R.drawable.list);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapEnabled = !mapEnabled;
                LoginActivity.this.savedInstanceState.putBoolean("mapEnabled", mapEnabled);
                LoginActivity.this.onResume();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dialog = new ProgressDialog(this);
        helper = new PresenterDBHelper(this);
        Thread.UncaughtExceptionHandler myHandler = new ExceptionReporter(
                tracker,
                Thread.getDefaultUncaughtExceptionHandler(),
                this);
        Thread.setDefaultUncaughtExceptionHandler(myHandler);
    }
    public <E extends IData> void passDataAdapter(List<E> list, int pageCount, int pageNumber) {

        this.list = list;
        this.searchPageNumber = pageNumber;
        if (list != null) {
            recyclerView.setAdapter(new Adapter<E>(list, R.layout.row, this));
            pageToggleView.setVisibility(View.VISIBLE);
            ((PageToggleView)pageToggleView).setPresenter(presenter);
            ((PageToggleView)pageToggleView).setPage(pageCount, pageNumber);
        }

        if (dialog.isShowing())
        dialog.dismiss();
        if (mapEnabled)
            onMapReady(map);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Facebook login
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
        helper.close();
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        super.onActivityResult(requestCode, responseCode, intent);
        //Facebook login
        callbackManager.onActivityResult(requestCode, responseCode, intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Facebook login
        if (Profile.getCurrentProfile() != null)
        currentProfile = Profile.getCurrentProfile();
        updateLogin();
        if (getSupportFragmentManager().findFragmentByTag("Detail") == null) {
            if (getSupportFragmentManager().findFragmentByTag("Search") == null)
                floatingActionButton.bringToFront();
        }
        else
            detailView.setVisibility(View.VISIBLE);
        if (fragment == null)
         fragment = new SupportMapFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (mapEnabled) {
            floatingActionButton.setImageDrawable(listIcon);
            transaction.replace(R.id.content_main, fragment, "Map");
            transaction.commit();
            fragment.getMapAsync(LoginActivity.this);
        }
        else {
            transaction.remove(fragment);
            transaction.commit();
            floatingActionButton.setImageDrawable(ContextCompat.getDrawable(LoginActivity.this, android.R.drawable.ic_dialog_map));
        }
        if (editText.getText().length() > 0)
            new SearchItemClick().onClick(null); // redo API call when activity re-created (quick search)
        // otherwise, get most recent advanced search state from DB
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {

            if (cursor.getCount() == 1) {
                cursor.moveToFirst();
                String dataClass = cursor.getString(cursor.getColumnIndex(IDATA_CLASS));
                if (dataClass != null) {
                    if (dataClass.equals(getResources().getResourceEntryName(R.id.artist))) {
                        presenter = new ArtistPresenter(this);
                        spinner.setSelection(0);
                    }
                    else if (dataClass.equals(getResources().getResourceEntryName(R.id.event))) {
                        presenter = new EventPresenter(this);
                        spinner.setSelection(1);
                    }
                    else if (dataClass.equals(getResources().getResourceEntryName(R.id.venue))) {
                        presenter = new VenuePresenter(this);
                        spinner.setSelection(2);
                    }
                    presenter.setValues(cursor);
                    presenter.setPageNumber(searchPageNumber);
                    queryIfConnectionAvailable();
                }
            }
            cursor.close();
        }
        tracker.setScreenName("main");
        tracker.send(new HitBuilders.ScreenViewBuilder().build());

    }

    private void updateLogin() {
        if (currentProfile != null) {
            holder.loadProfilePic(currentProfile);
        }
        else {
            holder.profilePic.setVisibility(View.INVISIBLE);
            holder.loginText.setText(R.string.not_logged_in);
        }

    }
    // for debugging purposes on virtual tablet. Map would not populate with markers if no internet
    void setPlaceholderLocation() {
        currentLocation = new Location("");
        currentLocation.setLatitude(51);
        currentLocation.setLongitude(0);
    }
    @Override
    public void onStart() {
        connectToGoogleApiClient();
        super.onStart();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("mapEnabled", mapEnabled);
        outState.putInt("spinner_selectedItemPosition", spinner.getSelectedItemPosition());
        outState.putInt("page_number", searchPageNumber);
        if (currentLocation != null) {
            outState.putDouble("latitude", currentLocation.getLatitude());
            outState.putDouble("longitude", currentLocation.getLongitude());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            mapEnabled = savedInstanceState.getBoolean("mapEnabled");
            spinner.setSelection(savedInstanceState.getInt("spinner_selectedItemPosition"));
            this.searchPageNumber = savedInstanceState.getInt("page_number");
            this.savedInstanceState = savedInstanceState;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        String title = item.getTitle().toString();
        if (title.equals(getString(R.string.artist)))
            spinner.setSelection(0);
        else if (title.equals(getString(R.string.event)))
            spinner.setSelection(1);
        else if (title.equals(getString(R.string.venue)))
            spinner.setSelection(2);
        drawer.closeDrawer(GravityCompat.START);
        DetailedSearchFragment fragment = new DetailedSearchFragment();
        Fragment existingFragment = getSupportFragmentManager().findFragmentByTag("Search");
        Fragment mapFragment = getSupportFragmentManager().findFragmentByTag("Map");
        Bundle bundle = new Bundle();
        bundle.putString("Search", title);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (existingFragment != null)
        transaction.remove(existingFragment);
        if (mapFragment != null)
        transaction.remove(mapFragment);
        transaction.replace(R.id.search_bar_view, fragment, "Search");
        transaction.commit();
        floatingActionButton.setVisibility(View.GONE);

        return true;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try {
            currentLocation = LocationServices.FusedLocationApi.getLastLocation(google);
            if (currentLocation != null) {
                savedInstanceState.putDouble("latitude", currentLocation.getLatitude());
                savedInstanceState.putDouble("longitude", currentLocation.getLongitude());
            }
            if (editText.getText().length() == 0 && !spinner.getSelectedItem().equals(getString(R.string.artist)) ) {
                SQLiteDatabase db = helper.getReadableDatabase();
                Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
                if (cursor == null || cursor.getCount() == 0)
                    new LocationClick().onClick(null);
            }


        } catch(SecurityException e) {
            onConnectionFailed(new ConnectionResult(ConnectionResult.SERVICE_MISSING_PERMISSION, null, getString(R.string.connection_unauthorised)));
        }

    }
     void queryIfConnectionAvailable() {
        if (RxNetwork.getConnectivityStatus(LoginActivity.this)) {
            presenter.query();
            showProgressDialog();
        }
        else
            onConnectionFailed(new ConnectionResult(ConnectionResult.NETWORK_ERROR, null, getString(R.string.no_connection_available)));
    }
    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Unable to connect: " + connectionResult.getErrorMessage(), Toast.LENGTH_LONG).show();
    }

     class NavHeaderHolder {
        @BindView(R.id.login)
        LoginButton login;
        @BindView(R.id.profile_pic)
        ProfilePictureView profilePic;
         @BindView(R.id.login_text)
         TextView loginText;
        public NavHeaderHolder(View view) {
            ButterKnife.bind(this, view);
            loginText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("loginText", "clicked");
                }
            });
        }
        public void loadProfilePic(Profile profile) {
            loginText.setText(getString(R.string.logged_in_as, profile.getName()));
            profilePic.setPresetSize(ProfilePictureView.NORMAL);
            profilePic.setProfileId(profile.getId());
            profilePic.setVisibility(View.VISIBLE);
        }
    }
    private class SearchItemClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Object item = spinner.getSelectedItem();
            if (item.equals(getString(R.string.artist)))
                presenter = new ArtistPresenter(LoginActivity.this);
            else if (item.equals(getString(R.string.event)))
                presenter = new EventPresenter(LoginActivity.this);
            else if (item.equals(getString(R.string.venue)))
                presenter = new VenuePresenter(LoginActivity.this);
            presenter.setQuery(editText.getText().toString());
            queryIfConnectionAvailable();
            // clear DB for quick search
            SQLiteDatabase db = helper.getReadableDatabase();
            helper.onUpgrade(db, 1, 1);

        }
    }
    private class LocationClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            locationButtonClicked = true;
            if (currentLocation == null)
                setPlaceholderLocation();
            Object item = spinner.getSelectedItem();
            if (item.equals(getString(R.string.event)))
                presenter = new EventPresenter(LoginActivity.this);
            else if (item.equals(getString(R.string.venue)))
                presenter = new VenuePresenter(LoginActivity.this);
            if (presenter instanceof EventPresenter)
                ((EventPresenter)presenter).setLocation(currentLocation.getLatitude() + "," + currentLocation.getLongitude());
            else if ( presenter instanceof VenuePresenter)
                ((VenuePresenter)presenter).setLocation(currentLocation.getLatitude() + "," + currentLocation.getLongitude());

             queryIfConnectionAvailable();
            // clear DB for quick search
            SQLiteDatabase db = helper.getReadableDatabase();
            helper.onUpgrade(db, 1, 1);
        }
    }

    protected void onStop() {
        if (google != null && google.isConnected())
        google.disconnect();
        super.onStop();
    }

     private void connectToGoogleApiClient() {
        if (google == null) {
            google = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            google.connect();
        }

    }
    private Drawable resize(Drawable image, int height, int width) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, height, width, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        Event event;
        Venue venue;
        if (list == null)
            return;
        double sumLat = 0, sumLong = 0;
        int count = list.size();
        MarkerOptions option = new MarkerOptions();
        for (int i = 0; i < list.size(); i++) {
            double lat = 0, lng = 0;

            if (list.get(i) instanceof Event) {
                event = (Event)list.get(i);
                if (event.getLongitude() == null || event.getLatitude() == null)
                    count -= 1;
                else {
                    lat = Double.parseDouble(event.getLatitude());
                    lng = Double.parseDouble(event.getLongitude());
                    addMarker(lat, lng, option, event);
                }
            }
            else if (list.get(i) instanceof Venue) {
                venue = (Venue)list.get(i);
                if (venue.getLongitude() == null || venue.getLatitude() == null)
                    count -= 1;
                else {
                    lat = Double.parseDouble(venue.getLatitude());
                    lng = Double.parseDouble(venue.getLongitude());
                    addMarker(lat, lng, option, venue);
                }
            }
            else
                count -= 1;
            sumLat += lat;
            sumLong += lng;

        }
        double avgLat = sumLat / count;
        double avgLong = sumLong / count;
        map.setInfoWindowAdapter(new InfoWindow(this));
        map.setOnInfoWindowClickListener(new Adapter.OnClickListener(this));
        if (locationButtonClicked)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(avgLat, avgLong), 12));
        else
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(avgLat, avgLong), 2));
    }
    private void addMarker(double lat, double lng, MarkerOptions option, Object iData) {
        String title = "", description = "";
        if (iData instanceof IData) {
            title = ((IData) iData).getName();
            description = ((IData) iData).getDesc();
        }
        LatLng mark = new LatLng(lat, lng);
        map.addMarker(option.position(mark).title(title).snippet(description)).setTag(iData);
    }
     void showProgressDialog() {
         if (dialog == null)
             dialog = new ProgressDialog(this);
         dialog.setMessage(getString(R.string.loading));
         dialog.show();
     }
    void saveQuery(ContentValues values) {
        SQLiteDatabase db = helper.getWritableDatabase();
        // clear DB, persist max 1 row in table at any time
        helper.onUpgrade(db, 1, 1);
        db.insert(TABLE_NAME, null, values);
    }

}

