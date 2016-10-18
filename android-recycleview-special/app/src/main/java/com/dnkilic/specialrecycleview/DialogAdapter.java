package com.dnkilic.specialrecycleview;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;

import java.util.ArrayList;
import java.util.List;

public class DialogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_DIALOG_SENT = 0;
    public static final int TYPE_DIALOG_RECEIVED = 1;
    public static final int TYPE_MESSAGE_ERROR = 2;
    public static final int TYPE_INFORMATION = 3;
    public static final int TYPE_PHOTO = 5;
    public static final int TYPE_NEWS = 6;
    public static final int TYPE_MESSAGE = 7;
    public static final int TYPE_TWEET = 8;
    public static final int TYPE_PLAYER = 9;

    private ArrayList<AssistantDialog> mItems;
    private DisplayMetrics mMetrics;
    private DialogEventCallback mCallback;
    private Activity mActivity;

    public DialogAdapter(ArrayList<AssistantDialog> items, Activity activity, DialogEventCallback callback) {
        mItems = items;
        mMetrics = new DisplayMetrics();
        mCallback = callback;
        mActivity = activity;
        activity.getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {

            case TYPE_DIALOG_SENT:
                view = inflater.inflate(R.layout.dialog_item_sent, parent, false);
                LinearLayout.LayoutParams lpSent = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f);
                lpSent.setMargins(mMetrics.widthPixels/3, mMetrics.widthPixels/100, 0, 0);
                view.setLayoutParams(lpSent);
                viewHolder = new DialogViewHolderSent(view);
                break;
            case TYPE_DIALOG_RECEIVED:
                view = inflater.inflate(R.layout.dialog_item_received, parent, false);
                LinearLayout.LayoutParams lpReceived = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f);
                lpReceived.setMargins(0, mMetrics.widthPixels/100, mMetrics.widthPixels/3, 0);
                view.setLayoutParams(lpReceived);
                viewHolder = new DialogViewHolderReceived(view);
                break;
            case TYPE_MESSAGE_ERROR:
                view = inflater.inflate(R.layout.dialog_item_error, parent, false);
                viewHolder = new DialogViewHolderError(view);
                LinearLayout.LayoutParams lpError = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                lpError.setMargins(mMetrics.widthPixels/6,  mMetrics.widthPixels/100, mMetrics.widthPixels/6, 0);
                view.setLayoutParams(lpError);
                break;
            case TYPE_INFORMATION:
                view = inflater.inflate(R.layout.dialog_item_information, parent, false);
                viewHolder = new DialogViewHolderInformation(view);
                LinearLayout.LayoutParams lpInfo = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                lpInfo.setMargins(0, mMetrics.widthPixels / 100, 0, 0);
                view.setLayoutParams(lpInfo);
                break;
            case TYPE_PHOTO:
                view = inflater.inflate(R.layout.dialog_item_photo, parent, false);
                viewHolder = new DialogViewHolderPhoto(view);
                LinearLayout.LayoutParams lpPhoto = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                lpPhoto.setMargins(mMetrics.widthPixels / 6, mMetrics.widthPixels / 100, mMetrics.widthPixels / 6, 0);
                view.setLayoutParams(lpPhoto);
                break;
            case TYPE_NEWS:
                view = inflater.inflate(R.layout.dialog_item_news, parent, false);
                viewHolder = new DialogViewHolderNews(view);
                LinearLayout.LayoutParams lpNews = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                lpNews.setMargins(0, mMetrics.widthPixels / 100, 0, 0);
                view.setLayoutParams(lpNews);
                break;
            case TYPE_MESSAGE:
                view = inflater.inflate(R.layout.dialog_item_message, parent, false);
                viewHolder = new DialogViewHolderMessage(view, mCallback);
                LinearLayout.LayoutParams lpMessage = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                lpMessage.setMargins(0, mMetrics.widthPixels / 100, 0, 0);
                view.setLayoutParams(lpMessage);
                break;
            case TYPE_TWEET:
                view = inflater.inflate(R.layout.dialog_item_tweets, parent, false);
                viewHolder = new DialogViewHolderTweet(view);
                LinearLayout.LayoutParams lpInfo1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                lpInfo1.setMargins(0, mMetrics.widthPixels / 100, 0, 0);
                view.setLayoutParams(lpInfo1);
                break;
            case TYPE_PLAYER:
                view = inflater.inflate(R.layout.dialog_item_player, parent, false);
                viewHolder = new DialogViewHolderPlayer(view, mCallback);
                LinearLayout.LayoutParams lpPlayer = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                lpPlayer.setMargins(0, mMetrics.widthPixels / 100, 0, 0);
                view.setLayoutParams(lpPlayer);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType())
        {
            case TYPE_DIALOG_SENT:
                DialogViewHolderSent sentHolder = (DialogViewHolderSent) holder;
                sentHolder.getTextView().setText(mItems.get(position).getDialogText());
                break;
            case TYPE_DIALOG_RECEIVED:
                DialogViewHolderReceived receivedHolder = (DialogViewHolderReceived) holder;
                receivedHolder.getTextView().setText(mItems.get(position).getDialogText());
                break;
            case TYPE_MESSAGE_ERROR:
                DialogViewHolderError errorHolder = (DialogViewHolderError) holder;
                errorHolder.getTextView().setText(mItems.get(position).getDialogText());
                break;
            case TYPE_INFORMATION:
                DialogViewHolderInformation informationHolder = (DialogViewHolderInformation) holder;
                informationHolder.getTextView().setText(mItems.get(position).getDialogText());
                break;
            case TYPE_PHOTO:
                DialogViewHolderPhoto photoHolder = (DialogViewHolderPhoto) holder;
                photoHolder.getImageView().setBackgroundResource(mItems.get(position).getResourceId());
                photoHolder.getImageView().setAlpha(0.9f);
                break;
            case TYPE_NEWS:
                DialogViewHolderNews newsHolder = (DialogViewHolderNews) holder;

                newsHolder.getImageView().setAlpha(0.9f);


                newsHolder.getTextViewDescription().setText(mItems.get(position).getSecondDialogText());
                newsHolder.getTextViewTitle().setText(mItems.get(position).getFirstDialogText());
                break;
            case TYPE_MESSAGE:
                DialogViewHolderMessage holderMessage = (DialogViewHolderMessage) holder;
                holderMessage.getEditTextView().setText(mItems.get(position).getDialogText());

                break;

            case TYPE_TWEET:
                DialogViewHolderTweet holderTweets = (DialogViewHolderTweet) holder;
                final LinearLayout llTweetLayout = holderTweets.getLinearLayout();

                final List<Long> tweetIds = mItems.get(position).getTweetIds();
                TweetUtils.loadTweets(tweetIds, new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> result) {

                        for (Tweet tweet : result.data) {

                            TweetView tweetView = new TweetView(mActivity, tweet);
                            tweetView.setTweetActionsEnabled(true);
                            llTweetLayout.addView(tweetView);
                        }
                    }

                    @Override
                    public void failure(TwitterException exception) {
                    }
                });
                break;
            case TYPE_PLAYER:
                DialogViewHolderPlayer holderPlayer= (DialogViewHolderPlayer) holder;
                holderPlayer.getTextViewMusicName().setText("Muziko");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {

        int currentRowType = 0;

        switch (mItems.get(position).getDialogType())
        {
            case TYPE_DIALOG_SENT:
                currentRowType = TYPE_DIALOG_SENT;
                break;
            case TYPE_DIALOG_RECEIVED:
                currentRowType = TYPE_DIALOG_RECEIVED;
                break;
            case TYPE_MESSAGE_ERROR:
                currentRowType = TYPE_MESSAGE_ERROR;
                break;
            case TYPE_INFORMATION:
                currentRowType = TYPE_INFORMATION;
                break;
            case TYPE_PHOTO:
                currentRowType = TYPE_PHOTO;
                break;
            case TYPE_NEWS:
                currentRowType = TYPE_NEWS;
                break;
            case TYPE_MESSAGE:
                currentRowType = TYPE_MESSAGE;
                break;
            case TYPE_TWEET:
                currentRowType = TYPE_TWEET;
                break;
            case TYPE_PLAYER:
                currentRowType = TYPE_PLAYER;
                break;
        }

        return currentRowType;
    }

    public class DialogViewHolderError extends RecyclerView.ViewHolder {
        public TextView mErrorMessage;

        public DialogViewHolderError(View itemView) {
            super(itemView);
            mErrorMessage = (TextView) itemView.findViewById(R.id.tvErrorMessage);
        }

        public TextView getTextView() {
            return mErrorMessage;
        }
    }

    private class DialogViewHolderInformation extends RecyclerView.ViewHolder {
        public TextView mInformationMessage;

        public DialogViewHolderInformation(View itemView) {
            super(itemView);
            mInformationMessage = (TextView) itemView.findViewById(R.id.tvInformationMessage);
        }

        public TextView getTextView() {
            return mInformationMessage;
        }
    }

    private class DialogViewHolderTweet extends RecyclerView.ViewHolder {
        public LinearLayout mLlTweets;

        public DialogViewHolderTweet(View itemView) {
            super(itemView);
            mLlTweets = (LinearLayout) itemView.findViewById(R.id.llTweets);
        }

        public LinearLayout getLinearLayout() {
            return mLlTweets;
        }
    }

    private class DialogViewHolderMessage extends RecyclerView.ViewHolder {
        public EditText mEtMessageText;
        public Button mBtnSend;
        public Button mBtnCancel;

        public DialogViewHolderMessage(View itemView, final DialogEventCallback callback) {
            super(itemView);
            mEtMessageText = (EditText) itemView.findViewById(R.id.etMessage);
            mBtnSend = (Button) itemView.findViewById(R.id.btnSend);
            mBtnCancel = (Button) itemView.findViewById(R.id.btnCancel);

            mBtnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onBtnCancelClick();
                }
            });

            mBtnSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onBtnSendClick();
                }
            });
        }

        public EditText getEditTextView() {
            return mEtMessageText;
        }
    }

    public class DialogViewHolderReceived extends RecyclerView.ViewHolder {
        private TextView mDialogMessage;

        public DialogViewHolderReceived(View itemView) {
            super(itemView);
            mDialogMessage = (TextView) itemView.findViewById(R.id.tvMessage);
        }

        public TextView getTextView() {
            return mDialogMessage;
        }
    }

    public class DialogViewHolderSent extends RecyclerView.ViewHolder {
        public TextView mDialogMessage;

        public DialogViewHolderSent(View itemView) {
            super(itemView);
            mDialogMessage = (TextView) itemView.findViewById(R.id.tvMessage);
        }

        public TextView getTextView() {
            return mDialogMessage;
        }
    }

    public class DialogViewHolderPhoto extends RecyclerView.ViewHolder {
        public ImageView mDialogPhoto;

        public DialogViewHolderPhoto(View itemView) {
            super(itemView);
            mDialogPhoto = (ImageView) itemView.findViewById(R.id.ivPhoto);
        }

        public ImageView getImageView() {
            return mDialogPhoto;
        }
    }

    public class DialogViewHolderNews extends RecyclerView.ViewHolder {
        public ImageView mNewsImage;
        public TextView mNewsDescription;
        public TextView mNewsTitle;

        public DialogViewHolderNews(View itemView) {
            super(itemView);
            mNewsImage = (ImageView) itemView.findViewById(R.id.ivNews);
            mNewsDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            mNewsTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }

        public TextView getTextViewTitle() {
            return mNewsTitle;
        }

        public TextView getTextViewDescription() {
            return mNewsDescription;
        }

        public ImageView getImageView() {
            return mNewsImage;
        }
    }

    private class DialogViewHolderPlayer extends RecyclerView.ViewHolder {
        public TextView tvMusicName;
        public ImageButton btnCancelPlayer;

        public DialogViewHolderPlayer(View itemView, final DialogEventCallback callback) {
            super(itemView);
            tvMusicName = (TextView) itemView.findViewById(R.id.tvMusicName);
            btnCancelPlayer = (ImageButton) itemView.findViewById(R.id.btnCancelPlayer);

            btnCancelPlayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onBtnCancelPlayerClick();
                }
            });
        }

        public TextView getTextViewMusicName() {
            return tvMusicName;
        }
    }
}
