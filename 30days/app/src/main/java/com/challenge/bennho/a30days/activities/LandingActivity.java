package com.challenge.bennho.a30days.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.challenge.bennho.a30days.R;

public class LandingActivity extends AppCompatActivity {

    private Button btnBegin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        btnBegin = (Button) findViewById(R.id.btnBegin);

        btnBegin.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {

                Intent intent = new Intent(LandingActivity.this, PersonalDetailActivity.class);
                startActivity(intent);
            }
        });


    }
}
