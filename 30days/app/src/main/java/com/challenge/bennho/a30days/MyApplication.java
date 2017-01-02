package com.challenge.bennho.a30days;

import android.app.Application;

import com.challenge.bennho.a30days.helpers.Threadings;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by sionglengho on 20/12/16.
 */

public class MyApplication extends Application {
    private Tracker mTracker;
    @Override
    public void onCreate() {
        super.onCreate();
        Threadings.setMainTreadId();
    }
    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }

}

