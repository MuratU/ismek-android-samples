package com.dnkilic.application38;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ConversionResultListener {

    private CurrencyCode mSourceCurrency;
    private CurrencyCode mDestinationCurrency;
    private EditText mEtAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String [] currencies = getResources().getStringArray(R.array.currencies);

        Spinner spSourceCurrency = (Spinner) findViewById(R.id.spSourceCurrency);
        spSourceCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSourceCurrency = getCurrencyCode(currencies[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        Spinner spDestinationCurrency = (Spinner) findViewById(R.id.spDestinationCurrency);
        spDestinationCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mDestinationCurrency = getCurrencyCode(currencies[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        mEtAmount = (EditText) findViewById(R.id.editText);

    }

    public void convertCurrency(View v)
    {
        CurrencyReader cr = new CurrencyReader(this);
        cr.convertCurrency(mSourceCurrency, mDestinationCurrency, Double.valueOf(mEtAmount.getText().toString()));
    }

    @Override
    public void onError(String errorMessage)
    {
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(errorMessage + "");
    }

    @Override
    public void onSuccess(Double result) {

        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(result + "");
    }

    public CurrencyCode getCurrencyCode(String currency)
    {
        CurrencyCode currencyCode = null;

        switch (currency)
        {
            case "ABD DOLARI":
                currencyCode = CurrencyCode.USD;
                break;
            case "AVUSTRALYA DOLARI":
                currencyCode = CurrencyCode.AUD;
                break;
            case "DANİMARKA KRONU":
                currencyCode = CurrencyCode.DKK;
                break;
            case "EURO":
                currencyCode = CurrencyCode.EUR;
                break;
            case "İNGİLİZ STERLİNİ":
                currencyCode = CurrencyCode.GBP;
                break;
            case "İSVİÇRE FRANGI":
                currencyCode = CurrencyCode.CHF;
                break;
            case "İSVEÇ KRONU":
                currencyCode = CurrencyCode.SEK;
                break;
            case "KANADA DOLARI":
                currencyCode = CurrencyCode.CAD;
                break;
            case "KUVEYT DİNARI":
                currencyCode = CurrencyCode.KWD;
                break;
            case "NORVEÇ KRONU":
                currencyCode = CurrencyCode.NOK;
                break;
            case "SUUDİ ARABİSTAN RİYALİ":
                currencyCode = CurrencyCode.SAR;
                break;
            case "JAPON YENİ":
                currencyCode = CurrencyCode.JPY;
                break;
            case "BULGAR LEVASI":
                currencyCode = CurrencyCode.BGN;
                break;
            case "RUMEN LEYİ":
                currencyCode = CurrencyCode.RON;
                break;
            case "RUS RUBLESİ":
                currencyCode = CurrencyCode.RUB;
                break;
            case "İRAN RİYALİ":
                currencyCode = CurrencyCode.IRR;
                break;
            case "ÇİN YUANI":
                currencyCode = CurrencyCode.CNY;
                break;
            case "PAKİSTAN RUPİSİ" :
                currencyCode = CurrencyCode.PKR;
                break;
            case "TÜRK LİRASI" :
                currencyCode = CurrencyCode.TRY;
                break;
        }

        return currencyCode;
    }

}
