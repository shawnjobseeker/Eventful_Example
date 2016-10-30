package uk.co.theappexperts.shawn.loginapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.theappexperts.shawn.loginapp.contract.ArtistPresenter;
import uk.co.theappexperts.shawn.loginapp.contract.EventPresenter;
import uk.co.theappexperts.shawn.loginapp.contract.IContract;
import uk.co.theappexperts.shawn.loginapp.contract.VenuePresenter;
import uk.co.theappexperts.shawn.loginapp.model.IData;

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
    private NavHeaderHolder holder;
    private Profile currentProfile;
    private GoogleApiClient google;
    private Location currentLocation;
    private boolean mapEnabled;
    private Drawable listIcon;
    private GoogleMap map;
    private Bundle savedInstanceState;
    private  SupportMapFragment fragment;
    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken token = loginResult.getAccessToken();
             LoginActivity.this.currentProfile = Profile.getCurrentProfile();
            updateLogin();
            Toast.makeText(getApplicationContext(), "Login successful.", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        navigationView.setNavigationItemSelectedListener(this);
        // initialize search view
        button.setImageDrawable(resize(ContextCompat.getDrawable(this, android.R.drawable.ic_menu_search), 25, 25));
        locationButton.setImageDrawable(resize(ContextCompat.getDrawable(this, android.R.drawable.ic_menu_mylocation), 25, 25));
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.search_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals(getString(R.string.venue))) {
                    locationButton.setVisibility(View.VISIBLE);
                    floatingActionButton.setVisibility(View.VISIBLE);
                } else {

                    locationButton.setVisibility(View.INVISIBLE);
                    floatingActionButton.setVisibility(View.INVISIBLE);
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                locationButton.setVisibility(View.INVISIBLE);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object item = spinner.getSelectedItem();
               if (item.equals(getString(R.string.artist)))
                   new ArtistPresenter(LoginActivity.this).query(editText.getText().toString());
                else if (item.equals(getString(R.string.event)))
                   new EventPresenter(LoginActivity.this).query(editText.getText().toString());
                else if (item.equals(getString(R.string.venue)))
                   new VenuePresenter(LoginActivity.this).query(editText.getText().toString());
            }
        });
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

    }
    public <E extends IData> void passDataAdapter(List<E> list) {
        recyclerView.setAdapter(new Adapter<E>(list, R.layout.row, this));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Facebook login
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();

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
        floatingActionButton.bringToFront();
        if (fragment == null)
         fragment = new SupportMapFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (mapEnabled) {
            floatingActionButton.setImageDrawable(listIcon);
            transaction.replace(R.id.content_main, fragment);
            transaction.commit();
            fragment.getMapAsync(LoginActivity.this);
        }
        else {
            transaction.remove(fragment);
            transaction.commit();
            floatingActionButton.setImageDrawable(ContextCompat.getDrawable(LoginActivity.this, android.R.drawable.ic_dialog_map));
        }


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
        int id = item.getItemId();
        drawer.closeDrawer(GravityCompat.START);
        /*SearchResultFragment fragment = new SearchResultFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main, fragment);
        //transaction.show(fragment);
        transaction.commit();*/
        return true;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try {
            currentLocation = LocationServices.FusedLocationApi.getLastLocation(google);
            savedInstanceState.putDouble("latitude", currentLocation.getLatitude());
            savedInstanceState.putDouble("longitude", currentLocation.getLongitude());
        } catch(SecurityException e) {
            onConnectionFailed(new ConnectionResult(ConnectionResult.SERVICE_MISSING_PERMISSION, null, "Connection unauthorised."));
        }

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
        double lat, lng;
        if (currentLocation == null) {
            lat = savedInstanceState.getDouble("latitude");
            lng = savedInstanceState.getDouble("longitude");
        }
        else {
            lat = currentLocation.getLatitude();
            lng = currentLocation.getLongitude();
        }
            LatLng mark = new LatLng(lat, lng);
            map.addMarker(new MarkerOptions().position(mark));
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(mark, 14));
    }
}

