package com.dnkilic.specialrecycleview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;

import io.fabric.sdk.android.Fabric;
import retrofit2.Call;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogEventCallback {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "dLtf8ibEOX4GVIaZOkCKALEqs";
    private static final String TWITTER_SECRET = "S8qWixhBpY9mtzIbbO8dV8UtshobpxCNDMXdyYsB4yfcH6Dy5v";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<AssistantDialog> mItems;

    private String deneme = "saskklas ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);

        initializeViews();
    }

    public void music(View v)
    {
        addItem(new AssistantDialog(DialogAdapter.TYPE_PLAYER));
    }


    public void sent(View v)
    {
        addItem(new AssistantDialog("sent sent", DialogAdapter.TYPE_DIALOG_SENT));
    }

    public void received(View v)
    {
        addItem(new AssistantDialog("received received", DialogAdapter.TYPE_DIALOG_RECEIVED));
    }

    public void dictation(View v)
    {
        addItem(new AssistantDialog("dictation dictation", DialogAdapter.TYPE_MESSAGE));
    }

    public void tweet(View v)
    {
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        StatusesService statusesService = twitterApiClient.getStatusesService();
        Call<List<Tweet>> call = statusesService.homeTimeline(10, null, null, null, null, null, null);
        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {
                System.out.print("ds");


                List<Tweet> deneme = result.data;

                List<Long> ids;
                ids = new ArrayList<>();

                for (Tweet item : deneme) {
                    ids.add(item.getId());
                }

                addItem(new AssistantDialog(ids, DialogAdapter.TYPE_TWEET));
            }

            @Override
            public void failure(TwitterException exception) {

            }
        });
    }

    public void update(View v)
    {
        // last item update edilmeli

        if(mItems != null && mItems.size() > 0)
        {
            AssistantDialog dialog = mItems.get(mItems.size() - 1);

            if(dialog.getDialogType() == DialogAdapter.TYPE_MESSAGE)
            {
                deneme = deneme + " " + "merhaba";
                AssistantDialog ad = new AssistantDialog(deneme, DialogAdapter.TYPE_MESSAGE);
                mItems.set(mItems.size() - 1, ad);
                mAdapter.notifyDataSetChanged();
            }

        }
    }

    public void addItem(final AssistantDialog dialog)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mItems.add(dialog);
                mAdapter.notifyDataSetChanged();
            }
        });

        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.smoothScrollToPosition(mAdapter.getItemCount() - 1);
            }
        });
    }

    public void initializeViews() {

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mItems = new ArrayList<>();

        mAdapter = new DialogAdapter(mItems, this, this);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onBtnSendClick() {
        Toast.makeText(getApplicationContext(), "Noldu bu gönlüm", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBtnCancelClick() {
        Toast.makeText(getApplicationContext(), "Noldu bu gönlüm2", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBtnCancelPlayerClick() {
        Toast.makeText(getApplicationContext(), "Müzik bitti.", Toast.LENGTH_LONG).show();
    }
}
