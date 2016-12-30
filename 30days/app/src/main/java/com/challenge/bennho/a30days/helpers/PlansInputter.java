package com.challenge.bennho.a30days.helpers;

import com.challenge.bennho.a30days.models.ExerciseModel;
import com.challenge.bennho.a30days.models.ExercisePartModel;

import java.util.ArrayList;

/**
 * Created by sionglengho on 30/12/16.
 */

public class PlansInputter {

    ArrayList<ExerciseModel> exerciseModels;

    public PlansInputter() {
        exerciseModels = new ArrayList();
        input();
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
        day2.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day2);

        ExerciseModel day3 = new ExerciseModel();
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 150);
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 45);
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 150);
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 150);
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 45);
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day3.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 150);
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
        day5.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day5);

        ExerciseModel day6 = new ExerciseModel();
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 45);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 45);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 45);
        day6.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
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
        day7.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
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
        day8.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day8);

        ExerciseModel day9 = new ExerciseModel();
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 90);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 75);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 90);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 75);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day9.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 90);
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
        day10.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day10);

        ExerciseModel day11 = new ExerciseModel();
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 120);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 105);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 120);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 105);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day11.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 120);
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
        day12.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day12);

        ExerciseModel day13 = new ExerciseModel();
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 150);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 135);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 150);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 90);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 135);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 90);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 150);
        day13.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day13);

        ExerciseModel day14 = new ExerciseModel();
        day14.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day14.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day14.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 330);
        day14.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day14.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 330);
        day14.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day14.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day14);

        ExerciseModel day15 = new ExerciseModel();
        day15.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day15.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day15.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 180);
        day15.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day15.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day15.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 180);
        day15.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day15.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day15.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 180);
        day15.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day15.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day15.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day15);

        ExerciseModel day16 = new ExerciseModel();
        day16.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day16.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day16.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 210);
        day16.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day16.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day16.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 210);
        day16.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day16.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day16.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 210);
        day16.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day16.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day16.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day16);

        ExerciseModel day17 = new ExerciseModel();
        day17.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day17.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day17.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 240);
        day17.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day17.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day17.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 240);
        day17.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day17.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day17.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 240);
        day17.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day17.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day17.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day17);

        ExerciseModel day18 = new ExerciseModel();
        day18.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day18.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day18.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 270);
        day18.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day18.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day18.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 270);
        day18.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day18.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day18.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 270);
        day18.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day18.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day18.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day18);

        ExerciseModel day19 = new ExerciseModel();
        day19.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day19.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day19.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 300);
        day19.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day19.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day19.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 300);
        day19.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day19.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day19.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 300);
        day19.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day19.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day19.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day19);

        ExerciseModel day20 = new ExerciseModel();
        day20.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day20.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day20.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 330);
        day20.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day20.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day20.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 330);
        day20.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day20.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day20.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 330);
        day20.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 15);
        day20.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day20.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day20);

        ExerciseModel day21 = new ExerciseModel();
        day21.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day21.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day21.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 600);
        day21.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day21.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 600);
        day21.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day21.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day21);

        ExerciseModel day22 = new ExerciseModel();
        day22.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day22.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day22.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 360);
        day22.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day22.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day22.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 360);
        day22.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day22.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day22.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 360);
        day22.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day22.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day22.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day22);

        ExerciseModel day23 = new ExerciseModel();
        day23.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day23.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day23.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 390);
        day23.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day23.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day23.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 390);
        day23.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day23.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day23.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 390);
        day23.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day23.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day23.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day23);

        ExerciseModel day24 = new ExerciseModel();
        day24.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day24.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day24.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 420);
        day24.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day24.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day24.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 420);
        day24.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day24.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day24.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 420);
        day24.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day24.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day24.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day24);

        ExerciseModel day25 = new ExerciseModel();
        day25.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day25.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day25.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 450);
        day25.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day25.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day25.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 450);
        day25.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day25.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day25.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 450);
        day25.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day25.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day25.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day25);

        ExerciseModel day26 = new ExerciseModel();
        day26.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day26.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day26.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 480);
        day26.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day26.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day26.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 480);
        day26.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day26.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day26.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 480);
        day26.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day26.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day26.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day26);

        ExerciseModel day27 = new ExerciseModel();
        day27.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day27.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day27.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 510);
        day27.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day27.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day27.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 510);
        day27.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day27.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day27.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 510);
        day27.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 20);
        day27.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day27.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day27);

        ExerciseModel day28 = new ExerciseModel();
        day28.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day28.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day28.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 900);
        day28.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day28.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 900);
        day28.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 120);
        day28.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day28);

        ExerciseModel day29 = new ExerciseModel();
        day29.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day29.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day29.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 540);
        day29.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 30);
        day29.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day29.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 540);
        day29.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 30);
        day29.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day29.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 540);
        day29.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 30);
        day29.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day29.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day29);

        ExerciseModel day30 = new ExerciseModel();
        day30.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 300);
        day30.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 300);
        day30.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 600);
        day30.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 30);
        day30.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day30.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 600);
        day30.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 30);
        day30.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day30.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 600);
        day30.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 30);
        day30.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        day30.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 300);
        exerciseModels.add(day30);

    }

    public void print(){
        int i = 0;
        for(ExerciseModel exerciseModel :exerciseModels){
            Logs.show("Day " + (i + 1) + " Duration: "  +
                    CalculationHelper.prettifySeconds(exerciseModel.getTotalDurationSecs()));
            i++;
        }
    }

}