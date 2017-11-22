package com.example.dynamicfragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements FragmentA.FragmentAListener {

    private Fragment frag_A;
    private Fragment frag_B;

    public static final String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();

        frag_A = fragmentManager.findFragmentByTag("fragA");

        if (frag_A == null) {
            frag_A = new FragmentA();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(android.R.id.content, frag_A);
            // Antes de realizar el commit se debe guardar la transacción
            fragmentTransaction.addToBackStack(null);

            fragmentTransaction.commit();
        }
    }

    @Override
    public void onFragmentAEvent(String msg, int size) {
        frag_B = new FragmentB();
        Bundle b = new Bundle();

        b.putString("msg", msg);
        b.putInt("size", size);

        // Con el método setArguments se pasa la información que necesita un fragment para ser
        // construido
        // setArguments debe ir antes que la Transaccion !!!

        // Se debe utilizar el patrón Factory donde la creación del objeto y el paso de argumentos
        // se ejecuten de forma consecutivamente

        //frag_B.setArguments(b);
        frag_B = FragmentB.newInstance(b);

        // A continuacion, se empieza la transicion de FragA a FragB
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(android.R.id.content, frag_B);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Activity: onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Activity: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Activity: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Activity: onDestroy()");
    }
}
