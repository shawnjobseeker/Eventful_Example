package uk.co.theappexperts.shawn.loginapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.List;

import uk.co.theappexperts.shawn.loginapp.model.Link;

/**
 * Created by TheAppExperts on 07/11/2016.
 */

 class LinkAdapter extends RecyclerView.Adapter<LinkAdapter.LinkViewHolder> {
    private List<Link> links;
    private  Context context;
      class LinkViewHolder extends RecyclerView.ViewHolder {
         TextView textView;
         LinkViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Tracker tracker = ((LoginActivity)context).tracker;
                    tracker.send(new HitBuilders.EventBuilder().setAction(Intent.ACTION_VIEW).build());
                }
            });
        }
    }
     LinkAdapter(List<Link> links, Context context) {
        this.links = links;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return (links == null) ? 0 : links.size();
    }

    @Override
    public void onBindViewHolder(LinkViewHolder holder, int position) {
        Link link = links.get(position);
        holder.textView.setClickable(true);
        holder.textView.setText(Html.fromHtml(link.getType() + ": <a href=\"" + link.getUrl() + "\">" + link.getUrl() + "</a>"));
        holder.textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public LinkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = new TextView(parent.getContext());
        return new LinkViewHolder(v);
    }
}