package com.example.tony.spark;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.wearable.view.GridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DayActivity extends Activity {
    String steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        steps = intent.getStringExtra("steps");
        if(steps == null){
            Log.i("test", "1500");
            steps = "2331";
        }
        if (extras != null) {
            String candidateString = extras.getString("CANDIDATE");
            if (candidateString.equals("One")) {
                pager.setAdapter(new MyGridViewPagerAdapter(this, 0));
            } else if (candidateString.equals("Two")) {
                pager.setAdapter(new MyGridViewPagerAdapter(this, 1));
            } else if (candidateString.equals("Three")) {
                pager.setAdapter(new MyGridViewPagerAdapter(this, 2));
            } else {
                pager.setAdapter(new MyGridViewPagerAdapter(this, 3));
            }

        } else {
            pager.setAdapter(new MyGridViewPagerAdapter(this, 3));
        }
    }

    private class MyGridViewPagerAdapter extends GridPagerAdapter {
        Context contexts;
        int rows;

        public MyGridViewPagerAdapter(final Context context, int row) {
            contexts = context;
            rows = row;
        }

        @Override
        public int getColumnCount(int arg0) {
            return 4;
        }

        @Override
        public int getRowCount() {
            return 1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int row, final int col) {
            final View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.detail_day, container, false);
            final TextView title = (TextView) view.findViewById(R.id.title);
            final TextView context = (TextView) view.findViewById(R.id.context);
            final TextView context2 = (TextView) view.findViewById(R.id.context2);



            if (col == 0) {

                title.setText("You are doing great!");
                context.setText("Swipe for more info");
            } else if (col == 1) {

                title.setText("You were active for");
                Log.i("This is steps", steps);
                context.setText(steps);
                context.setTextColor(Color.parseColor("#f8b45f"));
                context2.setText("29% of your goal");
            } else if (col == 2) {

                title.setText("You were inactive for");
                context.setText("2 hours 06 minutes");
                context.setTextColor(Color.parseColor("#ec5250"));
                context2.setText("52% of the day");
            } else {
                title.setText("You slept for");
                context.setTextColor(Color.parseColor("#bb6bcc"));
                context.setText("8 hours 23 minutes");
                context2.setText(" ");
            }

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int row, int col, Object view) {
            container.removeView((View)view);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
