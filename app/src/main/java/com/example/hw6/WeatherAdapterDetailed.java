/*
@venugopal
 */

package com.example.hw6;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class WeatherAdapterDetailed extends RecyclerView.Adapter<WeatherDetailedViewHolder>
{
    private List<Weather> weather;
    private Context context;
    private int layout;

    public WeatherAdapterDetailed(Context context, List<Weather> weather, int layout)
    {
        this.weather = weather;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public WeatherDetailedViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        Context currContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(currContext);

        View weatherView = inflater.inflate(layout, parent, false);

        return new WeatherDetailedViewHolder(weatherView);
    }

    @Override
    public void onBindViewHolder(WeatherDetailedViewHolder holder, int position)
    {
        Weather currWeather = weather.get(position);

        TextView textHour = holder.textHour;
        TextView textTemp = holder.textTemp;
        TextView textCondtition = holder.textCondition;
        TextView textHumidity = holder.textHumidity;
        TextView textPressure = holder.textPressure;
        TextView textWind = holder.textWind;
        ImageView weatherIcon = holder.weatherIcon;

        String temp = PreferenceActivity.isFahrenheit? currWeather.getTempFahrenheit() :
                currWeather.getTempCelsius();

        textHour.setText(currWeather.getFormattedHour());
        textTemp.setText(temp);
        textCondtition.setText(currWeather.getCondition());
        textHumidity.setText(currWeather.getHumidity() + "%");
        textPressure.setText(currWeather.getPressure() + " hPa");
        textWind.setText(currWeather.getWindSpeed() + "mps, " + currWeather.getWindDir());

        Picasso.with(context).load(currWeather.getIconURI()).into(weatherIcon);
    }

    @Override
    public int getItemCount()
    {
        return weather.size();
    }
}
