package uk.co.example.shawn.loginapp;

import android.provider.BaseColumns;

/**
 * Created by Shawn Li on 03/11/2016.
 */

public class PresenterParams {
    private PresenterParams() {
    }

    public static class Columns implements BaseColumns {

        public static final String IDATA_CLASS = "class";
        public static final String SEARCH_TERM = "keywords";
        public static final String LOCATION = "location";
        public static final String WITHIN = "within";
        public static final String UNITS = "units";
        public static final String INCLUDE_CATEGORIES = "category";
        public static final String EXCLUDE_CATEGORIES = "ex_category";
        public static final String DATE = "date";
        public static final String SORT_ORDER = "sort_order";
        public static final String SORT_DIRECTION = "sort_direction";
        public static final String PAGE_SIZE = "page_size";
    }
    public static final String TABLE_NAME = "presenter_params";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + Columns._ID + " INTEGER PRIMARY KEY,"
            + Columns.IDATA_CLASS + " TEXT,"
            + Columns.SEARCH_TERM + " TEXT,"
            + Columns.LOCATION + " TEXT,"
            + Columns.WITHIN + " INTEGER,"
            + Columns.UNITS + " TEXT,"
            + Columns.DATE + " TEXT,"
            + Columns.INCLUDE_CATEGORIES + " TEXT,"
            + Columns.EXCLUDE_CATEGORIES + " TEXT,"
            + Columns.SORT_ORDER + " TEXT,"
            + Columns.SORT_DIRECTION + " TEXT,"
            + Columns.PAGE_SIZE + " INTEGER);";

    public static final String DROP_TABLE = "DROP TABLE " + TABLE_NAME + ";";

}
