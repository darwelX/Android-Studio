package com.apps.darwel.apppaises.activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.apps.darwel.apppaises.R;
import com.apps.darwel.apppaises.models.Country;
import com.apps.darwel.apppaises.webservice.CountryFetcher;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private static final int MENU_NEXT = Menu.FIRST;
    private static final int MENU_BACK = Menu.FIRST+1;
    private static final int MENU_FIND = Menu.FIRST+2;
    private ListView listCountrys;
    private ProgressDialog progressDialog;
    private ArrayList<Country> countrys;

    private final Handler handler = new Handler(){
        @Override
        public void handleMessage(final Message msg){
            progressDialog.dismiss();
            if(countrys.size() == 0 || countrys == null){

            }else{
                Country [] arryc = (Country [])countrys.toArray();
                CountryAdapter adapter = new CountryAdapter(getApplicationContext(),
                        R.layout.listview_item_row,arryc);
                View header = (View) getLayoutInflater().inflate(R.layout.list_header_row,null);
                listCountrys.addHeaderView(header);
                listCountrys.setAdapter(adapter);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCountrys = (ListView) findViewById(R.id.list);
    }

    @Override
    public void onResume(){
        super.onResume();
        loadCountrys();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        menu.add(0,MainActivity.MENU_NEXT,0,R.string.menu_next).setIcon(
                android.R.drawable.ic_media_next);
        menu.add(0,MainActivity.MENU_BACK,0,R.string.menu_back).setIcon(
                android.R.drawable.ic_media_previous);
        menu.add(0,MainActivity.MENU_FIND,0,R.string.menu_find).setIcon(
                android.R.drawable.ic_menu_search);

        return true;
    }

    public void loadCountrys(){

        final CountryFetcher cf = new CountryFetcher();

        this.progressDialog = ProgressDialog.show(this, "Trabajando...","Recuperando listado",
                true, false);

        new Thread(){
            @Override
            public void run(){
                countrys = cf.getCountrys();
                handler.sendEmptyMessage(0);
            }
        }.start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
