package com.challenge.bennho.a30days.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialCallbacks;
import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.controls.BottomBar;
import com.challenge.bennho.a30days.controls.LayoutDayCounter;
import com.challenge.bennho.a30days.helpers.AdsMediation;
import com.challenge.bennho.a30days.models.User;

public class MainActivity extends MyActivity {

    private LayoutDayCounter dayCounterControl;
    private BottomBar bottomBar;
    private TextView txtStart;
    private ImageView imgViewPrevious, imgViewNext;
    private int userMaxDay;
    private int currentSelectedDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomBar = (BottomBar) findViewById(R.id.bottomBar) ;
        dayCounterControl = (LayoutDayCounter) findViewById(R.id.dayCounterControl);
        txtStart = (TextView) findViewById(R.id.txtStart);

        imgViewNext = (ImageView) findViewById(R.id.imgViewNext);
        imgViewPrevious = (ImageView) findViewById(R.id.imgViewPrevious);

        bottomBar.setCurrentSelectedPageIndex(0);

        refreshUserProgress();
        setListeners();
    }

    private void refreshUserProgress(){
        User user = new User(this);
        user.reload();
        userMaxDay = user.getCurrentDay();

        userMaxDay = 31;
        dayCounterControl.setMaxDayNumber(userMaxDay);
        updateDay(userMaxDay);
    }

    private void updateDay(int day){
        currentSelectedDay = day;
        if(currentSelectedDay > 30){
            currentSelectedDay = 30;
        }

        dayCounterControl.updateDayNumber(day);

        setEnablePrevDayButton(day > 1);
        setEnableNextDayButton(day < userMaxDay);
    }


    private void startExercise(){
        Intent intent = new Intent(this, ReadyActivity.class);
        intent.putExtra("dayPlan", currentSelectedDay);
        startActivity(intent);
    }


    private void setListeners(){
        txtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startExercise();
            }
        });
    }

    private void setEnableNextDayButton(boolean enabled){
        if(enabled){
            imgViewNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateDay(currentSelectedDay + 1);
                }
            });
            imgViewNext.setAlpha(1f);
        }
        else{
            imgViewNext.setOnClickListener(null);
            imgViewNext.setAlpha(0.3f);
        }
    }

    private void setEnablePrevDayButton(boolean enabled){
        if(enabled){
            imgViewPrevious.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateDay(currentSelectedDay - 1);
                }
            });
            imgViewPrevious.setAlpha(1f);
        }
        else{
            imgViewPrevious.setOnClickListener(null);
            imgViewPrevious.setAlpha(0.3f);
        }

    }

}
