package com.challenge.bennho.a30days.models;

/**
 * Created by sionglengho on 10/9/17.
 */

public class RunHistoryModel {

    private String currentExerciseDay;
    private String totalCaloriesBurnt;
    private String totalRunningSecs;

    public RunHistoryModel() {
    }

    public String getCurrentExerciseDay() {
        return currentExerciseDay;
    }

    public void setCurrentExerciseDay(String currentExerciseDay) {
        this.currentExerciseDay = currentExerciseDay;
    }

    public String getTotalCaloriesBurnt() {
        return totalCaloriesBurnt;
    }

    public void setTotalCaloriesBurnt(String totalCaloriesBurnt) {
        this.totalCaloriesBurnt = totalCaloriesBurnt;
    }

    public String getTotalRunningSecs() {
        return totalRunningSecs;
    }

    public void setTotalRunningSecs(String totalRunningSecs) {
        this.totalRunningSecs = totalRunningSecs;
    }
}



