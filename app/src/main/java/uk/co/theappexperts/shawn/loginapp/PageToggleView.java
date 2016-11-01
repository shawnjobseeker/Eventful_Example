package uk.co.theappexperts.shawn.loginapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.theappexperts.shawn.loginapp.contract.IContract;

/**
 * Created by TheAppExperts on 31/10/2016.
 */

public class PageToggleView extends LinearLayout  {

    @BindView(R.id.first_button)
    ImageButton firstButton;
    @BindView(R.id.previous_button)
    ImageButton previousButton;
    @BindView(R.id.next_button)
    ImageButton nextButton;
    @BindView(R.id.last_button)
    ImageButton lastButton;
    @BindView(R.id.page_of)
    TextView pageOf;
    @BindView(R.id.page_edit)
    EditText pageText;
    private Unbinder unbinder;
    private Context context;
    private int currentPage;
    private int pageCount;
    private IContract.IPresenter presenter;
    public PageToggleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.page_toggle_view, this);
        unbinder = ButterKnife.bind(this);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        firstButton.setImageDrawable(resize(ContextCompat.getDrawable(context, android.R.drawable.ic_media_previous), 25, 25));
        previousButton.setImageDrawable(resize(ContextCompat.getDrawable(context, android.R.drawable.ic_media_play), 25, 25));
        nextButton.setImageDrawable(resize(ContextCompat.getDrawable(context, android.R.drawable.ic_media_play), 25, 25));
        lastButton.setImageDrawable(resize(ContextCompat.getDrawable(context, android.R.drawable.ic_media_next), 25, 25));
        firstButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onPageChanged(1);
            }
        });
        previousButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onPageChanged(currentPage - 1);
            }
        });
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onPageChanged(currentPage + 1);
            }
        });
        lastButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onPageChanged(pageCount);
            }
        });
        pageText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                    onPageChanged(Integer.parseInt(pageText.getText().toString()));
            }
        });
    } // N.B. due to API bug, only the first 1,200 search results are available regardless of page size and total page count


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unbinder.unbind();
    }
    private Drawable resize(Drawable image, int height, int width) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, height, width, false);
        return new BitmapDrawable(context.getResources(), bitmapResized);
    }
    public void setPage(int pageCount, int pageNumber) {
        this.currentPage = pageNumber;
        this.pageCount = pageCount;
        pageText.setText(Integer.toString(currentPage));
        if (pageOf != null)
            pageOf.setText(context.getString(R.string.page_of, pageCount));

    }
    public void setPresenter(IContract.IPresenter presenter) {
        this.presenter = presenter;
    }
    public void onPageChanged(int newPage) {
        if (currentPage != newPage && newPage <= pageCount && newPage > 0) {
            presenter.setPageNumber(newPage);
            presenter.query();
            ((LoginActivity)context).showProgressDialog();
        }
    }
}
