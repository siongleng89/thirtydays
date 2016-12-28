package com.challenge.bennho.a30days.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.helpers.AndroidUtils;
import com.challenge.bennho.a30days.helpers.Threadings;

public class ReadyActivity extends AppCompatActivity {

    private FloatingActionButton fabPlusSecs, fabMusic;
    private TextView txtCountdown;
    private int countDownSecs;
    private boolean pauseCountDown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready);

        fabPlusSecs = (FloatingActionButton) findViewById(R.id.fabPlusSecs);
        fabMusic = (FloatingActionButton) findViewById(R.id.fabMusic);
        txtCountdown = (TextView) findViewById(R.id.txtCountdown);

        fabPlusSecs.setImageBitmap(AndroidUtils.textAsBitmap("+10", 70,
                ContextCompat.getColor(this, R.color.colorBtnWord)));

        setCountDownSecs(3);

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

    private void countDown(){
        if(countDownSecs <= 0){
            countDownComplete();
            return;
        }

        Threadings.runInBackground(new Runnable() {
            @Override
            public void run() {
                Threadings.sleep(1000);
                if(pauseCountDown) return;
                else{
                    countDownSecs--;
                    Threadings.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            txtCountdown.setText(String.valueOf(countDownSecs));
                            countDown();
                        }
                    });
                }
            }
        });
    }

    private void countDownComplete(){
        //complete
    }

    private void openMusic(){
        Intent intent = new Intent("android.intent.action.MUSIC_PLAYER");
        startActivity(intent);
    }

    private void addExtraTimeToCountDown(){
        setCountDownSecs(countDownSecs + 10);
    }

    private void setCountDownSecs(int secs){
        countDownSecs = secs;
        txtCountdown.setText(String.valueOf(countDownSecs));
    }

    private void setListeners(){
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
