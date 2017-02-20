package dnkilic.tcmb;

public class Currency
{
    private int CrossOrder;
    private double ForexBuying;
    private double CrossRateUSD;
    private double CrossRateOther;
    private String CurrencyName;
    private double ForexSelling;
    private double BanknoteSelling;
    private String Kod;
    private String CurrencyCode;
    private String Isim;
    private double BanknoteBuying;
    private int Unit;

    public int getCrossOrder ()
    {
        return CrossOrder;
    }

    public void setCrossOrder (int CrossOrder)
    {
        this.CrossOrder = CrossOrder;
    }

    public double getForexBuying ()
    {
        return ForexBuying;
    }

    public void setForexBuying (double ForexBuying)
    {
        this.ForexBuying = ForexBuying;
    }

    public double getCrossRateUSD ()
    {
        return CrossRateUSD;
    }

    public void setCrossRateUSD (double CrossRateUSD)
    {
        this.CrossRateUSD = CrossRateUSD;
    }

    public double getCrossRateOther ()
    {
        return CrossRateOther;
    }

    public void setCrossRateOther (double CrossRateOther)
    {
        this.CrossRateOther = CrossRateOther;
    }

    public String getCurrencyName ()
    {
        return CurrencyName;
    }

    public void setCurrencyName (String CurrencyName)
    {
        this.CurrencyName = CurrencyName;
    }

    public double getForexSelling ()
    {
        return ForexSelling;
    }

    public void setForexSelling (double ForexSelling)
    {
        this.ForexSelling = ForexSelling;
    }

    public double getBanknoteSelling ()
    {
        return BanknoteSelling;
    }

    public void setBanknoteSelling (double BanknoteSelling)
    {
        this.BanknoteSelling = BanknoteSelling;
    }

    public String getKod ()
    {
        return Kod;
    }

    public void setKod (String Kod)
    {
        this.Kod = Kod;
    }

    public String getCurrencyCode ()
    {
        return CurrencyCode;
    }

    public void setCurrencyCode (String CurrencyCode)
    {
        this.CurrencyCode = CurrencyCode;
    }

    public String getIsim ()
    {
        return Isim;
    }

    public void setIsim (String Isim)
    {
        this.Isim = Isim;
    }

    public double getBanknoteBuying ()
    {
        return BanknoteBuying;
    }

    public void setBanknoteBuying (double BanknoteBuying)
    {
        this.BanknoteBuying = BanknoteBuying;
    }

    public int getUnit ()
    {
        return Unit;
    }

    public void setUnit (int Unit)
    {
        this.Unit = Unit;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [CrossOrder = "+CrossOrder+", ForexBuying = "+ForexBuying+", CrossRateUSD = "+CrossRateUSD+", CrossRateOther = "+CrossRateOther+", CurrencyName = "+CurrencyName+", ForexSelling = "+ForexSelling+", BanknoteSelling = "+BanknoteSelling+", Kod = "+Kod+", CurrencyCode = "+CurrencyCode+", Isim = "+Isim+", BanknoteBuying = "+BanknoteBuying+", Unit = "+Unit+"]";
    }
}