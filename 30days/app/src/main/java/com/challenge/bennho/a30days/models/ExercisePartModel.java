package com.challenge.bennho.a30days.models;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.challenge.bennho.a30days.R;

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
                return "Warm Up";
            case FastWalk:
                return "Fast Walk";
            case Run:
                return "Run";
            case Sprint:
                return "Sprint";
            case Walk:
                return "Walk";
            case CoolDown:
                return "Cool Down";
        }
        return null;
    }

    public String getExerciseSpeech(Context context){
        switch (exerciseState){
            case WarmUp:
                return "Warm Up!";
            case FastWalk:
                return "Fast Walk Now, My Boy?";
            case Run:
                return "Run";
            case Sprint:
                return "Sprint";
            case Walk:
                return "Walk";
            case CoolDown:
                return "Cool Down";
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
     * exerciseHeartRate= intensity%(maxHeartRate-restHeartRate=total) + restHeartRate
     * warmUp=50%, walk=60*, fastWalk=80%, run=80%,sprint=100%
     *
     * Calories Burned = [(Age x 0.2017) — (Weight x 0.09036) + (exerciseHeartRate x 0.6309) — 55.0969] x Time / 4.184
     * Calories Burned = [(Age x 0.074) — (Weight x 0.05741) + (exerciseHeartRate x 0.4472) — 20.4022] x Time / 4.184
     *
     * @param forIntervalMs
     * @return
     */
    public float getCaloriesBurnt(float forIntervalMs){
        switch (exerciseState){
            case WarmUp:
                return 1.1f;
            case FastWalk:
                return 1.2f;
            case Run:
                return 1.3f;
            case Sprint:
                return 1.5f;
            case Walk:
                return 1.1f;
            case CoolDown:
                return 1.2f;

        }

        return 0;
    }

    public enum ExerciseState{
        WarmUp, FastWalk, Run, Walk, Sprint, CoolDown
    }



}
