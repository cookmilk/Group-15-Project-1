package com.example.tony.spark;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.GridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Notification extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        LinearLayout dayLog = (LinearLayout) findViewById(R.id.clicker);

        dayLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Notification.this, Done.class);
                startActivity(i);
            }
        });
//        final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);
//
//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        // if (extras != null) {
//        //     int randInt = extras.getInt("ZIP");
//        //     ((Helper) this.getApplication()).setZip(randInt);
//        // }
//        if (extras != null) {
//            String candidateString = extras.getString("CANDIDATE");
//            if (candidateString.equals("One")) {
//                pager.setAdapter(new MyGridViewPagerAdapter(this, 0));
//            } else if (candidateString.equals("Two")) {
//                pager.setAdapter(new MyGridViewPagerAdapter(this, 1));
//            } else {
//                pager.setAdapter(new MyGridViewPagerAdapter(this, 2));
//            }
//
//        } else {
//            pager.setAdapter(new MyGridViewPagerAdapter(this, 2));
//        }
//
////        LinearLayout vote2012 = (LinearLayout) findViewById(R.id.clicker);
////
////        vote2012.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent i = new Intent(ResultActivity.this, VoteActivity.class);
////                startActivity(i);
////            }
////        });
    }

//    private class MyGridViewPagerAdapter extends GridPagerAdapter {
//        Context contexts;
//        int rows;
//
//        public MyGridViewPagerAdapter(final Context context, int row) {
//            contexts = context;
//            rows = row;
//        }
//
//        @Override
//        public int getColumnCount(int arg0) {
//            return 3;
//        }
//
//        @Override
//        public int getRowCount() {
//            return 1;
//        }
//
//
//
//        @Override
//        public void destroyItem(ViewGroup container, int row, int col, Object view) {
//            container.removeView((View)view);
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == object;
//        }
//    }
}
