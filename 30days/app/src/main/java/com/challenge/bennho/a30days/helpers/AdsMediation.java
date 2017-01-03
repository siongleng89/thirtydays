package com.challenge.bennho.a30days.helpers;

import android.app.Activity;
import android.content.Context;

import com.appodeal.ads.Appodeal;

/**
 * Created by Dell-PC on 2/1/2017.
 */

public class AdsMediation {

    public AdsMediation(Activity context){
        String appKey = "38dc146385b8fdf2d856882e1b2ec115a0abbc8407d45e2f";
        Appodeal.disableNetwork(context, "applovin");
        Appodeal.disableNetwork(context, "chartboost");
        Appodeal.disableNetwork(context, "mailru");
        Appodeal.disableNetwork(context, "inmobi");
        Appodeal.disableNetwork(context, "startapp");
        Appodeal.disableNetwork(context, "yandex");
        Appodeal.disableNetwork(context, "flurry");
        Appodeal.disableNetwork(context, "liverail");
        Appodeal.disableLocationPermissionCheck();
        Appodeal.initialize(context, appKey, Appodeal.INTERSTITIAL | Appodeal.BANNER);
    }

}
