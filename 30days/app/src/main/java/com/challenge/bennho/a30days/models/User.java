package com.challenge.bennho.a30days.models;

import android.content.Context;
import android.content.Intent;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.enums.GenderEnum;
import com.challenge.bennho.a30days.enums.PreferenceType;
import com.challenge.bennho.a30days.helpers.PreferenceUtils;
import com.challenge.bennho.a30days.helpers.AllReminderHelper;

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
    private int runDifficultLevel;
    private int currentIteration;
    private Context context;
    private RunHistoriesModel runHistoriesModel;

    public User(Context context) {
        this.context = context;
        runHistoriesModel = new RunHistoriesModel();
    }

    //reset all user preferences to default
    public void initUser(){
        PreferenceUtils.putBoolean(context, PreferenceType.EnableMealPlanNotification, true);
        PreferenceUtils.putBoolean(context, PreferenceType.EnableNotification, true);
        PreferenceUtils.putString(context, PreferenceType.ReminderTime, "17");
        PreferenceUtils.putString(context, PreferenceType.ReminderDay, "1,2,3,4,5,6,7");
        PreferenceUtils.putString(context, PreferenceType.CurrentExerciseDay, "1");
        PreferenceUtils.putString(context, PreferenceType.RunDifficulty, "1");
        PreferenceUtils.putString(context, PreferenceType.CurrentIteration, "0");

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
        this.runDifficultLevel = PreferenceUtils.getDouble(context, PreferenceType.RunDifficulty).intValue();
        this.currentIteration = PreferenceUtils.getDouble(context, PreferenceType.CurrentIteration).intValue();
        this.runHistoriesModel.load(context, currentDay, totalCaloriesBurnt, totalRunningSecs);
    }

    public void delete(){
        PreferenceUtils.delete(context, PreferenceType.Unit);
        PreferenceUtils.delete(context, PreferenceType.Gender);
        PreferenceUtils.delete(context, PreferenceType.CurrentExerciseDay);
    }

    public int getCurrentIteration() {
        return currentIteration;
    }

    public void setCurrentIteration(int currentIteration) {
        this.currentIteration = currentIteration;
        PreferenceUtils.putString(context, PreferenceType.CurrentIteration, String.valueOf(currentIteration));

        RunHistoryModel runHistoryModel = this.runHistoriesModel.getRunHistoryModels().get(currentIteration);

        this.totalRunningSecs = Integer.valueOf(runHistoryModel.getTotalRunningSecs());
        PreferenceUtils.putString(context, PreferenceType.TotalRunningSecs, String.valueOf(totalRunningSecs));
        this.currentDay = Integer.valueOf(runHistoryModel.getCurrentExerciseDay());
        PreferenceUtils.putString(context, PreferenceType.CurrentExerciseDay, String.valueOf(currentDay));
        this.totalCaloriesBurnt = Integer.valueOf(runHistoryModel.getTotalCaloriesBurnt());
        PreferenceUtils.putString(context, PreferenceType.TotalCaloriesBurnt, String.valueOf(totalCaloriesBurnt));

    }

    public int getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
        PreferenceUtils.putString(context, PreferenceType.CurrentExerciseDay, String.valueOf(currentDay));
        updateRunHistories();
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

    public double getBMIValue(){
        return getWeightKg() / Math.pow(getHeightInCm() / 100, 2);
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

    public GenderEnum getGenderEnum(){
        if(getGenderIndex() == 0){
            return GenderEnum.male;
        }
        else{
            return GenderEnum.female;
        }
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
        updateRunHistories();
    }

    public void minusCaloriesBurnt(float calories){
        totalCaloriesBurnt -= calories;
        if(totalCaloriesBurnt < 0){
            totalCaloriesBurnt = 0;
        }
        PreferenceUtils.putString(context, PreferenceType.TotalCaloriesBurnt, String.valueOf(totalCaloriesBurnt));
        updateRunHistories();
    }

    public int getTotalRunningSecs() {
        return totalRunningSecs;
    }


    public void addRunningSecs(float secs){
        float currentRunningSecs = totalRunningSecs;
        currentRunningSecs += secs;
        totalRunningSecs = (int) Math.ceil(currentRunningSecs);

        PreferenceUtils.putString(context, PreferenceType.TotalRunningSecs, String.valueOf(totalRunningSecs));
        updateRunHistories();
    }

    public void minusRunningSecs(float secs){
        float currentRunningSecs = totalRunningSecs;
        currentRunningSecs -= secs;
        if(currentRunningSecs < 0){
            currentRunningSecs = 0;
        }
        totalRunningSecs = (int) Math.ceil(currentRunningSecs);

        PreferenceUtils.putString(context, PreferenceType.TotalRunningSecs, String.valueOf(totalRunningSecs));
        updateRunHistories();
    }

    public int getRunDifficultLevel() {
        return runDifficultLevel;
    }

    public String getRunDifficultText() {
        if(runDifficultLevel == 5){
            return context.getString(R.string.difficulty_5);
        }
        else if(runDifficultLevel == 4){
            return context.getString(R.string.difficulty_4);
        }
        else if(runDifficultLevel == 3){
            return context.getString(R.string.difficulty_3);
        }
        else if(runDifficultLevel == 2){
            return context.getString(R.string.difficulty_2);
        }
        else{
            return context.getString(R.string.difficulty_1);
        }
    }

    private void updateRunHistories() {
        this.runHistoriesModel.update(currentIteration, String.valueOf(currentDay),
                String.valueOf(totalCaloriesBurnt), String.valueOf(totalRunningSecs));
        this.runHistoriesModel.save(context);
    }

    public RunHistoriesModel getRunHistoriesModel() {
        return runHistoriesModel;
    }
}
