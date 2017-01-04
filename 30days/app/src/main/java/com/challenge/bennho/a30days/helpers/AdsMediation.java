package com.challenge.bennho.a30days.helpers;

import android.app.Activity;
import android.content.Context;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialCallbacks;
import com.challenge.bennho.a30days.statics.Constants;

/**
 * Created by Dell-PC on 2/1/2017.
 */

public class AdsMediation {

    private static boolean initiated = false;

    public static void init(Activity activity){
        if(initiated){
            return;
        }

        initiated = true;

        String appKey = Constants.AppoDealId;
        Appodeal.disableNetwork(activity, "applovin");
        Appodeal.disableNetwork(activity, "chartboost");
        Appodeal.disableNetwork(activity, "mailru");
        Appodeal.disableNetwork(activity, "inmobi");
        Appodeal.disableNetwork(activity, "startapp");
        Appodeal.disableNetwork(activity, "yandex");
        Appodeal.disableNetwork(activity, "flurry");
        Appodeal.disableNetwork(activity, "liverail");
        Appodeal.disableLocationPermissionCheck();
        Appodeal.initialize(activity, appKey, Appodeal.INTERSTITIAL | Appodeal.BANNER);

        Appodeal.cache(activity, Appodeal.INTERSTITIAL);
    }

    public static void showInterstitial(final Activity activity){
        if (Appodeal.isLoaded(Appodeal.INTERSTITIAL)) {
            Appodeal.setInterstitialCallbacks(new InterstitialCallbacks() {

                @Override
                public void onInterstitialLoaded(boolean isPrecache) {
                }

                @Override
                public void onInterstitialFailedToLoad() {
                }

                @Override
                public void onInterstitialShown() {
                    //logs analytics
                    Appodeal.cache(activity, Appodeal.INTERSTITIAL);
                }

                @Override
                public void onInterstitialClicked() {
                }

                @Override
                public void onInterstitialClosed() {
                }

            });

            Appodeal.show(activity, Appodeal.INTERSTITIAL);
        }
        else{
            //logs analytics
        }
    }

}
