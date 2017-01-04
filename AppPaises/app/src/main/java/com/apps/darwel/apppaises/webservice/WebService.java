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
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

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
                String stringJson=tagInit+"\"Table\":["+tagInit;
                SoapObject result = (SoapObject) obj;

                for(int i=0; i < result.getPropertyCount(); i++){
                    SoapObject countrie = (SoapObject) result.getProperty(i);
                    if(i == (result.getAttributeCount()-1) ){
                        stringJson+=tagInit+"\"Name\":"+countrie.getProperty(0).toString()+tagEnd;
                    }else{
                        stringJson+=tagInit+"\"Name\":"+countrie.getProperty(0).toString()+"},";
                    }

                }
                stringJson+="]"+tagEnd;

                JSONObject jsonObject = null;
                jsonObject = XML.toJSONObject(stringJson);
                JSONArray arrayJson = jsonObject.getJSONArray("Table");
                Log.i("info","Se retorno un JSONArray en base a un SOAPObject");
                return arrayJson;

            }else if(obj instanceof SoapPrimitive){
                    JSONObject jsonObject = XML.toJSONObject(envolpe.getResponse().toString());
                    JSONArray arrayJson = jsonObject.getJSONObject("NewDataSet").getJSONArray("Table");
                    Log.i("info","retorno un JSONArray en base a un SoapPrimitve");
                    return arrayJson;
            }else{
                Log.i("info","no encontro nada Buscar paises");
            }

        }catch (XmlPullParserException e){
            Log.e("error","Buscar paises XmlPullParserException "+e.getMessage());
        }catch (IOException ex){
            Log.e("error","Buscar paises IOException "+ ex.getMessage());
        }catch (JSONException ex){
            Log.e("error","Buscar paises SoapPrimitive "+ex.getMessage());
        }
        return null;
    }

    public JSONArray getISD(String nameCountry){
        try{
            SoapObject request = new SoapObject(WebService.namespace,WebService.METHOD_ISD);
            request.addProperty("CountryName",nameCountry);

            SoapSerializationEnvelope envolpe = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envolpe.dotNet=true;
            envolpe.setOutputSoapObject(request);

            HttpTransportSE transport = new HttpTransportSE(WebService.url);
            transport.call(WebService.GET_ISD,envolpe);

            Object obj = envolpe.getResponse();

            if(obj instanceof SoapObject){
                String tagInit="{";
                String tagEnd="}";
                String jsonString=tagInit+"\"Table\":["+tagInit;
                SoapObject result = (SoapObject) obj;
                SoapObject countrie = (SoapObject) result.getProperty(0);
                jsonString+=tagInit+"\"code\":"+countrie.getProperty(0)+tagEnd+"]"+tagEnd;
                JSONObject jsonObject = XML.toJSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("Table");
                Log.i("info","se retorno JSONArray en base a SOAPObject");
                return jsonArray;

            }else if(obj instanceof SoapPrimitive){
                JSONObject jsonObject = XML.toJSONObject(envolpe.getResponse().toString());
                JSONArray jsonArray = jsonObject.getJSONObject("NewDataSet").getJSONArray("Table");
                Log.i("info","retorno JSONArray en base a SOAPPrimitive");
                return jsonArray;
            }else{
                Log.i("info","No se encontro nada en buscar ISD");
            }

        }catch (XmlPullParserException ex){
            Log.e("error","Buscar ISD XmlPullParserException "+ex.getMessage());
        }catch (IOException ex){
            Log.e("error","Buscar ISD IOException "+ex.getMessage());
        }catch (JSONException ex){
            Log.e("error","Buscar ISD JSONException "+ex.getMessage());
        }

        return null;
    }

    public JSONArray getGMT(String nameCountry){

        try{
            SoapObject request = new SoapObject(WebService.namespace,WebService.METHOD_GMT);
            request.addProperty("CountryName",nameCountry);

            SoapSerializationEnvelope envolpe = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envolpe.dotNet=true;
            envolpe.setOutputSoapObject(request);

            HttpTransportSE transport = new HttpTransportSE(WebService.url);
            transport.call(WebService.GET_GMT,envolpe);

            Object obj = envolpe.getResponse();

            if(obj instanceof SoapObject){
                String tagInit="{";
                String tagEnd="}";
                String jsonString=tagInit+"\"Table\":["+tagInit;
                SoapObject soapObject = (SoapObject) obj;
                SoapObject countrie = (SoapObject)soapObject.getProperty(0);
                jsonString+=tagInit+"\"GMT\":"+countrie.getProperty(0)+tagEnd+"]"+tagEnd;
                JSONObject jsonObje = XML.toJSONObject(jsonString);
                JSONArray jsonArray = jsonObje.getJSONArray("Table");
                Log.i("info","retornando JSONArray a base de SoapObject");
                return jsonArray;
            }else if(obj instanceof SoapPrimitive){
                JSONObject jsonObject = XML.toJSONObject(envolpe.getResponse().toString());
                JSONArray jsonArray = jsonObject.getJSONObject("NewDataSet").getJSONArray("Table");
                Log.i("info","retornando JSONObject a base de SoapPrimitive");
                return jsonArray;
            }else{
                Log.i("info","No se encontro nada en buscar GMT");
            }
        }catch (XmlPullParserException ex){
            Log.e("error","Error XmlPaserException "+ex.getMessage());
        }catch (IOException ex){
            Log.e("error","Error IOException "+ex.getMessage());
        }catch (JSONException ex){
            Log.e("error","Erro JSONException "+ex.getMessage());
        }
        return null;
    }

    public JSONArray getCurrency(String nameCountry){
        try {
            SoapObject request = new SoapObject(WebService.namespace, WebService.METHOD_CURRENCY);
            request.addProperty("CountryName",nameCountry);

            SoapSerializationEnvelope envolpe = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envolpe.dotNet = true;
            envolpe.setOutputSoapObject(request);

            HttpTransportSE transport = new HttpTransportSE(WebService.url);
            transport.call(WebService.GET_CURRENCY, envolpe);

            Object obj = envolpe.getResponse();

            if(obj instanceof SoapObject){
                String tagInit="{";
                String tagEnd="}";
                String json = tagInit + "\"Table\":[" + tagInit;
                SoapObject soapObj = (SoapObject) obj;
                SoapObject currencyCountry = (SoapObject) soapObj.getProperty(0);
                json+="\"Name\":"+currencyCountry.getProperty(0)+",";
                json+="\"CountryCode\":"+currencyCountry.getProperty(1)+",";
                json+="\"Currency\":"+currencyCountry.getProperty(2)+",";
                json+="\"CurrencyCode\":"+currencyCountry.getProperty(3)+tagEnd+"]"+tagEnd;
                JSONObject jsonObj = XML.toJSONObject(json);
                JSONArray currency = jsonObj.getJSONArray("Table");
                Log.i("info","retornando JSONArray en base a SoapObject");
                return currency;
            }else if(obj instanceof SoapPrimitive){
                JSONObject jsonObject = XML.toJSONObject(envolpe.getResponse().toString());
                JSONArray currency = jsonObject.getJSONObject("NewDataSet").getJSONArray("Table");
                Log.i("info","retornando JSONArray en base a SoapPrimitive");
                return currency;
            }else{
                Log.i("info","no encontro nada en buscar Moneda");
            }
        }catch(XmlPullParserException ex){
            Log.e("error","error XmlPullParserException buscar moneda");
        }catch(IOException ex){
            Log.e("error","error IOException buscar moneda");
        }catch(JSONException ex){
            Log.e("error","error JSONException buscar moneda");
        }
        return null;
    }
}
