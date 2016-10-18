package com.dnkilic.shoppinglist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "shoppingList.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SHOPPING_LIST = "ShoppingList";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_COUNT = "count";

    private static final String CREATE_SHOPPING_LIST_DATABASE = "CREATE TABLE "
            + TABLE_SHOPPING_LIST + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_CONTENT + " TEXT NOT NULL" +
            COLUMN_COUNT + " TEXT NOT NULL" + ")";


    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(CREATE_SHOPPING_LIST_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPPING_LIST);
        onCreate(db);
    }

    public ShoppingItem findProduct(String itemName) {
        String query = "Select * FROM " + TABLE_SHOPPING_LIST + " WHERE " + COLUMN_CONTENT + " =  \"" + itemName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        ShoppingItem item = new ShoppingItem();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            item.setId(Integer.parseInt(cursor.getString(0)));
            item.setItemName(cursor.getString(1));
            item.setItemQuantity(cursor.getString(2));
            cursor.close();
        } else {
            item = null;
        }

        db.close();

        return item;
    }

    public void addItem(ShoppingItem item) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_CONTENT, item.getItemName());
        values.put(COLUMN_COUNT, item.getItemQuantity());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_SHOPPING_LIST, null, values);
        db.close();
    }

    public boolean deleteItem(String itemName) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_SHOPPING_LIST + " WHERE " + COLUMN_CONTENT + " =  \"" + itemName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        ShoppingItem item = new ShoppingItem();

        if (cursor.moveToFirst()) {
            item.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_SHOPPING_LIST, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(item.getId()) });
            cursor.close();
            result = true;
        }

        db.close();
        return result;
    }


}
