package dnkilic.trash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class AnimalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);

        ImageView ivAnimal = (ImageView) findViewById(R.id.ivAnimal);

        String animalName = getIntent().getStringExtra("ANIMAL_NAME").toLowerCase();

        switch (animalName)
        {
            case "zürafa":
                ivAnimal.setImageResource(R.drawable.zurafa);
                break;
            case "maymun":
                ivAnimal.setImageResource(R.drawable.maymun);
                break;
            case "fil":
                ivAnimal.setImageResource(R.drawable.fil);
                break;
        }

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle(animalName);
        }



    }
}
