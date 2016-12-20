package com.darwel.android.ejemplos.fragment.dinamico;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Enviar{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void enviarTexto(String mensaje) {

        //pregunta si no existe la instancia del Fragment para proceder a crearla
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null) {
            Derecha derecha = new Derecha(); //Se crea el Fragment para posteriormente ser añadido

            //se crea los argumentos con los cuales se inicializara el Fragment
            Bundle args = new Bundle();
            args.putString("texto",mensaje);
            derecha.setArguments(args);

            //Se crea la transaccion que añade el Fragment de forma dinamica
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, derecha);
            transaction.addToBackStack(null);//se añade el Fragment a la pila de retroceso administrada por el Activity anfitrion para que el usuario pueda navegar con el boton de Atras
            transaction.commit();
        }else{
            Derecha contenedor = (Derecha) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            contenedor.obtener(mensaje);
        }

    }

}
