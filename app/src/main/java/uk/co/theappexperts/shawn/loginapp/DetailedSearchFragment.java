package uk.co.theappexperts.shawn.loginapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.theappexperts.shawn.loginapp.contract.ArtistPresenter;
import uk.co.theappexperts.shawn.loginapp.contract.EventPresenter;
import uk.co.theappexperts.shawn.loginapp.contract.VenuePresenter;
import uk.co.theappexperts.shawn.loginapp.model.event.Category;

/**
 * Created by TheAppExperts on 27/10/2016.
 */

public class DetailedSearchFragment extends Fragment implements View.OnFocusChangeListener, View.OnClickListener, DatePickerDialog.OnDateSetListener, DatePickerDialog.OnDismissListener {

    FloatingActionButton floatingActionButton;
    private LoginActivity main;
    private Unbinder unbinder;

    @BindView(R.id.close_button)
    ImageButton closeButton;
    @BindView(R.id.units_spinner)
    Spinner units;
    @BindView(R.id.keyword_edit)
    EditText keywordEdit;
    @BindView(R.id.distance_edit)
    EditText distanceEdit;
    @BindView(R.id.location_edit)
    EditText locationEdit;
    @BindView(R.id.category_list)
    ListView categoryList;
    @BindView(R.id.category_panel)
    RelativeLayout categoryPanel;
    @BindView(R.id.categories_btn)
    Button categoriesBtn;
    @BindView(R.id.date_category)
    Spinner dateCategories;
    @BindView(R.id.date_from)
    EditText editFrom;
    @BindView(R.id.date_to)
    EditText editTo;
    @BindView(R.id.sort_by_category)
    Spinner sortBy;
    @BindView(R.id.asc_desc)
    Spinner ascDesc;
    @BindView(R.id.results_qty)
    Spinner resultsQty;
    @BindView(R.id.submit_button)
    Button submitButton;
    @BindView(R.id.advanced_search)
    TextView advancedSearch;
    @BindView(R.id.date_panel)
    RelativeLayout datePanel;
    @BindView(R.id.location_panel)
    RelativeLayout locationPanel;
    private Date dateFrom;
    private Date dateTo;
    private DatePickerDialog dialog;
    private Calendar calendar;
    private SimpleDateFormat formatToEditText = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatToUrl = new SimpleDateFormat("yyyyMMdd00");
    private CategoryAdapter catAdapter;
    private static Integer[] resultsPerPage = new Integer[]{5, 10, 20, 50, 100};
    private String searchFactor;
    private TypedArray array;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         main = (LoginActivity)getActivity();
        View v = inflater.inflate(R.layout.detailed_search, null, false);
        unbinder = ButterKnife.bind(this, v);
        calendar = Calendar.getInstance();
        searchFactor = getArguments().getString("Search");
        advancedSearch.setText(getString(R.string.detailed_search, searchFactor));

