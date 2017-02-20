package com.example.hafta_sonu.birds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LikeButton lb = (LikeButton) findViewById(R.id.lbButton);
        lb.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                Toast.makeText(getApplicationContext(), "Bana aşık oldu", Toast.LENGTH_SHORT).show();;
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                Toast.makeText(getApplicationContext(), "Benden nefret ediyor", Toast.LENGTH_SHORT).show();;

            }
        });
    }

    public void showBird(View vc)
    {
        EditText etSelectedType = (EditText) findViewById(R.id.etBirdType);
        String selectedBirdType = etSelectedType.getText().toString();

        EditText etSelectedColor = (EditText) findViewById(R.id.etSelectedColor);
        String selectedBackgroundColor = etSelectedColor.getText().toString();

        if(selectedBirdType.equalsIgnoreCase("Güvercin") || selectedBirdType.equalsIgnoreCase("Serçe")
                || selectedBirdType.equalsIgnoreCase("Bülbül") || selectedBirdType.equalsIgnoreCase("Saka")
                || selectedBirdType.equalsIgnoreCase("Sığırcık"))
        {
            if(selectedBackgroundColor.equalsIgnoreCase("mavi")
                    || selectedBackgroundColor.equalsIgnoreCase("kırmızı")
                    || selectedBackgroundColor.equalsIgnoreCase("yeşil")
                    || selectedBackgroundColor.equalsIgnoreCase("mor")
                    || selectedBackgroundColor.equalsIgnoreCase("turuncu")
                    || selectedBackgroundColor.equalsIgnoreCase("sarı")
                    || selectedBackgroundColor.equalsIgnoreCase("gri"))
            {
                selectedBackgroundColor = selectedBackgroundColor.toLowerCase();
            }
            else
            {
                selectedBackgroundColor = "siyah";
            }


            Bird myBird = new Bird(selectedBirdType);

            Intent i = new Intent(MainActivity.this, BirdActivity.class);
            i.putExtra("KUS_TURU" , myBird.getType());
            i.putExtra("ARKAPLAN_RENGİ" , selectedBackgroundColor);
            startActivity(i);

        }
        else
        {
            Toast.makeText(getApplicationContext(), "Adam gibi değer gir!", Toast.LENGTH_LONG).show();
        }
    }
}
