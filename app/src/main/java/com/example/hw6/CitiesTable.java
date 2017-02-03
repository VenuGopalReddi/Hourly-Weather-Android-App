/*

@venugopal
 */

package com.example.hw6;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CitiesTable
{
    public static final String TABLE_NAME = "cities";
    public static final String ID = "_id";
    public static final String CITY_NAME = "name";
    public static final String COUNTRY = "country";
    public static final String TEMPERATURE = "temperature";
    public static final String FAVORITE = "favorite";

    public static void onCreate(SQLiteDatabase db)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ").append(TABLE_NAME).append(" (")
                .append(ID).append(" integer primary key autoincrement, ")
                .append(CITY_NAME).append(" text not null, ")
                .append(COUNTRY).append(" text not null, ")
                .append(TEMPERATURE).append(" float not null, ")
                .append(FAVORITE).append(" integer not null);");

        try
        {
            db.execSQL(sb.toString());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + CitiesTable.TABLE_NAME);
        CitiesTable.onCreate(db);
    }
}
