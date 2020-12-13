package com.example.sahem_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    int id;
    String role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        Bundle e= i.getExtras();
        role= e.getString("Role");
        id = e.getInt("idUser");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState == null) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,new HomeFragement()).commit();
        navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else
        {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,new AssociationsFragement()).commit();
                break;
            case R.id.nav_campaigns:
                CampaignsFragement CF = new CampaignsFragement();
                Bundle args= new Bundle();
                args.putInt("idUser",id);
                args.putString("Role",role);
                CF.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,CF).commit();
                break;
            case R.id.nav_associations:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,new AssociationsFragement()).commit();
                break;
            case R.id.nav_events:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,new EventsFragement()).commit();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,new ProfileFragement()).commit();
                break;
            case R.id.nav_logout:
                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}