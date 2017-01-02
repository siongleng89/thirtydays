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

    public static void putString(Context context, PreferenceType preferenceType, String value){
        putString(context, preferenceType.name(), value);
    }

    public static void putString(Context context, String key, String value){
        SharedPreferences preferences = getSharedPref(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void putBoolean(Context context, PreferenceType preferenceType, Boolean value){
        putBoolean(context, preferenceType.name(), value);
    }

    public static void putBoolean(Context context, String key, Boolean value){
        SharedPreferences preferences = getSharedPref(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static String getString(Context context, PreferenceType preferenceType){
        return getString(context, preferenceType.name());
    }

    public static Boolean getBoolean(Context context, PreferenceType preferenceType){
        return getBoolean(context, preferenceType.name());
    }

    public static String getString(Context context, String key){
        SharedPreferences preferences = getSharedPref(context);

        String data = preferences.getString(key, "");
        return data;
    }

    public static Boolean getBoolean(Context context, String key){
        SharedPreferences preferences = getSharedPref(context);

        return preferences.getBoolean(key, false);
    }


    public static Double getDouble(Context context, PreferenceType preferenceType){
        String result = getString(context, preferenceType.name());
        if(Strings.isEmpty(result)){
            return 0.0;
        }
        else{
            return Double.valueOf(result);
        }
    }



    public static SharedPreferences getSharedPref(Context context) {
        if(sharedPref == null)
            sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref;
    }


}
