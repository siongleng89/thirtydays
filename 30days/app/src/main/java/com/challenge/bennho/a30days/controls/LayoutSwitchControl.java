package com.challenge.bennho.a30days.controls;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.helpers.AndroidUtils;
import com.challenge.bennho.a30days.helpers.AnimateBuilder;

/**
 * Created by sionglengho on 21/12/16.
 */

public class LayoutSwitchControl extends RelativeLayout {

    private RelativeLayout layoutOption1, layoutOption2, layoutBackground;
    private TextView txtOption1, txtOption2;
    private int colorSelected, colorUnSelected;
    private int movingWidth;
    private Context context;
    private int pad;
    private String optionText1, optionText2;
    private int selectedOption;

    public LayoutSwitchControl(Context context) {
        super(context);
        init(context);
    }

    public LayoutSwitchControl(Context context, AttributeSet attrs) {
        super(context, attrs);
        extractAttrs(attrs);
        init(context);
    }

    public LayoutSwitchControl(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        extractAttrs(attrs);
        init(context);
    }

    private void extractAttrs(AttributeSet attrs){
        TypedArray a=getContext().obtainStyledAttributes(
                attrs,
                R.styleable.LayoutSwitchControl);

        optionText1 = a.getString(R.styleable.LayoutSwitchControl_option1);
        optionText2 = a.getString(R.styleable.LayoutSwitchControl_option2);

        //Don't forget this
        a.recycle();
    }


    private void init(Context context){
        this.context = context;

        //define padding for tabs inner button
        pad = AndroidUtils.dpToPx(context, 4);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_switch_control, this, true);

        //text color for various state
        colorSelected = ContextCompat.getColor(context, R.color.colorPrimary);
        colorUnSelected = ContextCompat.getColor(context, R.color.colorBtnWord);

        layoutOption1 = (RelativeLayout) findViewById(R.id.layoutOption1);
        layoutOption2 = (RelativeLayout) findViewById(R.id.layoutOption2);
        layoutBackground = (RelativeLayout) findViewById(R.id.layoutBackground);

        txtOption1 = (TextView) findViewById(R.id.txtOption1);
        txtOption2 = (TextView) findViewById(R.id.txtOption2);

        //setting the text of textviews
        txtOption1.setText(optionText1);
        txtOption2.setText(optionText2);

        //set default select first option
        changeSelected(0, false);

        setListeners();
    }


    private void changeSelected(int choice, boolean animate){
        selectedOption = choice;

        if(choice == 0){
            txtOption1.setTextColor(colorSelected);
            txtOption2.setTextColor(colorUnSelected);
        }
        else{
            txtOption1.setTextColor(colorUnSelected);
            txtOption2.setTextColor(colorSelected);
        }

        moveLayoutBackground(choice, animate);
    }

    private void moveLayoutBackground(int choice, boolean animate){

        int moveToX = movingWidth;
        if(choice == 0){
            moveToX = pad;
        }

        if(animate){
            AnimateBuilder.build(context, layoutBackground)
                    .setAnimateType(AnimateBuilder.AnimateType.moveToX)
                    .setDurationMs(200)
                    .setValueDp(AndroidUtils.pxToDp(context, moveToX))
                    .start();
        }
        else{
            layoutBackground.setX(moveToX);
        }

    }


    private void setListeners(){

        LayoutSwitchControl.this.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        layoutBackground.setLayoutParams(new LayoutParams(LayoutSwitchControl.this.getWidth() / 2 - pad * 2,
                                LayoutSwitchControl.this.getHeight() - pad * 2));
                        layoutBackground.setY(pad);
                        movingWidth = LayoutSwitchControl.this.getWidth() / 2 + pad;

                        if (Build.VERSION.SDK_INT < 16) {
                            layoutBackground.getViewTreeObserver()
                                    .removeGlobalOnLayoutListener(this);
                        } else {
                            layoutBackground.getViewTreeObserver()
                                    .removeOnGlobalLayoutListener(this);
                        }

                    }
                });

        layoutOption1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSelected(0, true);
            }
        });


        layoutOption2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSelected(1, true);
            }
        });
    }

    public String getSelectedOption(){
        if (selectedOption == 0){
            return optionText1;
        }
        else{
            return optionText2;
        }
    }


}
