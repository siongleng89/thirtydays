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
        result.add(new FoodModel(FoodModel.FoodType.almond));
        result.add(new FoodModel(FoodModel.FoodType.apple));
        result.add(new FoodModel(FoodModel.FoodType.banana));
        result.add(new FoodModel(FoodModel.FoodType.bean));
        result.add(new FoodModel(FoodModel.FoodType.biscuit));
        result.add(new FoodModel(FoodModel.FoodType.bread));
        result.add(new FoodModel(FoodModel.FoodType.broccolli));
        result.add(new FoodModel(FoodModel.FoodType.bubble_milk_tea));
        result.add(new FoodModel(FoodModel.FoodType.candy));
        result.add(new FoodModel(FoodModel.FoodType.carrot));
        result.add(new FoodModel(FoodModel.FoodType.cheese));
        result.add(new FoodModel(FoodModel.FoodType.cheese_burger));
        result.add(new FoodModel(FoodModel.FoodType.cheese_tart));
        result.add(new FoodModel(FoodModel.FoodType.chicken));
        result.add(new FoodModel(FoodModel.FoodType.chicken_nugget));
        result.add(new FoodModel(FoodModel.FoodType.chocolate));
        result.add(new FoodModel(FoodModel.FoodType.chocolate_cake));
        result.add(new FoodModel(FoodModel.FoodType.coca_cola));
        result.add(new FoodModel(FoodModel.FoodType.corn));
        result.add(new FoodModel(FoodModel.FoodType.cupcake));
        result.add(new FoodModel(FoodModel.FoodType.donut));
        result.add(new FoodModel(FoodModel.FoodType.egg));
        result.add(new FoodModel(FoodModel.FoodType.fried_chicken));
        result.add(new FoodModel(FoodModel.FoodType.ham));
        result.add(new FoodModel(FoodModel.FoodType.ice_cream));
        result.add(new FoodModel(FoodModel.FoodType.instant_noodle));
        result.add(new FoodModel(FoodModel.FoodType.milkshake));
        result.add(new FoodModel(FoodModel.FoodType.oat));
        result.add(new FoodModel(FoodModel.FoodType.pancake));
        result.add(new FoodModel(FoodModel.FoodType.peanut_butter));
        result.add(new FoodModel(FoodModel.FoodType.pineapple));
        result.add(new FoodModel(FoodModel.FoodType.pizza));
        result.add(new FoodModel(FoodModel.FoodType.popcorn));
        result.add(new FoodModel(FoodModel.FoodType.pork));
        result.add(new FoodModel(FoodModel.FoodType.potato_chip));
        result.add(new FoodModel(FoodModel.FoodType.prawn));
        result.add(new FoodModel(FoodModel.FoodType.rice));
        result.add(new FoodModel(FoodModel.FoodType.salmon));
        result.add(new FoodModel(FoodModel.FoodType.sandwich));
        result.add(new FoodModel(FoodModel.FoodType.sausage));
        result.add(new FoodModel(FoodModel.FoodType.speghetti));
        result.add(new FoodModel(FoodModel.FoodType.spinach));
        result.add(new FoodModel(FoodModel.FoodType.steak));
        result.add(new FoodModel(FoodModel.FoodType.strawberry));
        result.add(new FoodModel(FoodModel.FoodType.sushi));
        result.add(new FoodModel(FoodModel.FoodType.sweet_potato));
        result.add(new FoodModel(FoodModel.FoodType.tea));
        result.add(new FoodModel(FoodModel.FoodType.tomato));
        result.add(new FoodModel(FoodModel.FoodType.waffle));
        result.add(new FoodModel(FoodModel.FoodType.water));

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
