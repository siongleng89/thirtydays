package com.challenge.bennho.a30days.models;

/**
 * Created by sionglengho on 9/1/17.
 */

public class DishModel {

    private String dishName;
    private String instruction;
    private String tip;

    public DishModel(String dishName, String instruction, String tip) {
        this.dishName = dishName;
        this.instruction = instruction;
        this.tip = tip;
    }

    public DishModel(String dishName, String instruction) {
        this.dishName = dishName;
        this.instruction = instruction;
    }

    public DishModel(String dishName) {
        this.dishName = dishName;
    }
}