        if (!searchFactor.equals(getString(R.string.event))) {
            categoryPanel.setVisibility(View.GONE);
            categoriesBtn.setVisibility(View.GONE);
            datePanel.setVisibility(View.GONE);
        }
        if (searchFactor.equals(getString(R.string.artist))) {
            locationPanel.setVisibility(View.GONE);
            setSpinnerAdapter(sortBy, R.array.artist_sort_by);
            array = getResources().obtainTypedArray(R.array.artist_sort_by);
        }
        else if (searchFactor.equals(getString(R.string.event))) {
            setSpinnerAdapter(sortBy, R.array.event_sort_by);
            array = getResources().obtainTypedArray(R.array.event_sort_by);
        }
        else if (searchFactor.equals(getString(R.string.venue))) {
            setSpinnerAdapter(sortBy, R.array.venue_sort_by);
            array = getResources().obtainTypedArray(R.array.venue_sort_by);
        }
        dialog = new DatePickerDialog(getContext(), 0, this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        dialog.setOnDismissListener(this);
        setSpinnerAdapter(units, R.array.units);
        setSpinnerAdapter(dateCategories, R.array.date_categories);

        setSpinnerAdapter(ascDesc, R.array.asc_desc);
        ArrayAdapter<Integer> intAdapter = new ArrayAdapter<Integer>(getContext(), R.layout.spinner_item, resultsPerPage);
        intAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        resultsQty.setAdapter(intAdapter);
        resultsQty.setSelection(2);
        catAdapter = new CategoryAdapter(getContext(), R.layout.category_row, R.array.categories_array);
        categoryList.setAdapter(catAdapter);
        categoryPanel.setVisibility(View.GONE);
        categoriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoriesBtn.setVisibility(View.GONE);
                categoryPanel.setVisibility(View.VISIBLE);
            }
        });
        editFrom.setInputType(InputType.TYPE_NULL);
        editTo.setInputType(InputType.TYPE_NULL);
        editFrom.setOnFocusChangeListener(this);
        editTo.setOnFocusChangeListener(this);
        editFrom.setOnClickListener(this);
        editTo.setOnClickListener(this);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFragment();
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (searchFactor.equals(getString(R.string.artist))) {
                    main.presenter = new ArtistPresenter(main);
                }
                else if (searchFactor.equals(getString(R.string.event))) {
                    main.presenter = new EventPresenter(main);
                    EventPresenter vp = (EventPresenter)main.presenter;
                    if (locationEdit.getText().length() > 0 || distanceEdit.getText().length() == 0)
                        vp.setLocation(locationEdit.getText().toString());
                    else {
                        vp.setLocation(main.currentLocation.getLatitude() + "," + main.currentLocation.getLongitude());
                        vp.setWithin(Integer.parseInt(distanceEdit.getText().toString()));
                        vp.setUnits(units.getSelectedItem().toString());
                    }
                    if (dateCategories.getSelectedItem().toString().equals(getString(R.string.all))) {
                        if (editFrom.getText().length() > 0 && editTo.getText().length() > 0) {
                            vp.setDate(formatToUrl.format(dateFrom) + "-" + formatToUrl.format(dateTo));
                        }
                    }
                    else
                        vp.setDate(dateCategories.getSelectedItem().toString());
                        vp.setIncludeCategories(catAdapter.retrieveCategories(Category.INCLUDED));
                        vp.setExcludeCategories(catAdapter.retrieveCategories(Category.EXCLUDED));
                    main.floatingActionButton.setVisibility(View.VISIBLE);
                }
                    else if (searchFactor.equals(getString(R.string.venue))) {
                    main.presenter = new VenuePresenter(main);
                    VenuePresenter vp = (VenuePresenter)main.presenter;
                    if (locationEdit.getText().length() > 0 || distanceEdit.getText().length() == 0)
                        vp.setLocation(locationEdit.getText().toString());
                    else {
                        vp.setLocation(main.currentLocation.getLatitude() + "," + main.currentLocation.getLongitude());
                        vp.setWithin(Integer.parseInt(distanceEdit.getText().toString()));
                        vp.setUnits(units.getSelectedItem().toString());
                    }
                    main.floatingActionButton.setVisibility(View.VISIBLE);
                }
                main.presenter.setQuery(keywordEdit.getText().toString());
                int sortId = array.getResourceId(sortBy.getSelectedItemPosition(), -1);
                main.presenter.setSortOrder(getResources().getResourceEntryName(sortId));
                main.presenter.setSortDirection(ascDesc.getSelectedItem().toString());
                main.presenter.setPageSize(Integer.parseInt(resultsQty.getSelectedItem().toString()));
                main.presenter.query();
                closeFragment();
                main.showProgressDialog();
            }
        });
        return v;
    }
    private void setSpinnerAdapter(Spinner spinner, int arrayId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), arrayId, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            DatePicker picker = dialog.getDatePicker();
            Calendar now = Calendar.getInstance();
            dialog.show();
            try {
                if (v.getId() == R.id.date_from) {
                    editFrom.setEnabled(false);
                    picker.setMinDate(0);
                    if (dateTo != null) {
                        if (dateTo.after(now.getTime()))
                            picker.updateDate(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE));
                        picker.setMaxDate(dateTo.getTime());
                    }
                } else if (v.getId() == R.id.date_to) {
                    editTo.setEnabled(false);
                    picker.setMaxDate(Long.MAX_VALUE);
                    if (dateFrom != null) {
                        if (dateFrom.before(now.getTime()))
                            picker.updateDate(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE));
                        picker.setMinDate(dateFrom.getTime());
                    }
                }
            }
            catch (IllegalArgumentException i) { // prevent crash when same day picked
                calendar.set(Calendar.HOUR, 23);
                picker.setMaxDate(calendar.getTime().getTime());
               calendar.set(Calendar.HOUR, 0);
                picker.setMinDate(calendar.getTime().getTime());

            }
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        calendar.set(year, month, dayOfMonth);
        if (!editFrom.isEnabled()) {
            dateFrom = calendar.getTime();
            editFrom.setText(formatToEditText.format(dateFrom));
            editFrom.setEnabled(true);
        }
        else if (!editTo.isEnabled()) {
            dateTo = calendar.getTime();
            editTo.setText(formatToEditText.format(dateTo));
            editTo.setEnabled(true);
        }
        if (dialog.isShowing())
            dialog.dismiss();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        editFrom.setEnabled(true);
        editTo.setEnabled(true);

    }

    @Override
    public void onClick(View v) {
        onFocusChange(v, true);
    }
    private void closeFragment() {
        FragmentTransaction transaction = main.getSupportFragmentManager().beginTransaction();
        transaction.remove(DetailedSearchFragment.this);
        transaction.commit();
    }
}
