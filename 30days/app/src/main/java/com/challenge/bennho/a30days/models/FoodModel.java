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
        return ContextCompat.getDrawable(context, getDrawableId(context));
    }

    public int getDrawableId(Context context){
        String id = "food_" + foodType.name();
        int resID = context.getResources().getIdentifier(id, "drawable", context.getPackageName());
        return resID;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public String getDescription() {
        return foodType.name();
    }

    public enum FoodType{
        french_fries, nil, almond, water, green_tea, apple,
        banana, bean, biscuit, bread, broccolli, cheese_burger_meal,
        bubble_milk_tea, candy, carrot, cheese, mcnugget_meal,
        cheese_burger, cheese_tart, chicken_chop, chicken_nugget, oreo_cookies,
        ramen, fried_pancake, baked_cheese_rice, sausage_and_beer, pork_knuckle, curry_chicken,
        american_breakfast, lamb_chop, chili_crab, meat_ball, honey_toast,
        popcorn_combo, sushi_roll, baskin_ice_cream, pork_ribs,
        dunkin_donut, fish_and_chip, fried_chicken_set,chips_and_drink, pablo_cheese,
        chocolate, chocolate_cake, coca_cola, corn, cupcake, donut,
        egg, ham, ice_cream, instant_noodle, milkshake,
        oat, pancake, peanut_butter, pineapple, pizza, popcorn, pork,
        potato_chip, prawn, rice, salmon, sandwich_set, sausage, speghetti,
        spinach, steak, strawberry, sushi, sweet_potato, tea, tomato, waffle;

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
