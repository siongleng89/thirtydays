package com.challenge.bennho.a30days.helpers;

import android.content.Context;

import java.io.File;

/**
 * Created by sionglengho on 26/1/17.
 */

public class UserPhotoHelpers {

    public static File getDayPhotoThumbnailFilePath(Context context, int day, int currentIteration) {
        if (currentIteration == 0) {
            return AndroidUtils.getPrivateFilePath(context, day + "thumb.jpg");
        } else {
            return AndroidUtils.getPrivateFilePath(context, currentIteration + "_" + day + "thumb.jpg");
        }
    }

    public static File getDayPhotoImageFilePath(Context context, int day, int currentIteration) {
        if (currentIteration == 0) {
            return AndroidUtils.getPrivateFilePath(context, day + ".jpg");
        } else {
            return AndroidUtils.getPrivateFilePath(context, currentIteration + "_" + day + ".jpg");
        }
    }


}
