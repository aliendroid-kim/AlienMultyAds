package com.aliendroid.alienads;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.aliendroid.alienads.config.AppLovinCustomEventInterstitial;
import com.aliendroid.alienads.interfaces.interstitial.admob.OnFullScreenContentCallbackAdmob;
import com.aliendroid.alienads.interfaces.interstitial.load.OnLoadInterstitialAdmob;
import com.aliendroid.alienads.interfaces.interstitial.load.OnLoadInterstitialAlienMediation;
import com.aliendroid.alienads.interfaces.interstitial.load.OnLoadInterstitialAlienView;
import com.aliendroid.alienads.interfaces.interstitial.load.OnLoadInterstitialApplovinDiscovery;
import com.aliendroid.alienads.interfaces.interstitial.load.OnLoadInterstitialApplovinMax;
import com.aliendroid.alienads.interfaces.interstitial.load.OnLoadInterstitialFacebook;
import com.aliendroid.alienads.interfaces.interstitial.load.OnLoadInterstitialGoogle;
import com.aliendroid.alienads.interfaces.interstitial.load.OnLoadInterstitialIronSource;
import com.aliendroid.alienads.interfaces.interstitial.load.OnLoadInterstitialStartApp;
import com.aliendroid.alienads.interfaces.interstitial.show.OnShowInterstitialAdmob;
import com.aliendroid.alienads.interfaces.interstitial.show.OnShowInterstitialAlienMediation;
import com.aliendroid.alienads.interfaces.interstitial.show.OnShowInterstitialAlienView;
import com.aliendroid.alienads.interfaces.interstitial.show.OnShowInterstitialApplovinDiscovery;
import com.aliendroid.alienads.interfaces.interstitial.show.OnShowInterstitialApplovinMax;
import com.aliendroid.alienads.interfaces.interstitial.show.OnShowInterstitialFacebook;
import com.aliendroid.alienads.interfaces.interstitial.show.OnShowInterstitialGoogle;
import com.aliendroid.alienads.interfaces.interstitial.show.OnShowInterstitialIronSource;
import com.aliendroid.alienads.interfaces.interstitial.show.OnShowInterstitialStartApp;
import com.aliendroid.sdkads.interfaces.OnLoadInterstitialMediation;
import com.aliendroid.sdkads.interfaces.OnLoadInterstitialView;
import com.aliendroid.sdkads.interfaces.OnShowInterstitial;
import com.aliendroid.sdkads.interfaces.OnShowInterstitialView;
import com.aliendroid.sdkads.type.mediation.AlienMediationAds;
import com.aliendroid.sdkads.type.view.AlienViewAds;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAdLoadCallback;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.InterstitialListener;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.adlisteners.AdDisplayListener;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;

public class AliendroidIntertitial {
    public static InterstitialAd mInterstitialAd;
    public static AdManagerInterstitialAd mAdManagerInterstitialAd;
    public static MaxInterstitialAd interstitialAd;
    public static com.facebook.ads.InterstitialAd FBinterstitialAd;
    public static int counter = 0;
    public static AppLovinInterstitialAdDialog interstitialAdlovin;
    public static AppLovinAd loadedAd;
    public static boolean irininter = false;
    private static StartAppAd startAppAd;
    public static boolean SHOW_ALIEN_VIEW=false;
    public static OnLoadInterstitialAdmob onLoadInterstitialAdmob;
    public static OnShowInterstitialAdmob onShowInterstitialAdmob;

    public static OnShowInterstitialGoogle onShowInterstitialGoogle;
    public static OnLoadInterstitialGoogle onLoadInterstitialGoogle;

    public static OnLoadInterstitialFacebook onLoadInterstitialFacebook;
    public static OnShowInterstitialFacebook onShowInterstitialFacebook;

    public static OnLoadInterstitialApplovinDiscovery onLoadInterstitialApplovinDiscovery;
    public static OnShowInterstitialApplovinDiscovery onShowInterstitialApplovinDiscovery;

    public static OnLoadInterstitialApplovinMax onLoadInterstitialApplovinMax;
    public static OnShowInterstitialApplovinMax onShowInterstitialApplovinMax;

    public static OnLoadInterstitialIronSource onLoadInterstitialIronSource;
    public static OnShowInterstitialIronSource onShowInterstitialIronSource;

    public static OnLoadInterstitialStartApp onLoadInterstitialStartApp;
    public static OnShowInterstitialStartApp onShowInterstitialStartApp;

    public static OnLoadInterstitialAlienMediation onLoadInterstitialAlienMediation;
    public static OnLoadInterstitialAlienView onLoadInterstitialAlienView;

    public static OnShowInterstitialAlienMediation onShowInterstitialAlienMediation;
    public static OnShowInterstitialAlienView onShowInterstitialAlienView;

    public static OnFullScreenContentCallbackAdmob onFullScreenContentCallbackAdmob;

    public static void LoadIntertitialUnity(Activity activity, String selectAds, String idIntertitial, String idBackupIntertitial) {

    }


