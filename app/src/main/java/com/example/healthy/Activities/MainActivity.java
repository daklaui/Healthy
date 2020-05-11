package com.example.healthy.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthy.R;


public class MainActivity extends AppCompatActivity {
    private static final int AUTO_HIDE_DELAY_MILLIS = 2 * 1000;
    private static final int UI_ANIMATION_DELAY = 300;
    private SharedPreferences sharedpreferences;
    private String MyPREFERENCES = "Session";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        checkNextScreenAfterDelay();
    }


    private void checkNextScreenAfterDelay() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(sharedpreferences.getString("Connected","0").contains("1"))
                {
                    Intent intent = new Intent(MainActivity.this, Profile.class);
                    startActivity(intent);
                }
                else
                {
                    openMainActivity();
                }
            }


        }, AUTO_HIDE_DELAY_MILLIS);

    }
    private boolean isConnected() {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
       // Toast.makeText(MainActivity.this,sharedpreferences.getString("Inscription",""),Toast.LENGTH_LONG).show();
        return sharedpreferences.getString("Inscription", "") == "";
    }

    private void openMainActivity() {

        startActivity(new Intent(this, LoginActivity.class));
        finishAffinity();
    }

    private void openLoginActivity() {

        startActivity(new Intent(this, LoginActivity.class));
        finishAffinity();
    }

}
