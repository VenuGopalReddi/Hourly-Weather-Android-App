/*
@venugopal
 */

package com.example.hw6;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

/**
 * Created by Computer on 10/22/2016.
 */

public class WeatherDetailedViewHolder extends RecyclerView.ViewHolder
{
    public TextView textTemp, textCondition, textPressure, textHumidity, textWind, textHour;
    public ImageView weatherIcon;

    public WeatherDetailedViewHolder(View itemView)
    {
        super(itemView);

        textTemp = (TextView) itemView.findViewById(R.id.textViewTemp);
        textCondition = (TextView) itemView.findViewById(R.id.textViewCondition);
        textPressure = (TextView) itemView.findViewById(R.id.textViewPressure);
        textHumidity = (TextView) itemView.findViewById(R.id.textViewHumidity);
        textWind = (TextView) itemView.findViewById(R.id.textViewWind);
        textHour = (TextView) itemView.findViewById(R.id.textViewHour);

        weatherIcon = (ImageView) itemView.findViewById(R.id.imageViewIcon);
    }
}
