package com.challenge.bennho.a30days;

import android.app.Application;

import com.challenge.bennho.a30days.helpers.Threadings;

/**
 * Created by sionglengho on 20/12/16.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Threadings.setMainTreadId();
    }
}
