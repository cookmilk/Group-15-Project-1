package com.example.tony.spark;

import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.graphics.Paint;

import com.androidplot.Plot;
import com.androidplot.util.PixelUtils;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


public class AnalyticsActivity extends AppCompatActivity {
    private XYPlot mySimpleXYPlot;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    Number[] x;
    Number[] y_sleep;
    Number[] y_inactivity;
    Number[] y_activity;
    Number[] y_mood;

    XYSeries moodPlot;
    XYSeries inactivityPlot;
    XYSeries sleepPlot;
    XYSeries activityPlot;

    CheckBox mood;
    CheckBox activity;
    CheckBox inactivity;
    CheckBox sleep;

    LineAndPointFormatter moodFormat;
    LineAndPointFormatter activityFormat;
    LineAndPointFormatter inactivityFormat;
    LineAndPointFormatter sleepFormat;

    final SparkDataBase sdb = new SparkDataBase(this);

    final Number[] t_dates = {1459807533, 1459893933, 1459980333,
            1460066733, 1460153133, 1460239533, 1460325933, 1460412333, 1460498733, 1460585133,
            1460671533, 1460757933, 1460844333, 1460930733, 1461017133, 1461103533, 1461189748,
            1461276148, 1461362548, 1461448948, 1461535348, 1461621748, 1461708148,
            1461794549, 1461881133, 1461967533, 1462053933, 1462140333, 1462226733, 1462313133};
    final Number[] t_moods = {1, 4, 3, 5, 2.3, 4.8, 3.1, 2.1, 2.4, 3.4,
            4.1, 2.1, 1.1, 2, 4, 5, 4.5, 2, 5, 2,
            2, 4, 2.2, 4.2, 5, 2.2, 4.2, 4, 4.99, 4.82};
    final Number[] t_sleep = {5, 6, 7, 8.2, 9, 10, 12, 10, 8, 5,
            5, 5, 5, 6, 7, 2, 5, 6, 7, 8,
            3, 4, 5, 7, 10, 8, 8, 8, 10, 11};
    final Number[] t_inactivity = {4, 2, 4.1, 5.2, 7, 1, 4.3, 5.1, 8.1, 1,
            5.24, 2.21, 8.63, 6.62, 9.2, 10, 5.23, 6.23, 7.56, 9.1,
            10.01, 1.14, 12, 4.92, 6.02, 7, 9.03, 10.24, 11.35, 8.25};
    final Number[] t_activity = {11.1,12.65, 14, 12, 10, 8, 19, 10, 6, 17,
            10, 14, 7, 6, 9, 5, 8, 7, 9, 10,
            4, 9, 3, 5.2, 8.5, 6.5, 8.1, 5.5, 4.2, 8.9};

    final Number[] s_dates = {1461794549, 1461881133, 1461967533, 1462053933, 1462140333, 1462226733, 1462313133};
    final Number[] s_sleep = {7, 10.1, 8.1, 8.2, 8.3, 10.2, 8.01};
    final Number[] s_inactivity = {4.92, 6.02, 7.2, 9.03, 10.24, 11.35, 8.25};
    final Number[] s_activity = {5.2, 8.5, 6.5, 8.1, 5.5, 4.2, 8.9};
    final Number[] s_moods = {4.2, 5, 2.2, 4.1, 4, 4.99, 4.82};

    Number[] d_dates ={1462246224,1462249824,1462253424,1462257053,
            1462260653,1462264253,1462269653,1462273253,1462276853,1462280453,
            1462284053,1462287653,1462291253};
    Number[] d_sleep = {0.001,1.12, 2.13, 3.12, 4.12, 5.12, 6.12, 7.125, 8.121,8.122,8.012,8.002,8.001};
    Number[] d_inactivity = {.01, .02, .03,.0343,.344,.341,.021,.022,.5,1.2,1.5,2.002,2.45};
    Number[] d_activity = {.001,.002,.003,.004,.005,.0051,.0052,.0032,.052,.5,1.23,1.78,2.15};
    Number[] d_moods = {.0112,.00113,.0013,.0014,.0023,.001,.006,.0015,.0031,2.99,4.99,4.87,4.92};

