package com.challenge.bennho.a30days.models;

import android.content.Context;
import android.content.Intent;
import android.preference.Preference;

import com.challenge.bennho.a30days.enums.GenderEnum;
import com.challenge.bennho.a30days.enums.PreferenceType;
import com.challenge.bennho.a30days.helpers.Logs;
import com.challenge.bennho.a30days.helpers.PreferenceUtils;

/**
 * Created by sionglengho on 30/12/16.
 */

public class User {

    private int currentDay;
    private double height;
    private double weight;
    private String unit;
    private int age;
    private String gender;
    private Context context;


    public User(Context context) {
        this.context = context;
    }

    //reset all user preferences to default
    public void initUser(){
        PreferenceUtils.putBoolean(context, PreferenceType.EnableNotification, true);
        PreferenceUtils.putString(context, PreferenceType.ReminderTime, "5");
        PreferenceUtils.putString(context, PreferenceType.ReminderDay, "1,2,3,4,5,6,7");
        PreferenceUtils.putString(context, PreferenceType.Gender, GenderEnum.male.name());
    }

    public void reload(){
        this.weight = PreferenceUtils.getDouble(context, PreferenceType.Weight);
        this.height = PreferenceUtils.getDouble(context, PreferenceType.Height);
        this.age = PreferenceUtils.getDouble(context, PreferenceType.Age).intValue();
        this.unit = PreferenceUtils.getString(context, PreferenceType.Unit);
        this.gender = PreferenceUtils.getString(context, PreferenceType.Gender);
        this.currentDay = PreferenceUtils.getDouble(context, PreferenceType.CurrentExerciseDay).intValue();
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
        PreferenceUtils.putString(context, PreferenceType.CurrentExerciseDay, String.valueOf(currentDay));
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
        PreferenceUtils.putString(context, PreferenceType.Height, String.valueOf(height));
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
        PreferenceUtils.putString(context, PreferenceType.Weight, String.valueOf(height));
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
        PreferenceUtils.putString(context, PreferenceType.Unit, unit);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        PreferenceUtils.putString(context, PreferenceType.Age, String.valueOf(age));
    }

    public String getGender() {
        return gender;
    }

    public GenderEnum getGenderEnum() {
        return GenderEnum.valueOf(gender);
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender.name();
        PreferenceUtils.putString(context, PreferenceType.Gender, gender.name());
    }
}
