package com.challenge.bennho.a30days.controls;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.drawables.SemiCircleDrawable;
import com.challenge.bennho.a30days.helpers.AndroidUtils;
import com.challenge.bennho.a30days.helpers.CalculationHelper;
import com.challenge.bennho.a30days.helpers.Logs;
import com.challenge.bennho.a30days.helpers.Threadings;
import com.challenge.bennho.a30days.models.ExerciseModel;
import com.challenge.bennho.a30days.models.ExercisePartModel;
import com.challenge.bennho.a30days.services.ExerciseService;

import java.util.ArrayList;

/**
 * Created by sionglengho on 26/12/16.
 */

public class LayoutExerciseStates extends RelativeLayout implements ExerciseService.ExerciseListener {

    private RelativeLayout layoutCirclesContainer, layoutInnerCircle;
    private ExerciseModel exerciseModel;
    private ArrayList<RelativeLayout> circlesArr;
    private float anglePerSec;
    private Context context;
    private ImageView imgState;
    private TextView txtState, txtStateTime, txtSkip;
    private ExerciseActionListener exerciseActionListener;
    private int currentCircleIndex = -1;
    private boolean demoMode;
    private boolean lock;


    public LayoutExerciseStates(Context context) {
        super(context);
        init(context);
    }

    public LayoutExerciseStates(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LayoutExerciseStates(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        this.context = context;

        circlesArr = new ArrayList();

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_exercise_states, this, true);

        layoutCirclesContainer = (RelativeLayout) findViewById(R.id.layoutCirclesContainer);
        layoutInnerCircle = (RelativeLayout) findViewById(R.id.layoutInnerCircle);
        imgState = (ImageView) findViewById(R.id.imageState);
        txtState = (TextView) findViewById(R.id.txtState);
        txtStateTime = (TextView) findViewById(R.id.txtStateTime);
        txtSkip = (TextView) findViewById(R.id.txtSkip);

        ViewCompat.setBackground(layoutInnerCircle, new SemiCircleDrawable(
                ContextCompat.getColor(context, R.color.colorBackground), 360
        ));

        setListeners();
    }

    public void start(ExerciseModel exerciseModel, ExerciseActionListener exerciseActionListener){
        this.exerciseActionListener = exerciseActionListener;
        this.exerciseModel = exerciseModel;
        initAnglePerSec();
        initExerciseCircles();
    }

    private void initExerciseCircles(){
        float accumulateAngle = 0f;

        for(ExercisePartModel exercisePartModel : exerciseModel.getExercisePartModels()){

            float exerciseAngle = getExercisePartAngle(exercisePartModel);

            RelativeLayout circleLayout = new RelativeLayout(this.context);
            ViewCompat.setBackground(circleLayout, new SemiCircleDrawable(
                    exercisePartModel.getExerciseColor(context), exerciseAngle, accumulateAngle
            ));
            circleLayout.setTag(exerciseAngle);

            Logs.show(exerciseAngle + "," + accumulateAngle);

            circleLayout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT));
            layoutCirclesContainer.addView(circleLayout);

            circlesArr.add(circleLayout);

