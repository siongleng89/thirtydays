package com.challenge.bennho.a30days.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.helpers.TextSpeak;

/**
 * Created by sionglengho on 5/1/17.
 */
public class FragmentTutorialItem extends Fragment {

    private RelativeLayout layoutItem;
    private TextView txtContent, txtTitle;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.layout_tutorial_fragment, container, false);
        Bundle args = getArguments();

        layoutItem = (RelativeLayout) rootView.findViewById(R.id.layoutItem);
        txtContent = (TextView) rootView.findViewById(R.id.txtContent);
        txtTitle = (TextView) rootView.findViewById(R.id.txtTitle);

        int index = args.getInt("index", 0);
        int total = args.getInt("total", 0);

        if(index == 0){
            txtTitle.setText("Quick Tutorial");
            txtContent.setText("We will walk you through for the first time you exercise. We adapt HIIT exercise method to maximize calories burn in our exercise plan, which means each exercise involves several state. The exercise plan will vary depends on the information you inputMeal. Each exercise contains several states which represented by different color.");
        }
        else if(index == 1){
            layoutItem.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorWarmUp));
            txtTitle.setText("Warm up");
            txtContent.setText("Warm up phase is represented by LIGHT ORANGE color, do some stretching and slow run, this phase is important to prevent injury.");
        }
        else if(index == 2){
            layoutItem.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorWalk));
            txtTitle.setText("Walk");
            txtContent.setText("Walk phase is represented by LIGHT GREEN color, in this phase please walk with your normal walking speed.");
        }
        else if(index == 3){
            layoutItem.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorFastWalk));
            txtTitle.setText("Fast Walk");
            txtContent.setText("Fast Walk phase is represented by DARK GREEN color, you should try to walk with your maximum possible speed in this phase.");
        }
        else if(index == 4){
            layoutItem.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorRun));
            txtTitle.setText("Run");
            txtContent.setText("Run phase is represented by LIGHT RED color, run normally in this phase.");
        }
        else if(index == 5){
            layoutItem.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSprint));
            txtTitle.setText("Sprint");
            txtContent.setText("Sprint phase is represented by DARK RED color, run with your FULL SPEED in this phase.");
        }
        else if(index == 6){
            layoutItem.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorCoolDown));
            txtTitle.setText("Cool Down");
            txtContent.setText("Cool down phase is represented by BLUE color, gradually slow down your run in this final exercise phase.");
        }


        return rootView;
    }

//    private void setNextListener(final int currentIndex){
//        imgViewNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (viewPagerListener != null){
//                    viewPagerListener.goToIndex(currentIndex + 1);
//                }
//            }
//        });
//    }
//
//    private void setOnEndListener(){
//        imgViewMain.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (viewPagerListener != null){
//                    viewPagerListener.onEnded();
//                }
//            }
//        });
//
//        txtViewEnd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (viewPagerListener != null){
//                    viewPagerListener.onEnded();
//                }
//            }
//        });
//
//    }





}
