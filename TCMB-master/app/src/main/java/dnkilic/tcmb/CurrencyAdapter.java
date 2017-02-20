package dnkilic.tcmb;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;


public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder>{

    private ArrayList<Currency> mDataset;

    public CurrencyAdapter(ArrayList<Currency> dataset) {
        this.mDataset = dataset;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivCurrency;
        private TextView tvCurrencyName;
        private TextView tvCurrencyValue;

        public ViewHolder(View v) {
            super(v);

            ivCurrency = (ImageView) v.findViewById(R.id.ivCurrency);
            tvCurrencyName = (TextView) v.findViewById(R.id.tvCurrencyName);
            tvCurrencyValue = (TextView) v.findViewById(R.id.tvCurrencyValue);
        }

        public ImageView getCurrencyImage()
        {
            return ivCurrency;
        }

        public TextView getTvCurrencyName()
        {
            return tvCurrencyName;
        }

        public TextView getTvCurrencyValue()
        {
            return tvCurrencyValue;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.currency, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       // holder.getCurrencyImage().setImageResource();

      /*  switch(mDataset.get(position).getCurrencyCode())
        {
            case "USD":
                holder.getCurrencyImage().setImageResource(R.drawable.usd);
                break;
        }*/

        holder.getTvCurrencyName().setText(mDataset.get(position).getCurrencyName());

        if(mDataset.get(position).getBanknoteBuying() == 0)
        {

        }

        holder.getTvCurrencyValue().setText(mDataset.get(position).getBanknoteBuying() + "");
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
