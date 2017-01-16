package com.challenge.bennho.a30days.helpers;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.speech.tts.Voice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;


/**
 * Created by Dell-PC on 2/1/2017.
 */

public class TextSpeak{

    private TextToSpeech textToSpeech;
    private ArrayList<String> unspeakStrings;
    private boolean initialized;
    private AudioManager audioManager;
    private int id;
    private static TextSpeak staticTextSpeak;
    private boolean focusing;

    public static TextSpeak getInstance(Context context){
        if(staticTextSpeak == null){
            staticTextSpeak = new TextSpeak(context);
        }
        return staticTextSpeak;
    }

    public TextSpeak(Context context){
        unspeakStrings = new ArrayList();
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        textToSpeech =new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
                    textToSpeech.setSpeechRate(0.75f);
                    if (android.os.Build.VERSION.SDK_INT >= 15){
                        textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                            @Override
                            public void onStart(String utteranceId) {

                            }

                            @Override
                            public void onDone(String utteranceId) {
                                focusing = false;
                                Threadings.delay(3000, new Runnable() {
                                    @Override
                                    public void run() {
                                        if(!focusing){
                                            audioManager.abandonAudioFocus(null);
                                        }
                                    }
                                });

                            }

                            @Override
                            public void onError(String utteranceId) {
                                focusing = false;
                                Threadings.delay(3000, new Runnable() {
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
                    else{
                        textToSpeech.setOnUtteranceCompletedListener(new TextToSpeech.OnUtteranceCompletedListener() {
                            @Override
                            public void onUtteranceCompleted(String utteranceId) {
                                audioManager.abandonAudioFocus(null);
                            }
                        });
                    }

                    initialized = true;
                    for(String toSpeak : unspeakStrings){
                        speak(toSpeak);
                    }

                }
            }
        });



    }

    public void speak(final String text){
        if(!initialized){
            unspeakStrings.add(text);
        }
        else{
            focusing = true;
            audioManager.requestAudioFocus(null,
                            AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

            id++;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Bundle bundle = new Bundle();
                bundle.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "");
                textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, bundle, String.valueOf(id));
            } else {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, String.valueOf(id));
                textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, map);
            }
        }
    }


    public void onPause(){
        if(textToSpeech !=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

    public void dispose(){
        textToSpeech.stop();
        textToSpeech.shutdown();
        unspeakStrings.clear();

    }

}
