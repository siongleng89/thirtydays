package com.challenge.bennho.a30days.helpers;

import android.content.Context;

import com.challenge.bennho.a30days.models.ExerciseModel;
import com.challenge.bennho.a30days.models.ExercisePartModel;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sionglengho on 30/12/16.
 */

public class PlansInputter {

    ArrayList<ExerciseModel> exerciseModels;
    private Context context;

    public PlansInputter(Context context) {
        this.context = context;
        exerciseModels = new ArrayList();
        input();
    }


    public void input(){
        //Day 1
        ExerciseModel day1 = new ExerciseModel();
        day1.addExercisePartModel(new ExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 30));
        day1.addExercisePartModel(new ExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60));
        day1.addExercisePartModel(new ExercisePartModel(ExercisePartModel.ExerciseState.Run, 60));
        day1.addExercisePartModel(new ExercisePartModel(ExercisePartModel.ExerciseState.Walk, 10));
        exerciseModels.add(day1);


        //Day 2
    }

    public void print(){
        int i = 0;
        for(ExerciseModel exerciseModel :exerciseModels){
            Logs.show("Day " + (i + 1) + " Duration: "  +
                    CalculationHelper.prettifySeconds(exerciseModel.getTotalDurationSecs()));

            Map<String, Float> dictionary = new HashMap<String, Float>();
            ArrayList<String> stateTransitions = new ArrayList();
            for(ExercisePartModel exercisePartModel : exerciseModel.getExercisePartModels()){
                String exercisePartText = exercisePartModel.getExerciseText(context);
                stateTransitions.add(exercisePartText + " " +
                        CalculationHelper.prettifySeconds(exercisePartModel.getDurationSecs()));

                if(dictionary.containsKey(exercisePartText)){
                    Float currentDuration = dictionary.get(exercisePartText);
                    currentDuration += exercisePartModel.getDurationSecs();
                    dictionary.put(exercisePartText, currentDuration);
                }
                else{
                    dictionary.put(exercisePartText, exercisePartModel.getDurationSecs());
                }

            }

            ArrayList<String> dictResults = new ArrayList();
            for (Map.Entry<String, Float> entry : dictionary.entrySet()) {
                String key = entry.getKey();
                Float value = entry.getValue();
                dictResults.add(key + ":" + CalculationHelper.prettifySeconds(value));
            }

            Logs.show("--------------------------------------------------------------------------------------");
            Logs.show("Printing exercise state transitions");
            Logs.show(Strings.joinArr(stateTransitions, " / "));
            Logs.show("--------------------------------------------------------------------------------------");
            Logs.show("Printing each state duration");
            Logs.show(Strings.joinArr(dictResults, " / "));
            Logs.show("--------------------------------------------------------------------------------------");

        }
    }

}
