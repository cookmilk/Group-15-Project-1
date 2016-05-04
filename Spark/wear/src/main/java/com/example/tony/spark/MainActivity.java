package com.example.tony.spark;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;

public class MainActivity extends WearableActivity implements SensorEventListener {

//    private TextView mTextView;
    private BoxInsetLayout mContainerView;
    private TextView text;
    String sendText = "2914 steps";
    private final int notification_id = 1;

    /* These are the classes you use to start the notification */
    private NotificationCompat.Builder notification_builder;
    private NotificationManagerCompat notification_manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout dayLog = (LinearLayout) findViewById(R.id.day);
        text = (TextView) findViewById(R.id.textView2);
        dayLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DayActivity.class);
                startActivity(i);
            }
        });
        int notificationId = 001;
        String intent = getIntent().getStringExtra("data");

        if (intent != null){
            final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.logo)
                    .setContentTitle("Suggestions!")
                    .setContentText(intent);

            Intent resultIntent = new Intent(this, Notification.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addParentStack(Notification.class);
            stackBuilder.addNextIntent(resultIntent);

            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                    0, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(resultPendingIntent);
            final NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(notification_id, mBuilder.build());
        }
// Build intent for notification content


// Build the notification and issues it with notification manager.

        LinearLayout monthLog = (LinearLayout) findViewById(R.id.month);

        monthLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, MonthActivity.class);
                startActivity(i);
            }
        });

        setAmbientEnabled();

        mContainerView = (BoxInsetLayout) findViewById(R.id.container);



        mContainerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Notification.class);
                i.putExtra("steps", sendText);
                startActivity(i);
            }
        });

        getStepCount();
    }
    private void getStepCount(){
        SensorManager mSensorManager = ((SensorManager) getSystemService(SENSOR_SERVICE));
        Sensor mStepCountSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        mSensorManager.registerListener(this, mStepCountSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            Integer value = (int) event.values[0];
            Log.i("THIS IS STEPS", value.toString());
            sendText = (value.toString() + " steps");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
