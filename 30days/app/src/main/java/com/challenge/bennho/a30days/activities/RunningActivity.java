package com.challenge.bennho.a30days.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appodeal.ads.Appodeal;
import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.controls.LayoutExerciseStates;
import com.challenge.bennho.a30days.helpers.AdsMediation;
import com.challenge.bennho.a30days.helpers.AndroidUtils;
import com.challenge.bennho.a30days.helpers.AnimateBuilder;
import com.challenge.bennho.a30days.helpers.OverlayBuilder;
import com.challenge.bennho.a30days.helpers.PlansInputter;
import com.challenge.bennho.a30days.helpers.Strings;
import com.challenge.bennho.a30days.helpers.Threadings;
import com.challenge.bennho.a30days.models.ExerciseModel;
import com.challenge.bennho.a30days.models.ExercisePartModel;
import com.challenge.bennho.a30days.models.User;
import com.challenge.bennho.a30days.services.ExerciseService;

public class RunningActivity extends MyActivity implements ExerciseService.ExerciseListener,
                            LayoutExerciseStates.ExerciseActionListener {

    private int dayPlan;
    private ExerciseModel exerciseModel;
    private TextView txtUnlockCountdown;
    private TextView txtTime, txtCalories;
    private LayoutExerciseStates layoutExerciseStates;
    private LinearLayout layoutBottom;
    private RelativeLayout layoutUnlockCountdown;
    private RelativeLayout layoutLock, layoutUnlock, layoutPause, layoutContinue, layoutGiveUp;
    private float currentCaloriesBurnt;
    private int movingHeight;
    private float screenWidthDp;
    private boolean cancelUnlock;
    private ExerciseService exerciseService;
    private boolean boundService;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);
        setAdsLayout();

        this.setActionBarVisibility(false);

        user = new User(this);
        user.reload();

        if(getIntent() != null){
            dayPlan = getIntent().getIntExtra("dayPlan", 1);
        }
        else{
            dayPlan = 1;
        }

        getExerciseModels();

        txtTime = (TextView) findViewById(R.id.txtTime);
        txtCalories = (TextView) findViewById(R.id.txtCalories);
        txtUnlockCountdown = (TextView) findViewById(R.id.txtUnlockCountDown);
        layoutBottom = (LinearLayout) findViewById(R.id.layoutBottom);
        layoutLock = (RelativeLayout) findViewById(R.id.layoutLock);
        layoutUnlock = (RelativeLayout) findViewById(R.id.layoutUnlock);
        layoutPause = (RelativeLayout) findViewById(R.id.layoutPause);
        layoutContinue = (RelativeLayout) findViewById(R.id.layoutContinue);
        layoutGiveUp = (RelativeLayout) findViewById(R.id.layoutGiveUp);
        layoutUnlockCountdown = (RelativeLayout) findViewById(R.id.layoutUnlockCountdown);
        layoutExerciseStates = (LayoutExerciseStates) findViewById(R.id.layoutExerciseStates);

        layoutExerciseStates.start(exerciseModel, this);

        setListeners();
    }

    @Override
    public void onBackPressed() {
        giveUp();
    }

    private ServiceConnection exerciseServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            ExerciseService.LocalBinder binder = (ExerciseService.LocalBinder) service;
            exerciseService = binder.getServiceInstance(); //Get instance of your service!
            boundService = true;

            if(!exerciseService.startExercise(exerciseModel, RunningActivity.this)){
                exerciseService.requestTriggerStateChangedOnce();
                if(exerciseService.isPaused()){
                    RunningActivity.this.pauseExercise();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            boundService = false;
        }
    };


    @Override
    public void onTimeChanged(final float totalElapsedMs, float currentExerciseElapsedMs,
                              final float caloriesBurnt, final ExercisePartModel currentExercisePartModel) {
        layoutExerciseStates.onTimeChanged(totalElapsedMs, currentExerciseElapsedMs,
                                caloriesBurnt, currentExercisePartModel);
        Threadings.postRunnable(new Runnable() {
            @Override
            public void run() {
                float remainingSecs = exerciseModel.getTotalDurationSecs() - (totalElapsedMs / 1000);
                double remainingMins = Math.ceil(remainingSecs / 60);
                txtTime.setText(String.valueOf((int) remainingMins));

                int decimalPlaces = 2;
                if(caloriesBurnt > 1000){
                    decimalPlaces = 1;
                }
                txtCalories.setText(Strings.formatToXDec(decimalPlaces, caloriesBurnt));
            }
        });
    }

    @Override
    public void onExercisePartChanged(ExercisePartModel newExercisePartModel) {
        layoutExerciseStates.onExercisePartChanged(newExercisePartModel);
    }

    @Override
    public void onExerciseEnded(float totalElapsedMs, float caloriesBurnt, boolean isCompleted) {
        Intent intent = new Intent(this, ExerciseResultActivity.class);
        intent.putExtra("totalElapsedMs", totalElapsedMs);
        intent.putExtra("caloriesBurnt", caloriesBurnt);
        intent.putExtra("isCompleted", isCompleted);
        intent.putExtra("dayPlan", dayPlan);
        startActivity(intent);

        exerciseService.disposeExercise();
        finish();
    }

    @Override
    public void onSkippingExercise() {
        exerciseService.goToNextExercisePart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startExerciseService();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(boundService){
            unbindService(exerciseServiceConnection);
            boundService = false;
        }
        getWindow().clearFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    private void startExerciseService(){
        Intent serviceIntent = new Intent(this, ExerciseService.class);
        startService(serviceIntent); //Starting the service
        bindService(serviceIntent, exerciseServiceConnection, Context.BIND_AUTO_CREATE);
    }


    private void pauseExercise(){
        exerciseService.pauseExercise();
        layoutPause.setVisibility(View.GONE);
        layoutContinue.setVisibility(View.VISIBLE);
    }

    private void continueExercise(){
        exerciseService.resumeExercise();
        layoutContinue.setVisibility(View.GONE);
        layoutPause.setVisibility(View.VISIBLE);
    }

    private void lockScreen(){
        layoutUnlock.setY(movingHeight);
        layoutUnlock.setVisibility(View.VISIBLE);
        AnimateBuilder.build(this, layoutUnlock)
                .setAnimateType(AnimateBuilder.AnimateType.moveToY)
                .setDurationMs(200)
                .setValuePx(0)
                .start();
        layoutExerciseStates.setLock(true);
    }

    private void unlockScreen(){
        closeLayoutUnlockScreen();
        layoutUnlock.setY(0);
        AnimateBuilder.build(this, layoutUnlock)
                .setAnimateType(AnimateBuilder.AnimateType.moveToY)
                .setDurationMs(200)
                .setValuePx(movingHeight)
                .setFinishCallback(new Runnable() {
                    @Override
                    public void run() {
                        layoutUnlock.setVisibility(View.GONE);
                    }
                })
                .start();
        layoutExerciseStates.setLock(false);
    }

    private void startUnlockScreen(){
        txtUnlockCountdown.setText("3");
        layoutUnlockCountdown.setVisibility(View.VISIBLE);
        layoutUnlockCountdown.setX(-AndroidUtils.dpToPx(this, screenWidthDp));
        AnimateBuilder.build(this, layoutUnlockCountdown)
                .setAnimateType(AnimateBuilder.AnimateType.moveToX)
                .setDurationMs(100)
                .setValuePx(0)
                .setFinishCallback(new Runnable() {
                    @Override
                    public void run() {
                        startUnlockScreenCountDown();
                    }
                })
                .start();


    }

    private void startUnlockScreenCountDown(){
        cancelUnlock = false;
        Threadings.runInBackground(new Runnable() {
            @Override
            public void run() {
                int i = 3;
                while (i > 0){
                    if(cancelUnlock){
                        return;
                    }

                    final int finalI = i;
                    Threadings.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            txtUnlockCountdown.setText(String.valueOf(finalI));
                        }
                    });

                    i--;
                    Threadings.sleep(1000);

                    if(cancelUnlock){
                        return;
                    }
                }

                Threadings.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        unlockScreen();
                    }
                });
            }
        });
    }

    private void cancelUnlockScreen(){
        closeLayoutUnlockScreen();
        cancelUnlock = true;
    }

    private void closeLayoutUnlockScreen(){
        if(layoutUnlockCountdown.getVisibility() == View.VISIBLE){
            //layoutUnlockCountdown.setX(0);
            AnimateBuilder.build(this, layoutUnlockCountdown)
                    .setAnimateType(AnimateBuilder.AnimateType.moveByX)
                    .setDurationMs(300)
                    .setValueDp(-screenWidthDp)
                    .setFinishCallback(new Runnable() {
                        @Override
                        public void run() {
                            layoutUnlockCountdown.setVisibility(View.GONE);
                        }
                    })
                    .start();
        }
    }




    private void giveUp(){
        OverlayBuilder.build(RunningActivity.this)
                .setOverlayType(OverlayBuilder.OverlayType.OkCancel)
                .setTitle("Give up?")
                .setContent("Confirm giving up this exercise?")
                .setRunnables(new Runnable() {
                    @Override
                    public void run() {
                        exerciseService.exerciseGaveUp();
                    }
                })
                .show();
    }

    private void setListeners(){
        layoutPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseExercise();
            }
        });

        layoutContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueExercise();
            }
        });

        layoutLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lockScreen();
            }
        });


        layoutUnlock.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startUnlockScreen();
                        break;

                    case MotionEvent.ACTION_UP:
                        cancelUnlockScreen();
                        break;
                }
                return true;
            }
        });

        layoutUnlock.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startUnlockScreen();
            }
        });

        layoutGiveUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giveUp();
            }
        });

        layoutBottom.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        movingHeight = layoutBottom.getHeight();

                        if (Build.VERSION.SDK_INT < 16) {
                            layoutBottom.getViewTreeObserver()
                                    .removeGlobalOnLayoutListener(this);
                        } else {
                            layoutBottom.getViewTreeObserver()
                                    .removeOnGlobalLayoutListener(this);
                        }

                    }
        });

        screenWidthDp = AndroidUtils.getScreenDpWidth(this);
    }

    private void getExerciseModels(){

        PlansInputter plansInputter = new PlansInputter(this);
        exerciseModel = plansInputter.getExerciseModelByDay(dayPlan,
                    user.getAge(), user.getBMIValue());

//        exerciseModel = new ExerciseModel();
//        exerciseModel.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
//        exerciseModel.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
//        exerciseModel.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 600);
//        exerciseModel.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 30);
//        exerciseModel.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
//        exerciseModel.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 600);
//        exerciseModel.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 30);
//        exerciseModel.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
//        exerciseModel.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 600);
//        exerciseModel.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 30);
//        exerciseModel.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 60);
//        exerciseModel.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);


//       exerciseModel = new ExerciseModel();
//        exerciseModel.addExercisePartModel(new ExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 20));
//        exerciseModel.addExercisePartModel(new ExercisePartModel(ExercisePartModel.ExerciseState.Walk, 10));
//        exerciseModel.addExercisePartModel(new ExercisePartModel(ExercisePartModel.ExerciseState.Run, 10));
//        exerciseModel.addExercisePartModel(new ExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 2));
//        exerciseModel.addExercisePartModel(new ExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 5));
//        exerciseModel.addExercisePartModel(new ExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 2));
    }



}
