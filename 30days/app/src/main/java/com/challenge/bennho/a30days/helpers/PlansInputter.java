package com.challenge.bennho.a30days.helpers;

import android.content.Context;

import com.challenge.bennho.a30days.models.ExerciseModel;
import com.challenge.bennho.a30days.models.ExercisePartModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sionglengho on 30/12/16.
 */

public class PlansInputter {

    private Context context;



    ArrayList<ExerciseModel> exerciseModels;

    public PlansInputter(Context context) {
        this.context = context;
        exerciseModels = new ArrayList();
        input();


    }

    /**
     * ageFactor
     * 10-29(run=1, sprint=1)
     * 30-39(run=1/1.2, sprint=1/1.2)
     * 40-49(run=1/1.5, sprint=1/1.5)
     * >50=(run=1/2, sprint=0)
     *
     * bmiFactor
     * 0-24.9(run=1, sprint=1)
     * 25-29.9=(run=1/1.5, sprint=1/1.5)
     * >30=(run=1/2, sprint=1/2)
     *  >35=(run=1/6, sprint=0)
     * BMI = weight in kilograms / height in meters²
     * @param day
     * @param age
     * @param bmi
     * @return
     */
    public ExerciseModel getExerciseModelByDay(int day, int age, double bmi, int difficulty){

        ExerciseModel exerciseModel = exerciseModels.get(day - 1);
        double runFactor = 1d;
        double sprintFactor = 1d;

        if(age >= 50){
            runFactor = 1d / 2d;
            sprintFactor = 0d;
        }
        else if(age >= 40){
            runFactor = 1d / 1.5d;
            sprintFactor = 1d / 1.5d;
        }
        else if(age >= 30){
            runFactor = 1d / 1.2d;
            sprintFactor = 1d / 1.2d;
        }


        decreaseIntensity(exerciseModel, runFactor, sprintFactor);

        runFactor = 1d;
        sprintFactor = 1d;


        if(bmi >= 35){
            runFactor = 1d / 2d;
            sprintFactor = 0d;
        }
        else if(bmi >= 30){
            runFactor = 1d / 1.5d;
            sprintFactor = 1d / 1.5d;
        }
        else if(bmi >= 25) {
            runFactor = 1d / 1.2d;
            sprintFactor = 1d / 1.2d;
        }


        decreaseIntensity(exerciseModel, runFactor, sprintFactor);
        processDifficulties(exerciseModel, difficulty);
        removedZeroDurationExercisePart(exerciseModel);

        return exerciseModel;
    }

    private void decreaseIntensity(ExerciseModel exerciseModel, double runFactor,
                                                double sprintFactor) {
        //processing run factor
        for (int i = 0; i < exerciseModel.getExercisePartModels().size(); i++) {
            if (exerciseModel.getExercisePartModels().get(i).getExerciseState()
                    == ExercisePartModel.ExerciseState.Run) {
                ExercisePartModel toMinusModel = exerciseModel.getExercisePartModels().get(i);
                int minusSecs = (int) toMinusModel.getDurationSecs() -
                        (int) (toMinusModel.getDurationSecs() * runFactor);

                for (int q = i; q < exerciseModel.getExercisePartModels().size(); q++) {
                    if (exerciseModel.getExercisePartModels().get(q).getExerciseState() !=
                            ExercisePartModel.ExerciseState.Run &&
                            exerciseModel.getExercisePartModels().get(q).getExerciseState() !=
                                    ExercisePartModel.ExerciseState.Sprint) {

                        ExercisePartModel toAddModel = exerciseModel.getExercisePartModels().get(q);
                        toMinusModel.setDurationSecs(toMinusModel.getDurationSecs() - minusSecs);
                        toAddModel.setDurationSecs(toAddModel.getDurationSecs() + minusSecs);
                        break;
                    }
                }
            }
        }

        //processing sprint factor
        for (int i = 0; i < exerciseModel.getExercisePartModels().size(); i++) {
            if (exerciseModel.getExercisePartModels().get(i).getExerciseState()
                    == ExercisePartModel.ExerciseState.Sprint) {
                ExercisePartModel toMinusModel = exerciseModel.getExercisePartModels().get(i);
                int minusSecs =  (int) toMinusModel.getDurationSecs() -
                                    (int) (toMinusModel.getDurationSecs() * sprintFactor);

                for (int q = i; q < exerciseModel.getExercisePartModels().size(); q++) {
                    if (exerciseModel.getExercisePartModels().get(q).getExerciseState() !=
                            ExercisePartModel.ExerciseState.Run &&
                            exerciseModel.getExercisePartModels().get(q).getExerciseState() !=
                                    ExercisePartModel.ExerciseState.Sprint) {

                        ExercisePartModel toAddModel = exerciseModel.getExercisePartModels().get(q);
                        toMinusModel.setDurationSecs(toMinusModel.getDurationSecs() - minusSecs);
                        toAddModel.setDurationSecs(toAddModel.getDurationSecs() + minusSecs);
                        break;
                    }
                }
            }
        }
    }

