package com.example.dynamicfragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

/**
 * Created by usuario on 16/11/17.
 */

public class FragmentA extends Fragment {

    private FragmentAListener mCallBack;

    // Obtener Views
    EditText edT_TextoAMostrar;
    Button btn_CambiarFontSize;
    SeekBar skB_fontSize;

    public static final String TAG = "FragmentA";

    // Interfaz que será contrato entre Fragment y Activity
    public interface FragmentAListener {

        void onFragmentAEvent(String msg, int size);
    }


    //region Método sin usar, onAttach(context)
     /* Este método solo funciona con > API 23
     * No da error en APIs anteriores, PERO NO COMUNICA CON ACTIVITY
     *
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    */
    //endregion

    /**onAttach
     * Aunque esté deprecado, lo usaremos por tener API mínima 21
     *
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Log.d(TAG, "onAttach");

        try {
            mCallBack = (FragmentAListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement FragmentAListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragmenta, container, false);

        edT_TextoAMostrar = (EditText) view.findViewById(R.id.edT_Texto);
        btn_CambiarFontSize = (Button) view.findViewById(R.id.btn_CambiarSize);
        skB_fontSize = (SeekBar) view.findViewById(R.id.skB_fontSize);

        Log.d(TAG, "onCreateView");

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d(TAG, "onViewCreated");

        btn_CambiarFontSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCallBack.onFragmentAEvent(edT_TextoAMostrar.getText().toString(),
                        skB_fontSize.getProgress());
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
        mCallBack = null;
        super.onDetach();
        Log.d(TAG, "onDetach");
    }
}
