package com.challenge.bennho.a30days.helpers;

import java.util.Random;

/**
 * Created by sionglengho on 18/1/17.
 */

public class Integers {

    /**
     * random a number between two integers, inclusively
     * @param start
     * @param end
     * @return
     */
    public static int random(int start, int end){
        Random r = new Random();
        int i1 = r.nextInt(end - start) + start;
        return i1;
    }

}
