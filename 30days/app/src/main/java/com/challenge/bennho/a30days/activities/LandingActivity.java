package com.challenge.bennho.a30days.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.challenge.bennho.a30days.R;

public class LandingActivity extends MyActivity {

    private Button btnBegin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        onLayoutSet();

        btnBegin = (Button) findViewById(R.id.btnBegin);

        setListeners();
    }

    private void begin(){
        Intent intent = new Intent(LandingActivity.this, PersonalDetailActivity.class);
        intent.putExtra("initial", "1");
        startActivity(intent);
        finish();
    }

    private void setListeners(){
        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                begin();
            }
        });
    }

}
