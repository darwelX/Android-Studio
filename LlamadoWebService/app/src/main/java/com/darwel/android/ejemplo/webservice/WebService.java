package com.darwel.android.ejemplo.webservice;

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
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.SimpleTimeZone;


/**
 * Created by Darwel on 22/12/2016.
 */
public class WebService {
    private static final String metodo="GetCountries";
    private static final String namespace="http://www.webserviceX.NET";
    private static final String accionSoap="http://www.webserviceX.NET/GetCountries";
    private static final String url="http://www.webservicex.net/country.asmx";
    private SoapObject resultado;
    private ArrayList<String> paises;


    public WebService() {
    }

    public ArrayList<String> buscarPaises(){
        try{
            SoapObject request = new SoapObject(WebService.namespace,WebService.metodo);

            SoapSerializationEnvelope sobre = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            sobre.dotNet=true;
            sobre.setOutputSoapObject(request);

            HttpTransportSE trasporte = new HttpTransportSE(WebService.url);
            trasporte.call(WebService.accionSoap, sobre);

            paises = new ArrayList<String>();
            Object obj = sobre.getResponse();

            if(obj instanceof SoapObject){
                this.resultado = (SoapObject) obj;

                Log.v("VERBOSE","paises encontrados "+this.resultado.getPropertyCount());

                for(int i=0; i < this.resultado.getPropertyCount(); i++){
                    SoapObject pais = (SoapObject)this.resultado.getProperty(i);
                    paises.add(pais.getProperty(0).toString());
                    Log.v("VERBOSE",pais.getProperty(0).toString());
                }
            }else {
                JSONObject jsonObj = null;
                try {
                    jsonObj = XML.toJSONObject(sobre.getResponse().toString());
                } catch (JSONException e) {
                    Log.e("JSON exception", e.getMessage());
                    e.printStackTrace();
                }
                JSONArray arrayJson = jsonObj.getJSONObject("NewDataSet").getJSONArray("Table");
                Log.v("VERBOSE","paises encontrados "+arrayJson.length());
                for(int i=0; i < arrayJson.length(); i++){
                    JSONObject object = arrayJson.getJSONObject(i);
                    paises.add(object.getString("Name"));
                    Log.v("VERBOSE", "pais " + i + " - " + object.getString("Name"));
                }

            }


        }catch (Exception e){
            Log.e("ERROR","aqui "+e.getMessage());
            e.getStackTrace();
        }

        return paises;
    }

}



