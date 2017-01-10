package com.challenge.bennho.a30days.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.challenge.bennho.a30days.helpers.NotificationShower;
import com.challenge.bennho.a30days.models.User;

/**
 * Created by sionglengho on 4/1/17.
 */

public class RunReminderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        User user = new User(context);
        user.reload();

        if(user.getCurrentDay() > 30){
            return;
        }

        NotificationShower.showRunReminder(context, user.getCurrentDay());
    }
}
