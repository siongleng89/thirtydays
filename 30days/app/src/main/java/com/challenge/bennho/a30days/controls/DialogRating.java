package com.challenge.bennho.a30days.controls;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.enums.PreferenceType;
import com.challenge.bennho.a30days.helpers.OverlayBuilder;
import com.challenge.bennho.a30days.helpers.PreferenceUtils;
import com.challenge.bennho.a30days.helpers.ShareRateHelper;
import com.challenge.bennho.a30days.models.User;
import com.google.android.gms.plus.PlusOneButton;

/**
 * Created by Dell-PC on 26/1/2017.
 */

public class DialogRating {

    private Activity activity;
    private View viwDialogRating;
    private AlertDialog dialog;
    private PlusOneButton btnPlusOne;
    private static final int PLUS_ONE_REQUEST_CODE = 9628;
    private LinearLayout layoutQuit, layoutRatePlayStore;

    public DialogRating(Activity activity) {
        this.activity = activity;

        viwDialogRating = activity.getLayoutInflater().inflate(R.layout.dialog_rating, null);
        btnPlusOne = (PlusOneButton) viwDialogRating.findViewById(R.id.btnPlusOne);
        layoutQuit = (LinearLayout) viwDialogRating.findViewById(R.id.layoutQuit);
        layoutRatePlayStore = (LinearLayout) viwDialogRating.findViewById(R.id.layoutRatePlayStore);

        setListeners();
    }


    public void showIfNeeded(boolean forceShow){
        boolean seenRating = PreferenceUtils.getBoolean(activity, PreferenceType.SeenRateApps);

        Runnable showDialogRunnable = new Runnable() {
            @Override
            public void run() {
                btnPlusOne.initialize("https://play.google.com/store/apps/details?id=com.challenge.bennho.a30days",
                        PLUS_ONE_REQUEST_CODE);

                dialog = OverlayBuilder.build(activity)
                        .setOverlayType(OverlayBuilder.OverlayType.CustomView)
                        .setContentView(viwDialogRating)
                        .setOnDismissRunnable(new Runnable() {
                            @Override
                            public void run() {
                                PreferenceUtils.putBoolean(activity, PreferenceType.SeenRateApps, true);
                            }
                        })
                        .show();
            }
        };

        if(forceShow){
            showDialogRunnable.run();
        }
        else if(!seenRating){
            User user = new User(activity);
            user.reload();
            if(user.getCurrentDay() >= 4){
                showDialogRunnable.run();
            }
        }
    }


    private void setListeners(){
        layoutQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        layoutRatePlayStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                ShareRateHelper.goToPlayStore(activity);
            }
        });
    }


}

