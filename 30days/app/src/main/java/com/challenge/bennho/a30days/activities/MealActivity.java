package com.challenge.bennho.a30days.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.challenge.bennho.a30days.R;

public class MealActivity extends MyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        onLayoutSet();

        setTitle("Day 3 Meal Plan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}
