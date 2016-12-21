package com.challenge.bennho.a30days.models;

import android.support.v4.util.Pair;

import java.util.ArrayList;

/**
 * Created by sionglengho on 20/12/16.
 */

public class TimeUnitData {

    private double avgMagnitude;
    private long avgDuration;


    public void setMagnitudeAndDurationPairs(ArrayList<Pair<Double, Long>> pairs){

        double totalMagnitudes = 0;
        long totalDuration = 0;

        for(Pair<Double, Long> pair : pairs){
            totalMagnitudes += pair.first;
            totalDuration += pair.second;
        }

        avgDuration = totalDuration / pairs.size();
        avgMagnitude = totalMagnitudes / pairs.size();
    }


    public double getAvgMagnitude() {
        return avgMagnitude;
    }

    public double getAvgDuration() {
        return avgDuration;
    }
}
