package com.challenge.bennho.a30days.models;

import java.util.HashMap;

/**
 * Created by sionglengho on 9/1/17.
 */

public class MealDayModel {

    private HashMap<MealType, DishModel> dishesMap;

    public MealDayModel() {
        this.dishesMap = new HashMap();
    }

    public MealDayModel addDish(MealType mealType, DishModel dishModel){
        dishesMap.put(mealType, dishModel);
        return this;
    }


    public enum MealType{
        breakfast, morning_snack, lunch, evening_snack, dinner
    }

}
