package com.challenge.bennho.a30days.helpers;

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
     * Convert seconds to format of mm:ss
     * @param seconds
     * @return mm:ss
     */
    public static String prettifySeconds(float seconds){
        double minutes = Math.floor(seconds / 60);
        double remainingSecs = Math.abs(60 * minutes - seconds);

        //format to add leading zero
        String formattedMins = String.format("%02d", (int) minutes);
        String formattedSecs = String.format("%02d", (int) remainingSecs);

        return formattedMins + ":" + formattedSecs;
    }


}
