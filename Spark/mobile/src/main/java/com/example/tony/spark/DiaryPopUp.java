package com.example.tony.spark;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tony on 4/30/2016.
 */
public class DiaryPopUp extends DialogFragment {
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String date = savedInstanceState.getString("date");
        final SparkDataBase sdb = new SparkDataBase(getActivity());
        List<ArrayList<String>> diarySummaries = sdb.getDiarySummary(date);
        final List<String> times = diarySummaries.get(0);
        List<String> entries = diarySummaries.get(1);
        String[] entriesArr = new String[entries.size()];
        entriesArr = entries.toArray(entriesArr);
        builder.setTitle(date)
                .setItems(entriesArr, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        Intent myIntent = new Intent(getActivity(), DiaryEntryEditActivity.class);
                        String time = times.get(which);
                        ArrayList<String> details = sdb.getSingleDiary(date, time);
                        myIntent.putExtra("details", details);
                        getActivity().startActivity(myIntent);
                    }
                });
        return builder.create();
    }
}
