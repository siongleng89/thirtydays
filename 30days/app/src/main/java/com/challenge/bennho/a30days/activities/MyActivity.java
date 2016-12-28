package com.challenge.bennho.a30days.activities;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by sionglengho on 26/12/16.
 */

public abstract class MyActivity extends AppCompatActivity {

    private boolean paused;

    protected void setActionBarVisibility(boolean visible){
        if(visible){
            getSupportActionBar().show();
        }
        else{
            getSupportActionBar().hide();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        paused = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        paused = false;
    }

    protected boolean isPaused() {
        return paused;
    }
}
