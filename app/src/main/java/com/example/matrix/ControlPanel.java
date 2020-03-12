package com.example.matrix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.text.DecimalFormat;

public class ControlPanel extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_panel);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.baseline_home_black_18dp);
        drawerLayout = findViewById(R.id.drawer_layout);
        final LocationTracker mLocationTracker = new LocationTracker(this);
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                final TextView user_textview = drawerView.findViewById(R.id.user_name);
                final TextView location_textview = drawerView.findViewById(R.id.user_location);

                mLocationTracker.getLocation();
                final double longtitude = mLocationTracker.getLongitude();
                final double latitude = mLocationTracker.getLatitude();
                if (Config.username == null) {
                    user_textview.setText("");
                    location_textview.setText("");
                } else {
                    user_textview.setText(Config.username);
                    location_textview.setText("Lat=" + new DecimalFormat(".##").
                            format(latitude) + ",Lon=" + new DecimalFormat(".##").
                            format(longtitude));

                }
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawers();
                if (item.getItemId() == R.id.drawer_logout) {
                    Config.username = null;
                    logout();
                }
                return true;
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, MainFragment.newInstance()).commit();

    }
    private void logout(){
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
