package com.example.dynamicfragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentA.FragmentAListener {

    private Fragment frag_A;
    private Fragment frag_B;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();

        frag_A = fragmentManager.findFragmentByTag("fragA");

        if (frag_A == null) {
            frag_A = new FragmentA();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(android.R.id.content, frag_A, "fragA");
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
        frag_B.setArguments(b);
        // A continuacion, se empieza la transicion de FragA a FragB
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(android.R.id.content, frag_B);

        fragmentTransaction.commit();
    }
}
