package com.challenge.bennho.a30days.controls;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.helpers.OverlayBuilder;
import com.challenge.bennho.a30days.models.FoodModel;
import com.makeramen.roundedimageview.RoundedImageView;

/**
 * Created by sionglengho on 29/12/16.
 */

public class ImageCircularFood extends RelativeLayout {
    private Context context;
    private RoundedImageView imgView;

    public ImageCircularFood(Context context) {
        super(context);
        init(context, null);
    }

    public ImageCircularFood(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ImageCircularFood(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void extractAttrs(AttributeSet attrs){

    }

    public void init(Context context, AttributeSet attrs) {
        extractAttrs(attrs);
        this.context = context;

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.image_circular_food, this, true);

        imgView = (RoundedImageView) findViewById(R.id.imageView);

//        imgView.setOval(true);
//        imgView.setBackgroundColor(Color.parseColor("#333333"));
//        imgView.mutateBackground(true);
//
//       // imgView.setImageResource(R.drawable.cross_icon);
    }

    public void setFood(final FoodModel food){
        setImage(food.getDrawable(context));
        imgView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                OverlayBuilder.build(context)
                        .setOverlayType(OverlayBuilder.OverlayType.OkOnly)
                        .setContent(food.getDescription())
                        .show();
            }
        });
    }

    private void setImage(Drawable drawable){
        imgView.setImageDrawable(drawable);
    }

}
