package com.example.tony.spark;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.wearable.view.GridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MonthActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);
        final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
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
                title.setText("May 2016");
                context.setText("Average Mood");
                context.setTextColor(Color.parseColor("#6acc6d"));
                context2.setText("Happy");
            } else if (col == 1) {
                title.setText("May 2016");
                context.setText("Activity Average");
                context.setTextColor(Color.parseColor("#f8b45f"));
                context2.setText("11913 Steps");
            } else if (col == 2) {
                title.setText("May 2016");
                context.setText("Inactivity Average");
                context.setTextColor(Color.parseColor("#ec5250"));
                context2.setText("6 hours 12 minutes");
            } else {
                title.setText("May 2016");
                context.setText("Sleep Average");
                context.setTextColor(Color.parseColor("#bb6bcc"));
                context2.setText("6 hours 20 minutes");
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
