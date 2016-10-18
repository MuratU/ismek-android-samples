package com.dnkilic.application38;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class CurrencyReader {

    private String TAG = "CurrencyReader";

    private ConversionResultListener mListener;

    public CurrencyReader(ConversionResultListener listener)
    {
        mListener = listener;
    }

    public void convertCurrency(CurrencyCode sourceCurrency, CurrencyCode destinationCurrency, Double amount)
    {
        if(sourceCurrency != destinationCurrency)
        {
            if(sourceCurrency != CurrencyCode.USD && sourceCurrency != CurrencyCode.TRY)
            {
                if(destinationCurrency != CurrencyCode.USD && destinationCurrency != CurrencyCode.TRY)
                {
                    mListener.onError("Girilen Kurlar İçin Çeviri Yapılamaz");
                }
                else
                {
                    new CurrencyConverter().execute(new ConversionRequest(sourceCurrency, destinationCurrency, amount));
                }
            }
            else
            {
                new CurrencyConverter().execute(new ConversionRequest(sourceCurrency, destinationCurrency, amount));
            }
        }
        else
        {
            mListener.onError("Aynı Kur İçin Çeviri Yapmayı Denediniz");
        }
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

    private CurrencyCode convertStringToCurrencyCode(String currency)
    {
        CurrencyCode currencyCode = null;

        switch (currency)
        {
            case "USD":
                currencyCode = CurrencyCode.USD;
                break;
            case "AUD":
                currencyCode = CurrencyCode.AUD;
                break;
            case "DKK":
                currencyCode = CurrencyCode.DKK;
                break;
            case "EUR":
                currencyCode = CurrencyCode.EUR;
                break;
            case "GBP":
                currencyCode = CurrencyCode.GBP;
                break;
            case "CHF":
                currencyCode = CurrencyCode.CHF;
                break;
            case "SEK":
                currencyCode = CurrencyCode.SEK;
                break;
            case "CAD":
                currencyCode = CurrencyCode.CAD;
                break;
            case "KWD":
                currencyCode = CurrencyCode.KWD;
                break;
            case "NOK":
                currencyCode = CurrencyCode.NOK;
                break;
            case "SAR":
                currencyCode = CurrencyCode.SAR;
                break;
            case "JPY":
                currencyCode = CurrencyCode.JPY;
                break;
            case "BGN":
                currencyCode = CurrencyCode.BGN;
                break;
            case "RON":
                currencyCode = CurrencyCode.RON;
                break;
            case "RUB":
                currencyCode = CurrencyCode.RUB;
                break;
            case "IRR":
                currencyCode = CurrencyCode.IRR;
                break;
            case "CNY":
                currencyCode = CurrencyCode.CNY;
                break;
            case "PKR" :
                currencyCode = CurrencyCode.PKR;
                break;
        }

        return currencyCode;
    }

    private class CurrencyConverter extends AsyncTask<ConversionRequest, Integer, Double>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Double doInBackground(ConversionRequest... params) {

            ConversionRequest request = params[0];
            HashMap<CurrencyCode, Currency> currencyMap = new HashMap<>();

            try {
                DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                        .newDocumentBuilder();

                URL u = new URL("http://www.tcmb.gov.tr/kurlar/today.xml");

                Document doc = builder.parse(u.openStream());
                NodeList date = doc.getElementsByTagName("Tarih_Date");
                NodeList nodes = doc.getElementsByTagName("Currency");

                for (int i = 0; i < nodes.getLength(); i++) {
                    Element element = (Element) nodes.item(i);

                    Currency currency = new Currency();

                    currency.setCurrencyCode(convertStringToCurrencyCode(element.getAttribute("CurrencyCode")));
                    currency.setCrossOrder(Integer.parseInt(element.getAttribute("CrossOrder")));
                    currency.setCurrencyName(getElementValue(element, "Isim"));
                    currency.setCurrencyUnit(Integer.parseInt(getElementValue(element, "Unit")));
                    currency.setBanknoteBuying(getFloat(getElementValue(element, "BanknoteBuying"))/Integer.parseInt(getElementValue(element, "Unit")));
                    currency.setBanknoteSelling(getFloat(getElementValue(element, "BanknoteSelling"))/Integer.parseInt(getElementValue(element, "Unit")));
                    currency.setForexBuying(getFloat(getElementValue(element, "ForexBuying"))/Integer.parseInt(getElementValue(element, "Unit")));
                    currency.setForexSelling(getFloat(getElementValue(element, "ForexSelling"))/Integer.parseInt(getElementValue(element, "Unit")));
                    currency.setCrossRateUSD(getFloat(getElementValue(element, "CrossRateUSD"))/Integer.parseInt(getElementValue(element, "Unit")));
                    currency.setCrossRateOther(getFloat(getElementValue(element, "CrossRateOther"))/Integer.parseInt(getElementValue(element, "Unit")));

                    currencyMap.put(currency.getCurrencyCode(), currency);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            CurrencyCode source = request.getSourceCurrency();
            CurrencyCode destination = request.getDestinationCurrency();

            Double result = 0.0;

            if(destination == CurrencyCode.TRY)
            {
                for(Map.Entry<CurrencyCode, Currency> item : currencyMap.entrySet())
                {
                    if(item.getKey() == source)
                    {
                        Currency sourceCurrency = item.getValue();

                        Double money;
                        if(sourceCurrency.getBanknoteSelling() == 0.0000)
                        {
                            money = sourceCurrency.getForexBuying();
                        }
                        else
                        {
                            money = sourceCurrency.getBanknoteSelling();
                        }

                        result = request.getAmount() * money;
                    }
                }
            }

            if(source == CurrencyCode.TRY)
            {
                for(Map.Entry<CurrencyCode, Currency> item : currencyMap.entrySet())
                {
                    if(item.getKey() == destination)
                    {
                        Currency destinationCurrency = item.getValue();

                        Double money;
                        if(destinationCurrency.getBanknoteSelling() == 0.0000)
                        {
                            money = destinationCurrency.getForexBuying();
                        }
                        else
                        {
                            money = destinationCurrency.getBanknoteSelling();
                        }

                        result = request.getAmount() / money;
                    }
                }
            }

            if(destination == CurrencyCode.USD)
            {
                for(Map.Entry<CurrencyCode, Currency> item : currencyMap.entrySet())
                {
                    if(item.getKey() == source)
                    {
                        Currency sourceCurrency = item.getValue();

                        Double money;
                        if(sourceCurrency.getCrossRateUSD() != 0.0000)
                        {
                            money = sourceCurrency.getCrossRateUSD();
                        }
                        else
                        {
                            money = sourceCurrency.getCrossRateOther();
                        }

                        result = request.getAmount() * money;
                    }
                }
            }

            if(source == CurrencyCode.USD)
            {
                for(Map.Entry<CurrencyCode, Currency> item : currencyMap.entrySet())
                {
                    if(item.getKey() == destination)
                    {
                        Currency destinationCurrency = item.getValue();

                        Double money;
                        if(destinationCurrency.getCrossRateUSD() != 0.0000)
                        {
                            money = destinationCurrency.getCrossRateUSD();
                        }
                        else
                        {
                            money = destinationCurrency.getCrossRateOther();
                        }

                        result = request.getAmount() / money;
                    }
                }
            }








            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Double s) {
            mListener.onSuccess(s);
            super.onPostExecute(s);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }


}
