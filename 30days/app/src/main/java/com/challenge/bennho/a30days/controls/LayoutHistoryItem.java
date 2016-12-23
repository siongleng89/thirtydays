package com.challenge.bennho.a30days.controls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.challenge.bennho.a30days.R;

/**
 * Created by Dell-PC on 23/12/2016.
 */

public class LayoutHistoryItem extends RelativeLayout {

    private Context context;

    public LayoutHistoryItem(Context context) {

        super(context);
        init(context);
    }

    public LayoutHistoryItem(Context context, AttributeSet attrs) {

        super(context, attrs);
        init(context);
    }

    public LayoutHistoryItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);


    }

    private void init(Context context) {
        this.context = context;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_history_item, this, true);

    }
}
