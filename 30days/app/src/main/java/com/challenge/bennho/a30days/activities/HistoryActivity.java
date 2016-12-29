package com.challenge.bennho.a30days.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.controls.BottomBar;

public class HistoryActivity extends AppCompatActivity {

    private BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setCurrentSelectedPageIndex(1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
}
