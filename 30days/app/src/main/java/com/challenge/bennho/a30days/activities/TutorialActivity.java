package com.challenge.bennho.a30days.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.controls.LayoutExerciseStates;
import com.challenge.bennho.a30days.fragments.FragmentTutorialItem;
import com.challenge.bennho.a30days.helpers.Logs;
import com.challenge.bennho.a30days.helpers.Threadings;
import com.challenge.bennho.a30days.models.ExerciseModel;
import com.challenge.bennho.a30days.models.ExercisePartModel;

import me.relex.circleindicator.CircleIndicator;

public class TutorialActivity extends MyActivity{

    private TutorialAdapter tutorialAdapter;
    private LayoutExerciseStates layoutExerciseStates;
    private ViewPager pagerTutorial;
    private CircleIndicator indicator;
    private LinearLayout layoutPrev, layoutNext, layoutEnd;
    private final int TOTAL_PAGE = 7;
    private ExerciseModel demoExerciseModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        onLayoutSet();

        setTitle("Exercise Tutorial");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        layoutPrev = (LinearLayout) findViewById(R.id.layoutPrev);
        layoutNext = (LinearLayout) findViewById(R.id.layoutNext);
        layoutEnd = (LinearLayout) findViewById(R.id.layoutEnd);

        layoutExerciseStates = (LayoutExerciseStates) findViewById(R.id.layoutExerciseStates);
        layoutExerciseStates.enableDemoMode();

        tutorialAdapter = new TutorialAdapter(getSupportFragmentManager());

        pagerTutorial = (ViewPager) findViewById(R.id.pagerTutorial);
        pagerTutorial.setAdapter(tutorialAdapter);

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(pagerTutorial);

        populate();

        setListeners();
    }


    private void populate(){

        demoExerciseModel = new ExerciseModel();
        demoExerciseModel.addExercisePartModel(ExercisePartModel.ExerciseState.WarmUp, 60);
        demoExerciseModel.addExercisePartModel(ExercisePartModel.ExerciseState.Walk, 60);
        demoExerciseModel.addExercisePartModel(ExercisePartModel.ExerciseState.FastWalk, 60);
        demoExerciseModel.addExercisePartModel(ExercisePartModel.ExerciseState.Run, 60);
        demoExerciseModel.addExercisePartModel(ExercisePartModel.ExerciseState.Sprint, 60);
        demoExerciseModel.addExercisePartModel(ExercisePartModel.ExerciseState.CoolDown, 60);

        layoutExerciseStates.start(demoExerciseModel, null);

    }

    private void endTutorial(){
        finish();
    }

    private void onPageChanged(int currentPage){

        if(currentPage == 0){
            layoutExerciseStates.removeFocusExercisePart();
            layoutExerciseStates.onExercisePartChanged(null);
        }
        else{
            layoutExerciseStates.focusExercisePart(currentPage - 1);
            layoutExerciseStates.onExercisePartChanged(
                    demoExerciseModel.getExercisePartModels().get(currentPage - 1));
        }


        if(currentPage == TOTAL_PAGE - 1){
            layoutPrev.setVisibility(View.VISIBLE);
            layoutNext.setVisibility(View.GONE);
            layoutEnd.setVisibility(View.VISIBLE);
        }
        else if(currentPage == 0){
            layoutPrev.setVisibility(View.GONE);
            layoutNext.setVisibility(View.VISIBLE);
            layoutEnd.setVisibility(View.GONE);
        }
        else{
            layoutPrev.setVisibility(View.VISIBLE);
            layoutNext.setVisibility(View.VISIBLE);
            layoutEnd.setVisibility(View.GONE);
        }
    }

    private void setListeners(){
        layoutPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pagerTutorial.getCurrentItem() > 0){
                    pagerTutorial.setCurrentItem(pagerTutorial.getCurrentItem() - 1);
                }
            }
        });

        layoutNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pagerTutorial.getCurrentItem() < TOTAL_PAGE - 1){
                    pagerTutorial.setCurrentItem(pagerTutorial.getCurrentItem() + 1);
                }
            }
        });

        layoutEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endTutorial();
            }
        });

        pagerTutorial.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                onPageChanged(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public class TutorialAdapter extends FragmentPagerAdapter {


        public TutorialAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            FragmentTutorialItem fragment = new FragmentTutorialItem();
            Bundle args = new Bundle();

            args.putInt("index", i);
            args.putInt("total", TOTAL_PAGE);

            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return 7;
        }




    }




}


