package com.dnkilic.diyalog;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class DialogViewHolderReceived extends RecyclerView.ViewHolder {

    private TextView mDialogMessage;

    public DialogViewHolderReceived(View itemView) {
        super(itemView);

        mDialogMessage = (TextView) itemView.findViewById(R.id.tvMessage);
    }

    public TextView getTextView()
    {
        return mDialogMessage;
    }
}
