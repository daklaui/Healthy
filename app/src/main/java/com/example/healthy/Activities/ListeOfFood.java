package com.example.healthy.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.app.progresviews.ProgressLine;
import com.example.healthy.Adapters.FoodListeAdap;
import com.example.healthy.Classes.Food;
import com.example.healthy.Classes.Regime;
import com.example.healthy.Database.DatabaseHandler;
import com.example.healthy.R;

import java.util.Calendar;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class ListeOfFood extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseHandler databaseHandler;
    ProgressLine progressLine;
    String listF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_of_food);
        Toolbar toolbar=findViewById(R.id.include);
        toolbar.setTitle("List Of Foods");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView=findViewById(R.id.listeoffoodlocal);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseHandler=new DatabaseHandler(this);

        Regime regime=databaseHandler.getRegime(1);
        progressLine=findViewById(R.id.progress_line);
        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        List<Food> foods = databaseHandler.getListeNourriture(getDay(startDate));
        recyclerView.setAdapter(new FoodListeAdap(getApplicationContext(), foods,ListeOfFood.this));

        int month=startDate.get(Calendar.MONTH);


        int cal;
        if(regime.getTyp_regime().contains("GA"))
        {
            if(regime.getObjectif()==1)
            {
                cal=databaseHandler.getDiet(6).getCalories();
            }
            else
            {
                cal=databaseHandler.getDiet(7).getCalories();
            }
        }
        else
        if(regime.getTyp_regime().contains("PERDER"))
        {
            if(regime.getObjectif()==1)
            {
                cal=databaseHandler.getDiet(1).getCalories();
            }
            else if(regime.getObjectif()==2)
            {
                cal=databaseHandler.getDiet(2).getCalories();
            }
            else if(regime.getObjectif()==3)
            {
                cal=databaseHandler.getDiet(3).getCalories();
            }
            else
            {
                cal=databaseHandler.getDiet(4).getCalories();
            }
        }
        else
        {

            cal=databaseHandler.getDiet(5).getCalories();
        }

        CalculePourcentage(foods,cal);

        startDate.add(Calendar.MONTH, -month);

        // ends after 1 month from now
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {

            //    Toast.makeText(ListeOfFood.this,getDay(date),Toast.LENGTH_LONG).show();
                List<Food> foods = databaseHandler.getListeNourriture(getDay(date));
                CalculePourcentage(foods,cal);
                recyclerView.setAdapter(new FoodListeAdap(getApplicationContext(), foods,ListeOfFood.this));
            }
        });
    }


    public String getDay(Calendar calendar)
    {

      //  Calendar calendar = Calendar.getInstance();
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


    void CalculePourcentage(List<Food> foods,int cal)
    {

        int sum=0;
        for(int i=0;i<foods.size();i++)
        {
            sum+=foods.get(i).getCalories();
        }
        int pourcentage= (int) ((100*Math.abs(sum)/cal));
        progressLine.setmValueText(String.valueOf((cal-sum)));
        progressLine.setmPercentage(pourcentage);
    }



}
