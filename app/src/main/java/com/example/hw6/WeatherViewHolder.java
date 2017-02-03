/*
@venugopal
 */

package com.example.hw6;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Computer on 10/21/2016.
 */

public class WeatherViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView textDate, textTemp;
    public ImageView imageIcon;

    public WeatherViewHolder(View itemView)
    {
        super(itemView);

        itemView.setOnClickListener(this);

        textDate =  (TextView)  itemView.findViewById(R.id.textViewDate);
        textTemp =  (TextView)  itemView.findViewById(R.id.textViewAvgTemp);
        imageIcon = (ImageView) itemView.findViewById(R.id.imageViewWeatherIcon);
    }

    @Override
    public void onClick(View v)
    {
        WeatherAdapter.clickListener.onItemClick(getAdapterPosition(), v);
    }
}
