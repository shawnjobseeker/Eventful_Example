package uk.co.example.shawn.loginapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.example.shawn.loginapp.model.IData;

/**
 * Created by Shawn Li on 11/11/2016.
 */

public class InfoWindow implements GoogleMap.InfoWindowAdapter {

    private Activity context;
    private IData data;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.im_going)
    Button imGoing;
    @BindView(R.id.image_thumb)
    ImageView imageView;
    @Override
    public View getInfoContents(Marker marker) {
        View v = LayoutInflater.from(context).inflate(R.layout.row, null);
        data = (IData)marker.getTag();
        ButterKnife.bind(this, v);
        titleText.setText(marker.getTitle());
        description.setText(marker.getSnippet());
        imGoing.setVisibility(View.GONE);
        String url = data.getImageUrl(false);
        if (url != null && url.length() > 0)
            Picasso.with(context).load(data.getImageUrl(false)).resize(100, 100).into(imageView);
        return v;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }
    public InfoWindow(Activity context) {
        this.context = context;
    }
}
