package uk.co.theappexperts.shawn.loginapp.contract;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.Arrays;
import java.util.List;

import uk.co.theappexperts.shawn.loginapp.Analytics;
import uk.co.theappexperts.shawn.loginapp.DetailedEntryFragment;
import uk.co.theappexperts.shawn.loginapp.LoginActivity;
import uk.co.theappexperts.shawn.loginapp.PresenterParams.Columns;
import uk.co.theappexperts.shawn.loginapp.connect.ApiClient;
import uk.co.theappexperts.shawn.loginapp.connect.ApiInterface;
import uk.co.theappexperts.shawn.loginapp.model.IData;

public interface IContract {
    abstract class IPresenter {
        /**
         * All API calls and calls to data sources local or remote
         * called by views
         */
        protected ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        // query fields and setters
        protected String query;
        protected String sortDirection;
        protected String sortOrder;
        protected int pageSize;
        protected int pageNumber;
        protected Context context;
        public abstract void query();
        protected void trackException(Throwable e) {
            String msg = "";
            if (e.getMessage() != null)
                msg = e.getMessage();
            Log.e(e.getClass().getName(), msg + Arrays.deepToString(e.getStackTrace()));
            Application app = ((LoginActivity)context).getApplication();
            Tracker t = ((Analytics)app).getDefaultTracker();
            t.enableExceptionReporting(true);
            t.send(new HitBuilders.ExceptionBuilder()
                    .setDescription(e.getClass().getName() + ":" + Arrays.deepToString(e.getStackTrace()))
                    .setFatal(false)
                    .build());
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public void setValues(Cursor cursor) {
            this.query = cursor.getString(cursor.getColumnIndex(Columns.SEARCH_TERM));
            this.sortDirection = cursor.getString(cursor.getColumnIndex(Columns.SORT_DIRECTION));
            this.sortOrder = cursor.getString(cursor.getColumnIndex(Columns.SORT_ORDER));
            this.pageSize = cursor.getInt(cursor.getColumnIndex(Columns.PAGE_SIZE));
        }
        public void setValues(ContentValues values) {
            this.query = values.getAsString(Columns.SEARCH_TERM);
            this.sortDirection = values.getAsString(Columns.SORT_DIRECTION);
            this.sortOrder = values.getAsString(Columns.SORT_ORDER);
            Integer pageSize = values.getAsInteger(Columns.PAGE_SIZE);
            this.pageSize = (pageSize == null) ? 0 : pageSize;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }


    }
    abstract class IDetailPresenter {
        protected ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        protected String id;
        protected DetailedEntryFragment fragment;
        public abstract void get();

        public void setId(String id) {
            this.id = id;
        }
        public IDetailPresenter(DetailedEntryFragment fragment) {
            this.fragment = fragment;
        }
        protected void trackException(Throwable e) {
            String msg = "";
            if (e.getMessage() != null)
                msg = e.getMessage();
            Log.e(e.getClass().getName(), msg + Arrays.deepToString(e.getStackTrace()));
            Application app = fragment.getActivity().getApplication();
            Tracker t = ((Analytics)app).getDefaultTracker();
            t.enableExceptionReporting(true);
            t.send(new HitBuilders.ExceptionBuilder()
                    .setDescription(e.getClass().getName() + ":" + Arrays.deepToString(e.getStackTrace()))
                    .setFatal(false)
                    .build());
        }
    }
    interface IView<T extends IPresenter> {
        /**
         * All things which view should perform
         * call by presenter
         */
        public void setPresenter(T presenter);
        public <E extends IData> void passDataAdapter(List<E> list);
        public void passDataAdapter(IData data);
        public void showProgressDialog();
        public void hideProgressDialog();
        public boolean checkInternet();
    }
}
