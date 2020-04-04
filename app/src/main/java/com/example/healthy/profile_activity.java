package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.healthy.Classes.Regime;
import com.example.healthy.Database.DatabaseHandler;
import com.github.anastr.speedviewlib.SpeedView;

public class profile_activity extends AppCompatActivity {
    SpeedView speedView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activity);

    }
}
