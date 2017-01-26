package com.challenge.bennho.a30days.controls;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.enums.PreferenceType;
import com.challenge.bennho.a30days.helpers.OverlayBuilder;
import com.challenge.bennho.a30days.helpers.PreferenceUtils;
import com.challenge.bennho.a30days.models.User;

/**
 * Created by Dell-PC on 26/1/2017.
 */

public class DialogRating {

    private Activity activity;
    private View viwDialogRating;
    private AlertDialog dialog;
    private User user;

    public DialogRating(Activity activity) {
        this.activity = activity;

        viwDialogRating = activity.getLayoutInflater().inflate(R.layout.dialog_rating, null);

        user = new User(activity);
        user.reload();

        setListeners();
    }


    public void showIfNeeded(){
        dialog = OverlayBuilder.build(activity)
                .setOverlayType(OverlayBuilder.OverlayType.CustomView)
                .setContentView(viwDialogRating)
                .setOnDismissRunnable(new Runnable() {
                    @Override
                    public void run() {

                    }
                })
                .show();
    }


    private void setListeners(){

    }


}

