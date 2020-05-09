package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.healthy.Classes.Historique_Regime;
import com.example.healthy.Classes.Profile;
import com.example.healthy.Classes.Regime;
import com.example.healthy.Database.DatabaseHandler;

import org.joda.time.DateTime;
import org.joda.time.Weeks;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Suivi extends AppCompatActivity {
DatabaseHandler databaseHandler;
TextView CurrentW_Suivi,WeekGoalSuivi,StartW,Difference,TimeSpent,EndGoal,lose,RemainingT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suivi);
        databaseHandler= new DatabaseHandler(this);
        CurrentW_Suivi=findViewById(R.id.CurrentW_Suivi);
        WeekGoalSuivi=findViewById(R.id.WeekGoalSuivi);
        StartW=findViewById(R.id.StartW);
        Difference=findViewById(R.id.Difference);
        TimeSpent=findViewById(R.id.TimeSpent);
        EndGoal=findViewById(R.id.EndGoal);
        lose=findViewById(R.id.lose);
        RemainingT=findViewById(R.id.RemainingT);

        Regime regime = databaseHandler.getRegime(1);
        Historique_Regime historique_regime = databaseHandler.isteHistorique_Regime();
Profile profile= databaseHandler.getProfile(1);
        double obejctif=0;

        if(regime.getTyp_regime().contains("PERDER"))
        {
            switch (regime.getObjectif())
            {
                case 1: obejctif=0.25; break;
                case 2: obejctif=0.5; break;
                case 3: obejctif=0.75; break;
                case 4:  obejctif=1;break;
            }
         //   Goal.setTextColor(Color.RED);
        }
        else if (regime.getTyp_regime().contains("GA"))
        {
            switch (regime.getObjectif())
            {
                case 1: obejctif=0.25; break;
                case 2: obejctif=0.5; break;

            }
           // Goal.setTextColor(Color.GREEN);
        }
        else
        {
            obejctif=0;
         //   Goal.setTextColor(Color.BLUE);
        }

long nbsays=Math.round(((profile.get_poids()-regime.getNew_poids())*obejctif)*7);

        Log.e("testtss",profile.getDate_Start());
        CurrentW_Suivi.setText(historique_regime.getNew_Poids()+"");
        WeekGoalSuivi.setText(historique_regime.getNew_Poids()-obejctif+"");
        StartW.setText(profile.get_poids()+" Kg le "+profile.getDate_Start());
        Difference.setText(profile.get_poids()-historique_regime.getNew_Poids()+"");
        TimeSpent.setText(GetNbDays(profile.getDate_Start())+" Jours");

        EndGoal.setText(regime.getNew_poids()+" Kg le "+GetDateWithNbDays(profile.getDate_Start(),nbsays));
        lose.setText(profile.get_poids()-regime.getNew_poids()+"");
        RemainingT.setText(GetNbDays(GetDateWithNbDays(profile.getDate_Start(),nbsays))+" Jours");
    }
    public double calculeIMC(double p , int t)
    {
        double res ;
        double x = Math.pow((double)t/100,2.0);
        return res = p /x ;
    }

    public String getDay()
    {

        Calendar calendar = Calendar.getInstance();
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


    private int GetNbDays(String date)
    {
        String date_sys=getDay();
        String NewDateSys=date_sys.substring(6,10)+"-"+date_sys.substring(3,5)+"-"+date_sys.substring(0,2);
        String NewDate=date.substring(6,10)+"-"+date.substring(3,5)+"-"+date.substring(0,2);
        // String NewDateSys=date_sys;
        //String NewDate=date;
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = f.parse(NewDateSys);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            date2 = f.parse(NewDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Parsing the date
        Calendar day1 = Calendar.getInstance();
        Calendar day2 = Calendar.getInstance();
        if(date1!=null && date2!=null)
        {
            day1.setTime(date1);
            day2.setTime(date2);

        }
        DateTime start = new DateTime(day1.YEAR, day1.MONTH, day1.DAY_OF_MONTH, 0, 0, 0, 0);
        DateTime end   = new DateTime(day2.YEAR, day2.MONTH, day2.DAY_OF_MONTH, 0, 0, 0, 0);

        int daysBetween = day1.get(Calendar.DAY_OF_YEAR) - day2.get(Calendar.DAY_OF_YEAR);

        int nbweek= Weeks.weeksBetween(start,end).getWeeks();

        return  Math.abs(daysBetween);
    }

    private String GetDateWithNbDays(String OldDate,long nbdays)
    {
        String NewDate=OldDate.substring(6,10)+"-"+OldDate.substring(3,5)+"-"+OldDate.substring(0,2);
        DateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = f1.parse(NewDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String newDate="";
        if(date1!=null)
        {
            Calendar c = Calendar.getInstance();
            c.setTime(date1);
            c.add(Calendar.DAY_OF_MONTH, (int) nbdays);
             newDate = f.format(c.getTime());
        }


return  newDate;

    }
}
