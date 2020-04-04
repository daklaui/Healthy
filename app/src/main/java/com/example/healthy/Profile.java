package com.example.healthy;

import android.graphics.Color;
import android.os.Bundle;

import com.example.healthy.Classes.Account;
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
import android.widget.TextView;

public class Profile extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    SpeedView speedView;
    TextView Start,Goal,Gain,Current,Nom_profile,Email,Profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        DatabaseHandler db = new DatabaseHandler(this);
        speedView = findViewById(R.id.speedView1);
        speedView.setMaxSpeed(50);
        speedView.clearSections();
        speedView.addSections(new Section(0f, .37f, Color.YELLOW,75, Section.Style.SQUARE)
                ,new Section(.37f, .50f, Color.GREEN,75, Section.Style.SQUARE)
                ,new Section(.50f, 1f, Color.RED,75, Section.Style.SQUARE)
        );
        com.example.healthy.Classes.Profile profile = db.getProfile(1);
        Regime regime=db.getRegime(1);
        Account account=db.getAccount_parid(1);
        Log.e("mmmmmm",profile.get_prénom());
        speedView.setSpeedAt((float)profile.get_imc());
        Start=findViewById(R.id.StartW);
        Goal=findViewById(R.id.Goal);

        Start.setText(String.valueOf(profile.get_poids()));
        Goal.setText(String.valueOf(regime.getNew_poids()));
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       /* FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
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
