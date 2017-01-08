package com.challenge.bennho.a30days.helpers;

import android.content.Context;

import com.challenge.bennho.a30days.models.DishModel;

import java.util.ArrayList;

/**
 * Created by sionglengho on 9/1/17.
 */

public class MealsInputter {

    private Context context;
    private ArrayList<DishModel> dishModels;

    public MealsInputter(Context context) {
        this.context = context;
        dishModels = new ArrayList();
        input();
    }

    public DishModel getDishByDayNumber(int dayNumber){
        return dishModels.get(dayNumber - 1);
    }

    public void input(){
        dishModels.add(0, new DishModel("First Dish Name"));

        //.
        //.
        //.

        dishModels.add(29, new DishModel("Last Dish Name"));
    }

}
