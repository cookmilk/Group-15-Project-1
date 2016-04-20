package com.example.jeremy.spark;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ChallengePage2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_page2);

        View myView = findViewById(R.id.imageButton);

        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(ChallengePage2Activity.this, ChallengePage3Activity.class));
            }
        });
        myView.setOnTouchListener(new OnSwipeTouchListener(this) {
            public boolean onSwipeTop() {
                //startActivity(new Intent(HomeScreenActivity.this, MenuPageActivity.class));
                return true;
            }

            public boolean onSwipeRight() {
                //startActivity(new Intent(HomeScreenActivity.this, HomeScreenActivity.class));
                return true;
            }

            public boolean onSwipeLeft() {
                //startActivity(new Intent(HomeScreenActivity.this, HomeScreenActivity.class));
                return true;
            }

            public boolean onSwipeBottom() {
                return true;
            }

        });
    }

}
