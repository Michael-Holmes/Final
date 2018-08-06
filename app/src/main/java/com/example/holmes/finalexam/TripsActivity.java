package com.example.holmes.finalexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TripsActivity extends AppCompatActivity {

    static String MAIN_TO_ADD_TRIP;
    RecyclerView rvTripList;
    ImageView ivHome, ivAdd;
    DatabaseReference mDatabase;
    ComplexRecyclerViewAdapter adapter;
    static ArrayList<Object> trips = new ArrayList<>();
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);
        FirebaseApp.initializeApp(this);
        setTitle("Trips");

        rvTripList = findViewById(R.id.rvTripsList);
        ivHome = findViewById(R.id.ivTripHome);
        ivAdd = findViewById(R.id.ivTripAdd);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Trip trip1 = new Trip();
        trip1.tripName = "Trip 1";
        trip1.tripPlace = "Trip 1 Place";
        Place place1 = new Place();
        place1.name = "Place 1 Name";
        trips.add(trip1);
        trips.add(place1);

        HttpURLConnection connection = null;
        ArrayList<String> result = new ArrayList<>();
        try {
            URL url = new URL("https://maps.googleapis.com/maps/api/place/autocomplete/json?input=Amoeba&types=establishment&location=37.76999,-122.44696&radius=500&key=AIzaSyD5RCtsnvNM4JB49fvLTs1_ftK2oAoRKiw");
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String json = IOUtils.toString(connection.getInputStream(), "UTF8");

                Log.d("demo",json);

                JSONObject root = new JSONObject(json);
                /*JSONArray persons = root.getJSONArray("persons");
                for (int i=0;i<persons.length();i++) {
                    JSONObject personJson = persons.getJSONObject(i);*/




                   // result.add();
                }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TripsActivity.this, AddTripActivity.class);
                intent.putExtra(MAIN_TO_ADD_TRIP,trips);
                startActivity(intent);
            }
        });

        mDatabase.child("trips").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                trips.clear();
                for(DataSnapshot node : dataSnapshot.getChildren()){

                    if(node.getValue() instanceof Trip) {
                        Trip trip = node.getValue(Trip.class);
                        trip.key = node.getKey();
                        trips.add(trip);
                    }else if(node.getValue() instanceof Place){
                        Place place = node.getValue(Place.class);
                        place.key = node.getKey();
                        trips.add(place);
                    }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        rvTripList = findViewById(R.id.rvTripsList);
        mLayoutManager = new LinearLayoutManager(this);
        rvTripList.setLayoutManager(mLayoutManager);
        // TODO: Add in the item decoration?
        //rvTripList.addItemDecoration(new DividerItemDecoration(rvTripList.getContext(),
        // DividerItemDecoration.VERTICAL));
        adapter = new ComplexRecyclerViewAdapter(trips);
        rvTripList.setAdapter(adapter);
    }
}
