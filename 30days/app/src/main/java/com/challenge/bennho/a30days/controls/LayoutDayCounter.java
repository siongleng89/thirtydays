package com.challenge.bennho.a30days.controls;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.drawables.SemiCircleDrawable;

/**
 * Created by sionglengho on 22/12/16.
 */

public class LayoutDayCounter extends RelativeLayout {

    private Context context;
    private RelativeLayout layoutOuterCircle, layoutCircle, layoutInnerCircle;
    private LinearLayout layoutContentCircle;
    private TextView txtDay, txtCompleted;

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

        txtDay = (TextView) findViewById(R.id.txtDay);
        txtCompleted = (TextView) findViewById(R.id.txtCompleted);

        ViewCompat.setBackground(layoutOuterCircle, new SemiCircleDrawable(
                ContextCompat.getColor(context, R.color.colorPrimaryDark), 360
        ));

        ViewCompat.setBackground(layoutInnerCircle, new SemiCircleDrawable(
                ContextCompat.getColor(context, R.color.colorPrimaryDark), 360
        ));
        ViewCompat.setBackground(layoutContentCircle, new SemiCircleDrawable(
                ContextCompat.getColor(context, R.color.colorBtnWord), 360
        ));


    }


    public void updateDayNumber(int day){
        ViewCompat.setBackground(layoutCircle, new SemiCircleDrawable(
                ContextCompat.getColor(context, R.color.colorPrimary), 12 * day
        ));

        double completedPercent = (day / 30d) * 100d;

        txtDay.setText(String.format(context.getString(R.string.day_X), String.valueOf(day)));
        txtCompleted.setText(String.format(context.getString(R.string.X_completed),
                    String.format("%.2f", completedPercent)));

    }





}