    public static void LoadIntertitialAdmob(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup, String Hpk1,
                                            String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                .build();

        InterstitialAd.load(activity,idIntertitial, request,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        if (onLoadInterstitialAdmob!=null) {
                            onLoadInterstitialAdmob.onInterstitialAdLoaded();
                        }
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");

                    }
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        if (onLoadInterstitialAdmob!=null) {
                            onLoadInterstitialAdmob.onInterstitialAdFailedToLoad("");
                        }
                        Log.d(TAG, loadAdError.toString());
                        mInterstitialAd = null;

                    }
                });
        switch (selectAdsBackup) {
            case "APPLOVIN-M":
                interstitialAd = new MaxInterstitialAd(idIntertitialBackup, activity);
                interstitialAd.loadAd();
                break;
            case "MOPUB":
            case "UNITY":

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
            case "FACEBOOK":
                FBinterstitialAd = new com.facebook.ads.InterstitialAd(activity, idIntertitialBackup);
                FBinterstitialAd.loadAd();
                break;
            case "ALIEN-V":
                AlienViewAds.Interstitial(activity, idIntertitialBackup);
                AlienViewAds.onLoadInterstitialView = new OnLoadInterstitialView() {
                    @Override
                    public void onInterstitialAdLoaded() {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdLoaded();
                        }
                    }

                    @Override
                    public void onInterstitialAdClosed() {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdClosed();
                        }
                    }

                    @Override
                    public void onInterstitialAdClicked() {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdClicked();
                        }
                    }

                    @Override
                    public void onInterstitialAdFailedToLoad(String error) {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdFailedToLoad();
                        }
                    }
                };
                break;
            case "ALIEN-M":
                AlienMediationAds.LoadInterstitial(activity, idIntertitialBackup);
                break;

        }
    }

    public static void LoadIntertitialGoogleAds(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup) {
        AdManagerAdRequest adRequest =
                new AdManagerAdRequest.Builder()
                        .build();

        AdManagerInterstitialAd.load(activity, idIntertitial, adRequest,
                new AdManagerInterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitialAd) {
                        mAdManagerInterstitialAd = interstitialAd;
                        mAdManagerInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdClicked() {
                                Log.d(TAG, "Ad was clicked.");
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                Log.d(TAG, "Ad dismissed fullscreen content.");
                                mAdManagerInterstitialAd = null;
                            }

                            @Override
                            public void onAdImpression() {
                                Log.d(TAG, "Ad recorded an impression.");
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                Log.d(TAG, "Ad showed fullscreen content.");
                            }
                        });
                        Log.i(TAG, "onAdLoaded");
                        if (onLoadInterstitialGoogle != null) {
                            onLoadInterstitialGoogle.onInterstitialAdLoaded();
                        }
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.i(TAG, loadAdError.getMessage());
                        mAdManagerInterstitialAd = null;
                        if (onLoadInterstitialGoogle != null) {
                            onLoadInterstitialGoogle.onInterstitialAdFailedToLoad("");
                        }
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
            case "UNITY":

                break;
            case "IRON":
                IronSource.isInterstitialPlacementCapped(idIntertitialBackup);
                IronSource.loadInterstitial();
                break;
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
            case "FACEBOOK":
                FBinterstitialAd = new com.facebook.ads.InterstitialAd(activity, idIntertitialBackup);
                FBinterstitialAd.loadAd();
                break;
            case "ALIEN-V":
                AlienViewAds.Interstitial(activity, idIntertitialBackup);
                AlienViewAds.onLoadInterstitialView = new OnLoadInterstitialView() {
                    @Override
                    public void onInterstitialAdLoaded() {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdLoaded();
                        }
                    }

                    @Override
                    public void onInterstitialAdClosed() {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdClosed();
                        }
                    }

                    @Override
                    public void onInterstitialAdClicked() {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdClicked();
                        }
                    }

                    @Override
                    public void onInterstitialAdFailedToLoad(String error) {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdFailedToLoad();
                        }
                    }
                };
                break;
            case "ALIEN-M":
                AlienMediationAds.LoadInterstitial(activity, idIntertitialBackup);
                break;


        }
    }

    public static void LoadIntertitialFAN(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup) {
        FBinterstitialAd = new com.facebook.ads.InterstitialAd(activity, idIntertitial);
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(com.facebook.ads.Ad ad) {
                if (onLoadInterstitialFacebook != null) {
                    onLoadInterstitialFacebook.onInterstitialDisplayed();
                }
            }

            @Override
            public void onInterstitialDismissed(com.facebook.ads.Ad ad) {
                if (onLoadInterstitialFacebook != null) {
                    onLoadInterstitialFacebook.onInterstitialDismissed();
                }
            }

            @Override
            public void onError(com.facebook.ads.Ad ad, AdError adError) {
                if (onLoadInterstitialFacebook != null) {
                    onLoadInterstitialFacebook.onError();
                }
            }

            @Override
            public void onAdLoaded(com.facebook.ads.Ad ad) {
                if (onLoadInterstitialFacebook != null) {
                    onLoadInterstitialFacebook.onAdLoaded();
                }
            }

            @Override
            public void onAdClicked(com.facebook.ads.Ad ad) {
                if (onLoadInterstitialFacebook != null) {
                    onLoadInterstitialFacebook.onAdClicked();
                }
            }

            @Override
            public void onLoggingImpression(com.facebook.ads.Ad ad) {
                if (onLoadInterstitialFacebook != null) {
                    onLoadInterstitialFacebook.onLoggingImpression();
                }
            }
        };
        FBinterstitialAd.loadAd(
                FBinterstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());

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
            case "UNITY":

                break;
            case "IRON":
                IronSource.isInterstitialPlacementCapped(idIntertitialBackup);
                IronSource.loadInterstitial();
                break;
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
                    }
                });
                interstitialAdlovin = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);
                break;
            case "ADMOB":
                AdRequest request = new AdRequest.Builder()
                        .build();
                InterstitialAd.load(activity, idIntertitialBackup, request,
                        new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
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
            case "GOOGLE-ADS":
                AdManagerAdRequest adRequest =
                        new AdManagerAdRequest.Builder()
                                .build();

                AdManagerInterstitialAd.load(activity, idIntertitialBackup, adRequest,
                        new AdManagerInterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitialAd) {
                                mAdManagerInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error
                                Log.i(TAG, loadAdError.getMessage());
                                mAdManagerInterstitialAd = null;
                            }
                        });

                break;
            case "ALIEN-V":
                AlienViewAds.Interstitial(activity, idIntertitialBackup);
                AlienViewAds.onLoadInterstitialView = new OnLoadInterstitialView() {
                    @Override
                    public void onInterstitialAdLoaded() {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdLoaded();
                        }
                    }

                    @Override
                    public void onInterstitialAdClosed() {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdClosed();
                        }
                    }

                    @Override
                    public void onInterstitialAdClicked() {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdClicked();
                        }
                    }

                    @Override
                    public void onInterstitialAdFailedToLoad(String error) {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdFailedToLoad();
                        }
                    }
                };
                break;
            case "ALIEN-M":
                AlienMediationAds.LoadInterstitial(activity, idIntertitialBackup);
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
                if (onLoadInterstitialApplovinDiscovery != null) {
                    onLoadInterstitialApplovinDiscovery.adReceived();
                }
            }

            @Override
            public void failedToReceiveAd(int errorCode) {
                if (onLoadInterstitialApplovinDiscovery != null) {
                    onLoadInterstitialApplovinDiscovery.failedToReceiveAd("");
                }
            }
        });
        interstitialAdlovin = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);

        switch (selectAdsBackup) {
            case "APPLOVIN-M":
                interstitialAd = new MaxInterstitialAd(idIntertitialBackup, activity);
                interstitialAd.loadAd();
                break;
            case "MOPUB":
            case "UNITY":

                break;
            case "IRON":
                IronSource.isInterstitialPlacementCapped(idIntertitialBackup);
                IronSource.loadInterstitial();
                break;
            case "ADMOB":
                AdRequest request = new AdRequest.Builder()
                        .build();
                InterstitialAd.load(activity, idIntertitialBackup, request,
                        new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
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
            case "FACEBOOK":
                FBinterstitialAd = new com.facebook.ads.InterstitialAd(activity, idIntertitialBackup);
                FBinterstitialAd.loadAd();
                break;
            case "GOOGLE-ADS":
                AdManagerAdRequest adRequest =
                        new AdManagerAdRequest.Builder()
                                .build();

                AdManagerInterstitialAd.load(activity, idIntertitialBackup, adRequest,
                        new AdManagerInterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitialAd) {
                                mAdManagerInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                Log.i(TAG, loadAdError.getMessage());
                                mAdManagerInterstitialAd = null;
                            }
                        });

                break;
            case "ALIEN-V":
                AlienViewAds.Interstitial(activity, idIntertitialBackup);
                AlienViewAds.onLoadInterstitialView = new OnLoadInterstitialView() {
                    @Override
                    public void onInterstitialAdLoaded() {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdLoaded();
                        }
                    }

                    @Override
                    public void onInterstitialAdClosed() {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdClosed();
                        }
                    }

                    @Override
                    public void onInterstitialAdClicked() {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdClicked();
                        }
                    }

                    @Override
                    public void onInterstitialAdFailedToLoad(String error) {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdFailedToLoad();
                        }
                    }
                };
                break;
            case "ALIEN-M":
                AlienMediationAds.LoadInterstitial(activity, idIntertitialBackup);
                break;

        }
    }

    public static void LoadIntertitialApplovinDisHPK(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                                     String HPK1,
                                                     String HPK2, String HPK3, String HPK4, String HPK5
    ) {

        AdRequest.Builder builder = new AdRequest.Builder().addKeyword(HPK1).addKeyword(HPK2).addKeyword(HPK3).addKeyword(HPK4).addKeyword(HPK5);
        Bundle interstitialExtras = new Bundle();
        interstitialExtras.putString("zone_id", idIntertitial);
        builder.addCustomEventExtrasBundle(AppLovinCustomEventInterstitial.class, interstitialExtras);

        AppLovinSdk.getInstance(activity).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
            @Override
            public void adReceived(AppLovinAd ad) {
                loadedAd = ad;
                if (onLoadInterstitialApplovinDiscovery != null) {
                    onLoadInterstitialApplovinDiscovery.adReceived();
                }
            }

            @Override
            public void failedToReceiveAd(int errorCode) {
                if (onLoadInterstitialApplovinDiscovery != null) {
                    onLoadInterstitialApplovinDiscovery.failedToReceiveAd("");
                }
            }
        });
        interstitialAdlovin = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);

        switch (selectAdsBackup) {
            case "APPLOVIN-M":
                interstitialAd = new MaxInterstitialAd(idIntertitialBackup, activity);
                interstitialAd.loadAd();
                break;
            case "MOPUB":
            case "UNITY":

                break;
            case "IRON":
                IronSource.isInterstitialPlacementCapped(idIntertitialBackup);
                IronSource.loadInterstitial();
                break;
            case "ADMOB":
                AdRequest request = new AdRequest.Builder()
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
            case "FACEBOOK":
                FBinterstitialAd = new com.facebook.ads.InterstitialAd(activity, idIntertitialBackup);
                FBinterstitialAd.loadAd();
                break;
            case "GOOGLE-ADS":
                AdManagerAdRequest adRequest =
                        new AdManagerAdRequest.Builder()
                                .build();

                AdManagerInterstitialAd.load(activity, idIntertitialBackup, adRequest,
                        new AdManagerInterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitialAd) {
                                // The mAdManagerInterstitialAd reference will be null until
                                // an ad is loaded.
                                mAdManagerInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error
                                Log.i(TAG, loadAdError.getMessage());
                                mAdManagerInterstitialAd = null;
                            }
                        });

                break;
            case "ALIEN-V":
                AlienViewAds.Interstitial(activity, idIntertitialBackup);
                AlienViewAds.onLoadInterstitialView = new OnLoadInterstitialView() {
                    @Override
                    public void onInterstitialAdLoaded() {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdLoaded();
                        }
                    }

                    @Override
                    public void onInterstitialAdClosed() {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdClosed();
                        }
                    }

                    @Override
                    public void onInterstitialAdClicked() {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdClicked();
                        }
                    }

                    @Override
                    public void onInterstitialAdFailedToLoad(String error) {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdFailedToLoad();
                        }
                    }
                };
                break;
            case "ALIEN-M":
                AlienMediationAds.LoadInterstitial(activity, idIntertitialBackup);
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
                        if (onLoadInterstitialApplovinMax != null) {
                            onLoadInterstitialApplovinMax.adReceived();
                        }
                    }

                    @Override
                    public void failedToReceiveAd(int errorCode) {
                        if (onLoadInterstitialApplovinMax != null) {
                            onLoadInterstitialApplovinMax.failedToReceiveAd("");
                        }
                    }
                });
                interstitialAdlovin = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);
                break;
            case "MOPUB":
            case "UNITY":

                break;
            case "IRON":
                IronSource.isInterstitialPlacementCapped(idIntertitialBackup);
                IronSource.loadInterstitial();
                break;
            case "ADMOB":


                AdRequest request = new AdRequest.Builder()
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
            case "GOOGLE-ADS":
                AdManagerAdRequest adRequest =
                        new AdManagerAdRequest.Builder()
                                .build();

                AdManagerInterstitialAd.load(activity, idIntertitialBackup, adRequest,
                        new AdManagerInterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitialAd) {
                                // The mAdManagerInterstitialAd reference will be null until
                                // an ad is loaded.
                                mAdManagerInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error
                                Log.i(TAG, loadAdError.getMessage());
                                mAdManagerInterstitialAd = null;
                            }
                        });

                break;
            case "FACEBOOK":
                FBinterstitialAd = new com.facebook.ads.InterstitialAd(activity, idIntertitialBackup);
                FBinterstitialAd.loadAd();
                break;
            case "ALIEN-V":
                AlienViewAds.Interstitial(activity, idIntertitialBackup);
                AlienViewAds.onLoadInterstitialView = new OnLoadInterstitialView() {
                    @Override
                    public void onInterstitialAdLoaded() {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdLoaded();
                        }
                    }

                    @Override
                    public void onInterstitialAdClosed() {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdClosed();
                        }
                    }

                    @Override
                    public void onInterstitialAdClicked() {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdClicked();
                        }
                    }

                    @Override
                    public void onInterstitialAdFailedToLoad(String error) {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdFailedToLoad();
                        }
                    }
                };
                break;
            case "ALIEN-M":
                AlienMediationAds.LoadInterstitial(activity, idIntertitialBackup);
                break;


        }
    }

    public static void LoadIntertitialIron(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup) {

        IronSource.isInterstitialPlacementCapped(idIntertitial);
        IronSource.setInterstitialListener(new InterstitialListener() {
            @Override
            public void onInterstitialAdReady() {
                irininter = false;
                if (onLoadInterstitialIronSource != null) {
                    onLoadInterstitialIronSource.onInterstitialAdReady();
                }
            }

            @Override
            public void onInterstitialAdLoadFailed(IronSourceError error) {
                irininter = true;
                if (onLoadInterstitialIronSource != null) {
                    onLoadInterstitialIronSource.onInterstitialAdLoadFailed();
                }
            }

            @Override
            public void onInterstitialAdOpened() {
                if (onLoadInterstitialIronSource != null) {
                    onLoadInterstitialIronSource.onInterstitialAdOpened();
                }
            }

            @Override
            public void onInterstitialAdClosed() {
                if (onLoadInterstitialIronSource != null) {
                    onLoadInterstitialIronSource.onInterstitialAdClosed();
                }
            }

            @Override
            public void onInterstitialAdShowFailed(IronSourceError error) {
                if (onLoadInterstitialIronSource != null) {
                    onLoadInterstitialIronSource.onInterstitialAdLoadFailed();
                }
            }

            @Override
            public void onInterstitialAdClicked() {
                if (onLoadInterstitialIronSource != null) {
                    onLoadInterstitialIronSource.onInterstitialAdClicked();
                }
            }

            @Override
            public void onInterstitialAdShowSucceeded() {
                if (onLoadInterstitialIronSource != null) {
                    onLoadInterstitialIronSource.onInterstitialAdShowSucceeded();
                }
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
            case "UNITY":

                break;
            case "APPLOVIN-M":
                interstitialAd = new MaxInterstitialAd(idIntertitialBackup, activity);
                interstitialAd.loadAd();
                break;
            case "ADMOB":
                AdRequest request = new AdRequest.Builder()
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
            case "GOOGLE-ADS":
                AdManagerAdRequest adRequest =
                        new AdManagerAdRequest.Builder()
                                .build();

                AdManagerInterstitialAd.load(activity, idIntertitialBackup, adRequest,
                        new AdManagerInterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitialAd) {
                                // The mAdManagerInterstitialAd reference will be null until
                                // an ad is loaded.
                                mAdManagerInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error
                                Log.i(TAG, loadAdError.getMessage());
                                mAdManagerInterstitialAd = null;
                            }
                        });

                break;
            case "FACEBOOK":
                FBinterstitialAd = new com.facebook.ads.InterstitialAd(activity, idIntertitialBackup);
                FBinterstitialAd.loadAd();
                break;
            case "ALIEN-V":
                AlienViewAds.Interstitial(activity, idIntertitialBackup);
                AlienViewAds.onLoadInterstitialView = new OnLoadInterstitialView() {
                    @Override
                    public void onInterstitialAdLoaded() {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdLoaded();
                        }
                    }

                    @Override
                    public void onInterstitialAdClosed() {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdClosed();
                        }
                    }

                    @Override
                    public void onInterstitialAdClicked() {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdClicked();
                        }
                    }

                    @Override
                    public void onInterstitialAdFailedToLoad(String error) {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdFailedToLoad();
                        }
                    }
                };
                break;
            case "ALIEN-M":
                AlienMediationAds.LoadInterstitial(activity, idIntertitialBackup);
                break;

        }
    }

    public static void LoadIntertitialMopub(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup) {
    }

    public static void LoadIntertitialStartApp(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup) {
        startAppAd = new StartAppAd(activity);
        startAppAd.loadAd(new AdEventListener() {
            @Override
            public void onReceiveAd(Ad ad) {
                if (onLoadInterstitialStartApp != null) {
                    onLoadInterstitialStartApp.onReceiveAd();
                }

            }

            @Override
            public void onFailedToReceiveAd(Ad ad) {
                if (onLoadInterstitialStartApp != null) {
                    onLoadInterstitialStartApp.onFailedToReceiveAd("");
                }
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


                AdRequest request = new AdRequest.Builder()
                        .build();
                InterstitialAd.load(activity, idIntertitialBackup, request,
                        new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                mInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                Log.i(TAG, loadAdError.getMessage());
                                mInterstitialAd = null;
                            }
                        });
                break;
            case "MOPUB":
            case "UNITY":

                break;
            case "FACEBOOK":
                FBinterstitialAd = new com.facebook.ads.InterstitialAd(activity, idIntertitialBackup);
                FBinterstitialAd.loadAd();
                break;
            case "GOOGLE-ADS":
                AdManagerAdRequest adRequest =
                        new AdManagerAdRequest.Builder()
                                .build();

                AdManagerInterstitialAd.load(activity, idIntertitialBackup, adRequest,
                        new AdManagerInterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitialAd) {
                                mAdManagerInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                Log.i(TAG, loadAdError.getMessage());
                                mAdManagerInterstitialAd = null;
                            }
                        });
                break;
            case "ALIEN-V":
                AlienViewAds.Interstitial(activity, idIntertitialBackup);
                AlienViewAds.onLoadInterstitialView = new OnLoadInterstitialView() {
                    @Override
                    public void onInterstitialAdLoaded() {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdLoaded();
                        }
                    }

                    @Override
                    public void onInterstitialAdClosed() {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdClosed();
                        }
                    }

                    @Override
                    public void onInterstitialAdClicked() {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdClicked();
                        }
                    }

                    @Override
                    public void onInterstitialAdFailedToLoad(String error) {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdFailedToLoad();
                        }
                    }
                };
                break;
            case "ALIEN-M":
                AlienMediationAds.LoadInterstitial(activity, idIntertitialBackup);
                break;

        }
    }

    public static void LoadIntertitialAlienView(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup) {
        AlienViewAds.Interstitial(activity, idIntertitial);
        AlienViewAds.onLoadInterstitialView = new OnLoadInterstitialView() {
            @Override
            public void onInterstitialAdLoaded() {
                if (onLoadInterstitialAlienView!=null){
                    onLoadInterstitialAlienView.onInterstitialAdLoaded();
                }
            }

            @Override
            public void onInterstitialAdClosed() {
                if (onLoadInterstitialAlienView!=null){
                    onLoadInterstitialAlienView.onInterstitialAdClosed();
                }
            }

            @Override
            public void onInterstitialAdClicked() {
                if (onLoadInterstitialAlienView!=null){
                    onLoadInterstitialAlienView.onInterstitialAdClicked();
                }
            }

            @Override
            public void onInterstitialAdFailedToLoad(String error) {
                if (onLoadInterstitialAlienView!=null){
                    onLoadInterstitialAlienView.onInterstitialAdFailedToLoad();
                }
            }
        };
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
                AdRequest request = new AdRequest.Builder()
                        .build();
                InterstitialAd.load(activity, idIntertitialBackup, request,
                        new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                mInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                Log.i(TAG, loadAdError.getMessage());
                                mInterstitialAd = null;
                            }
                        });
                break;
            case "MOPUB":
            case "UNITY":

                break;
            case "FACEBOOK":
                FBinterstitialAd = new com.facebook.ads.InterstitialAd(activity, idIntertitialBackup);
                FBinterstitialAd.loadAd();
                break;
            case "GOOGLE-ADS":
                AdManagerAdRequest adRequest =
                        new AdManagerAdRequest.Builder()
                                .build();

                AdManagerInterstitialAd.load(activity, idIntertitialBackup, adRequest,
                        new AdManagerInterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitialAd) {
                                mAdManagerInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                Log.i(TAG, loadAdError.getMessage());
                                mAdManagerInterstitialAd = null;
                            }
                        });

                break;
            case "STARTAPP":
                startAppAd = new StartAppAd(activity);
                startAppAd.loadAd(new AdEventListener() {
                    @Override
                    public void onReceiveAd(Ad ad) {
                        if (onLoadInterstitialStartApp != null) {
                            onLoadInterstitialStartApp.onReceiveAd();
                        }

                    }

                    @Override
                    public void onFailedToReceiveAd(Ad ad) {
                        if (onLoadInterstitialStartApp != null) {
                            onLoadInterstitialStartApp.onFailedToReceiveAd("");
                        }
                    }
                });
                break;
            case "ALIEN-M":
                AlienMediationAds.LoadInterstitial(activity, idIntertitialBackup);
                break;

        }
    }

    public static void LoadIntertitialAlienMediation(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup) {
        AlienMediationAds.LoadInterstitial(activity, idIntertitial);
        AlienMediationAds.onLoadInterstitialMediation = new OnLoadInterstitialMediation() {
            @Override
            public void onInterstitialAdLoaded() {
                if (onLoadInterstitialAlienMediation!=null){
                    onLoadInterstitialAlienMediation.onInterstitialAdLoaded();
                }
            }

            @Override
            public void onInterstitialAdClosed() {
                if (onLoadInterstitialAlienMediation!=null){
                    onLoadInterstitialAlienMediation.onInterstitialAdClosed();
                }
            }

            @Override
            public void onInterstitialAdClicked() {
                if (onLoadInterstitialAlienMediation!=null){
                    onLoadInterstitialAlienMediation.onInterstitialAdClicked();
                }
            }

            @Override
            public void onInterstitialAdFailedToLoad(String error) {
                if (onLoadInterstitialAlienMediation!=null){
                    onLoadInterstitialAlienMediation.onInterstitialAdFailedToLoad();
                }
            }
        };
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


                AdRequest request = new AdRequest.Builder()
                        .build();
                InterstitialAd.load(activity, idIntertitialBackup, request,
                        new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                mInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                Log.i(TAG, loadAdError.getMessage());
                                mInterstitialAd = null;
                            }
                        });
                break;
            case "MOPUB":
            case "UNITY":

                break;
            case "FACEBOOK":
                FBinterstitialAd = new com.facebook.ads.InterstitialAd(activity, idIntertitialBackup);
                FBinterstitialAd.loadAd();
                break;
            case "GOOGLE-ADS":
                AdManagerAdRequest adRequest =
                        new AdManagerAdRequest.Builder()
                                .build();

                AdManagerInterstitialAd.load(activity, idIntertitialBackup, adRequest,
                        new AdManagerInterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitialAd) {
                                mAdManagerInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                Log.i(TAG, loadAdError.getMessage());
                                mAdManagerInterstitialAd = null;
                            }
                        });

                break;
            case "STARTAPP":
                startAppAd = new StartAppAd(activity);
                startAppAd.loadAd(new AdEventListener() {
                    @Override
                    public void onReceiveAd(Ad ad) {
                        if (onLoadInterstitialStartApp != null) {
                            onLoadInterstitialStartApp.onReceiveAd();
                        }

                    }

                    @Override
                    public void onFailedToReceiveAd(Ad ad) {
                        if (onLoadInterstitialStartApp != null) {
                            onLoadInterstitialStartApp.onFailedToReceiveAd("");
                        }
                    }
                });
                break;
            case "ALIEN-V":
                AlienViewAds.Interstitial(activity, idIntertitialBackup);
                AlienViewAds.onLoadInterstitialView = new OnLoadInterstitialView() {
                    @Override
                    public void onInterstitialAdLoaded() {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdLoaded();
                        }
                    }

                    @Override
                    public void onInterstitialAdClosed() {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdClosed();
                        }
                    }

                    @Override
                    public void onInterstitialAdClicked() {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdClicked();
                        }
                    }

                    @Override
                    public void onInterstitialAdFailedToLoad(String error) {
                        if (onLoadInterstitialAlienView!=null){
                            onLoadInterstitialAlienView.onInterstitialAdFailedToLoad();
                        }
                    }
                };
                break;
        }
    }

    public static void ShowIntertitialAdmob(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                            int interval, String Hpk1,
                                            String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        if (counter >= interval) {
            if (mInterstitialAd != null) {
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                    @Override
                    public void onAdClicked() {
                        if (onFullScreenContentCallbackAdmob!=null){
                            onFullScreenContentCallbackAdmob.onAdClicked();
                        }

                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        if (onFullScreenContentCallbackAdmob!=null){
                            onFullScreenContentCallbackAdmob.onAdDismissedFullScreenContent();
                        }
                        mInterstitialAd = null;
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                        if (onFullScreenContentCallbackAdmob!=null){
                            onFullScreenContentCallbackAdmob.onAdFailedToShowFullScreenContent();
                        }
                        mInterstitialAd = null;
                    }

                    @Override
                    public void onAdImpression() {
                        // Called when an impression is recorded for an ad.
                        if (onFullScreenContentCallbackAdmob!=null){
                            onFullScreenContentCallbackAdmob.onAdImpression();
                        }
                        Log.d(ContentValues.TAG, "Ad recorded an impression.");
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when ad is shown.
                        Log.d(ContentValues.TAG, "Ad showed fullscreen content.");
                        if (onFullScreenContentCallbackAdmob!=null){
                            onFullScreenContentCallbackAdmob.onAdShowedFullScreenContent();
                        }
                    }
                });
                mInterstitialAd.show(activity);
                if (onShowInterstitialAdmob != null) {
                    onShowInterstitialAdmob.onAdSuccess();
                }
               // LoadIntertitialAdmob(activity, selectAdsBackup, idIntertitial, idIntertitialBackup, Hpk1, Hpk2, Hpk3, Hpk4, Hpk5);
            } else {
                if (onShowInterstitialAdmob != null) {
                    onShowInterstitialAdmob.onAdFailedShow();
                }
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
                    case "UNITY":

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
                    case "FACEBOOK":
                        if (FBinterstitialAd == null || !FBinterstitialAd.isAdLoaded()) {
                        } else {
                            FBinterstitialAd.show();
                        }
                        break;
                    case "ALIEN-V":
                        SHOW_ALIEN_VIEW = true;
                        break;
                    case "ALIEN-M":
                        AlienMediationAds.ShowInterstitial(activity);
                        break;
                }
            }
            LoadIntertitialAdmob(activity, selectAdsBackup, idIntertitial, idIntertitialBackup, Hpk1, Hpk2, Hpk3, Hpk4, Hpk5);
            counter = 0;
        } else {
            counter++;
        }
    }

    public static void ShowIntertitialGoogleAds(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                                int interval) {
        if (counter >= interval) {
            if (mAdManagerInterstitialAd != null) {
                mAdManagerInterstitialAd.show(activity);
                LoadIntertitialGoogleAds(activity, selectAdsBackup, idIntertitial, idIntertitialBackup);
                if (onShowInterstitialGoogle != null) {
                    onShowInterstitialGoogle.onAdSuccess();
                }
            } else {
                if (onShowInterstitialGoogle != null) {
                    onShowInterstitialGoogle.onAdFailedShow();
                }
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
                    case "UNITY":


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
                    case "FACEBOOK":
                        if (FBinterstitialAd == null || !FBinterstitialAd.isAdLoaded()) {
                        } else {
                            FBinterstitialAd.show();
                        }
                        break;
                    case "ALIEN-V":
                        SHOW_ALIEN_VIEW = true;
                        break;
                    case "ALIEN-M":
                        AlienMediationAds.ShowInterstitial(activity);
                        break;
                }
                LoadIntertitialGoogleAds(activity, selectAdsBackup, idIntertitial, idIntertitialBackup);
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
                        if (onShowInterstitialApplovinDiscovery != null) {
                            onShowInterstitialApplovinDiscovery.onAdSuccess();
                        }
                    }

                    @Override
                    public void adHidden(AppLovinAd ad) {
                        if (onShowInterstitialApplovinDiscovery != null) {
                            onShowInterstitialApplovinDiscovery.onAdFailedShow();
                        }
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
                            case "UNITY":

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
                            case "GOOGLE-ADS":
                                if (mAdManagerInterstitialAd != null) {
                                    mAdManagerInterstitialAd.show(activity);
                                }
                                break;
                            case "FACEBOOK":
                                if (FBinterstitialAd != null || !FBinterstitialAd.isAdLoaded()) {
                                    FBinterstitialAd.show();
                                }
                                break;
                            case "ALIEN-V":
                                SHOW_ALIEN_VIEW = true;
                                break;
                            case "ALIEN-M":
                                AlienMediationAds.ShowInterstitial(activity);
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

    public static void ShowIntertitialApplovinDisHPK(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                                     int interval, String HPK1,
                                                     String HPK2, String HPK3, String HPK4, String HPK5) {
        if (counter >= interval) {
            if (interstitialAdlovin != null) {
                AppLovinAdDisplayListener listener = new AppLovinAdDisplayListener() {
                    @Override
                    public void adDisplayed(AppLovinAd ad) {
                        if (onShowInterstitialApplovinDiscovery != null) {
                            onShowInterstitialApplovinDiscovery.onAdSuccess();
                        }
                    }

                    @Override
                    public void adHidden(AppLovinAd ad) {
                        if (onShowInterstitialApplovinDiscovery != null) {
                            onShowInterstitialApplovinDiscovery.onAdFailedShow();
                        }
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
                            case "UNITY":

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
                            case "GOOGLE-ADS":
                                if (mAdManagerInterstitialAd != null) {
                                    mAdManagerInterstitialAd.show(activity);
                                }
                                break;
                            case "FACEBOOK":
                                if (FBinterstitialAd != null || !FBinterstitialAd.isAdLoaded()) {
                                    FBinterstitialAd.show();
                                }
                                break;
                            case "ALIEN-V":
                                SHOW_ALIEN_VIEW = true;
                                break;
                            case "ALIEN-M":
                                AlienMediationAds.ShowInterstitial(activity);
                                break;
                        }
                        LoadIntertitialApplovinDisHPK(activity, selectAdsBackup, idIntertitial, idIntertitialBackup, HPK1,
                                HPK2, HPK3, HPK4, HPK5);
                    }
                };
                interstitialAdlovin.setAdDisplayListener(listener);
                interstitialAdlovin.showAndRender(loadedAd);
                LoadIntertitialApplovinDisHPK(activity, selectAdsBackup, idIntertitial, idIntertitialBackup, HPK1,
                        HPK2, HPK3, HPK4, HPK5);
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
                if (onShowInterstitialApplovinMax != null) {
                    onShowInterstitialApplovinMax.onAdSuccess();
                }
            } else {
                if (onShowInterstitialApplovinMax != null) {
                    onShowInterstitialApplovinMax.onAdFailedShow();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-D":
                        if (interstitialAdlovin != null) {
                            interstitialAdlovin.showAndRender(loadedAd);
                        }
                        break;
                    case "MOPUB":
                    case "UNITY":


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
                    case "GOOGLE-ADS":
                        if (mAdManagerInterstitialAd != null) {
                            mAdManagerInterstitialAd.show(activity);
                        }
                        break;
                    case "FACEBOOK":
                        if (FBinterstitialAd == null || !FBinterstitialAd.isAdLoaded()) {
                        } else {
                            FBinterstitialAd.show();
                        }
                        break;
                    case "ALIEN-V":
                        SHOW_ALIEN_VIEW = true;
                        break;
                    case "ALIEN-M":
                        AlienMediationAds.ShowInterstitial(activity);
                        break;
                }
                interstitialAd.loadAd();
            }
            LoadIntertitialApplovinMax(activity, selectAdsBackup, idIntertitial, idIntertitialBackup);
            counter = 0;
        } else {
            counter++;
        }

    }

    public static void ShowIntertitialIron(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                           int interval) {
        if (counter >= interval) {
            if (irininter) {
                if (onShowInterstitialIronSource != null) {
                    onShowInterstitialIronSource.onAdFailedShow();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-D":
                        if (interstitialAdlovin != null) {
                            interstitialAdlovin.showAndRender(loadedAd);
                        }
                        break;
                    case "MOPUB":
                    case "UNITY":


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
                    case "GOOGLE-ADS":
                        if (mAdManagerInterstitialAd != null) {
                            mAdManagerInterstitialAd.show(activity);
                        }
                        break;
                    case "FACEBOOK":
                        if (FBinterstitialAd == null || !FBinterstitialAd.isAdLoaded()) {
                        } else {
                            FBinterstitialAd.show();
                        }
                        break;
                    case "ALIEN-V":
                        SHOW_ALIEN_VIEW = true;
                        break;
                    case "ALIEN-M":
                        AlienMediationAds.ShowInterstitial(activity);
                        break;
                }
            } else {
                if (onShowInterstitialIronSource != null) {
                    onShowInterstitialIronSource.onAdSuccess();
                }
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

    }


    public static void ShowIntertitialSartApp(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                              int interval) {
        if (counter >= interval) {
            startAppAd.showAd();
            startAppAd.showAd(new AdDisplayListener() {
                @Override
                public void adHidden(Ad ad) {
                    if (onShowInterstitialStartApp != null) {
                        onShowInterstitialStartApp.adHidden();
                    }
                }

                @Override
                public void adDisplayed(Ad ad) {
                    if (onShowInterstitialStartApp != null) {
                        onShowInterstitialStartApp.adDisplayed();
                    }
                }

                @Override
                public void adClicked(Ad ad) {
                    if (onShowInterstitialStartApp != null) {
                        onShowInterstitialStartApp.adClicked();
                    }
                }

                @Override
                public void adNotDisplayed(Ad ad) {
                    if (onShowInterstitialStartApp != null) {
                        onShowInterstitialStartApp.adNotDisplayed();
                    }
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
                        case "UNITY":


                            break;
                        case "ADMOB":
                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(activity);
                            }
                            break;
                        case "GOOGLE-ADS":
                            if (mAdManagerInterstitialAd != null) {
                                mAdManagerInterstitialAd.show(activity);
                            }
                            break;
                        case "FACEBOOK":
                            if (FBinterstitialAd == null || !FBinterstitialAd.isAdLoaded()) {
                            } else {
                                FBinterstitialAd.show();
                            }
                            break;
                        case "ALIEN-V":
                            SHOW_ALIEN_VIEW = true;
                            break;
                        case "ALIEN-M":
                            AlienMediationAds.ShowInterstitial(activity);
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


    public static void ShowIntertitialFAN(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                          int interval) {
        if (counter >= interval) {
            if (FBinterstitialAd == null || !FBinterstitialAd.isAdLoaded()) {
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
                    case "UNITY":


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
                    case "ADMOB":
                        if (mInterstitialAd != null) {
                            mInterstitialAd.show(activity);
                        }
                        break;
                    case "GOOGLE-ADS":
                        if (mAdManagerInterstitialAd != null) {
                            mAdManagerInterstitialAd.show(activity);
                        }
                        break;
                    case "ALIEN-V":
                        SHOW_ALIEN_VIEW = true;
                        break;
                    case "ALIEN-M":
                        AlienMediationAds.ShowInterstitial(activity);
                        break;
                }
                LoadIntertitialFAN(activity, selectAdsBackup, idIntertitial, idIntertitialBackup);
                if (onShowInterstitialFacebook != null) {
                    onShowInterstitialFacebook.onAdFailedShow();
                }
            } else {
                FBinterstitialAd.show();
                LoadIntertitialFAN(activity, selectAdsBackup, idIntertitial, idIntertitialBackup);
                if (onShowInterstitialFacebook != null) {
                    onShowInterstitialFacebook.onAdSuccess();
                }
            }
            counter = 0;
        } else {
            counter++;
        }
    }

    public static void ShowIntertitialUnity(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                            int interval) {

    }

    public static void ShowIntertitialAlienView(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                                int interval) {
        if (counter >= interval) {
            SHOW_ALIEN_VIEW = true;
            AlienViewAds.onShowInterstitialView = new OnShowInterstitialView() {
                @Override
                public void onAdSuccess() {
                    if (onShowInterstitialAlienMediation!=null){
                        onShowInterstitialAlienMediation.onAdSuccess();
                    }
                }

                @Override
                public void onAdFailedShow() {
                    if (onShowInterstitialAlienMediation!=null){
                        onShowInterstitialAlienMediation.onAdFailedShow();
                    }
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
                        case "UNITY":
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
                        case "ADMOB":
                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(activity);
                            }
                            break;
                        case "GOOGLE-ADS":
                            if (mAdManagerInterstitialAd != null) {
                                mAdManagerInterstitialAd.show(activity);
                            }
                            break;
                        case "FACEBOOK":
                            if (FBinterstitialAd == null || !FBinterstitialAd.isAdLoaded()) {
                            } else {
                                FBinterstitialAd.show();
                            }
                            break;
                        case "ALIEN-M":
                            AlienMediationAds.ShowInterstitial(activity);
                            break;
                    }
                }
            };
            LoadIntertitialAlienView(activity, selectAdsBackup, idIntertitial, idIntertitialBackup);
            counter = 0;
        } else {
            counter++;
        }
    }

    public static void ShowIntertitialAlienMediation(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                                     int interval) {
        if (counter >= interval) {
            AlienMediationAds.ShowInterstitial(activity);
            AlienMediationAds.onShowInterstitial = new OnShowInterstitial() {
                @Override
                public void onAdSuccess() {
                    if (onShowInterstitialAlienMediation!=null){
                        onShowInterstitialAlienMediation.onAdSuccess();
                    }
                }

                @Override
                public void onAdFailedShow() {
                    if (onShowInterstitialAlienMediation!=null){
                        onShowInterstitialAlienMediation.onAdFailedShow();
                    }
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
                        case "UNITY":
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
                        case "ADMOB":
                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(activity);
                            }
                            break;
                        case "GOOGLE-ADS":
                            if (mAdManagerInterstitialAd != null) {
                                mAdManagerInterstitialAd.show(activity);
                            }
                            break;
                        case "FACEBOOK":
                            if (FBinterstitialAd == null || !FBinterstitialAd.isAdLoaded()) {
                            } else {
                                FBinterstitialAd.show();
                            }
                            break;
                        case "ALIEN-V":
                            SHOW_ALIEN_VIEW = true;
                            break;
                    }
                }
            };
            LoadIntertitialAlienView(activity, selectAdsBackup, idIntertitial, idIntertitialBackup);
            counter = 0;
        } else {
            counter++;
        }
    }
}
