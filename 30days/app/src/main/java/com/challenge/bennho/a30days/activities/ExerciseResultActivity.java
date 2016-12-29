package com.challenge.bennho.a30days.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.challenge.bennho.a30days.R;

public class ExerciseResultActivity extends AppCompatActivity {

    private Button btnEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_result);

        setTitle("Day 1 exercise result");

        btnEnd = (Button) findViewById(R.id.btnEnd);
        setListeners();
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

}