    private void processDifficulties(ExerciseModel exerciseModel, int difficulty){
        if(difficulty <= 1) return;

        boolean isSprintBefore;
        for (int i = 0; i < exerciseModel.getExercisePartModels().size(); i++) {
            if(exerciseModel.getExercisePartModels().get(i).getExerciseState()
                                        == ExercisePartModel.ExerciseState.Sprint){
                isSprintBefore = true;
            }
            else{
                isSprintBefore = false;
            }

            if(exerciseModel.getExercisePartModels().get(i).getExerciseState()
                    == ExercisePartModel.ExerciseState.Run){
                if(!isSprintBefore){
                    ExercisePartModel toAddModel = exerciseModel.getExercisePartModels().get(i);
                    int toAddSecs = (difficulty - 1) * 30;
                    toAddModel.setDurationSecs(toAddModel.getDurationSecs() + toAddSecs);
                }
            }
            else if(exerciseModel.getExercisePartModels().get(i).getExerciseState()
                    == ExercisePartModel.ExerciseState.Walk){
                ExercisePartModel toMinusModel = exerciseModel.getExercisePartModels().get(i);
                int toMinusSecs = (difficulty - 1) * 5;
                toMinusModel.setDurationSecs(toMinusModel.getDurationSecs() - toMinusSecs);
            }

        }
    }

    private void removedZeroDurationExercisePart(ExerciseModel exerciseModel){
        ArrayList<Integer> indexToRemove = new ArrayList();
        for (int i = 0; i < exerciseModel.getExercisePartModels().size(); i++) {
            if(exerciseModel.getExercisePartModels().get(i).getDurationSecs() <= 0){
                indexToRemove.add(i);
            }
        }

        Collections.sort(indexToRemove, Collections.reverseOrder());
        for(Integer index : indexToRemove){
            exerciseModel.getExercisePartModels().remove(exerciseModel.getExercisePartModels().get(index));
        }
    }


