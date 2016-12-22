package com.challenge.bennho.a30days.drawables;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.challenge.bennho.a30days.R;

/**
 * Created by sionglengho on 22/12/16.
 */

public class SemiCircleDrawable extends Drawable {

    private Paint paint, paintWhite;
    private RectF rectF;
    private int color;
    private int angle;


    public SemiCircleDrawable(int color, int angle) {
        this.color = color;
        this.angle = angle;
        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);

        paintWhite = new Paint();
        paintWhite.setColor(Color.WHITE);
        paintWhite.setStyle(Paint.Style.FILL);

        rectF = new RectF();
    }

    public int getColor() {
        return color;
    }

    /**
     * A 32bit color not a color resources.
     * @param color
     */
    public void setColor(int color) {
        this.color = color;
        paint.setColor(color);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();

        Rect bounds = getBounds();
        rectF.set(bounds);

        canvas.drawArc(rectF, -90, angle, true, paint);

        canvas.drawArc(rectF, angle - 90, 360 - angle, true, paintWhite);
    }

    @Override
    public void setAlpha(int alpha) {
        // Has no effect
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        // Has no effect
    }

    @Override
    public int getOpacity() {
        // Not Implemented
        return PixelFormat.OPAQUE;
    }

}
