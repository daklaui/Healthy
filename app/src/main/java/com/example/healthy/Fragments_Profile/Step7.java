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


public class Step7 extends Fragment {
RadioButton r1,r2;

    public Step7() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_step7, container, false);
        r1=view.findViewById(R.id.r1_Ga);
        r2=view.findViewById(R.id.r2_Ga);
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(r1.isChecked()){
                    r1.setChecked(true);
                    r2.setChecked(false);

                }

            }
        });
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(r2.isChecked()){
                    r2.setChecked(true);
                    r1.setChecked(false);

                }

            }
        });
        return view;
    }


}
