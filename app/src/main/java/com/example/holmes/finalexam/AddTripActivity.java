package com.example.holmes.finalexam;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddTripActivity extends AppCompatActivity {

    EditText etTripName, etTripCity;
    RecyclerView rvAddTrip;
    Button btnAddTrip, btnSearch;
    private DatabaseReference mDatabase;
    Context context;
    Trip trip;
    ArrayList<String> cities;
    GeoDataClient mGeoDataClient;
    String tripCity, tripName;
    LatLng northeast;
    LatLng southwest;
    LatLngBounds llBounds;
    LatLngBounds.Builder boundsBuilder;
    AutocompleteFilter.Builder acBuilder;
    AutocompleteFilter acFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        context = getApplicationContext();
        etTripName = findViewById(R.id.etAddTripName);
        etTripCity = findViewById(R.id.etAddTripCity);
        rvAddTrip = findViewById(R.id.rvAddTripCities);
        btnAddTrip = findViewById(R.id.btnAddTripAddTrip);
        btnSearch = findViewById(R.id.btnAddTripSearch);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        northeast = new LatLng(50.0,-65.0);
        southwest = new LatLng(24.0,-125.0);
        boundsBuilder = new LatLngBounds.Builder();
        boundsBuilder.include(northeast);
        boundsBuilder.include(southwest);
        llBounds = boundsBuilder.build();
        acBuilder = new AutocompleteFilter.Builder();
        acBuilder.setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES);
        acFilter = acBuilder.build();





        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tripCity = etTripCity.getText().toString().trim();
                mGeoDataClient.getAutocompletePredictions(tripCity,llBounds, AutocompleteFilter.TYPE_FILTER_CITIES,acFilter);
            }
        });

        btnAddTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trip.tripPlace = etTripCity.getText().toString().trim();
                trip.tripName = etTripName.getText().toString().trim();
            }
        });

    }
}
