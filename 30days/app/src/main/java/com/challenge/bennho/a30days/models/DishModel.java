package com.challenge.bennho.a30days.models;

/**
 * Created by sionglengho on 9/1/17.
 */

public class DishModel {

    private String dishName;
    private String instruction;
    private String tip;
    private int imageResourceId;
    private int calories;

    public DishModel(String dishName, int imageResourceId, int calories, String instruction, String tip) {
        this.dishName = dishName;
        this.instruction = instruction;
        this.imageResourceId = imageResourceId;
        this.tip = tip;
        this.calories = calories;
    }

    public DishModel(String dishName, int imageResourceId, int calories, String instruction) {
        this.dishName = dishName;
        this.instruction = instruction;
        this.imageResourceId = imageResourceId;
        this.calories = calories;
    }

    public DishModel(String dishName, int imageResourceId, int calories) {
        this.dishName = dishName;
        this.imageResourceId = imageResourceId;
        this.calories = calories;
    }

    public String getDishName() {
        return dishName;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getTip() {
        return tip;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getCalories() {
        return calories;
    }
}
