package com.challenge.bennho.a30days.helpers;

import com.challenge.bennho.a30days.models.FoodModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by sionglengho on 29/12/16.
 */

public class CaloriesToImagesConverter {

    private float calories;

    public CaloriesToImagesConverter(float calories) {
        this.calories = calories;
    }


    public ArrayList<FoodModel> getFoods(){
        ArrayList<FoodModel> result = new ArrayList();
        ArrayList<FoodModel.FoodType> typeAFood= new ArrayList();
        typeAFood.add(FoodModel.FoodType.pizza);
        typeAFood.add(FoodModel.FoodType.steak);
        typeAFood.add(FoodModel.FoodType.cheese_burger);
        typeAFood.add(FoodModel.FoodType.french_fries);
        typeAFood.add(FoodModel.FoodType.salmon);
        typeAFood.add(FoodModel.FoodType.instant_noodle);
        typeAFood.add(FoodModel.FoodType.pork);
        typeAFood.add(FoodModel.FoodType.milkshake);
        typeAFood.add(FoodModel.FoodType.chocolate_cake);
        typeAFood.add(FoodModel.FoodType.sandwich);
        typeAFood.add(FoodModel.FoodType.chicken_nugget);
        typeAFood.add(FoodModel.FoodType.fried_chicken);

        ArrayList<FoodModel.FoodType> typeBFood= new ArrayList();
        typeBFood.add(FoodModel.FoodType.corn);
        typeBFood.add(FoodModel.FoodType.biscuit);
        typeBFood.add(FoodModel.FoodType.candy);
        typeBFood.add(FoodModel.FoodType.pancake);
        typeBFood.add(FoodModel.FoodType.sushi);
        typeBFood.add(FoodModel.FoodType.chocolate);
        typeBFood.add(FoodModel.FoodType.chicken);
        typeBFood.add(FoodModel.FoodType.bubble_milk_tea);
        typeBFood.add(FoodModel.FoodType.sausage);
        typeBFood.add(FoodModel.FoodType.speghetti);
        typeBFood.add(FoodModel.FoodType.rice);
        typeBFood.add(FoodModel.FoodType.almond);
        typeBFood.add(FoodModel.FoodType.waffle);
        typeBFood.add(FoodModel.FoodType.donut);

        ArrayList<FoodModel.FoodType> typeCFood= new ArrayList();
        typeCFood.add(FoodModel.FoodType.peanut_butter);
        typeCFood.add(FoodModel.FoodType.cheese_tart);
        typeCFood.add(FoodModel.FoodType.oat);
        typeCFood.add(FoodModel.FoodType.potato_chip);
        typeCFood.add(FoodModel.FoodType.ice_cream);
        typeCFood.add(FoodModel.FoodType.popcorn);
        typeCFood.add(FoodModel.FoodType.coca_cola);
        typeCFood.add(FoodModel.FoodType.cupcake);
        typeCFood.add(FoodModel.FoodType.ham);
        typeCFood.add(FoodModel.FoodType.sweet_potato);
        typeCFood.add(FoodModel.FoodType.cheese);
        typeCFood.add(FoodModel.FoodType.bean);
        typeCFood.add(FoodModel.FoodType.prawn);

        ArrayList<FoodModel.FoodType> typeDFood= new ArrayList();
        typeDFood.add(FoodModel.FoodType.banana);
        typeDFood.add(FoodModel.FoodType.pineapple);
        typeDFood.add(FoodModel.FoodType.bread);
        typeDFood.add(FoodModel.FoodType.egg);
        typeDFood.add(FoodModel.FoodType.apple);
        typeDFood.add(FoodModel.FoodType.strawberry);
        typeDFood.add(FoodModel.FoodType.spinach);
        typeDFood.add(FoodModel.FoodType.carrot);
        typeDFood.add(FoodModel.FoodType.broccolli);
        typeDFood.add(FoodModel.FoodType.tomato);

        ArrayList<FoodModel.FoodType> typeEFood= new ArrayList();
        typeEFood.add(FoodModel.FoodType.tea);
        typeEFood.add(FoodModel.FoodType.water);
        typeEFood.add(FoodModel.FoodType.green_tea);
        

        FoodModel.FoodType foodOne;
        FoodModel.FoodType foodTwo;
        FoodModel.FoodType foodThree;


        if (calories > 900){
            foodOne = getRandomFood(typeAFood);
            foodTwo = getRandomFood(typeAFood);
            foodThree = getRandomFood(typeAFood);
        }
        else if (calories > 800){
            foodOne = getRandomFood(typeAFood);
            foodTwo = getRandomFood(typeAFood);
            foodThree = getRandomFood(typeBFood);
        }
        else if (calories > 700){
            foodOne = getRandomFood(typeAFood);
            foodTwo = getRandomFood(typeBFood);
            foodThree = getRandomFood(typeBFood);
        }
        else if (calories > 600){
            foodOne = getRandomFood(typeBFood);
            foodTwo = getRandomFood(typeBFood);
            foodThree = getRandomFood(typeBFood);
        }
        else if (calories > 500){
            foodOne = getRandomFood(typeBFood);
            foodTwo = getRandomFood(typeBFood);
            foodThree = getRandomFood(typeCFood);
        }
        else if (calories > 400){
            foodOne = getRandomFood(typeBFood);
            foodTwo = getRandomFood(typeCFood);
            foodThree = getRandomFood(typeCFood);
        }
        else if (calories > 300){
            foodOne = getRandomFood(typeCFood);
            foodTwo = getRandomFood(typeCFood);
            foodThree = getRandomFood(typeCFood);
        }
        else if (calories > 200){
            foodOne = getRandomFood(typeCFood);
            foodTwo = getRandomFood(typeDFood);
            foodThree = getRandomFood(typeDFood);
        }
        else if (calories > 100){
            foodOne = getRandomFood(typeDFood);
            foodTwo = getRandomFood(typeDFood);
            foodThree = getRandomFood(typeEFood);
        }
        else{
            foodOne = getRandomFood(typeDFood);
            foodTwo = getRandomFood(typeEFood);
            foodThree = getRandomFood(typeEFood);
        }

        FoodModel foodModelOne=new FoodModel(foodOne);
        FoodModel foodModelTwo=new FoodModel(foodTwo);
        FoodModel foodModelThree=new FoodModel(foodThree);

        result.add(foodModelOne);
        result.add(foodModelTwo);
        result.add(foodModelThree);


        return result;

    }
    private FoodModel.FoodType getRandomFood(ArrayList<FoodModel.FoodType> foodType){
        //get type a food, shuffle, add food, remove the food from list,
        Collections.shuffle(foodType);
        FoodModel.FoodType getFoodOne =  foodType.get(0);
        foodType.remove(0);


        return getFoodOne;

    }


}
