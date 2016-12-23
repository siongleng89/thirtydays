package com.challenge.bennho.a30days.controls;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.ListPreference;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.helpers.OverlayBuilder;
import com.challenge.bennho.a30days.helpers.Strings;

import java.util.ArrayList;

/**
 * Created by sionglengho on 22/12/16.
 */

public class MultipleCheckboxPreference extends ListPreference {

    private String emptySelectedText, allSelectedText;


    public MultipleCheckboxPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        extractAttrs(attrs);
    }

    public MultipleCheckboxPreference(Context context) {
        super(context);
    }

    private void extractAttrs(AttributeSet attrs){
        TypedArray a = getContext().obtainStyledAttributes(
                attrs,
                R.styleable.MultipleCheckboxPreference);

        emptySelectedText = a.getString(R.styleable.MultipleCheckboxPreference_emptySelectedText);
        allSelectedText = a.getString(R.styleable.MultipleCheckboxPreference_allSelectedText);
        a.recycle();
    }


    @Override
    protected void onClick() {
        ArrayList<String> entries = new ArrayList();
        for(CharSequence sequence : this.getEntries()){
            entries.add(sequence.toString());
        }

        ArrayList<String> values = new ArrayList();
        for(CharSequence sequence : this.getEntryValues()){
            values.add(sequence.toString());
        }

        ArrayList<String> selectedValues = new ArrayList();
        if(this.getValue() != null){
            for(String value : this.getValue().split(",")){
                selectedValues.add(value);
            }
        }


        final LayoutCheckboxesDialog layoutCheckboxesDialog = new LayoutCheckboxesDialog(getContext());
        layoutCheckboxesDialog.initEntriesAndValues(entries, values, selectedValues);
        OverlayBuilder.build(getContext()).setOverlayType(OverlayBuilder.OverlayType.OkCancel)
                .setTitle(getTitle().toString())
                .setContentView(layoutCheckboxesDialog)
                .setRunnables(new Runnable() {
                    @Override
                    public void run() {
                        String newValue = Strings.joinArr(layoutCheckboxesDialog.getSelectedValues(), ",");
                        persistString(newValue);
                        MultipleCheckboxPreference.this.setValue(newValue);
                        MultipleCheckboxPreference.this.notifyChanged();
                    }
                })
                .show();

    }

    @Override
    public CharSequence getSummary() {
        if(Strings.isEmpty(this.getValue())){
            return emptySelectedText;
        }
        else{

            ArrayList<String> result = new ArrayList();
            for(String value : this.getValue().split(",")){
                int index = this.findIndexOfValue(value);
                if(index >= 0){
                    result.add((this.getEntries()[index]).toString());
                }
            }

            if(result.size() == this.getEntries().length){
                return allSelectedText;
            }
            else{
                return Strings.joinArr(result, ", ");
            }


        }
    }




    @Override
    protected void onBindView(final View view) {
        super.onBindView(view);
    }


}
