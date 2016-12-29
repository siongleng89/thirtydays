package com.challenge.bennho.a30days.models;

import android.content.Context;
import android.graphics.Color;
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

    public ExercisePartModel(int index, ExerciseState exerciseState, float durationSecs) {
        this.index = index;
        this.exerciseState = exerciseState;
        this.durationSecs = durationSecs;
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

    public Drawable getExerciseIcon(Context context){
        switch (exerciseState){
            case WarmUp:
                return ContextCompat.getDrawable(context, R.drawable.warmup_icon);
            case FastWalk:
                return ContextCompat.getDrawable(context, R.drawable.walking_icon);
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
