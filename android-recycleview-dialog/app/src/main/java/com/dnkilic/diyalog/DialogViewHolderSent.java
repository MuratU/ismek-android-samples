package com.dnkilic.diyalog;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class DialogViewHolderSent extends RecyclerView.ViewHolder {

    public TextView mDialogMessage;

    public DialogViewHolderSent(View itemView) {
        super(itemView);

        mDialogMessage = (TextView) itemView.findViewById(R.id.tvMessage);



    }

    public TextView getTextView()
    {
        return mDialogMessage;
    }


}
