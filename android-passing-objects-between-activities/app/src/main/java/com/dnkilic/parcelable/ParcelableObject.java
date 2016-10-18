package com.dnkilic.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableObject implements Parcelable{
    private String name;
    private String surname;
    private int id;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeInt(id);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public ParcelableObject createFromParcel(Parcel in) {
            return new ParcelableObject(in);
        }

        public ParcelableObject[] newArray(int size) {
            return new ParcelableObject[size];
        }
    };

    // Constructor
    public ParcelableObject(String name, String surname, int id){
        this.name = name;
        this.surname = surname;
        this.id = id;
    }

    // Parcelling part
    private ParcelableObject(Parcel in){
        this.name = in.readString();
        this.surname = in.readString();
        this.id = in.readInt();
    }
}