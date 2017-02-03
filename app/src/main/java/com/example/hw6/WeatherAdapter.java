/*
@venugopal
 */

package com.example.hw6;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Computer on 10/21/2016.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder>
{
    public static ClickListener clickListener;

    private List<Weather> weather;
    private Context context;
    private int layout;

    public int position;

    public WeatherAdapter(Context context, List<Weather> weather, int layout)
    {
        this.weather = weather;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context currContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(currContext);

        View weatherView = inflater.inflate(layout, parent, false);

        return new WeatherViewHolder(weatherView);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position)
    {
        Weather currWeather = weather.get(position);

        this.position = holder.getLayoutPosition();

        TextView textDate = holder.textDate;
        TextView textTemp = holder.textTemp;
        ImageView icon    = holder.imageIcon;

        String temp = PreferenceActivity.isFahrenheit? currWeather.getTempFahrenheit() :
                currWeather.getTempCelsius();

        textDate.setText(currWeather.getFormattedDate());
        textTemp.setText(temp);
        Picasso.with(context).load(currWeather.getIconURI()).into(icon);
    }

    @Override
    public int getItemCount()
    {
        return weather.size();
    }

    public Context getContext()
    {
        return context;
    }

    public void setOnItemClickListener(ClickListener clickListener)
    {
        WeatherAdapter.clickListener = clickListener;
    }

    public interface ClickListener
    {
        void onItemClick(int position, View v);
    }
}
