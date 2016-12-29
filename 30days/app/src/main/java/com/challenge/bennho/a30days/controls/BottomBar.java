package com.challenge.bennho.a30days.controls;

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
    private boolean isMainActivity;


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

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_bottom_button, this, true);

        layoutLinearChallenge = (LinearLayout) findViewById(R.id.linearChallenge);
        layoutLinearMore = (LinearLayout) findViewById(R.id.linearMore);
        layoutLinearHistory = (LinearLayout) findViewById(R.id.linearHistory);

        setMyOnClickListener(layoutLinearChallenge,0);
        setMyOnClickListener(layoutLinearHistory,1);
        setMyOnClickListener(layoutLinearMore,2);
    }



    private void setMyOnClickListener(LinearLayout lay, final int goToPage){
        lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;

                if(goToPage==0){
                    if(isMainActivity==true){
                        return;
                    }

                    intent = new Intent(BottomBar.this.context, MainActivity.class);
                }
                else if(goToPage==1){
                    intent = new Intent(BottomBar.this.context, HistoryActivity.class);
                }
                else if(goToPage==2){
                    intent = new Intent(BottomBar.this.context, SettingsActivity.class);
                }
                BottomBar.this.context.startActivity(intent);
            }
        });

    }

    public void setIsMainActivity(boolean isTrue){
        isMainActivity=isTrue;

    }
}

