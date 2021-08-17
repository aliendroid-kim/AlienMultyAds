package com.aliendroid.alienads;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.sdk.AppLovinAd;
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
import com.mopub.mobileads.MoPubInterstitial;
import com.startapp.sdk.adsbase.StartAppAd;

public class AliendroidIntertitial {
    public static InterstitialAd mInterstitialAd;
    public static MaxInterstitialAd interstitialAd;
    public static MoPubInterstitial mInterstitial;
    public static int counter = 0;
    public static AppLovinInterstitialAdDialog interstitialAdlovin;
    public static AppLovinAd loadedAd;

    public static void LoadIntertitial(Activity activity, String selectAds, String idIntertitial, String Hpk1,
                                       String Hpk2, String Hpk3, String Hpk4, String Hpk5 ) {
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
            case "APPLOVIN-M":
                if (idIntertitial==null){
                    interstitialAd = new MaxInterstitialAd("qwerty1234", activity);
                    interstitialAd.loadAd();
                } else {
                    interstitialAd = new MaxInterstitialAd(idIntertitial, activity);
                    interstitialAd.loadAd();
                }

                break;
            case "MOPUB":
                mInterstitial = new MoPubInterstitial(activity, idIntertitial);
                mInterstitial.load();
                break;
            case "IRON":
                IronSource.isInterstitialPlacementCapped(idIntertitial);
                IronSource.loadInterstitial();
                break;
            case "APPLOVIN-D":
                AdRequest.Builder builder = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                        .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5);
                Bundle interstitialExtras = new Bundle();
                interstitialExtras.putString( "zone_id", idIntertitial );
                builder.addCustomEventExtrasBundle( AppLovinCustomEventInterstitial.class, interstitialExtras );

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

    public static void LoadIntertitialAdmob(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup, String Hpk1,
                                            String Hpk2, String Hpk3, String Hpk4, String Hpk5 ) {
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
                if (idIntertitialBackup.equals("")){
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
                interstitialExtras.putString( "zone_id", idIntertitialBackup );
                builder.addCustomEventExtrasBundle( AppLovinCustomEventInterstitial.class, interstitialExtras );

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

    public static void ShowIntertitial(Activity activity, String selectAds, String idIntertitial, int interval,String Hpk1,
                                       String Hpk2, String Hpk3, String Hpk4, String Hpk5 ) {
        if (counter >= interval) {
            switch (selectAds) {
                case "ADMOB":
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(activity);
                        LoadIntertitial(activity, selectAds, idIntertitial, Hpk1, Hpk2, Hpk3, Hpk4, Hpk5);
                    } else {
                        LoadIntertitial(activity, selectAds, idIntertitial,Hpk1, Hpk2, Hpk3, Hpk4, Hpk5);
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
                    interstitialAdlovin.showAndRender( loadedAd );
                    break;
            }
            counter = 0;
        } else {
            counter++;
        }
    }

    public static void ShowIntertitialBackup(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                             int interval,String Hpk1,
                                             String Hpk2, String Hpk3, String Hpk4, String Hpk5 ) {
        if (counter >= interval) {

            if (mInterstitialAd != null) {
                mInterstitialAd.show(activity);
                LoadIntertitial(activity, "ADMOB", idIntertitial, Hpk1, Hpk2, Hpk3, Hpk4, Hpk5);
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
                        interstitialAdlovin.showAndRender( loadedAd );
                        break;
                }
                LoadIntertitial(activity, "ADMOB", idIntertitial,Hpk1, Hpk2, Hpk3, Hpk4, Hpk5);
            }

            counter = 0;
        } else {
            counter++;
        }
    }
}
