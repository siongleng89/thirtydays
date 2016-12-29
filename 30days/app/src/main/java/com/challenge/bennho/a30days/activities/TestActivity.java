package com.challenge.bennho.a30days.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.helpers.AndroidUtils;
import com.challenge.bennho.a30days.helpers.CalculationHelper;
import com.challenge.bennho.a30days.helpers.Logs;
import com.challenge.bennho.a30days.helpers.PlansInputter;
import com.challenge.bennho.a30days.models.ExerciseModel;
import com.challenge.bennho.a30days.models.ExercisePartModel;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        PlansInputter plansInputter = new PlansInputter();
        plansInputter.print();
    }



}
