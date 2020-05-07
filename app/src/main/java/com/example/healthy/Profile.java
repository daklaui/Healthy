package com.example.healthy;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.app.progresviews.ProgressLine;
import com.example.healthy.Classes.Account;
import com.example.healthy.Classes.Historique_Regime;
import com.example.healthy.Classes.Regime;
import com.example.healthy.Database.DatabaseHandler;
import com.github.anastr.speedviewlib.SpeedView;
import com.github.anastr.speedviewlib.components.Section;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class Profile extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    SpeedView speedView;
    TextView Start,Goal,Gain,Current,Nom_profile,Email,Profile,nbCalperDay;
    ProgressLine progressLine;    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
         db = new DatabaseHandler(this);
        speedView = findViewById(R.id.speedView1);
        speedView.setMaxSpeed(50);
        speedView.clearSections();
        speedView.addSections(new Section(0f, .37f, Color.YELLOW,75, Section.Style.SQUARE)
                ,new Section(.37f, .50f, Color.GREEN,75, Section.Style.SQUARE)
                ,new Section(.50f, 1f, Color.RED,75, Section.Style.SQUARE)
        );
        final com.example.healthy.Classes.Profile profile = db.getProfile(1);
        Regime regime=db.getRegime(1);
        Account account=db.getAccount_parid(1);
        Historique_Regime historique_regime=db.isteHistorique_Regime();
        Log.e("mmmmmm",profile.get_prénom());
        speedView.setSpeedAt((float)profile.get_imc());
        Start=findViewById(R.id.StartW);
        Goal=findViewById(R.id.Goal);
        progressLine=findViewById(R.id.progress_line);

        nbCalperDay=findViewById(R.id.NbCalories);
        int cal;
        if(regime.getTyp_regime().contains("GA"))
        {
            if(regime.getObjectif()==1)
            {
               cal=db.getDiet(6).getCalories();
            }
            else
            {
                cal=db.getDiet(7).getCalories();
            }
        }
        else
        if(regime.getTyp_regime().contains("PERDER"))
        {
            if(regime.getObjectif()==1)
            {
                cal=db.getDiet(1).getCalories();
            }
            else if(regime.getObjectif()==2)
            {
                cal=db.getDiet(2).getCalories();
            }
            else if(regime.getObjectif()==3)
            {
                cal=db.getDiet(3).getCalories();
            }
            else
            {
                cal=db.getDiet(4).getCalories();
            }
        }
        else
        {

            cal=db.getDiet(5).getCalories();
        }
        nbCalperDay.setText(String.valueOf(cal));
        Start.setText(String.valueOf(profile.get_poids()));
        Goal.setText(String.valueOf(regime.getNew_poids()));
        int pourcentage= (int) ((100*Math.abs(profile.get_poids()-historique_regime.getNew_Poids()))/Math.abs(profile.get_poids()-regime.getNew_poids()));

        progressLine.setmValueText(String.valueOf(Math.abs(profile.get_poids()-historique_regime.getNew_Poids())));
        progressLine.setmPercentage(pourcentage);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
     FloatingActionButton fab = findViewById(R.id.AjouterHistoriqueRegime);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                AlertDialog.Builder builder= new AlertDialog.Builder(Profile.this);
                View mview=getLayoutInflater().inflate(R.layout.activity_add__historique__regime,null);
                final EditText editText=mview.findViewById(R.id.New_Poids_Historique);
                Button confirme=mview.findViewById(R.id.confirm_button_);

                confirme.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(editText.getText().toString().isEmpty())
                        {
                            editText.setError("");

                        }
                        else if (profile.get_poids()>Double.parseDouble(editText.getText().toString()))
                        {
                            editText.setError("");
                        }
                        else
                        {

                            Insert_Historique(editText.getText().toString(),profile.get_taille(),profile.get_poids());
                            com.example.healthy.Profile.this.finish();
                            startActivity(new Intent(Profile.this,Profile.class));

                        }


                    }
                });
                builder.setView(mview);
                final AlertDialog dialog=builder.create();
                dialog.show();

            }
        });

        FloatingActionButton fab1 = findViewById(R.id.ajouterHistoriqueRegime);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                AlertDialog.Builder builder= new AlertDialog.Builder(Profile.this);
                View mview=getLayoutInflater().inflate(R.layout.templatechoix,null);

                Button PetitDej=mview.findViewById(R.id.PetitDej);
                Button Dej=mview.findViewById(R.id.Déjeuner);
                Button Diner=mview.findViewById(R.id.Diner);
                Button Snack=mview.findViewById(R.id.Snacks);
               
                builder.setView(mview);
                final AlertDialog dialog=builder.create();
                dialog.show();

            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        View headerview =navigationView.getHeaderView(0);
        Nom_profile=headerview.findViewById(R.id.nomp);
        Email=headerview.findViewById(R.id.Mail_Profile);
        Nom_profile.setText(profile.get_nom()+" "+profile.get_prénom());
        Email.setText(account.get_email());
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


    }
    private void Insert_Historique(String toString,int taille,double poids) {

        Historique_Regime historique_regime = new Historique_Regime();
        historique_regime.setNew_Poids(Double.parseDouble(toString));
        historique_regime.setDate(getDay());
        historique_regime.setNew_IMC(calculeIMC(Double.parseDouble(toString),taille));
        historique_regime.setEvolution(poids-Double.parseDouble(toString));
        db.addHistorique_Regime(historique_regime);
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
