package dnkilic.splashscreen;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Switch swBluetooth = (Switch) findViewById(R.id.swBluetooth);
        swBluetooth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                bluetooth(b);
            }
        });

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter == null)
        {
            swBluetooth.setEnabled(false);
        }

        ToggleButton tgWifi = (ToggleButton) findViewById(R.id.tbWifi);
        tgWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                if(b)
                {
                    wifiManager.setWifiEnabled(true);
                }
                else
                {
                    wifiManager.setWifiEnabled(false);
                }
            }
        });

        final String[] countries = {"Almanya", "Fransa", "İngiltere"};
        Spinner spCountries = (Spinner) findViewById(R.id.spCountry);

       /* ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getApplicationContext(), android.R.layout.simple_spinner_item, countries);

        spCountries.setAdapter(adapter);*/
        spCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), countries[i] + " seçildi.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(),"Kullanıcı bişey seçmedi.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void bluetooth(boolean state)
    {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

            if(bluetoothAdapter.isEnabled() && !state)
            {
                bluetoothAdapter.disable();
            }
            else if(!bluetoothAdapter.isEnabled() && state)
            {
                bluetoothAdapter.enable();
            }

    }

    public void onMaleSelected(View v)
    {
        Toast.makeText(getApplicationContext(), "Erkek Seçildi", Toast.LENGTH_SHORT).show();
    }

    public void onFemaleSelected(View v)
    {
        Toast.makeText(getApplicationContext(), "Kadın Seçildi", Toast.LENGTH_SHORT).show();
    }

    String selected;

    public void onGenderSelected(View view) {
        RadioButton rbSelected = (RadioButton) view;

        switch (rbSelected.getId())
        {
            case R.id.rbMonkey:
                Toast.makeText(getApplicationContext(), "Maymun seçildi.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rbDog:
                Toast.makeText(getApplicationContext(), "Köpek seçildi.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rbLion:
                Toast.makeText(getApplicationContext(), "Aslan seçildi.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rbGiraffe:
                Toast.makeText(getApplicationContext(), "Zürafa seçildi.", Toast.LENGTH_SHORT).show();
        }
    }
}
