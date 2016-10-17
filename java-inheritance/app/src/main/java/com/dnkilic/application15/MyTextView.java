package com.dnkilic.application15;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by dnkilic on 26/03/16.
 */
public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setTextColor(int color) {
        super.setTextColor(getResources().getColor(android.R.color.black));
    }

    @Override
    public void setTextColor(ColorStateList colors) {
        super.setTextColor(colors);
    }
}
