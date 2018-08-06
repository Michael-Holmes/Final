package com.example.holmes.finalexam;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TripAdapter extends RecyclerView.ViewHolder{

    TextView tvTripName, tvTripPlace;
    ImageView ivTripLocation, ivTripAdd;

    public TripAdapter(View itemView) {
        super(itemView);
        tvTripName = itemView.findViewById(R.id.tvTripItemTripName);
        tvTripPlace = itemView.findViewById(R.id.tvTripItemPlaceName);
        ivTripLocation = itemView.findViewById(R.id.ivTripItemLocation);
        ivTripAdd = itemView.findViewById(R.id.ivTripItemAdd);
    }

    public TextView getTvTripName() {
        return tvTripName;
    }

    public void setTvTripName(TextView tvTripName) {
        this.tvTripName = tvTripName;
    }

    public TextView getTvTripPlace() {
        return tvTripPlace;
    }

    public void setTvTripPlace(TextView tvTripPlace) {
        this.tvTripPlace = tvTripPlace;
    }

    public ImageView getIvTripLocation() {
        return ivTripLocation;
    }

    public void setIvTripLocation(ImageView ivTripLocation) {
        this.ivTripLocation = ivTripLocation;
    }

    public ImageView getIvTripAdd() {
        return ivTripAdd;
    }

    public void setIvTripAdd(ImageView ivTripAdd) {
        this.ivTripAdd = ivTripAdd;
    }
}
