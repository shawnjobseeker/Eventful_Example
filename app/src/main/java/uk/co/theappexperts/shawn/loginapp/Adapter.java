package uk.co.theappexperts.shawn.loginapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.theappexperts.shawn.loginapp.contract.EventPresenter;
import uk.co.theappexperts.shawn.loginapp.model.IData;
import uk.co.theappexperts.shawn.loginapp.model.artist.Performer;
import uk.co.theappexperts.shawn.loginapp.model.event.Event;
import uk.co.theappexperts.shawn.loginapp.model.venue.Venue;

/**
 * Created by TheAppExperts on 30/10/2016.
 */

public class Adapter<E extends IData> extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<E> list;
    private int layoutId;
    private static Context context;

    public static class ViewHolder<E> extends RecyclerView.ViewHolder {
        E item;
        @BindView(R.id.title_text)
        TextView titleText;
        @BindView(R.id.date_text)
        TextView dateText;
        @BindView(R.id.description)
        TextView description;
        @BindView(R.id.im_going)
        Button imGoing;
        @BindView(R.id.image_thumb)
        ImageView imageView;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager manager = ((LoginActivity)context).getSupportFragmentManager();
                    Fragment existingFragment = manager.findFragmentByTag("Detail");
                    FragmentTransaction  transaction = manager.beginTransaction();
                    if (existingFragment != null)
                        transaction.remove(existingFragment);
                    Bundle bundle = new Bundle();
                    bundle.putString("IDataClass", item.getClass().getName());
                    bundle.putString("id", ((IData)item).getId());
                    DetailedEntryFragment newFragment = new DetailedEntryFragment();
                    newFragment.setArguments(bundle);
                    DisplayMetrics displaymetrics = new DisplayMetrics();
                    ((LoginActivity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                    if (((float)displaymetrics.widthPixels / displaymetrics.density) < 420.0f)
                    transaction.replace(R.id.search_bar_view, newFragment, "Detail");
                    else
                        transaction.replace(R.id.detail_view, newFragment, "Detail");
                    transaction.commit();
                }
            });
        }
    }

    public Adapter(List<E> list, int layoutId, Context context) {
        this.list = list;
        this.layoutId = layoutId;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        E item = list.get(position);
        holder.item = item;
        String title = (item.getName().length() < 50) ? item.getName() : item.getName().substring(0, 49) + "...";
        holder.titleText.setText(title);
        String desc = null;
        if (item.getDesc() != null)
                desc = (item.getDesc().length() < 80) ? item.getDesc() : item.getDesc().substring(0, 79) + "...";
        else
                desc = "";
        holder.description.setText(desc);
        if (item instanceof Event) {
            Event event = (Event)item;
            holder.dateText.setText(EventPresenter.getDateString(event.getStartTime(), event.getStopTime()));
            holder.imGoing.setVisibility(View.VISIBLE);
            holder.description.setVisibility(View.VISIBLE);
        }
        else if (item instanceof Venue) {
            holder.dateText.setText(((Venue)item).getAddress() + "\n" + ((Venue)item).getCountry());
            holder.description.setVisibility(View.VISIBLE);
            holder.imageView.setVisibility(View.GONE);
        }
        else {
            holder.dateText.setVisibility(View.GONE);
            holder.description.setVisibility(View.VISIBLE);
        }
        boolean a = item instanceof Performer && ((Performer)item).getImage() != null;
        boolean b = item instanceof Event && ((Event)item).getImage() != null;
        if (a || b)
        Picasso.with(context).load(item.getImageUrl(false)).resize(100, 100).into(holder.imageView);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ViewHolder<E>(view);
    }

    @Override
    public int getItemCount() {
        return (list == null) ? 0 : list.size();
    }
}
