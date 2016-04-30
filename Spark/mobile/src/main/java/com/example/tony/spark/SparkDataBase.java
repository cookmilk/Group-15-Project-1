package com.example.tony.spark;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
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
        super(context, SPARK_DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table active " +
                        "(id integer primary key autoincrement, date text, time text, step_counts integer)"
        );
        db.execSQL(
                "create table inactive " +
                        "(id integer primary key autoincrement, date text, time text, inactive_seconds integer)"
        );
        db.execSQL(
                "create table mood " +
                        "(id integer primary key autoincrement, date text, time text, level integer)"
        );
        db.execSQL(
                "create table sleep " +
                        "(id integer primary key autoincrement, date text, time text, sleep_seconds integer)"
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

    public boolean insertActive (String date, String time, int stepCounts)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("step_counts", stepCounts);
        contentValues.put("date", date);
        contentValues.put("time", time);
        db.insert("active", null, contentValues);
        return true;
    }

    public boolean insertInactive (String date, String time, int inactiveSecs)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("time", time);
        contentValues.put("inactive_seconds", inactiveSecs);
        db.insert("inactive", null, contentValues);
        return true;
    }

    public boolean insertMood (String date, String time, int moodLevel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("time", time);
        contentValues.put("level", moodLevel);
        db.insert("mood", null, contentValues);
        return true;
    }

    public boolean insertSleep (String date, String time, int sleepSecs)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("time", time);
        contentValues.put("sleep_seconds", sleepSecs);
        db.insert("sleep", null, contentValues);
        return true;
    }

    public boolean insertDiary (String date, String time, String initialThoughts, String actionPlan, String challenge, int moodLevel)
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

    public ArrayList<ArrayList<String>> getActive(String date){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select time, step_counts from active where date=?", new String[] {date} );
        return cursorToArrayList(res);
    }

    public ArrayList<ArrayList<String>> getActivePastWeek() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select date, sum(step_counts) from active group by date order by date desc limit 7", null );
        return cursorToArrayList(res);
    }

    public ArrayList<ArrayList<String>> getActivePastMonth() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select date, sum(step_counts) from active group by date order by date desc limit 30", null );
        return cursorToArrayList(res);
    }

    public ArrayList<ArrayList<String>> getInactive(String date){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select time, inactive_seconds from inactive where date=?", new String[] {date} );
        return cursorToArrayList(res);
    }

    public ArrayList<ArrayList<String>> getInactivePastWeek() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select date, sum(inactive_seconds) from inactive group by date order by date desc limit 7", null );
        return cursorToArrayList(res);
    }

    public ArrayList<ArrayList<String>> getInactivePastMonth() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select date, sum(inactive_seconds) from inactive group by date order by date desc limit 30", null );
        return cursorToArrayList(res);
    }

    public ArrayList<ArrayList<String>> getSleep(String date){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select time, sleep_seconds from sleep where date=?", new String[] {date} );
        return cursorToArrayList(res);
    }

    public ArrayList<ArrayList<String>> getSleepPastWeek() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select date, sum(sleep_seconds) from sleep group by date order by date desc limit 7", null );
        return cursorToArrayList(res);
    }

    public ArrayList<ArrayList<String>> getSleepPastMonth() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select date, sum(sleep_seconds) from sleep group by date order by date desc limit 30", null );
        return cursorToArrayList(res);
    }

    public ArrayList<ArrayList<String>> getMood(String date){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select time, level from mood where date=?", new String[] {date} );
        return cursorToArrayList(res);
    }

    public ArrayList<ArrayList<String>> getMoodPastWeek() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select date, avg(level) from mood group by date order by date desc limit 7", null );
        return cursorToArrayList(res);
    }

    public ArrayList<ArrayList<String>> getMoodPastMonth() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select date, avg(level) from mood group by date order by date desc limit 30", null );
        return cursorToArrayList(res);
    }

    public ArrayList<ArrayList<String>> getDiarySummary(String date){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select time, initial_thoughts from diary where date=?", new String[]{date});
        return cursorToArrayList(res);
    }

    public ArrayList<String> getSingleDiary(String date, String time) {
        ArrayList<String> result = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select initial_thoughts, action_plan, challenge, mood from diary where date=? and time=?", new String[] {date, time});
        res.moveToFirst();
        result.add(res.getString(0));
        result.add(res.getString(1));
        result.add(res.getString(2));
        result.add(String.valueOf(res.getInt(3)));
        return result;
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

    public boolean updateDiary (String date, String time, String initialThoughts, String actionPlan, String challenge, int moodLevel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("initial_thoughts", initialThoughts);
        contentValues.put("action_plan", actionPlan);
        contentValues.put("challenge", challenge);
        contentValues.put("mood", moodLevel);
        db.update("diary", contentValues, "date = ? and time = ? ", new String[] { date, time } );
        return true;
    }

    public Integer deleteDiary (String date, String time)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("diary",
                "date = ? and time = ?",
                new String[] { date, time });
    }

    public ArrayList<ArrayList<String>> cursorToArrayList(Cursor cursor) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        ArrayList<String> time = new ArrayList<String>();
        ArrayList<String> value = new ArrayList<String>();
        cursor.moveToFirst();
        int count = cursor.getCount();
        for (int i = 0; i < count; i++) {
            time.add(cursor.getString(0));
            value.add(cursor.getString(1));
            cursor.moveToNext();
        }
        result.add(time);
        result.add(value);
        return result;
    }

//    public ArrayList<String> getAllDiaries()
//    {
//        ArrayList<String> array_list = new ArrayList<String>();
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from diary", null );
//        res.moveToFirst();
//
//        while(res.isAfterLast() == false){
//            array_list.add(res.getString(res.getColumnIndex("date")));
//            res.moveToNext();
//        }
//        return array_list;
//    }
}
