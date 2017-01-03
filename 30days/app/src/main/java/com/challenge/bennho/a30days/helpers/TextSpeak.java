package com.challenge.bennho.a30days.helpers;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Locale;
import com.challenge.bennho.a30days.R;


/**
 * Created by Dell-PC on 2/1/2017.
 */

public class TextSpeak{

    TextToSpeech t1;

    public TextSpeak(Context context){
        t1=new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });
    }

    public void speak(String text){
        t1.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }


    public void onPause(){
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
    }
}
