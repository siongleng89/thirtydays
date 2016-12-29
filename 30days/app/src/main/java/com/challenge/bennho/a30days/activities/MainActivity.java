package com.challenge.bennho.a30days.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.controls.BottomBar;
import com.challenge.bennho.a30days.controls.LayoutDayCounter;

public class MainActivity extends MyActivity {

    private LayoutDayCounter dayCounterControl;
    private BottomBar bottomBar;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomBar = (BottomBar) findViewById(R.id.bottomBar) ;
        dayCounterControl = (LayoutDayCounter) findViewById(R.id.dayCounterControl);
        btnStart = (Button) findViewById(R.id.btnStart);

        dayCounterControl.updateDayNumber(1);
        bottomBar.setCurrentSelectedPageIndex(0);

        setListeners();
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    private void startExercise(){
        Intent intent = new Intent(this, ReadyActivity.class);
        startActivity(intent);
    }


    private void setListeners(){
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startExercise();
            }
        });
    }



}
