package com.aliendroid.alienads;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.mediation.AppLovinExtras;
import com.applovin.mediation.ApplovinAdapter;
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
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.UnityAdsShowOptions;
import com.unity3d.mediation.IInterstitialAdLoadListener;
import com.unity3d.mediation.IInterstitialAdShowListener;
import com.unity3d.mediation.errors.LoadError;
import com.unity3d.mediation.errors.ShowError;

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
    public static com.unity3d.mediation.InterstitialAd unityInterstitialAd;

    public static void LoadIntertitialUnity(Activity activity, String selectAds, String idIntertitial, String idBackupIntertitial) {

        unityInterstitialAd = new com.unity3d.mediation.InterstitialAd(activity,idIntertitial);
        final IInterstitialAdLoadListener loadListener = new IInterstitialAdLoadListener() {
            @Override
            public void onInterstitialLoaded(com.unity3d.mediation.InterstitialAd unityInterstitialAd) {

            }
            @Override
            public void onInterstitialFailedLoad(com.unity3d.mediation.InterstitialAd unityInterstitialAd, LoadError loadError, String s) {
                switch (selectAds) {
                    case "ADMOB":
                        Bundle extrasApplovin = new AppLovinExtras.Builder()
                                .setMuteAudio(true)
                                .build();

                        Bundle extras = new FacebookExtras()
                                .setNativeBanner(true)
                                .build();
                        AdRequest request = new AdRequest.Builder()
                                .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                                .addNetworkExtrasBundle(ApplovinAdapter.class, extrasApplovin)
                                .build();
                        InterstitialAd.load(activity, idBackupIntertitial, request,
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

                        AdManagerInterstitialAd.load(activity, idBackupIntertitial, adRequest,
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
                    case "APPLOVIN-M":
                        interstitialAd = new MaxInterstitialAd(idBackupIntertitial, activity);
                        interstitialAd.loadAd();
                        break;
                    case "MOPUB":

                        break;
                    case "IRON":
                        IronSource.isInterstitialPlacementCapped(idBackupIntertitial);
                        IronSource.loadInterstitial();
                        break;
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle interstitialExtras = new Bundle();
                        interstitialExtras.putString("zone_id", idBackupIntertitial);
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


        };
        unityInterstitialAd.load(loadListener);
    }

    public static void LoadIntertitialAdmob(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup, String Hpk1,
                                            String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        Bundle extrasApplovin = new AppLovinExtras.Builder()
                .setMuteAudio(true)
                .build();

        Bundle extras = new FacebookExtras()
                .setNativeBanner(true)
                .build();
        AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                .addNetworkExtrasBundle(ApplovinAdapter.class, extrasApplovin)
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

                break;
            case "UNITY":
                unityInterstitialAd = new com.unity3d.mediation.InterstitialAd(activity,idIntertitialBackup);
                final IInterstitialAdLoadListener loadListener = new IInterstitialAdLoadListener() {
                    @Override
                    public void onInterstitialLoaded(com.unity3d.mediation.InterstitialAd unityInterstitialAd) {

                    }
                    @Override
                    public void onInterstitialFailedLoad(com.unity3d.mediation.InterstitialAd unityInterstitialAd, LoadError loadError, String s) {

                    }


                };
                unityInterstitialAd.load(loadListener);
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

                break;
            case "UNITY":
                unityInterstitialAd = new com.unity3d.mediation.InterstitialAd(activity,idIntertitialBackup);
                final IInterstitialAdLoadListener loadListener = new IInterstitialAdLoadListener() {
                    @Override
                    public void onInterstitialLoaded(com.unity3d.mediation.InterstitialAd unityInterstitialAd) {

                    }
                    @Override
                    public void onInterstitialFailedLoad(com.unity3d.mediation.InterstitialAd unityInterstitialAd, LoadError loadError, String s) {

                    }


                };
                unityInterstitialAd.load(loadListener);
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

        }
    }

    public static void LoadIntertitialFAN(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup) {
        FBinterstitialAd = new com.facebook.ads.InterstitialAd(activity, idIntertitial);
        FBinterstitialAd.loadAd();

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

                break;
            case "UNITY":
                unityInterstitialAd = new com.unity3d.mediation.InterstitialAd(activity,idIntertitialBackup);
                final IInterstitialAdLoadListener loadListener = new IInterstitialAdLoadListener() {
                    @Override
                    public void onInterstitialLoaded(com.unity3d.mediation.InterstitialAd unityInterstitialAd) {

                    }
                    @Override
                    public void onInterstitialFailedLoad(com.unity3d.mediation.InterstitialAd unityInterstitialAd, LoadError loadError, String s) {

                    }


                };
                unityInterstitialAd.load(loadListener);
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
            case "ADMOB":
                Bundle extrasApplovin = new AppLovinExtras.Builder()
                        .setMuteAudio(true)
                        .build();

                Bundle extras = new FacebookExtras()
                        .setNativeBanner(true)
                        .build();
                AdRequest request = new AdRequest.Builder()
                        .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                        .addNetworkExtrasBundle(ApplovinAdapter.class, extrasApplovin)
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
                interstitialAd = new MaxInterstitialAd(idIntertitialBackup, activity);
                interstitialAd.loadAd();
                break;
            case "MOPUB":

                break;
            case "UNITY":
                unityInterstitialAd = new com.unity3d.mediation.InterstitialAd(activity,idIntertitialBackup);
                final IInterstitialAdLoadListener loadListener = new IInterstitialAdLoadListener() {
                    @Override
                    public void onInterstitialLoaded(com.unity3d.mediation.InterstitialAd unityInterstitialAd) {

                    }
                    @Override
                    public void onInterstitialFailedLoad(com.unity3d.mediation.InterstitialAd unityInterstitialAd, LoadError loadError, String s) {

                    }


                };
                unityInterstitialAd.load(loadListener);
                break;
            case "IRON":
                IronSource.isInterstitialPlacementCapped(idIntertitialBackup);
                IronSource.loadInterstitial();
                break;
            case "ADMOB":
                Bundle extrasApplovin = new AppLovinExtras.Builder()
                        .setMuteAudio(true)
                        .build();

                Bundle extras = new FacebookExtras()
                        .setNativeBanner(true)
                        .build();
                AdRequest request = new AdRequest.Builder()
                        .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                        .addNetworkExtrasBundle(ApplovinAdapter.class, extrasApplovin)
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

        }
    }

    public static void LoadIntertitialApplovinDisHPK(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                                     String HPK1,
                                                     String HPK2, String HPK3, String HPK4, String HPK5
    ) {

        AdRequest.Builder builder = new AdRequest.Builder().addKeyword(HPK1).addKeyword(HPK2).addKeyword(HPK3).addKeyword(HPK4).addKeyword(HPK5);
        ;
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
                interstitialAd = new MaxInterstitialAd(idIntertitialBackup, activity);
                interstitialAd.loadAd();
                break;
            case "MOPUB":

                break;
            case "UNITY":
                unityInterstitialAd = new com.unity3d.mediation.InterstitialAd(activity,idIntertitialBackup);
                final IInterstitialAdLoadListener loadListener = new IInterstitialAdLoadListener() {
                    @Override
                    public void onInterstitialLoaded(com.unity3d.mediation.InterstitialAd unityInterstitialAd) {

                    }
                    @Override
                    public void onInterstitialFailedLoad(com.unity3d.mediation.InterstitialAd unityInterstitialAd, LoadError loadError, String s) {

                    }


                };
                unityInterstitialAd.load(loadListener);
                break;
            case "IRON":
                IronSource.isInterstitialPlacementCapped(idIntertitialBackup);
                IronSource.loadInterstitial();
                break;
            case "ADMOB":
                Bundle extrasApplovin = new AppLovinExtras.Builder()
                        .setMuteAudio(true)
                        .build();

                Bundle extras = new FacebookExtras()
                        .setNativeBanner(true)
                        .build();
                AdRequest request = new AdRequest.Builder()
                        .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                        .addNetworkExtrasBundle(ApplovinAdapter.class, extrasApplovin)
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

                break;
            case "UNITY":
                unityInterstitialAd = new com.unity3d.mediation.InterstitialAd(activity,idIntertitialBackup);
                final IInterstitialAdLoadListener loadListener = new IInterstitialAdLoadListener() {
                    @Override
                    public void onInterstitialLoaded(com.unity3d.mediation.InterstitialAd unityInterstitialAd) {

                    }
                    @Override
                    public void onInterstitialFailedLoad(com.unity3d.mediation.InterstitialAd unityInterstitialAd, LoadError loadError, String s) {

                    }


                };
                unityInterstitialAd.load(loadListener);
                break;
            case "IRON":
                IronSource.isInterstitialPlacementCapped(idIntertitialBackup);
                IronSource.loadInterstitial();
                break;
            case "ADMOB":
                Bundle extrasApplovin = new AppLovinExtras.Builder()
                        .setMuteAudio(true)
                        .build();

                Bundle extras = new FacebookExtras()
                        .setNativeBanner(true)
                        .build();
                AdRequest request = new AdRequest.Builder()
                        .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                        .addNetworkExtrasBundle(ApplovinAdapter.class, extrasApplovin)
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

                break;
            case "UNITY":
                unityInterstitialAd = new com.unity3d.mediation.InterstitialAd(activity,idIntertitialBackup);
                final IInterstitialAdLoadListener loadListener = new IInterstitialAdLoadListener() {
                    @Override
                    public void onInterstitialLoaded(com.unity3d.mediation.InterstitialAd unityInterstitialAd) {

                    }
                    @Override
                    public void onInterstitialFailedLoad(com.unity3d.mediation.InterstitialAd unityInterstitialAd, LoadError loadError, String s) {

                    }


                };
                unityInterstitialAd.load(loadListener);
                break;
            case "APPLOVIN-M":
                interstitialAd = new MaxInterstitialAd(idIntertitialBackup, activity);
                interstitialAd.loadAd();
                break;
            case "ADMOB":
                Bundle extrasApplovin = new AppLovinExtras.Builder()
                        .setMuteAudio(true)
                        .build();

                Bundle extras = new FacebookExtras()
                        .setNativeBanner(true)
                        .build();
                AdRequest request = new AdRequest.Builder()
                        .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                        .addNetworkExtrasBundle(ApplovinAdapter.class, extrasApplovin)
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

        }
    }

    public static void LoadIntertitialMopub(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup) {
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
                Bundle extrasApplovin = new AppLovinExtras.Builder()
                        .setMuteAudio(true)
                        .build();

                Bundle extras = new FacebookExtras()
                        .setNativeBanner(true)
                        .build();
                AdRequest request = new AdRequest.Builder()
                        .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                        .addNetworkExtrasBundle(ApplovinAdapter.class, extrasApplovin)
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

                break;
            case "UNITY":
                unityInterstitialAd = new com.unity3d.mediation.InterstitialAd(activity,idIntertitialBackup);
                final IInterstitialAdLoadListener loadListener = new IInterstitialAdLoadListener() {
                    @Override
                    public void onInterstitialLoaded(com.unity3d.mediation.InterstitialAd unityInterstitialAd) {

                    }
                    @Override
                    public void onInterstitialFailedLoad(com.unity3d.mediation.InterstitialAd unityInterstitialAd, LoadError loadError, String s) {

                    }


                };
                unityInterstitialAd.load(loadListener);
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
                    case "UNITY":
                        IInterstitialAdShowListener showListener = new IInterstitialAdShowListener() {
                            @Override
                            public void onInterstitialShowed(com.unity3d.mediation.InterstitialAd interstitialAd) {

                            }

                            @Override
                            public void onInterstitialClicked(com.unity3d.mediation.InterstitialAd interstitialAd) {

                            }

                            @Override
                            public void onInterstitialClosed(com.unity3d.mediation.InterstitialAd interstitialAd) {

                            }

                            @Override
                            public void onInterstitialFailedShow(com.unity3d.mediation.InterstitialAd interstitialAd, ShowError showError, String s) {

                            }


                        };
                        unityInterstitialAd.show(showListener);
                        break;
                }
                LoadIntertitialAdmob(activity, selectAdsBackup, idIntertitial, idIntertitialBackup, Hpk1, Hpk2, Hpk3, Hpk4, Hpk5);
            }

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
                    case "UNITY":
                        IInterstitialAdShowListener showListener = new IInterstitialAdShowListener() {
                            @Override
                            public void onInterstitialShowed(com.unity3d.mediation.InterstitialAd interstitialAd) {

                            }

                            @Override
                            public void onInterstitialClicked(com.unity3d.mediation.InterstitialAd interstitialAd) {

                            }

                            @Override
                            public void onInterstitialClosed(com.unity3d.mediation.InterstitialAd interstitialAd) {

                            }

                            @Override
                            public void onInterstitialFailedShow(com.unity3d.mediation.InterstitialAd interstitialAd, ShowError showError, String s) {

                            }


                        };
                        unityInterstitialAd.show(showListener);
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
            if (interstitialAdlovin.isAdReadyToDisplay()) {
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
                            case "UNITY":
                                IInterstitialAdShowListener showListener = new IInterstitialAdShowListener() {
                                    @Override
                                    public void onInterstitialShowed(com.unity3d.mediation.InterstitialAd interstitialAd) {

                                    }

                                    @Override
                                    public void onInterstitialClicked(com.unity3d.mediation.InterstitialAd interstitialAd) {

                                    }

                                    @Override
                                    public void onInterstitialClosed(com.unity3d.mediation.InterstitialAd interstitialAd) {

                                    }

                                    @Override
                                    public void onInterstitialFailedShow(com.unity3d.mediation.InterstitialAd interstitialAd, ShowError showError, String s) {

                                    }


                                };
                                unityInterstitialAd.show(showListener);
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
                            case "UNITY":
                                IInterstitialAdShowListener showListener = new IInterstitialAdShowListener() {
                                    @Override
                                    public void onInterstitialShowed(com.unity3d.mediation.InterstitialAd interstitialAd) {

                                    }

                                    @Override
                                    public void onInterstitialClicked(com.unity3d.mediation.InterstitialAd interstitialAd) {

                                    }

                                    @Override
                                    public void onInterstitialClosed(com.unity3d.mediation.InterstitialAd interstitialAd) {

                                    }

                                    @Override
                                    public void onInterstitialFailedShow(com.unity3d.mediation.InterstitialAd interstitialAd, ShowError showError, String s) {

                                    }


                                };
                                unityInterstitialAd.show(showListener);
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
            } else {
                switch (selectAdsBackup) {
                    case "APPLOVIN-D":
                        if (interstitialAdlovin != null) {
                            interstitialAdlovin.showAndRender(loadedAd);
                        }
                        break;
                    case "MOPUB":

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
                    case "UNITY":
                        IInterstitialAdShowListener showListener = new IInterstitialAdShowListener() {
                            @Override
                            public void onInterstitialShowed(com.unity3d.mediation.InterstitialAd interstitialAd) {

                            }

                            @Override
                            public void onInterstitialClicked(com.unity3d.mediation.InterstitialAd interstitialAd) {

                            }

                            @Override
                            public void onInterstitialClosed(com.unity3d.mediation.InterstitialAd interstitialAd) {

                            }

                            @Override
                            public void onInterstitialFailedShow(com.unity3d.mediation.InterstitialAd interstitialAd, ShowError showError, String s) {

                            }


                        };
                        unityInterstitialAd.show(showListener);
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
                    case "UNITY":
                        IInterstitialAdShowListener showListener = new IInterstitialAdShowListener() {
                            @Override
                            public void onInterstitialShowed(com.unity3d.mediation.InterstitialAd interstitialAd) {

                            }

                            @Override
                            public void onInterstitialClicked(com.unity3d.mediation.InterstitialAd interstitialAd) {

                            }

                            @Override
                            public void onInterstitialClosed(com.unity3d.mediation.InterstitialAd interstitialAd) {

                            }

                            @Override
                            public void onInterstitialFailedShow(com.unity3d.mediation.InterstitialAd interstitialAd, ShowError showError, String s) {

                            }


                        };
                        unityInterstitialAd.show(showListener);
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
                        case "UNITY":
                            IInterstitialAdShowListener showListener = new IInterstitialAdShowListener() {
                                @Override
                                public void onInterstitialShowed(com.unity3d.mediation.InterstitialAd interstitialAd) {

                                }

                                @Override
                                public void onInterstitialClicked(com.unity3d.mediation.InterstitialAd interstitialAd) {

                                }

                                @Override
                                public void onInterstitialClosed(com.unity3d.mediation.InterstitialAd interstitialAd) {

                                }

                                @Override
                                public void onInterstitialFailedShow(com.unity3d.mediation.InterstitialAd interstitialAd, ShowError showError, String s) {

                                }


                            };
                            unityInterstitialAd.show(showListener);
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
                    case "UNITY":
                        IInterstitialAdShowListener showListener = new IInterstitialAdShowListener() {
                            @Override
                            public void onInterstitialShowed(com.unity3d.mediation.InterstitialAd interstitialAd) {

                            }

                            @Override
                            public void onInterstitialClicked(com.unity3d.mediation.InterstitialAd interstitialAd) {

                            }

                            @Override
                            public void onInterstitialClosed(com.unity3d.mediation.InterstitialAd interstitialAd) {

                            }

                            @Override
                            public void onInterstitialFailedShow(com.unity3d.mediation.InterstitialAd interstitialAd, ShowError showError, String s) {

                            }


                        };
                        unityInterstitialAd.show(showListener);
                        break;
                }
                LoadIntertitialFAN(activity, selectAdsBackup, idIntertitial, idIntertitialBackup);
            } else {
                FBinterstitialAd.show();
                LoadIntertitialFAN(activity, selectAdsBackup, idIntertitial, idIntertitialBackup);
            }
            counter = 0;
        } else {
            counter++;
        }
    }

    public static void ShowIntertitialUnity(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                            int interval) {
        if (counter >= interval) {
            IInterstitialAdShowListener showListener = new IInterstitialAdShowListener() {
                @Override
                public void onInterstitialShowed(com.unity3d.mediation.InterstitialAd interstitialAd) {

                }
                @Override
                public void onInterstitialClicked(com.unity3d.mediation.InterstitialAd interstitialAd) {

                }

                @Override
                public void onInterstitialClosed(com.unity3d.mediation.InterstitialAd interstitialAd) {

                }

                @Override
                public void onInterstitialFailedShow(com.unity3d.mediation.InterstitialAd unityInterstitialAd, ShowError showError, String s) {
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

                    }
                }


            };
            unityInterstitialAd.show(showListener);

            LoadIntertitialUnity(activity, selectAdsBackup, idIntertitial, idIntertitialBackup);
            counter = 0;
        } else {
            counter++;
        }
    }
}
