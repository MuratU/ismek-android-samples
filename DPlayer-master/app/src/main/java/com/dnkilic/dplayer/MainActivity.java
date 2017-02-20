package com.dnkilic.dplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "dPlayer";

    private ViewManager viewManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewManager = new ViewManager(this);
    }

    public void start(View v)
    {
        MusicRepositoryMediator adapter = new MusicRepositoryMediator(this);

        switch(adapter.searchMusic(""))
        {
            case MusicRepositoryMediator.EMPTY_MUSIC_LIST:
                Toast.makeText(getApplicationContext(), "Empty", Toast.LENGTH_LONG).show();
                break;
            case MusicRepositoryMediator.NO_MUSIC_WITH_SELECTED_CRITERIA:
                Toast.makeText(getApplicationContext(), "No music with criteria.", Toast.LENGTH_LONG).show();
                break;
            case MusicRepositoryMediator.QUERY_MUSIC_LIST_ERROR:
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                break;
            case MusicRepositoryMediator.SUCCESSFUL_PLAY_MUSIC:
                Toast.makeText(getApplicationContext(), "Successfull", Toast.LENGTH_LONG).show();
                viewManager.refreshWithMediaPlay(adapter.getSelectedMusic());
                break;
            case MusicRepositoryMediator.ERROR_PLAY_MUSIC:
                Toast.makeText(getApplicationContext(), "Error while playing", Toast.LENGTH_LONG).show();
        }
    }
}
