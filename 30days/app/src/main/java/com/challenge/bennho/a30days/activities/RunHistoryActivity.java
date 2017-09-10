package com.challenge.bennho.a30days.activities;

import android.content.Intent;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.os.Bundle;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.enums.PreferenceType;
import com.challenge.bennho.a30days.helpers.AllReminderHelper;

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
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.user_settings);


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




            findPreference("Parameter").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Intent intent = new Intent(getActivity(), PersonalDetailActivity.class);
                    intent.putExtra("initial", "0");
                    startActivity(intent);
                    return true;
                }
            });

            findPreference("RunHistory").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Intent intent = new Intent(getActivity(), RunHistoryActivity.class);
                    startActivity(intent);
                    return true;
                }
            });


        }


        private void enableNotificationChanged(boolean isEnabled){
            findPreference(PreferenceType.ReminderDay).setEnabled(isEnabled);
            findPreference(PreferenceType.ReminderTime).setEnabled(isEnabled);
        }


        public Preference findPreference(PreferenceType type) {
            return findPreference(type.name());
        }


    }

}
