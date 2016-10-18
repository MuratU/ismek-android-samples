package com.dnkilic.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dogan.kilic on 21.07.2016.
 */
public class ParcelableObjectWithInnerHashMap implements Parcelable{

    private HashMap <String, String> peopleList = new HashMap<>();

    public ParcelableObjectWithInnerHashMap(HashMap<String, String> peopleList)
    {
        this.peopleList = peopleList;
    }

    protected ParcelableObjectWithInnerHashMap(Parcel in) {

        final int size = in.readInt();

        for (int i = 0; i < size; i++) {
            String key = in.readString();
            String value = in.readString();
            peopleList.put(key, value);
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(peopleList.size());

        for (Map.Entry<String, String> entry : peopleList.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ParcelableObjectWithInnerHashMap> CREATOR = new Creator<ParcelableObjectWithInnerHashMap>() {
        @Override
        public ParcelableObjectWithInnerHashMap createFromParcel(Parcel in) {
            return new ParcelableObjectWithInnerHashMap(in);
        }

        @Override
        public ParcelableObjectWithInnerHashMap[] newArray(int size) {
            return new ParcelableObjectWithInnerHashMap[size];
        }
    };
}
