package com.dnkilic.application15;

import android.util.Log;

/**
 * Created by dnkilic on 26/03/16.
 */
public class Animal extends Alive{

    public Animal()
    {
        Log.i("Animal","Ben bir hayvanım");
    }

    public void whoIsMyAncestor()
    {
        Log.i("Animal","Sen bir hayvandan türedin");
    }
}
