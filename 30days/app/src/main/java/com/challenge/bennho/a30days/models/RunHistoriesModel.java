package com.challenge.bennho.a30days.models;

import java.util.ArrayList;

/**
 * Created by sionglengho on 10/9/17.
 */

public class RunHistoriesModel {

    private ArrayList<RunHistoryModel> runHistoryModels;

    public RunHistoriesModel() {
        this.runHistoryModels = new ArrayList<>();
    }

    public void update() {

    }


    public ArrayList<RunHistoryModel> getRunHistoryModels() {
        return runHistoryModels;
    }

    public void setRunHistoryModels(ArrayList<RunHistoryModel> runHistoryModels) {
        this.runHistoryModels = runHistoryModels;
    }
}
