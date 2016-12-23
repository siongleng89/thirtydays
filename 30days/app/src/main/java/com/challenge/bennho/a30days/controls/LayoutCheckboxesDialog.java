package com.challenge.bennho.a30days.controls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.challenge.bennho.a30days.R;

import java.util.ArrayList;

/**
 * Created by sionglengho on 22/12/16.
 */

public class LayoutCheckboxesDialog extends LinearLayout {

    private Context context;

    public LayoutCheckboxesDialog(Context context) {
        super(context);
        init(context);
    }

    public LayoutCheckboxesDialog(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LayoutCheckboxesDialog(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        this.context = context;
        this.setOrientation(LinearLayout.VERTICAL);
    }

    public void setEntries(ArrayList<String> entries){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        for(String entry : entries){
            final View view = inflater.inflate(R.layout.layout_checkbox, null);

            ((TextView) view.findViewById(R.id.txtView)).setText(entry);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((CheckBox) view.findViewById(R.id.checkBox)).toggle();
                }
            });

            this.addView(view);

        }

    }




}
