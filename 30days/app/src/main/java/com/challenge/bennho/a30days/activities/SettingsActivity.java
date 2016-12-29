package com.challenge.bennho.a30days.activities;

import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.enums.PreferenceType;
import com.challenge.bennho.a30days.helpers.CalculationHelper;
import com.challenge.bennho.a30days.helpers.Logs;
import com.challenge.bennho.a30days.helpers.PreferenceUtils;
import com.challenge.bennho.a30days.helpers.Strings;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    public static class SettingsFragment extends PreferenceFragment {

        private String originalUnitValue;


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.user_settings);

            originalUnitValue = ((ListPreference) findPreference(PreferenceType.Unit)).getValue();

            EditTextPreference weightPref = (EditTextPreference) findPreference(PreferenceType.Weight);
            weightPref.setSummary(weightPref.getText() + " " + getUnitText());

            EditTextPreference agePref = (EditTextPreference) findPreference(PreferenceType.Age);
            agePref.setSummary(agePref.getText());

            setListeners();

            init();
        }

        public void init(){
            enableNotificationChanged(((CheckBoxPreference)
                    findPreference(PreferenceType.EnableNotification)).isChecked());
        }

        public void setListeners(){
            findPreference(PreferenceType.EnableNotification).setOnPreferenceChangeListener(
                    new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    enableNotificationChanged((boolean) newValue);
                    return true;
                }
            });

            findPreference(PreferenceType.Weight).setOnPreferenceChangeListener(
                    new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if(Strings.isNumeric(newValue.toString())){
                        preference.setSummary(newValue.toString() + " " + getUnitText());
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            });

            findPreference(PreferenceType.Age).setOnPreferenceChangeListener(
                    new Preference.OnPreferenceChangeListener() {
                        @Override
                        public boolean onPreferenceChange(Preference preference, Object newValue) {
                            if(Strings.isNumeric(newValue.toString())){
                                preference.setSummary(newValue.toString());
                                return true;
                            }
                            else{
                                return false;
                            }
                        }
                    });

            findPreference(PreferenceType.Unit).setOnPreferenceChangeListener(
                    new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if(!originalUnitValue.equals(newValue.toString())){
                        originalUnitValue = newValue.toString();

                        EditTextPreference weightPref = (EditTextPreference) findPreference(PreferenceType.Weight);
                        String currentWeight = weightPref.getText();
                        if(Strings.isNumeric(currentWeight)){
                            Double weight = Double.valueOf(currentWeight);
                            Double converted = 0.0;
                            if(originalUnitValue.equals("kg")){
                                converted = CalculationHelper.poundsToKg(weight);
                            }
                            else{
                                converted = CalculationHelper.kgToPounds(weight);
                            }

                            PreferenceUtils.put(SettingsFragment.this.getActivity(),
                                                    PreferenceType.Weight, String.valueOf(converted));
                            weightPref.setText(converted.toString());


                            ListPreference unitPrefs = ((ListPreference) findPreference(PreferenceType.Unit));
                            int newIndex = unitPrefs.findIndexOfValue(originalUnitValue);
                            weightPref.setSummary(converted + " " + unitPrefs.getEntries()[newIndex]);


                        }

                        return true;
                    }

                    return false;
                }
            });

        }


        private void enableNotificationChanged(boolean isEnabled){
            findPreference(PreferenceType.ReminderDay).setEnabled(isEnabled);
            findPreference(PreferenceType.ReminderTime).setEnabled(isEnabled);
        }

        private String getUnitText(){
            ListPreference unitPreference = (ListPreference) findPreference(PreferenceType.Unit);
            return unitPreference.getEntry().toString();
        }

        public Preference findPreference(PreferenceType type) {
            return findPreference(type.name());
        }


    }











}
