package com.challenge.bennho.a30days.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.activities.RunningActivity;
import com.challenge.bennho.a30days.helpers.CalculationHelper;
import com.challenge.bennho.a30days.helpers.Logs;
import com.challenge.bennho.a30days.helpers.Threadings;
import com.challenge.bennho.a30days.models.ExerciseModel;
import com.challenge.bennho.a30days.models.ExercisePartModel;

/**
 * Created by sionglengho on 27/12/16.
 */

public class ExerciseService extends Service {

    private Binder binder;
    private ExerciseModel exerciseModel;
    private boolean skippingExercisePart;
    private float previousStatesElapsedMs, currentTotalElapsedMs, realElapsedMs, currentCaloriesBurnt;
    private long accSleepMs;
    private ExercisePartModel currentExercisePartModel;
    private ExerciseListener exerciseListener;
    private boolean paused, stopped, running, completed;
    private final int SERVICE_ID = 7012;
    private String lastNotificationText;

    public ExerciseService() {
        this.binder = new LocalBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    /**
     *
     * @param exerciseModel
     * @param exerciseListener
     * @return true if start successfully, else false(cause already have a exist instance running)
     */
    public boolean startExercise(ExerciseModel exerciseModel, ExerciseListener exerciseListener){
        this.exerciseModel = exerciseModel;
        this.exerciseListener = exerciseListener;

        if(running){
            return false;
        }

        this.stopped = false;
        this.paused = false;
        this.completed = false;
        this.skippingExercisePart = false;
        this.previousStatesElapsedMs = 0;
        this.currentTotalElapsedMs = 0;
        this.currentCaloriesBurnt = 0;
        this.realElapsedMs = 0;
        this.lastNotificationText = "";
        exercising(0);
        running = true;
        startAsForeground();

        return true;
    }

    private void exercising(final int index){
        if(index >= exerciseModel.getExercisePartModels().size()){
            exerciseCompleted();
            return;
        }

        skippingExercisePart = false;

        previousStatesElapsedMs = currentTotalElapsedMs;
        currentExercisePartModel = exerciseModel.getExercisePartModels().get(index);

        if(exerciseListener != null){
            exerciseListener.onExercisePartChanged(currentExercisePartModel);
            exerciseListener.onTimeChanged(currentTotalElapsedMs, 0, currentCaloriesBurnt,
                                                currentExercisePartModel);
            updateNotification(0, currentExercisePartModel);
        }


        Threadings.runInBackground(new Runnable() {
            @Override
            public void run() {

                final long sleepMs = 100;
                accSleepMs = 0;

                while (accSleepMs < currentExercisePartModel.getDurationSecs() * 1000){
                    if(skippingExercisePart){
                        currentTotalElapsedMs = previousStatesElapsedMs +
                                currentExercisePartModel.getDurationSecs() * 1000;
                        exercising(index + 1);
                        return;
                    }

                    Threadings.sleep(sleepMs);

                    if(paused){
                        continue;
                    }

                    if(stopped){
                        return;
                    }

                    accSleepMs += sleepMs;
                    currentTotalElapsedMs += sleepMs;
                    realElapsedMs += sleepMs;

                    updateNotification(accSleepMs, currentExercisePartModel);
                    updateCaloriesBurnt(sleepMs, currentExercisePartModel);
                    exerciseListener.onTimeChanged(currentTotalElapsedMs,
                            accSleepMs, currentCaloriesBurnt, currentExercisePartModel);

                }

                exercising(index + 1);
            }
        });
    }

    private void startAsForeground(){
        startForeground(SERVICE_ID, getNotification(getString(R.string.app_name), ""));
    }

    private void updateNotification(float currentExercisePartElapsedMs,
                                                ExercisePartModel currentExercisePartModel){
        String notificationTime = CalculationHelper.prettifySeconds(
                currentExercisePartModel.getDurationSecs() - (currentExercisePartElapsedMs / 1000));
        if(!notificationTime.equals(lastNotificationText)){
            NotificationManager mNotificationManager = (NotificationManager)
                                        getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(SERVICE_ID, getNotification(
                    currentExercisePartModel.getExerciseText(this.getBaseContext()), notificationTime));
        }
    }

    private void updateCaloriesBurnt(float deltaMs, ExercisePartModel currentExercisePartModel){
        currentCaloriesBurnt += currentExercisePartModel.getCaloriesBurnt(deltaMs);
    }

    public void exerciseCompleted(){
        stopped = true;
        completed = true;
        stopForeground(true);
        NotificationManager mNotificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(SERVICE_ID, getNotification("Good Job!",
                                "Completed exercise, click for result."));
        exerciseListener.onExerciseEnded(realElapsedMs, currentCaloriesBurnt, completed);
    }

    public void exerciseGaveUp(){
        stopped = true;
        exerciseListener.onExerciseEnded(realElapsedMs, currentCaloriesBurnt, false);
    }

    public void disposeExercise(){
        stopForeground(true);
        stopSelf();
        NotificationManager mNotificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(SERVICE_ID);
        stopped = true;
        running = false;
        this.exerciseListener = null;
    }

    public void pauseExercise(){
        paused = true;
    }

    public void resumeExercise(){
        paused = false;
    }



    public void requestTriggerStateChangedOnce(){
        exerciseListener.onTimeChanged(currentTotalElapsedMs,
                accSleepMs, currentCaloriesBurnt, currentExercisePartModel);

        if(stopped){
            exerciseListener.onExerciseEnded(currentTotalElapsedMs, currentCaloriesBurnt, completed);
        }
    }

    public void goToNextExercisePart(){
        skippingExercisePart = true;
    }

    private Notification getNotification(String title, String content){
        Intent notificationIntent = new Intent(this, RunningActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pendingIntent).build();

        return notification;
    }


    public void setExerciseListener(ExerciseListener exerciseListener) {
        this.exerciseListener = exerciseListener;
    }

    public boolean isPaused() {
        return paused;
    }

    public class LocalBinder extends Binder {
        public ExerciseService getServiceInstance(){
            return ExerciseService.this;
        }
    }

    public interface ExerciseListener{

        void onTimeChanged(float totalElapsedMs, float currentExerciseElapsedMs, float caloriesBurnt,
                                  ExercisePartModel currentExercisePartModel);

        void onExercisePartChanged(ExercisePartModel newExercisePartModel);

        void onExerciseEnded(float totalElapsedMs, float caloriesBurnt, boolean isCompleted);
    }
}
