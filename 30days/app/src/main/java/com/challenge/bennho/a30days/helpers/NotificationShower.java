package com.challenge.bennho.a30days.helpers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.activities.LaunchActivity;
import com.challenge.bennho.a30days.activities.MealActivity;
import com.challenge.bennho.a30days.activities.SettingsActivity;
import com.challenge.bennho.a30days.models.DishModel;

import static android.support.v4.app.NotificationCompat.DEFAULT_LIGHTS;
import static android.support.v4.app.NotificationCompat.DEFAULT_SOUND;
import static android.support.v4.app.NotificationCompat.DEFAULT_VIBRATE;

/**
 * Created by sionglengho on 4/1/17.
 */

public class NotificationShower {

    public static int RunReminderId = 10000;
    public static int MealReminderId = 20000;


    public static void showRunReminder(Context context, int dayNumber){

        String title = "Remember to run";
        String content = String.format("Complete your day %s plan today!", dayNumber);


        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.icon)
                        .setContentTitle(title)
                        .setContentText(content);

        Intent targetIntent = new Intent(context, LaunchActivity.class);
        targetIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent intent = PendingIntent.getActivity(context, 0,
                                targetIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(intent);


        Intent mainIntent = new Intent(context, LaunchActivity.class);
        PendingIntent pendingMainIntent = PendingIntent.getActivity(context, 0,
                mainIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent settingsIntent = new Intent(context, SettingsActivity.class);
        PendingIntent pendingSettingsIntent = PendingIntent.getActivity(context, 0, settingsIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        builder.addAction(R.drawable.ic_action_maps_directions_walk, "Run Now", pendingMainIntent);
        builder.addAction(R.drawable.ic_action_action_settings, "Settings", pendingSettingsIntent);

        builder.setAutoCancel(true);
        builder.setDefaults(DEFAULT_SOUND | DEFAULT_VIBRATE | DEFAULT_LIGHTS);

        Notification notification = builder.build();

        NotificationManager nManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(RunReminderId, notification);

    }

    public static void showMealReminder(Context context, int dayNumber,
                                        DishModel dishModelWithTip){

        String title = String.format("Meal day %s", dayNumber);
        String content = dishModelWithTip.getTip();

        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle(title);
        bigTextStyle.bigText(content);

        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), dishModelWithTip.getImageResourceId());
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.icon)
                        .setLargeIcon(bm)
                        .setContentTitle(title)
                        .setContentText(content);

        builder.setStyle(bigTextStyle);

        Intent targetIntent = new Intent(context, MealActivity.class);
        targetIntent.putExtra("dayPlan", dayNumber);
        targetIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent intent = PendingIntent.getActivity(context, 0,
                targetIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(intent);


        Intent mealIntent = new Intent(context, MealActivity.class);
        mealIntent.putExtra("dayPlan", dayNumber);
        PendingIntent pendingMealIntent = PendingIntent.getActivity(context, 0,
                mealIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent settingsIntent = new Intent(context, SettingsActivity.class);
        PendingIntent pendingSettingsIntent = PendingIntent.getActivity(context, 0, settingsIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        builder.addAction(R.drawable.ic_action_action_pageview, "View Plan", pendingMealIntent);
        builder.addAction(R.drawable.ic_action_action_settings, "Settings", pendingSettingsIntent);

        builder.setAutoCancel(true);
        builder.setDefaults(DEFAULT_SOUND | DEFAULT_VIBRATE | DEFAULT_LIGHTS);

        Notification notification = builder.build();

        NotificationManager nManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(MealReminderId, notification);

    }

}
