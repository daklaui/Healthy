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


public class Step6 extends Fragment {
RadioButton R1,R2,R3,R4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_step6, container, false);

        R1=view.findViewById(R.id.r1_Perder);

        R2=view.findViewById(R.id.r2_Perder);

        R3=view.findViewById(R.id.r3_Perder);

        R4=view.findViewById(R.id.r4_Perder);

/*******************************CheckBox***********************************************/
        R1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(R1.isChecked()){
                    R1.setChecked(true);
                    R2.setChecked(false);
                    R3.setChecked(false);
                    R4.setChecked(false);
                }


            }
        });


        R2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(R2.isChecked()){
                    R2.setChecked(true);
                    R1.setChecked(false);
                    R3.setChecked(false);
                    R4.setChecked(false);
                }

            }
        });


        R3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(R3.isChecked()){
                    R3.setChecked(true);
                    R2.setChecked(false);
                    R1.setChecked(false);
                    R4.setChecked(false);
                }

            }
        });



        R4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(R4.isChecked()){
                    R4.setChecked(true);
                    R2.setChecked(false);
                    R3.setChecked(false);
                    R1.setChecked(false);
                }
            }
        });


/************************************FIN****************************************************/
return  view;
    }


}
