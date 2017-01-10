package com.challenge.bennho.a30days.models;

/**
 * Created by sionglengho on 9/1/17.
 */

public class DishModel {

    private String dishName;
    private String instruction;
    private String tip;
    private int imageResourceId;

    public DishModel(String dishName, int imageResourceId, String instruction, String tip) {
        this.dishName = dishName;
        this.instruction = instruction;
        this.imageResourceId = imageResourceId;
        this.tip = tip;
    }

    public DishModel(String dishName, int imageResourceId, String instruction) {
        this.dishName = dishName;
        this.instruction = instruction;
        this.imageResourceId = imageResourceId;
    }

    public DishModel(String dishName, int imageResourceId) {
        this.dishName = dishName;
        this.imageResourceId = imageResourceId;
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
}
