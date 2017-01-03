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


    //
    public ArrayList<FoodModel> getFoods(){
        ArrayList<FoodModel> result = new ArrayList();
        result.add(new FoodModel(FoodModel.FoodType.french_fries));
        result.add(new FoodModel(FoodModel.FoodType.apple));
        result.add(new FoodModel(FoodModel.FoodType.banana));


        /*almond 529
        apple 78
        banana 89
        bean 109
        biscuit 270
        bread 80
        broccolli 30
        bubblemilktea 230
        candy 270
        carrot 40
        cheese 110
        cheeseburger 303
        cheesetart 181
        chicken 231
        chickennugget 296
        chocolate 235
        chocolatecake 352
        cocacola 140
        corn 365
        cupcake 131
        donut 195
        egg 78
        frenchfries 477
        friedchicken 285
        ham 123
        icecream 143
        instantnoodle 380
        milkshake 356
        oat 166
        pancake 268
        peanutebutter 188
        pineapple 82
        popcorn 142
        pork 360
        potatochip 152
        prawn 105
        rice 206
        salmon 412
        sandwich 348
        sausage 229
        speghetti 221
        spinach 41
        steak 679
        strawberry 47
        sushi 240
        sweetpotato 112
        tea 2
        tomato 26
        waffle 204
        water 0*/
        //  result.add(new FoodModel(FoodModel.FoodType.french_fries));

        return result;
    }

}
