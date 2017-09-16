package com.challenge.bennho.a30days.activities;

import android.content.Context;
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
        setContentView(R.layout.activity_run_history);
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

            Context mContext = screen.getContext();
            mContext.setTheme(R.style.AppTheme_PreferenceScreenStyle);


            PreferenceCategory category = new PreferenceCategory(screen.getContext());
            category.setTitle("Pick your run save slot");
            screen.addPreference(category);

            int i = 0;
            for(RunHistoryModel runHistoryModel : user.getRunHistoriesModel().getRunHistoryModels()) {
                CheckBoxPreference preference = new CheckBoxPreference(getActivity());
                preference.setKey(String.valueOf(i));
                preference.setTitle(getString(R.string.avty_run_history_slot) + " " + (i + 1));

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(String.format(Locale.ENGLISH,getString(R.string.day_X),
                        runHistoryModel.getCurrentExerciseDay()));
                stringBuilder.append("\n");

                stringBuilder.append(getString(R.string.summary_total_calories_burnt)).append(" ");
                stringBuilder.append(String.format(Locale.ENGLISH,getString(R.string.x_kcal),
                        runHistoryModel.getTotalCaloriesBurnt()));
                stringBuilder.append("\n");

                stringBuilder.append(getString(R.string.summary_total_exercise_duration)).append(" ");
                stringBuilder.append(String.valueOf((int) Math.ceil(Double.valueOf(runHistoryModel.getTotalRunningSecs()) / 60d))).append(" ");
                stringBuilder.append(getString(R.string.min_s));

                preference.setSummary(stringBuilder.toString());

                preference.setChecked(i == user.getCurrentIteration());
                preference.setEnabled(i != user.getCurrentIteration());
                category.addPreference(preference);
                i += 1;

                preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference preference) {
                        if(((CheckBoxPreference) preference).isChecked()) {
                            int q = 0;
                            for(RunHistoryModel runHistoryModel : user.getRunHistoriesModel().getRunHistoryModels()) {
                                if(q != Integer.valueOf(preference.getKey())){
                                    ((CheckBoxPreference) findPreference(String.valueOf(q))).setChecked(false);
                                    ((CheckBoxPreference) findPreference(String.valueOf(q))).setEnabled(true);
                                }
                                else {
                                    ((CheckBoxPreference) findPreference(String.valueOf(q))).setEnabled(false);
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
