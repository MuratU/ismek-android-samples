package com.dnkilic.applicatin32;

import android.content.Intent;
import android.media.Image;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS)
                {
                    tts.setLanguage(Locale.getDefault());
                    tts.setSpeechRate((float) 0.3);
                    //TODO ben bu uygulamada ses sentezlemeyi şu andan itibaren kullanabilirim
                    Toast.makeText(getApplicationContext(), "ben bu uygulamada ses sentezlemeyi şu andan itibaren kullanabilirim", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageView ivBanana = (ImageView) findViewById(R.id.imageView);
        if(ivBanana != null)
        {
            ivBanana.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, FruitActivity.class);
                    i.putExtra("Fruit", "Banana");
                    startActivity(i);
                }
            });

            ivBanana.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    tts.speak("Muz'a tıkladın", TextToSpeech.QUEUE_ADD, null);
                    return false;
                }
            });
        }

        ImageView ivKiraz = (ImageView) findViewById(R.id.imageView2);
        if(ivKiraz != null)
        {
            ivKiraz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, FruitActivity.class);
                    i.putExtra("Fruit", "Cherry");
                    startActivity(i);
                }
            });
        }



        ImageView ivCilek = (ImageView) findViewById(R.id.imageView3);
        if(ivCilek != null)
        {
            ivCilek.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, FruitActivity.class);
                    i.putExtra("Fruit", "Strawberry");
                    startActivity(i);
                }
            });
        }


        ImageView ivKaradut = (ImageView) findViewById(R.id.imageView4);
        if(ivKaradut != null)
        {
            ivKaradut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, FruitActivity.class);
                    i.putExtra("Fruit", "Blackberry");
                    startActivity(i);
                }
            });
        }
    }
}
