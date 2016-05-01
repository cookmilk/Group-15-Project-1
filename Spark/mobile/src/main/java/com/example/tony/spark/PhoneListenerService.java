package com.example.tony.spark;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

public class PhoneListenerService extends WearableListenerService {

    //   WearableListenerServices don't need an iBinder or an onStartCommand: they just need an onMessageReceieved.
    private static final String NOTICE = "/path";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.i("WE HERE", "WE REALLY IN HERE");
        Log.d("T", "BUT DID U KNOW got: " + messageEvent.getPath());
        if( messageEvent.getPath().equalsIgnoreCase(NOTICE) ) {

            // Value contains the String we sent over in WatchToPhoneService, "good job"
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);

            // Make a toast with the String
            Log.i("WE GOT THE TOAST", value);
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, value, duration);
            toast.show();

        } else {
            super.onMessageReceived( messageEvent );
        }

    }
}
