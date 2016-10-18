package com.dnkilic.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dogan.kilic on 21.07.2016.
 */
public class ParcelableObjectWithInnerEnum implements Parcelable{

    private ParcelableEnum parcelableEnum;


    public ParcelableObjectWithInnerEnum(ParcelableEnum parcelableEnum){
        this.parcelableEnum = parcelableEnum;
    }

    protected ParcelableObjectWithInnerEnum(Parcel in) {
        this.parcelableEnum = ParcelableEnum.values()[in.readInt()];
    }

    public static final Creator<ParcelableObjectWithInnerEnum> CREATOR = new Creator<ParcelableObjectWithInnerEnum>() {
        @Override
        public ParcelableObjectWithInnerEnum createFromParcel(Parcel in) {
            return new ParcelableObjectWithInnerEnum(in);
        }

        @Override
        public ParcelableObjectWithInnerEnum[] newArray(int size) {
            return new ParcelableObjectWithInnerEnum[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(parcelableEnum.ordinal());
    }
}
