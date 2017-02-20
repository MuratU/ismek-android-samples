package com.dnkilic.multistreaming;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.Wsdl2Code.WebServices.MyService.DataIdPair;
import com.Wsdl2Code.WebServices.MyService.MyService;
import com.Wsdl2Code.WebServices.MyService.VectorByte;

public class ClientActivity extends AppCompatActivity {

    private AudioTrack track = null;
    private int bufferSize = 1440;
    private String segmentId = "0";
    private String TAG = "DEVICE_RECEIVER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle("Client");
        }

        showMessageDialog("Yayını başlatmak için butona tıklayın.");
    }

    public void listen(View v)
    {
        Log.d(TAG, "======== Listen Button Clicked ==========");
        startReceiverThread();
    }

    private void startReceiverThread()
    {
        Thread m_thread = new Thread(new Runnable() {
            public void run() {
                run_loop();
            }
        });
        m_thread.start();
    }

    public AudioTrack initializeAudioDevice(AudioTrack track)
    {
        Log.d(TAG, "===== Initialize AudioTrack ====");
        int m_bufferSize = AudioTrack.getMinBufferSize(8000,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT);

        if (m_bufferSize != AudioTrack.ERROR_BAD_VALUE)
        {
            track = new AudioTrack(AudioManager.STREAM_MUSIC, 8000,
                    AudioFormat.CHANNEL_OUT_MONO,
                    AudioFormat.ENCODING_PCM_16BIT, m_bufferSize,
                    AudioTrack.MODE_STREAM);

            if (track.getState() == AudioTrack.STATE_UNINITIALIZED) {
                Log.e(TAG, "===== AudioTrack Uninitialized =====");
                return null;
            }
        }
        return track;
    }

    public void run_loop ()
    {
        if(track != null)
        {
            if (AudioTrack.STATE_INITIALIZED == track.getState()){
                track.stop();
                track.release();
            }
            return;
        }

        /** ======= Initialize AudioRecord and AudioTrack ======== **/

        track = initializeAudioDevice(track);
        if (track == null) {
            Log.e(TAG, "======== AudioDevice : Returned Error! ========== ");
            return;
        }

        if ((AudioTrack.STATE_INITIALIZED == track.getState()))
        {
            Log.d(TAG, "========= AudioDevice Initialized... =========");
        }
        else
        {
            Log.d(TAG, "==== Initilazation failed for AudioDevice =====");
            return;
        }


        final MyService svc = new MyService("http://sesteksynth0001.westeurope.cloudapp.azure.com:1544/HelloService");

        while(true)
        {
            DataIdPair pair = svc.GetLiveStream(segmentId);
            if(pair == null)
            {
                showMessageDialog("Yayın bulunamadı.");
                return;
            }

            VectorByte bytes = pair.data;
            if(bytes != null)
            {
                Log.v(TAG, "Data Size : " + pair.data.size());
                Log.v(TAG, "Buffer Size : " + bufferSize);
                segmentId = pair.segmentID;
                Log.w(TAG, "Segment Id : " + pair.segmentID);
                track.play();
                track.write(bytes.toBytes(), 0, bufferSize);
            }
        }
    }

    private void showMessageDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogStyle);
        builder.setMessage(message);
        builder.setPositiveButton("TAMAM", null);
        builder.show();
    }
}
