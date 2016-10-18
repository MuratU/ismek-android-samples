package com.dnkilic.androiddatabase;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity implements DatabaseErrorHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("database oluşturmak/açmak");
    }

    /* FLAGS
    *  OPEN_READWRITE
    *  OPEN_READONLY
    *  CREATE_IF_NECESSARY*/

    public void openDatabaseWithErrorHandling(View v)
    {
        SQLiteDatabase mydatabase = SQLiteDatabase.openDatabase(getApplicationContext().getFilesDir() + "/db1", null, SQLiteDatabase.CREATE_IF_NECESSARY, this);
        String path = mydatabase.getPath();

        File file = new File(getApplicationContext().getFilesDir() +"");
        for (String strFile : file.list())
        {
            Log.i("Dosyalar", " File name : " + strFile);
        }

        openInsertionActivity("db1");
    }

    public void openDatabaseWithoutErrorHandling(View v)
    {
        SQLiteDatabase mydatabase = SQLiteDatabase.openDatabase(getApplicationContext().getFilesDir() + "/db2", null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String path = mydatabase.getPath();

        File file = new File(getApplicationContext().getFilesDir() +"");
        for (String strFile : file.list())
        {
            Log.i("Dosyalar", " File name : " + strFile);
        }

        openInsertionActivity("db2");
    }

    public void openOrCreateDatabaseWithErrorHandling(View v)
    {
        SQLiteDatabase mydatabase = SQLiteDatabase.openOrCreateDatabase(getApplicationContext().getFilesDir() + "/db3", null, this);
        String path = mydatabase.getPath();

        File file = new File(getApplicationContext().getFilesDir() +"");
        for (String strFile : file.list())
        {
            Log.i("Dosyalar", " File name : " + strFile);
        }

        openInsertionActivity("db3");
    }

    public void openOrCreateDatabaseWithoutErrorHandling(View v)
    {
        SQLiteDatabase mydatabase = SQLiteDatabase.openOrCreateDatabase(getApplicationContext().getFilesDir() + "/db4", null);
        String path = mydatabase.getPath();

        File file = new File(getApplicationContext().getFilesDir() +"");
        for (String strFile : file.list())
        {
            Log.i("Dosyalar", " File name : " + strFile);
        }

        openInsertionActivity("db4");
    }

    public void openOrCreateDatabaseWithoutErrorHandlingWithFile(View v)
    {
        SQLiteDatabase mydatabase = SQLiteDatabase.openOrCreateDatabase(new File(getApplicationContext().getFilesDir() + "/db5"), null);
        String path = mydatabase.getPath();

        File file = new File(getApplicationContext().getFilesDir() +"");
        for (String strFile : file.list())
        {
            Log.i("Dosyalar", " File name : " + strFile);
        }

        openInsertionActivity("db5");
    }

    private void openInsertionActivity(String dbName)
    {
        Intent i = new Intent(MainActivity.this, InsertionActivity.class);
        i.putExtra("DatabaseName" , dbName);
        startActivity(i);
    }

    @Override
    public void onCorruption(SQLiteDatabase dbObj) {

    }
}
