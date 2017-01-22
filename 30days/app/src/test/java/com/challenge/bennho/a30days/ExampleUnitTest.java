package com.challenge.bennho.a30days;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.test.mock.MockContext;

import com.challenge.bennho.a30days.enums.PreferenceType;
import com.challenge.bennho.a30days.helpers.CaloriesToImagesConverter;
import com.challenge.bennho.a30days.helpers.PreferenceUtils;
import com.challenge.bennho.a30days.helpers.Strings;
import com.challenge.bennho.a30days.models.FoodModel;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.util.ArrayList;

import org.powermock.modules.junit4.PowerMockRunner;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({PreferenceUtils.class, PreferenceManager.class})
public class ExampleUnitTest {

    @Test
    public void testGetFood() throws Exception {

        CaloriesToImagesConverter caloriesToImagesConverter = new CaloriesToImagesConverter(400);
        MockContext mockContext = new MockContext();

        PowerMockito.mockStatic(PreferenceUtils.class);
        PowerMockito.mockStatic(PreferenceManager.class);

        ArrayList<FoodModel.FoodType> foodTypes = caloriesToImagesConverter.getTypeAFoods();
        ArrayList<String> foodTypeStrings = new ArrayList();
        for(FoodModel.FoodType foodType : foodTypes){
            foodTypeStrings.add(foodType.name());
        }

        PowerMockito.when(PreferenceUtils.getString(any(Context.class), any(PreferenceType.class)))
                .thenReturn(Strings.joinArr(foodTypeStrings, ","));


        ArrayList<FoodModel> foodModels = caloriesToImagesConverter.getFoods(mockContext);

        Assert.assertEquals(1, foodModels.size());
    }

    @Test
    public void testAllFoodImageLoadable() throws Exception {

        MockContext mockContext = new MockContext();
        for(FoodModel.FoodType foodType : FoodModel.FoodType.values()){
            FoodModel foodModel = new FoodModel(foodType);
            Assert.assertEquals(false, foodModel.getDrawable(mockContext) == null);
        }
    }

}