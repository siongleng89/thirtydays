package com.challenge.bennho.a30days.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.drawables.CustomAnimationDrawable;
import com.challenge.bennho.a30days.helpers.AndroidUtils;

import java.util.ArrayList;

/**
 * Created by Dell-PC on 5/1/2017.
 */

public class ActivityLayout extends MyActivity {

    private ImageView imageViewStickman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_warmup_walk_fastwalk);

        imageViewStickman= (ImageView) findViewById(R.id.imgStickManWalk);

        CustomAnimationDrawable customAnimationDrawable = getAnimationDrawable("running_0", 6, 100, true);
        imageViewStickman.setImageDrawable(customAnimationDrawable);
        customAnimationDrawable.start();

    }

    private CustomAnimationDrawable getAnimationDrawable(String name, int totalFrames,
                                                         int frameDuration, final boolean repeat){

        final CustomAnimationDrawable animation = new CustomAnimationDrawable();

        animation.setAnimationListener(new CustomAnimationDrawable.AnimationListener() {
            @Override
            public void onComplete() {
                if(repeat){
                    animation.reset();
                }
                else{
                    animation.stop();
                }
            }
        });

        if(repeat){
            animation.setOneShot(true);
        }
        else{
            animation.setOneShot(true);
        }


        ArrayList<Drawable> drawables = AndroidUtils.getAnimationDrawables(this, name, totalFrames);
        for(Drawable drawable : drawables){
            animation.addFrame(drawable, frameDuration);
        }

        return animation;
    }


}
