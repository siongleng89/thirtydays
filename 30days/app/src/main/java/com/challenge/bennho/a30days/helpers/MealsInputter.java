package com.challenge.bennho.a30days.helpers;

import android.content.Context;

import com.challenge.bennho.a30days.models.DishModel;
import com.challenge.bennho.a30days.models.MealDayModel;

import java.util.ArrayList;

/**
 * Created by sionglengho on 9/1/17.
 */

public class MealsInputter {

    private Context context;
    private ArrayList<MealDayModel> mealDayModels;

    public MealsInputter(Context context) {
        this.context = context;
        mealDayModels = new ArrayList();
        input();
    }

    public MealDayModel getMealByDayNumber(int dayNumber){
        return mealDayModels.get(dayNumber - 1);
    }

    public void input(){

        mealDayModels.add(0, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("First Dish Name"))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("First Dish Name"))
                .addDish(MealDayModel.MealType.lunch, new DishModel("First Dish Name"))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("First Dish Name"))
                .addDish(MealDayModel.MealType.dinner, new DishModel("First Dish Name")));

        //.
        //.
        //.

        mealDayModels.add(29, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("First Dish Name"))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("First Dish Name"))
                .addDish(MealDayModel.MealType.lunch, new DishModel("First Dish Name"))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("First Dish Name"))
                .addDish(MealDayModel.MealType.dinner, new DishModel("First Dish Name")));
    }

}
