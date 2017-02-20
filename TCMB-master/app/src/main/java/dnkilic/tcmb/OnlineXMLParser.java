package dnkilic.tcmb;

import android.os.AsyncTask;
import android.util.Log;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class OnlineXMLParser {

    private CurrencyResultListener mCurrencyResultListener;

    public OnlineXMLParser(CurrencyResultListener listener) {
        mCurrencyResultListener = listener;
        new ParseOnlineXML().execute("http://www.tcmb.gov.tr/kurlar/today.xml");
    }

    private String getCharacterDataFromElement(Element e) {
        try {
            Node child = e.getFirstChild();
            if (child instanceof CharacterData) {
                CharacterData cd = (CharacterData) child;
                return cd.getData();
            }
        } catch (Exception ex) {
        }
        return "";
    }

    private Double getFloat(String value) {
        if (value != null && !value.equals("")) {
            double x = Double.valueOf(value);

            return x;
        }
        else
        {
            return new Double(0.0000);
        }
    }

    private String getElementValue(Element parent, String label) {
        return getCharacterDataFromElement((Element) parent
                .getElementsByTagName(label).item(0));
    }

    private class ParseOnlineXML extends AsyncTask<String, Void, ArrayList<Currency>> {

        @Override
        protected ArrayList<Currency> doInBackground(String... strings) {

            String url = strings[0];
            ArrayList<Currency> currencyList = new ArrayList<>();

            try {
                URL xmlURL = new URL(url);
                InputStream xml = xmlURL.openStream();
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(xml);

                NodeList date = doc.getElementsByTagName("Tarih_Date");
                NodeList nodes = doc.getElementsByTagName("Currency");

                for (int i = 0; i < nodes.getLength(); i++) {
                    Element element = (Element) nodes.item(i);
                    Currency currency = new Currency();
                    currency.setCurrencyCode(element.getAttribute("CurrencyCode"));
                    currency.setCrossOrder(Integer.parseInt(element.getAttribute("CrossOrder")));
                    currency.setCurrencyName(getElementValue(element, "Isim"));
                    currency.setUnit(Integer.parseInt(getElementValue(element, "Unit")));
                    currency.setBanknoteBuying(getFloat(getElementValue(element, "BanknoteBuying"))/Integer.parseInt(getElementValue(element, "Unit")));
                    currency.setBanknoteSelling(getFloat(getElementValue(element, "BanknoteSelling"))/Integer.parseInt(getElementValue(element, "Unit")));
                    currency.setForexBuying(getFloat(getElementValue(element, "ForexBuying"))/Integer.parseInt(getElementValue(element, "Unit")));
                    currency.setForexSelling(getFloat(getElementValue(element, "ForexSelling"))/Integer.parseInt(getElementValue(element, "Unit")));
                    currency.setCrossRateUSD(getFloat(getElementValue(element, "CrossRateUSD"))/Integer.parseInt(getElementValue(element, "Unit")));
                    currency.setCrossRateOther(getFloat(getElementValue(element, "CrossRateOther"))/Integer.parseInt(getElementValue(element, "Unit")));

                    currencyList.add(currency);
                }

                xml.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            return currencyList;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<Currency> currencyList) {
            super.onPostExecute(currencyList);

            if(currencyList != null && !currencyList.isEmpty())
            {
                mCurrencyResultListener.onSuccess(currencyList);
            }
            else
            {
                mCurrencyResultListener.onFailed("Şuan için için veri alamıyoruz");
            }
        }

        @Override
        protected void onProgressUpdate(Void... voids) {
            super.onProgressUpdate(voids);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}
