package com.challenge.bennho.a30days.helpers;

import com.challenge.bennho.a30days.models.FoodModel;

import java.util.ArrayList;

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
        result.add(new FoodModel(FoodModel.FoodType.french_fries));
        result.add(new FoodModel(FoodModel.FoodType.french_fries));
      //  result.add(new FoodModel(FoodModel.FoodType.french_fries));

        return result;
    }

}
