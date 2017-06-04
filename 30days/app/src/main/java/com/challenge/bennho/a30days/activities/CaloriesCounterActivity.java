package com.challenge.bennho.a30days.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.enums.GenderEnum;
import com.challenge.bennho.a30days.helpers.PlansInputter;
import com.challenge.bennho.a30days.models.ExerciseModel;
import com.challenge.bennho.a30days.models.ExercisePartModel;

public class CaloriesCounterActivity extends AppCompatActivity {

    private EditText editTextDayNumber, editTextWalkInt, editTextFastWalkInt,
            editTextRunInt, editTextSprintInt, editTextWeightFac,
            editTextWeight, editTextHeight, editTextAge, editTextGender;
    private TextView txtResult;
    private Button btnRun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories_counter);

        editTextDayNumber = (EditText) findViewById(R.id.editTextDayNumber);
        editTextWalkInt = (EditText) findViewById(R.id.editTextWalkInt);
        editTextFastWalkInt = (EditText) findViewById(R.id.editTextFastWalkInt);
        editTextRunInt = (EditText) findViewById(R.id.editTextRunInt);
        editTextSprintInt = (EditText) findViewById(R.id.editTextSprintInt);
        editTextWeightFac = (EditText) findViewById(R.id.editTextWeightFac);
        editTextWeight = (EditText) findViewById(R.id.editTextWeight);
        editTextHeight = (EditText) findViewById(R.id.editTextHeight);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        editTextGender = (EditText) findViewById(R.id.editTextGender);
        txtResult = (TextView) findViewById(R.id.txtResult);
        btnRun = (Button) findViewById(R.id.btnRun);

        btnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run();
            }
        });


        editTextDayNumber.setText("1");
        editTextWalkInt.setText("0.6");
        editTextFastWalkInt.setText("0.8");
        editTextRunInt.setText("0.9");
        editTextSprintInt.setText("1.0");
        editTextWeightFac.setText("1.0");
        editTextWeight.setText("70");
        editTextHeight.setText("178");
        editTextAge.setText("28");
        editTextGender.setText("0");
    }

    private void run(){
        int dayNumber = getInteger(editTextDayNumber);
        double walkInt = getDouble(editTextWalkInt);
        double fastWalkInt = getDouble(editTextFastWalkInt);
        double runInt = getDouble(editTextRunInt);
        double sprintInt = getDouble(editTextSprintInt);
        double weightFac = getDouble(editTextWeightFac);
        double weight = getDouble(editTextWeight);
        double height = getDouble(editTextHeight);
        int age = getInteger(editTextAge);
        GenderEnum genderEnum = GenderEnum.male;
        if(getInteger(editTextGender) == 1){
            genderEnum = GenderEnum.female;
        }

        double bmi = weight / Math.pow((height  / 100), 2);

        //fat: weightFac: 0.65
        //slim: weightFac = 0.85
        //sprintInt : 5


        PlansInputter plansInputter = new PlansInputter(this);
        ExerciseModel exerciseModel = plansInputter.getExerciseModelByDay(dayNumber, age, bmi, 1);
        double caloriesBurnt = 0;

        for(ExercisePartModel exercisePartModel : exerciseModel.getExercisePartModels()){
            exercisePartModel.WEIGHT_FACTOR = weightFac;
            exercisePartModel.WALK_INTENSITY = walkInt;
            exercisePartModel.FAST_WALK_INTENSITY = fastWalkInt;
            exercisePartModel.RUN_INTENSITY = runInt;
            exercisePartModel.SPRINT_INTENSITY = sprintInt;

            caloriesBurnt += exercisePartModel.getCaloriesBurnt(exercisePartModel.getDurationSecs() * 1000,
                                bmi, weight, age, genderEnum);

        }

        txtResult.setText(String.valueOf(caloriesBurnt));

    }


    private Integer getInteger(EditText text){
        return Integer.valueOf(text.getText().toString());
    }

    private Double getDouble(EditText text){
        return Double.valueOf(text.getText().toString());
    }


}
