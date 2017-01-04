package com.apps.darwel.apppaises.webservice;

import android.util.Log;

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

    public CountryFetcher() {
        this.wbs = new WebService();
    }

    public ArrayList<Country> getCountrys(){
        JSONArray countrys = wbs.getContries();
        ArrayList<Country> arrayCountry = new ArrayList<Country>();

        for(int i = 0; i < countrys.length(); i++){
            try {
                JSONObject objCountry = (JSONObject) countrys.getJSONObject(i);
                Country c = new Country();
                c.setName(objCountry.getString("Name"));
                arrayCountry.add(c);
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
}
