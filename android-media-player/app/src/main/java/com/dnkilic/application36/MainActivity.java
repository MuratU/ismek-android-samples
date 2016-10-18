package com.dnkilic.application36;

import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        copyNecessaryFilesIfNotExist("b.mp3");
        copyNecessaryFilesIfNotExist("a.mp3");
        copyNecessaryFilesIfNotExist("c.mp3");


        initializeMediaPlayer("b.mp3");
    }

    private void initializeMediaPlayer(String mp3Name)
    {
        File music = new File(Environment.getExternalStorageDirectory() + "/Deneme", mp3Name);
        String filePath = music.getPath();
        mediaPlayer = new  MediaPlayer();
        try {
            mediaPlayer.setDataSource(filePath);
            mediaPlayer.prepare();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mix(View v)
    {
        if(mediaPlayer.isPlaying())
        {
           // mediaPlayer.stop();
           // mediaPlayer.release();
            mediaPlayer.reset();

            Random rand = new Random();

            if(rand.nextInt() % 2 == 0)
            {
                initializeMediaPlayer("c.mp3");
                mediaPlayer.start();
            }
            else
            {
                initializeMediaPlayer("a.mp3");
                mediaPlayer.start();
            }


        }

    }

    public void play(View v)
    {
        mediaPlayer.start();
    }

    public void stop(View v)
    {
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    public void pause(View v)
    {
        mediaPlayer.pause();
    }

    private void copyNecessaryFilesIfNotExist(String fileName) {

        File myFile = new File(Environment.getExternalStorageDirectory() + "/Deneme", fileName);
        if(!myFile.exists())
        {
            copyAssetFile(fileName);
        }
    }

    private void copyAssetFile(String filename) {

        FileOutputStream fos = null;
        InputStream is = null;

        try
        {
            int BUFFER_LEN = 1024;

            AssetManager assetManager = getResources().getAssets();
            is = assetManager.open(filename);

            File out = new File(Environment.getExternalStorageDirectory() + "/Deneme", filename);
            byte[] buffer = new byte[BUFFER_LEN];
            fos = new FileOutputStream(out);
            int read = 0;

            while ((read = is.read(buffer, 0, BUFFER_LEN)) >= 0) {
                fos.write(buffer, 0, read);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {

            if(fos != null && is != null)
            {
                try {
                    fos.flush();
                    fos.close();
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
