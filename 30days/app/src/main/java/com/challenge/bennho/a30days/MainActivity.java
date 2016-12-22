package com.challenge.bennho.a30days;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.challenge.bennho.a30days.controls.LayoutDayCounter;

public class MainActivity extends AppCompatActivity {

    private LayoutDayCounter dayCounterControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Main Page");


        dayCounterControl = (LayoutDayCounter) findViewById(R.id.dayCounterControl);
        dayCounterControl.updateDayNumber(0);
    }
}
