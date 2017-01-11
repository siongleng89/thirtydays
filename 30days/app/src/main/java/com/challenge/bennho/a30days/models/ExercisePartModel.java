package com.challenge.bennho.a30days.models;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.enums.GenderEnum;
import com.challenge.bennho.a30days.helpers.CalculationHelper;

/**
 * Created by sionglengho on 26/12/16.
 */

public class ExercisePartModel {

    private ExerciseState exerciseState;
    private float durationSecs;
    private int index;

    public ExercisePartModel(ExerciseState exerciseState, float durationSecs) {
        this.exerciseState = exerciseState;
        this.durationSecs = durationSecs;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public ExerciseState getExerciseState() {
        return exerciseState;
    }

    public float getDurationSecs() {
        return durationSecs;
    }

    public float getRemainingDurationSecs(float elapsedDurationSecs){
        return getDurationSecs() - elapsedDurationSecs;
    }

    public void setDurationSecs(float durationSecs) {
        this.durationSecs = durationSecs;
    }

    public int getExerciseColor(Context context){
        switch (exerciseState){
            case WarmUp:
                return ContextCompat.getColor(context, R.color.colorWarmUp);
            case Run:
                return ContextCompat.getColor(context, R.color.colorRun);
            case Sprint:
                return ContextCompat.getColor(context, R.color.colorSprint);
            case Walk:
                return ContextCompat.getColor(context, R.color.colorWalk);
            case FastWalk:
                return ContextCompat.getColor(context, R.color.colorFastWalk);
            case CoolDown:
                return ContextCompat.getColor(context, R.color.colorCoolDown);
        }

        return 0;
    }

    public String getExerciseText(Context context){
        switch (exerciseState){
            case WarmUp:
                return context.getString(R.string.warm_up_state);
            case FastWalk:
                return context.getString(R.string.fast_walk_state);
            case Run:
                return context.getString(R.string.run_state);
            case Sprint:
                return context.getString(R.string.sprint_state);
            case Walk:
                return context.getString(R.string.walk_state);
            case CoolDown:
                return context.getString(R.string.cool_down_state);
        }
        return null;
    }

    public String getExerciseSpeech(Context context){
        switch (exerciseState){
            case WarmUp:
                return context.getString(R.string.speech_warm_up);
            case FastWalk:
                return context.getString(R.string.speech_fast_walk);
            case Run:
                return context.getString(R.string.speech_run);
            case Sprint:
                return context.getString(R.string.speech_sprint);
            case Walk:
                return context.getString(R.string.speech_walk);
            case CoolDown:
                return context.getString(R.string.speech_cool_down);
        }
        return null;
    }

    public Drawable getExerciseIcon(Context context){
        switch (exerciseState){
            case WarmUp:
                return ContextCompat.getDrawable(context, R.drawable.warmup_icon);
            case FastWalk:
                return ContextCompat.getDrawable(context, R.drawable.fast_walk_icon);
            case Run:
                return ContextCompat.getDrawable(context, R.drawable.jogging_icon);
            case Sprint:
                return ContextCompat.getDrawable(context, R.drawable.sprinting_icon);
            case Walk:
                return ContextCompat.getDrawable(context, R.drawable.walking_icon);
            case CoolDown:
                return ContextCompat.getDrawable(context, R.drawable.cool_down_icon);
        }
        return null;
    }

    /**
     * restHeartRate = BMI
     * 0-24.9=70, 25-29.9=80, 30-39.9=90, >40=100
     *
     * maxHeartRate = 210 - (0.8 * Age)
     *
     * exerciseHeartRate= intensity * (maxHeartRate-restHeartRate=total) + restHeartRate
     * warmUp=50%, walk=60*, fastWalk=80%, run=90%,sprint=100%
     *
     * Calories Burned = [(Age x 0.2017) — (Weight x 0.09036) + (exerciseHeartRate x 0.6309) — 55.0969] x Time / 4.184
     * Calories Burned = [(Age x 0.074) — (Weight x 0.05741) + (exerciseHeartRate x 0.4472) — 20.4022] x Time / 4.184
     *
     * @param forIntervalMs
     * @return
     */
    public float getCaloriesBurnt(float forIntervalMs, double bmiValue, double weightInKg,
                                  int age, GenderEnum genderEnum){

        double restHeartRate = 70;
        if(bmiValue >= 25){
            restHeartRate = 80;
        }
        else if(bmiValue >= 30){
            restHeartRate = 90;
        }
        else if(bmiValue >= 40){
            restHeartRate = 100;
        }

        double intensity = 0.50;
        if(exerciseState == ExerciseState.Walk
                || exerciseState == ExerciseState.CoolDown){
            intensity = 0.60;
        }
        else if(exerciseState == ExerciseState.FastWalk){
            intensity = 0.80;
        }
        else if(exerciseState == ExerciseState.Run){
            intensity = 0.90;
        }
        else if(exerciseState == ExerciseState.Sprint){
            intensity = 1.00;
        }

        double maxHeartRate;
        maxHeartRate = 210d - (0.8d * (double) age);

        double exerciseHeartRate;
        exerciseHeartRate = (intensity * (maxHeartRate - restHeartRate)) + restHeartRate;

        double caloriesBurntPerMinute = 0;
        if(genderEnum == GenderEnum.male){
            caloriesBurntPerMinute = (((double) age * 0.2017) - (CalculationHelper.kgToPounds(weightInKg) * 0.09036)
                    + (exerciseHeartRate * 0.6309) - 55.0969) * (1 / 4.184);
        }
        else if(genderEnum == GenderEnum.female){
            caloriesBurntPerMinute = (((double) age * 0.074) - (CalculationHelper.kgToPounds(weightInKg) * 0.05741)
                    + (exerciseHeartRate * 0.4472) - 20.4022) * (1 / 4.184);
        }

        float caloriesBurntPerMs = (float) caloriesBurntPerMinute / (60f * 1000f);

        return caloriesBurntPerMs * forIntervalMs;
    }

    public enum ExerciseState{
        WarmUp, FastWalk, Run, Walk, Sprint, CoolDown
    }



}
