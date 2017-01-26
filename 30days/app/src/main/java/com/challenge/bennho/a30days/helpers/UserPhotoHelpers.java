package com.challenge.bennho.a30days.helpers;

import android.content.Context;

import java.io.File;

/**
 * Created by sionglengho on 26/1/17.
 */

public class UserPhotoHelpers {

    public static File getDayPhotoThumbnailFilePath(Context context, int day){
        return AndroidUtils.getPrivateFilePath(context, day + "thumb.jpg");
    }

    public static File getDayPhotoImageFilePath(Context context, int day){
        return AndroidUtils.getPrivateFilePath(context, day + ".jpg");
    }


}
