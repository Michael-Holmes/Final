package com.example.holmes.finalexam;

import android.os.Parcel;
import android.os.Parcelable;

public class Place implements Parcelable{
    String name, city, key, parent;

    public Place(){

    }

    protected Place(Parcel in) {
        name = in.readString();
        city = in.readString();
        key = in.readString();
        parent = in.readString();
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(city);
        dest.writeString(key);
        dest.writeString(parent);
    }
}
