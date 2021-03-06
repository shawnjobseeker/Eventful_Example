package uk.co.example.shawn.loginapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.example.shawn.loginapp.model.event.Category;


/**
 * Created by Shawn Li on 01/11/2016.
 */

 class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private int layoutId;
    private TypedArray array;
    private Integer[] include;
    private Context context;
     CategoryAdapter(Context context, int layoutId, int arrayRes) {

        this.array = context.getResources().obtainTypedArray(arrayRes);
        this.include = new Integer[array.length()];
        this.layoutId = layoutId;
        Arrays.fill(include, 0);
        this.context = context;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.category)
        TextView category;
        @BindView(R.id.radio_group)
        RadioGroup group;
         ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    @Override
    public void onBindViewHolder(final CategoryAdapter.ViewHolder holder, final int position) {
        holder.category.setText(array.getString(position));
        holder.group.setTag(array.getString(position));
        SharedPreferences preferences = ((LoginActivity)context).getPreferences(Context.MODE_PRIVATE);
        holder.group.check(preferences.getInt(holder.group.getTag().toString(), R.id.undecided_button));
        holder.group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == R.id.include_button)
                        include[position] = Category.INCLUDED;
                    else if (checkedId == R.id.exclude_button)
                        include[position] = Category.EXCLUDED;
                // store checked radio button
                SharedPreferences preferences = ((LoginActivity)context).getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt(group.getTag().toString(), checkedId);
                editor.commit();
            }
        });
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return (array == null) ? 0 : array.length();
    }

     String retrieveCategories(int included) {
        String returnString = "";
        for (int i = 0; i < array.length(); i++) {
            if (include[i] == included)
                returnString += context.getResources().getResourceEntryName(array.getResourceId(i, -1)) + ",";
        }
        if (returnString.endsWith(","))
        return returnString.substring(0, returnString.length()-1);
        else
            return (returnString.length() > 0) ? returnString : null;
         // field only accepts 'item,item,item' format
    }
    void reset() {
        SharedPreferences preferences = ((LoginActivity)context).getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }


}
