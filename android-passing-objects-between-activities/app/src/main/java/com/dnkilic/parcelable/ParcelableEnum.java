package com.dnkilic.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dogan.kilic on 21.07.2016.
 */
public enum ParcelableEnum implements Parcelable {

    VALUE;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeInt(ordinal());
    }

    public static final Creator<ParcelableEnum> CREATOR = new Creator<ParcelableEnum>() {
        @Override
        public ParcelableEnum createFromParcel(final Parcel source) {
            return ParcelableEnum.values()[source.readInt()];
        }

        @Override
        public ParcelableEnum[] newArray(final int size) {
            return new ParcelableEnum[size];
        }
    };
}
