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
 * Created by sionglengho on 25/1/17.
 */

public class DialogChangeDifficulty {

    private Activity activity;
    private View viwDialogChangeDifficulty;
    private LinearLayout layoutYes, layoutNo;
    private AlertDialog dialog;
    private CheckBox chkDontAskAgain;
    private User user;

    public DialogChangeDifficulty(Activity activity) {
        this.activity = activity;

        viwDialogChangeDifficulty = activity.getLayoutInflater().inflate(R.layout.dialog_change_difficulty, null);
        layoutNo = (LinearLayout) viwDialogChangeDifficulty.findViewById(R.id.layoutNo);
        layoutYes = (LinearLayout) viwDialogChangeDifficulty.findViewById(R.id.layoutYes);
        chkDontAskAgain = (CheckBox) viwDialogChangeDifficulty.findViewById(R.id.chkDontAskAgain);

        user = new User(activity);
        user.reload();

        setListeners();
    }

    /**
     * only show the dialog if all of the criteria is met (not last day plan, not on max level)
     * else direct return
     */
    public void showIfNeeded(final Runnable onDismiss){
        boolean dontAsk = PreferenceUtils.getBoolean(activity, PreferenceType.DontAskRunDifficultyAgain);

        if(user.getCurrentDay() < 30 && user.getRunDifficultLevel() < 5 && !dontAsk){
            dialog = OverlayBuilder.build(activity)
                    .setOverlayType(OverlayBuilder.OverlayType.CustomView)
                    .setContentView(viwDialogChangeDifficulty)
                    .setOnDismissRunnable(new Runnable() {
                        @Override
                        public void run() {
                            if(onDismiss != null) onDismiss.run();
                            if(chkDontAskAgain.isChecked()){
                                PreferenceUtils.putBoolean(activity, PreferenceType.DontAskRunDifficultyAgain, true);
                            }
                        }
                    })
                    .show();
        }
        else{
            if(onDismiss != null) onDismiss.run();
        }
    }

    private void agreeIncreaseDifficulty(){
        int currentLevel = user.getRunDifficultLevel();
        if(currentLevel < 5){
            currentLevel += 1;
        }
        PreferenceUtils.putString(activity, PreferenceType.RunDifficulty, String.valueOf(currentLevel));
        dialog.dismiss();
    }

    private void rejectIncreaseDifficulty(){
        dialog.dismiss();
    }

    private void setListeners(){
        layoutYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agreeIncreaseDifficulty();
            }
        });


        layoutNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rejectIncreaseDifficulty();
            }
        });
    }


}
