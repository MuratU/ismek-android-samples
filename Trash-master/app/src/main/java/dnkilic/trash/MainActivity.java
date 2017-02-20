package dnkilic.trash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] data = {"Maymun", "Zürafa", "Fil"};
        ListView lvAnimals = (ListView) findViewById(R.id.lvAnimals);
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, data);

        lvAnimals.setAdapter(adapter);

        lvAnimals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, AnimalActivity.class);
                intent.putExtra("ANIMAL_NAME", data[i]);
                startActivity(intent);
            }
        });
    }
}
