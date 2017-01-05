package com.challenge.bennho.a30days.helpers;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.challenge.bennho.a30days.R;

/**
 * Created by sionglengho on 4/1/17.
 */

public class MediaHelper {

    private static boolean focusing = false;

    public static void playAnnouncementSound(Context context){
        focusing = true;
        final AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.requestAudioFocus(null,
                AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        MediaPlayer mp = MediaPlayer.create(context,
                R.raw.announcement);
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
