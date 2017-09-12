package com.challenge.bennho.a30days.activities;

import android.content.Intent;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.os.Bundle;
import android.preference.PreferenceScreen;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.enums.PreferenceType;
import com.challenge.bennho.a30days.helpers.AllReminderHelper;
import com.challenge.bennho.a30days.models.RunHistoryModel;
import com.challenge.bennho.a30days.models.User;

import java.util.Locale;

public class RunHistoryActivity extends MyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        onLayoutSet();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getFragmentManager().beginTransaction()
                .replace(R.id.layout_run_settings, new RunSettingsFragment())
                .commit();

    }

    @Override
    protected void onPause() {
        super.onPause();
        AllReminderHelper.updateReminders(this);
    }

    public static class RunSettingsFragment extends PreferenceFragment {

        private User user;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.run_settings);

            user = new User(this.getActivity());
            user.reload();

            init();
        }

        public void init(){
            PreferenceScreen screen = this.getPreferenceScreen();

            PreferenceCategory category = new PreferenceCategory(screen.getContext());
            category.setTitle("Pick your run save slot");
            screen.addPreference(category);

            int i = 0;
            for(RunHistoryModel runHistoryModel : user.getRunHistoriesModel().getRunHistoryModels()) {
                CheckBoxPreference preference = new CheckBoxPreference(getActivity());
                preference.setKey(String.valueOf(0));
                preference.setTitle("Run " + (i + 1));
                preference.setSummary(String.format(Locale.ENGLISH, "Day %s, Total Calories Burnt %s, Total Running Time %s",
                        runHistoryModel.getCurrentExerciseDay(), runHistoryModel.getTotalCaloriesBurnt(),
                        runHistoryModel.getTotalRunningSecs()));
                preference.setChecked(i == user.getCurrentIteration());
                category.addPreference(preference);
                i += 1;

                preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference preference) {
                        if(((CheckBoxPreference) preference).isChecked()) {
                            return true;
                        }
                        else {
                            int q = 0;
                            for(RunHistoryModel runHistoryModel : user.getRunHistoriesModel().getRunHistoryModels()) {
                                if(q != Integer.valueOf(preference.getKey())){
                                    ((CheckBoxPreference) findPreference(String.valueOf(q))).setChecked(false);
                                }
                                q += 1;
                            }

                            user.setCurrentIteration(Integer.valueOf(preference.getKey()));

                        }

                        return true;
                    }
                });

            }
        }


    }

}
