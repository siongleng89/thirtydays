package com.challenge.bennho.a30days.helpers;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;

import com.challenge.bennho.a30days.R;

/**
 * Created by sionglengho on 4/1/17.
 */

public class MediaHelper {

    private static boolean focusing = false;

    /**
     * Play annoucement bell sound and vibrate at the same time
     * @param context
     */
    public static void playAnnouncementSoundAndVibrate(Context context){
        focusing = true;
        final AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.requestAudioFocus(null,
                AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        MediaPlayer mp = MediaPlayer.create(context,
                R.raw.announcement);



        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        // Start without a delay
        // Each element then alternates between vibrate, sleep, vibrate, sleep...
        long[] pattern = {0, 200, 300, 200};

        v.vibrate(pattern, -1);

        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
                focusing = false;
                Threadings.delay(2000, new Runnable() {
                    @Override
                    public void run() {
                        if(!focusing){
                            audioManager.abandonAudioFocus(null);
                        }
                    }
                });

            }

        });
    }


}
