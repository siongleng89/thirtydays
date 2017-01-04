package com.challenge.bennho.a30days.helpers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.challenge.bennho.a30days.enums.PreferenceType;
import com.challenge.bennho.a30days.models.User;
import com.challenge.bennho.a30days.receivers.RunReminderReceiver;

import java.util.Calendar;

/**
 * Created by sionglengho on 4/1/17.
 */

public class RunReminderHelper {

    public static void updateReminders(Context context){
        removeAllReminders(context);

        boolean enabledNotification = PreferenceUtils.getBoolean(context, PreferenceType.EnableNotification);
        if(enabledNotification){
            User user = new User(context);
            user.reload();
            if(user.getCurrentDay() < 31){
                String days = PreferenceUtils.getString(context, PreferenceType.ReminderDay);
                if(!Strings.isEmpty(days)){
                    String reminderTime = PreferenceUtils.getString(context, PreferenceType.ReminderTime);
                    if(!Strings.isNumeric(reminderTime)){
                        reminderTime = "17";
                    }

                    for(String day : days.split(",")){
                        setReminderForDay(Integer.valueOf(day), Integer.valueOf(reminderTime), context);
                    }
                }
            }
        }
    }

    private static void setReminderForDay(int day, int reminderTime, Context context){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, day);
        calendar.set(Calendar.HOUR_OF_DAY, reminderTime);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        if(calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DAY_OF_YEAR, 7);
        }

        PendingIntent pi = PendingIntent.getBroadcast(context, day,
                new Intent(context, RunReminderReceiver.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                7 * AlarmManager.INTERVAL_DAY, pi);     //every one week

    }


    private static void removeAllReminders(Context context){
        for(int i= 1; i <= 7; i++){
            removeReminderForDay(i, context);
        }
    }

    public static void removeReminderForDay(int day, Context context){
        PendingIntent pi = PendingIntent.getBroadcast(context, day,
                new Intent(context, RunReminderReceiver.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.cancel(pi);
    }



}
