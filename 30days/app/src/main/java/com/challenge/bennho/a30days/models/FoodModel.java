package com.challenge.bennho.a30days.models;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.challenge.bennho.a30days.helpers.Strings;

/**
 * Created by sionglengho on 29/12/16.
 */

public class FoodModel {

    private FoodType foodType;

    public FoodModel(FoodType foodType) {
        this.foodType = foodType;
    }

    public Drawable getDrawable(Context context){
        String id = "food_" + foodType.name();
        int resID = context.getResources().getIdentifier(id, "drawable", context.getPackageName());
        return ContextCompat.getDrawable(context, resID);
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public String getDescription() {
        return "sample";
    }

    public enum FoodType{
        french_fries, nil;

        public static FoodType convertStringToFoodType(String input){
            if(Strings.isEmpty(input)) return nil;
            else{
                for (FoodType c : FoodType.values()) {
                    if (c.name().equals(input)) {
                        return FoodType.valueOf(input);
                    }
                }
            }
            return nil;
        }
    }

}
