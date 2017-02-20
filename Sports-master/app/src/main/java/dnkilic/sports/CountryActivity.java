package dnkilic.sports;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class CountryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        ImageView iv = (ImageView) findViewById(R.id.ivCountryFlag);
        String countryName = getIntent().getStringExtra("COUNTRY_NAME");

        switch (countryName)
        {
            case "Türkiye":
                iv.setImageResource(R.drawable.tr);
                break;
            case "İran":
                iv.setImageResource(R.drawable.ir);
                break;
            case "Rusya":
                iv.setImageResource(R.drawable.ru);
                break;
            case "Libya":
                iv.setImageResource(R.drawable.ly);
                break;
            case "Afganistan":
                iv.setImageResource(R.drawable.af);
                break;
            case "Irak":
                iv.setImageResource(R.drawable.iq);
                break;
            case "Mısır":
                iv.setImageResource(R.drawable.eg);
                break;
            case "Zimbawbe":
                iv.setImageResource(R.drawable.zw);
                break;
            case "Uganda":
                iv.setImageResource(R.drawable.ug);
                break;
            case "Rwanda":
                iv.setImageResource(R.drawable.rw);
                break;
            case "Kongo":
                iv.setImageResource(R.drawable.kn);
                break;
            case "Kenya":
                iv.setImageResource(R.drawable.ke);
                break;
            case "Nijerya":
                iv.setImageResource(R.drawable.ng);
                break;
            case "Jamaika":
                iv.setImageResource(R.drawable.jm);
                break;
            case "Yeni Zelanda":
                iv.setImageResource(R.drawable.nz);
                break;
            case "Avustralya":
                iv.setImageResource(R.drawable.au);
                break;
            case "Hindistan":
                iv.setImageResource(R.drawable.in);
                break;
            case "Pakistan":
                iv.setImageResource(R.drawable.pk);
                break;
            case "Norveç":
                iv.setImageResource(R.drawable.no);
                break;
            case "İsveç":
                iv.setImageResource(R.drawable.se);
                break;
            case "Güney Kore":
                iv.setImageResource(R.drawable.kr);
                break;
            case "Finlandinya":
                iv.setImageResource(R.drawable.fi);
                break;
            default:
                iv.setImageResource(R.drawable.popupic);
                break;
        }
    }
}
