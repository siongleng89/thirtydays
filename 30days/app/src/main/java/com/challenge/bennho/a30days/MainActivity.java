package com.challenge.bennho.a30days;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.challenge.bennho.a30days.controls.BottomBar;
import com.challenge.bennho.a30days.controls.LayoutDayCounter;

public class MainActivity extends AppCompatActivity {

    private LayoutDayCounter dayCounterControl;
    private BottomBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Main Page");

        bar = (BottomBar) findViewById(R.id.bottomBar) ;
        dayCounterControl = (LayoutDayCounter) findViewById(R.id.dayCounterControl);

        dayCounterControl.updateDayNumber(0);

        bar.setIsMainActivity(true);

    }
}
