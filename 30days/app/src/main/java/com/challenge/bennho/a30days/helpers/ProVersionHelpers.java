package com.challenge.bennho.a30days.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.challenge.bennho.a30days.enums.PreferenceType;
import com.challenge.bennho.a30days.helpers.Iab.IabHelper;
import com.challenge.bennho.a30days.helpers.Iab.IabResult;
import com.challenge.bennho.a30days.helpers.Iab.Inventory;
import com.challenge.bennho.a30days.helpers.Iab.Purchase;
import com.challenge.bennho.a30days.statics.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SiongLeng on 15/9/2016.
 */
public class ProVersionHelpers {

    private static ProVersionHelpers instance;
    private IabHelper iabHelper;
    private Context context;
    private boolean purchased;
    private boolean ready;
    private ArrayList<RunnableArgs<Boolean>> toRunRunnables;
    private static final int RC_REQUEST = 10001;
    private final String itemSku = "pro_version";
    private Runnable onPurchaseComplete;

    public static ProVersionHelpers getInstance(Context context){
        if(instance == null){
            instance = new ProVersionHelpers(context);
        }
        return instance;
    }

    private ProVersionHelpers(Context context) {
        this.context = context;
        toRunRunnables = new ArrayList();

        iabHelper = new IabHelper(context, Constants.IABKey);
        iabHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            @Override
            public void onIabSetupFinished(IabResult result) {
                if(result.isSuccess()){
                    List<String> moreSkus = new ArrayList<String>();
                    moreSkus.add(itemSku);

                    try {
                        iabHelper.queryInventoryAsync(true, moreSkus, moreSkus,
                                mGotInventoryListener);

                    } catch (IabHelper.IabAsyncInProgressException e) {
                        e.printStackTrace();
                        cannotDetermineProIsPurchased();
                    }
                }
            }
        });
    }

    IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new
            IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result, final Inventory inventory) {
            // Have we been disposed of in the meantime? If so, quit.
            if (iabHelper == null){
                cannotDetermineProIsPurchased();
                return;
            }

            // Is it a failure?
            if (result.isFailure()) {
                cannotDetermineProIsPurchased();
                return;
            }


            Purchase proPurchase = inventory.getPurchase(itemSku);
            if (proPurchase != null && proPurchase.getDeveloperPayload().equals(Constants.IABDeveloperPayload)) {
                proPurchased();
                return;
            }

            proNotPurchased();


        }
    };

    private void proPurchased(){
        purchased = true;
        onIabReady();
    }

    private void proNotPurchased(){
        purchased = false;
        onIabReady();
    }

    //cannot determine as Iab error occurred, temporarily use cache vip value until next reboot
    private void cannotDetermineProIsPurchased(){
        String isVip = PreferenceUtils.getString(context, PreferenceType.ProPurchased);
        if(!Strings.isEmpty(isVip) && isVip.equals("1")){
            proPurchased();
        }
        else{
            proNotPurchased();
        }
    }

    private void onIabReady(){
        ready = true;

        Threadings.postRunnable(new Runnable() {
            @Override
            public void run() {
                for(RunnableArgs<Boolean> toRun: toRunRunnables){
                    toRun.run(purchased);
                }

                toRunRunnables.clear();
            }
        });
    }

    public void isProPurchased(final RunnableArgs<Boolean> onResult){
        if(!ready){
            toRunRunnables.add(onResult);
        }
        else{
            onResult.run(purchased);
        }
    }

    public void purchasePro(Activity activity, Runnable onFinish){
        this.onPurchaseComplete = onFinish;
        try {
            iabHelper.launchSubscriptionPurchaseFlow(activity,
                    itemSku,
                    RC_REQUEST, mPurchaseFinishedListener,
                    Constants.IABDeveloperPayload);
        } catch (IabHelper.IabAsyncInProgressException e) {

        }
    }


    // Callback for when a purchase is finished
    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
            // if we were disposed of in the meantime, quit.
            if (iabHelper == null) return;

            if (result.isFailure()) {
                return;
            }

            if (purchase.getDeveloperPayload() == null ||
                    !purchase.getDeveloperPayload().equals(Constants.IABDeveloperPayload)) {
                return;
            }

            PreferenceUtils.putString(context, PreferenceType.ProPurchased, "1");
            purchased = true;
            if(onPurchaseComplete != null) onPurchaseComplete.run();
        }
    };

    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        if (iabHelper == null) return false;

        // Pass on the activity result to the helper for handling
        if (!iabHelper.handleActivityResult(requestCode, resultCode, data)) {
            // not handled, so handle it ourselves (here's where you'd
            // perform any handling of activity results not related to in-app
            // billing...
            return false;
        }
        else{
            return true;
        }
    }


    public void dispose(){
        if (iabHelper != null) {
            iabHelper.disposeWhenFinished();
            iabHelper = null;
        }
    }

}
