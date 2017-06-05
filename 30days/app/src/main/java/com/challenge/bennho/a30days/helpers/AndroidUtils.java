package com.challenge.bennho.a30days.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

/**
 * Created by sionglengho on 21/12/16.
 */

public class AndroidUtils {

    public static int getAdViewHeightInPixels(Activity activity) {
        int adHeight = 0;

        int screenHeightInDP = (int) getScreenDpHeight(activity);
        if (screenHeightInDP < 400)
            adHeight = 32;
        else if (screenHeightInDP >= 400 && screenHeightInDP <= 720)
            adHeight = 50;
        else
            adHeight = 90;

        return dpToPx(activity, adHeight);
    }

    public static int pxToDp(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public static int dpToPx(Context context, float dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }


    public static float getScreenDpHeight(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density = activity.getResources().getDisplayMetrics().density;
        float dpHeight = outMetrics.heightPixels / density;
        float dpWidth = outMetrics.widthPixels / density;
        return dpHeight;
    }

    public static float getScreenDpWidth(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density = activity.getResources().getDisplayMetrics().density;
        float dpHeight = outMetrics.heightPixels / density;
        float dpWidth = outMetrics.widthPixels / density;
        return dpWidth;
    }

    public static Bitmap textAsBitmap(String text, float textSize, int textColor) {
        Paint paint = new Paint(ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setFakeBoldText(true);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.0f); // round
        int height = (int) (baseline + paint.descent() + 0.0f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, baseline, paint);
        return image;
    }


    public static ArrayList<Drawable> getAnimationDrawables(Context context, String name, int totalFrames) {
        ArrayList<Drawable> arr = new ArrayList();
        for (int i = 0; i < totalFrames; i++) {
            String id = name + String.format("%02d", i);
            int resID = context.getResources().getIdentifier(id, "drawable", context.getPackageName());
            arr.add(ContextCompat.getDrawable(context, resID));
        }

        return arr;

    }

    public static String getStringByIdentifier(Context context, String name) {
        return context.getString(context.getResources().getIdentifier(name, "string", context.getPackageName()));
    }

    public static int getDrawableIdentifier(Context context, String name) {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }

    public static File getPrivateFilePath(Context context, String fileName) {
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir("days30", Context.MODE_PRIVATE);
        File theFile = new File(directory, fileName);
        if (!theFile.exists()) {
            try {
                theFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return theFile;
    }

    public static void moveFileToPrivateDir(Context context, File fromFile, String saveAsName) throws IOException {
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir("days30", Context.MODE_PRIVATE);
        File to = new File(directory, saveAsName);

        if (!to.exists()) {
            to.createNewFile();
        }
        copy(fromFile, to);
    }


    public static void resizeAndShrinkImageFile(File file, int destWidth, int destHeight) {
        Bitmap b = BitmapFactory.decodeFile(file.getAbsolutePath());
        Bitmap out = Bitmap.createScaledBitmap(b, destWidth, destHeight, false);

        FileOutputStream fOut;
        try {
            fOut = new FileOutputStream(file);
            out.compress(Bitmap.CompressFormat.JPEG, 90, fOut);
            fOut.flush();
            fOut.close();
            b.recycle();
            out.recycle();
        } catch (Exception e) {
        }

    }

    public static void copy(File src, File dst) throws IOException {
        dst.delete();

        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);

        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }

    public static void setFullscreen(final Activity activity, final boolean fullscreen) {
        Threadings.postRunnable(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    final View decorView = activity.getWindow().getDecorView();
                    if (fullscreen) {
                        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                            @Override
                            public void onSystemUiVisibilityChange(int visibility) {
                                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                                    decorView.setSystemUiVisibility(
                                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                                }
                            }
                        });

                        decorView.setSystemUiVisibility(
                                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                    } else {
                        decorView.setOnSystemUiVisibilityChangeListener(null);
                        activity.getWindow().getDecorView().setSystemUiVisibility(0);
                    }
                } else {
                    WindowManager.LayoutParams attrs = activity.getWindow().getAttributes();
                    if (fullscreen) {
                        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
                    } else {
                        attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
                    }
                    activity.getWindow().setAttributes(attrs);
                }
            }
        });

    }

}
