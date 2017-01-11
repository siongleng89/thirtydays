package com.challenge.bennho.a30days.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.enums.PreferenceType;
import com.challenge.bennho.a30days.helpers.AdsMediation;
import com.challenge.bennho.a30days.helpers.NotificationShower;
import com.challenge.bennho.a30days.helpers.PreferenceUtils;
import com.challenge.bennho.a30days.helpers.Strings;
import com.challenge.bennho.a30days.models.User;

public class LaunchActivity extends MyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        onLayoutSet();

        this.setActionBarVisibility(false);

        User user = new User(this);
        user.reload();

        Intent intent;
        //new user
        if(user.getHeightInCm() == 0){
            user.initUser();
            intent = new Intent(this, LandingActivity.class);
        }
        else{
            intent = new Intent(this, MainActivity.class);
        }
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
