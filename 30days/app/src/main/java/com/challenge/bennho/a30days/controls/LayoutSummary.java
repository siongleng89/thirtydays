package com.challenge.bennho.a30days.controls;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.drawables.SemiCircleDrawable;
import com.challenge.bennho.a30days.models.User;

/**
 * Created by sionglengho on 22/12/16.
 */

public class LayoutSummary extends RelativeLayout {

    private Context context;
    private ProgressBar progressDay;
    private TextView txtTotalDuration, txtTotalCalories;
    private ImageView imgViewFat, imgViewFit;

    public LayoutSummary(Context context) {
        super(context);
        init(context, null);
    }

    public LayoutSummary(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LayoutSummary(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void extractAttrs(AttributeSet attrs){

    }


    public void init(Context context, AttributeSet attrs){
        extractAttrs(attrs);
        this.context = context;

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_summary, this, true);

        progressDay = (ProgressBar) findViewById(R.id.progressDay);
        txtTotalCalories = (TextView) findViewById(R.id.txtTotalCalories);
        txtTotalDuration = (TextView) findViewById(R.id.txtTotalDuration);
        imgViewFit = (ImageView) findViewById(R.id.imgViewFit);
        imgViewFat = (ImageView) findViewById(R.id.imgViewFat);


        User user = new User(context);
        user.reload();

        update(user);
    }

    public void update(User user){

        //female
        if(user.getGenderIndex() == 1){
            imgViewFit.setImageResource(R.drawable.fit_woman);
            imgViewFat.setImageResource(R.drawable.fat_woman);
        }

        progressDay.setProgress(user.getCurrentDay() - 1);
        txtTotalDuration.setText(String.valueOf((int) Math.ceil((double) user.getTotalRunningSecs() / 60d)));
        txtTotalCalories.setText(String.valueOf(user.getTotalCaloriesBurnt()));
    }

}
