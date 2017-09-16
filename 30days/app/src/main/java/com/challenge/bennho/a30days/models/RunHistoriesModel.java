package com.challenge.bennho.a30days.models;

import android.content.Context;

import com.challenge.bennho.a30days.enums.PreferenceType;
import com.challenge.bennho.a30days.helpers.PreferenceUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sionglengho on 10/9/17.
 */

public class RunHistoriesModel {

    private ArrayList<RunHistoryModel> runHistoryModels;

    public RunHistoriesModel() {
        this.runHistoryModels = new ArrayList<>();
    }

    public void load(Context context, int currentDay, int totalCaloriesBurnt, int totalRunningSecs) {
        String json = PreferenceUtils.getString(context, PreferenceType.RunHistories);
        if (json != null && !json.isEmpty()) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                runHistoryModels = objectMapper.readValue(json, new TypeReference<List<RunHistoryModel>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (runHistoryModels.size() < 10) {
            for (int i = 0; i < 10; i++) {
                runHistoryModels.add(new RunHistoryModel("1", "0", "0"));
            }
        }

        if (json == null || json.isEmpty()) {
            update(0, String.valueOf(currentDay), String.valueOf(totalCaloriesBurnt), String.valueOf(totalRunningSecs));
            save(context);
        }

    }

    public void update(int iteration, String currentExerciseDay, String totalCaloriesBurnt, String totalRunningSecs) {
        runHistoryModels.get(iteration).setCurrentExerciseDay(currentExerciseDay);
        runHistoryModels.get(iteration).setTotalCaloriesBurnt(totalCaloriesBurnt);
        runHistoryModels.get(iteration).setTotalRunningSecs(totalRunningSecs);
    }

    public void save(Context context) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(runHistoryModels);
            PreferenceUtils.putString(context, PreferenceType.RunHistories, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<RunHistoryModel> getRunHistoryModels() {
        return runHistoryModels;
    }

    public void setRunHistoryModels(ArrayList<RunHistoryModel> runHistoryModels) {
        this.runHistoryModels = runHistoryModels;
    }
}
