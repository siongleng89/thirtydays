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
    private int unitIndex;      //0: kg/cm 1:pounds/miles
    private int age;
    private int genderIndex;        //0: male 1:female
    private int totalCaloriesBurnt;
    private int totalRunningSecs;
    private Context context;


    public User(Context context) {
        this.context = context;
    }

    //reset all user preferences to default
    public void initUser(){
        PreferenceUtils.putBoolean(context, PreferenceType.EnableNotification, true);
        PreferenceUtils.putString(context, PreferenceType.ReminderTime, "17");
        PreferenceUtils.putString(context, PreferenceType.ReminderDay, "1,2,3,4,5,6,7");
        PreferenceUtils.putString(context, PreferenceType.CurrentExerciseDay, "1");
    }

    public void reload(){
        this.weight = PreferenceUtils.getDouble(context, PreferenceType.Weight);
        this.height = PreferenceUtils.getDouble(context, PreferenceType.Height);
        this.age = PreferenceUtils.getDouble(context, PreferenceType.Age).intValue();
        this.unitIndex = PreferenceUtils.getDouble(context, PreferenceType.Unit).intValue();
        this.genderIndex = PreferenceUtils.getDouble(context, PreferenceType.Gender).intValue();
        this.currentDay = PreferenceUtils.getDouble(context, PreferenceType.CurrentExerciseDay).intValue();
        this.totalCaloriesBurnt = PreferenceUtils.getDouble(context, PreferenceType.TotalCaloriesBurnt).intValue();
        this.totalRunningSecs = PreferenceUtils.getDouble(context, PreferenceType.TotalRunningSecs).intValue();
    }

    public void delete(){
        PreferenceUtils.delete(context, PreferenceType.Unit);
        PreferenceUtils.delete(context, PreferenceType.Gender);
        PreferenceUtils.delete(context, PreferenceType.CurrentExerciseDay);
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
        PreferenceUtils.putString(context, PreferenceType.CurrentExerciseDay, String.valueOf(currentDay));
    }

    public void addCurrentDay(){
        if(currentDay < 31){
            currentDay++;
            setCurrentDay(currentDay);
        }
    }

    public double getHeightInCm() {
        return height;
    }

    public void setHeightInCm(double height) {
        this.height = height;
        PreferenceUtils.putString(context, PreferenceType.Height, String.valueOf(height));
    }

    public double getWeightKg() {
        return weight;
    }

    public void setWeightKg(double weight) {
        this.weight = weight;
        PreferenceUtils.putString(context, PreferenceType.Weight, String.valueOf(weight));
    }

    public int getUnitIndex() {
        return unitIndex;
    }

    public void setUnitIndex(int unitIndex) {
        this.unitIndex = unitIndex;
        PreferenceUtils.putString(context, PreferenceType.Unit, String.valueOf(unitIndex));
    }

    public int getGenderIndex() {
        return genderIndex;
    }

    public void setGenderIndex(int genderIndex) {
        this.genderIndex = genderIndex;
        PreferenceUtils.putString(context, PreferenceType.Gender, String.valueOf(genderIndex));
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        PreferenceUtils.putString(context, PreferenceType.Age, String.valueOf(age));
    }

    public int getTotalCaloriesBurnt() {
        return totalCaloriesBurnt;
    }

    public void addCaloriesBurnt(float calories){
        totalCaloriesBurnt += calories;
        PreferenceUtils.putString(context, PreferenceType.TotalCaloriesBurnt, String.valueOf(totalCaloriesBurnt));
    }

    public void minusCaloriesBurnt(float calories){
        totalCaloriesBurnt -= calories;
        if(totalCaloriesBurnt < 0){
            totalCaloriesBurnt = 0;
        }
        PreferenceUtils.putString(context, PreferenceType.TotalCaloriesBurnt, String.valueOf(totalCaloriesBurnt));
    }

    public int getTotalRunningSecs() {
        return totalRunningSecs;
    }


    public void addRunningSecs(float secs){
        float currentRunningSecs = totalRunningSecs;
        currentRunningSecs += secs;
        totalRunningSecs = (int) Math.ceil(currentRunningSecs);

        PreferenceUtils.putString(context, PreferenceType.TotalRunningSecs, String.valueOf(totalRunningSecs));
    }

    public void minusRunningSecs(float secs){
        float currentRunningSecs = totalRunningSecs;
        currentRunningSecs -= secs;
        if(currentRunningSecs < 0){
            currentRunningSecs = 0;
        }
        totalRunningSecs = (int) Math.ceil(currentRunningSecs);

        PreferenceUtils.putString(context, PreferenceType.TotalRunningSecs, String.valueOf(totalRunningSecs));
    }

}
