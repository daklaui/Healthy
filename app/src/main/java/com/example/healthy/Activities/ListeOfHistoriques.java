package com.example.healthy.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.healthy.Adapters.HistoriqueAdapteur;
import com.example.healthy.Classes.Historique_Regime;
import com.example.healthy.Classes.Regime;
import com.example.healthy.Database.DatabaseHandler;
import com.example.healthy.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class ListeOfHistoriques extends AppCompatActivity {
ListView tableLayout;
FloatingActionButton floatingActionButton;
    ArrayList<Historique_Regime> dataModels;
    DatabaseHandler databaseHandler;
    private static HistoriqueAdapteur adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_of_historiques);


        Toolbar toolbar=findViewById(R.id.includ4);
        toolbar.setTitle("Journal");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        databaseHandler=new DatabaseHandler(this);
        tableLayout=(ListView)findViewById(R.id.ListeHistorique);
        dataModels= databaseHandler.getListeHistorique_Regime(1);
        floatingActionButton=findViewById(R.id.floatingActionButton2);
        adapter= new HistoriqueAdapteur(dataModels,getApplicationContext());
        tableLayout.setAdapter(adapter);
        com.example.healthy.Classes.Profile profile=databaseHandler.getProfile(1);
Regime regime = databaseHandler.getRegime(1);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                      //  .setAction("Action", null).show();
                AlertDialog.Builder builder= new AlertDialog.Builder(ListeOfHistoriques.this);
                View mview=getLayoutInflater().inflate(R.layout.activity_add__historique__regime,null);
                final EditText editText=mview.findViewById(R.id.New_Poids_Historique);
                Button confirme=mview.findViewById(R.id.confirm_button_);
                builder.setView(mview);
                final AlertDialog dialog=builder.create();
                dialog.show();
                confirme.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(editText.getText().toString().isEmpty())
                        {
                            editText.setError("");

                        }
                        switch (regime.getTyp_regime())
                        {
                            case "PERDER" :
                                if (profile.get_poids()<Double.parseDouble(editText.getText().toString()))
                                {
                                    editText.setError("");
                                }
                                else
                                {

                                    Insert_Historique(editText.getText().toString(),profile.get_taille(),profile.get_poids());
                                    //  com.example.healthy.Activities.Profile.this.finish();
                                    //startActivity(new Intent(L.this,Profile.class));
                                    dataModels= databaseHandler.getListeHistorique_Regime(1);
                                    adapter= new HistoriqueAdapteur(dataModels,getApplicationContext());
                                    tableLayout.setAdapter(adapter);
                                    dialog.cancel();
                                }

                                break;
                            case "GA" :
                                if (profile.get_poids()>Double.parseDouble(editText.getText().toString()))
                                {
                                    editText.setError("");
                                }
                                else
                                {

                                    Insert_Historique(editText.getText().toString(),profile.get_taille(),profile.get_poids());
                                    //  com.example.healthy.Activities.Profile.this.finish();
                                    //startActivity(new Intent(L.this,Profile.class));
                                    dataModels= databaseHandler.getListeHistorique_Regime(1);
                                    adapter= new HistoriqueAdapteur(dataModels,getApplicationContext());
                                    tableLayout.setAdapter(adapter);
                                    dialog.cancel();
                                }

                                break;
                            default :
                                if (profile.get_poids()!=Double.parseDouble(editText.getText().toString()))
                                {
                                    editText.setError("");
                                }


                                break;

                        }







                    }
                });


            }
        });
    }
    private void Insert_Historique(String toString,int taille,double poids) {

        Historique_Regime historique_regime = new Historique_Regime();
        historique_regime.setNew_Poids(Double.parseDouble(toString));
        historique_regime.setDate(getDay());
        historique_regime.setNew_IMC(calculeIMC(Double.parseDouble(toString),taille));
        historique_regime.setEvolution(poids-Double.parseDouble(toString));
        databaseHandler.addHistorique_Regime(historique_regime);
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

}
