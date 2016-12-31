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
        french_fries, nil, almond, water, apple, banana, bean, biscuit, bread, broccolli, bubble_milk_tea, candy, carrot, cheese, cheese_burger, cheese_tart, chicken, chicken_nugget, chocolate, chocolate_cake, coca_cola, corn, cupcake, donut, egg, fried_chicken, ham, ice_cream, instant_noodle, milkshake, oat, pancake, peanut_butter, pineapple, pizza, popcorn, pork, potato_chip, prawn, rice, salmon, sandwich, sausage, speghetti, spinach, steak, strawberry, sushi, sweet_potato, tea, tomato, waffle;


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
