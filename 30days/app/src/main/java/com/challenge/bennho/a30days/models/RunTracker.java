package com.challenge.bennho.a30days.models;

import android.content.Context;


import java.util.List;

/**
 * Created by sionglengho on 20/12/16.
 */

public class RunTracker implements IRunTracker{

    private long startDurationMs;
    private long totalDurationMs;
    private boolean running;
    private Context context;


    public RunTracker(Context context) {
        this.context = context;
    }

    @Override
    public void start(){

        startDurationMs = System.currentTimeMillis();
        running = true;
    }

    @Override
    public void stop() {
        totalDurationMs = System.currentTimeMillis() - startDurationMs;
        running = false;
    }

    @Override
    public long getTotalDistanceMeters() {
        return 0;
    }

    @Override
    public long getAverageSpeedMeterSecs() {
        return getTotalDistanceMeters() / (getTotalTimeMs() * 1000);
    }

    @Override
    public void getSprintingBreakdowns(List<Sprinting> sprintings) {

    }

    @Override
    public long getTotalTimeMs() {
        if(running){
            return System.currentTimeMillis() - startDurationMs;
        }
        else{
            return totalDurationMs;
        }
    }
    
}
