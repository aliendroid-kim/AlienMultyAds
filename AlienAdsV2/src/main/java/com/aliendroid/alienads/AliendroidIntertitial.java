package com.aliendroid.alienads;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.app.Activity;
import android.content.ContentValues;
import android.util.Log;

import androidx.annotation.NonNull;

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
import com.aliendroid.alienads.interfaces.interstitial.load.OnLoadInterstitialWortise;
import com.aliendroid.alienads.interfaces.interstitial.show.OnShowInterstitialAdmob;
import com.aliendroid.alienads.interfaces.interstitial.show.OnShowInterstitialAlienMediation;
import com.aliendroid.alienads.interfaces.interstitial.show.OnShowInterstitialAlienView;
import com.aliendroid.alienads.interfaces.interstitial.show.OnShowInterstitialApplovinDiscovery;
import com.aliendroid.alienads.interfaces.interstitial.show.OnShowInterstitialApplovinMax;
import com.aliendroid.alienads.interfaces.interstitial.show.OnShowInterstitialFacebook;
import com.aliendroid.alienads.interfaces.interstitial.show.OnShowInterstitialGoogle;
import com.aliendroid.alienads.interfaces.interstitial.show.OnShowInterstitialIronSource;
import com.aliendroid.alienads.interfaces.interstitial.show.OnShowInterstitialStartApp;
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
import com.props.adsmanager.PropsAdsManagement;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.adlisteners.AdDisplayListener;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.UnityAdsShowOptions;

public class AliendroidIntertitial {

    public static InterstitialAd mInterstitialAd;
    public static InterstitialAd mInterstitialAd2;
    public static AdManagerInterstitialAd mAdManagerInterstitialAd;
    public static com.facebook.ads.InterstitialAd FBinterstitialAd;
    public static com.facebook.ads.InterstitialAd FBinterstitialAd2;
    public static int counter = 0;
    public static boolean irininter = false;
    private static StartAppAd startAppAd;
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

    public static OnLoadInterstitialWortise onLoadInterstitialWortise;
    //public static OnShowInterstitialStartApp onShowInterstitialStartApp;

    public static void LoadIntertitialUnity(Activity activity, String selectAds, String idIntertitial, String idBackupIntertitial) {
        IUnityAdsLoadListener listener = new IUnityAdsLoadListener() {
            @Override
            public void onUnityAdsAdLoaded(String placementId) {

            }

            @Override
            public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {

            }
        };
        try {
            UnityAds.load(idIntertitial, listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (selectAds) {
            case "FACEBOOK":
                FBinterstitialAd = new com.facebook.ads.InterstitialAd(activity, idBackupIntertitial);
                FBinterstitialAd.loadAd();
                break;
            case "ALIEN-M":
                PropsAdsManagement.loadInterstitialAds(activity, idBackupIntertitial, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);

                    }

                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAdProps) {
                        super.onAdLoaded(interstitialAdProps);
                    }
                });

