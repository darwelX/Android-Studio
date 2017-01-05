package com.apps.darwel.apppaises.activitys;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.apps.darwel.apppaises.R;
import com.apps.darwel.apppaises.models.Country;
import android.app.Activity;

/**
 * Created by Darwel on 05/01/2017.
 */
public class CountryAdapter extends ArrayAdapter<Country>{

    private Context context;
    private int layoutResourceID;
    private Country data[] = null;

    public CountryAdapter(Context context, int layoutResourceid, Country [] data ){
        super(context, layoutResourceid, data);
        this.context = context;
        this.layoutResourceID = layoutResourceid;
        this.data = data;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        View row = convertView;
        CountryHolder ch = null;

        if(row == null){
            LayoutInflater inflater = ( (Activity) context ).getLayoutInflater();
            row = inflater.inflate(this.layoutResourceID,parent,false);
            ch = new CountryHolder();
            ch.nameCountry = (TextView) row.findViewById(R.id.nameCountry);
            row.setTag(ch);
        }else{
            ch = (CountryHolder) row.getTag();
        }

        Country co = data[position];

        ch.nameCountry.setText(co.getName());

        return row;
    }

    static class CountryHolder{
        TextView nameCountry;
    }
}
