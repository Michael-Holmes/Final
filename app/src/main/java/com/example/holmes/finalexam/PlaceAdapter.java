package com.example.holmes.finalexam;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PlaceAdapter extends RecyclerView.ViewHolder{
    TextView tvPlaceName;
    ImageView ivPlaceDelete, ivPlaceType;

    public TextView getTvPlaceName() {
        return tvPlaceName;
    }

    public void setTvPlaceName(TextView tvPlaceName) {
        this.tvPlaceName = tvPlaceName;
    }

    public ImageView getIvPlaceDelete() {
        return ivPlaceDelete;
    }

    public void setIvPlaceDelete(ImageView ivPlaceDelete) {
        this.ivPlaceDelete = ivPlaceDelete;
    }

    public ImageView getIvPlaceType() {
        return ivPlaceType;
    }

    public void setIvPlaceType(ImageView ivPlaceType) {
        this.ivPlaceType = ivPlaceType;
    }

    public PlaceAdapter(View itemView) {
        super(itemView);

        tvPlaceName = itemView.findViewById(R.id.tvPlaceItemName);
        ivPlaceDelete = itemView.findViewById(R.id.ivPlaceItemAdd);
        ivPlaceType = itemView.findViewById(R.id.ivPlaceItemType);

    }
}
