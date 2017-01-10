package com.challenge.bennho.a30days.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.challenge.bennho.a30days.helpers.AllReminderHelper;

/**
 * Created by sionglengho on 4/1/17.
 */

public class OnBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        AllReminderHelper.updateReminders(context);
    }
}
