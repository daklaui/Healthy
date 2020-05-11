package com.example.healthy.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthy.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DiaryActivity extends AppCompatActivity {

    FloatingActionButton fab1,fab2,fab ;
    Boolean isFABOpen = false ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DiaryActivity.this , addFood.class );
                startActivity(i);
            }
        });



}


}
