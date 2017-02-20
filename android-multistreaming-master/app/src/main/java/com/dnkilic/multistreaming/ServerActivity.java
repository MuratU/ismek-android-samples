package com.dnkilic.multistreaming;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.Wsdl2Code.WebServices.MyService.MyService;
import com.Wsdl2Code.WebServices.MyService.VectorByte;

import java.io.ByteArrayOutputStream;

public class ServerActivity extends AppCompatActivity {

    private String TAG = "DEVICE_STREAMER";
    private boolean isRunning = true;
    private Thread m_thread;               /* Thread for running the Loop */

    private AudioRecord recorder = null;

    int bufferSize = 1440;                  /* Buffer for recording data */
    byte buffer[] = new byte[bufferSize];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);

        getSupportActionBar().setTitle("Server");
        showFirstMessageDialog();
    }

    public void serve(View v)
    {
        Log.d(TAG, "======== Serve Button Pressed ==========");
        isRunning = true;
        do_loopback(isRunning);
    }

    private void do_loopback(final boolean flag)
    {
        m_thread = new Thread(new Runnable() {
            public void run() {
                run_loop(flag);
            }
        });
        m_thread.start();
    }


    public AudioRecord findAudioRecord (AudioRecord recorder)
    {
        Log.d(TAG, "===== Initializing AudioRecord API =====");
        int m_bufferSize = AudioRecord.getMinBufferSize(8000,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT);

        if (m_bufferSize != AudioRecord.ERROR_BAD_VALUE)
        {
            recorder = new AudioRecord(MediaRecorder.AudioSource.MIC, 8000,
                    AudioFormat.CHANNEL_IN_MONO,
                    AudioFormat.ENCODING_PCM_16BIT, m_bufferSize);

            if (recorder.getState() == AudioRecord.STATE_UNINITIALIZED) {
                Log.e(TAG, "====== AudioRecord UnInitilaised ====== ");
                return null;
            }
        }
        return recorder;
    }

    public void run_loop (boolean isRunning)
    {

        if (isRunning == false) {
            Log.d(TAG, "=====  Stop Button is pressed ===== ");

            if (AudioRecord.STATE_INITIALIZED == recorder.getState()){
                recorder.stop();
                recorder.release();
            }

            return;
        }


        /** ======= Initialize AudioRecord and AudioTrack ======== **/
        recorder = findAudioRecord(recorder);
        if (recorder == null) {
            Log.e(TAG, "======== findAudioRecord : Returned Error! =========== ");
            return;
        }


        if ((AudioRecord.STATE_INITIALIZED == recorder.getState()))
        {
            recorder.startRecording();
            Log.d(TAG, "========= Recorder Started... =========");
        }
        else
        {
            Log.d(TAG, "==== Initilazation failed for AudioRecord or AudioTrack =====");
            return;
        }

        /** ------------------------------------------------------ **/

        /* Recording and Playing in chunks of 320 bytes */
        int readed = 0;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        int totalByteCount = 0;

        while (isRunning == true)
        {
            Log.i(TAG, "BufferSize : " + bufferSize);
            Log.i(TAG, "Buffer : " + buffer.length);

            readed = recorder.read(buffer, 0, bufferSize);
            totalByteCount += readed;

            Log.i(TAG, "TotalByteCount : " + totalByteCount);

            Log.i(TAG, "Readed : " + readed);
            Log.i(TAG, "Buffer : " + buffer.length);

            try {
                outputStream.write(buffer, 0, readed);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(totalByteCount >= 1440)
            {
                MyService svc = new MyService("http://sesteksynth0001.westeurope.cloudapp.azure.com:1544/HelloService");
                svc.SendLiveStream(new VectorByte(outputStream.toByteArray()));
                outputStream.reset();
                totalByteCount = 0;
            }
        }

        Log.i(TAG, "Loopback exit");
        return;
    }

    private void showFirstMessageDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogStyle);
        builder.setMessage("Yayını başlatmak için butona tıklayın.");
        builder.setPositiveButton("TAMAM", null);
        builder.show();
    }
}
