package com.challenge.bennho.a30days.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.controls.ImageCircularFood;
import com.challenge.bennho.a30days.drawables.CustomAnimationDrawable;
import com.challenge.bennho.a30days.enums.AnalyticEvent;
import com.challenge.bennho.a30days.enums.PreferenceType;
import com.challenge.bennho.a30days.helpers.Analytics;
import com.challenge.bennho.a30days.helpers.AllReminderHelper;
import com.challenge.bennho.a30days.helpers.AndroidUtils;
import com.challenge.bennho.a30days.helpers.AnimateBuilder;
import com.challenge.bennho.a30days.helpers.CaloriesToImagesConverter;
import com.challenge.bennho.a30days.helpers.PreferenceUtils;
import com.challenge.bennho.a30days.helpers.RealmHelper;
import com.challenge.bennho.a30days.helpers.Strings;
import com.challenge.bennho.a30days.helpers.Threadings;
import com.challenge.bennho.a30days.models.FoodModel;
import com.challenge.bennho.a30days.models.HistoryRecord;
import com.challenge.bennho.a30days.models.User;

import java.util.ArrayList;

public class ExerciseResultActivity extends MyActivity {

    private Button btnEnd;
    private LinearLayout layoutIncompleteCircle, layoutCompletedCircle, linearDuration, linearCalories;
    private ImageView imgViewStickman;
    private ImageCircularFood imageFood1, imageFood2, imageFood3;
    private TextView txtCalories, txtTime, txtTitle;
    private User user;
    private RealmHelper realmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_result);
        onLayoutSet();

        btnEnd = (Button) findViewById(R.id.btnEnd);
        layoutIncompleteCircle = (LinearLayout) findViewById(R.id.layoutIncompleteCircle);
        layoutCompletedCircle = (LinearLayout) findViewById(R.id.layoutCompletedCircle);
        imgViewStickman = (ImageView) findViewById(R.id.imgViewStickman);
        linearDuration = (LinearLayout) findViewById(R.id.linearDuration);
        linearCalories = (LinearLayout) findViewById(R.id.linearCalories);
        imageFood1 = (ImageCircularFood) findViewById(R.id.imageFood1);
        imageFood2 = (ImageCircularFood) findViewById(R.id.imageFood2);
        imageFood3 = (ImageCircularFood) findViewById(R.id.imageFood3);
        txtTime = (TextView) findViewById(R.id.txtTime);
        txtCalories = (TextView) findViewById(R.id.txtCalories);
        txtTitle = (TextView) findViewById(R.id.txtTitle);

        layoutIncompleteCircle.setVisibility(View.GONE);
        layoutCompletedCircle.setVisibility(View.GONE);
        linearDuration.setAlpha(0);
        linearCalories.setAlpha(0);

        user = new User(this);
        user.reload();

        realmHelper = new RealmHelper(this);

        setListeners();

        processResults();
    }

    @Override
    protected void onStop() {
        super.onStop();

        realmHelper.dispose();
    }

    private void processResults(){
        if(getIntent() != null){
            float totalElapsedMs = getIntent().getFloatExtra("totalElapsedMs", 0);
            float caloriesBurnt = getIntent().getFloatExtra("caloriesBurnt", 0);
            boolean isCompleted = getIntent().getBooleanExtra("isCompleted", true);
            int dayPlan = getIntent().getIntExtra("dayPlan", 1);

            if(isCompleted){
                Analytics.logEvent(AnalyticEvent.ExerciseComplete, String.valueOf(dayPlan));
            }
            else{
                Analytics.logEvent(AnalyticEvent.ExerciseFail, dayPlan + ": " +String.valueOf(totalElapsedMs/1000));
            }

            setTitle(String.format(getString(R.string.avty_result_title), String.valueOf(dayPlan)));
            txtTitle.setText(String.format(getString(R.string.avty_result_day_x), String.valueOf(dayPlan)));

            int minutes = (int) Math.ceil((totalElapsedMs / 1000) / 60);
            int calories = (int) Math.ceil(caloriesBurnt);

            txtTime.setText(String.valueOf(minutes));
            txtCalories.setText(String.valueOf(calories));

            CaloriesToImagesConverter converter = new CaloriesToImagesConverter(calories);
            ArrayList<FoodModel> foodModels = converter.getFoods();

            //save history record
            String saved = PreferenceUtils.getString(this, PreferenceType.ExerciseRecordSaved);
            if(Strings.isEmpty(saved) || !saved.equals("1")){
                HistoryRecord historyRecord = new HistoryRecord();
                historyRecord.setDayNumber(dayPlan);
                historyRecord.setFoodModels(foodModels);
                historyRecord.setCompletedExercise(isCompleted);
                historyRecord.setRecordUnixTime(System.currentTimeMillis());
                historyRecord.setExerciseTimeMs(totalElapsedMs);
                historyRecord.setCaloriesBurnt(caloriesBurnt);
                realmHelper.insertHistoryRecord(historyRecord);

                user.addCaloriesBurnt(calories);
                user.addRunningSecs(totalElapsedMs / 1000);


                if(isCompleted && dayPlan == user.getCurrentDay()){
                    user.addCurrentDay();

                    AllReminderHelper.updateReminders(this);

                }

                PreferenceUtils.putString(this, PreferenceType.ExerciseRecordSaved, "1");
            }


            //start animations
            for(int i = 0; i < foodModels.size(); i++){
                ImageCircularFood imageCircularFood = imageFood1;
                if(i == 0){
                    imageCircularFood = imageFood1;
                }
                else if(i == 1){
                    imageCircularFood = imageFood2;
                }
                else if(i == 2){
                    imageCircularFood = imageFood3;
                }

                imageCircularFood.setVisibility(View.VISIBLE);
                imageCircularFood.setFood(foodModels.get(i));
            }

            animatePartOne(isCompleted);
        }
    }


    private void animatePartOne(final boolean isCompleted){

        final LinearLayout layoutResultCircle = isCompleted ? layoutCompletedCircle : layoutIncompleteCircle;
        layoutResultCircle.setVisibility(View.INVISIBLE);

        CustomAnimationDrawable customAnimationDrawable = getAnimationDrawable("running_0", 6, 100, true);
        imgViewStickman.setImageDrawable(customAnimationDrawable);
        customAnimationDrawable.start();

        AnimateBuilder.build(this, imgViewStickman)
                .setAnimateType(AnimateBuilder.AnimateType.moveByX)
                .setValueDp(AndroidUtils.getScreenDpWidth(ExerciseResultActivity.this) / 2 + 100 - 12)
                .setDurationMs(3000)
                .setFinishCallback(new Runnable() {
                    @Override
                    public void run() {

                        if(isCompleted){
                            imgViewStickman.setImageResource(R.drawable.win);
                            imgViewStickman.setScaleY(1.2f);
                            imgViewStickman.setScaleX(1.2f);
                        }
                        else{
                            AnimateBuilder.build(ExerciseResultActivity.this, imgViewStickman)
                                    .setAnimateType(AnimateBuilder.AnimateType.moveByX)
                                    .setValueDp(50)
                                    .setDurationMs(200)
                                    .start();

                            CustomAnimationDrawable customAnimationDrawable2 = getAnimationDrawable("falling_0", 5, 100, false);
                            imgViewStickman.setImageDrawable(customAnimationDrawable2);
                            customAnimationDrawable2.start();
                        }



                        Threadings.delay(500, new Runnable() {
                            @Override
                            public void run() {
                                layoutResultCircle.setScaleY(5);
                                layoutResultCircle.setScaleX(5);

                                AnimateBuilder.build(ExerciseResultActivity.this, layoutResultCircle)
                                        .setDurationMs(200)
                                        .setAnimateType(AnimateBuilder.AnimateType.scale)
                                        .setValueDp(1)
                                        .setFinishCallback(new Runnable() {
                                            @Override
                                            public void run() {
                                                animatePartTwo();
                                            }
                                        })
                                        .start();

                                layoutResultCircle.setVisibility(View.VISIBLE);
                            }
                        });

                    }
                })
                .start();
    }

    private void animatePartTwo(){
        AnimateBuilder.build(this, linearDuration)
                .setValueDp(1)
                .setAnimateType(AnimateBuilder.AnimateType.alpha)
                .setDurationMs(200)
                .setFinishCallback(new Runnable() {
                    @Override
                    public void run() {
                        AnimateBuilder.build(ExerciseResultActivity.this, linearCalories)
                                .setValueDp(1)
                                .setAnimateType(AnimateBuilder.AnimateType.alpha)
                                .setDurationMs(200)
                                .setFinishCallback(new Runnable() {
                                    @Override
                                    public void run() {

                                    }
                                })
                                .start();
                    }
                })
                .start();
    }

    private void finishExercise(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void setListeners(){
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishExercise();
            }
        });
    }

    private CustomAnimationDrawable getAnimationDrawable(String name, int totalFrames,
                                                         int frameDuration, final boolean repeat){

        final CustomAnimationDrawable animation = new CustomAnimationDrawable();

        animation.setAnimationListener(new CustomAnimationDrawable.AnimationListener() {
            @Override
            public void onComplete() {
                if(repeat){
                    animation.reset();
                }
                else{
                    animation.stop();
                }
            }
        });

        if(repeat){
            animation.setOneShot(true);
        }
        else{
            animation.setOneShot(true);
        }


        ArrayList<Drawable> drawables = AndroidUtils.getAnimationDrawables(this, name, totalFrames);
        for(Drawable drawable : drawables){
            animation.addFrame(drawable, frameDuration);
        }

        return animation;
    }

}
