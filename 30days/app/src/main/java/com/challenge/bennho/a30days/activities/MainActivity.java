package com.challenge.bennho.a30days.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialCallbacks;
import com.challenge.bennho.a30days.MyApplication;
import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.controls.BottomBar;
import com.challenge.bennho.a30days.controls.LayoutDayCounter;
import com.challenge.bennho.a30days.helpers.AdsMediation;
import com.challenge.bennho.a30days.helpers.TextSpeak;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class MainActivity extends MyActivity {

    private LayoutDayCounter dayCounterControl;
    private BottomBar bottomBar;
    private TextView txtStart;
    private Tracker mTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomBar = (BottomBar) findViewById(R.id.bottomBar) ;
        dayCounterControl = (LayoutDayCounter) findViewById(R.id.dayCounterControl);
        txtStart = (TextView) findViewById(R.id.txtStart);

        dayCounterControl.updateDayNumber(18);
        bottomBar.setCurrentSelectedPageIndex(0);

        setListeners();

        MyApplication application = (MyApplication) getApplication();
        mTracker = application.getDefaultTracker();
        mTracker.setScreenName("Image~");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        AdsMediation adsMediation=new AdsMediation(this);



    }



    private void startExercise(){
        Intent intent = new Intent(this, ReadyActivity.class);
        startActivity(intent);

        Appodeal.setInterstitialCallbacks(new InterstitialCallbacks() {

            @Override
            public void onInterstitialLoaded(boolean isPrecache) {
            }

            @Override
            public void onInterstitialFailedToLoad() {
            }

            @Override
            public void onInterstitialShown() {
            }

            @Override
            public void onInterstitialClicked() {
            }

            @Override
            public void onInterstitialClosed() {
            }

        });
        Appodeal.show(this, Appodeal.INTERSTITIAL);
    }


    private void setListeners(){
        txtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startExercise();

            }
        });
    }



}
