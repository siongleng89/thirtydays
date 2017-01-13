package com.challenge.bennho.a30days.models;

import android.content.Context;

import com.challenge.bennho.a30days.R;

import java.util.Comparator;

/**
 * Created by sionglengho on 12/1/17.
 */

public class IngredientModel {

    private String name;
    private String quantity;
    private IngredientType ingredientType;

    public IngredientModel(String name, String quantity, IngredientType ingredientType) {
        this.name = name;
        this.quantity = quantity;
        this.ingredientType = ingredientType;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public IngredientType getIngredientType() {
        return ingredientType;
    }

    public enum IngredientType{
        fruits, vegetable, carbohydrate, protein, others;

        public String toString(Context context) {
            switch (this){
                case fruits:
                    return context.getString(R.string.itype_fruits);
                case vegetable:
                    return context.getString(R.string.itype_vege);
                case carbohydrate:
                    return context.getString(R.string.itype_carbo);
                case protein:
                    return context.getString(R.string.itype_protein);

                case others:
                    return context.getString(R.string.itype_others);

            }
            return "";
        }
    }

}
