package com.dnkilic.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableObjectWithInnerClass implements Parcelable {
    private String name;
    private String surname;
    private int id;
    private Address address;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeInt(id);

        dest.writeParcelable(address, flags);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public ParcelableObjectWithInnerClass createFromParcel(Parcel in) {
            return new ParcelableObjectWithInnerClass(in);
        }

        public ParcelableObjectWithInnerClass[] newArray(int size) {
            return new ParcelableObjectWithInnerClass[size];
        }
    };

    // Constructor
    public ParcelableObjectWithInnerClass(String name, String surname, int id, Address address){
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.address = address;
    }

    // Parcelling part
    public ParcelableObjectWithInnerClass(Parcel in){
        this.name = in.readString();
        this.surname = in.readString();
        this.id = in.readInt();

        address = (Address) in.readParcelable(Address.class.getClassLoader());
    }

    public static class Address implements Parcelable {
        private String street;
        private String city;
        private int number;

        public Address(String street, String city, int number) {
            this.city = city;
            this.street = street;
            this.number = number;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(street);
            dest.writeString(city);
            dest.writeInt(number);
        }

        // Creator
        public static final Parcelable.Creator
                CREATOR
                = new Parcelable.Creator
                () {
            public Address createFromParcel(Parcel in) {
                return new Address(in);
            }

            public Address[] newArray(int size) {
                return new Address[size];
            }
        };

        // "De-parcel object
        private Address(Parcel in) {
            street = in.readString();
            city = in.readString();
            number = in.readInt();
        }
    }
}