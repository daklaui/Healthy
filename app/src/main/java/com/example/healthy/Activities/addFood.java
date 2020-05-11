package com.example.healthy.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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


import com.android.volley.RequestQueue;
import com.android.volley.Response;

import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.healthy.Adapters.FoodAdapter;
import com.example.healthy.Classes.Food;
import com.example.healthy.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mancj.materialsearchbar.MaterialSearchBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;


import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class addFood extends AppCompatActivity {
RecyclerView recyclerView;
MaterialSearchBar  searchBar;
String dateFinal="";
String date="";

    FloatingActionButton fab1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        recyclerView=findViewById(R.id.testryceycel);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchBar = findViewById(R.id.searchBar);
        searchBar.setCardViewElevation(10);
        searchBar.setSpeechMode(false);
        fab1=findViewById(R.id.fasb);

        Toolbar toolbar=findViewById(R.id.include2);
        toolbar.setTitle("Add food");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(addFood.this, CreateFood.class));
            }
        });
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

        Calendar startDate = Calendar.getInstance();
        int month=startDate.get(Calendar.MONTH);


        startDate.add(Calendar.MONTH, -month);

        // ends after 1 month from now
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView2)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                dateFinal=getDay(date);
              //  Toast.makeText(addFood.this,getDay(date),Toast.LENGTH_LONG).show();
            }
        });

    }






    void  ListeDesFoods(String text)
    {
        String JSON_URL = "http://92.222.83.184:9999/api/Food?mot"+text;
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest (JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                final ArrayList<Food> foods = new ArrayList<>();
                // Toast.makeText(Liste_Des_Doctors.this,adress,Toast.LENGTH_LONG).show();
                 for (int i = 0; i < response.length(); i++) {

                     try {
                         JSONObject jsonObject = response.getJSONObject(i);
                         Food food = new Food();
                         food.setId(Integer.parseInt(jsonObject.getString("ID")));
                         food.setTitle(jsonObject.getString("Titre"));
                         food.setQnparUnite(jsonObject.getString("QT_Par_Unite"));
                         food.setUnite(jsonObject.getString("Titre_Unite"));
                         food.setCalories(Integer.parseInt(jsonObject.getString("Calories")));
                         food.setImage("http://92.222.83.184:9999" + jsonObject.getString("Image"));
                         food.setDate(dateFinal);
                         foods.add(food);
                         Log.e("tableChar",foods.toString());
                     } catch (JSONException e) {
                         e.printStackTrace();
                     }
                 }

                recyclerView.setAdapter(new FoodAdapter(getApplicationContext(), foods,addFood.this));

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
                          /*  int position = rv.getChildLayoutPosition(child);
                            Food food = foods.get(position);
                            Intent myIntent = new Intent(addFood.this, InformationFood.class);
                            myIntent.putExtra("Food", food);
                            startActivity(myIntent);*/

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



    public String getDay(Calendar calendar)
    {

        /*  Calendar calendar = Calendar.getInstance();*/
        final int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        month=month+1;
        String day_1="";
        String Month_1="";
        if(day<10)
        {
            day_1="0"+day;

        }
        else
        {
            day_1=String.valueOf(day);
        }
        if(month<10)
        {
            Month_1="0"+month;
        }
        else
        {
            Month_1=String.valueOf(month);
        }
        return day_1+"-"+Month_1+"-"+year;

    }
}
