package com.challenge.bennho.a30days.controls;

import android.content.Context;
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

public class LayoutSummary extends RelativeLayout {

    private Context context;

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


    }

}
