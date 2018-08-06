package com.example.holmes.finalexam;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Trip implements Parcelable {
    String tripName, tripPlace, key;
    ArrayList<Place> places;

    public Trip(){

    }

    protected Trip(Parcel in) {
        tripName = in.readString();
        tripPlace = in.readString();
        key = in.readString();
    }

    public static final Creator<Trip> CREATOR = new Creator<Trip>() {
        @Override
        public Trip createFromParcel(Parcel in) {
            return new Trip(in);
        }

        @Override
        public Trip[] newArray(int size) {
            return new Trip[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tripName);
        dest.writeString(tripPlace);
        dest.writeString(key);
    }
}
