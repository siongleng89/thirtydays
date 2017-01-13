package com.challenge.bennho.a30days.helpers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

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

        String title = String.format(context.getString(R.string.notf_run_reminder_title), String.valueOf(dayNumber));
        String content = AndroidUtils.getStringByIdentifier(context, "exercise_quote" + dayNumber);


        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle(title);
        bigTextStyle.bigText(content);

        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.icon)
                        .setLargeIcon(bm)
                        .setContentTitle(title)
                        .setContentText(content);

        builder.setStyle(bigTextStyle);


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

        builder.addAction(R.drawable.ic_action_maps_directions_walk,
                                context.getString(R.string.notf_run_reminder_action_run), pendingMainIntent);
        builder.addAction(R.drawable.ic_action_action_settings,
                                context.getString(R.string.notf_run_reminder_action_settings), pendingSettingsIntent);

        builder.setAutoCancel(true);
        builder.setDefaults(DEFAULT_SOUND | DEFAULT_VIBRATE | DEFAULT_LIGHTS);

        Notification notification = builder.build();

        NotificationManager nManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(RunReminderId, notification);

    }

    public static void showMealReminder(Context context, int dayNumber,
                                        DishModel dishModelWithTip){

        String title = String.format(context.getString(R.string.notf_meal_reminder_title),
                String.valueOf(dayNumber), dishModelWithTip.getDishName());
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

        builder.addAction(R.drawable.ic_action_action_pageview,
                context.getString(R.string.notf_meal_reminder_action_view), pendingMealIntent);
        builder.addAction(R.drawable.ic_action_action_settings,
                context.getString(R.string.notf_meal_reminder_action_settings), pendingSettingsIntent);

        builder.setAutoCancel(true);
        builder.setDefaults(DEFAULT_SOUND | DEFAULT_VIBRATE | DEFAULT_LIGHTS);

        Notification notification = builder.build();

        NotificationManager nManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(MealReminderId, notification);

    }

}
