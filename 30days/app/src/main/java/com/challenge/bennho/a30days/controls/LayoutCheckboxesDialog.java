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
import java.util.Collection;
import java.util.Collections;

/**
 * Created by sionglengho on 22/12/16.
 */

public class LayoutCheckboxesDialog extends LinearLayout {

    private Context context;
    private ArrayList<String> selectedValues;

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
        selectedValues = new ArrayList();
    }

    public void initEntriesAndValues(ArrayList<String> entries, final ArrayList<String> values,
                                     final ArrayList<String> inputSelectedValues){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.selectedValues.clear();
        this.selectedValues.addAll(inputSelectedValues);

        for(int i = 0; i < entries.size(); i++){
            final View view = inflater.inflate(R.layout.layout_checkbox, null);

            ((TextView) view.findViewById(R.id.txtView)).setText(entries.get(i));
            final CheckBox checkBox = ((CheckBox) view.findViewById(R.id.checkBox));

            //value is selected default
            if(inputSelectedValues.contains(values.get(i))){
                checkBox.setChecked(true);
            }

            final int finalI = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkBox.isChecked()){
                        checkBox.setChecked(false);
                        selectedValues.remove(values.get(finalI));
                    }
                    else{
                        checkBox.setChecked(true);
                        selectedValues.add(values.get(finalI));
                    }
                }
            });

            this.addView(view);
        }
    }

    public ArrayList<String> getSelectedValues() {
        Collections.sort(selectedValues);
        return selectedValues;
    }
}
