package com.challenge.bennho.a30days.models;

import java.util.List;

/**
 * Created by sionglengho on 20/12/16.
 */

public interface IRunTracker {

    void start();
    void stop();
    long getTotalDistanceMeters();
    long getAverageSpeedMeterSecs();
    void getSprintingBreakdowns(List<Sprinting> sprintings);
    long getTotalTimeMs();


}
