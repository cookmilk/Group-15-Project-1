package com.example.tony.spark;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeScreenMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        String emoticon = getIntent().getStringExtra("emoticon");
        ImageView moodpic = (ImageView) findViewById(R.id.moodz);
        TextView suggest = (TextView) findViewById(R.id.suggestion);
        Intent sendIntent;
        if (emoticon != null){
            if (emoticon.equals("happy")){
                moodpic.setImageResource(R.drawable.face_neutral);
                suggest.setText("  ");
            }
            else if (emoticon.equals("neutral")){
                moodpic.setImageResource(R.drawable.face_neutral);
                String txt = "How about taking a walk?";
                suggest.setText("You should go outside and take a nice walk!");
                sendIntent = new Intent(this, PhoneToWatchService.class);
                sendIntent.putExtra("noti", txt);
                startService(sendIntent);

            }
            else{
                moodpic.setImageResource(R.drawable.face_neutral);
                suggest.setText("You should take a break and have a walk!");
                String txt = "How about taking a walk?";
                sendIntent = new Intent(this, PhoneToWatchService.class);
                sendIntent.putExtra("noti", txt);
                Log.i("What it do", "SEND ANDI NTENE");
                startService(sendIntent);
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
           //Do nothing
        } else if (id == R.id.nav_analytics) {
            startActivity(new Intent(this, AnalyticsActivity.class));
        } else if (id == R.id.nav_diary) {
            startActivity(new Intent(this, DiaryActivity.class));
        } else if (id == R.id.nav_challenge) {
            startActivity(new Intent(this, ChallengeActivity.class));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(this, AboutActivity.class));
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
