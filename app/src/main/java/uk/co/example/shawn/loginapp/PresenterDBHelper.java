package uk.co.example.shawn.loginapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shawn Li on 03/11/2016.
 */

public class PresenterDBHelper extends SQLiteOpenHelper {
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PresenterParams.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PresenterParams.DROP_TABLE);
        onCreate(db);
    }

    public PresenterDBHelper(Context context) {
        super(context, "MockEventful.db", null, 1);

    }
}
