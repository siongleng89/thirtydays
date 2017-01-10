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
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Ham Sandwich" , R.drawable.meal_sandwich))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Nuts + Tea", R.drawable.meal_almond, "Any kind of nut and tea without sugar"))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Brown Rice with Grill Chicken and Brocolli", R.drawable.meal_rice_chicken_brocolli, "Grill with lil bit of olive oil"))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Apple", R.drawable.meal_apple))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Steam Egg White With Mushroom", R.drawable.meal_steamegg_mushroom, "4 egg white and 1 egg yolk")));

        mealDayModels.add(1, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Omelette and Whole Wheat Bread + Coffee" , R.drawable.meal_omelette))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Banana", R.drawable.meal_banana))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Salmon with Potato and Tomato", R.drawable.meal_salmon_potato))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Papaya", R.drawable.meal_papaya))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Boiled Egg White and Garden Salad", R.drawable.meal_salad, "Without Dressing")));

        mealDayModels.add(2, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Berries Oatmeal" , R.drawable.meal_berry_oatmeal))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Nuts + Tea"  , R.drawable.food_green_tea, "Any kind of nut and tea without sugar"))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Brown Rice with Lean Pork Meat and Spinach", R.drawable.meal_rice_pork))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Apple", R.drawable.meal_apple))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Steam Chicken with Brocolli", R.drawable.meal_steam_chicken)));


        mealDayModels.add(3, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Egg White Sandwich + Coffee" , R.drawable.meal_egg_sandwich))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Orange", R.drawable.meal_orange))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Grill Chicken Breast with Potato and Bell Pepper", R.drawable.meal_bell_pepper_chicken, "Remove the skin and fat from chicken"))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Banana", R.drawable.meal_banana))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Steam Dory Fish with Green Beans", R.drawable.meal_dory_greenbean)));

        mealDayModels.add(4, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Ham and Lettuce Wrap" , R.drawable.meal_ham_wrap))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Berries Greek Yogurt + Green Tea", R.drawable.meal_berries_yogurt))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Chicken Bolognese Spaghetti", R.drawable.meal_chicken_spaghetti))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Papaya", R.drawable.meal_papaya))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Boiled Egg White and Spinach", R.drawable.meal_egg_spinach)));

        mealDayModels.add(5, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Oatmeal and Scramble Egg White" , R.drawable.meal_oat_egg))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Nut + Tea", R.drawable.meal_walnut, "Any kind of nut and tea without sugar"))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Brown Rice with Stir-fry Chicken and Celery", R.drawable.meal_celery_chicken))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Apple", R.drawable.meal_apple))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Steam Prawn with Garlic", R.drawable.meal_steam_prawn)));

        mealDayModels.add(6, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Boiled Egg and Toast + Coffee" , R.drawable.meal_egg_toast))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Banana", R.drawable.meal_banana))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Grill Steak with Potato and Asparagus", R.drawable.meal_steak_potato))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Tomato Juice", R.drawable.meal_tomato_juice))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Garden Salad", R.drawable.meal_garden_salad,"Without Dressing")));

        mealDayModels.add(7, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Berries Overnight Oat" , R.drawable.meal_berries_overnightoat))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Nuts + Tea ", R.drawable.food_tea,"Any kind of nut and tea without sugar"))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Tuna with Mashed Potato and Brocolli", R.drawable.meal_tuna_potato))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Guava", R.drawable.meal_guava))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Boiled Egg with Celery and Carrot", R.drawable.meal_celery_carrot)));

        mealDayModels.add(8, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Chicken Sandwich + Coffee" , R.drawable.meal_chicken_sandwish))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Papaya", R.drawable.meal_papaya))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Brown Rice with Duck Meat and Celery", R.drawable.meal_duck_rice, "Remove the duck skin"))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Apple Juice", R.drawable.meal_apple_juice))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Steam Tilapia with Spring Onion", R.drawable.meal_tilapia, "You can replace the fish with dory fish")));

        mealDayModels.add(9, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Blueberry Cereal Greek Yogurt" , R.drawable.meal_cereal_yogurt))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Orange + Tea", R.drawable.meal_orange))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Grill Lean Pork Meat with Carrot and Spinach", R.drawable.meal_pork_spinach_carrot))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Banana", R.drawable.meal_banana))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Steam Tofu with Mushroom and Minced Chicken", R.drawable.meal_tofu_mushroom)));

        mealDayModels.add(10, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Peanut Butter Toast" , R.drawable.meal_peanut_butter_toast,"Two teaspoon of peanut butter"))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Apple + Green Tea", R.drawable.meal_apple))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Brown Rice with Chicken Breast and Cucumber", R.drawable.meal_chicken_cucumber))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Guava", R.drawable.meal_guava))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Steam Dory Fish and Green Beans", R.drawable.meal_dory_greenbean)));

        mealDayModels.add(11, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Oatmeal and Boiled Egg White" , R.drawable.meal_oatmeal_egg))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Orange + Tea", R.drawable.meal_orange))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Baked Salmon with Potato and Bell Pepper", R.drawable.meal_salmon_pepper))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Berries", R.drawable.meal_berries))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Garden Salad", R.drawable.meal_garden_salad,"Without dressing")));

        mealDayModels.add(12, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Omelette with Whole Wheat Bread + Coffee" , R.drawable.meal_omelette, "4 egg white and 1 egg yolk"))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Banana Greek Yogurt", R.drawable.meal_banana_yogurt))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Beef Bolognese Spaghetti", R.drawable.meal_beef_spaghetti))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Watermelon", R.drawable.meal_watermelon))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Boiled Egg with Tomato and Carrot", R.drawable.meal_tomato_carrot)));

        mealDayModels.add(13, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Blueberry Pancake" , R.drawable.meal_blueberry_pancake))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Nuts + Tea", R.drawable.meal_almond, "Any kind of nut and tea without sugar"))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Brown Rice with Stir-fry Prawn and Spinach", R.drawable.meal_prawn_spinach))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Apple", R.drawable.meal_apple))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Chicken Breast Salad", R.drawable.meal_chicken_salad,"Without Dressing")));

        mealDayModels.add(14, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Boiled with Toast + Coffee" , R.drawable.meal_egg_toast))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Guava", R.drawable.meal_guava))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Grill Lamb Steak with Green Beans and Potato", R.drawable.meal_lamb_greenbean))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Orange Juice", R.drawable.meal_orange_juice))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Steam Squid with Lime Sauce", R.drawable.meal_lime_squid)));

        mealDayModels.add(15, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Egg White Salad" , R.drawable.meal_salad,"Natural Greek Yogurt Dressing"))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Berries + Tea", R.drawable.meal_berries))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Grill Dory Fish with Potato and Green Beans", R.drawable.meal_dory_greenbean))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Banana", R.drawable.meal_banana))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Boiled Egg with Asparagus", R.drawable.meal_egg_asparagus)));

        mealDayModels.add(16, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Salmon Cucumber Toast" , R.drawable.meal_salmon_toast))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Nuts + Tea", R.drawable.food_green_tea,"Any kind of nut and tea without sugar"))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Brown Rice with Stir-fry Chicken and Spring Onion", R.drawable.meal_chicken_spring_onion))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Berries", R.drawable.meal_berries))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Steam Egg with Mushroom", R.drawable.meal_steamegg_mushroom,"4 egg white and 1 egg yolk")));

        mealDayModels.add(17, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Ham Omelette" , R.drawable.meal_ham_omelette))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Banana + Tea", R.drawable.meal_banana))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Baked Salmon with Corn and Spinach", R.drawable.meal_salmon_spinach))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Apple", R.drawable.meal_apple))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Steam Prawn with Garlic", R.drawable.meal_steam_prawn)));

        mealDayModels.add(18, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Oatmeal and Scramble Egg" , R.drawable.meal_oat_egg))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Nuts + Tea", R.drawable.meal_walnut,"Any kind of nut and tea without sugar"))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Chicken Sandwich with Lettuce and Tomato", R.drawable.meal_chicken_sandwish,"Without Sauce"))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Guava", R.drawable.meal_guava))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Garden Salad", R.drawable.meal_garden_salad)));

        mealDayModels.add(19, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Avocado Chicken Wrap" , R.drawable.meal_chicken_avocado))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Orange + Tea", R.drawable.meal_orange))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Spaghetti with Prawn and Asparagus", R.drawable.meal_prawn_spaghetti))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Banana", R.drawable.meal_banana))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Steam Tofu with Minced Chicken and Mushroom", R.drawable.meal_tofu_mushroom)));

        mealDayModels.add(20, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Peanut Butter Waffle" , R.drawable.meal_peanut_waffle,"2 teaspoon peanut butter"))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Apple", R.drawable.meal_apple))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Stir-fry Beef with Brocolli and Carrot", R.drawable.meal_beef_carrot))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Papaya", R.drawable.meal_papaya))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Boiled Egg White and Garden Salad", R.drawable.meal_salad,"Without Dressing")));

        mealDayModels.add(21, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Banana Nut Oatmeal" , R.drawable.meal_banana_oatmeal))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Apple and Tea", R.drawable.meal_apple))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Roasted Lamb with Carrot and Brocolli", R.drawable.meal_lamb_carrot))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Papaya", R.drawable.meal_papaya))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Boiled Egg White and Asparagus", R.drawable.meal_egg_asparagus)));

        mealDayModels.add(22, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Salmon Egg Toast" , R.drawable.meal_salmon_toast))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Nutsand Tea", R.drawable.food_tea))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Brown Rice with Grill Tuna and Bell Pepper", R.drawable.meal_tuna_pepper))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Banana", R.drawable.meal_banana))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Chicken Breast Salad", R.drawable.meal_chicken_salad,"Without skin and dressing")));

        mealDayModels.add(23, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Ham Omelette" , R.drawable.meal_ham_omelette))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Guava and Tea", R.drawable.meal_guava))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Salmon with Mashed Potato and Asparagus", R.drawable.meal_salmon_asparagus))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Berries", R.drawable.meal_berries))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Steam Egg with Mushroom", R.drawable.meal_steamegg_mushroom,"4 egg white 1 egg yolk")));

        mealDayModels.add(24, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Banana Kiwi Overnight Oat" , R.drawable.meal_oat_banana_kiwi))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Nuts and Tea", R.drawable.food_green_tea))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Roasted Chicken with Potato and Carrot", R.drawable.meal_chicken_carrot_potato,"Remove chicken skin and fat"))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Banana", R.drawable.meal_banana))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Garden Salad", R.drawable.meal_garden_salad,"Without dressing")));

        mealDayModels.add(25, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Avocado Toast with Egg + Coffee" , R.drawable.meal_avocado_egg_toast))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Orange", R.drawable.meal_orange))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Brown Rice with Grill Dory Fish and Brocolli", R.drawable.meal_dory_brocolli))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Apple", R.drawable.meal_apple))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Boiled Egg White and Spinach", R.drawable.meal_egg_spinach)));

        mealDayModels.add(26, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Scramble Egg and Whole Wheat Bread + Coffee" , R.drawable.meal_egg_bread))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Watermelon", R.drawable.meal_watermelon))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Brown Rice with Stir-fry Prawn and Squid and Asparagus", R.drawable.meal_prawn_squid_asparagus))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Banana", R.drawable.meal_banana))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Steam Tofu with Minced Chicken and Mushroom", R.drawable.meal_tofu_mushroom)));

        mealDayModels.add(27, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Banana Pancake" , R.drawable.meal_banana_pancake,"Without Sauce"))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Berries and Tea", R.drawable.meal_berries))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Grill Steak with Bell Pepper", R.drawable.meal_steak_pepper))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Carrot Juice", R.drawable.meal_carrot_juice))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Prawn Salad", R.drawable.meal_prawn_salad,"With lime dressing")));

        mealDayModels.add(28, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Berries Overnight Oat" , R.drawable.meal_berries_overnightoat))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Guava", R.drawable.meal_guava))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Brown Rice with Baked Salmon and Spinach", R.drawable.meal_salmon_spinach))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Tomato Juice", R.drawable.meal_tomato_juice))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Egg White and Garden Salad", R.drawable.meal_salad,"Without Dressing")));

        mealDayModels.add(29, new MealDayModel()
                .addDish(MealDayModel.MealType.breakfast, new DishModel("Oatmeal and Scramble Egg White" , R.drawable.meal_oat_egg))
                .addDish(MealDayModel.MealType.morning_snack, new DishModel("Apple", R.drawable.meal_apple))
                .addDish(MealDayModel.MealType.lunch, new DishModel("Brown Rice with Roasted Chicken and Brocolli", R.drawable.meal_rice_chicken_brocolli,"Remove chicken fat and skin"))
                .addDish(MealDayModel.MealType.evening_snack, new DishModel("Banana", R.drawable.meal_banana))
                .addDish(MealDayModel.MealType.dinner, new DishModel("Boiled Egg White", R.drawable.meal_egg_white,"6 egg white")));
    }

    private void inputTipOfTheDay(){
        for(int i=0; i<30; i++){
            tipOfTheDays.add(0, "You SHOULD NOT add sugar to your tea and coffee within 30 days!");
            tipOfTheDays.add(1, "Always remember to remove skin and fat from the meat");
        }
    }

    private void inputIngredientsList(){
        ArrayList<Pair<String, String>> ingredientsWeek1 = new ArrayList();
        ingredientsWeek1.add(new Pair<String, String>("Apple", "3 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Asparagus", "1 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Banana", "3 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Bell Pepper", "1 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Berries", "3 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Bolognese Sauce", "1 bottle"));
        ingredientsWeek1.add(new Pair<String, String>("Brocolli", "2 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Brown Rice", "1 pack"));
        ingredientsWeek1.add(new Pair<String, String>("Celery", "1 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Chicken Breast", "5 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Coffee", "3 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Dory Fish", "1 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Egg", "24 unit"));
        ingredientsWeek1.add(new Pair<String, String>("Garlic", "1 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Green Beans", "1 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Lean Ham", "2 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Lettuce", "2 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Mushroom", "1 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Natural Greek Yogurt", "2 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Nuts", "3 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Oatmeal", "1 pack"));
        ingredientsWeek1.add(new Pair<String, String>("Olive Oil", "1 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Orange", "1 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Papaya", "1 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Lean Pork Meat", "1 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Potato", "3 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Prawn", "1 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Salad", "2 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Salmon", "1 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Spinach", "3 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Steak", "1 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Tea", "3 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Tomato", "4 serv"));
        ingredientsWeek1.add(new Pair<String, String>("Whole Wheat Bread", "1 pack"));
        ingredientsWeek1.add(new Pair<String, String>("Whole Wheat Spaghetti", "1 pack"));
        ingredientsWeek1.add(new Pair<String, String>("Whole Wheat Wrap", "1 pack"));


        ingredientsList.add(ingredientsWeek1);

        ArrayList<Pair<String, String>> ingredientsWeek2 = new ArrayList();
        ingredientsWeek2.add(new Pair<String, String>("Apple", "3 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Banana", "3 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Bell Pepper", "1 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Berries", "3 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Brocolli", "1 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Brown Rice", "1 pack"));
        ingredientsWeek2.add(new Pair<String, String>("Carrot", "3 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Celery", "2 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Cereal", "1 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Chicken Breast", "3 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Coffee", "2 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Cucumber", "1 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Dory Fish", "1 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Duck Meat", "1 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Egg", "14 Unit"));
        ingredientsWeek2.add(new Pair<String, String>("Green Beans", "1 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Guava", "2 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Lettuce", "1 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Minced Chicken", "1 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Mushroom", "1 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Natural Greek Yogurt", "2 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Nuts", "2 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Oatmeal", "1 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Orange", "1 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Pancake", "1 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Papaya", "1 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Peanut Butter", "1 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Lean Pork Meat", "1 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Potato", "2 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Prawn", "1 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Salad", "2 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Salmon", "1 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Spinach", "3 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Spring Onion", "1 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Steak", "1 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Tea", "5 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Tilapia", "1 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Tofu", "1 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Tomato", "3 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Tuna", "1 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Watermelon", "1 serv"));
        ingredientsWeek2.add(new Pair<String, String>("Whole Wheat Bread", "1 pack"));
        ingredientsWeek2.add(new Pair<String, String>("Whole Wheat Spaghetti", "1 pack"));
        
        ingredientsList.add(ingredientsWeek2);

        ArrayList<Pair<String, String>> ingredientsWeek3 = new ArrayList();
        ingredientsWeek3.add(new Pair<String, String>("Apple", "2 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Asparagus", "2 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Avocado", "1 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Banana", "3 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Berries", "2 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Brocolli", "1 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Brown Rice", "1 pack"));
        ingredientsWeek3.add(new Pair<String, String>("Carrot", "1 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Chicken Breast", "3 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Coffee", "2 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Corn", "1 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Cucumber", "1 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Dory Fish", "1 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Egg", "24 Unit"));
        ingredientsWeek3.add(new Pair<String, String>("Garlic", "1 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Green Beans", "2 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Guava", "2 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Lamb", "1 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Lean Ham", "1 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Lettuce", "2 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Lime", "1 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Minced Chicken", "1 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Mushroom", "2 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Natural Greek Yogurt", "1 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Nuts", "2 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Oatmeal", "1 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Orange", "2 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Papaya", "1 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Peanut Butter", "1 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Potato", "2 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Prawn", "2 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Salad", "3 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Salmon", "2 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Spinach", "1 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Spring Onion", "1 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Squid", "1 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Steak", "1 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Tea", "5 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Tofu", "1 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Tomato", "1 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Waffle", "1 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Watermelon", "1 serv"));
        ingredientsWeek3.add(new Pair<String, String>("Whole Wheat Bread", "1 pack"));
        ingredientsWeek3.add(new Pair<String, String>("Whole Wheat Spaghetti", "1 pack"));
        ingredientsWeek3.add(new Pair<String, String>("Whole Wheat Wrap", "1 pack"));

        ingredientsList.add(ingredientsWeek3);

        ArrayList<Pair<String, String>> ingredientsWeek4 = new ArrayList();
        ingredientsWeek4.add(new Pair<String, String>("Apple", "3 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Asparagus", "3 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Avocado", "1 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Banana", "8 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Bell Pepper", "2 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Berries", "3 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Brocolli", "3 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Brown Rice", "1 pack"));
        ingredientsWeek4.add(new Pair<String, String>("Carrot", "3 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Chicken Breast", "3 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Coffee", "1 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Dory Fish", "1 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Egg", "34 Unit"));
        ingredientsWeek4.add(new Pair<String, String>("Guava", "2 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Kiwi", "1 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Lamb", "1 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Lean Ham", "1 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Lettuce", "1 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Minced Chicken", "1 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Mushroom", "2 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Natural Greek Yogurt", "2 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Nuts", "3 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Oatmeal", "1 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Orange", "1 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Pancake", "1 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Papaya", "1 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Potato", "2 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Prawn", "2 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Salad", "4 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Salmon", "3 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Spinach", "2 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Squid", "1 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Steak", "1 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Tea", "5 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Tofu", "1 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Tomato", "1 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Tuna", "1 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Watermelon", "1 serv"));
        ingredientsWeek4.add(new Pair<String, String>("Whole Wheat Bread", "1 pack"));

        ingredientsList.add(ingredientsWeek4);

    }

}
