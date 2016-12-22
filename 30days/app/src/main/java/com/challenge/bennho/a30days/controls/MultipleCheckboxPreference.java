package com.challenge.bennho.a30days.controls;

import android.content.Context;
import android.preference.ListPreference;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.helpers.OverlayBuilder;

import java.util.ArrayList;

/**
 * Created by sionglengho on 22/12/16.
 */

public class MultipleCheckboxPreference extends ListPreference {

    public MultipleCheckboxPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MultipleCheckboxPreference(Context context) {
        super(context);
    }

    @Override
    protected void onClick() {
        ArrayList<String> entries = new ArrayList();
        for(CharSequence sequence : this.getEntries()){
            entries.add(sequence.toString());
        }

        LayoutCheckboxesDialog layoutCheckboxesDialog = new LayoutCheckboxesDialog(getContext());
        layoutCheckboxesDialog.setEntries(entries);
        OverlayBuilder.build(getContext()).setOverlayType(OverlayBuilder.OverlayType.OkCancel)
                .setTitle(getTitle().toString())
                .setContentView(layoutCheckboxesDialog)
                .show();
    }

    @Override
    protected void onBindView(final View view) {
        super.onBindView(view);
    }


}
