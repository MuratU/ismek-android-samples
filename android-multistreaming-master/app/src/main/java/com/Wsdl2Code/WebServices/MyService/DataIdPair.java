package com.Wsdl2Code.WebServices.MyService;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import java.util.Hashtable;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

public class DataIdPair implements KvmSerializable {
    
    public VectorByte data;
    public String segmentID;
    
    public DataIdPair(){}
    
    public DataIdPair(SoapObject soapObject)
    {
        if (soapObject == null)
            return;
        if (soapObject.hasProperty("Data"))
        {
            SoapPrimitive j = (SoapPrimitive)soapObject.getProperty("Data");
            data = new VectorByte(j);
        }
        if (soapObject.hasProperty("SegmentID"))
        {
            Object obj = soapObject.getProperty("SegmentID");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                segmentID = j.toString();
            }else if (obj!= null && obj instanceof String){
                segmentID = (String) obj;
            }
        }
    }
    @Override
    public Object getProperty(int arg0) {
        switch(arg0){
            case 0:
                return data.toString();
            case 1:
                return segmentID;
        }
        return null;
    }
    
    @Override
    public int getPropertyCount() {
        return 2;
    }
    
    @Override
    public void getPropertyInfo(int index, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        switch(index){
            case 0:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Data";
                break;
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "SegmentID";
                break;
        }
    }

    
    @Override
    public void setProperty(int arg0, Object arg1) {
    }
    
}
