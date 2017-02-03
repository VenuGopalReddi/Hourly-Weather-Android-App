/*
@venugopal
 */

package com.example.hw6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "MainActivity";
    public static final String CITY_KEY  = "CITY";
    public static final String COUNTRY_KEY = "COUNTRY";

    private TextView textViewNothingSaved;
    private EditText editCity, editState;
    private Button   buttonSubmit, buttonSettings;

    public static DataManager dm;

    public static ArrayList<City> cities;

    private RecyclerView rvSavedCities;
    private SavedCityAdapter cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setAllViews();
        setRecyclerView();

        PreferenceActivity.initSettings(this);

        buttonSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(MainActivity.this, CityWeatherActivity.class);
                intent.putExtra(CITY_KEY,  editCity.getText().toString());
                intent.putExtra(COUNTRY_KEY, editState.getText().toString());

                startActivity(intent);
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, PreferenceActivity.class);
                startActivity(intent);
            }
        });

        Log.d(TAG, cities.toString());
    }

    private void setRecyclerView()
    {
        showRecyclerView(!cities.isEmpty());
        cityAdapter = new SavedCityAdapter(this, cities, R.layout.saved_city_item);

        cityAdapter.setOnItemClickListener(new SavedCityAdapter.IClickListener()
        {
            @Override
            public void onItemClick(int position, View v)
            {
                Log.d(TAG, "onItemClick: " + position);

                City city = cities.get(position);
                Intent intent = new Intent(MainActivity.this, CityWeatherActivity.class);
                intent.putExtra(CITY_KEY,  city.getName());
                intent.putExtra(COUNTRY_KEY, city.getCountry());

                startActivity(intent);
            }

            @Override
            public void onItemLongClick(int position, View v)
            {
                Log.d(TAG, "onItemLongClick: " + position);
                dm.deleteCity(cities.remove(position));
                updateCities();
            }
        });

        rvSavedCities.setAdapter(cityAdapter);
        rvSavedCities.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setAllViews()
    {
        editCity             = (EditText) findViewById(R.id.editTextCity);
        editState            = (EditText) findViewById(R.id.editTextState);
        buttonSubmit         = (Button)   findViewById(R.id.buttonSubmit);
        buttonSettings       = (Button)   findViewById(R.id.buttonSettings);
        textViewNothingSaved = (TextView)     findViewById(R.id.textViewNoFav);
        rvSavedCities        = (RecyclerView) findViewById(R.id.recyclerSavedCities);

        dm = new DataManager(MainActivity.this);
        cities = dm.getAllCities();
    }

    private void showRecyclerView(boolean show)
    {
        if(show)
        {
            textViewNothingSaved.setVisibility(View.GONE);
            rvSavedCities.setVisibility(View.VISIBLE);
        }
        else
        {
            textViewNothingSaved.setVisibility(View.VISIBLE);
            rvSavedCities.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        updateCities();
    }

    private void updateCities()
    {
        cities.clear();
        cities.addAll(dm.getAllCities());
        cityAdapter.notifyDataSetChanged();
        rvSavedCities.scrollToPosition(0);
        showRecyclerView(!cities.isEmpty());
    }
}
