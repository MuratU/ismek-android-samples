package dnkilic.sports;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {


    private String[] countries = {"Türkiye", "İran" , "Rusya",
            "Libya", "Afganistan", "Irak", "Mısır", "Zimbawbe",
            "Uganda", "Rwanda", "Kongo", "Kenya", "Nijerya", "Papua Yeni Gine",
            "Jamaika", "Yeni Zelanda", "Avustralya", "Hindistan", "Pakistan",
            "Norveç", "İsveç", "Güney Kore", "Finlandinya"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ListView lvCountries = (ListView) findViewById(R.id.lvSports);

        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (getApplicationContext(), R.layout.custom_listview_item, R.id.textView, countries);

        lvCountries.setAdapter(adapter);

        lvCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            final AlertDialog.Builder myPopup = new AlertDialog.Builder(SecondActivity.this);

            final String countryName = countries[i];
            myPopup.setMessage(countryName)
                    .setCancelable(false)
                    .setIcon(getResources().getDrawable(R.drawable.popupic))
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            Intent intent = new Intent(SecondActivity.this, CountryActivity.class);
                            intent.putExtra("COUNTRY_NAME", countryName);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getApplicationContext(), "Hayıra bastın!!!!!!!", Toast.LENGTH_SHORT).show();
                            dialogInterface.dismiss();
                        }
                    });

            myPopup.create().show();

        }
    });
}
}
