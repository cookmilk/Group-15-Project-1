package com.example.yshen.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yshen on 4/28/2016.
 */
public class SparkDataBase extends SQLiteOpenHelper {
    public static final String SPARK_DATABASE_NAME = "Spark.db";
    public static final String ACTIVE_TABLE_NAME = "active";
    public static final String INACTIVE_TABLE_NAME = "inactive";
    public static final String MOOD_TABLE_NAME = "mood";
    public static final String SLEEP_TABLE_NAME = "sleep";
    public static final String DIARY_TABLE_NAME = "diary";

    public SparkDataBase(Context context)
    {
        super(context, SPARK_DATABASE_NAME , null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table active " +
                        "(id integer primary key autoincrement, date text, step_counts integer)"
        );
        db.execSQL(
                "create table inactive " +
                        "(id integer primary key autoincrement, date text, inactive_seconds integer)"
        );
        db.execSQL(
                "create table mood " +
                        "(id integer primary key autoincrement, date text, level integer)"
        );
        db.execSQL(
                "create table sleep " +
                        "(id integer primary key autoincrement, date text, sleep_seconds integer)"
        );
        db.execSQL(
                "create table diary " +
                        "(id integer primary key autoincrement, date text, time text, initial_thoughts text, action_plan text, challenge text, mood integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS active");
        db.execSQL("DROP TABLE IF EXISTS inactive");
        db.execSQL("DROP TABLE IF EXISTS mood");
        db.execSQL("DROP TABLE IF EXISTS sleep");
        db.execSQL("DROP TABLE IF EXISTS diary");
        onCreate(db);
    }

    public boolean insertActive  (String date, int stepCounts)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("step_counts", stepCounts);
        contentValues.put("date", date);
        db.insert("active", null, contentValues);
        return true;
    }

    public boolean insertInactive  (String date, int inactiveSecs)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("inactive_seconds", inactiveSecs);
        db.insert("inactive", null, contentValues);
        return true;
    }

    public boolean insertMood  (String date, int moodLevel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("level", moodLevel);
        db.insert("mood", null, contentValues);
        return true;
    }

    public boolean insertSleep  (String date, int sleepSecs)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("sleep_seconds", sleepSecs);
        db.insert("sleep", null, contentValues);
        return true;
    }

    public boolean insertDiary  (String date, String time, String initialThoughts, String actionPlan, String challenge, int moodLevel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("time", time);
        contentValues.put("initial_thoughts", initialThoughts);
        contentValues.put("action_plan", actionPlan);
        contentValues.put("challenge", challenge);
        contentValues.put("mood", moodLevel);
        db.insert("diary", null, contentValues);
        return true;
    }

    public Cursor getActive(String date){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from active where date="+date+"", null );
        return res;
    }

    public Cursor getActivePastWeek() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select sum(step_counts) from active groupby date orderby date desc limit 7", null );
        return res;
    }

    public Cursor getActivePastMonth() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select sum(step_counts) from active groupby date orderby date desc limit 30", null );
        return res;
    }

    public Cursor getInactive(String date){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from inactive where date="+date+"", null );
        return res;
    }

    public Cursor getInactivePastWeek() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select sum(inactive_seconds) from inactive groupby date orderby date desc limit 7", null );
        return res;
    }

    public Cursor getInactivePastMonth() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select sum(inactive_seconds) from inactive groupby date orderby date desc limit 30", null );
        return res;
    }

    public Cursor getSleep(String date){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from sleep where date="+date+"", null );
        return res;
    }

    public Cursor getSleepPastWeek() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select sum(sleep_seconds) from sleep groupby date orderby date desc limit 7", null );
        return res;
    }

    public Cursor getSleepPastMonth() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select sum(sleep_seconds) from sleep groupby date orderby date desc limit 30", null );
        return res;
    }

    public Cursor getMood(String date){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from mood where date="+date+"", null );
        return res;
    }

    public Cursor getMoodPastWeek() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select avg(level) from mood groupby date orderby date desc limit 7", null );
        return res;
    }

    public Cursor getMoodPastMonth() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select sum(sleep_seconds) from sleep groupby date orderby date desc limit 30", null );
        return res;
    }

    public Cursor getDiarySummary(String date){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select time, initial_thoughts from diary where date="+date+"", null );
        return res;
    }

    public Cursor getSingleDiary(String date, String time) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select initial_thoughts, action_plan, challenge from diary where date="+date+" and time="+time+"", null);
        return res;
    }

    public int numberOfRowsActive(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, ACTIVE_TABLE_NAME);
        return numRows;
    }

    public int numberOfRowsInactive(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, INACTIVE_TABLE_NAME);
        return numRows;
    }

    public int numberOfRowsSleep(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, SLEEP_TABLE_NAME);
        return numRows;
    }

    public int numberOfRowsMood(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, MOOD_TABLE_NAME);
        return numRows;
    }

    public int numberOfRowsDiary(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, DIARY_TABLE_NAME);
        return numRows;
    }

    public boolean updateDiary (Integer id, long time, String initialThoughts, String actionPlan, String challenge, int moodLevel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date_time", time);
        contentValues.put("initial_thoughts", initialThoughts);
        contentValues.put("action_plan", actionPlan);
        contentValues.put("challenge", challenge);
        contentValues.put("mood", moodLevel);
        db.update("diary", contentValues, "date_time = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteDiary (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("diary",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllDiaries()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from diary", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex("date_time")));
            res.moveToNext();
        }
        return array_list;
    }
}
