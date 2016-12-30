package com.challenge.bennho.a30days.models;

import com.challenge.bennho.a30days.helpers.Strings;

import java.util.ArrayList;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by sionglengho on 30/12/16.
 */

public class HistoryRecord extends RealmObject {

    private long recordUnixTime;
    private int dayNumber;
    private float exerciseTimeMs;
    private float caloriesBurnt;
    private boolean completedExercise;
    private String foodIds;

    @Ignore
    private ArrayList<FoodModel> foodModels;


    public boolean isCompletedExercise() {
        return completedExercise;
    }

    public void setCompletedExercise(boolean completedExercise) {
        this.completedExercise = completedExercise;
    }

    public long getRecordUnixTime() {
        return recordUnixTime;
    }

    public void setRecordUnixTime(long recordUnixTime) {
        this.recordUnixTime = recordUnixTime;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public float getExerciseTimeMs() {
        return exerciseTimeMs;
    }

    public void setExerciseTimeMs(float exerciseTimeMs) {
        this.exerciseTimeMs = exerciseTimeMs;
    }

    public float getCaloriesBurnt() {
        return caloriesBurnt;
    }

    public void setCaloriesBurnt(float caloriesBurnt) {
        this.caloriesBurnt = caloriesBurnt;
    }

    public String getFoodIds() {
        return foodIds;
    }

    public void setFoodIds(String foodIds) {
        this.foodIds = foodIds;
    }

    public ArrayList<FoodModel> getFoodModels() {
        foodModels = new ArrayList();
        if(!Strings.isEmpty(foodIds)){
            String foodIdentifiers[] = foodIds.split(",");
            for(String foodIdentifier : foodIdentifiers){
                FoodModel.FoodType foodType = FoodModel.FoodType.convertStringToFoodType(foodIdentifier);
                if(foodType != FoodModel.FoodType.nil){
                    foodModels.add(new FoodModel(foodType));
                }
            }
        }

        return foodModels;
    }

    public void setFoodModels(ArrayList<FoodModel> foodModels) {
        ArrayList<String> foodIdentifiers = new ArrayList();
        for(FoodModel foodModel : foodModels){
            foodIdentifiers.add(foodModel.getFoodType().name());
        }
        foodIds = Strings.joinArr(foodIdentifiers, ",");
    }
}
