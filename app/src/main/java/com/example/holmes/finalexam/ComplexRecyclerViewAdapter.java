package com.example.holmes.finalexam;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ComplexRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> items;
    private final int TRIP = 0, PLACE = 1;

    @Override
    public int getItemViewType(int position) {
        if(items.get(position) instanceof Trip){
            return TRIP;
        }else if(items.get(position) instanceof Place){
            return PLACE;
        }
        return -1;
    }

    public ComplexRecyclerViewAdapter(List<Object> items){
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType){
            case TRIP:
                View tripView = inflater.inflate(R.layout.trip_item, parent, false);
                viewHolder = new TripAdapter(tripView);
                break;
            case PLACE:
                View placeView = inflater.inflate(R.layout.place_item, parent, false);
                viewHolder = new PlaceAdapter(placeView);
                break;
            default:
                Log.d("demo","Error: Could not create the View in onCreateViewHolder.");
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()){
            case TRIP:
                TripAdapter trip = (TripAdapter) viewHolder;
                configureTripViewHolder(trip, position);
                break;
            case PLACE:
                PlaceAdapter place = (PlaceAdapter) viewHolder;
                configurePlaceViewHolder(place, position);
                break;
            default:
                    Log.d("demo","Error: Could not bind the View in onBindViewHolder.");
                    break;
        }
    }

    private void configureTripViewHolder(TripAdapter tripAdapter, int position){
        Trip trip = (Trip) items.get(position);
        if(trip != null){
            // TODO: Change to pull from Firebase
            tripAdapter.getTvTripName().setText(trip.tripName);
            tripAdapter.getTvTripPlace().setText(trip.tripPlace);
        }
    }

    private void configurePlaceViewHolder(PlaceAdapter placeAdapter, int position){
        Place place = (Place) items.get(position);
        if(place != null){
            // TODO: Change to pull from Firebase
            placeAdapter.getTvPlaceName().setText(place.name);
        }
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
}

