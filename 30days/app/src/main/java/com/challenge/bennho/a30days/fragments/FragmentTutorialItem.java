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
            txtTitle.setText(R.string.avty_tutorial_introduction_title);
            txtContent.setText(R.string.avty_tutorial_introduction_content);
        }
        else if(index == 1){
            layoutItem.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorWarmUp));
            txtTitle.setText(R.string.warm_up_state);
            txtContent.setText(R.string.avty_tutorial_warm_up_explain);
        }
        else if(index == 2){
            layoutItem.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorWalk));
            txtTitle.setText(R.string.walk_state);
            txtContent.setText(R.string.avty_tutorial_walk_explain);
        }
        else if(index == 3){
            layoutItem.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorFastWalk));
            txtTitle.setText(R.string.fast_walk_state);
            txtContent.setText(R.string.avty_tutorial_fast_walk_explain);
        }
        else if(index == 4){
            layoutItem.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorRun));
            txtTitle.setText(R.string.run_state);
            txtContent.setText(R.string.avty_tutorial_run_explain);
        }
        else if(index == 5){
            layoutItem.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSprint));
            txtTitle.setText(R.string.sprint_state);
            txtContent.setText(R.string.avty_tutorial_sprint_explain);
        }
        else if(index == 6){
            layoutItem.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorCoolDown));
            txtTitle.setText(R.string.cool_down_state);
            txtContent.setText(R.string.avty_tutorial_cool_down_explain);
        }


        return rootView;
    }




}
