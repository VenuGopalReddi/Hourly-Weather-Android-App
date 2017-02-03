/*
@venugopal
 */

package com.example.hw6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "cities.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TAG = "DatabaseHelper";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        CitiesTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        CitiesTable.onUpgrade(db, oldVersion, newVersion);
    }
}
