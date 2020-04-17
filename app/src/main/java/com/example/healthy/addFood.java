package com.example.healthy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.healthy.Adapters.FoodAdapter;
import com.example.healthy.Classes.Food;
import com.google.gson.JsonObject;
import com.mancj.materialsearchbar.MaterialSearchBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class addFood extends AppCompatActivity {
RecyclerView recyclerView;
MaterialSearchBar  searchBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        recyclerView=findViewById(R.id.testryceycel);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchBar = findViewById(R.id.searchBar);
        searchBar.setCardViewElevation(10);
        searchBar.setSpeechMode(false);

        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ListeDesFoods(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });



    }


    void  ListeDesFoods(String text)
    {
        String JSON_URL = "https://api.spoonacular.com/food/products/search?apiKey=ca329df47b10418eb01bfcc6439c4e7a&query="+text;
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET,JSON_URL,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                final ArrayList<Food> foods = new ArrayList<>();
                // Toast.makeText(Liste_Des_Doctors.this,adress,Toast.LENGTH_LONG).show();
                // for (int i = 0; i < response.length(); i++) {

                //JSONObject jsonObject = response.getJSONObject(i);

                //  food.setId(Integer.parseInt(jsonObject.getString("id")));
                try {
                    JSONArray array = response.getJSONArray("products");
                    for(int i=0;i<array.length();i++){
                        // Get current json object
                        Food food = new Food();
                        JSONObject student = array.getJSONObject(i);
                        food.setId(Integer.parseInt(student.getString("id")));
                        food.setTitle(student.getString("title"));
                        food.setImage(student.getString("image"));
                        // Get the current student (json object) data

                        // Display the formatted json data in text view
                        foods.add(food);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //  food.setCalories(jsonObject.getJSONObject("nutrition").getJSONObject("caloricBreakdown").getString("percentProtein"));
                //food.setUnite(jsonObject.getString("serving_size"));
                //  food.setImage(jsonObject.getJSONArray("images").get(0).toString());


                //  }

                //    Log.e("Dec", String.valueOf(foods.size()));
                recyclerView.setAdapter(new FoodAdapter(getApplicationContext(), foods));

                recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                    GestureDetector gestureDetector = new GestureDetector(addFood.this, new GestureDetector.SimpleOnGestureListener() {
                        @Override
                        public boolean onSingleTapUp(MotionEvent e) {
                            return true;
                        }
                    });

                    @Override
                    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent motionEvent) {
                        View child = rv.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                        if (child != null && gestureDetector.onTouchEvent(motionEvent)) {
                            int position = rv.getChildLayoutPosition(child);
                            Food food = foods.get(position);
                            Intent myIntent = new Intent(addFood.this, InformationFood.class);
                            myIntent.putExtra("Food", food);
                            startActivity(myIntent);

                        }
                        return false;
                    }

                    @Override
                    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent motionEvent) {

                    }

                    @Override
                    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                    }
                });


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());

            }
        });

        requestQueue.add(jsonArrayRequest);

    }
}
