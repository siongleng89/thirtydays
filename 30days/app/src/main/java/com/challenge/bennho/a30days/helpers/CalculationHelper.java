package com.challenge.bennho.a30days.helpers;

/**
 * Created by sionglengho on 23/12/16.
 */

public class CalculationHelper {

    public static Double poundsToKg(double pounds){
        return Math.round((pounds * 0.45359237) * 100.0) / 100.0;
    }

    public static Double kgToPounds(double kg){
        return Math.round((kg / 0.45359237) * 100.0) / 100.0;
    }

}
