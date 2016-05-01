package com.example.tony.spark;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DiaryEntryEditActivity extends AppCompatActivity {
    EditText initial_thoughts = (EditText)findViewById(R.id.initial_thoughts);
    EditText action_plan = (EditText) findViewById(R.id.action_plan);
    EditText cha = (EditText) findViewById(R.id.challenge);
    final SparkDataBase sdb = new SparkDataBase(this);
    int mood = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_diary_entry_edit);

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
            }
        });
    }

}
