package com.example.dynamicfragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by usuario on 16/11/17.
 */

public class FragmentB extends Fragment {

    private TextView txtV_textoACambiar;
    //private String msg;
    //private int size;

    private View rootView;

    public static final String TAG = "FragmentB";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Log.d(TAG, "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retener el estado de un fragment
        // INCONVENIENTES: para que el estado dinámico de un fragment sea permanente ante un
        // cambio de configuración usar setRetainInstance
        // Si lo utilizamos con frecuencia, obligamos a que la app guarde en memoria todos los datos
        // que habíamos mostrado
        setRetainInstance(true);

        Log.d(TAG, "onCreate");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragmentb, container, false);

        if (rootView == null) {
            // Obtener View para modificar
            txtV_textoACambiar = rootView.findViewById(R.id.txtV_TextoCambiado);

        }
        Log.d(TAG, "onCreateView");

        return rootView;

    }


    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    /*
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("msg", txtV_textoACambiar.getText().toString());
        outState.putFloat("size", txtV_textoACambiar.getTextSize());
    }
    */

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle b = getArguments();
        if (savedInstanceState == null) {
            if (b != null) {
                msg = b.getString("msg");
                size = b.getInt("size");
            }
        }

        changeTextSize(msg, size);
        Log.d(TAG, "onViewCreated");
    }

    public void changeTextSize(String text, int size) {
        txtV_textoACambiar.setText(text);
        txtV_textoACambiar.setTextSize(size);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            txtV_textoACambiar.setText(savedInstanceState.getString("msg"));
            txtV_textoACambiar.setTextSize(savedInstanceState.getFloat("size")
                    / getResources().getDisplayMetrics().scaledDensity);
        }

        Log.d(TAG, "onActivityCreated");
    }


    @Override
    public void onStart() {
        super.onStart();

        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Log.d(TAG, "onDetach");
    }

    /**
     * Patron FACTORY, simplificación del patrón BUILDER
     * @param b Bundle a cargar
     * @return Devuelve FragmentB cargado con Bundle
     */
    public static Fragment newInstance(Bundle b) {
        FragmentB fragB = new FragmentB();

        if (b != null)
        {
            fragB.setArguments(b);
        }
        return fragB;

    }
}
