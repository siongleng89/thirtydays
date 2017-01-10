package com.challenge.bennho.a30days.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.challenge.bennho.a30days.MyApplication;
import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.controls.LayoutDayCounter;
import com.challenge.bennho.a30days.helpers.OverlayBuilder;
import com.challenge.bennho.a30days.helpers.RunnableArgs;
import com.challenge.bennho.a30days.models.User;
import com.challenge.bennho.a30days.services.ExerciseService;

public class MainActivity extends MyActivity {

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private LayoutDayCounter dayCounterControl;
    private ImageView imgViewPrevious, imgViewNext;
    private int userMaxDay;
    private int currentSelectedDay;
    private RelativeLayout layoutExercise, layoutMeal;
    private TextView txtDayNumber1, txtDayNumber2;
    private RelativeLayout layoutLockExercise, layoutLockMeal;
    private boolean lockedExercise, lockedMeal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onLayoutSet();

        layoutExercise = (RelativeLayout) findViewById(R.id.layoutExercise);
        layoutMeal = (RelativeLayout) findViewById(R.id.layoutMeal);
        layoutLockExercise = (RelativeLayout) findViewById(R.id.layoutLockExercise);
        layoutLockMeal = (RelativeLayout) findViewById(R.id.layoutLockMeal);
        dayCounterControl = (LayoutDayCounter) findViewById(R.id.dayCounterControl);
        imgViewNext = (ImageView) findViewById(R.id.imgViewNext);
        imgViewPrevious = (ImageView) findViewById(R.id.imgViewPrevious);
        txtDayNumber1 = (TextView) findViewById(R.id.txtDayNumber1);
        txtDayNumber2 = (TextView) findViewById(R.id.txtDayNumber2);

        setListeners();

        refreshUserProgress();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //stop any orphaned ExerciseService
        Intent serviceIntent = new Intent(this, ExerciseService.class);
        stopService(serviceIntent);

        checkProVersionLockedMeal();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(!((MyApplication) getApplication()).getProVersionHelpers()
                .onActivityResult(requestCode, resultCode, data)){
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void refreshUserProgress(){
        User user = new User(this);
        user.reload();
        userMaxDay = user.getCurrentDay();

        dayCounterControl.setMaxDayNumber(userMaxDay);
        updateDay(userMaxDay);
    }

    private void updateDay(int day){
        currentSelectedDay = day;
        if(currentSelectedDay > 30){
            currentSelectedDay = 30;
        }

        dayCounterControl.updateDayNumber(day);
        txtDayNumber1.setText(String.format("DAY %s", day));
        txtDayNumber2.setText(String.format("DAY %s", day));

        setEnablePrevDayButton(day > 1);
        setEnableNextDayButton(day < 30);
        setLockMeal(day > 7);
        setLockExercise(currentSelectedDay > userMaxDay);
    }


    private void startExercise(){


        if(lockedExercise){
            OverlayBuilder.build(this)
                    .setTitle("Exercise Locked")
                    .setContent("Please finish all previous running exercise plans first!")
                    .setOverlayType(OverlayBuilder.OverlayType.OkOnly)
                    .show();
        }
        else{
            Intent intent = new Intent(this, ReadyActivity.class);
            intent.putExtra("dayPlan", currentSelectedDay);
            startActivity(intent);
        }
    }

    private void showMeal(){
        if(lockedMeal){
            OverlayBuilder.build(this)
                    .setTitle("Pro Version")
                    .setContent("Upgrade to pro version now to view all meal plans and remove ads.")
                    .setOverlayType(OverlayBuilder.OverlayType.OkCancel)
                    .setRunnables(new Runnable() {
                        @Override
                        public void run() {
                            getProVersionHelpers().purchasePro(MainActivity.this,
                                    new Runnable() {
                                @Override
                                public void run() {

                                }
                            });
                        }
                    })
                    .show();
        }
        else{
            Intent intent = new Intent(this, MealActivity.class);
            intent.putExtra("dayPlan", currentSelectedDay);
            startActivity(intent);
        }

    }

    private void checkProVersionLockedMeal(){
        if(currentSelectedDay > 7){
            getProVersionHelpers().isProPurchased(new RunnableArgs<Boolean>() {
                @Override
                public void run() {
                    setLockMeal(!this.getFirstArg());
                }
            });
        }
    }

    private void setListeners(){
        layoutExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startExercise();
            }
        });

        layoutMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMeal();
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

    private void setLockExercise(boolean locked){
        lockedExercise = locked;
        if(locked){
            layoutLockExercise.setVisibility(View.VISIBLE);
        }
        else{
            layoutLockExercise.setVisibility(View.GONE);
        }
    }

    private void setLockMeal(boolean locked){
        lockedMeal = locked;
        if(locked){
            layoutLockMeal.setVisibility(View.VISIBLE);
        }
        else{
            layoutLockMeal.setVisibility(View.GONE);
        }
    }




}
