package com.dnkilic.dplayer;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

public class MusicPlayer extends Service implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {

    public static final String ACTION_START = "com.dnkilic.dplayer.INITIALIZE";
    public static final String ACTION_PLAY = "com.dnkilic.dplayer.PLAY";
    public static final String ACTION_STOP = "com.dnkilic.dplayer.STOP";
    public static final String ACTION_PAUSE = "com.dnkilic.dplayer.PAUSE";
    public static final String ACTION_FORWARD = "com.dnkilic.dplayer.FORWARD";
    public static final String ACTION_BACKWARD = "com.dnkilic.dplayer.BACKWARD";
    public static final String ACTION_FORWARD_LONG = "com.dnkilic.dplayer.FORWARD_LONG";
    public static final String ACTION_BACKWARD_LONG = "com.dnkilic.dplayer.BACKWARD_LONG";

    private static final int CLICK_IN_MILLIS = 5000;
    private static final int LONG_CLICK_IN_MILLIS = 10000;

    private MediaPlayer mPlayer = null;
    private int mCurrentPosition;
    private Uri mSelectedMusicUri;
    private String mediaName;
    private WifiManager.WifiLock wifiLock;
    private  Notification notification;

    private void startForeground()
    {
        RemoteViews remoteView = new RemoteViews(getPackageName(), R.layout.notification);
        remoteView.setImageViewResource(R.id.ivIcon, R.mipmap.ic_launcher);
        remoteView.setTextViewText(R.id.tvAppName, "Mobil Asistan");
        remoteView.setTextViewText(R.id.tvTitle, mediaName);
        remoteView.setImageViewResource(R.id.ibBackward, R.mipmap.ic_player_fast_backward);
        remoteView.setOnClickPendingIntent(R.id.ibBackward, makePendingIntent(ACTION_BACKWARD));
        remoteView.setImageViewResource(R.id.ibForward, R.mipmap.ic_player_fast_forward);
        remoteView.setOnClickPendingIntent(R.id.ibForward, makePendingIntent(ACTION_FORWARD));
        remoteView.setImageViewResource(R.id.ibPlayPause, R.mipmap.ic_player_pause);
        remoteView.setOnClickPendingIntent(R.id.ibPlayPause, makePendingIntent(ACTION_PAUSE));
        remoteView.setImageViewResource(R.id.ibStop, R.mipmap.ic_player_stop);
        remoteView.setOnClickPendingIntent(R.id.ibStop, makePendingIntent(ACTION_STOP));
        remoteView.setImageViewResource(R.id.ivSingleLine, android.R.color.white);

        notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setCustomContentView(remoteView)
                .setCustomBigContentView(remoteView)
                .setContentIntent(makeContentIntent())
                .build();

        startForeground(2000, notification);
    }

    private PendingIntent makePendingIntent(String broadcast)
    {
        Intent intent = new Intent(broadcast);
        return PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
    }

