package com.darwel.android.ejemplos.fragment.dinamico;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Este Fragment representa el Fragment master
 * encargado de controlar los Fragment de detalle
 */
public class Izquierda extends Fragment {

    View rootView;
    Button boton;
    EditText txt;
    Enviar ENVIAR;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_izquierda, container, false);

        txt = (EditText) rootView.findViewById(R.id.text);
        boton = (Button) rootView.findViewById(R.id.boton);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje = txt.getText().toString();
                ENVIAR.enviarTexto(mensaje);
            }
        });

        return rootView;
    }

    /*
     * Este metodo es parte del ciclo de vida de un Fragment
     * y es el primero en ser llamado para posteriormente llamar
     * el metodo onCreateView.
     * Se utiliza para inyectar la instancia del Activity que contiene
     * los Fragment. En este ejemplo el Activity tiene la logica que controla
     * el Fragment de detalle
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = getActivity();
        try{
            ENVIAR = (Enviar) activity;
        }catch (ClassCastException e){
            throw new ClassCastException("Clase incorrecta");
        }
    }

}
