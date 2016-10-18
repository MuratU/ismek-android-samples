package com.dnkilic.diyalog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<Dialog> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);


        mRecyclerView.setLayoutManager(mLayoutManager);

        mItems = new ArrayList<>();

        mItems.add(new Dialog("DenemeArtık şifrenizi kaydetmicem bana ne bana ne abnananas desenee selam nasılsın merhaba", DialogType.TYPE_DIALOG_RECEIVED));
        mItems.add(new Dialog("Deneme2Artık şifrenizi kaydetmicem bana ne bana ne abnananas desenee selam nasılsın merhaba", DialogType.TYPE_DIALOG_RECEIVED));
        mItems.add(new Dialog("Dördüncü elemanArtık şifrenizi kaydetmicem bana ne bana ne abnananas desenee selam nasılsın merhaba", DialogType.TYPE_DIALOG_SENT));

        mItems.add(new Dialog("Bir hata oluştuArtık şifrenizi kaydetmicem bana ne bana ne abnananas desenee selam nasılsın merhaba", DialogType.TYPE_MESSAGE_ERROR));
        mItems.add(new Dialog("Artık şifrenizi kaydetmicem bana ne bana ne abnananas desenee selam nasılsın merhaba", DialogType.TYPE_MESSAGE_INFORMATION));


        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new DialogAdapter(mItems, this);

        mRecyclerView.setAdapter(mAdapter);
    }

    public void sent(View v)
    {
        addItem(new Dialog("I sent n message", DialogType.TYPE_DIALOG_SENT));
    }

    public void receive(View v)
    {
        addItem(new Dialog("I receive a message", DialogType.TYPE_DIALOG_RECEIVED));
    }

    public void error(View v)
    {
        addItem(new Dialog("DenemeArtık şifrenizi kaydetmicem bana ne bana ne abnananas desenee selam nasılsın merhaba", DialogType.TYPE_MESSAGE_ERROR));
    }

    private void addItem(Dialog dialog)
    {
        mItems.add(dialog);
        mAdapter.notifyDataSetChanged();
    }
}
