package com.challenge.bennho.a30days.activities;

import android.content.Intent;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.controls.LayoutSwitchControl;
import com.challenge.bennho.a30days.enums.GenderEnum;
import com.challenge.bennho.a30days.helpers.CalculationHelper;
import com.challenge.bennho.a30days.helpers.OverlayBuilder;
import com.challenge.bennho.a30days.helpers.Strings;
import com.challenge.bennho.a30days.models.User;

public class PersonalDetailActivity extends MyActivity {

    private Button btnDone;
    private LinearLayout layoutHeightCM, layoutHeighFeet;
    private LayoutSwitchControl switchControlUnit ,switchControlGender;
    private EditText txtWeight, txtHeightCM, txtHeightFeet, txtHeightInch, txtAge;
    private boolean isInitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_detail);
        onLayoutSet();

        isInitial = false;
        if(getIntent() != null){
            isInitial = getIntent().getStringExtra("initial").equals("1");
        }

        if(!isInitial){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        btnDone = (Button) findViewById(R.id.btnDone);
        switchControlUnit = (LayoutSwitchControl) findViewById(R.id.switchControlUnit);
        switchControlGender = (LayoutSwitchControl) findViewById(R.id.switchControlGender);
        txtAge = (EditText) findViewById(R.id.txtAge);
        txtWeight = (EditText) findViewById(R.id.txtWeight);
        txtHeightCM = (EditText) findViewById(R.id.txtHeightCM);
        txtHeightFeet = (EditText) findViewById(R.id.txtHeightFeet);
        txtHeightInch = (EditText) findViewById(R.id.txtHeightInch);
        layoutHeightCM = (LinearLayout) findViewById(R.id.layoutHeightCm);
        layoutHeighFeet = (LinearLayout) findViewById(R.id.layoutHeightFeet);

        setListeners();
        repopulateData();
    }

    private void repopulateData(){
        User myUser = new User(this);
        myUser.reload();

        switchControlUnit.setSelectedOptionIndex(myUser.getUnitIndex());
        switchControlGender.setSelectedOptionIndex(myUser.getGenderIndex());
        txtAge.setText(Strings.setEmptyIfZero(myUser.getAge()));

        //user selected kg/cm
        if(myUser.getUnitIndex() == 0){
            setUnitToKgCm();
            txtWeight.setText(Strings.setEmptyIfZero(myUser.getWeightKg()));
            txtHeightCM.setText(Strings.setEmptyIfZero(myUser.getHeightInCm()));
        }
        //user selected pounds/miles
        else{
            setUnitToPoundsMiles();
            if(myUser.getWeightKg() != 0){
                txtWeight.setText(
                        Strings.removeRedundantZero(
                                Double.valueOf(
                                        Strings.formatToXDec(1, CalculationHelper.kgToPounds(myUser.getWeightKg())
                                        )
                                )
                        ));
            }


            Pair<Double, Double> footInchesPair = CalculationHelper.heightCmToInch(myUser.getHeightInCm());
            txtHeightFeet.setText(Strings.setEmptyIfZero(footInchesPair.first));
            txtHeightInch.setText(Strings.setEmptyIfZero(footInchesPair.second));
        }


    }

    private void setUnitToKgCm(){
        txtWeight.setHint("Your weight in kg");

        layoutHeightCM.setVisibility(View.VISIBLE);
        layoutHeighFeet.setVisibility(View.GONE);

        txtWeight.setText("");
    }

    private void setUnitToPoundsMiles(){
        txtWeight.setHint("Your weight in pounds");

        layoutHeightCM.setVisibility(View.GONE);
        layoutHeighFeet.setVisibility(View.VISIBLE);

        txtWeight.setText("");
    }

    private void validateSubmit(){
        double weightKg = 0;
        double heightCm = 0;
        int age = 0;

        //user selected kg/cm
        if(switchControlUnit.getSelectedOptionIndex() == 0){
            String inputWeight = txtWeight.getText().toString();
            String inputHeight = txtHeightCM.getText().toString();
            String inputAge = txtAge.getText().toString();

            if(checkInputsIsValid(inputWeight, inputHeight, inputAge)){


                weightKg = Double.valueOf(inputWeight);
                heightCm = Double.valueOf(inputHeight);
                age = Integer.valueOf(inputAge);
                complete(switchControlUnit.getSelectedOptionIndex(), weightKg,
                        heightCm, age, switchControlGender.getSelectedOptionIndex());
            }
        }
        //user selected pounds/miles
        else{
            String inputWeight = txtWeight.getText().toString();
            String inputHeightFeet = txtHeightFeet.getText().toString();
            String inputHeightInch = txtHeightInch.getText().toString();
            String inputAge = txtAge.getText().toString();

            if(checkInputsIsValid(inputWeight, inputHeightFeet, inputAge)){

                if(!Strings.isEmpty(inputHeightInch) && !Strings.isNumeric(inputHeightInch)){
                    error(false);
                    return;
                }

                if(Strings.isEmpty(inputHeightInch)){
                    inputHeightInch = "0";
                }

                weightKg = CalculationHelper.poundsToKg(Double.valueOf(inputWeight));
                heightCm = CalculationHelper.heightInchToCm(Double.valueOf(inputHeightFeet),
                        Double.valueOf(inputHeightInch));
                age = Integer.valueOf(inputAge);
                complete(switchControlUnit.getSelectedOptionIndex(), weightKg, heightCm, age,
                        switchControlGender.getSelectedOptionIndex());
            }



        }

    }

    private boolean checkInputsIsValid(String... inputs){

        for(String input : inputs){
            if(Strings.isEmpty(input)){
                error(true);
                return false;
            }
            if(!Strings.isNumeric(input)){
                error(false);
                return false;
            }
        }


        return true;
    }

    private void error(boolean isEmptyError){
        if(isEmptyError){
            OverlayBuilder.build(this)
                    .setOverlayType(OverlayBuilder.OverlayType.OkOnly)
                    .setContent("Please make sure all fields are filled before continue")
                    .show();
        }
        else{
            OverlayBuilder.build(this)
                    .setOverlayType(OverlayBuilder.OverlayType.OkOnly)
                    .setContent("Some of the value input is not valid, please check and try again.")
                    .show();
        }
    }

    private void complete(int unitIndex, Double weightKg, Double heightCm, int age, int genderIndex){
        User myUser = new User(this);
        myUser.setAge(age);
        myUser.setUnitIndex(unitIndex);
        myUser.setWeightKg(weightKg);
        myUser.setHeightInCm(heightCm);
        myUser.setGenderIndex(genderIndex);

        if(isInitial){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            finish();
        }
    }


    private void setListeners(){

        switchControlUnit.setSwitchControlListener(new LayoutSwitchControl.SwitchControlListener() {
            @Override
            public void onChanged(int index) {
                if(index == 0){
                    setUnitToKgCm();
                }
                else{
                    setUnitToPoundsMiles();
                }
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateSubmit();
            }
        });
    }

}
