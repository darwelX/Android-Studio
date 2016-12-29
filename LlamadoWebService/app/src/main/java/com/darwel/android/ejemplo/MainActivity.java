package com.darwel.android.ejemplo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.darwel.android.ejemplo.webservice.WebService;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    TextView txt;
    Button boton;
    WebService webService;
    List<String> paises;

    private final Handler handler = new Handler(){
        @Override
        public void handleMessage(final Message msg){
                //(paises.size() == 0) ||
                if ( (paises == null) ) {
                    txt.setText("Pais no encontrado");
                } else {
                    txt.setText(paises.get(0).toString());
                }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView) findViewById(R.id.txt);
        boton = (Button) findViewById(R.id.enviar);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar();
            }
        });

    }

    public void buscar(){
        webService = new WebService();

        new Thread(){
            @Override
            public void run(){
                paises = (List)webService.buscarPaises();
                handler.sendEmptyMessage(0);
            }
        }.start();
    }
}
