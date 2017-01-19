package com.challenge.bennho.a30days.helpers;

import android.content.Context;

import com.challenge.bennho.a30days.enums.PreferenceType;
import com.challenge.bennho.a30days.models.FoodModel;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by sionglengho on 29/12/16.
 */

public class CaloriesToImagesConverter {

    private float calories;

    public CaloriesToImagesConverter(float calories) {
        this.calories = calories;
    }


    public ArrayList<FoodModel> getFoods(Context context){
        ArrayList<FoodModel> result = new ArrayList();


        ArrayList<FoodModel.FoodType> typeAFood = getTypeAFoods();
        ArrayList<FoodModel.FoodType> typeAFoodCopy = (ArrayList) typeAFood.clone();

        ArrayList<FoodModel.FoodType> typeBFood = getTypeBFoods();
        ArrayList<FoodModel.FoodType> typeCFood = getTypeCFoods();
        ArrayList<FoodModel.FoodType> typeDFood = getTypeDFoods();

        FoodModel.FoodType foodOne;

        if(calories > 300){
            String usedFood = PreferenceUtils.getString(context, PreferenceType.UsedFoodResult);
            if(!Strings.isEmpty(usedFood)){
                for(String item : usedFood.split(",")){
                    typeAFood.remove(FoodModel.FoodType.convertStringToFoodType(item));
                }
            }

            //if all foods ran out in the list, might happen after post 30 days
            //then we will delete and reset everything again!
            if(typeAFood.size() <= 0){
                PreferenceUtils.delete(context, PreferenceType.UsedFoodResult);
                usedFood = "";
                typeAFood = typeAFoodCopy;
            }

            //get random food from list
            foodOne = getRandomFood(typeAFood);

            ArrayList<String> usedFoodArray= new ArrayList();
            usedFoodArray.add(foodOne.name());
            if(!Strings.isEmpty(usedFood)){
                usedFoodArray.add(usedFood);
            }

            PreferenceUtils.putString(context, PreferenceType.UsedFoodResult, Strings.joinArr(usedFoodArray, ","));
        }
        else if(calories > 200){
            foodOne = getRandomFood(typeBFood);
        }
        else if(calories > 5){
            foodOne = getRandomFood(typeCFood);
        }
        else{
            foodOne = getRandomFood(typeDFood);
        }


        FoodModel foodModelOne = new FoodModel(foodOne);
        result.add(foodModelOne);

        return result;

    }
    private FoodModel.FoodType getRandomFood(ArrayList<FoodModel.FoodType> foodType){
        //get type a food, shuffle, add food, remove the food from list,
        Collections.shuffle(foodType);
        FoodModel.FoodType getFoodOne =  foodType.get(0);
        foodType.remove(0);


        return getFoodOne;
    }

    public ArrayList<FoodModel.FoodType> getTypeAFoods(){
        ArrayList<FoodModel.FoodType> typeAFood = new ArrayList();
        typeAFood.add(FoodModel.FoodType.pizza);
        typeAFood.add(FoodModel.FoodType.steak);
        typeAFood.add(FoodModel.FoodType.cheese_burger_meal);
        typeAFood.add(FoodModel.FoodType.instant_noodle);
        typeAFood.add(FoodModel.FoodType.chocolate_cake);
        typeAFood.add(FoodModel.FoodType.mcnugget_meal);
        typeAFood.add(FoodModel.FoodType.fried_chicken_set);
        typeAFood.add(FoodModel.FoodType.pancake);
        typeAFood.add(FoodModel.FoodType.speghetti);
        typeAFood.add(FoodModel.FoodType.dunkin_donut);
        typeAFood.add(FoodModel.FoodType.pork_ribs);
        typeAFood.add(FoodModel.FoodType.waffle);
        typeAFood.add(FoodModel.FoodType.sandwich_set);
        typeAFood.add(FoodModel.FoodType.popcorn_combo);
        typeAFood.add(FoodModel.FoodType.sushi_roll);
        typeAFood.add(FoodModel.FoodType.ice_cream);
        typeAFood.add(FoodModel.FoodType.chips_and_drink);
        typeAFood.add(FoodModel.FoodType.ramen);
        typeAFood.add(FoodModel.FoodType.fried_pancake);
        typeAFood.add(FoodModel.FoodType.baked_cheese_rice);
        typeAFood.add(FoodModel.FoodType.sausage_and_beer);
        typeAFood.add(FoodModel.FoodType.pork_knuckle);
        typeAFood.add(FoodModel.FoodType.curry_chicken);
        typeAFood.add(FoodModel.FoodType.american_breakfast);
        typeAFood.add(FoodModel.FoodType.fish_and_chip);
        typeAFood.add(FoodModel.FoodType.lamb_chop);
        typeAFood.add(FoodModel.FoodType.chili_crab);
        typeAFood.add(FoodModel.FoodType.chicken_chop);
        typeAFood.add(FoodModel.FoodType.meat_ball);
        typeAFood.add(FoodModel.FoodType.honey_toast);

        return typeAFood;
    }

    private ArrayList<FoodModel.FoodType> getTypeBFoods(){
        ArrayList<FoodModel.FoodType> typeBFood= new ArrayList();
        typeBFood.add(FoodModel.FoodType.milkshake);
        typeBFood.add(FoodModel.FoodType.oreo_cookies);
        typeBFood.add(FoodModel.FoodType.candy);
        typeBFood.add(FoodModel.FoodType.chocolate);
        typeBFood.add(FoodModel.FoodType.pablo_cheese);
        typeBFood.add(FoodModel.FoodType.coca_cola);
        typeBFood.add(FoodModel.FoodType.cupcake);
        return typeBFood;
    }

    private ArrayList<FoodModel.FoodType> getTypeCFoods(){
        ArrayList<FoodModel.FoodType> typeCFood= new ArrayList();
        typeCFood.add(FoodModel.FoodType.banana);
        typeCFood.add(FoodModel.FoodType.pineapple);
        typeCFood.add(FoodModel.FoodType.bread);
        typeCFood.add(FoodModel.FoodType.egg);
        typeCFood.add(FoodModel.FoodType.apple);
        typeCFood.add(FoodModel.FoodType.strawberry);
        typeCFood.add(FoodModel.FoodType.spinach);
        typeCFood.add(FoodModel.FoodType.carrot);
        typeCFood.add(FoodModel.FoodType.broccolli);
        return typeCFood;
    }

    private ArrayList<FoodModel.FoodType> getTypeDFoods(){
        ArrayList<FoodModel.FoodType> typeDFood= new ArrayList();
        typeDFood.add(FoodModel.FoodType.tea);
        typeDFood.add(FoodModel.FoodType.water);
        typeDFood.add(FoodModel.FoodType.green_tea);
        return typeDFood;
    }

}
