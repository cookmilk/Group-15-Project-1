package com.example.tony.spark;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

public class DiaryEntryEditActivity extends AppCompatActivity {
    final SparkDataBase sdb = new SparkDataBase(this);
    int mood = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_entry_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            Window w = this.getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //status bar height
            int statusBarHeight = (int) Math.ceil(25 * this.getResources().getDisplayMetrics().density);

            View view = new View(this);
            view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            view.getLayoutParams().height = statusBarHeight;
            ((ViewGroup) w.getDecorView()).addView(view);
            view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorLightGreenDark));
        }

        final EditText initial_thoughts = (EditText)findViewById(R.id.initial_thoughts);
        final EditText action_plan = (EditText) findViewById(R.id.action_plan);
        final EditText cha = (EditText) findViewById(R.id.challenge);

        Intent intent = getIntent();
        final String date = intent.getStringExtra("date");
        final String time = intent.getStringExtra("time");
        String[] details = intent.getStringArrayExtra("details");
        if (details != null) {
            String initialThoughts = details[0];
            String actionPlan = details[1];
            String challenge = details[2];
            mood = Integer.valueOf(details[3]);
            initial_thoughts.setText(initialThoughts);
            action_plan.setText(actionPlan);
            cha.setText(challenge);
        }

        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String it = initial_thoughts.getText().toString();
                String ap = action_plan.getText().toString();
                String c = cha.getText().toString();
                sdb.insertDiary(date, time, it, ap, c, mood);
                startActivity(new Intent(DiaryEntryEditActivity.this, HomeScreenMenuActivity.class));
            }
        });
    }

}
