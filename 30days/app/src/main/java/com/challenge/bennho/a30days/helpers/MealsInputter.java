package com.challenge.bennho.a30days.helpers;

import android.content.Context;
import android.support.v4.util.Pair;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.models.DishModel;
import com.challenge.bennho.a30days.models.MealDayModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sionglengho on 9/1/17.
 */

public class MealsInputter {

    private Context context;
    private ArrayList<MealDayModel> mealDayModels;
    private ArrayList<String> tipOfTheDays;
    private ArrayList<ArrayList<Pair<String, String>>> ingredientsList;

    public MealsInputter(Context context) {
        this.context = context;

        mealDayModels = new ArrayList();
        tipOfTheDays = new ArrayList();
        ingredientsList = new ArrayList();

        inputMeal();
        inputTipOfTheDay();
        inputIngredientsList();
    }

    public MealDayModel getMealByDayNumber(int dayNumber){
        return mealDayModels.get(dayNumber - 1);
    }

    public String getTipByDayNumber(int dayNumber){
        return tipOfTheDays.get(dayNumber - 1);
    }

    public ArrayList<Pair<String, String>> getIngredientsByDayNumber(int dayNumber){
        if(dayNumber <= 7){
            return ingredientsList.get(0);
        }
        else if(dayNumber <= 15){
            return ingredientsList.get(1);
        }
        else if(dayNumber <= 23){
            return ingredientsList.get(2);
        }
        else{
            return ingredientsList.get(3);
        }
    }

    public DishModel getDishWithTipByDayNumber(int dayNumber){
        MealDayModel mealDayModel =  mealDayModels.get(dayNumber - 1);
        if(!Strings.isEmpty(mealDayModel.getDishByMealType(MealDayModel.MealType.breakfast).getTip())){
            return mealDayModel.getDishByMealType(MealDayModel.MealType.breakfast);
        }
        else if(!Strings.isEmpty(mealDayModel.getDishByMealType(MealDayModel.MealType.morning_snack).getTip())){
            return mealDayModel.getDishByMealType(MealDayModel.MealType.morning_snack);
        }
        else if(!Strings.isEmpty(mealDayModel.getDishByMealType(MealDayModel.MealType.lunch).getTip())){
            return mealDayModel.getDishByMealType(MealDayModel.MealType.lunch);
        }
        else if(!Strings.isEmpty(mealDayModel.getDishByMealType(MealDayModel.MealType.evening_snack).getTip())){
            return mealDayModel.getDishByMealType(MealDayModel.MealType.evening_snack);
        }
        else if(!Strings.isEmpty(mealDayModel.getDishByMealType(MealDayModel.MealType.dinner).getTip())){
            return mealDayModel.getDishByMealType(MealDayModel.MealType.dinner);
        }
        return null;
    }

    private void inputMeal(){

        mealDayModels.add(0, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("First Dish Name", R.drawable.food_almond))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("First Dish Name", R.drawable.food_almond))
                .addDish(MealDayModel.MealType.lunch, new DishModel("First Dish Name", R.drawable.food_almond))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("First Dish Name", R.drawable.food_almond))
                .addDish(MealDayModel.MealType.dinner, new DishModel("First Dish Name", R.drawable.food_almond)));

        mealDayModels.add(1, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("First Dish Name", R.drawable.food_almond))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("First Dish Name", R.drawable.food_almond))
                .addDish(MealDayModel.MealType.lunch, new DishModel("First Dish Name", R.drawable.food_almond))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("First Dish Name", R.drawable.food_almond))
                .addDish(MealDayModel.MealType.dinner, new DishModel("First Dish Name", R.drawable.food_almond)));

        mealDayModels.add(2, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("First Dish Name", R.drawable.food_almond))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("First Dish Name", R.drawable.food_almond))
                .addDish(MealDayModel.MealType.lunch, new DishModel("First Dish Name", R.drawable.food_almond))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("First Dish Name", R.drawable.food_almond))
                .addDish(MealDayModel.MealType.dinner, new DishModel("First Dish Name", R.drawable.food_bread, "eat",
                        "ALMOND IS GOOOD ALMOND IS GOOD, ALMOND IS GOOD, EAT ALOT EAT ALOT EAT ALOT EAT ALOT EAT UNTIL YOU STOMACH BURST FOR MAXIMUM EFFECT!!")));

        mealDayModels.add(3, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("First Dish Name", R.drawable.food_almond))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("First Dish Name", R.drawable.food_almond))
                .addDish(MealDayModel.MealType.lunch, new DishModel("First Dish Name", R.drawable.food_almond))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("First Dish Name", R.drawable.food_almond))
                .addDish(MealDayModel.MealType.dinner, new DishModel("First Dish Name", R.drawable.food_almond)));
    }

    private void inputTipOfTheDay(){
        tipOfTheDays.add(0, "TIP 1");
        tipOfTheDays.add(1, "TIP 2");
        tipOfTheDays.add(2, "TIP 3");
        tipOfTheDays.add(3, "TIP 4");
        tipOfTheDays.add(4, "TIP 5");
    }

    private void inputIngredientsList(){
        ArrayList<Pair<String, String>> ingredientsWeek1 = new ArrayList();
        ingredientsWeek1.add(new Pair<String, String>("Egg", "20"));

        ingredientsList.add(ingredientsWeek1);

        ArrayList<Pair<String, String>> ingredientsWeek2 = new ArrayList();
        ingredientsWeek2.add(new Pair<String, String>("Egg", "20"));

        ingredientsList.add(ingredientsWeek2);

        ArrayList<Pair<String, String>> ingredientsWeek3 = new ArrayList();
        ingredientsWeek3.add(new Pair<String, String>("Egg", "20"));

        ingredientsList.add(ingredientsWeek3);

        ArrayList<Pair<String, String>> ingredientsWeek4 = new ArrayList();
        ingredientsWeek4.add(new Pair<String, String>("Egg", "20"));

        ingredientsList.add(ingredientsWeek4);

    }

}
