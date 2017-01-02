package com.challenge.bennho.a30days.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.models.User;

public class LaunchActivity extends MyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        this.setActionBarVisibility(false);


        User user = new User(this);
        user.initUser();


        Intent intent = new Intent(this, LandingActivity.class);
        //Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.overridePendingTransition(0, 0);
        finish();
    }


    @Override
    protected void onPause() {
        super.onPause();
        this.overridePendingTransition(0, 0);
    }
}
