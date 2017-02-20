package com.dnkilic.dplayer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import static com.dnkilic.dplayer.MusicPlayer.ACTION_BACKWARD;
import static com.dnkilic.dplayer.MusicPlayer.ACTION_BACKWARD_LONG;
import static com.dnkilic.dplayer.MusicPlayer.ACTION_FORWARD;
import static com.dnkilic.dplayer.MusicPlayer.ACTION_FORWARD_LONG;
import static com.dnkilic.dplayer.MusicPlayer.ACTION_PAUSE;
import static com.dnkilic.dplayer.MusicPlayer.ACTION_PLAY;
import static com.dnkilic.dplayer.MusicPlayer.ACTION_START;
import static com.dnkilic.dplayer.MusicPlayer.ACTION_STOP;

public class ViewManager {

    private Activity activity;

    private TextView tvMusicName;
    private ImageButton btnCancelPlayer;
    private ImageButton btnPausePlayer;
    private ImageButton btnPlayPlayer;
    private ImageButton btnBackwardPlayer;
    private ImageButton btnForwardPlayer;

    public ViewManager(Activity activity) {
        this.activity = activity;

        tvMusicName = (TextView) activity.findViewById(R.id.tvMusicName);
        btnCancelPlayer = (ImageButton) activity.findViewById(R.id.btnCancelPlayer);
        btnPausePlayer = (ImageButton) activity.findViewById(R.id.btnPausePlayer);
        btnPlayPlayer = (ImageButton) activity.findViewById(R.id.btnPlayPlayer);
        btnBackwardPlayer = (ImageButton) activity.findViewById(R.id.btnBackwardPlayer);
        btnForwardPlayer = (ImageButton) activity.findViewById(R.id.btnForwardPlayer);

        btnCancelPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnCancelPlayerClick();

            }
        });

        btnPausePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPlayPlayer.setVisibility(View.VISIBLE);
                btnPausePlayer.setVisibility(View.GONE);
                onBtnPausePlayerClick();
            }
        });

        btnPlayPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPausePlayer.setVisibility(View.VISIBLE);
                btnPlayPlayer.setVisibility(View.GONE);
                onBtnPlayPlayerClick();
            }
        });

        btnBackwardPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBtnBackwardPlayerClick();
            }
        });

        btnBackwardPlayer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onBtnBackwardPlayerLongClick();
                return false;
            }
        });

        btnForwardPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnForwardPlayerClick();
            }
        });

        btnForwardPlayer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onBtnForwardPlayerLongClick();
                return false;
            }
        });
    }

    public void refreshWithMediaPlay(Media media) {

        tvMusicName.setText(media.getTitle());
        btnPausePlayer.setVisibility(View.VISIBLE);
        btnPlayPlayer.setVisibility(View.GONE);

        Intent intent = new Intent(activity, MusicPlayer.class);
        intent.setAction(ACTION_START);
        intent.setData(Uri.parse(media.getPath()));
        intent.putExtra("CHANNEL_NAME", media.getTitle());
        activity.startService(intent);


      /*  switch (media.getMediaType()) {
            case Media.MUSIC:
            case Media.VOICE_NOTE:



                mMusicPlayer = new MusicPlayer(activity, media.getPath(), this);
                mMusicPlayer.onPlay(MusicPlayer.START);
                break;
            case Media.RADIO:
                mMusicPlayer = new MusicPlayer(activity, this, media, Media.RADIO);
                mMusicPlayer.onPlayUrl(MusicPlayer.START);
                break;
            case Media.STORY_READER:
                mMusicPlayer = new MusicPlayer(activity, this, media, Media.STORY_READER);
                mMusicPlayer.onPlayUrl(MusicPlayer.START);
                break;
        }*/
    }


    public void onBtnBackwardPlayerClick() {
        Intent intent = new Intent(activity, MusicPlayer.class);
        intent.setAction(ACTION_BACKWARD);
        activity.startService(intent);
    }

    public void onBtnForwardPlayerClick() {

        Intent intent = new Intent(activity, MusicPlayer.class);
        intent.setAction(ACTION_FORWARD);
        activity.startService(intent);
    }

    public void onBtnBackwardPlayerLongClick() {
        Intent intent = new Intent(activity, MusicPlayer.class);
        intent.setAction(ACTION_BACKWARD_LONG);
        activity.startService(intent);
    }

    public void onBtnForwardPlayerLongClick() {
        Intent intent = new Intent(activity, MusicPlayer.class);
        intent.setAction(ACTION_FORWARD_LONG);
        activity.startService(intent);
    }

    public void onBtnPlayPlayerClick() {
        Intent intent = new Intent(activity, MusicPlayer.class);
        intent.setAction(ACTION_PLAY);
        activity.startService(intent);
    }


    public void onBtnCancelPlayerClick() {
        Intent intent = new Intent(activity, MusicPlayer.class);
        intent.setAction(ACTION_STOP);
        activity.startService(intent);

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onBtnPausePlayerClick() {
        Intent intent = new Intent(activity, MusicPlayer.class);
        intent.setAction(ACTION_PAUSE);
        activity.startService(intent);
    }
}
