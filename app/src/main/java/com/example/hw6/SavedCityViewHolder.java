/*
@venugopal
 */

package com.example.hw6;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SavedCityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
{
    public TextView textLocation, textTemp, textUpdatedOn;
    public ImageView ivFavIcon;

    public SavedCityViewHolder(View itemView)
    {
        super(itemView);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);

        textLocation  = (TextView)  itemView.findViewById(R.id.textViewLocation);
        textTemp      = (TextView)  itemView.findViewById(R.id.textViewCityTemp);
        textUpdatedOn = (TextView)  itemView.findViewById(R.id.textViewUpdatedOn);
        ivFavIcon     = (ImageView) itemView.findViewById(R.id.imageViewFavorite);
    }

    @Override
    public void onClick(View v)
    {
        SavedCityAdapter.clickListener.onItemClick(getAdapterPosition(), v);
    }

    @Override
    public boolean onLongClick(View v)
    {
        SavedCityAdapter.clickListener.onItemLongClick(getAdapterPosition(), v);
        return false;
    }
}
