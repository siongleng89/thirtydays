package com.challenge.bennho.a30days.controls;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.challenge.bennho.a30days.activities.HistoryActivity;
import com.challenge.bennho.a30days.activities.MainActivity;
import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.activities.SettingsActivity;

/**
 * Created by Dell-PC on 22/12/2016.
 */

public class BottomBar extends RelativeLayout{

    private Context context;
    private LinearLayout layoutLinearChallenge;
    private LinearLayout layoutLinearHistory;
    private LinearLayout layoutLinearMore;
    private int currentSelectedPageIndex;


    public BottomBar(Context context) {
        super(context);
        init(context);
    }

    public BottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BottomBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init (Context context){
        this.context = context;

        currentSelectedPageIndex = -1;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_bottom_button, this, true);

        layoutLinearChallenge = (LinearLayout) findViewById(R.id.linearChallenge);
        layoutLinearMore = (LinearLayout) findViewById(R.id.linearMore);
        layoutLinearHistory = (LinearLayout) findViewById(R.id.linearHistory);

        setListeners();
    }

    public void setCurrentSelectedPageIndex(int index){
        currentSelectedPageIndex = index;
    }

    private void changeSelectedPage(int index){
        if(currentSelectedPageIndex == index){
            return;
        }
        else{
            Intent intent = null;
            if(index == 0){
                intent = new Intent(BottomBar.this.context, MainActivity.class);
            }
            else if(index == 1){
                intent = new Intent(BottomBar.this.context, HistoryActivity.class);
            }
            else if(index == 2){
                intent = new Intent(BottomBar.this.context, SettingsActivity.class);
            }

            BottomBar.this.context.startActivity(intent);
//            if(BottomBar.this.context instanceof Activity){
//                ((Activity) BottomBar.this.context).overridePendingTransition(0, 0);
//            }


        }
    }

    private void setListeners(){
        layoutLinearChallenge.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSelectedPage(0);
            }
        });

        layoutLinearHistory.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSelectedPage(1);
            }
        });

        layoutLinearMore.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSelectedPage(2);
            }
        });
    }

}