                break;
            case "ADMOB":
                AdRequest request = new AdRequest.Builder()
                        .build();
                InterstitialAd.load(activity, idBackupIntertitial, request,
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

        }
    }


    public static void LoadIntertitialAdmob(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup, String Hpk1,
                                            String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                .build();

        InterstitialAd.load(activity, idIntertitial, request,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        if (onLoadInterstitialAdmob != null) {
                            onLoadInterstitialAdmob.onInterstitialAdLoaded();
                        }
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");

                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        if (onLoadInterstitialAdmob != null) {
                            onLoadInterstitialAdmob.onInterstitialAdFailedToLoad("");
                        }
                        Log.d(TAG, loadAdError.toString());
                        mInterstitialAd = null;

                    }
                });
        switch (selectAdsBackup) {
            case "UNITY":
                IUnityAdsLoadListener listener = new IUnityAdsLoadListener() {
                    @Override
                    public void onUnityAdsAdLoaded(String placementId) {

                    }

                    @Override
                    public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {

                    }
                };
                try {
                    UnityAds.load(idIntertitialBackup, listener);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "FACEBOOK":
                FBinterstitialAd = new com.facebook.ads.InterstitialAd(activity, idIntertitialBackup);
                FBinterstitialAd.loadAd();
                break;
            case "ALIEN-M":
                PropsAdsManagement.loadInterstitialAds(activity, idIntertitialBackup, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);

                    }

                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAdProps) {
                        super.onAdLoaded(interstitialAdProps);
                    }
                });
                break;
            case "ADMOB":
                AdRequest request2 = new AdRequest.Builder()
                        .build();
                InterstitialAd.load(activity, idIntertitialBackup, request2,
                        new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                mInterstitialAd2 = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error
                                Log.i(TAG, loadAdError.getMessage());
                                mInterstitialAd2 = null;
                            }
                        });
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
                        mAdManagerInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
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
            case "UNITY":
                IUnityAdsLoadListener listener = new IUnityAdsLoadListener() {
                    @Override
                    public void onUnityAdsAdLoaded(String placementId) {

                    }

                    @Override
                    public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {

                    }
                };
                try {
                    UnityAds.load(idIntertitialBackup, listener);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "FACEBOOK":
                FBinterstitialAd = new com.facebook.ads.InterstitialAd(activity, idIntertitialBackup);
                FBinterstitialAd.loadAd();
                break;
            case "ALIEN-M":
                PropsAdsManagement.loadInterstitialAds(activity, idIntertitialBackup, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);

                    }

                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAdProps) {
                        super.onAdLoaded(interstitialAdProps);
                    }
                });
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
            case "UNITY":
                IUnityAdsLoadListener listener = new IUnityAdsLoadListener() {
                    @Override
                    public void onUnityAdsAdLoaded(String placementId) {

                    }

                    @Override
                    public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {

                    }
                };
                try {
                    UnityAds.load(idIntertitialBackup, listener);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "FACEBOOK":
                FBinterstitialAd2 = new com.facebook.ads.InterstitialAd(activity, idIntertitialBackup);
                FBinterstitialAd2.loadAd();
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
            case "ALIEN-M":
                PropsAdsManagement.loadInterstitialAds(activity, idIntertitialBackup, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);

                    }

                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAdProps) {
                        super.onAdLoaded(interstitialAdProps);
                    }
                });
                break;
        }
    }

    public static void LoadIntertitialApplovinDis(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup
    ) {
    }

    public static void LoadIntertitialApplovinDisHPK(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                                     String HPK1,
                                                     String HPK2, String HPK3, String HPK4, String HPK5) {
    }

    public static void LoadIntertitialApplovinMax(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup) {

    }

    public static void LoadIntertitialIron(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup) {

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
            case "UNITY":
                IUnityAdsLoadListener listener = new IUnityAdsLoadListener() {
                    @Override
                    public void onUnityAdsAdLoaded(String placementId) {

                    }

                    @Override
                    public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {

                    }
                };
                try {
                    UnityAds.load(idIntertitialBackup, listener);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
            case "ALIEN-M":
                PropsAdsManagement.loadInterstitialAds(activity, idIntertitialBackup, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);

                    }

                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAdProps) {
                        super.onAdLoaded(interstitialAdProps);
                    }
                });
                break;
        }
    }

    public static void LoadIntertitialAlienView(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup) {
    }

    public static void LoadIntertitialAlienMediation(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup) {
        PropsAdsManagement.loadInterstitialAds(activity, idIntertitial, new InterstitialAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);

            }

            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAdProps) {
                super.onAdLoaded(interstitialAdProps);
            }
        });
        switch (selectAdsBackup) {
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
            case "UNITY":
                IUnityAdsLoadListener listener = new IUnityAdsLoadListener() {
                    @Override
                    public void onUnityAdsAdLoaded(String placementId) {

                    }

                    @Override
                    public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {

                    }
                };
                try {
                    UnityAds.load(idIntertitialBackup, listener);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
        }
    }

    public static void LoadIntertitialWortise(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup) {

    }

    public static void ShowIntertitialAdmob(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                            int interval, String Hpk1,
                                            String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        if (counter >= interval) {
            if (mInterstitialAd != null) {
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdClicked() {
                        if (onFullScreenContentCallbackAdmob != null) {
                            onFullScreenContentCallbackAdmob.onAdClicked();
                        }

                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        if (onFullScreenContentCallbackAdmob != null) {
                            onFullScreenContentCallbackAdmob.onAdDismissedFullScreenContent();
                        }
                        mInterstitialAd = null;
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                        if (onFullScreenContentCallbackAdmob != null) {
                            onFullScreenContentCallbackAdmob.onAdFailedToShowFullScreenContent();
                        }
                        mInterstitialAd = null;
                    }

                    @Override
                    public void onAdImpression() {
                        // Called when an impression is recorded for an ad.
                        if (onFullScreenContentCallbackAdmob != null) {
                            onFullScreenContentCallbackAdmob.onAdImpression();
                        }
                        Log.d(ContentValues.TAG, "Ad recorded an impression.");
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when ad is shown.
                        Log.d(ContentValues.TAG, "Ad showed fullscreen content.");
                        if (onFullScreenContentCallbackAdmob != null) {
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
                    case "UNITY":
                        IUnityAdsShowListener showListener = new IUnityAdsShowListener() {
                            @Override
                            public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {

                            }

                            @Override
                            public void onUnityAdsShowStart(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowClick(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {

                            }
                        };
                        UnityAds.show(activity, idIntertitialBackup, new UnityAdsShowOptions(), showListener);
                        break;

                    case "STARTAPP":
                        StartAppAd.showAd(activity);
                        break;
                    case "FACEBOOK":
                        if (FBinterstitialAd == null || !FBinterstitialAd.isAdLoaded()) {
                        } else {
                            FBinterstitialAd.show();
                        }
                        break;
                    case "ALIEN-M":
                        if (PropsAdsManagement.getInterstitialAds() != null) {
                            PropsAdsManagement.getInterstitialAds().show(activity);
                        }
                        break;
                    case "ADMOB":
                        if (mInterstitialAd2 != null) {
                            mInterstitialAd2.show(activity);
                        }
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
                    case "UNITY":
                        IUnityAdsShowListener showListener = new IUnityAdsShowListener() {
                            @Override
                            public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {

                            }

                            @Override
                            public void onUnityAdsShowStart(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowClick(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {

                            }
                        };
                        UnityAds.show(activity, idIntertitialBackup, new UnityAdsShowOptions(), showListener);
                        break;

                    case "STARTAPP":
                        StartAppAd.showAd(activity);
                        break;
                    case "FACEBOOK":
                        if (FBinterstitialAd == null || !FBinterstitialAd.isAdLoaded()) {
                        } else {
                            FBinterstitialAd.show();
                        }
                        break;
                    case "ALIEN-M":
                        if (PropsAdsManagement.getInterstitialAds() != null) {
                            PropsAdsManagement.getInterstitialAds().show(activity);
                        }
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
    }

    public static void ShowIntertitialApplovinDisHPK(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                                     int interval, String HPK1,
                                                     String HPK2, String HPK3, String HPK4, String HPK5) {
    }

    public static void ShowIntertitialApplovinMax(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                                  int interval) {
    }

    public static void ShowIntertitialIron(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                           int interval) {
        if (counter >= interval) {
            if (irininter) {
                if (onShowInterstitialIronSource != null) {
                    onShowInterstitialIronSource.onAdFailedShow();
                }
                switch (selectAdsBackup) {
                    case "UNITY":
                        IUnityAdsShowListener showListener = new IUnityAdsShowListener() {
                            @Override
                            public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {

                            }

                            @Override
                            public void onUnityAdsShowStart(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowClick(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {

                            }
                        };
                        UnityAds.show(activity, idIntertitialBackup, new UnityAdsShowOptions(), showListener);

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
                    case "ALIEN-M":
                        if (PropsAdsManagement.getInterstitialAds() != null) {
                            PropsAdsManagement.getInterstitialAds().show(activity);
                        }
                        break;
                }
            } else {
                if (onShowInterstitialIronSource != null) {
                    onShowInterstitialIronSource.onAdSuccess();
                }
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
                        case "UNITY":
                            IUnityAdsShowListener showListener = new IUnityAdsShowListener() {
                                @Override
                                public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {

                                }

                                @Override
                                public void onUnityAdsShowStart(String placementId) {

                                }

                                @Override
                                public void onUnityAdsShowClick(String placementId) {

                                }

                                @Override
                                public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {

                                }
                            };
                            UnityAds.show(activity, idIntertitialBackup, new UnityAdsShowOptions(), showListener);

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
                            if (PropsAdsManagement.getInterstitialAds() != null) {
                                PropsAdsManagement.getInterstitialAds().show(activity);
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

    public static void ShowIntertitialFAN(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                          int interval) {
        if (counter >= interval) {
            if (FBinterstitialAd == null || !FBinterstitialAd.isAdLoaded()) {
                switch (selectAdsBackup) {
                    case "UNITY":
                        IUnityAdsShowListener showListener = new IUnityAdsShowListener() {
                            @Override
                            public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {

                            }

                            @Override
                            public void onUnityAdsShowStart(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowClick(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {

                            }
                        };
                        UnityAds.show(activity, idIntertitialBackup, new UnityAdsShowOptions(), showListener);

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
                    case "ALIEN-M":
                        if (PropsAdsManagement.getInterstitialAds() != null) {
                            PropsAdsManagement.getInterstitialAds().show(activity);
                        }
                        break;
                    case "FACEBOOK":
                        if (FBinterstitialAd == null || !FBinterstitialAd.isAdLoaded()) {
                        } else {
                            FBinterstitialAd.show();
                        }
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
        if (counter >= interval) {

            IUnityAdsShowListener showListener = new IUnityAdsShowListener() {
                @Override
                public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
                    switch (selectAdsBackup) {
                        case "UNITY":
                            IUnityAdsShowListener showListener = new IUnityAdsShowListener() {
                                @Override
                                public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {

                                }

                                @Override
                                public void onUnityAdsShowStart(String placementId) {

                                }

                                @Override
                                public void onUnityAdsShowClick(String placementId) {

                                }

                                @Override
                                public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {

                                }
                            };
                            UnityAds.show(activity, idIntertitialBackup, new UnityAdsShowOptions(), showListener);
                            break;
                        case "STARTAPP":
                            StartAppAd.showAd(activity);
                            break;
                        case "FACEBOOK":
                            if (FBinterstitialAd == null || !FBinterstitialAd.isAdLoaded()) {
                            } else {
                                FBinterstitialAd.show();
                            }
                            break;
                        case "ALIEN-M":
                            if (PropsAdsManagement.getInterstitialAds() != null) {
                                PropsAdsManagement.getInterstitialAds().show(activity);
                            }
                            break;
                        case "ADMOB":
                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(activity);
                            }
                            break;

                    }
                }

                @Override
                public void onUnityAdsShowStart(String placementId) {

                }

                @Override
                public void onUnityAdsShowClick(String placementId) {

                }

                @Override
                public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {

                }
            };
            UnityAds.show(activity, idIntertitial, new UnityAdsShowOptions(), showListener);
            LoadIntertitialUnity(activity, selectAdsBackup, idIntertitial, idIntertitialBackup);
            counter = 0;
        } else {
            counter++;
        }
    }

    public static void ShowIntertitialAlienView(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                                int interval) {
    }

    public static void ShowIntertitialAlienMediation(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                                     int interval) {
        if (counter >= interval) {
            if (PropsAdsManagement.getInterstitialAds() != null) {
                PropsAdsManagement.getInterstitialAds().show(activity);
            } else {
                switch (selectAdsBackup) {
                    case "UNITY":
                        IUnityAdsShowListener showListener = new IUnityAdsShowListener() {
                            @Override
                            public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {

                            }

                            @Override
                            public void onUnityAdsShowStart(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowClick(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {

                            }
                        };
                        UnityAds.show(activity, idIntertitialBackup, new UnityAdsShowOptions(), showListener);
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
                }
            }

            LoadIntertitialAlienView(activity, selectAdsBackup, idIntertitial, idIntertitialBackup);
            counter = 0;
        } else {
            counter++;
        }
    }

    public static void ShowIntertitialWortise(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                              int interval) {

    }
}
