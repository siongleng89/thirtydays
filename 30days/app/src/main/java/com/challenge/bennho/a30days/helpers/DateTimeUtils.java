package com.challenge.bennho.a30days.helpers;

import android.content.Context;
import android.content.Intent;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by SiongLeng on 2/9/2016.
 */
public class DateTimeUtils {


    public static String convertUnixMsToDateTimeString(Context context, long unixMiliSecs){

        if(unixMiliSecs == 0){
            return "";
        }


        Date date = new Date();
        date.setTime(unixMiliSecs);

        String result = "";

        Format dateFormat = android.text.format.DateFormat.getDateFormat(context);
        String pattern = ((SimpleDateFormat) dateFormat).toLocalizedPattern() + " hh:mm aa";
        DateFormat writeFormat = new SimpleDateFormat(pattern);
        result = writeFormat.format(date);

        return result;
    }



}
