package dnkilic.tcmb;

import java.util.ArrayList;
import java.util.HashMap;

public interface CurrencyResultListener {
    void onSuccess(ArrayList<Currency> currencyList);
    void onFailed(String message);
}
