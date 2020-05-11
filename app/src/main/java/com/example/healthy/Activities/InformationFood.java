package com.example.healthy.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.app.progresviews.ProgressWheel;
import com.example.healthy.Adapters.FoodAdapter;
import com.example.healthy.Classes.Food;
import com.example.healthy.R;
import com.king.view.arcseekbar.ArcSeekBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InformationFood extends AppCompatActivity {
ProgressWheel arcSeekBar,Net_Carbs,Protien,Fat;String text="180203";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_food);
        arcSeekBar=findViewById(R.id.Calories);
        Net_Carbs=findViewById(R.id.Net_Carbs);
        Protien=findViewById(R.id.Protien);
        Fat=findViewById(R.id.Fat);
        Intent myIntent = getIntent();
        final Food food = (Food)myIntent.getSerializableExtra("Food");
        text=food.getId()+"";
        GetValues();





        //arcSeekBar.setLabelText(100+"Cal");
      //  arcSeekBar.showAnimation(80,3000);
    }


   void GetValues()
    {
        String JSON_URL = "https://api.spoonacular.com/food/products/"+text+"?apiKey=ca329df47b10418eb01bfcc6439c4e7a";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET,JSON_URL,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject jsonObject=response.getJSONObject("nutrition");
                    Log.e("sssssqsxqscxs", jsonObject.toString());
                    JSONArray array = jsonObject.getJSONArray("nutrients");
                    Log.e("sssssqsxqscxs", array.toString());
                    for(int i=0;i<array.length();i++){

                        JSONObject student = array.getJSONObject(i);
                        Log.e("sssssqsxqscxs", student.getString("title"));
                        switch (student.getString("title"))
                        {
                            case "Fat" :
                                Fat.setDefText(student.getString("unit"));
                                Fat.setStepCountText(student.getString("amount"));
                                break;
                            case "Protein" :
                                Protien.setDefText(student.getString("unit"));
                                Protien.setStepCountText(student.getString("amount"));

                                break;
                            case "Calories" :
                                arcSeekBar.setDefText(student.getString("unit"));
                                arcSeekBar.setStepCountText(student.getString("amount"));

                                break;
                            case "Carbohydrates" :
                                Net_Carbs.setDefText(student.getString("unit"));
                                Net_Carbs.setStepCountText(student.getString("amount"));

                                break;
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
