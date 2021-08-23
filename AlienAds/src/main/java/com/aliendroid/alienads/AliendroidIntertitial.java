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
                if (idIntertitial == null) {
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

        }
    }

    public static void LoadIntertitialApplovinMax(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup)
    {

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

        }
    }

    public static void LoadIntertitialIron(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup)
    {

        IronSource.isInterstitialPlacementCapped(idIntertitial);
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
                interstitialAd = new MaxInterstitialAd(idIntertitial, activity);
                interstitialAd.loadAd();
                break;
            case "ADMOB":
                Bundle extras = new FacebookExtras()
                        .setNativeBanner(true)
                        .build();
                AdRequest request = new AdRequest.Builder()
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
            IronSource.showInterstitial(idIntertitial);
            IronSource.setInterstitialListener(new InterstitialListener() {
                /**
                 * Invoked when Interstitial Ad is ready to be shown after load function was called.
                 */
                @Override
                public void onInterstitialAdReady() {
                }
                /**
                 * invoked when there is no Interstitial Ad available after calling load function.
                 */
                @Override
                public void onInterstitialAdLoadFailed(IronSourceError error) {
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
            LoadIntertitialIron(activity, selectAdsBackup, idIntertitial, idIntertitialBackup);
            counter = 0;
        } else {
            counter++;
        }

    }
}
