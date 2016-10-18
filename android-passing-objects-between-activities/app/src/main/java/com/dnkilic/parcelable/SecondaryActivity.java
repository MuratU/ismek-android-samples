package com.dnkilic.parcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SecondaryActivity extends AppCompatActivity {

    //Parcelable
    //Serializable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        Intent intent = getIntent();

        switch (intent.getIntExtra("Type", 0))
        {
            case MainActivity.TransitionType.PARCEL_OBJECT:
                ParcelableObject object = intent.getExtras().getParcelable("User");
                break;
            case MainActivity.TransitionType.PARCEL_ARRAY_LIST_OF_OBJECTS:
                ArrayList aarayListOfObject = (ArrayList) intent.getParcelableArrayListExtra("User");
                break;
            case MainActivity.TransitionType.PARCEL_OBJECT_WITH_INNER_CLASS:
                ParcelableObjectWithInnerClass objectWithInnerClass = intent.getExtras().getParcelable("User");
                break;
            case MainActivity.TransitionType.SERIALIZE_OBJECT:
                SerializableObject serializableObject = (SerializableObject) intent.getExtras().getSerializable("User");
                String deee = "34234242";
            case MainActivity.TransitionType.PARCEL_OBJECT_WITH_INNER_HASHMAP:
                ParcelableObjectWithInnerHashMap objectWithHashMap =  intent.getExtras().getParcelable("People");
                String deneme1 = "34234242";
            case MainActivity.TransitionType.PARCEL_OBJECT_WITH_INNER_ENUM:
                ParcelableObjectWithInnerEnum objectWithEnum =  intent.getExtras().getParcelable("Enum");
                String deneme2 = "34234242";
            default:
                String deee2 = "34234242";
                break;
        }
    }
}
