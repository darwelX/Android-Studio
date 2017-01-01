package com.apps.darwel.apppaises.webservice;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Realiza las invocaciones principales del WebService de la App
 * Created by Darwel on 30/12/2016.
 */
public class WebService {
    private final static String SEPARATOR="/";
    private final static String url="http://www.webservicex.net/country.asmx";
    private final static String namespace="http://www.webserviceX.NET";
    private final static String GET_COUNTRIES=WebService.namespace+WebService.SEPARATOR+"GetCountries";
    private final static String METHOD_COUNTRIES="GetCountries";
    private final static String GET_ISD=WebService.namespace+WebService.SEPARATOR+"GetISD";
    private final static String METHOD_ISD="GetISD";
    private final static String GET_GMT=WebService.namespace+WebService.SEPARATOR+"GetGMTbyCountry";
    private final static String METHOD_GMT="GetGMTbyCountry";
    private final static String GET_CURRENCY=WebService.namespace+WebService.SEPARATOR+"GetCurrencyByCountry";
    private final static String METHOD_CURRENCY="GetCurrencyByCountry";
    private JSONArray resultado = null;


    public WebService() {
    }

    public JSONArray getContries(){
        try{
            SoapObject request = new SoapObject(WebService.namespace,WebService.METHOD_COUNTRIES);

            SoapSerializationEnvelope envolpe = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envolpe.dotNet=true;
            envolpe.setOutputSoapObject(request);

            HttpTransportSE transport = new HttpTransportSE(WebService.url);
            transport.call(WebService.GET_COUNTRIES,envolpe);

            Object obj = envolpe.getResponse();

            if(obj instanceof SoapObject){

                String tagInit="{";
                String tagEnd="}";
                String stringJson=tagInit+"\"Table\":[";
                SoapObject result = (SoapObject) obj;

                for(int i=0; i < result.getPropertyCount(); i++){
                    SoapObject countrie = (SoapObject) result.getProperty(i);
                    if(i == (result.getAttributeCount()-1) ){
                        stringJson+=tagInit+"\"Name\":"+countrie.getProperty(0).toString()+"}";
                    }else{
                        stringJson+=tagInit+"\"Name\":"+countrie.getProperty(0).toString()+"},";
                    }

                }
                
                stringJson+="]"+tagEnd;

                JSONObject jsonObject = null;
                try{
                    jsonObject = XML.toJSONObject(stringJson);
                }catch (JSONException ex){
                    Log.e("error", "Buscar paises SoapObject "+ex.getMessage());
                }

                JSONArray arrayJson = jsonObject.getJSONArray("Table");
                return arrayJson;

            }else if(obj instanceof SoapPrimitive){
                JSONObject jsonObject = null;

                try{
                    jsonObject = XML.toJSONObject(envolpe.getResponse().toString());
                }catch (JSONException ex){
                    Log.e("error","Buscar paises SoapPrimitive "+ex.getMessage());
                }

                JSONArray arrayJson = jsonObject.getJSONObject("NewDataSet").getJSONArray("Table");
                Log.i("info","retorno un JSONArray en base a un SoapPrimitve");
                return arrayJson;
            }
        }catch (Exception e){
            Log.e("error","Buscar paises "+e.getMessage());
        }
        return resultado;
    }
}
