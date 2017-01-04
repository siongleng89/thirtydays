package com.challenge.bennho.a30days.helpers;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;

import java.util.ArrayList;
import java.util.Locale;


/**
 * Created by Dell-PC on 2/1/2017.
 */

public class TextSpeak{

    private TextToSpeech textToSpeech;
    private ArrayList<String> unspeakStrings;
    private boolean initialized;

    public TextSpeak(Context context){
        unspeakStrings = new ArrayList();
        textToSpeech =new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
            } else {
                textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
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