    Format monthly = new Format() {
        private SimpleDateFormat dateFormat = new SimpleDateFormat("M/dd");
        @Override
        public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
            // because our timestamps are in seconds and SimpleDateFormat expects milliseconds
            // we multiply our timestamp by 1000:
            long timestamp = ((Number) obj).longValue() * 1000;
            Date date = new Date(timestamp);
            return dateFormat.format(date, toAppendTo, pos);
        }
        @Override
        public Object parseObject(String source, ParsePosition pos) {
            return null;
        }
    };

    View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v) {
            Log.i("DID YOU KNOW", "IT CRASHES");
            mySimpleXYPlot.clear();
            if (mood.isChecked()) {
                mySimpleXYPlot.addSeries(moodPlot, moodFormat);
            }
            if (activity.isChecked()) {
                mySimpleXYPlot.addSeries(activityPlot, activityFormat);
            }
            if (inactivity.isChecked()) {
                mySimpleXYPlot.addSeries(inactivityPlot, inactivityFormat);
            }
            if (sleep.isChecked()) {
                mySimpleXYPlot.addSeries(sleepPlot, sleepFormat);
            }
            mySimpleXYPlot.redraw();

        }
    };

    AdapterView.OnItemSelectedListener listen = new AdapterView.OnItemSelectedListener(){
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            // your code here
            if (position == 0) {
                Log.i("WE IN HER", "IN POSITIONS 1");
                x = d_dates;
                y_sleep = d_sleep;
                y_inactivity = d_inactivity;
                y_activity = d_activity;
                y_mood = d_moods;

            } else if (position == 1) {
                Log.i("WE IN HERE TOO", "IN OS2");
                x = s_dates;
                y_sleep = s_sleep;
                y_inactivity = s_inactivity;
                y_activity = s_activity;
                y_mood = s_moods;

                mySimpleXYPlot.setDomainValueFormat(monthly);

            } else {
                x = t_dates;
                y_sleep = t_sleep;
                y_inactivity = t_inactivity;
                y_activity = t_activity;
                y_mood = t_moods;

                mySimpleXYPlot.setDomainValueFormat(monthly);
            }

            moodPlot = new SimpleXYSeries(Arrays.asList(x), Arrays.asList(y_mood), " ");
            inactivityPlot = new SimpleXYSeries(Arrays.asList(x), Arrays.asList(y_inactivity), " ");
            activityPlot = new SimpleXYSeries(Arrays.asList(x), Arrays.asList(y_activity), " ");
            sleepPlot = new SimpleXYSeries(Arrays.asList(x), Arrays.asList(y_sleep), " ");
            moodFormat = new LineAndPointFormatter();
            moodFormat.setPointLabelFormatter(new PointLabelFormatter());
            moodFormat.configure(getApplicationContext(),
                    R.xml.line_mood);
            moodFormat.setPointLabeler(null);
            // setup our line fill paint to be a slightly transparent gradient:
            activityFormat = new LineAndPointFormatter();
            activityFormat.setPointLabelFormatter(new PointLabelFormatter());
            activityFormat.configure(getApplicationContext(),
                    R.xml.line_activity);
            activityFormat.setPointLabeler(null);

            inactivityFormat = new LineAndPointFormatter();
            inactivityFormat.setPointLabelFormatter(new PointLabelFormatter());
            inactivityFormat.configure(getApplicationContext(),
                    R.xml.line_inactivity);
            inactivityFormat.setPointLabeler(null);
            sleepFormat = new LineAndPointFormatter();
            sleepFormat.setPointLabelFormatter(new PointLabelFormatter());
            sleepFormat.configure(getApplicationContext(),
                    R.xml.line_sleep);
            sleepFormat.setPointLabeler(null);

            mySimpleXYPlot.getGraphWidget().setPaddingRight(2);
//        Paint color = new Paint(Color.parseColor("#FFFFFF"));
//        mySimpleXYPlot.getGraphWidget().setDomainTickLabelPaint(color);
//        mySimpleXYPlot.getGraphWidget().getDomainTickLabelPaint().setTextSize(PixelUtils.spToPix(13));
//        mySimpleXYPlot.getGraphWidget().getRangeTickLabelPaint().setTextSize(PixelUtils.spToPix(13));
//        mySimpleXYPlot.getGraphWidget().setRangeTickLabelPaint(color);

            // draw a domain tick for each year:
            mySimpleXYPlot.setDomainStep(XYStepMode.SUBDIVIDE, 7);

            mySimpleXYPlot.clear();
            if (mood.isChecked()) {
                mySimpleXYPlot.addSeries(moodPlot, moodFormat);
            }
            if (activity.isChecked()) {
                mySimpleXYPlot.addSeries(activityPlot, activityFormat);
            }
            if (inactivity.isChecked()) {
                mySimpleXYPlot.addSeries(inactivityPlot, inactivityFormat);
            }
            if (sleep.isChecked()) {
                mySimpleXYPlot.addSeries(sleepPlot, sleepFormat);
            }
            mySimpleXYPlot.redraw();
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
            x = s_dates;
            y_sleep = s_sleep;
            y_inactivity = s_inactivity;
            y_activity = s_activity;
            y_mood = s_moods;


            moodPlot = new SimpleXYSeries(Arrays.asList(x), Arrays.asList(y_mood), " ");
            inactivityPlot = new SimpleXYSeries(Arrays.asList(x), Arrays.asList(y_inactivity), " ");
            activityPlot = new SimpleXYSeries(Arrays.asList(x), Arrays.asList(y_activity), " ");
            sleepPlot = new SimpleXYSeries(Arrays.asList(x), Arrays.asList(y_sleep), " ");

            moodFormat = new LineAndPointFormatter();
            moodFormat.setPointLabelFormatter(new PointLabelFormatter());
            moodFormat.configure(getApplicationContext(),
                    R.xml.line_mood);
            moodFormat.setPointLabeler(null);
            // setup our line fill paint to be a slightly transparent gradient:
            activityFormat = new LineAndPointFormatter();
            activityFormat.setPointLabelFormatter(new PointLabelFormatter());
            activityFormat.configure(getApplicationContext(),
                    R.xml.line_activity);
            activityFormat.setPointLabeler(null);

            inactivityFormat = new LineAndPointFormatter();
            inactivityFormat.setPointLabelFormatter(new PointLabelFormatter());
            inactivityFormat.configure(getApplicationContext(),
                    R.xml.line_inactivity);
            inactivityFormat.setPointLabeler(null);
            sleepFormat = new LineAndPointFormatter();
            sleepFormat.setPointLabelFormatter(new PointLabelFormatter());
            sleepFormat.configure(getApplicationContext(),
                    R.xml.line_sleep);
            sleepFormat.setPointLabeler(null);

            mySimpleXYPlot.getGraphWidget().setPaddingRight(2);
//        Paint color = new Paint(Color.parseColor("#FFFFFF"));
//        mySimpleXYPlot.getGraphWidget().setDomainTickLabelPaint(color);
//        mySimpleXYPlot.getGraphWidget().getDomainTickLabelPaint().setTextSize(PixelUtils.spToPix(13));
//        mySimpleXYPlot.getGraphWidget().getRangeTickLabelPaint().setTextSize(PixelUtils.spToPix(13));
//        mySimpleXYPlot.getGraphWidget().setRangeTickLabelPaint(color);

            // draw a domain tick for each year:
            mySimpleXYPlot.setDomainStep(XYStepMode.SUBDIVIDE, 7);
            if (mood.isChecked()) {
                mySimpleXYPlot.addSeries(moodPlot, moodFormat);
            }
            if (activity.isChecked()) {
                mySimpleXYPlot.addSeries(activityPlot, activityFormat);
            }
            if (inactivity.isChecked()) {
                mySimpleXYPlot.addSeries(inactivityPlot, inactivityFormat);
            }
            if (sleep.isChecked()) {
                mySimpleXYPlot.addSeries(sleepPlot, sleepFormat);
            }
            mySimpleXYPlot.redraw();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mood = (CheckBox) findViewById(R.id.checkBox);
        activity = (CheckBox) findViewById(R.id.checkBox2);
        sleep = (CheckBox) findViewById(R.id.checkBox3);
        inactivity = (CheckBox) findViewById(R.id.checkBox4);
        Spinner spin = (Spinner) findViewById(R.id.spinner);

        mySimpleXYPlot = (XYPlot) findViewById(R.id.plot);

        y_mood = d_moods;
        x = d_dates;
        y_activity = d_activity;
        y_inactivity = d_inactivity;
        y_sleep= d_sleep;

        moodPlot = new SimpleXYSeries(Arrays.asList(x), Arrays.asList(y_mood), " ");
        inactivityPlot = new SimpleXYSeries(Arrays.asList(x), Arrays.asList(y_inactivity), " ");
        activityPlot = new SimpleXYSeries(Arrays.asList(x), Arrays.asList(y_activity), " ");
        sleepPlot = new SimpleXYSeries(Arrays.asList(x), Arrays.asList(y_sleep), " ");
        moodFormat = new LineAndPointFormatter();
        moodFormat.setPointLabelFormatter(new PointLabelFormatter());
        moodFormat.configure(getApplicationContext(),
                R.xml.line_mood);
        moodFormat.setPointLabeler(null);
        // setup our line fill paint to be a slightly transparent gradient:
        activityFormat = new LineAndPointFormatter();
        activityFormat.setPointLabelFormatter(new PointLabelFormatter());
        activityFormat.configure(getApplicationContext(),
                R.xml.line_activity);
        activityFormat.setPointLabeler(null);

        inactivityFormat = new LineAndPointFormatter();
        inactivityFormat.setPointLabelFormatter(new PointLabelFormatter());
        inactivityFormat.configure(getApplicationContext(),
                R.xml.line_inactivity);
        inactivityFormat.setPointLabeler(null);
        sleepFormat = new LineAndPointFormatter();
        sleepFormat.setPointLabelFormatter(new PointLabelFormatter());
        sleepFormat.configure(getApplicationContext(),
                R.xml.line_sleep);
        sleepFormat.setPointLabeler(null);

        mySimpleXYPlot.getGraphWidget().setPaddingRight(2);
//        Paint color = new Paint(Color.parseColor("#FFFFFF"));
//        mySimpleXYPlot.getGraphWidget().setDomainTickLabelPaint(color);
//        mySimpleXYPlot.getGraphWidget().getDomainTickLabelPaint().setTextSize(PixelUtils.spToPix(13));
//        mySimpleXYPlot.getGraphWidget().getRangeTickLabelPaint().setTextSize(PixelUtils.spToPix(13));
//        mySimpleXYPlot.getGraphWidget().setRangeTickLabelPaint(color);

        // draw a domain tick for each year:
        mySimpleXYPlot.setDomainStep(XYStepMode.SUBDIVIDE, 7);
        mySimpleXYPlot.redraw();


        spin.setOnItemSelectedListener(listen);


        mySimpleXYPlot.getGraphWidget().setPaddingRight(2);
//        Paint color = new Paint(Color.parseColor("#FFFFFF"));
//        mySimpleXYPlot.getGraphWidget().setDomainTickLabelPaint(color);
//        mySimpleXYPlot.getGraphWidget().getDomainTickLabelPaint().setTextSize(PixelUtils.spToPix(13));
//        mySimpleXYPlot.getGraphWidget().getRangeTickLabelPaint().setTextSize(PixelUtils.spToPix(13));
//        mySimpleXYPlot.getGraphWidget().setRangeTickLabelPaint(color);

        // draw a domain tick for each year:
        mySimpleXYPlot.setDomainStep(XYStepMode.SUBDIVIDE, 7);
        mySimpleXYPlot.setBorderStyle(Plot.BorderStyle.SQUARE, null, null);
        mySimpleXYPlot.getBorderPaint().setStrokeWidth(1);
        mySimpleXYPlot.getBorderPaint().setAntiAlias(false);
        mySimpleXYPlot.getBorderPaint().setColor(Color.WHITE);

        mySimpleXYPlot.getGraphWidget().getDomainOriginLinePaint().setColor(Color.BLACK);
        mySimpleXYPlot.getGraphWidget().getRangeOriginLinePaint().setColor(Color.BLACK);

        // Create a formatter to use for drawing a series using LineAndPointRenderer:// fill color

        // customize our domain/range labels
        mySimpleXYPlot.setDomainLabel("Date");
        mySimpleXYPlot.setRangeLabel(" ");

        // get rid of decimal points in our range labels:
        mySimpleXYPlot.setRangeValueFormat(new DecimalFormat("0"));


        mood.setOnClickListener(listener);
        activity.setOnClickListener(listener);
        inactivity.setOnClickListener(listener);
        sleep.setOnClickListener(listener);

        mySimpleXYPlot.setDomainStep(XYStepMode.SUBDIVIDE, 3);
        if (mood.isChecked()) {
            mySimpleXYPlot.addSeries(moodPlot, moodFormat);
        }
        if (activity.isChecked()) {
            mySimpleXYPlot.addSeries(activityPlot, activityFormat);
        }
        if (inactivity.isChecked()) {
            mySimpleXYPlot.addSeries(inactivityPlot, inactivityFormat);
        }
        if (sleep.isChecked()) {
            mySimpleXYPlot.addSeries(sleepPlot, sleepFormat);
        }
        mySimpleXYPlot.redraw();

        mySimpleXYPlot.setDomainValueFormat(new Format() {

            // create a simple date format that draws on the year portion of our timestamp.
            // see http://download.oracle.com/javase/1.4.2/docs/api/java/text/SimpleDateFormat.html
            // for a full description of SimpleDateFormat."h:mm a"
            private SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");

            @Override
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {

                // because our timestamps are in seconds and SimpleDateFormat expects milliseconds
                // we multiply our timestamp by 1000:
                long timestamp = ((Number) obj).longValue() * 1000;
                Date date = new Date(timestamp);
                return dateFormat.format(date, toAppendTo, pos);
            }

            @Override
            public Object parseObject(String source, ParsePosition pos) {
                return null;

            }
        });
        // by default, AndroidPlot displays developer guides to aid in laying out your plot.
        // To get rid of them call disableAllMarkup()

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Analytics Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.tony.spark/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Analytics Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.tony.spark/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
