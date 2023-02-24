package com.aliendroid.alienads;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.aliendroid.sdkads.interfaces.OnShowAdCompleteListener;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAppOpenAd;



import java.util.Date;

public class ApplovinOpenAds implements LifecycleObserver, Application.ActivityLifecycleCallbacks {
    public static MyApplication myApplication;
    public static Activity currentActivity;
    public ApplovinOpenAds(MyApplication myApplication) {
        ApplovinOpenAds.myApplication = myApplication;
        ApplovinOpenAds.myApplication.registerActivityLifecycleCallbacks(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }
    public static boolean LOADADS;
    public static String IDOPEN ="";
    public static void LoadOpenAds(String idOpenAds, boolean loadads) {
        LOADADS=loadads;
        try {
            if (LOADADS){
                IDOPEN = idOpenAds;
            } else {
                IDOPEN = "";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static class AppOpenAdManager {
        private static final String LOG_TAG = "AppOpenAdManager";
        public static MaxAppOpenAd appOpenAdApplovin = null;
        private static boolean isLoadingAd = false;
        private static boolean isShowingAd = false;

        /** Constructor. */
        public AppOpenAdManager() {}

        /**
         * Load an ad.
         *
         * @param context the context of the activity that loads the ad
         */
        public static void loadAd(Context context) {
            // Do not load ad if there is an unused ad or one is already loading.
            if (isLoadingAd || isAdAvailable()) {
                return;
            }

            isLoadingAd = true;
            appOpenAdApplovin = new MaxAppOpenAd(IDOPEN, context);
            appOpenAdApplovin.loadAd();


        }

        public static boolean isAdAvailable() {
            return appOpenAdApplovin != null ;
        }


        public static void showAdIfAvailable(@NonNull final Activity activity) {
            showAdIfAvailable(activity,()  -> {
            });
        }


        /**
         * Show the ad if one isn't already showing.
         *
         * @param activity the activity that shows the app open ad
         * @param onShowAdCompleteListener the listener to be notified when an app open ad is complete
         */
        public static void showAdIfAvailable(@NonNull final Activity activity, @NonNull OnShowAdCompleteListener onShowAdCompleteListener) {
            if (isShowingAd) {
                return;
            }

            if (!isAdAvailable()) {
                Log.d(LOG_TAG, "The app open ad is not ready yet.");
                onShowAdCompleteListener.onShowAdComplete();
                loadAd(activity);
                return;
            }

            Log.d(LOG_TAG, "Will show ad.");
            appOpenAdApplovin.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {
                    isLoadingAd = true;
                    Log.d(LOG_TAG, "onAdLoaded.");
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {
                    isLoadingAd = false;
                    appOpenAdApplovin = null;
                    isShowingAd = false;
                    loadAd(activity);
                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    isShowingAd = false;
                    onShowAdCompleteListener.onShowAdComplete();
                    loadAd(activity);
                    Log.d(LOG_TAG, "onAdDismissedFullScreenContent.");
                }

                @Override
                public void onAdClicked(MaxAd ad) {
                    isLoadingAd = false;
                    appOpenAdApplovin = null;
                    isShowingAd = false;
                    loadAd(activity);
                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    isLoadingAd = false;
                    appOpenAdApplovin = null;
                    isShowingAd = false;
                    onShowAdCompleteListener.onShowAdComplete();
                    loadAd(activity);
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                    isLoadingAd = false;
                    appOpenAdApplovin = null;
                    isShowingAd = false;
                    onShowAdCompleteListener.onShowAdComplete();
                    loadAd(activity);
                }
            });

            isShowingAd = true;
            appOpenAdApplovin.showAd();

        }


    }
    /** LifecycleObserver method that shows the app open ad when the app moves to foreground. */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected void onMoveToForeground() {
        // Show the ad (if available) when the app moves to foreground.
        AppOpenAdManager.showAdIfAvailable(currentActivity);
    }


    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        if (!AppOpenAdManager.isShowingAd) {
            currentActivity = activity;

        }
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {


    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }
}

