package com.darwel.android.ejemplos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

/*
 * Esta clase implementa una interfaz la cual es usada para compartir
 * el comportamiento del Activity sin necesidad de heredar el Activity
 * completo, con el uso de la interfaz la Activity controla el comportamiento de los
 * Fragment. Las clases que hacen uso del comportamiento son los
 * Fragments
 */
public class MainActivity extends AppCompatActivity implements Enviar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void enviarTexto(String mensaje) {
        //usa el administrador de Fragment para octener el Fragment
        FragmentManager manager = getSupportFragmentManager();
        FragmentDerecha derecha = (FragmentDerecha)manager.findFragmentById(R.id.derecha);
        //(FragmentDerecha) getFragmentManager().findFragmentById(R.id.derecha);
        derecha.obtener(mensaje);
    }
}
