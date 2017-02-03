/*
@venugopal
 */

package com.example.hw6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Computer on 10/22/2016.
 */

public class DataManager
{
    Context context;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    CityDAO cityDAO;

    public DataManager(Context context)
    {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
        cityDAO = new CityDAO(db);
    }

    public void close()
    {
        db.close();
    }

    public long saveCity(City city)
    {
        return cityDAO.save(city);
    }

    public boolean updateCity(City city)
    {
        return cityDAO.update(city);
    }

    public boolean deleteCity(City city)
    {
        return cityDAO.delete(city);
    }

    public ArrayList<City> getAllCities()
    {
        return cityDAO.getAll();
    }
}
