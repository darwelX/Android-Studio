package com.apps.darwel.apppaises.webservice;

import android.util.Log;

import com.apps.darwel.apppaises.activitys.MainActivity;
import com.apps.darwel.apppaises.models.Country;
import com.apps.darwel.apppaises.models.Currency;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Darwel on 04/01/2017.
 */
public class CountryFetcher {
    private WebService wbs;
    private int TOTAL;

    public CountryFetcher() {
        this.wbs = new WebService();
    }

    public Country[] getCountrys(int startfrom, int numResults){
        JSONArray countrys = wbs.getContries();

        this.setTOTAL(countrys.length());

        int max = startfrom + numResults;
        if( (startfrom + numResults) >= countrys.length() ){
            max = countrys.length();
        }
        int j = 0;
        Country arrayCountry[] = new Country[max-startfrom];
        Log.i("info","inicio en "+startfrom+" finalizo en "+max+" de un total de "+countrys.length());
        for(int i = startfrom; i < max; i++){
            try {
                JSONObject objCountry = (JSONObject) countrys.getJSONObject(i);
                Country c = new Country();
                c.setName(objCountry.getString("Name"));
                arrayCountry[j++] = c;
            }catch (JSONException ex){
                Log.e("error","CountryFecher - JSONException en el metodo getCountrys "+ex.getMessage());
            }
        }
        
        return arrayCountry;
    }

    public Country getDataCountry(Country c){
        try {

            JSONArray data = wbs.getCurrency(c.getName());
            JSONObject jo = (JSONObject) data.getJSONObject(0);
            c.setCodeIso(jo.getString("CountryCode"));
            Currency curr = new Currency();
            curr.setName(jo.getString("Currency"));
            curr.setCode(jo.getString("CurrencyCode"));
            c.setCurrency(curr);

            data = wbs.getISD(c.getName());
            jo = (JSONObject) data.getJSONObject(0);
            c.setISD("+"+jo.getString("code"));

            data= wbs.getGMT(c.getName());
            jo = (JSONObject) data.getJSONObject(0);
            c.setGMT("GMT-"+jo.getString("GMT"));

        }catch(JSONException ex){
            Log.e("error","CountryFetcher - JSONException en el metodo getDataCountry "+ex.getMessage());
        }

        return c;
    }

    public int getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(int TOTAL) {
        this.TOTAL = TOTAL;
    }
}
