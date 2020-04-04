package com.example.healthy.Fragments_Profile;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.healthy.R;


public class Step5 extends Fragment {
RadioButton r,r1,r2,r3;
    public Step5() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_step5, container, false);
        r= view.findViewById(R.id.r);
        r1= view.findViewById(R.id.r1);
        r2= view.findViewById(R.id.r2);
        r3= view.findViewById(R.id.r3);


/*******************************CheckBox***********************************************/
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(r.isChecked()){
                    r.setChecked(true);
                    r1.setChecked(false);
                    r2.setChecked(false);
                    r3.setChecked(false);
                }


            }
        });


        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(r1.isChecked()){
                    r1.setChecked(true);
                    r.setChecked(false);
                    r3.setChecked(false);
                    r2.setChecked(false);
                }

            }
        });


        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(r2.isChecked()){
                    r2.setChecked(true);
                    r.setChecked(false);
                    r3.setChecked(false);
                    r1.setChecked(false);
                }

            }
        });



        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(r3.isChecked()){
                    r3.setChecked(true);
                    r.setChecked(false);
                    r1.setChecked(false);
                    r2.setChecked(false);
                }
            }
        });


/************************************FIN****************************************************/


        return view;

    }

}
