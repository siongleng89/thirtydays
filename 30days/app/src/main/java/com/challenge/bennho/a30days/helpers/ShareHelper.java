package com.challenge.bennho.a30days.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.challenge.bennho.a30days.R;

/**
 * Created by Dell-PC on 25/1/2017.
 */

public class ShareHelper {

    public static void shareResult(int calories, int minutes, Activity activity){

            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = String.format(activity.getString(R.string.share_result_msg),String.valueOf(minutes),String.valueOf(calories));
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, activity.getString(R.string.app_name));
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            activity.startActivityForResult(Intent.createChooser(sharingIntent, activity.getString(R.string.share)), 123);
    }
}
