package com.challenge.bennho.a30days.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.challenge.bennho.a30days.enums.PreferenceType;

/**
 * Created by SiongLeng on 29/8/2016.
 */
public class PreferenceUtils {

    private static SharedPreferences sharedPref;

    public static void delete(Context context, PreferenceType preferenceType){
        delete(context, preferenceType.name());
    }

    public static void delete(Context context, String key){
        SharedPreferences preferences = getSharedPref(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.commit();
    }

    public static void put(Context context, PreferenceType preferenceType, String value){
        put(context, preferenceType.name(), value);
    }

    public static void put(Context context, String key, String value){
        SharedPreferences preferences = getSharedPref(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String get(Context context, PreferenceType preferenceType){
        return get(context, preferenceType.name());
    }

    public static String get(Context context, String key){
        SharedPreferences preferences = getSharedPref(context);

        String data = preferences.getString(key, "");
        return data;
    }

    public static SharedPreferences getSharedPref(Context context) {
        if(sharedPref == null)
            sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref;
    }


}
