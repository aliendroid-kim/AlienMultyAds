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

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAppOpenAd;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

import java.util.Date;

public class JamboxOpenAds implements LifecycleObserver, Application.ActivityLifecycleCallbacks {
    public static String IDOPEN = "";
    public static MyApplication myApplication;
    public static AppOpenAdManager appOpenAdManager;
    public static Activity currentActivity;
    public static boolean LOADADS;
    public static String SELECT_ADS = "";

    public JamboxOpenAds(MyApplication myApplication) {
        JamboxOpenAds.myApplication = myApplication;
        JamboxOpenAds.myApplication.registerActivityLifecycleCallbacks(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    public static void LoadOpenAds(String idOpenAds, boolean loadads) {
        LOADADS = loadads;
        try {
            if (LOADADS) {
                IDOPEN = idOpenAds;
            } else {
                IDOPEN = "";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void LoadOpenAds(String idOpenAds, boolean loadads, String selectADS) {
        LOADADS = loadads;
        SELECT_ADS = selectADS;
        try {
            if (LOADADS) {
                IDOPEN = idOpenAds;
            } else {
                IDOPEN = "";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected void onMoveToForeground() {
        // Show the ad (if available) when the app moves to foreground.
        appOpenAdManager.showAdIfAvailable(currentActivity);
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        if (!appOpenAdManager.isShowingAd) {
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


    public interface OnShowAdCompleteListener {
        void onShowAdComplete();
    }

    public static class AppOpenAdManager {
        private static final String LOG_TAG = "AppOpenAdManager";
        public static AppOpenAd appOpenAd = null;
        public static MaxAppOpenAd appOpenAdApplovin = null;
        private static boolean isLoadingAd = false;
        static boolean isShowingAd = false;
        private static long loadTime = 0;

        public AppOpenAdManager() {
        }

        public static void loadAd(Context context) {
            if (SELECT_ADS.equals("ADMOB")) {
                if (isLoadingAd || isAdAvailable()) {
                    return;
                }
                isLoadingAd = true;
                AdRequest request = new AdRequest.Builder().build();
                AppOpenAd.load(context, IDOPEN, request,
                        AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
                        new AppOpenAd.AppOpenAdLoadCallback() {
                            @Override
                            public void onAdLoaded(AppOpenAd ad) {
                                appOpenAd = ad;
                                isLoadingAd = false;
                                loadTime = (new Date()).getTime();
                                Log.d(LOG_TAG, "onAdLoaded.");

                            }

                            @Override
                            public void onAdFailedToLoad(LoadAdError loadAdError) {
                                isLoadingAd = false;
                                Log.d(LOG_TAG, "onAdFailedToLoad: " + loadAdError.getMessage());

                            }
                        });
            } else if (SELECT_ADS.equals("ALIEN-V")) {
                // Do not load ad if there is an unused ad or one is already loading.
                if (isLoadingAd || isAdAvailable()) {
                    return;
                }

                isLoadingAd = true;
                appOpenAdApplovin = new MaxAppOpenAd(IDOPEN, context);
                appOpenAdApplovin.loadAd();

            } else {
                if (isLoadingAd || isAdAvailable()) {
                    return;
                }
                isLoadingAd = true;
                AdRequest request = new AdRequest.Builder().build();
                AppOpenAd.load(context, IDOPEN, request,
                        AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
                        new AppOpenAd.AppOpenAdLoadCallback() {
                            @Override
                            public void onAdLoaded(AppOpenAd ad) {
                                appOpenAd = ad;
                                isLoadingAd = false;
                                loadTime = (new Date()).getTime();
                                Log.d(LOG_TAG, "onAdLoaded.");

                            }

                            @Override
                            public void onAdFailedToLoad(LoadAdError loadAdError) {
                                isLoadingAd = false;
                                Log.d(LOG_TAG, "onAdFailedToLoad: " + loadAdError.getMessage());

                            }
                        });
            }

        }

        private static boolean wasLoadTimeLessThanNHoursAgo(long numHours) {
            long dateDifference = (new Date()).getTime() - loadTime;
            long numMilliSecondsPerHour = 3600000;
            return (dateDifference < (numMilliSecondsPerHour * numHours));
        }

        private static boolean isAdAvailable() {
            if (SELECT_ADS.equals("ADMOB")) {
                return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4);
            } else if (SELECT_ADS.equals("ALIEN-V")) {
                return appOpenAdApplovin != null;
            } else {
                return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4);
            }

        }

        public static void showAdIfAvailable(@NonNull final Activity activity) {
            showAdIfAvailable(activity, new OnShowAdCompleteListener() {
                @Override
                public void onShowAdComplete() {

                }
            });
        }

        public static void showAdIfAvailable(
                @NonNull final Activity activity,
                @NonNull OnShowAdCompleteListener onShowAdCompleteListener) {
            if (SELECT_ADS.equals("ADMOB")) {
                if (isShowingAd) {
                    return;
                }

                if (!isAdAvailable()) {
                    onShowAdCompleteListener.onShowAdComplete();
                    loadAd(activity);
                    return;
                }

                appOpenAd.setFullScreenContentCallback(
                        new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                appOpenAd = null;
                                isShowingAd = false;
                                onShowAdCompleteListener.onShowAdComplete();
                                loadAd(activity);
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                appOpenAd = null;
                                isShowingAd = false;
                                onShowAdCompleteListener.onShowAdComplete();
                                loadAd(activity);
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                            }
                        });

                isShowingAd = true;
                appOpenAd.show(activity);
            } else if (SELECT_ADS.equals("ALIEN-V")) {
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
            } else {
                if (isShowingAd) {
                    return;
                }

                if (!isAdAvailable()) {
                    onShowAdCompleteListener.onShowAdComplete();
                    loadAd(activity);
                    return;
                }

                appOpenAd.setFullScreenContentCallback(
                        new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                appOpenAd = null;
                                isShowingAd = false;
                                onShowAdCompleteListener.onShowAdComplete();
                                loadAd(activity);
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                appOpenAd = null;
                                isShowingAd = false;
                                onShowAdCompleteListener.onShowAdComplete();
                                loadAd(activity);
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                            }
                        });

                isShowingAd = true;
                appOpenAd.show(activity);
            }

        }
    }
}