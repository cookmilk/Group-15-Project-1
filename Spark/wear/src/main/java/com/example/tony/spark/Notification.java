package com.example.tony.spark;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.TextView;

public class Notification extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        TextView happy = (TextView) findViewById(R.id.textView4);
        TextView sad = (TextView) findViewById(R.id.textView7);
        TextView neutral = (TextView) findViewById(R.id.textView6);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                launchClick(v);
            }
        };
        happy.setOnClickListener(listener);
        sad.setOnClickListener(listener);
        neutral.setOnClickListener(listener);
    }
    private void launchClick (View v){
        new CountDownTimer(2000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                //set the new Content of your activity
                setContentView(R.layout.activity_done);
            }
        }.start();
        Log.i("WE HERE", "TRYNA PRINT");
        Intent sendIntent = new Intent(this, WatchToPhoneService.class);
        this.startService(sendIntent);
    }

}
