package com.challenge.bennho.a30days;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.challenge.bennho.a30days.helpers.Analytics;
import com.challenge.bennho.a30days.helpers.ProVersionHelpers;
import com.challenge.bennho.a30days.helpers.TextSpeak;
import com.challenge.bennho.a30days.helpers.Threadings;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by sionglengho on 20/12/16.
 */

public class MyApplication extends MultiDexApplication {
    private Tracker mTracker;
    private ProVersionHelpers proVersionHelpers;

    @Override
    public void onCreate() {
        super.onCreate();
        Threadings.setMainTreadId();
        TextSpeak.getInstance(this);
        getProVersionHelpers();
    }



    synchronized public Tracker getDefaultTracker() {
        if(BuildConfig.DEBUG_MODE) return null;

        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            mTracker = analytics.newTracker(R.xml.global_tracker);
            mTracker.enableExceptionReporting(true);
            Analytics.setTracker(mTracker);
        }
        return mTracker;
    }

    synchronized public ProVersionHelpers getProVersionHelpers() {
        if (proVersionHelpers == null) {
            proVersionHelpers = ProVersionHelpers.getInstance(this);
        }
        return proVersionHelpers;
    }

}