    private PendingIntent makeContentIntent()
    {
        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
        return PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, 0);
    }

    final BroadcastReceiver broadcastReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction();

            if (action.equals(ACTION_STOP)) {
                stopSelf();
            }
            else if(action.equals(ACTION_PAUSE)) {
                pausePlaying();
                onPlay(ACTION_PAUSE);

                RemoteViews remoteView = new RemoteViews(getPackageName(), R.layout.notification);
                remoteView.setImageViewResource(R.id.ivIcon, R.mipmap.ic_launcher);
                remoteView.setTextViewText(R.id.tvAppName, "Mobil Asistan");
                remoteView.setTextViewText(R.id.tvTitle, mediaName);
                remoteView.setImageViewResource(R.id.ibBackward, R.mipmap.ic_player_fast_backward);
                remoteView.setOnClickPendingIntent(R.id.ibBackward, makePendingIntent(ACTION_BACKWARD));
                remoteView.setImageViewResource(R.id.ibForward, R.mipmap.ic_player_fast_forward);
                remoteView.setOnClickPendingIntent(R.id.ibForward, makePendingIntent(ACTION_FORWARD));
                remoteView.setImageViewResource(R.id.ibPlayPause, R.mipmap.ic_player_play);
                remoteView.setOnClickPendingIntent(R.id.ibPlayPause, makePendingIntent(ACTION_PLAY));
                remoteView.setImageViewResource(R.id.ibStop, R.mipmap.ic_player_stop);
                remoteView.setOnClickPendingIntent(R.id.ibStop, makePendingIntent(ACTION_STOP));
                remoteView.setImageViewResource(R.id.ivSingleLine, android.R.color.white);

                notification = new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setCustomContentView(remoteView)
                        .setCustomBigContentView(remoteView)
                        .setContentIntent(makeContentIntent())
                        .build();

                startForeground(2000, notification);
            }
            else if(action.equals(ACTION_PLAY)) {
                onPlay(ACTION_PLAY);

                RemoteViews remoteView = new RemoteViews(getPackageName(), R.layout.notification);
                remoteView.setImageViewResource(R.id.ivIcon, R.mipmap.ic_launcher);
                remoteView.setTextViewText(R.id.tvAppName, "Mobil Asistan");
                remoteView.setTextViewText(R.id.tvTitle, mediaName);
                remoteView.setImageViewResource(R.id.ibBackward, R.mipmap.ic_player_fast_backward);
                remoteView.setOnClickPendingIntent(R.id.ibBackward, makePendingIntent(ACTION_BACKWARD));
                remoteView.setImageViewResource(R.id.ibForward, R.mipmap.ic_player_fast_forward);
                remoteView.setOnClickPendingIntent(R.id.ibForward, makePendingIntent(ACTION_FORWARD));
                remoteView.setImageViewResource(R.id.ibPlayPause, R.mipmap.ic_player_pause);
                remoteView.setOnClickPendingIntent(R.id.ibPlayPause, makePendingIntent(ACTION_PAUSE));
                remoteView.setImageViewResource(R.id.ibStop, R.mipmap.ic_player_stop);
                remoteView.setOnClickPendingIntent(R.id.ibStop, makePendingIntent(ACTION_STOP));
                remoteView.setImageViewResource(R.id.ivSingleLine, android.R.color.white);

                notification = new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setCustomContentView(remoteView)
                        .setCustomBigContentView(remoteView)
                        .setContentIntent(makeContentIntent())
                        .build();

                startForeground(2000, notification);
            }
            else if(action.equals(ACTION_FORWARD)) {
                onPlay(ACTION_FORWARD);
            }
            else if(action.equals(ACTION_BACKWARD)) {
                onPlay(ACTION_BACKWARD);
            }
        }
    };

    @Override
    public void onCreate() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_STOP);
        intentFilter.addAction(ACTION_PAUSE);
        intentFilter.addAction(ACTION_PLAY);
        intentFilter.addAction(ACTION_FORWARD);
        intentFilter.addAction(ACTION_BACKWARD);
        registerReceiver(broadcastReceiver, intentFilter);

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(intent != null)
        {
            if(intent.getAction() != null && intent.getAction().equals(ACTION_START))
            {
                mSelectedMusicUri = intent.getData();
                mediaName = intent.getStringExtra("CHANNEL_NAME");
            }

            onPlay(intent.getAction());
        }

        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onPlay(String state) {
        switch (state) {
            case ACTION_START:
                startPlaying();
                break;
            case ACTION_PLAY:
                resumePlaying();
                break;
            case ACTION_PAUSE:
                pausePlaying();
                break;
            case ACTION_STOP:
                stopPlaying();
                break;
            case ACTION_FORWARD:
                forwardPlaying(false);
                break;
            case ACTION_BACKWARD:
                backwardPlaying(false);
                break;
            case ACTION_FORWARD_LONG:
                forwardPlaying(true);
                break;
            case ACTION_BACKWARD_LONG:
                backwardPlaying(true);
                break;
        }
    }

    private void forwardPlaying(boolean isLongClick)
    {
        if (mPlayer != null) {
            try {
                mCurrentPosition = mPlayer.getCurrentPosition();

                int duration = mPlayer.getDuration();
                if(isLongClick)
                {
                    if(duration - mCurrentPosition < LONG_CLICK_IN_MILLIS)
                    {
                        mPlayer.seekTo(duration);
                    }
                    else
                    {
                        mPlayer.seekTo(mCurrentPosition + LONG_CLICK_IN_MILLIS);
                    }
                }
                else
                {
                    if(duration - mCurrentPosition < CLICK_IN_MILLIS)
                    {
                        mPlayer.seekTo(duration);
                    }
                    else
                    {
                        mPlayer.seekTo(mCurrentPosition + CLICK_IN_MILLIS);
                    }
                }
            } catch (Exception e) {
                Log.e(MainActivity.TAG, "MusicPlayer forwardPlaying() failed");
                e.printStackTrace();
            }
        }
    }

    private void backwardPlaying(boolean isLongClick)
    {
        if (mPlayer != null) {
            try {
                mCurrentPosition = mPlayer.getCurrentPosition();

                if(isLongClick)
                {
                    if(mCurrentPosition < LONG_CLICK_IN_MILLIS)
                    {
                        mPlayer.seekTo(0);
                    }
                    else
                    {
                        mPlayer.seekTo(mCurrentPosition - LONG_CLICK_IN_MILLIS);
                    }
                }
                else
                {
                    if(mCurrentPosition < CLICK_IN_MILLIS)
                    {
                        mPlayer.seekTo(0);
                    }
                    else
                    {
                        mPlayer.seekTo(mCurrentPosition - CLICK_IN_MILLIS);
                    }
                }
            } catch (Exception e) {
                Log.e(MainActivity.TAG, "MusicPlayer backwardPlaying() failed");
                e.printStackTrace();
            }
        }
    }

    private void startPlaying() {
        mPlayer = new MediaPlayer();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        mPlayer.setOnCompletionListener(this);
        mPlayer.setOnPreparedListener(this);
        mPlayer.setOnErrorListener(this);

        try {
            mPlayer.setDataSource(getApplicationContext(), mSelectedMusicUri);
            mPlayer.prepare();

            wifiLock = ((WifiManager) getSystemService(Context.WIFI_SERVICE))
                    .createWifiLock(WifiManager.WIFI_MODE_FULL, "mylock");

            wifiLock.acquire();

        } catch (Exception e) {
            Log.e(MainActivity.TAG, "MusicPlayer initializeAndPlay() failed");
            e.printStackTrace();
        }

        startForeground();
    }

    private void pausePlaying() {
        if (mPlayer != null) {
            try {
                mPlayer.pause();
                mCurrentPosition = mPlayer.getCurrentPosition();

                wifiLock.release();
            } catch (Exception e) {
                Log.e(MainActivity.TAG, "MusicPlayer pauseMusic() failed");
                e.printStackTrace();
            }
        }
    }

    private void resumePlaying() {
        if (mPlayer != null) {
            try {
                mPlayer.seekTo(mCurrentPosition);
                mPlayer.start();

                wifiLock.acquire();
            } catch (Exception e) {
                Log.e(MainActivity.TAG, "MusicPlayer playMusic() failed");
                e.printStackTrace();
            }
        }
    }

    private void stopPlaying() {

        if (mPlayer != null) {

            int currentPosition = 0;

            try {
                currentPosition = mPlayer.getCurrentPosition();
                mPlayer.stop();
                mPlayer.release();
                mPlayer = null;

                wifiLock.release();
            } catch (Exception e) {
                Log.e(MainActivity.TAG, "MusicPlayer stopPlaying() failed");
                e.printStackTrace();
            }

            stopForeground(true);
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.i(MainActivity.TAG, "MediaPlayer.OnCompletionListener media play is done.");
        stopSelf();
        stopForeground(true);
    }

    @Override
    public void onDestroy() {
        if (mPlayer != null)
        {
            mPlayer.release();
            mPlayer = null;
        }

        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }
}
