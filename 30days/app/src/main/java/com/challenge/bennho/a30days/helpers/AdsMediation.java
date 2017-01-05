package com.challenge.bennho.a30days.helpers;

import android.app.Activity;
import android.content.Context;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.BannerCallbacks;
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

        Appodeal.cache(activity, Appodeal.INTERSTITIAL | Appodeal.BANNER);
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

    public static void showBanner(final Activity activity, final AdsListener adsListener){
//        if(Appodeal.isLoaded(Appodeal.BANNER)){
//
//        }
//        else{
//            //log analytics
//        }
        setBannerListener(activity, adsListener);



        Appodeal.show(activity, Appodeal.BANNER_BOTTOM);

    }

    public static void setBannerListener(final Activity activity, final AdsListener adsListener){
        Appodeal.setBannerCallbacks(new BannerCallbacks() {
            private int nextAdsHeightPx = AndroidUtils.getAdViewHeightInPixels(activity);

            @Override
            public void onBannerLoaded(int height, boolean isPrecache) {
                nextAdsHeightPx = AndroidUtils.dpToPx(activity, height);
            }

            @Override
            public void onBannerFailedToLoad() {
            }

            @Override
            public void onBannerShown() {
                if(adsListener != null){
                    adsListener.onBannerShown(nextAdsHeightPx);
                }
                //log analytics
            }

            @Override
            public void onBannerClicked() {
            }

        });
    }

    public static void hideBanner(Activity activity){
        Appodeal.hide(activity, Appodeal.BANNER_BOTTOM);
    }

    public interface AdsListener{
        void onBannerShown(int heightPixel);
    }

}
