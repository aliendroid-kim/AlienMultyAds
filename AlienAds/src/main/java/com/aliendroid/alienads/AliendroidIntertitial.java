package com.aliendroid.alienads;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;
import com.google.ads.mediation.facebook.FacebookAdapter;
import com.google.ads.mediation.facebook.FacebookExtras;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.adunit.adapter.listener.InterstitialAdListener;
import com.ironsource.mediationsdk.adunit.adapter.utility.ErrorType;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.InterstitialListener;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.adlisteners.AdDisplayListener;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;

public class AliendroidIntertitial {
    public static InterstitialAd mInterstitialAd;
    public static MaxInterstitialAd interstitialAd;
    public static MoPubInterstitial mInterstitial;
    public static int counter = 0;
    public static AppLovinInterstitialAdDialog interstitialAdlovin;
    public static AppLovinAd loadedAd;
    public static boolean irininter = false;
    private static StartAppAd startAppAd;

    public static void LoadIntertitial(Activity activity, String selectAds, String idIntertitial, String Hpk1,
                                       String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        switch (selectAds) {
            case "ADMOB":
                Bundle extras = new FacebookExtras()
                        .setNativeBanner(true)
                        .build();
                AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                        .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                        .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                        .build();
                InterstitialAd.load(activity, idIntertitial, request,
                        new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                // The mInterstitialAd reference will be null until
                                // an ad is loaded.
                                mInterstitialAd = interstitialAd;

                                if (Hpk1.equalsIgnoreCase("log")) {
                                    Log.i("AlienLog", "Admob Interstitial Loaded ");
                                }

                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error
                                Log.i(TAG, loadAdError.getMessage());
                                mInterstitialAd = null;
                                if (Hpk1.equalsIgnoreCase("log")) {
                                    Log.e("AlienLog", "Admob Interstitial Failed to Load -> " + loadAdError.getMessage());
                                }
                            }
                        });
                break;
            case "APPLOVIN-M":
                if (idIntertitial == null) {
                    interstitialAd = new MaxInterstitialAd("qwerty1234", activity);
                    interstitialAd.loadAd();
                    interstitialAd.setListener(new MaxAdListener() {
                        @Override
                        public void onAdLoaded(MaxAd ad) {
                            if (Hpk1.equalsIgnoreCase("log")) {
                                Log.i("AlienLog", "MAX Interstitial Loaded ");
                            }
                        }

                        @Override
                        public void onAdDisplayed(MaxAd ad) {

                        }

                        @Override
                        public void onAdHidden(MaxAd ad) {

                        }

                        @Override
                        public void onAdClicked(MaxAd ad) {

                        }

                        @Override
                        public void onAdLoadFailed(String adUnitId, MaxError error) {
                            if (Hpk1.equalsIgnoreCase("log")) {
                                Log.e("AlienLog", "MAX  Interstitial Failed to Load -> " + error.getMessage());
                            }
                        }

                        @Override
                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                            if (Hpk1.equalsIgnoreCase("log")) {
                                Log.e("AlienLog", "Admob Interstitial Failed to Display -> " + error.getMessage());
                            }
                        }
                    });

                } else {
                    interstitialAd = new MaxInterstitialAd(idIntertitial, activity);
                    interstitialAd.loadAd();
                }

                break;
            case "MOPUB":
                mInterstitial = new MoPubInterstitial(activity, idIntertitial);
                mInterstitial.load();
                mInterstitial.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                    @Override
                    public void onInterstitialLoaded(MoPubInterstitial moPubInterstitial) {
                        if (Hpk1.equalsIgnoreCase("log")) {
                            Log.i("AlienLog", "Mopub Interstitial Loaded ");
                        }
                    }

                    @Override
                    public void onInterstitialFailed(MoPubInterstitial moPubInterstitial, MoPubErrorCode moPubErrorCode) {
                        if (Hpk1.equalsIgnoreCase("log")) {
                            Log.e("AlienLog", "Mopub Interstitial Failed -> " + moPubErrorCode.toString());
                        }
                    }

                    @Override
                    public void onInterstitialShown(MoPubInterstitial moPubInterstitial) {

                    }

                    @Override
                    public void onInterstitialClicked(MoPubInterstitial moPubInterstitial) {

                    }

                    @Override
                    public void onInterstitialDismissed(MoPubInterstitial moPubInterstitial) {

                    }
                });
                break;
            case "IRON":
                IronSource.isInterstitialPlacementCapped(idIntertitial);
                IronSource.loadInterstitial();
                IronSource.setInterstitialListener(new InterstitialListener() {
                    @Override
                    public void onInterstitialAdReady() {
                        if (Hpk1.equalsIgnoreCase("log")) {
                            Log.e("AlienLog", "Iron Source Interstitial Loaded ");
                        }
                    }

                    @Override
                    public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {
                        if (Hpk1.equalsIgnoreCase("log")) {
                            Log.e("AlienLog", "Iron Source Interstitial Failed to Load -> " + ironSourceError.getErrorMessage());
                        }
                    }

                    @Override
                    public void onInterstitialAdOpened() {

                    }

                    @Override
                    public void onInterstitialAdClosed() {

                    }

                    @Override
                    public void onInterstitialAdShowSucceeded() {

                    }

                    @Override
                    public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {
                        if (Hpk1.equalsIgnoreCase("log")) {
                            Log.e("AlienLog", "Iron Source Interstitial Failed to Show -> " + ironSourceError.getErrorMessage());
                        }
                    }

                    @Override
                    public void onInterstitialAdClicked() {

                    }
                });
                break;
            case "APPLOVIN-D":
                AdRequest.Builder builder = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                        .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5);
                Bundle interstitialExtras = new Bundle();
                interstitialExtras.putString("zone_id", idIntertitial);
                builder.addCustomEventExtrasBundle(AppLovinCustomEventInterstitial.class, interstitialExtras);

                AppLovinSdk.getInstance(activity).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
                    @Override
                    public void adReceived(AppLovinAd ad) {
                        loadedAd = ad;
                        if (Hpk1.equalsIgnoreCase("log")) {
                            Log.i("AlienLog", "Discovery Interstitial received ");
                        }
                        }

                    @Override
                    public void failedToReceiveAd(int errorCode) {
                        // Look at AppLovinErrorCodes.java for list of error codes.
                        if (Hpk1.equalsIgnoreCase("log")) {
                        Log.e("AlienLog", "Discovery Interstitial Failed to received -> " + errorCode);
                    }
                    }
                });
                interstitialAdlovin = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);
                break;

        }
    }

    public static void LoadIntertitialAdmob(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup, String Hpk1,
                                            String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        Bundle extras = new FacebookExtras()
                .setNativeBanner(true)
                .build();
        AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                .build();
        InterstitialAd.load(activity, idIntertitial, request,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });

        switch (selectAdsBackup) {
            case "APPLOVIN-M":
                if (idIntertitialBackup.equals("")) {
                    interstitialAd = new MaxInterstitialAd("qwerty12345", activity);
                    interstitialAd.loadAd();
                } else {
                    interstitialAd = new MaxInterstitialAd(idIntertitialBackup, activity);
                    interstitialAd.loadAd();
                }

                break;
            case "MOPUB":
                mInterstitial = new MoPubInterstitial(activity, idIntertitialBackup);
                mInterstitial.load();
                break;
            case "IRON":
                IronSource.isInterstitialPlacementCapped(idIntertitialBackup);
                IronSource.loadInterstitial();
                break;
            case "APPLOVIN-D":
                AdRequest.Builder builder = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                        .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5);
                Bundle interstitialExtras = new Bundle();
                interstitialExtras.putString("zone_id", idIntertitialBackup);
                builder.addCustomEventExtrasBundle(AppLovinCustomEventInterstitial.class, interstitialExtras);

                AppLovinSdk.getInstance(activity).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
                    @Override
                    public void adReceived(AppLovinAd ad) {
                        loadedAd = ad;
                    }

                    @Override
                    public void failedToReceiveAd(int errorCode) {
                        // Look at AppLovinErrorCodes.java for list of error codes.
                    }
                });
                interstitialAdlovin = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);
                break;

        }
    }

    public static void LoadIntertitialApplovinDis(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup
    ) {

        AdRequest.Builder builder = new AdRequest.Builder();
        Bundle interstitialExtras = new Bundle();
        interstitialExtras.putString("zone_id", idIntertitial);
        builder.addCustomEventExtrasBundle(AppLovinCustomEventInterstitial.class, interstitialExtras);

        AppLovinSdk.getInstance(activity).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
            @Override
            public void adReceived(AppLovinAd ad) {
                loadedAd = ad;
            }

            @Override
            public void failedToReceiveAd(int errorCode) {
                // Look at AppLovinErrorCodes.java for list of error codes.
            }
        });
        interstitialAdlovin = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);

        switch (selectAdsBackup) {
            case "APPLOVIN-M":
                if (idIntertitialBackup.equals("")) {
                    interstitialAd = new MaxInterstitialAd("qwerty12345", activity);
                    interstitialAd.loadAd();
                } else {
                    interstitialAd = new MaxInterstitialAd(idIntertitialBackup, activity);
                    interstitialAd.loadAd();
                }

                break;
            case "MOPUB":
                mInterstitial = new MoPubInterstitial(activity, idIntertitialBackup);
                mInterstitial.load();
                break;
            case "IRON":
                IronSource.isInterstitialPlacementCapped(idIntertitialBackup);
                IronSource.loadInterstitial();
                break;
            case "ADMOB":
                Bundle extras = new FacebookExtras()
                        .setNativeBanner(true)
                        .build();
                AdRequest request = new AdRequest.Builder()
                        .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                        .build();
                InterstitialAd.load(activity, idIntertitialBackup, request,
                        new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                // The mInterstitialAd reference will be null until
                                // an ad is loaded.
                                mInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error
                                Log.i(TAG, loadAdError.getMessage());
                                mInterstitialAd = null;
                            }
                        });
                break;

        }
    }

    public static void LoadIntertitialApplovinMax(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup) {

        interstitialAd = new MaxInterstitialAd(idIntertitial, activity);
        interstitialAd.loadAd();

        switch (selectAdsBackup) {
            case "APPLOVIN-D":
                AdRequest.Builder builder = new AdRequest.Builder();
                Bundle interstitialExtras = new Bundle();
                interstitialExtras.putString("zone_id", idIntertitialBackup);
                builder.addCustomEventExtrasBundle(AppLovinCustomEventInterstitial.class, interstitialExtras);
                AppLovinSdk.getInstance(activity).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
                    @Override
                    public void adReceived(AppLovinAd ad) {
                        loadedAd = ad;
                    }

                    @Override
                    public void failedToReceiveAd(int errorCode) {
                        // Look at AppLovinErrorCodes.java for list of error codes.
                    }
                });
                interstitialAdlovin = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);
                break;
            case "MOPUB":
                mInterstitial = new MoPubInterstitial(activity, idIntertitialBackup);
                mInterstitial.load();
                break;
            case "IRON":
                IronSource.isInterstitialPlacementCapped(idIntertitialBackup);
                IronSource.loadInterstitial();
                break;
            case "ADMOB":
                Bundle extras = new FacebookExtras()
                        .setNativeBanner(true)
                        .build();
                AdRequest request = new AdRequest.Builder()
                        .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                        .build();
                InterstitialAd.load(activity, idIntertitialBackup, request,
                        new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                // The mInterstitialAd reference will be null until
                                // an ad is loaded.
                                mInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error
                                Log.i(TAG, loadAdError.getMessage());
                                mInterstitialAd = null;
                            }
                        });
                break;

        }
    }

    public static void LoadIntertitialIron(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup) {

        IronSource.isInterstitialPlacementCapped(idIntertitial);
        IronSource.setInterstitialListener(new InterstitialListener() {
            /**
             * Invoked when Interstitial Ad is ready to be shown after load function was called.
             */
            @Override
            public void onInterstitialAdReady() {
                irininter = false;
            }

            /**
             * invoked when there is no Interstitial Ad available after calling load function.
             */
            @Override
            public void onInterstitialAdLoadFailed(IronSourceError error) {
                irininter = true;
            }

            /**
             * Invoked when the Interstitial Ad Unit is opened
             */
            @Override
            public void onInterstitialAdOpened() {
            }

            /*
             * Invoked when the ad is closed and the user is about to return to the application.
             */
            @Override
            public void onInterstitialAdClosed() {
            }

            /**
             * Invoked when Interstitial ad failed to show.
             * @param error - An object which represents the reason of showInterstitial failure.
             */
            @Override
            public void onInterstitialAdShowFailed(IronSourceError error) {

            }

            /*
             * Invoked when the end user clicked on the interstitial ad, for supported networks only.
             */
            @Override
            public void onInterstitialAdClicked() {
            }

            /** Invoked right before the Interstitial screen is about to open.
             *  NOTE - This event is available only for some of the networks.
             *  You should NOT treat this event as an interstitial impression, but rather use InterstitialAdOpenedEvent
             */
            @Override
            public void onInterstitialAdShowSucceeded() {
            }
        });
        IronSource.loadInterstitial();
        switch (selectAdsBackup) {
            case "APPLOVIN-D":
                AdRequest.Builder builder = new AdRequest.Builder();
                Bundle interstitialExtras = new Bundle();
                interstitialExtras.putString("zone_id", idIntertitialBackup);
                builder.addCustomEventExtrasBundle(AppLovinCustomEventInterstitial.class, interstitialExtras);
                AppLovinSdk.getInstance(activity).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
                    @Override
                    public void adReceived(AppLovinAd ad) {
                        loadedAd = ad;
                    }

                    @Override
                    public void failedToReceiveAd(int errorCode) {
                        // Look at AppLovinErrorCodes.java for list of error codes.
                    }
                });
                interstitialAdlovin = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);
                break;
            case "MOPUB":
                mInterstitial = new MoPubInterstitial(activity, idIntertitialBackup);
                mInterstitial.load();
                break;
            case "APPLOVIN-M":
                interstitialAd = new MaxInterstitialAd(idIntertitialBackup, activity);
                interstitialAd.loadAd();
                break;
            case "ADMOB":
                Bundle extras = new FacebookExtras()
                        .setNativeBanner(true)
                        .build();
                AdRequest request = new AdRequest.Builder()
                        .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                        .build();
                InterstitialAd.load(activity, idIntertitialBackup, request,
                        new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                // The mInterstitialAd reference will be null until
                                // an ad is loaded.
                                mInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error
                                Log.i(TAG, loadAdError.getMessage());
                                mInterstitialAd = null;
                            }
                        });
                break;

        }
    }

    public static void LoadIntertitialMopub(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup) {

        mInterstitial = new MoPubInterstitial(activity, idIntertitial);
        mInterstitial.load();
        switch (selectAdsBackup) {
            case "APPLOVIN-D":
                AdRequest.Builder builder = new AdRequest.Builder();
                Bundle interstitialExtras = new Bundle();
                interstitialExtras.putString("zone_id", idIntertitialBackup);
                builder.addCustomEventExtrasBundle(AppLovinCustomEventInterstitial.class, interstitialExtras);
                AppLovinSdk.getInstance(activity).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
                    @Override
                    public void adReceived(AppLovinAd ad) {
                        loadedAd = ad;
                    }

                    @Override
                    public void failedToReceiveAd(int errorCode) {
                        // Look at AppLovinErrorCodes.java for list of error codes.
                    }
                });
                interstitialAdlovin = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);
                break;
            case "IRON":
                IronSource.isInterstitialPlacementCapped(idIntertitialBackup);
                IronSource.loadInterstitial();
                break;
            case "APPLOVIN-M":
                interstitialAd = new MaxInterstitialAd(idIntertitialBackup, activity);
                interstitialAd.loadAd();
                break;
            case "ADMOB":
                Bundle extras = new FacebookExtras()
                        .setNativeBanner(true)
                        .build();
                AdRequest request = new AdRequest.Builder()
                        .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                        .build();
                InterstitialAd.load(activity, idIntertitialBackup, request,
                        new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                // The mInterstitialAd reference will be null until
                                // an ad is loaded.
                                mInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error
                                Log.i(TAG, loadAdError.getMessage());
                                mInterstitialAd = null;
                            }
                        });
                break;

        }
    }

    public static void LoadIntertitialStartApp(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup) {
        startAppAd = new StartAppAd(activity);
        startAppAd.loadAd(new AdEventListener() {
            @Override
            public void onReceiveAd(Ad ad) {
            }

            @Override
            public void onFailedToReceiveAd(Ad ad) {

            }
        });
        switch (selectAdsBackup) {
            case "APPLOVIN-D":
                AdRequest.Builder builder = new AdRequest.Builder();
                Bundle interstitialExtras = new Bundle();
                interstitialExtras.putString("zone_id", idIntertitialBackup);
                builder.addCustomEventExtrasBundle(AppLovinCustomEventInterstitial.class, interstitialExtras);
                AppLovinSdk.getInstance(activity).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
                    @Override
                    public void adReceived(AppLovinAd ad) {
                        loadedAd = ad;
                    }

                    @Override
                    public void failedToReceiveAd(int errorCode) {
                        // Look at AppLovinErrorCodes.java for list of error codes.
                    }
                });
                interstitialAdlovin = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);
                break;
            case "IRON":
                IronSource.isInterstitialPlacementCapped(idIntertitialBackup);
                IronSource.loadInterstitial();
                break;
            case "APPLOVIN-M":
                interstitialAd = new MaxInterstitialAd(idIntertitialBackup, activity);
                interstitialAd.loadAd();
                break;
            case "ADMOB":
                Bundle extras = new FacebookExtras()
                        .setNativeBanner(true)
                        .build();
                AdRequest request = new AdRequest.Builder()
                        .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                        .build();
                InterstitialAd.load(activity, idIntertitialBackup, request,
                        new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                // The mInterstitialAd reference will be null until
                                // an ad is loaded.
                                mInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error
                                Log.i(TAG, loadAdError.getMessage());
                                mInterstitialAd = null;
                            }
                        });
                break;
            case "MOPUB":
                mInterstitial = new MoPubInterstitial(activity, idIntertitial);
                mInterstitial.load();
                break;

        }
    }

    public static void ShowIntertitial(Activity activity, String selectAds, String idIntertitial, int interval, String Hpk1,
                                       String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        if (counter >= interval) {
            switch (selectAds) {
                case "ADMOB":
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(activity);
                        LoadIntertitial(activity, selectAds, idIntertitial, Hpk1, Hpk2, Hpk3, Hpk4, Hpk5);
                    } else {
                        LoadIntertitial(activity, selectAds, idIntertitial, Hpk1, Hpk2, Hpk3, Hpk4, Hpk5);
                    }
                    break;
                case "APPLOVIN-M":
                    if (interstitialAd.isReady()) {
                        interstitialAd.showAd();
                        interstitialAd.loadAd();
                    } else {
                        interstitialAd.loadAd();
                    }
                    break;
                case "MOPUB":
                    if (mInterstitial.isReady()) {
                        mInterstitial.show();
                        mInterstitial.load();
                    } else {
                        mInterstitial.load();
                    }
                    break;
                case "IRON":
                    IronSource.showInterstitial(idIntertitial);
                    break;
                case "STARTAPP":
                    StartAppAd.showAd(activity);
                    break;
                case "APPLOVIN-D":
                    interstitialAdlovin.showAndRender(loadedAd);
                    break;
            }
            counter = 0;
        } else {
            counter++;
        }
    }

    public static void ShowIntertitialAdmob(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                            int interval, String Hpk1,
                                            String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        if (counter >= interval) {
            if (mInterstitialAd != null) {
                mInterstitialAd.show(activity);
                LoadIntertitialAdmob(activity, selectAdsBackup, idIntertitial, idIntertitialBackup, Hpk1, Hpk2, Hpk3, Hpk4, Hpk5);
            } else {
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        if (interstitialAd.isReady()) {
                            interstitialAd.showAd();
                            interstitialAd.loadAd();
                        } else {
                            interstitialAd.loadAd();
                        }
                        break;
                    case "MOPUB":
                        if (mInterstitial.isReady()) {
                            mInterstitial.show();
                            mInterstitial.load();
                        } else {
                            mInterstitial.load();
                        }
                        break;
                    case "IRON":
                        IronSource.showInterstitial(idIntertitialBackup);
                        break;
                    case "STARTAPP":
                        StartAppAd.showAd(activity);
                        break;
                    case "APPLOVIN-D":
                        if (interstitialAdlovin != null) {
                            interstitialAdlovin.showAndRender(loadedAd);
                        }
                        break;
                }
                LoadIntertitialAdmob(activity, selectAdsBackup, idIntertitial, idIntertitialBackup, Hpk1, Hpk2, Hpk3, Hpk4, Hpk5);
            }

            counter = 0;
        } else {
            counter++;
        }
    }

    public static void ShowIntertitialApplovinDis(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                                  int interval) {
        if (counter >= interval) {
            if (interstitialAdlovin != null) {
                AppLovinAdDisplayListener listener = new AppLovinAdDisplayListener() {
                    @Override
                    public void adDisplayed(AppLovinAd ad) {

                    }

                    @Override
                    public void adHidden(AppLovinAd ad) {
                        switch (selectAdsBackup) {
                            case "APPLOVIN-M":
                                if (interstitialAd.isReady()) {
                                    interstitialAd.showAd();
                                    interstitialAd.loadAd();
                                } else {
                                    interstitialAd.loadAd();
                                }
                                break;
                            case "MOPUB":
                                if (mInterstitial.isReady()) {
                                    mInterstitial.show();
                                    mInterstitial.load();
                                } else {
                                    mInterstitial.load();
                                }
                                break;
                            case "IRON":
                                IronSource.showInterstitial(idIntertitialBackup);
                                break;
                            case "STARTAPP":
                                StartAppAd.showAd(activity);
                                break;
                            case "ADMOB":
                                if (mInterstitialAd != null) {
                                    mInterstitialAd.show(activity);
                                }
                                break;
                        }
                        LoadIntertitialApplovinDis(activity, selectAdsBackup, idIntertitial, idIntertitialBackup);
                    }
                };
                interstitialAdlovin.setAdDisplayListener(listener);
                interstitialAdlovin.showAndRender(loadedAd);
                LoadIntertitialApplovinDis(activity, selectAdsBackup, idIntertitial, idIntertitialBackup);
            }

            counter = 0;
        } else {
            counter++;
        }

    }

    public static void ShowIntertitialApplovinMax(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                                  int interval) {
        if (counter >= interval) {
            if (interstitialAd.isReady()) {
                interstitialAd.showAd();
                LoadIntertitialApplovinMax(activity, selectAdsBackup, idIntertitial, idIntertitialBackup);
            } else {
                switch (selectAdsBackup) {
                    case "APPLOVIN-D":
                        if (interstitialAdlovin != null) {
                            interstitialAdlovin.showAndRender(loadedAd);
                        }
                        break;
                    case "MOPUB":
                        if (mInterstitial.isReady()) {
                            mInterstitial.show();
                            mInterstitial.load();
                        } else {
                            mInterstitial.load();
                        }
                        break;
                    case "IRON":
                        IronSource.showInterstitial(idIntertitialBackup);
                        break;
                    case "STARTAPP":
                        StartAppAd.showAd(activity);
                        break;
                    case "ADMOB":
                        if (mInterstitialAd != null) {
                            mInterstitialAd.show(activity);
                        }
                        break;
                }
                LoadIntertitialApplovinMax(activity, selectAdsBackup, idIntertitial, idIntertitialBackup);
                interstitialAd.loadAd();
            }

            counter = 0;
        } else {
            counter++;
        }

    }

    public static void ShowIntertitialIron(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                           int interval) {
        if (counter >= interval) {
            if (irininter) {
                switch (selectAdsBackup) {
                    case "APPLOVIN-D":
                        if (interstitialAdlovin != null) {
                            interstitialAdlovin.showAndRender(loadedAd);
                        }
                        break;
                    case "MOPUB":
                        if (mInterstitial.isReady()) {
                            mInterstitial.show();
                            mInterstitial.load();
                        } else {
                            mInterstitial.load();
                        }
                        break;
                    case "APPLOVIN-M":
                        if (interstitialAd.isReady()) {
                            interstitialAd.showAd();
                        }
                        break;
                    case "STARTAPP":
                        StartAppAd.showAd(activity);
                        break;
                    case "ADMOB":
                        if (mInterstitialAd != null) {
                            mInterstitialAd.show(activity);
                        }
                        break;
                }
            } else {
                IronSource.showInterstitial(idIntertitial);
            }

            LoadIntertitialIron(activity, selectAdsBackup, idIntertitial, idIntertitialBackup);
            counter = 0;
        } else {
            counter++;
        }

    }

    public static void ShowIntertitialMopub(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                            int interval) {
        if (counter >= interval) {
            if (mInterstitial.isReady()) {
                mInterstitial.show();
            } else {
                switch (selectAdsBackup) {
                    case "APPLOVIN-D":
                        if (interstitialAdlovin != null) {
                            interstitialAdlovin.showAndRender(loadedAd);
                        }
                        break;
                    case "IRON":
                        IronSource.showInterstitial(idIntertitial);
                        break;
                    case "APPLOVIN-M":
                        if (interstitialAd.isReady()) {
                            interstitialAd.showAd();
                        }
                        break;
                    case "STARTAPP":
                        StartAppAd.showAd(activity);
                        break;
                    case "ADMOB":
                        if (mInterstitialAd != null) {
                            mInterstitialAd.show(activity);
                        }
                        break;
                }
            }
            LoadIntertitialMopub(activity, selectAdsBackup, idIntertitial, idIntertitialBackup);
            counter = 0;
        } else {
            counter++;
        }

    }


    public static void ShowIntertitialSartApp(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                              int interval) {
        if (counter >= interval) {
            startAppAd.showAd();
            startAppAd.showAd(new AdDisplayListener() {
                @Override
                public void adHidden(Ad ad) {
                }

                @Override
                public void adDisplayed(Ad ad) {
                }

                @Override
                public void adClicked(Ad ad) {
                }

                @Override
                public void adNotDisplayed(Ad ad) {
                    switch (selectAdsBackup) {
                        case "APPLOVIN-D":
                            if (interstitialAdlovin != null) {
                                interstitialAdlovin.showAndRender(loadedAd);
                            }
                            break;
                        case "IRON":
                            IronSource.showInterstitial(idIntertitial);
                            break;
                        case "APPLOVIN-M":
                            if (interstitialAd.isReady()) {
                                interstitialAd.showAd();
                            }
                            break;
                        case "MOPUB":
                            if (mInterstitial.isReady()) {
                                mInterstitial.show();
                            }
                            break;
                        case "ADMOB":
                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(activity);
                            }
                            break;
                    }
                }
            });

            LoadIntertitialStartApp(activity, selectAdsBackup, idIntertitial, idIntertitialBackup);
            counter = 0;
        } else {
            counter++;
        }

    }
}
