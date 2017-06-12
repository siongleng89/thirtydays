package com.challenge.bennho.a30days.helpers;

import android.support.v4.util.Pair;

import java.util.Locale;

/**
 * Created by sionglengho on 23/12/16.
 */

public class CalculationHelper {

    /**
     * Convert pounds to kg
     * @param pounds
     * @return kg in two decimal place
     */
    public static Double poundsToKg(double pounds){
        return Math.round((pounds * 0.45359237) * 100.0) / 100.0;
    }

    /**
     * Convert kg to pounds
     * @param kg
     * @return pounds in two decimal place
     */
    public static Double kgToPounds(double kg){
        return Math.round((kg / 0.45359237) * 100.0) / 100.0;
    }

    /**
     * Convert america height metric to cm
     * @param inputFoot
     * @param inputInch
     * @return height in cm
     */
    public static Double heightInchToCm(double inputFoot, double inputInch){
        double cm1 = inputFoot * 30.48;
        double cm2 = inputInch * 2.54;

        return cm1 + cm2;
    }

    public static Pair<Double, Double> heightCmToInch(double d){
        int feetPart = 0;
        int inchesPart = 0;
        if (String.valueOf(d) != null && String.valueOf(d).trim().length() != 0) {
            feetPart = (int) Math.floor((d / 2.54) / 12);
            inchesPart = (int) Math.floor((d / 2.54) - (feetPart * 12));
        }
        return new Pair<>((double) feetPart, (double) inchesPart);
    }

    /**
     * Convert seconds to format of mm:ss
     * @param seconds
     * @return mm:ss
     */
    public static String prettifySeconds(float seconds){
        double minutes = Math.floor(seconds / 60);
        double remainingSecs = Math.abs(60 * minutes - seconds);

        //format to add leading zero
        String formattedMins = String.format(Locale.ENGLISH, "%02d", (int) minutes);
        String formattedSecs = String.format(Locale.ENGLISH, "%02d", (int) remainingSecs);

        return formattedMins + ":" + formattedSecs;
    }


}
