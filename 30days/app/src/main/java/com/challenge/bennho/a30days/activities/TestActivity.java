package com.challenge.bennho.a30days.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.helpers.AndroidUtils;
import com.challenge.bennho.a30days.helpers.CalculationHelper;
import com.challenge.bennho.a30days.helpers.Logs;
import com.challenge.bennho.a30days.models.ExerciseModel;
import com.challenge.bennho.a30days.models.ExercisePartModel;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        plans();
    }

    private void plans(){
        ArrayList<ExerciseModel> exerciseModels = new ArrayList();

        //Day 1
        ExerciseModel day1 = new ExerciseModel();
        day1.addExercisePartModel(new ExercisePartModel(0, ExercisePartModel.ExerciseState.CoolDown, 30));
        exerciseModels.add(day1);


        //Day 2




        printAllExerciseModels(exerciseModels);
    }

    private void printAllExerciseModels(ArrayList<ExerciseModel> exerciseModels){
        int i = 0;
        for(ExerciseModel exerciseModel :exerciseModels){
            Logs.show("Day " + (i + 1) + " Duration: "  +
                            CalculationHelper.prettifySeconds(exerciseModel.getTotalDurationSecs()));
        }
    }


}
