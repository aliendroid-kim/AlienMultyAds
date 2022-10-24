package com.aliendroid.alienads;

import static androidx.lifecycle.Lifecycle.Event.ON_START;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.aliendroid.alienads.interfaces.open.OnLoadOpenAppAdmob;
import com.aliendroid.alienads.interfaces.open.OnShowOpenAppAdmob;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

import java.util.Date;

public class AlienOpenAds implements LifecycleObserver, Application.ActivityLifecycleCallbacks {
    private static final String LOG_TAG = "AlienOpenAds";
    public static String IDOPEN;
    public static MyApplication myApplication;
    private static boolean isShowingAd = false;
    private static AppOpenAd.AppOpenAdLoadCallback loadCallback;
    public static AppOpenAd appOpenAd = null;

    private static Activity currentActivity;
    private long loadTime = 0;
    public static boolean LOADADS;
    public static OnShowOpenAppAdmob onShowOpenAppAdmob;
    public static OnLoadOpenAppAdmob onLoadOpenAppAdmob;

    public AlienOpenAds(MyApplication myApplication) {
        AlienOpenAds.myApplication = myApplication;
        AlienOpenAds.myApplication.registerActivityLifecycleCallbacks(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    private static AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }

    public static void ShowOpen(Activity activity) {

    }

    public static void LoadOpenAds(String idOpenAds, boolean loadads) {
        LOADADS=loadads;
        if (LOADADS){
            try {
                IDOPEN = idOpenAds;
                AdRequest request = getAdRequest();
                AppOpenAd.load(
                        myApplication, idOpenAds, request,
                        AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @OnLifecycleEvent(ON_START)
    public void onStart() {
        showAdIfAvailable();
        Log.d(LOG_TAG, "onStart");
    }

    public void fetchAd() {
            if (isAdAvailable()) {
                return;
            }
            loadCallback =
                    new AppOpenAd.AppOpenAdLoadCallback() {
                        @Override
                        public void onAdLoaded(AppOpenAd ad) {
                            if (onLoadOpenAppAdmob!=null){
                                onLoadOpenAppAdmob.onAdLoaded();
                            }
                            appOpenAd = ad;
                            AlienOpenAds.this.loadTime = (new Date()).getTime();
                            showAdIfAvailable();

                        }
                        @Override
                        public void onAdFailedToLoad(LoadAdError loadAdError) {

                            if (onLoadOpenAppAdmob!=null){
                                onLoadOpenAppAdmob.onAdFailedToLoad();
                            }
                        }
                    };
            LoadOpenAds(IDOPEN,LOADADS);

    }

    private boolean wasLoadTimeLessThanNHoursAgo(long numHours) {
        long dateDifference = (new Date()).getTime() - this.loadTime;
        long numMilliSecondsPerHour = 3600000;
        return (dateDifference < (numMilliSecondsPerHour * numHours));
    }

    public boolean isAdAvailable() {
        return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(24);
    }

    public void showAdIfAvailable() {
        if (!isShowingAd && isAdAvailable()) {
            Log.d(LOG_TAG, "Will show ad.");
            FullScreenContentCallback fullScreenContentCallback =
                    new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            appOpenAd = null;
                            isShowingAd = false;
                                if (onShowOpenAppAdmob!=null){
                                    onShowOpenAppAdmob.onAdDismissedFullScreenContent();
                                }
                            LOADADS=false;
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            appOpenAd = null;
                            isShowingAd = false;
                            if (onShowOpenAppAdmob!=null){
                                onShowOpenAppAdmob.onAdFailedToShowFullScreenContent();
                            }
                            LOADADS=false;
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            if (onShowOpenAppAdmob!=null){
                                onShowOpenAppAdmob.onAdShowedFullScreenContent();
                            }
                            appOpenAd = null;
                            isShowingAd = false;
                            LOADADS=false;
                        }
                    };

            appOpenAd.setFullScreenContentCallback(fullScreenContentCallback);
            appOpenAd.show(currentActivity);

        } else {
            Log.d(LOG_TAG, "Can not show ad.");
            fetchAd();
        }
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

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