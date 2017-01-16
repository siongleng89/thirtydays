package com.challenge.bennho.a30days.controls;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.drawables.SemiCircleDrawable;
import com.challenge.bennho.a30days.helpers.AndroidUtils;
import com.challenge.bennho.a30days.helpers.AnimateBuilder;

/**
 * Created by sionglengho on 22/12/16.
 */

public class LayoutDayCounter extends RelativeLayout {

    private Context context;
    private RelativeLayout layoutOuterCircle, layoutCircle, layoutDummyCircle, layoutBlinkingCircle,
                            layoutInnerCircle;
    private LinearLayout layoutContentCircle;
    private TextView txtDay, txtCompleted, txtQuote;
    private ImageView imgViewMedal;
    private int maxDayNumber;

    public LayoutDayCounter(Context context) {
        super(context);
        init(context, null);
    }

    public LayoutDayCounter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LayoutDayCounter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void extractAttrs(AttributeSet attrs){

    }

    public void init(Context context, AttributeSet attrs){
        extractAttrs(attrs);
        this.context = context;

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_day_counter, this, true);

        layoutOuterCircle = (RelativeLayout) findViewById(R.id.layoutOuterCircle);
        layoutCircle = (RelativeLayout) findViewById(R.id.layoutCircle);
        layoutInnerCircle = (RelativeLayout) findViewById(R.id.layoutInnerCircle);
        layoutContentCircle = (LinearLayout) findViewById(R.id.layoutContentCircle);
        layoutDummyCircle = (RelativeLayout) findViewById(R.id.layoutDummyCircle);
        layoutBlinkingCircle = (RelativeLayout) findViewById(R.id.layoutBlinkingCircle);

        txtDay = (TextView) findViewById(R.id.txtDay);
        txtCompleted = (TextView) findViewById(R.id.txtCompleted);
        txtQuote = (TextView) findViewById(R.id.txtQuote);
        imgViewMedal = (ImageView) findViewById(R.id.imgViewMedal);

        ViewCompat.setBackground(layoutOuterCircle, new SemiCircleDrawable(
                ContextCompat.getColor(context, R.color.colorAccent), 360
        ));

        ViewCompat.setBackground(layoutInnerCircle, new SemiCircleDrawable(
                ContextCompat.getColor(context, R.color.colorAccent), 360
        ));
        ViewCompat.setBackground(layoutContentCircle, new SemiCircleDrawable(
                ContextCompat.getColor(context, R.color.colorBtnWord), 360
        ));

        ViewCompat.setBackground(layoutDummyCircle, new SemiCircleDrawable(
                ContextCompat.getColor(context, R.color.colorBtnWord), 360
        ));

        AnimateBuilder.build(context, layoutBlinkingCircle)
                .setAnimateType(AnimateBuilder.AnimateType.alpha)
                .setRepeat(true)
                .setValueDp(1)
                .setDurationMs(1000)
                .start();

    }

    public void setMaxDayNumber(int day){
        this.maxDayNumber = day;

        if(maxDayNumber > 0){
            ViewCompat.setBackground(layoutCircle, new SemiCircleDrawable(
                    ContextCompat.getColor(context, R.color.colorAccent), 12 * (maxDayNumber - 1)
            ));
        }
    }

    public void updateDayNumber(int day){

        int currentDayNumber = day;
        if(currentDayNumber > 30){
            currentDayNumber = 30;
        }


        if(currentDayNumber < maxDayNumber){
            imgViewMedal.setVisibility(VISIBLE);
            txtCompleted.setText(context.getString(R.string.completed));
        }
        else{
            imgViewMedal.setVisibility(GONE);
            txtCompleted.setText(context.getString(R.string.incomplete));
        }
        txtDay.setText(String.format(context.getString(R.string.day_X), String.valueOf(currentDayNumber)));

        ViewCompat.setBackground(layoutBlinkingCircle, new SemiCircleDrawable(
                ContextCompat.getColor(context, R.color.colorSprint),
                12, 12 * (currentDayNumber - 1)
        ));

        if(currentDayNumber > maxDayNumber){
            txtQuote.setText(R.string.locked);
        }
        else{
            txtQuote.setText(String.format("\"%s\"", AndroidUtils.getStringByIdentifier(context, "exercise_quote" + currentDayNumber)));
        }

    }





}
