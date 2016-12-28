package com.challenge.bennho.a30days.models;

import java.util.ArrayList;

/**
 * Created by sionglengho on 26/12/16.
 */

public class ExerciseModel {

    private ArrayList<ExercisePartModel> exercisePartModels;
    private float totalDurationSecs;

    public ExerciseModel() {
        exercisePartModels = new ArrayList();
    }

    public void addExercisePartModel(ExercisePartModel exercisePartModel){
        this.totalDurationSecs = 0;
        exercisePartModels.add(exercisePartModel);
    }

    public float getTotalDurationSecs(){
        if(totalDurationSecs == 0){
            for(ExercisePartModel exercisePartModel : exercisePartModels){
                totalDurationSecs += exercisePartModel.getDurationSecs();
            }
        }
        return totalDurationSecs;
    }

    public ArrayList<ExercisePartModel> getExercisePartModels() {
        return exercisePartModels;
    }

    public int getExercisePartModelIndex(ExercisePartModel exercisePartModel){
        int i = 0;
        for(ExercisePartModel model : exercisePartModels){
            if(model == exercisePartModel){
                return i;
            }
            i++;
        }
        return -1;
    }

}
