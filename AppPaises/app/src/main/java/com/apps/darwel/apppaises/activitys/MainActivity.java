package com.apps.darwel.apppaises.activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.darwel.apppaises.R;
import com.apps.darwel.apppaises.models.Constant;
import com.apps.darwel.apppaises.models.Country;
import com.apps.darwel.apppaises.webservice.CountryFetcher;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private static final int MENU_NEXT = Menu.FIRST;
    private static final int MENU_BACK = Menu.FIRST+1;
    private static final int MENU_FIND = Menu.FIRST+2;
    private static final int NUM_RESULTS_PER_PAGE=8;
    private int TOTAL_RESULTS;
    private ListView listCountrys;
    private TextView empty;
    private ProgressDialog progressDialog;
    private Country[] countrys;
    private Context context;

    private final Handler handler = new Handler(){
        @Override
        public void handleMessage(final Message msg){
            progressDialog.dismiss();
            if(countrys.length == 0 || countrys == null){
                empty.setText(R.string.empty_list);
            }else{
                CountryAdapter adapter = new CountryAdapter(context,
                        R.layout.listview_item_row,countrys);

                if(listCountrys.findViewById(R.id.header_label) == null) {
                    Log.i("info","inicializando header");
                    View header = (View) getLayoutInflater().inflate(R.layout.list_header_row,null);
                    listCountrys.addHeaderView(header);
                }

                listCountrys.setAdapter(adapter);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        listCountrys = (ListView) findViewById(R.id.list);
        empty = (TextView) findViewById(R.id.empty);
    }

    @Override
    public void onResume(){
        super.onResume();
        int startFrom = getIntent().getIntExtra(Constant.STARTFROM_EXTRA,1);
        loadCountrys(startFrom,MainActivity.NUM_RESULTS_PER_PAGE);
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        Intent intent = null;
        switch (item.getItemId()){
            case MainActivity.MENU_NEXT:
                intent = new Intent(this,MainActivity.class);
                if(TOTAL_RESULTS<getIntent().getIntExtra(Constant.STARTFROM_EXTRA,1)+
                        MainActivity.NUM_RESULTS_PER_PAGE){
                    intent.putExtra(Constant.STARTFROM_EXTRA,1);
                }else {
                    intent.putExtra(Constant.STARTFROM_EXTRA,
                            getIntent().getIntExtra(Constant.STARTFROM_EXTRA, 1) +
                                    MainActivity.NUM_RESULTS_PER_PAGE);
                }
                startActivity(intent);
                return true;
            case MainActivity.MENU_BACK:
                Toast.makeText(MainActivity.this, "back", Toast.LENGTH_SHORT).show();
                return true;
            case MainActivity.MENU_FIND:
                Toast.makeText(MainActivity.this, "buscar", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void loadCountrys(final int startfrom, final int numResults){

        final CountryFetcher cf = new CountryFetcher();

        this.progressDialog = ProgressDialog.show(this, "Trabajando...","Recuperando listado",
                true, false);

        new Thread(){
            @Override
            public void run(){
                countrys = cf.getCountrys(startfrom,numResults);
                TOTAL_RESULTS = cf.getTOTAL();
                handler.sendEmptyMessage(0);
            }
        }.start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
