/*
@venugopal
 */

package com.example.hw6;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Computer on 10/22/2016.
 */

public class SavedCityAdapter extends RecyclerView.Adapter<SavedCityViewHolder>
{
    public static IClickListener clickListener;

    private List<City> cities;
    private Context context;
    private int layout;

    public SavedCityAdapter(Context context, List<City> cities, int layout)
    {
        this.context = context;
        this.cities = cities;
        this.layout = layout;
    }

    @Override
    public SavedCityViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context currContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(currContext);

        View cityView = inflater.inflate(layout, parent, false);

        return new SavedCityViewHolder(cityView);
    }

    @Override
    public void onBindViewHolder(SavedCityViewHolder holder, final int position)
    {
        final City currCity = cities.get(position);

        TextView textLocation = holder.textLocation;
        TextView textTemp = holder.textTemp;
        TextView textUpdatedOn = holder.textUpdatedOn;
        ImageView ivFavIcon = holder.ivFavIcon;

        ivFavIcon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MainActivity.cities.remove(position);
                currCity.setFavorite(currCity.isFavorite()? 0 : 1);
                MainActivity.cities.add(0, currCity);
                notifyDataSetChanged();
                MainActivity.dm.updateCity(currCity);
            }
        });

        // TODO: probably change?
        String updatedOn = new SimpleDateFormat("MMM dd, yyyy").format(new Date());
        String temp = PreferenceActivity.isFahrenheit? currCity.getTempFahrenheit() :
                currCity.getTempCelsius();

        textLocation.setText(currCity.getName() + ", " + currCity.getCountry());
        textTemp.setText(temp);
        textUpdatedOn.setText(context.getString(R.string.lbl_updated_on) + " " + updatedOn);

        int image = currCity.isFavorite()? R.drawable.star_gold : R.drawable.star_gray;
        Picasso.with(context).load(image).into(ivFavIcon);
    }

    public void setOnItemClickListener(IClickListener clickListener)
    {
        SavedCityAdapter.clickListener = clickListener;
    }

    @Override
    public int getItemCount()
    {
        return cities.size();
    }

    public interface IClickListener
    {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }
}
