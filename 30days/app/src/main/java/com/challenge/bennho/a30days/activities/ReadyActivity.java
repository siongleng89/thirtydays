package com.challenge.bennho.a30days.activities;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.enums.AnalyticEvent;
import com.challenge.bennho.a30days.enums.PreferenceType;
import com.challenge.bennho.a30days.helpers.AdsMediation;
import com.challenge.bennho.a30days.helpers.Analytics;
import com.challenge.bennho.a30days.helpers.AndroidUtils;
import com.challenge.bennho.a30days.helpers.PreferenceUtils;
import com.challenge.bennho.a30days.helpers.Strings;
import com.challenge.bennho.a30days.helpers.TextSpeak;
import com.challenge.bennho.a30days.helpers.Threadings;
import com.challenge.bennho.a30days.services.ExerciseService;

public class ReadyActivity extends MyActivity {

    private FloatingActionButton fabPlusSecs, fabMusic;
    private TextView txtCountdown;
    private int countDownSecs;
    private boolean pauseCountDown;
    private int dayPlan;
    private TextSpeak textSpeak;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready);
        onLayoutSet();

        //let the service do some initiation first
        Intent serviceIntent = new Intent(this, ExerciseService.class);
        startService(serviceIntent);

        textSpeak = TextSpeak.getInstance(this);

        showTutorialIfNeededOrShowAds();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fabPlusSecs = (FloatingActionButton) findViewById(R.id.fabPlusSecs);
        fabMusic = (FloatingActionButton) findViewById(R.id.fabMusic);
        txtCountdown = (TextView) findViewById(R.id.txtCountdown);

        fabPlusSecs.setImageBitmap(AndroidUtils.textAsBitmap("+10", 70,
                ContextCompat.getColor(this, R.color.colorBtnWord)));

        if (getIntent() != null) {
            dayPlan = getIntent().getIntExtra("dayPlan", 1);
        } else {
            dayPlan = 1;
        }

        setTitle(String.format(getString(R.string.avty_ready_title), String.valueOf(dayPlan)));

        setCountDownSecs(5);
        setListeners();
    }

    @Override
    protected void onPause() {
        super.onPause();

        pauseCountDown = true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        pauseCountDown = false;
        countDown();
    }

    private void showTutorialIfNeededOrShowAds() {
        String seenTutorial = PreferenceUtils.getString(this, PreferenceType.SeenTutorial);
        if (Strings.isEmpty(seenTutorial) || !seenTutorial.equals("1")) {
            Intent intent = new Intent(this, TutorialActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        } else {
            AdsMediation.showInterstitial(this);
        }
    }

    private void countDown() {
        if (countDownSecs <= 0) {
            countDownComplete();
            return;
        }

        Threadings.runInBackground(new Runnable() {
            @Override
            public void run() {
                Threadings.sleep(1000);
                if (pauseCountDown) return;
                else {
                    countDownSecs--;
                    Threadings.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            if (countDownSecs <= 3 && countDownSecs > 0) {
                                textSpeak.speak(String.valueOf(countDownSecs));
                            }
                            txtCountdown.setText(String.valueOf(countDownSecs));
                            countDown();
                        }
                    });
                }
            }
        });
    }

    private void countDownComplete() {
        //Stop previously running exercise service if available
        Intent serviceIntent = new Intent(this, ExerciseService.class);
        stopService(serviceIntent);
        PreferenceUtils.delete(this, PreferenceType.ExerciseRecordSaved);
        PreferenceUtils.delete(this, PreferenceType.ExerciseRunning);

        Intent intent = new Intent(this, RunningActivity.class);
        intent.putExtra("dayPlan", dayPlan);
        startActivity(intent);

        finish();
        Analytics.logEvent(AnalyticEvent.StartExercise);
    }

    private void openMusic() {
        try{
            if (android.os.Build.VERSION.SDK_INT >= 15) {
                Intent intent = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN,
                        Intent.CATEGORY_APP_MUSIC);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//Min SDK 15
                startActivity(intent);
            } else {
                Intent intent = new Intent("android.intent.action.MUSIC_PLAYER");//Min SDK 8
                startActivity(intent);
            }
        }
        catch (Exception ex){

        }
    }

    private void addExtraTimeToCountDown() {
        setCountDownSecs(countDownSecs + 10);
    }

    private void setCountDownSecs(int secs) {
        countDownSecs = secs;
        txtCountdown.setText(String.valueOf(countDownSecs));
    }

    private void setListeners() {
        fabPlusSecs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExtraTimeToCountDown();
            }
        });

        fabMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMusic();
            }
        });
    }


}
