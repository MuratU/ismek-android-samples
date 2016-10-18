package com.dnkilic.shoppinglist;

import android.app.Activity;
import android.util.Log;

public class ShoppingManager {

    private Activity mAct;


    public ShoppingManager(Activity act)
    {
        mAct = act;
    }

    public void insertItem (String itemName, String itemQuantity) {

        DatabaseHelper dbHandler = new DatabaseHelper(mAct, null, null, 1);

        ShoppingItem item = new ShoppingItem(itemName, itemQuantity);

        dbHandler.addItem(item);
    }

    public void queryItem (String itemName) {
        DatabaseHelper dbHandler = new DatabaseHelper(mAct, null, null, 1);

        ShoppingItem item =  dbHandler.findProduct(itemName);

        if (item != null)
        {
            Log.i("ShoppingList", "No Match Found");
        }
        else
        {
            Log.i("ShoppingList", "Item Found");
        }
    }

    public void deleteItem (String itemName) {
        DatabaseHelper dbHandler = new DatabaseHelper(mAct, null, null, 1);

        boolean deletionResult = dbHandler.deleteItem(itemName);

        if (deletionResult)
        {
            Log.i("ShoppingList", "Record deleted");
        }
        else
        {
            Log.i("ShoppingList", "Record not deleted");
        }
    }
}