    public void input(){

        //Day 1
        ExerciseModel day1 = new ExerciseModel();
        day1.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day1.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day1.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day1.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 180);
        day1.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day1.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 180);
        day1.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day1.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 180);
        day1.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day1.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 180);
        day1.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day1);


        //Day 2
        ExerciseModel day2 = new ExerciseModel();
        day2.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day2.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day2.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day2.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 150);
        day2.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day2.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 150);
        day2.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day2.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 150);
        day2.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day2.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 150);
        day2.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day2.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 150);
        day2.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day2);

        ExerciseModel day3 = new ExerciseModel();
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 150);
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 45);
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 150);
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 150);
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 45);
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 150);
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 150);
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day3);

        ExerciseModel day4 = new ExerciseModel();
        day4.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day4.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day4.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day4.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day4.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day4.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 120);
        day4.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day4.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day4.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day4.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 120);
        day4.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day4.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day4.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day4.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 120);
        day4.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day4);

        ExerciseModel day5 = new ExerciseModel();
        day5.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day5.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day5.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day5.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day5.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day5.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day5.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day5.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day5.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day5.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day5.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day5.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day5.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day5.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day5.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day5);

        ExerciseModel day6 = new ExerciseModel();
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 45);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 45);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 45);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day6);

        ExerciseModel day7 = new ExerciseModel();
        day7.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day7.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day7.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day7.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day7.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day7.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day7.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day7.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 60);
        day7.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day7.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day7.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day7.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day7.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day7.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 60);
        day7.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day7.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day7.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day7.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day7.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day7);

        ExerciseModel day8 = new ExerciseModel();
        day8.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day8.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day8.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 90);
        day8.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day8.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 90);
        day8.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day8.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 90);
        day8.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day8.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 90);
        day8.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day8.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 90);
        day8.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day8.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day8);

        ExerciseModel day9 = new ExerciseModel();
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 90);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 75);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 90);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 75);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 90);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day9);

        ExerciseModel day10 = new ExerciseModel();
        day10.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day10.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day10.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 120);
        day10.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day10.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 120);
        day10.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day10.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 120);
        day10.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day10.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 120);
        day10.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day10.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 120);
        day10.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day10.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day10);

        ExerciseModel day11 = new ExerciseModel();
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 120);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 105);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 120);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 105);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 120);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day11);

        ExerciseModel day12 = new ExerciseModel();
        day12.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day12.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day12.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 150);
        day12.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day12.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 150);
        day12.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day12.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 150);
        day12.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day12.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 150);
        day12.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day12.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 150);
        day12.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day12.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day12);

        ExerciseModel day13 = new ExerciseModel();
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 150);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 135);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 150);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 135);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 150);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day13);

        ExerciseModel day14 = new ExerciseModel();
        day14.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day14.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day14.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 210);
        day14.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day14.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 210);
        day14.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day14.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 210);
        day14.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 120);
        day14.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day14);

        ExerciseModel day15 = new ExerciseModel();
        day15.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day15.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day15.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 165);
        day15.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day15.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day15.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day15.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 165);
        day15.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day15.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day15.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day15.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 165);
        day15.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day15.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day15.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day15.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day15);

        ExerciseModel day16 = new ExerciseModel();
        day16.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day16.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day16.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 195);
        day16.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day16.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day16.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day16.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 195);
        day16.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day16.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day16.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day16.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 195);
        day16.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day16.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day16.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day16.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day16);

        ExerciseModel day17 = new ExerciseModel();
        day17.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day17.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day17.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 225);
        day17.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day17.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day17.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day17.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 225);
        day17.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day17.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day17.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day17.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 225);
        day17.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day17.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day17.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day17.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day17);

        ExerciseModel day18 = new ExerciseModel();
        day18.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day18.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day18.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 255);
        day18.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day18.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day18.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day18.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 255);
        day18.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day18.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day18.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day18.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 255);
        day18.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day18.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day18.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day18.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day18);

        ExerciseModel day19 = new ExerciseModel();
        day19.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day19.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day19.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 285);
        day19.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day19.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day19.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day19.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 300);
        day19.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day19.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day19.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day19.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 285);
        day19.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day19.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day19.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day19.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day19);

        ExerciseModel day20 = new ExerciseModel();
        day20.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day20.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day20.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 315);
        day20.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day20.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day20.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day20.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 315);
        day20.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day20.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day20.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day20.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 315);
        day20.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day20.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day20.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day20.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day20);

        ExerciseModel day21 = new ExerciseModel();
        day21.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day21.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day21.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 390);
        day21.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day21.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 390);
        day21.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 120);
        day21.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day21);

        ExerciseModel day22 = new ExerciseModel();
        day22.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day22.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day22.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 345);
        day22.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day22.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day22.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day22.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 345);
        day22.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day22.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day22.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day22.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 345);
        day22.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day22.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day22.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day22.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day22);

        ExerciseModel day23 = new ExerciseModel();
        day23.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day23.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day23.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 375);
        day23.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day23.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day23.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day23.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 375);
        day23.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day23.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day23.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day23.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 375);
        day23.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day23.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day23.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day23.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day23);

        ExerciseModel day24 = new ExerciseModel();
        day24.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day24.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day24.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 405);
        day24.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day24.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day24.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day24.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 405);
        day24.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day24.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day24.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day24.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 405);
        day24.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day24.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day24.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day24.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day24);

        ExerciseModel day25 = new ExerciseModel();
        day25.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day25.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day25.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 435);
        day25.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day25.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day25.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day25.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 435);
        day25.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day25.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day25.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day25.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 435);
        day25.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day25.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day25.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day25.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day25);

        ExerciseModel day26 = new ExerciseModel();
        day26.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day26.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day26.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 465);
        day26.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day26.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day26.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day26.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 465);
        day26.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day26.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day26.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day26.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 465);
        day26.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day26.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day26.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day26.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day26);

        ExerciseModel day27 = new ExerciseModel();
        day27.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day27.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day27.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 495);
        day27.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day27.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day27.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day27.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 495);
        day27.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day27.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day27.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day27.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 495);
        day27.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day27.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day27.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day27.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day27);

        ExerciseModel day28 = new ExerciseModel();
        day28.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day28.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day28.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 600);
        day28.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day28.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 600);
        day28.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day28.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day28);

        ExerciseModel day29 = new ExerciseModel();
        day29.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day29.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day29.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 525);
        day29.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 30);
        day29.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day29.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day29.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 525);
        day29.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 30);
        day29.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day29.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day29.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 525);
        day29.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 30);
        day29.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day29.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 60);
        day29.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day29);

        ExerciseModel day30 = new ExerciseModel();
        day30.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day30.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day30.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 585);
        day30.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 30);
        day30.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day30.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day30.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 585);
        day30.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 30);
        day30.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day30.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day30.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 585);
        day30.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 30);
        day30.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 10);
        day30.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 60);
        day30.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day30);

    }

    public void print(){
        int i = 0;
        for(ExerciseModel exerciseModel :exerciseModels){
            Logs.show("Day " + (i + 1) + " Duration: "  +
                    CalculationHelper.prettifySeconds(exerciseModel.getTotalDurationSecs()));
            i++;

            printExerciseModel(exerciseModel);
        }
    }

    private void printExerciseModel(ExerciseModel exerciseModel){
        Map<String, Float> dictionary = new HashMap<String, Float>();
        ArrayList<String> stateTransitions = new ArrayList();
        for(ExercisePartModel exercisePartModel : exerciseModel.getExercisePartModels()){
            String exercisePartText = exercisePartModel.getExerciseText(context);
            stateTransitions.add(exercisePartText + " " +
                    CalculationHelper.prettifySeconds(exercisePartModel.getDurationSecs()));

            if(dictionary.containsKey(exercisePartText)){
                Float currentDuration = dictionary.get(exercisePartText);
                currentDuration += exercisePartModel.getDurationSecs();
                dictionary.put(exercisePartText, currentDuration);
            }
            else{
                dictionary.put(exercisePartText, exercisePartModel.getDurationSecs());
            }

        }

        ArrayList<String> dictResults = new ArrayList();
        for (Map.Entry<String, Float> entry : dictionary.entrySet()) {
            String key = entry.getKey();
            Float value = entry.getValue();
            dictResults.add(key + ":" + CalculationHelper.prettifySeconds(value));
        }

     /*   Logs.show("");
        Logs.show("Printing exercise state transitions");
        Logs.show(Strings.joinArr(stateTransitions, " / "));*/
        Logs.show("");
        Logs.show("Printing each state duration");
        Logs.show(Strings.joinArr(dictResults, " / "));
        Logs.show("--------------------------------------------------------------------------------------");
    }

}
