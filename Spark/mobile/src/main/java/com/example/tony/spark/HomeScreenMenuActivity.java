package com.example.tony.spark;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeScreenMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView e;
    public static final String PREFS = "SleepOrWake";

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
                moodpic.setImageResource(R.drawable.happy_face);
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
                moodpic.setImageResource(R.drawable.sad_face);
                suggest.setText("You should take a break and have a walk!");
                String txt = "How about taking a walk?";
                sendIntent = new Intent(this, PhoneToWatchService.class);
                sendIntent.putExtra("noti", txt);
                Log.i("What it do", "SEND ANDI NTENE");
                startService(sendIntent);
            }
        }

        TextView todayDate = (TextView) findViewById(R.id.today_date);
        Button newEntry = (Button) findViewById(R.id.new_entry);
        SimpleDateFormat df = new SimpleDateFormat("M/dd HH:mm");
        Calendar c = Calendar.getInstance();
        final String dateTime = df.format(c.getTime());
        String[] dateTimeArr = dateTime.split(" ");
        final String date = dateTimeArr[0];
        final String currTime = dateTimeArr[1];
        todayDate.setText(date);

        // To do: display entries
        LinearLayout entries =(LinearLayout) findViewById(R.id.entries);

        final SparkDataBase sdb = new SparkDataBase(this);
        List<ArrayList<String>> diarySummary = sdb.getDiarySummary(date);
        ArrayList<String> time = diarySummary.get(0);
        ArrayList<String> summary = diarySummary.get(1);
        int numEntries = time.size();
        List<TextView> entriesList = new ArrayList<TextView>();
        for (int i = 0; i < Math.min(3, numEntries); i++) {
            ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            TextView tv = new TextView(this);
            tv.setLayoutParams(lparams);
            String t = time.get(i);
            String e = summary.get(i);
            tv.setText(t + "   " + e);
            entries.addView(tv);
            entriesList.add(tv);
        }
        for (int j = 0; j < entriesList.size(); j++) {
            e = entriesList.get(j);
            e.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] timeEntry = e.getText().toString().split("   ");
                    String t = timeEntry[0];
                    Intent myIntent = new Intent(v.getContext(), DiaryEntryEditActivity.class);
                    ArrayList<String> details = sdb.getSingleDiary(date, t);
                    String[] detailsArr = new String[details.size()];
                    detailsArr = details.toArray(detailsArr);
                    myIntent.putExtra("details", detailsArr);
                    myIntent.putExtra("date", date);
                    myIntent.putExtra("time", t);
                    v.getContext().startActivity(myIntent);
                }
            });
        }

        final ImageView sw = (ImageView) findViewById(R.id.sleep_wake);
        final SharedPreferences sleep_wake = getSharedPreferences(PREFS, 0);
        final SharedPreferences.Editor editor = sleep_wake.edit();
        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean wake = sleep_wake.getBoolean("wake", true);
                if (wake) {
                    sw.setImageResource(R.drawable.wake);
                    editor.putBoolean("wake", false);
                    editor.commit();
                } else {
                    sw.setImageResource(R.drawable.sleep);
                    editor.putBoolean("wake", true);
                    editor.commit();
                }
            }
        });


        newEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DiaryEntryEditActivity.class);
                intent.putExtra("date", date);
                intent.putExtra("time", currTime);
                v.getContext().startActivity(intent);
            }
        });
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
