package dnkilic.tcmb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CurrencyResultListener {

    private OnlineXMLParser parser;

    private RecyclerView mRvCurrencies;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Currency> mData;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRvCurrencies = (RecyclerView) findViewById(R.id.rvCurrencies);
        mLayoutManager = new LinearLayoutManager(this);
        mRvCurrencies.setLayoutManager(mLayoutManager);
        mData = new ArrayList<>();
        mAdapter = new CurrencyAdapter(mData);
        mRvCurrencies.setAdapter(mAdapter);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        parser = new OnlineXMLParser(this);
    }

    @Override
    public void onSuccess(final ArrayList<Currency> currencyList) {

        progressBar.setVisibility(View.INVISIBLE);
        mRvCurrencies.setVisibility(View.VISIBLE);

        for(Currency item : currencyList) {
            mData.add(item);
        }

        mAdapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), "Başarılı", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailed(final String message) {

        progressBar.setVisibility(View.GONE);
        mRvCurrencies.setVisibility(View.VISIBLE);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
