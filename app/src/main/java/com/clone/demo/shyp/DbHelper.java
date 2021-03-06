package com.clone.demo.shyp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper {

    public static final String COLUMN_NAME = "fruits";
    private static final String TAG = "App";
    private DatabaseHelper myDbHelper;
    private SQLiteDatabase myDb;

    private static final String DATABASE_NAME = "Data";
    private static final String FTS_VIRTUAL_TABLE = "Info";
    private static final int DATABASE_VERSION = 1;

    //Create a Virtual Table for fast searches
    private static final String DATABASE_CREATE =
            "CREATE VIRTUAL TABLE " + FTS_VIRTUAL_TABLE + " USING fts3("
                    + COLUMN_NAME
                    + " UNIQUE (" + COLUMN_NAME + "));";


    private final Context context;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.w(TAG, DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + FTS_VIRTUAL_TABLE);
            onCreate(db);
        }
    }

    public DbHelper(Context ctx) {
        this.context = ctx;
    }

    public DbHelper open() throws SQLException {
        myDbHelper = new DatabaseHelper(context);
        myDb = myDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (myDbHelper != null) {
            myDbHelper.close();
        }
    }


    public long createList(String name) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(COLUMN_NAME, name);

        return myDb.insert(FTS_VIRTUAL_TABLE, null, initialValues);

    }


    public Cursor searchByInputText(String inputText) throws SQLException {

        String query = "SELECT docid as _id," +
                COLUMN_NAME + " from " + FTS_VIRTUAL_TABLE +
                " where " + COLUMN_NAME + " MATCH '" + inputText + "';";

        Cursor mCursor = myDb.rawQuery(query, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }


    public boolean deleteAllNames() {

        int doneDelete = myDb.delete(FTS_VIRTUAL_TABLE, null, null);
        return doneDelete > 0;

    }
}