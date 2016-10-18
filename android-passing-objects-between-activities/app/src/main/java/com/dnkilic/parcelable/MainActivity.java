package com.dnkilic.parcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    //TODO Lütfen okuyunuz http://blog.prolificinteractive.com/2014/07/18/why-we-love-parcelable/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void parcel(View v)
    {
        ParcelableObject parcelableObject = new ParcelableObject("Doğan","Kılıç", 10);
        Intent i = new Intent(MainActivity.this, SecondaryActivity.class);
        i.putExtra("User", parcelableObject);
        i.putExtra("Type", TransitionType.PARCEL_OBJECT);
        startActivity(i);
    }

    public void parcelArrayList(View v)
    {
        ArrayList<ParcelableObject> listOfParcel = new ArrayList<>();
        listOfParcel.add(new ParcelableObject("Doğan","Kılıç", 10));
        listOfParcel.add(new ParcelableObject("Yücel","Kılıç", 11));
        listOfParcel.add(new ParcelableObject("Mert","Kılıç", 12));

        Intent i = new Intent(MainActivity.this, SecondaryActivity.class);
        i.putParcelableArrayListExtra("User", listOfParcel);
        i.putExtra("Type", TransitionType.PARCEL_ARRAY_LIST_OF_OBJECTS);
        startActivity(i);
    }

    public void parcelObjectWithInnerClass(View v)
    {
        ParcelableObjectWithInnerClass parcelableObject = new ParcelableObjectWithInnerClass("Doğan","Kılıç", 10, new ParcelableObjectWithInnerClass.Address("Ankara", "YeniMahalle", 3));
        Intent i = new Intent(MainActivity.this, SecondaryActivity.class);
        i.putExtra("User", parcelableObject);
        i.putExtra("Type", TransitionType.PARCEL_OBJECT_WITH_INNER_CLASS);
        startActivity(i);
    }

    public void parcelObjectWithInnerEnum(View v)
    {
        ParcelableObjectWithInnerEnum parcelableObjectWithInnerEnum = new ParcelableObjectWithInnerEnum(ParcelableEnum.VALUE);
        Intent i = new Intent(MainActivity.this, SecondaryActivity.class);
        i.putExtra("Enum", parcelableObjectWithInnerEnum);
        i.putExtra("Type", TransitionType.PARCEL_OBJECT_WITH_INNER_ENUM);
        startActivity(i);
    }

    public void parcelObjectWithInnerHashMap(View v)
    {
        HashMap<String, String> people = new HashMap<>();
        people.put("Doğan","Kılıç");
        people.put("Aras","Dağlı");
        people.put("Bedirvan","Nazlı");

        ParcelableObjectWithInnerHashMap parcelableObjectWithInnerHashMap = new ParcelableObjectWithInnerHashMap(people);
        Intent i = new Intent(MainActivity.this, SecondaryActivity.class);
        i.putExtra("People", parcelableObjectWithInnerHashMap);
        i.putExtra("Type", TransitionType.PARCEL_OBJECT_WITH_INNER_HASHMAP);
        startActivity(i);
    }

    public void serialize(View v)
    {
        SerializableObject serializableObject = new SerializableObject("Doğan","Kılıç", 10);
        Intent i = new Intent(MainActivity.this, SecondaryActivity.class);
        i.putExtra("User", serializableObject);
        i.putExtra("Type", TransitionType.SERIALIZE_OBJECT);
        startActivity(i);
    }

    public static class TransitionType
    {
        public static final int PARCEL_OBJECT = 1;
        public static final int PARCEL_ARRAY_LIST_OF_OBJECTS = 2;
        public static final int PARCEL_OBJECT_WITH_INNER_CLASS = 3;
        public static final int SERIALIZE_OBJECT = 4;
        public static final int PARCEL_OBJECT_WITH_INNER_ENUM = 5;
        public static final int PARCEL_OBJECT_WITH_INNER_HASHMAP = 6;



    }
}
