package com.dnkilic.application37;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sleepyButton(View c)
    {
        final TextView tvResult = (TextView) findViewById(R.id.tvResult);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 10000);

        Thread thread = new Thread()
        {
            @Override
            public void run() {
                try {
                    sleep(10000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvResult.setText("Merhaba Dünya");
                        }
                    });
                } catch (InterruptedException e) {
                }
            }
        };
        thread.start();



        ScheduledThreadPoolExecutor sch = (ScheduledThreadPoolExecutor)
                Executors.newScheduledThreadPool(5);

        // Create a task for one-shot execution using schedule()
        Runnable oneShotTask = new Runnable(){
            @Override
            public void run() {
                System.out.println("\t oneShotTask Execution Time: "
                        + new Date());
            }
        };

        // Create another task
        Runnable delayTask = new Runnable(){
            @Override
            public void run() {
                try{
                    System.out.println("\t delayTask Execution Time: "
                            + new Date());
                    Thread.sleep(10 * 1000);
                    System.out.println("\t delayTask End Time: "
                            + new Date());
                }catch(Exception e){

                }
            }
        };

        // And yet another
        Runnable periodicTask = new Runnable(){
            @Override
            public void run() {
                try{
                    System.out.println("\t periodicTask Execution Time: "
                            + new Date());
                    Thread.sleep(10 * 1000);
                    System.out.println("\t periodicTask End Time: "
                            + new Date());
                }catch(Exception e){

                }
            }
        };

        System.out.println("Submission Time: " + new Date());
        ScheduledFuture<?> oneShotFuture = sch.schedule(oneShotTask, 5, TimeUnit.SECONDS);
        ScheduledFuture<?> delayFuture = sch.scheduleWithFixedDelay(delayTask, 5, 5, TimeUnit.SECONDS);
        ScheduledFuture<?> periodicFuture = sch.scheduleAtFixedRate(periodicTask, 5, 5, TimeUnit.SECONDS);


        ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);

        long period = 2000; // the period between successive executions
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("İki saniyede bir");
            }
        }, 0, period, TimeUnit.MILLISECONDS);

        long delay = 3000; //the delay between the termination of one execution and the commencement of the next
        exec.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("Üç saniyede bir");
            }
        }, 0, delay, TimeUnit.MILLISECONDS);


        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Timer : " + counter);
                counter++;
            }

        }, 0, 2000);
    }
}
