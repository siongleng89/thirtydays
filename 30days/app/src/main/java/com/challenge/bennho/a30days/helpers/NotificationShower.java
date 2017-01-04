package com.challenge.bennho.a30days.helpers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.activities.LaunchActivity;

import static android.support.v4.app.NotificationCompat.DEFAULT_LIGHTS;
import static android.support.v4.app.NotificationCompat.DEFAULT_SOUND;
import static android.support.v4.app.NotificationCompat.DEFAULT_VIBRATE;

/**
 * Created by sionglengho on 4/1/17.
 */

public class NotificationShower {

    public static int RunReminderId = 10000;


    public static void show(Context context, int notificationId,
                            String title, String content){
        show(context, notificationId, title, content, LaunchActivity.class);
    }

    public static void show(Context context, int notificationId,
                            String title, String content,
                            Class openingActivityClass){

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.icon)
                        .setContentTitle(title)
                        .setContentText(content);

        Intent targetIntent = new Intent(context, openingActivityClass);
        targetIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent intent = PendingIntent.getActivity(context, 0,
                                targetIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(intent);
        builder.setAutoCancel(true);
        builder.setDefaults(DEFAULT_SOUND | DEFAULT_VIBRATE | DEFAULT_LIGHTS);

        Notification notification = builder.build();

        NotificationManager nManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(notificationId, notification);

    }



}
