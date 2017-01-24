package com.challenge.bennho.a30days.helpers;

import android.content.Context;
import android.support.v4.util.Pair;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.enums.GenderEnum;
import com.challenge.bennho.a30days.models.DishModel;
import com.challenge.bennho.a30days.models.IngredientModel;
import com.challenge.bennho.a30days.models.MealDayModel;
import com.challenge.bennho.a30days.models.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by sionglengho on 9/1/17.
 */

public class MealsInputter {

    private Context context;
    private ArrayList<MealDayModel> mealDayModels;
    private ArrayList<String> tipOfTheDays;
    private ArrayList<ArrayList<IngredientModel>> ingredientsList;






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

    public ArrayList<IngredientModel> getIngredientsByDayNumber(int dayNumber){
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

    //Total calories needed= BMR x 1.55
    //BMR = 10 * weight(kg) + 6.25 * height(cm) - 5 * age(y) + 5         (man)
    //BMR = 10 * weight(kg) + 6.25 * height(cm) - 5 * age(y) - 161     (woman)
    public int caloriesNeeded(User user) {
        if (user.getGenderEnum() == GenderEnum.male) {
            double manBmr = (10 * user.getWeightKg() + 6.25 * user.getHeightInCm() - 5 * user.getAge() + 5) * 1.55 ;
            return (int)manBmr;
        } else {
            double womanBmr = (10 * user.getWeightKg() + 6.25 * user.getHeightInCm() - 5 * user.getAge() - 161) * 1.55;
            return (int)womanBmr;
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
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_0_0_name) , R.drawable.meal_sandwich, 392))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_0_1_name), R.drawable.meal_almond, 263, context.getString(R.string.meal_0_1_instruction)))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_0_2_name), R.drawable.meal_rice_chicken_brocolli, 528, context.getString(R.string.meal_0_2_instruction), context.getString(R.string.tip_olive_oil)))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_0_3_name), R.drawable.meal_apple, 75))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_0_4_name), R.drawable.meal_steamegg_mushroom, 245, context.getString(R.string.meal_0_4_instruction))));

        mealDayModels.add(1, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_1_0_name) , R.drawable.meal_omelette, 432, "", context.getString(R.string.tip_coffee)))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_1_1_name), R.drawable.meal_banana, 95))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_1_2_name), R.drawable.meal_salmon_potato, 551))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_1_3_name), R.drawable.meal_papaya, 87))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_1_4_name), R.drawable.meal_salad, 168, context.getString(R.string.meal_1_4_instruction))));

        mealDayModels.add(2, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_2_0_name) , R.drawable.meal_berry_oatmeal, 361))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_2_1_name)  , R.drawable.food_green_tea, 263, context.getString(R.string.meal_2_1_instruction)))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_2_2_name), R.drawable.meal_rice_pork, 559, "", context.getString(R.string.tip_spinach)))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_2_3_name), R.drawable.meal_apple, 75))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_2_4_name),  R.drawable.meal_steam_chicken, 252)));


        mealDayModels.add(3, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_3_0_name) , R.drawable.meal_egg_sandwich, 350))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_3_1_name), R.drawable.meal_orange, 45))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_3_2_name), R.drawable.meal_bell_pepper_chicken, 545, context.getString(R.string.meal_3_2_instruction),  context.getString(R.string.tip_bell_pepper)))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_3_3_name), R.drawable.meal_banana, 95))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_3_4_name), R.drawable.meal_dory_greenbean, 345)));

        mealDayModels.add(4, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_4_0_name) , R.drawable.meal_ham_wrap, 390, "", context.getString(R.string.tip_lettuce)))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_4_1_name), R.drawable.meal_berries_yogurt, 185))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_4_2_name), R.drawable.meal_chicken_spaghetti, 584))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_4_3_name), R.drawable.meal_papaya, 87))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_4_4_name), R.drawable.meal_egg_spinach, 178)));

        mealDayModels.add(5, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_5_0_name) , R.drawable.meal_oat_egg, 400))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_5_1_name), R.drawable.meal_walnut, 285, context.getString(R.string.meal_5_1_instruction)))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_5_2_name), R.drawable.meal_celery_chicken, 616,context.getString(R.string.meal_5_2_instruction)))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_5_3_name), R.drawable.meal_apple, 75))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_5_4_name), R.drawable.meal_steam_prawn, 263, "", context.getString(R.string.tip_prawn))));

        mealDayModels.add(6, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_6_0_name) , R.drawable.meal_egg_toast, 267))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_6_1_name), R.drawable.meal_banana, 95))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_6_2_name), R.drawable.meal_steak_potato, 679,context.getString(R.string.meal_6_2_instruction)))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_6_3_name), R.drawable.meal_tomato_juice, 42, "", context.getString(R.string.tip_tomato_juice)))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_6_4_name), R.drawable.meal_garden_salad, 132, context.getString(R.string.meal_6_4_instruction))));

        mealDayModels.add(7, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_7_0_name) , R.drawable.meal_berries_overnightoat, 358))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_7_1_name), R.drawable.food_tea, 263, context.getString(R.string.meal_7_1_instruction)))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_7_2_name), R.drawable.meal_tuna_potato, 457, context.getString(R.string.meal_7_2_instruction)))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_7_3_name), R.drawable.meal_guava, 112))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_7_4_name), R.drawable.meal_celery_carrot, 135, "", context.getString(R.string.tip_celery))));

        mealDayModels.add(8, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_8_0_name) , R.drawable.meal_chicken_sandwish, 460))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_8_1_name), R.drawable.meal_papaya, 87))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_8_2_name), R.drawable.meal_duck_rice, 582, context.getString(R.string.meal_8_2_instruction)))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_8_3_name), R.drawable.meal_apple_juice, 75, "", context.getString(R.string.tip_apple)))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_8_4_name), R.drawable.meal_tilapia, 148, context.getString(R.string.meal_8_4_instruction))));

        mealDayModels.add(9, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_9_0_name) , R.drawable.meal_cereal_yogurt, 261, "", context.getString(R.string.tip_greek_yogurt)))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_9_1_name), R.drawable.meal_orange, 45))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_9_2_name),  R.drawable.meal_pork_spinach_carrot, 587))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_9_3_name), R.drawable.meal_banana, 95))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_9_4_name), R.drawable.meal_tofu_mushroom, 258)));

        mealDayModels.add(10, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_10_0_name) , R.drawable.meal_peanut_butter_toast, 340, context.getString(R.string.meal_10_0_instruction)))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_10_1_name), R.drawable.meal_apple, 75))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_10_2_name), R.drawable.meal_chicken_cucumber, 531))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_10_3_name), R.drawable.meal_guava, 112))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_10_4_name), R.drawable.meal_dory_greenbean, 345, "", context.getString(R.string.tip_green_bean))));

        mealDayModels.add(11, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_11_0_name) ,  R.drawable.meal_oatmeal_egg, 226))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_11_1_name), R.drawable.meal_orange, 45))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_11_2_name), R.drawable.meal_salmon_pepper, 653))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_11_3_name), R.drawable.meal_berries, 85, "", context.getString(R.string.tip_strawberry)))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_11_4_name), R.drawable.meal_garden_salad, 132, context.getString(R.string.meal_11_4_instruction))));

        mealDayModels.add(12, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_12_0_name) , R.drawable.meal_omelette, 432,  context.getString(R.string.meal_12_0_instruction)))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_12_1_name), R.drawable.meal_banana_yogurt, 195))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_12_2_name), R.drawable.meal_beef_spaghetti, 552))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_12_3_name), R.drawable.meal_watermelon, 85))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_12_4_name), R.drawable.meal_tomato_carrot, 135, "", context.getString(R.string.tip_carrots))));

        mealDayModels.add(13, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_13_0_name),  R.drawable.meal_blueberry_pancake, 357))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_13_1_name), R.drawable.meal_almond, 263, context.getString(R.string.meal_13_1_instruction), context.getString(R.string.tip_almonds)))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_13_2_name), R.drawable.meal_prawn_spinach, 624, context.getString(R.string.meal_13_2_instruction)))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_13_3_name), R.drawable.meal_apple, 75))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_13_4_name), R.drawable.meal_chicken_salad, 205, context.getString(R.string.meal_13_4_instruction))));

        mealDayModels.add(14, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_14_0_name) , R.drawable.meal_egg_toast, 267))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_14_1_name), R.drawable.meal_guava, 112))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_14_2_name), R.drawable.meal_lamb_greenbean, 572))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_14_3_name), R.drawable.meal_orange_juice, 49, "", context.getString(R.string.tip_orange)))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_14_4_name), R.drawable.meal_lime_squid, 249)));

        mealDayModels.add(15, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_15_0_name) , R.drawable.meal_salad, 258, context.getString(R.string.meal_15_0_instruction)))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_15_1_name), R.drawable.meal_berries, 85))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_15_2_name), R.drawable.meal_dory_greenbean, 345))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_15_3_name), R.drawable.meal_banana, 95))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_15_4_name), R.drawable.meal_egg_asparagus, 102, "", context.getString(R.string.tip_asparagus))));

        mealDayModels.add(16, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_16_0_name) , R.drawable.meal_salmon_toast, 282, "", context.getString(R.string.tip_salmon_oil)))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_16_1_name), R.drawable.food_green_tea, 263, context.getString(R.string.meal_16_1_instruction)))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_16_2_name), R.drawable.meal_chicken_spring_onion, 564,context.getString(R.string.meal_16_2_instruction)))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_16_3_name), R.drawable.meal_berries, 85))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_16_4_name), R.drawable.meal_steamegg_mushroom, 245, context.getString(R.string.meal_16_4_instruction))));

        mealDayModels.add(17, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_17_0_name) , R.drawable.meal_ham_omelette, 383))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_17_1_name), R.drawable.meal_banana, 95))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_17_2_name), R.drawable.meal_salmon_spinach, 622))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_17_3_name), R.drawable.meal_apple, 75))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_17_4_name), R.drawable.meal_steam_prawn, 263, "", context.getString(R.string.tip_garlic))));

        mealDayModels.add(18, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_18_0_name) , R.drawable.meal_oat_egg, 412))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_18_1_name), R.drawable.meal_walnut, 285,context.getString(R.string.meal_18_1_instruction)))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_18_2_name), R.drawable.meal_chicken_sandwish, 460, context.getString(R.string.meal_18_2_instruction), context.getString(R.string.tip_chicken_breast)))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_18_3_name), R.drawable.meal_guava, 112))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_18_4_name), R.drawable.meal_garden_salad, 132)));

        mealDayModels.add(19, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_19_0_name) , R.drawable.meal_chicken_avocado, 445, "", context.getString(R.string.tip_avocados)))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_19_1_name), R.drawable.meal_orange, 45))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_19_2_name), R.drawable.meal_prawn_spaghetti, 437))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_19_3_name), R.drawable.meal_banana, 95))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_19_4_name), R.drawable.meal_tofu_mushroom, 258)));

        mealDayModels.add(20, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_20_0_name) , R.drawable.meal_peanut_waffle, 460, context.getString(R.string.meal_20_0_instruction)))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_20_1_name), R.drawable.meal_apple, 75))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_20_2_name), R.drawable.meal_beef_carrot, 528, context.getString(R.string.meal_20_2_instruction)))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_20_3_name), R.drawable.meal_papaya, 87, "", context.getString(R.string.tip_papaya)))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_20_4_name), R.drawable.meal_salad, 200, context.getString(R.string.meal_20_4_instruction))));

        mealDayModels.add(21, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_21_0_name) , R.drawable.meal_banana_oatmeal, 340,  "", context.getString(R.string.tip_oatmeal)))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_21_1_name), R.drawable.meal_apple, 75))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_21_2_name), R.drawable.meal_lamb_carrot, 452))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_21_3_name), R.drawable.meal_papaya, 87))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_21_4_name), R.drawable.meal_egg_asparagus, 137)));

        mealDayModels.add(22, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_22_0_name),  R.drawable.meal_salmon_toast, 382))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_22_1_name), R.drawable.food_tea, 263, "", context.getString(R.string.tip_green_tea)))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_22_2_name), R.drawable.meal_tuna_pepper, 548, context.getString(R.string.meal_22_2_instruction)))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_22_3_name), R.drawable.meal_banana, 95))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_22_4_name), R.drawable.meal_chicken_salad, 205, context.getString(R.string.meal_22_4_instruction))));

        mealDayModels.add(23, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_23_0_name) , R.drawable.meal_ham_omelette, 383))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_23_1_name), R.drawable.meal_guava, 112))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_23_2_name), R.drawable.meal_salmon_asparagus, 642, "", context.getString(R.string.tip_potato)))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_23_3_name), R.drawable.meal_berries, 85))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_23_4_name), R.drawable.meal_steamegg_mushroom, 245, context.getString(R.string.meal_23_4_instruction))));

        mealDayModels.add(24, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_24_0_name) , R.drawable.meal_oat_banana_kiwi, 372))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_24_1_name), R.drawable.meal_walnut, 285, "", context.getString(R.string.tip_walnuts)))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_24_2_name), R.drawable.meal_chicken_carrot_potato, 562, context.getString(R.string.meal_24_2_instruction)))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_24_3_name), R.drawable.meal_banana, 95))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_24_4_name), R.drawable.meal_garden_salad, 132, context.getString(R.string.meal_24_4_instruction))));

        mealDayModels.add(25, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_25_0_name) , R.drawable.meal_avocado_egg_toast, 357))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_25_1_name), R.drawable.meal_orange, 45))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_25_2_name), R.drawable.meal_dory_brocolli, 525, "", context.getString(R.string.tip_brocolli)))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_25_3_name), R.drawable.meal_apple, 75))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_25_4_name), R.drawable.meal_egg_spinach, 178)));

        mealDayModels.add(26, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_26_0_name) , R.drawable.meal_egg_bread, 328))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_26_1_name), R.drawable.meal_watermelon, 85))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_26_2_name), R.drawable.meal_prawn_squid_asparagus, 529, context.getString(R.string.meal_26_2_instruction)))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_26_3_name), R.drawable.meal_banana, 95))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_26_4_name), R.drawable.meal_tofu_mushroom, 258, "", context.getString(R.string.tip_mushrooms))));

        mealDayModels.add(27, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_27_0_name) , R.drawable.meal_banana_pancake, 349, context.getString(R.string.meal_27_0_instruction), context.getString(R.string.tip_banana)))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_27_1_name), R.drawable.meal_berries, 85))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_27_2_name), R.drawable.meal_steak_pepper, 547, context.getString(R.string.meal_27_2_instruction)))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_27_3_name), R.drawable.meal_carrot_juice, 35))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_27_4_name), R.drawable.meal_prawn_salad, 168, context.getString(R.string.meal_27_4_instruction))));

        mealDayModels.add(28, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_28_0_name) , R.drawable.meal_berries_overnightoat, 358))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_28_1_name), R.drawable.meal_guava, 112))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_28_2_name), R.drawable.meal_salmon_spinach, 616, "", context.getString(R.string.tip_brown_rice)))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_28_3_name), R.drawable.meal_tomato_juice, 42))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_28_4_name), R.drawable.meal_salad, 200, context.getString(R.string.meal_28_4_instruction))));

        mealDayModels.add(29, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel(context.getString(R.string.meal_29_0_name),  R.drawable.meal_oat_egg , 412))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel(context.getString(R.string.meal_29_1_name), R.drawable.meal_apple, 75))
                .addDish(MealDayModel.MealType.lunch, new DishModel(context.getString(R.string.meal_29_2_name), R.drawable.meal_rice_chicken_brocolli, 528, context.getString(R.string.meal_29_2_instruction)))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel(context.getString(R.string.meal_29_3_name), R.drawable.meal_banana, 95))
                .addDish(MealDayModel.MealType.dinner, new DishModel(context.getString(R.string.meal_29_4_name), R.drawable.meal_egg_white, 102, context.getString(R.string.meal_29_4_instruction), context.getString(R.string.tip_eggs))));
    }

    private void inputTipOfTheDay(){
        for(int i=0; i<30; i++){
            tipOfTheDays.add(0, context.getString(R.string.tip_day_0));
            tipOfTheDays.add(1, context.getString(R.string.tip_day_1));
            tipOfTheDays.add(2, context.getString(R.string.tip_day_2));
            tipOfTheDays.add(3, context.getString(R.string.tip_day_3));
            tipOfTheDays.add(4, context.getString(R.string.tip_day_4));
            tipOfTheDays.add(5, context.getString(R.string.tip_day_5));
            tipOfTheDays.add(6, context.getString(R.string.tip_day_6));
            tipOfTheDays.add(7, context.getString(R.string.tip_day_7));
            tipOfTheDays.add(8, context.getString(R.string.tip_day_8));
            tipOfTheDays.add(9, context.getString(R.string.tip_day_9));
            tipOfTheDays.add(10, context.getString(R.string.tip_day_10));
            tipOfTheDays.add(11, context.getString(R.string.tip_day_11));
            tipOfTheDays.add(12, context.getString(R.string.tip_day_12));
            tipOfTheDays.add(13, context.getString(R.string.tip_day_13));
            tipOfTheDays.add(14, context.getString(R.string.tip_day_14));
            tipOfTheDays.add(15, context.getString(R.string.tip_day_15));
            tipOfTheDays.add(16, context.getString(R.string.tip_day_16));
            tipOfTheDays.add(17, context.getString(R.string.tip_day_17));
            tipOfTheDays.add(18, context.getString(R.string.tip_day_18));
            tipOfTheDays.add(19, context.getString(R.string.tip_day_19));
            tipOfTheDays.add(20, context.getString(R.string.tip_day_20));
            tipOfTheDays.add(21, context.getString(R.string.tip_day_21));
            tipOfTheDays.add(22, context.getString(R.string.tip_day_22));
            tipOfTheDays.add(23, context.getString(R.string.tip_day_23));
            tipOfTheDays.add(24, context.getString(R.string.tip_day_24));
            tipOfTheDays.add(25, context.getString(R.string.tip_day_25));
            tipOfTheDays.add(26, context.getString(R.string.tip_day_26));
            tipOfTheDays.add(27, context.getString(R.string.tip_day_27));
            tipOfTheDays.add(28, context.getString(R.string.tip_day_28));
            tipOfTheDays.add(29, context.getString(R.string.tip_day_29));

        }
    }

    private void inputIngredientsList(){

        ArrayList<IngredientModel> ingredientsWeek1 = new ArrayList();
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_apple), String.format(context.getString(R.string.x_serving), "3"), IngredientModel.IngredientType.fruits));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_banana), String.format(context.getString(R.string.x_serving), "3"), IngredientModel.IngredientType.fruits));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_berries), String.format(context.getString(R.string.x_serving), "3"), IngredientModel.IngredientType.fruits));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_orange), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.fruits));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_papaya), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.fruits));

        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_asparagus), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_bell_pepper), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_brocolli), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_celery), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_garlic), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_green_beans), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_lettuce), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_mushroom), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_potato), String.format(context.getString(R.string.x_serving), "3"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_salad), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_spinach), String.format(context.getString(R.string.x_serving), "3"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_tomato), String.format(context.getString(R.string.x_pack),"4"), IngredientModel.IngredientType.vegetable));

        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_chicken_breast), String.format(context.getString(R.string.x_serving),"5"), IngredientModel.IngredientType.protein));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_dory_fish), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.protein));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_egg), String.format(context.getString(R.string.x_unit), "24"), IngredientModel.IngredientType.protein));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_lean_ham), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.protein));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_lean_pork_meat), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.protein));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_prawn), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.protein));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_salmon), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.protein));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_steak), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.protein));

        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_brown_rice),  String.format(context.getString(R.string.x_pack),"1"), IngredientModel.IngredientType.carbohydrate));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_oatmeal),  String.format(context.getString(R.string.x_pack),"1"), IngredientModel.IngredientType.carbohydrate));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_whole_wheat_bread),  String.format(context.getString(R.string.x_pack),"1"), IngredientModel.IngredientType.carbohydrate));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_whole_wheat_spaghetti),  String.format(context.getString(R.string.x_pack),"1"), IngredientModel.IngredientType.carbohydrate));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_whole_wheat_wrap),  String.format(context.getString(R.string.x_pack),"1"), IngredientModel.IngredientType.carbohydrate));

        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_bolognese_sauce), String.format(context.getString(R.string.x_bottle), "1"), IngredientModel.IngredientType.others));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_coffee), String.format(context.getString(R.string.x_serving), "3"), IngredientModel.IngredientType.others));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_natural_greek_yogurt), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.others));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_nuts), String.format(context.getString(R.string.x_serving), "3"), IngredientModel.IngredientType.others));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_olive_oil), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.others));
        ingredientsWeek1.add(new IngredientModel(context.getString(R.string.i_tea),  String.format(context.getString(R.string.x_pack),"1"), IngredientModel.IngredientType.others));
        
        Collections.sort(ingredientsWeek1, new IngredientComparator());
        ingredientsList.add(ingredientsWeek1);

        ArrayList<IngredientModel> ingredientsWeek2 = new ArrayList();
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_apple), String.format(context.getString(R.string.x_serving), "3"), IngredientModel.IngredientType.fruits));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_banana), String.format(context.getString(R.string.x_serving), "3"), IngredientModel.IngredientType.fruits));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_berries),String.format(context.getString(R.string.x_serving), "3"), IngredientModel.IngredientType.fruits));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_guava), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.fruits));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_orange), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.fruits));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_papaya), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.fruits));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_watermelon), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.fruits));

        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_bell_pepper), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_brocolli), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_carrot), String.format(context.getString(R.string.x_serving), "3"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_celery), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_cucumber), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_green_beans), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_lettuce), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_mushroom), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_potato), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_salad), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_spinach), String.format(context.getString(R.string.x_serving), "3"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_spring_onion), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_tomato), String.format(context.getString(R.string.x_serving), "3"), IngredientModel.IngredientType.vegetable));

        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_chicken_breast), String.format(context.getString(R.string.x_serving), "3"), IngredientModel.IngredientType.protein));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_dory_fish), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.protein));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_duck_meat), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.protein));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_egg), String.format(context.getString(R.string.x_unit), "14"), IngredientModel.IngredientType.protein));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_minced_chicken), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.protein));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_lean_pork_meat), String.format(context.getString(R.string.x_serving),"1"), IngredientModel.IngredientType.protein));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_prawn), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.protein));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_salmon), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.protein));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_steak), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.protein));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_tilapia), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.protein));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_tofu), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.protein));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_tuna), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.protein));

        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_brown_rice),  String.format(context.getString(R.string.x_pack),"1"), IngredientModel.IngredientType.carbohydrate));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_cereal), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.carbohydrate));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_oatmeal), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.carbohydrate));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_pancake), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.carbohydrate));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_whole_wheat_bread), String.format(context.getString(R.string.x_pack),"1"), IngredientModel.IngredientType.carbohydrate));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_whole_wheat_spaghetti),  String.format(context.getString(R.string.x_pack),"1"), IngredientModel.IngredientType.carbohydrate));

        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_coffee), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.others));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_natural_greek_yogurt), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.others));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_nuts), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.others));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_peanut_butter), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.others));
        ingredientsWeek2.add(new IngredientModel(context.getString(R.string.i_tea), String.format(context.getString(R.string.x_serving),"5"), IngredientModel.IngredientType.others));

        Collections.sort(ingredientsWeek2, new IngredientComparator());
        ingredientsList.add(ingredientsWeek2);

        ArrayList<IngredientModel> ingredientsWeek3 = new ArrayList();
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_apple), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.fruits));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_avocado), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.fruits));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_banana), String.format(context.getString(R.string.x_serving), "3"), IngredientModel.IngredientType.fruits));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_berries), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.fruits));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_guava), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.fruits));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_lime), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.fruits));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_orange), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.fruits));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_papaya), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.fruits));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_watermelon), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.fruits));

        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_asparagus), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_brocolli), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_carrot), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_corn), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_cucumber), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_garlic), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_green_beans), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_lettuce), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_mushroom), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.vegetable));

        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_potato), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_salad), String.format(context.getString(R.string.x_serving), "3"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_spinach), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_spring_onion), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_minced_chicken), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_tofu), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_tomato), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.vegetable));

        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_chicken_breast), String.format(context.getString(R.string.x_serving), "3"), IngredientModel.IngredientType.protein));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_dory_fish), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.protein));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_egg), String.format(context.getString(R.string.x_unit), "24"), IngredientModel.IngredientType.protein));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_lamb), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.protein));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_lean_ham), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.protein));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_prawn),String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.protein));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_salmon), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.protein));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_squid), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.protein));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_steak), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.protein));

        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_brown_rice), String.format(context.getString(R.string.x_pack),"1"), IngredientModel.IngredientType.carbohydrate));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_oatmeal), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.carbohydrate));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_waffle), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.carbohydrate));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_whole_wheat_bread), String.format(context.getString(R.string.x_pack), "1"), IngredientModel.IngredientType.carbohydrate));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_whole_wheat_spaghetti), String.format(context.getString(R.string.x_pack),"1"), IngredientModel.IngredientType.carbohydrate));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_whole_wheat_wrap), String.format(context.getString(R.string.x_pack),"1"), IngredientModel.IngredientType.carbohydrate));

        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_coffee), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.others));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_natural_greek_yogurt), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.others));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_nuts), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.others));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_peanut_butter), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.others));
        ingredientsWeek3.add(new IngredientModel(context.getString(R.string.i_tea), String.format(context.getString(R.string.x_serving), "5"), IngredientModel.IngredientType.others));

        Collections.sort(ingredientsWeek3, new IngredientComparator());
        ingredientsList.add(ingredientsWeek3);

        ArrayList<IngredientModel> ingredientsWeek4 = new ArrayList();
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_apple), String.format(context.getString(R.string.x_serving), "3"), IngredientModel.IngredientType.fruits));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_avocado), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.fruits));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_banana), String.format(context.getString(R.string.x_serving), "8"), IngredientModel.IngredientType.fruits));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_berries), String.format(context.getString(R.string.x_serving), "3"), IngredientModel.IngredientType.fruits));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_guava), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.fruits));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_kiwi), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.fruits));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_orange), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.fruits));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_papaya), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.fruits));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_watermelon), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.fruits));

        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_asparagus), String.format(context.getString(R.string.x_serving), "3"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_bell_pepper), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_brocolli), String.format(context.getString(R.string.x_serving), "3"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_carrot),String.format(context.getString(R.string.x_serving), "3"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_lettuce), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_mushroom), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_potato), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_salad), String.format(context.getString(R.string.x_serving), "4"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_spinach), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_tofu), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.vegetable));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_tomato), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.vegetable));

        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_chicken_breast), String.format(context.getString(R.string.x_serving), "3"), IngredientModel.IngredientType.protein));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_dory_fish), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.protein));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_egg), String.format(context.getString(R.string.x_unit), "34"), IngredientModel.IngredientType.protein));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_lamb), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.protein));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_lean_ham), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.protein));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_minced_chicken), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.protein));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_prawn), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.protein));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_salmon), String.format(context.getString(R.string.x_serving), "3"), IngredientModel.IngredientType.protein));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_squid), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.protein));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_steak), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.protein));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_tuna), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.protein));

        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_brown_rice), String.format(context.getString(R.string.x_pack),"1"), IngredientModel.IngredientType.carbohydrate));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_oatmeal), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.carbohydrate));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_pancake), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.carbohydrate));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_whole_wheat_bread), String.format(context.getString(R.string.x_pack),"1"), IngredientModel.IngredientType.carbohydrate));

        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_coffee), String.format(context.getString(R.string.x_serving), "1"), IngredientModel.IngredientType.others));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_natural_greek_yogurt), String.format(context.getString(R.string.x_serving), "2"), IngredientModel.IngredientType.others));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_nuts), String.format(context.getString(R.string.x_serving), "3"), IngredientModel.IngredientType.others));
        ingredientsWeek4.add(new IngredientModel(context.getString(R.string.i_tea), String.format(context.getString(R.string.x_serving), "5"), IngredientModel.IngredientType.others));

        Collections.sort(ingredientsWeek4, new IngredientComparator());
        ingredientsList.add(ingredientsWeek4);
    }



    public class IngredientComparator implements Comparator<IngredientModel> {
        @Override
        public int compare(IngredientModel o1, IngredientModel o2) {
            return o1.getIngredientType().name().compareTo(o2.getIngredientType().name());
        }
    }

}
