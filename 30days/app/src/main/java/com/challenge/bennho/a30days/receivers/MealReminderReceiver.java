package com.challenge.bennho.a30days.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.util.Pair;

import com.challenge.bennho.a30days.activities.MealActivity;
import com.challenge.bennho.a30days.helpers.MealsInputter;
import com.challenge.bennho.a30days.helpers.NotificationShower;
import com.challenge.bennho.a30days.helpers.ProVersionHelpers;
import com.challenge.bennho.a30days.helpers.RunnableArgs;
import com.challenge.bennho.a30days.models.DishModel;
import com.challenge.bennho.a30days.models.MealDayModel;
import com.challenge.bennho.a30days.models.User;

/**
 * Created by sionglengho on 9/1/17.
 */

public class MealReminderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        final User user = new User(context);
        user.reload();

        final int currentDay = user.getCurrentDay();
        if(currentDay > 30){
            return;
        }

        ProVersionHelpers proVersionHelpers = ProVersionHelpers.getInstance(context);

        proVersionHelpers.isProPurchased(new RunnableArgs<Boolean>() {
            @Override
            public void run() {
                if(currentDay < 8 || this.getFirstArg()){
                    MealsInputter mealsInputter = new MealsInputter(context);
                    MealDayModel mealDayModel = mealsInputter.getMealByDayNumber(user.getCurrentDay());
                    DishModel dishWithTip = mealsInputter.getDishWithTipByDayNumber(user.getCurrentDay());
                    if(dishWithTip == null) return;

                    NotificationShower.showMealReminder(context, user.getCurrentDay(), dishWithTip);
                }
            }
        });


    }
}
