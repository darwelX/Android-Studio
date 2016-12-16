package com.darwel.android.ejemplos;

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
public class FragmentDerecha extends Fragment {
    View rootView;
    TextView text;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // infla el Layout del Activity con el fragmento
        rootView = inflater.inflate(R.layout.fragment_derecha, container, false);

        text = (TextView) rootView.findViewById(R.id.txt);
        return rootView;
    }


    /*
     * Setea el texto del TextView del Fragment,
     * este metodo es invocado desde el Activity que contiene
     * los fragments
     */
    public void obtener(String mensaje){
        text.setText(mensaje);
    }
}
