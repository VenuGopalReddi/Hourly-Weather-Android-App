/*
@venugopal
 */

package com.example.hw6;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class CityDAO
{
    private SQLiteDatabase db;

    public CityDAO(SQLiteDatabase db)
    {
        this.db = db;
    }

    public long save(City city)
    {
        ContentValues values = new ContentValues();
        values.put(CitiesTable.CITY_NAME, city.getName());
        values.put(CitiesTable.COUNTRY, city.getCountry());
        values.put(CitiesTable.FAVORITE, city.isFavorite());
        values.put(CitiesTable.TEMPERATURE, city.getTemperature());

        return db.insert(CitiesTable.TABLE_NAME, null, values);
    }

    public boolean update(City city)
    {
        ContentValues values = new ContentValues();
        values.put(CitiesTable.CITY_NAME, city.getName());
        values.put(CitiesTable.COUNTRY, city.getCountry());
        values.put(CitiesTable.FAVORITE, city.isFavorite());
        values.put(CitiesTable.TEMPERATURE, city.getTemperature());

        return db.update(CitiesTable.TABLE_NAME, values, CitiesTable.ID + "=" + city.getId(), null) > 0;
    }

    public boolean delete(City city)
    {
        return db.delete(CitiesTable.TABLE_NAME, CitiesTable.ID + "=" + city.getId(), null) > 0;
    }

    public ArrayList<City> getAll()
    {
        ArrayList<City> cities = new ArrayList<>();



        Cursor c = db.query(CitiesTable.TABLE_NAME,
                new String[] {CitiesTable.ID, CitiesTable.CITY_NAME, CitiesTable.COUNTRY,
                CitiesTable.TEMPERATURE, CitiesTable.FAVORITE},
                null, null, null, null, CitiesTable.FAVORITE + " DESC");

        if(c != null && c.getCount() > 0)
        {
            c.moveToFirst();

            do
            {
                cities.add(buildCityFromCursor(c));
            } while(c.moveToNext());

            if(!c.isClosed())
                c.close();
        }

        return cities;
    }

    private City buildCityFromCursor(Cursor c)
    {
        City city = null;

        if (c != null)
        {
            city = new City();
            city.setId(c.getInt(0));
            city.setName(c.getString(1));
            city.setCountry(c.getString(2));
            city.setTemperature(c.getFloat(3));
            city.setFavorite(c.getInt(4));
        }

        return city;
    }
}
