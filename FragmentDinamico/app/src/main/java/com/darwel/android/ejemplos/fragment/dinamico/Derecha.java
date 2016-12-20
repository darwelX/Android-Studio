package com.darwel.android.ejemplos.fragment.dinamico;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Este fragment es un fragment detalle el cual es controlado por
 * las acciones del Fragment maestro
 */
public class Derecha extends Fragment {

    View rootView;
    TextView label;
    String mensajeActual="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_derecha, container, false);
        label = (TextView) rootView.findViewById(R.id.lbl);

        return rootView;
    }

    /*
     * En este metodo se deben de manipular las propiedades
     * fijar valores, listas de valores. Dichos parametros se
     * extraen del objeto Bundle el cual fue cargado desde el
     * Activity que controla los Fragments
     */
    @Override
    public void onStart(){
        super.onStart();
        Bundle args = getArguments();
        if(args != null){
            obtener(args.getString("texto"));
        }else if(mensajeActual != ""){
            obtener(mensajeActual);
        }
    }
    /*
     * Setea el texto del TextView del Fragment,
     * este metodo es invocado desde el Activity que contiene
     * los fragments
     */
    public void obtener(String mensaje){
        label.setText(mensaje);
        mensajeActual = mensaje;
    }
}
