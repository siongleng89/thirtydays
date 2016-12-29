package com.challenge.bennho.a30days.helpers;

import com.challenge.bennho.a30days.models.ExerciseModel;
import com.challenge.bennho.a30days.models.ExercisePartModel;

import java.util.ArrayList;

/**
 * Created by sionglengho on 30/12/16.
 */

public class PlansInputter {

    ArrayList<ExerciseModel> exerciseModels;

    public PlansInputter() {
        exerciseModels = new ArrayList();
        input();
    }


    public void input(){
        //Day 1
        ExerciseModel day1 = new ExerciseModel();
        day1.addExercisePartModel(new ExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 30));
        day1.addExercisePartModel(new ExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60));
        exerciseModels.add(day1);


        //Day 2
    }

    public void print(){
        int i = 0;
        for(ExerciseModel exerciseModel :exerciseModels){
            Logs.show("Day " + (i + 1) + " Duration: "  +
                    CalculationHelper.prettifySeconds(exerciseModel.getTotalDurationSecs()));
        }
    }

}
