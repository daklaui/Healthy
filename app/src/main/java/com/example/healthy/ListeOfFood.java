package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.healthy.Adapters.FoodAdapter;
import com.example.healthy.Adapters.FoodListeAdap;
import com.example.healthy.Classes.Food;
import com.example.healthy.Database.DatabaseHandler;

import java.util.List;

public class ListeOfFood extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_of_food);
        recyclerView=findViewById(R.id.listeoffoodlocal);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseHandler=new DatabaseHandler(this);
        List<Food> foods = databaseHandler.getListeNourriture();
        recyclerView.setAdapter(new FoodListeAdap(getApplicationContext(), foods,ListeOfFood.this));
    }
}