            accumulateAngle += exerciseAngle;

        }
    }


    @Override
    public void onTimeChanged(float totalElapsedMs, final float currentExerciseElapsedMs,
                              float caloriesBurnt, final ExercisePartModel currentExercisePartModel) {

        if(currentCircleIndex != currentExercisePartModel.getIndex()){
            onExercisePartChanged(currentExercisePartModel);
        }

        final RelativeLayout animatingCircle = circlesArr.get(currentExercisePartModel.getIndex());
        final float elapsedAngle = anglePerSec * (currentExerciseElapsedMs / 1000f);

        Threadings.postRunnable(new Runnable() {
            @Override
            public void run() {
                for(int i = currentExercisePartModel.getIndex() - 1; i >= 0; i--){
                    final RelativeLayout animatingCircle = circlesArr.get(i);
                    ((SemiCircleDrawable) animatingCircle.getBackground()).setCompleteElapsed();
                }

                ((SemiCircleDrawable) animatingCircle.getBackground())
                        .setElapsedAngle(elapsedAngle);

                txtStateTime.setText(CalculationHelper.prettifySeconds(
                        currentExercisePartModel.getRemainingDurationSecs(currentExerciseElapsedMs / 1000)));
            }
        });

    }

    @Override
    public void onExercisePartChanged(final ExercisePartModel newExercisePartModel) {
        if(newExercisePartModel != null){
            currentCircleIndex = newExercisePartModel.getIndex();
        }

        Threadings.postRunnable(new Runnable() {
            @Override
            public void run() {

                if(newExercisePartModel == null){
                    imgState.setVisibility(GONE);
                    txtState.setVisibility(GONE);
                    txtStateTime.setVisibility(GONE);
                }
                else{
                    imgState.setVisibility(VISIBLE);
                    txtState.setVisibility(VISIBLE);
                    txtStateTime.setVisibility(VISIBLE);

                    if(!demoMode){
                        for(int i = newExercisePartModel.getIndex() - 1; i >= 0; i--){
                            final RelativeLayout animatingCircle = circlesArr.get(i);
                            ((SemiCircleDrawable) animatingCircle.getBackground()).setCompleteElapsed();
                        }
                    }


                    imgState.setColorFilter(newExercisePartModel.getExerciseColor(context));
                    imgState.setImageDrawable(newExercisePartModel.getExerciseIcon(context));
                    txtState.setText(newExercisePartModel.getExerciseText(context));
                    txtState.setTextColor(newExercisePartModel.getExerciseColor(context));
                    txtStateTime.setTextColor(newExercisePartModel.getExerciseColor(context));

                    if(!demoMode){
                        txtSkip.setVisibility(newExercisePartModel.getExerciseState() ==
                                ExercisePartModel.ExerciseState.WarmUp ?
                                VISIBLE : GONE);
                        txtSkip.setBackgroundColor(newExercisePartModel.getExerciseColor(context));
                    }


                    txtStateTime.setText(CalculationHelper.prettifySeconds(
                            newExercisePartModel.getDurationSecs()));
                }
            }
        });
    }

    @Override
    public void onExerciseEnded(float totalElapsedMs, float caloriesBurnt, boolean isCompleted) {

    }

    public void removeFocusExercisePart(){
        for(RelativeLayout circleLayout : circlesArr){
            circleLayout.setAlpha(1f);
        }
    }

    public void focusExercisePart(int exerciseIndex){
        int i = 0;
        for(RelativeLayout circleLayout : circlesArr){
            if(i == exerciseIndex){
                circleLayout.setAlpha(1);
            }
            else{
                circleLayout.setAlpha(0.2f);
            }
            i++;
        }
    }

    public void enableDemoMode(){
        demoMode = true;

        imgState.setScaleX(0.5f);
        imgState.setScaleY(0.5f);

        txtStateTime.setScaleY(0.5f);
        txtStateTime.setScaleX(0.5f);

        txtState.setScaleY(0.5f);
        txtState.setScaleX(0.5f);

        imgState.setY(AndroidUtils.dpToPx(context, 30f));
        txtState.setY(AndroidUtils.dpToPx(context, -30f));
    }

    private void skipExercisePart(){
        if(exerciseActionListener != null){
            exerciseActionListener.onSkippingExercise();
        }
    }

    private float getExercisePartAngle(ExercisePartModel exercisePartModel){
        return (360 * exercisePartModel.getDurationSecs()) / (exerciseModel.getTotalDurationSecs());
    }

    private void initAnglePerSec(){
        anglePerSec = 360 / exerciseModel.getTotalDurationSecs();
    }

    private void setListeners(){
        txtSkip.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lock){
                    txtSkip.setText("Unlock First");
                }
                else{
                    skipExercisePart();
                }
            }
        });
    }

    public void setLock(boolean lock) {
        this.lock = lock;
        if(!lock){
            txtSkip.setText("Skip");
        }
    }

    public interface ExerciseActionListener{
        void onSkippingExercise();
    }

}
