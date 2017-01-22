package com.challenge.bennho.a30days;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.ContextCompat;
import android.test.mock.MockContext;

import com.challenge.bennho.a30days.helpers.Logs;
import com.challenge.bennho.a30days.models.FoodModel;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private Context instrumentationCtx;

    @Before
    public void setup() {
        instrumentationCtx = InstrumentationRegistry.getContext();
    }

    @Test
    public void testAllFoodImageLoadable() throws Exception {

        int z = R.drawable.food_pizza;
//        int b = getInstrumentation().getContext().getResources()
//                .getIdentifier(R.drawable.food_pizza, "drawable", getInstrumentation().getContext().getPackageName());

//        for(FoodModel.FoodType foodType : FoodModel.FoodType.values()){
//            FoodModel foodModel = new FoodModel(foodType);
//            if(foodType == FoodModel.FoodType.nil) continue;
//            Logs.show(foodType);
//            Assert.assertEquals(false, foodModel.getDrawableId(getBaseContext()) == 0);
//        }
    }


}
