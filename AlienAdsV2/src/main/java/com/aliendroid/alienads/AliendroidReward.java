package com.aliendroid.alienads;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.aliendroid.alienads.interfaces.rewards.load.OnLoadRewardsAdmob;
import com.aliendroid.alienads.interfaces.rewards.load.OnLoadRewardsAlienView;
import com.aliendroid.alienads.interfaces.rewards.load.OnLoadRewardsApplovinDiscovery;
import com.aliendroid.alienads.interfaces.rewards.load.OnLoadRewardsApplovinMax;
import com.aliendroid.alienads.interfaces.rewards.load.OnLoadRewardsGoogle;
import com.aliendroid.alienads.interfaces.rewards.load.OnLoadRewardsIronSource;
import com.aliendroid.alienads.interfaces.rewards.load.OnLoadRewardsStartApp;
import com.aliendroid.alienads.interfaces.rewards.show.OnShowRewardsAdmob;
import com.aliendroid.alienads.interfaces.rewards.show.OnShowRewardsAlienMedition;
import com.aliendroid.alienads.interfaces.rewards.show.OnShowRewardsAlienView;
import com.aliendroid.alienads.interfaces.rewards.show.OnShowRewardsApplovinDiscovery;
import com.aliendroid.alienads.interfaces.rewards.show.OnShowRewardsGoogle;
import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinSdk;
import com.facebook.ads.Ad;
import com.facebook.ads.RewardedVideoAd;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.props.adsmanager.PropsAdsManagement;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.adlisteners.VideoListener;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.UnityAdsShowOptions;


import java.util.Map;

public class AliendroidReward {
    public static RewardedAd rewardedAdUnity;
    public static RewardedVideoAd rewardedVideoAdFan;
    public static RewardedVideoAd rewardedVideoAdFan2;
    public static MaxRewardedAd rewardedAd;
    public static MaxRewardedAd jamboxRewardedAd;
    public static boolean unlockreward = false;
    public static AppLovinIncentivizedInterstitial incentivizedInterstitial;
    public static StartAppAd rewardedVideo;
    private static RewardedAd mRewardedAd;
    private static RewardedAd mRewardedAd2;
    public static OnLoadRewardsAdmob onLoadRewardsAdmob;
    public static OnLoadRewardsStartApp onLoadRewardsStartApp;
    public static OnLoadRewardsGoogle onLoadRewardsGoogle;
    public static OnLoadRewardsApplovinDiscovery onLoadRewardsApplovinDiscovery;
    public static OnLoadRewardsApplovinMax onLoadRewardsApplovinMax;

    public static OnLoadRewardsAlienView onLoadRewardsAlienView;

    public static OnShowRewardsAdmob onShowRewardsAdmob;
    public static OnShowRewardsGoogle onShowRewardsGoogle;
    public static OnShowRewardsApplovinDiscovery onShowRewardsApplovinDiscovery;
    public static OnShowRewardsAlienMedition onShowRewardsAlienMedition;
    public static OnShowRewardsAlienView onShowRewardsAlienView;

    public static boolean SHOW_ALIEN_VIEW = false;

    public static void LoadRewardFan(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {
        try {

            rewardedVideoAdFan = new RewardedVideoAd(activity, idReward);
            com.facebook.ads.RewardedVideoAdListener rewardedVideoAdListener = new com.facebook.ads.RewardedVideoAdListener() {

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {

                }

                @Override
                public void onAdLoaded(Ad ad) {
                    // Rewarded video ad is loaded and ready to be displayed
                    Log.d(TAG, "Rewarded video ad is loaded and ready to be displayed!");
                }

                @Override
                public void onAdClicked(Ad ad) {
                    // Rewarded video ad clicked
                    Log.d(TAG, "Rewarded video ad clicked!");
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                    // Rewarded Video ad impression - the event will fire when the
                    // video starts playing
                    Log.d(TAG, "Rewarded video ad impression logged!");
                }

                @Override
                public void onRewardedVideoCompleted() {
                    unlockreward = true;
                }

                @Override
                public void onRewardedVideoClosed() {
                    // The Rewarded Video ad was closed - this can occur during the video
                    // by closing the app, or closing the end card.
                    Log.d(TAG, "Rewarded video ad closed!");
                }
            };
            rewardedVideoAdFan.loadAd(
                    rewardedVideoAdFan.buildLoadAdConfig()
                            .withAdListener(rewardedVideoAdListener)
                            .build());

            switch (selectBackupAds) {

                case "APPLOVIN-M":
                    rewardedAd = MaxRewardedAd.getInstance(idBackupReward, activity);
                    rewardedAd.loadAd();
                    MaxRewardedAdListener maxRewardedAdListener = new MaxRewardedAdListener() {
                        @Override
                        public void onRewardedVideoStarted(MaxAd ad) {

                        }

                        @Override
                        public void onRewardedVideoCompleted(MaxAd ad) {
                            unlockreward = true;
                        }

                        @Override
                        public void onUserRewarded(MaxAd ad, MaxReward reward) {

                        }

                        @Override
                        public void onAdLoaded(MaxAd ad) {

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

                        }

                        @Override
                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                        }
                    };
                    rewardedAd.setListener(maxRewardedAdListener);
                    break;

                case "UNITY":
                    IUnityAdsLoadListener loadListener = new IUnityAdsLoadListener() {
                        @Override
                        public void onUnityAdsAdLoaded(String placementId) {

                        }

                        @Override
                        public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {

                        }
                    };
                    try {
                        UnityAds.load(idBackupReward, loadListener);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case "APPLOVIN-D":
                    incentivizedInterstitial = AppLovinIncentivizedInterstitial.create(idBackupReward, AppLovinSdk.getInstance(activity));
                    incentivizedInterstitial.preload(new AppLovinAdLoadListener() {
                        @Override
                        public void adReceived(AppLovinAd appLovinAd) {

                        }

                        @Override
                        public void failedToReceiveAd(int errorCode) {

                        }
                    });
                    break;
                case "STARTAPP":
                    rewardedVideo = new StartAppAd(activity);
                    rewardedVideo.setVideoListener(new VideoListener() {
                        @Override
                        public void onVideoCompleted() {
                            unlockreward = true;
                        }
                    });

                    rewardedVideo.loadAd(StartAppAd.AdMode.REWARDED_VIDEO, new AdEventListener() {
                        @Override
                        public void onReceiveAd(com.startapp.sdk.adsbase.Ad ad) {

                        }

                        @Override
                        public void onFailedToReceiveAd(com.startapp.sdk.adsbase.Ad ad) {

                        }
                    });
                    break;

                case "ALIEN-M":
                    PropsAdsManagement.loadRewardedAds(activity, idBackupReward, new RewardedAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                            Log.i(TAG, "testing rewarded");
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error
                        }
                    });
                    break;
                case "ALIEN-V":
                    jamboxRewardedAd= MaxRewardedAd.getInstance(idBackupReward, activity);
                    jamboxRewardedAd.loadAd();
                    MaxRewardedAdListener jamboxRewardedAdListener = new MaxRewardedAdListener() {
                        @Override
                        public void onRewardedVideoStarted(MaxAd ad) {

                        }

                        @Override
                        public void onRewardedVideoCompleted(MaxAd ad) {
                            unlockreward = true;
                        }

                        @Override
                        public void onUserRewarded(MaxAd ad, MaxReward reward) {

                        }

                        @Override
                        public void onAdLoaded(MaxAd ad) {

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

                        }

                        @Override
                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                        }
                    };
                    jamboxRewardedAd.setListener(jamboxRewardedAdListener);
                    break;

                case "ADMOB":
                    AdRequest adRequest2 = new AdRequest.Builder()
                            .build();
                    RewardedAd.load(activity, idBackupReward,
                            adRequest2, new RewardedAdLoadCallback() {
                                @Override
                                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                    mRewardedAd = null;
                                }

                                @Override
                                public void onAdLoaded(@NonNull RewardedAd rewardedAd) {

                                    mRewardedAd = rewardedAd;

                                }
                            });
                    break;
                case "FACEBOOK":
                    rewardedVideoAdFan2 = new RewardedVideoAd(activity, idBackupReward);
                    com.facebook.ads.RewardedVideoAdListener rewardedVideoAdListener2 = new com.facebook.ads.RewardedVideoAdListener() {

                        @Override
                        public void onError(Ad ad, com.facebook.ads.AdError adError) {

                        }

                        @Override
                        public void onAdLoaded(Ad ad) {
                            // Rewarded video ad is loaded and ready to be displayed
                            Log.d(TAG, "Rewarded video ad is loaded and ready to be displayed!");
                        }

                        @Override
                        public void onAdClicked(Ad ad) {
                            // Rewarded video ad clicked
                            Log.d(TAG, "Rewarded video ad clicked!");
                        }

                        @Override
                        public void onLoggingImpression(Ad ad) {
                            // Rewarded Video ad impression - the event will fire when the
                            // video starts playing
                            Log.d(TAG, "Rewarded video ad impression logged!");
                        }

                        @Override
                        public void onRewardedVideoCompleted() {
                            unlockreward = true;
                        }

                        @Override
                        public void onRewardedVideoClosed() {
                            // The Rewarded Video ad was closed - this can occur during the video
                            // by closing the app, or closing the end card.
                            Log.d(TAG, "Rewarded video ad closed!");
                        }
                    };
                    rewardedVideoAdFan2.loadAd(
                            rewardedVideoAdFan2.buildLoadAdConfig()
                                    .withAdListener(rewardedVideoAdListener2)
                                    .build());
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void LoadRewardAdmob(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {
        try {

            AdRequest adRequest = new AdRequest.Builder()
                    .build();
            RewardedAd.load(activity, idReward,
                    adRequest, new RewardedAdLoadCallback() {
                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            if (onLoadRewardsAdmob != null) {
                                onLoadRewardsAdmob.onAdFailedToLoad();
                            }
                            mRewardedAd = null;
                        }

                        @Override
                        public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                            if (onLoadRewardsAdmob != null) {
                                onLoadRewardsAdmob.onAdLoaded("");
                            }
                            mRewardedAd = rewardedAd;

                        }
                    });
            switch (selectBackupAds) {
                case "APPLOVIN-M":
                    rewardedAd = MaxRewardedAd.getInstance(idBackupReward, activity);
                    rewardedAd.loadAd();
                    MaxRewardedAdListener maxRewardedAdListener = new MaxRewardedAdListener() {
                        @Override
                        public void onRewardedVideoStarted(MaxAd ad) {

                        }

                        @Override
                        public void onRewardedVideoCompleted(MaxAd ad) {
                            unlockreward = true;
                        }

                        @Override
                        public void onUserRewarded(MaxAd ad, MaxReward reward) {

                        }

                        @Override
                        public void onAdLoaded(MaxAd ad) {

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

                        }

                        @Override
                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                        }
                    };
                    rewardedAd.setListener(maxRewardedAdListener);
                    break;
                case "UNITY":
                    IUnityAdsLoadListener loadListener = new IUnityAdsLoadListener() {
                        @Override
                        public void onUnityAdsAdLoaded(String placementId) {

                        }

                        @Override
                        public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {

                        }
                    };
                    try {
                        UnityAds.load(idBackupReward, loadListener);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    break;
                case "APPLOVIN-D":
                    incentivizedInterstitial = AppLovinIncentivizedInterstitial.create(idBackupReward, AppLovinSdk.getInstance(activity));
                    incentivizedInterstitial.preload(new AppLovinAdLoadListener() {
                        @Override
                        public void adReceived(AppLovinAd appLovinAd) {

                        }

                        @Override
                        public void failedToReceiveAd(int errorCode) {

                        }
                    });
                    break;
                case "STARTAPP":
                    rewardedVideo = new StartAppAd(activity);
                    rewardedVideo.setVideoListener(new VideoListener() {
                        @Override
                        public void onVideoCompleted() {
                            unlockreward = true;
                        }
                    });

                    rewardedVideo.loadAd(StartAppAd.AdMode.REWARDED_VIDEO, new AdEventListener() {
                        @Override
                        public void onReceiveAd(com.startapp.sdk.adsbase.Ad ad) {

                        }

                        @Override
                        public void onFailedToReceiveAd(com.startapp.sdk.adsbase.Ad ad) {

                        }
                    });
                    break;

                case "ALIEN-M":
                    PropsAdsManagement.loadRewardedAds(activity, idBackupReward, new RewardedAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                            Log.i(TAG, "testing rewarded");
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error
                        }
                    });
                    break;
                case "ALIEN-V":
                     jamboxRewardedAd= MaxRewardedAd.getInstance(idBackupReward, activity);
                    jamboxRewardedAd.loadAd();
                    MaxRewardedAdListener jamboxRewardedAdListener = new MaxRewardedAdListener() {
                        @Override
                        public void onRewardedVideoStarted(MaxAd ad) {

                        }

                        @Override
                        public void onRewardedVideoCompleted(MaxAd ad) {
                            unlockreward = true;
                        }

                        @Override
                        public void onUserRewarded(MaxAd ad, MaxReward reward) {

                        }

                        @Override
                        public void onAdLoaded(MaxAd ad) {

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

                        }

                        @Override
                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                        }
                    };
                    jamboxRewardedAd.setListener(jamboxRewardedAdListener);
                    break;

                case "ADMOB":
                    AdRequest adRequest2 = new AdRequest.Builder()
                            .build();
                    RewardedAd.load(activity, idBackupReward,
                            adRequest2, new RewardedAdLoadCallback() {
                                @Override
                                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                    mRewardedAd2 = null;
                                }

                                @Override
                                public void onAdLoaded(@NonNull RewardedAd rewardedAd) {

                                    mRewardedAd2 = rewardedAd;

                                }
                            });
                    break;
                case "FACEBOOK":
                    rewardedVideoAdFan = new RewardedVideoAd(activity, idBackupReward);
                    com.facebook.ads.RewardedVideoAdListener rewardedVideoAdListener2 = new com.facebook.ads.RewardedVideoAdListener() {

                        @Override
                        public void onError(Ad ad, com.facebook.ads.AdError adError) {

                        }

                        @Override
                        public void onAdLoaded(Ad ad) {
                            // Rewarded video ad is loaded and ready to be displayed
                            Log.d(TAG, "Rewarded video ad is loaded and ready to be displayed!");
                        }

                        @Override
                        public void onAdClicked(Ad ad) {
                            // Rewarded video ad clicked
                            Log.d(TAG, "Rewarded video ad clicked!");
                        }

                        @Override
                        public void onLoggingImpression(Ad ad) {
                            // Rewarded Video ad impression - the event will fire when the
                            // video starts playing
                            Log.d(TAG, "Rewarded video ad impression logged!");
                        }

                        @Override
                        public void onRewardedVideoCompleted() {
                            unlockreward = true;
                        }

                        @Override
                        public void onRewardedVideoClosed() {
                            // The Rewarded Video ad was closed - this can occur during the video
                            // by closing the app, or closing the end card.
                            Log.d(TAG, "Rewarded video ad closed!");
                        }
                    };
                    rewardedVideoAdFan.loadAd(
                            rewardedVideoAdFan.buildLoadAdConfig()
                                    .withAdListener(rewardedVideoAdListener2)
                                    .build());
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void LoadRewardAlienMediation(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {
        try {
            PropsAdsManagement.loadRewardedAds(activity, idReward, new RewardedAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                    Log.i(TAG, "testing rewarded");
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    // Handle the error
                }
            });
            switch (selectBackupAds) {
                case "FACEBOOK":
                    rewardedVideoAdFan = new RewardedVideoAd(activity, idBackupReward);
                    com.facebook.ads.RewardedVideoAdListener rewardedVideoAdListener2 = new com.facebook.ads.RewardedVideoAdListener() {

                        @Override
                        public void onError(Ad ad, com.facebook.ads.AdError adError) {

                        }

                        @Override
                        public void onAdLoaded(Ad ad) {
                            // Rewarded video ad is loaded and ready to be displayed
                            Log.d(TAG, "Rewarded video ad is loaded and ready to be displayed!");
                        }

                        @Override
                        public void onAdClicked(Ad ad) {
                            // Rewarded video ad clicked
                            Log.d(TAG, "Rewarded video ad clicked!");
                        }

                        @Override
                        public void onLoggingImpression(Ad ad) {
                            // Rewarded Video ad impression - the event will fire when the
                            // video starts playing
                            Log.d(TAG, "Rewarded video ad impression logged!");
                        }

                        @Override
                        public void onRewardedVideoCompleted() {
                            unlockreward = true;
                        }

                        @Override
                        public void onRewardedVideoClosed() {
                            // The Rewarded Video ad was closed - this can occur during the video
                            // by closing the app, or closing the end card.
                            Log.d(TAG, "Rewarded video ad closed!");
                        }
                    };
                    rewardedVideoAdFan.loadAd(
                            rewardedVideoAdFan.buildLoadAdConfig()
                                    .withAdListener(rewardedVideoAdListener2)
                                    .build());
                    break;
                case "APPLOVIN-M":
                    rewardedAd = MaxRewardedAd.getInstance(idBackupReward, activity);
                    rewardedAd.loadAd();
                    MaxRewardedAdListener maxRewardedAdListener = new MaxRewardedAdListener() {
                        @Override
                        public void onRewardedVideoStarted(MaxAd ad) {

                        }

                        @Override
                        public void onRewardedVideoCompleted(MaxAd ad) {
                            unlockreward = true;
                        }

                        @Override
                        public void onUserRewarded(MaxAd ad, MaxReward reward) {

                        }

                        @Override
                        public void onAdLoaded(MaxAd ad) {

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

                        }

                        @Override
                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                        }
                    };
                    rewardedAd.setListener(maxRewardedAdListener);
                    break;


                case "UNITY":
                    IUnityAdsLoadListener loadListener = new IUnityAdsLoadListener() {
                        @Override
                        public void onUnityAdsAdLoaded(String placementId) {

                        }

                        @Override
                        public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {

                        }
                    };
                    try {
                        UnityAds.load(idBackupReward, loadListener);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case "APPLOVIN-D":
                    incentivizedInterstitial = AppLovinIncentivizedInterstitial.create(idBackupReward, AppLovinSdk.getInstance(activity));
                    incentivizedInterstitial.preload(new AppLovinAdLoadListener() {
                        @Override
                        public void adReceived(AppLovinAd appLovinAd) {

                        }

                        @Override
                        public void failedToReceiveAd(int errorCode) {

                        }
                    });
                    break;
                case "STARTAPP":
                    rewardedVideo = new StartAppAd(activity);
                    rewardedVideo.setVideoListener(new VideoListener() {
                        @Override
                        public void onVideoCompleted() {
                            unlockreward = true;
                        }
                    });

                    rewardedVideo.loadAd(StartAppAd.AdMode.REWARDED_VIDEO, new AdEventListener() {
                        @Override
                        public void onReceiveAd(com.startapp.sdk.adsbase.Ad ad) {

                        }

                        @Override
                        public void onFailedToReceiveAd(com.startapp.sdk.adsbase.Ad ad) {

                        }
                    });
                    break;

                case "ADMOB":
                    AdRequest adRequest = new AdRequest.Builder()
                            .build();
                    RewardedAd.load(activity, idBackupReward,
                            adRequest, new RewardedAdLoadCallback() {
                                @Override
                                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                    if (onLoadRewardsAdmob != null) {
                                        onLoadRewardsAdmob.onAdFailedToLoad();
                                    }
                                    mRewardedAd = null;
                                }

                                @Override
                                public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                                    if (onLoadRewardsAdmob != null) {
                                        onLoadRewardsAdmob.onAdLoaded("");
                                    }
                                    mRewardedAd = rewardedAd;

                                }
                            });
                    break;
                case "ALIEN-V":
                     jamboxRewardedAd= MaxRewardedAd.getInstance(idBackupReward, activity);
                    jamboxRewardedAd.loadAd();
                    MaxRewardedAdListener jamboxRewardedAdListener = new MaxRewardedAdListener() {
                        @Override
                        public void onRewardedVideoStarted(MaxAd ad) {

                        }

                        @Override
                        public void onRewardedVideoCompleted(MaxAd ad) {
                            unlockreward = true;
                        }

                        @Override
                        public void onUserRewarded(MaxAd ad, MaxReward reward) {

                        }

                        @Override
                        public void onAdLoaded(MaxAd ad) {

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

                        }

                        @Override
                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                        }
                    };
                    jamboxRewardedAd.setListener(jamboxRewardedAdListener);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void LoadRewardAlienView(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {
        try {
            jamboxRewardedAd= MaxRewardedAd.getInstance(idReward, activity);
            jamboxRewardedAd.loadAd();
            MaxRewardedAdListener jamboxRewardedAdListener = new MaxRewardedAdListener() {
                @Override
                public void onRewardedVideoStarted(MaxAd ad) {

                }

                @Override
                public void onRewardedVideoCompleted(MaxAd ad) {
                    unlockreward = true;
                }

                @Override
                public void onUserRewarded(MaxAd ad, MaxReward reward) {

                }

                @Override
                public void onAdLoaded(MaxAd ad) {

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

                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                }
            };
            jamboxRewardedAd.setListener(jamboxRewardedAdListener);
            switch (selectBackupAds) {
                case "FACEBOOK":
                    rewardedVideoAdFan = new RewardedVideoAd(activity, idBackupReward);
                    com.facebook.ads.RewardedVideoAdListener rewardedVideoAdListener2 = new com.facebook.ads.RewardedVideoAdListener() {

                        @Override
                        public void onError(Ad ad, com.facebook.ads.AdError adError) {

                        }

                        @Override
                        public void onAdLoaded(Ad ad) {
                            // Rewarded video ad is loaded and ready to be displayed
                            Log.d(TAG, "Rewarded video ad is loaded and ready to be displayed!");
                        }

                        @Override
                        public void onAdClicked(Ad ad) {
                            // Rewarded video ad clicked
                            Log.d(TAG, "Rewarded video ad clicked!");
                        }

                        @Override
                        public void onLoggingImpression(Ad ad) {
                            // Rewarded Video ad impression - the event will fire when the
                            // video starts playing
                            Log.d(TAG, "Rewarded video ad impression logged!");
                        }

                        @Override
                        public void onRewardedVideoCompleted() {
                            unlockreward = true;
                        }

                        @Override
                        public void onRewardedVideoClosed() {
                            // The Rewarded Video ad was closed - this can occur during the video
                            // by closing the app, or closing the end card.
                            Log.d(TAG, "Rewarded video ad closed!");
                        }
                    };
                    rewardedVideoAdFan.loadAd(
                            rewardedVideoAdFan.buildLoadAdConfig()
                                    .withAdListener(rewardedVideoAdListener2)
                                    .build());
                    break;
                case "APPLOVIN-M":
                    rewardedAd = MaxRewardedAd.getInstance(idBackupReward, activity);
                    rewardedAd.loadAd();
                    MaxRewardedAdListener maxRewardedAdListener = new MaxRewardedAdListener() {
                        @Override
                        public void onRewardedVideoStarted(MaxAd ad) {

                        }

                        @Override
                        public void onRewardedVideoCompleted(MaxAd ad) {
                            unlockreward = true;
                        }

                        @Override
                        public void onUserRewarded(MaxAd ad, MaxReward reward) {

                        }

                        @Override
                        public void onAdLoaded(MaxAd ad) {

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

                        }

                        @Override
                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                        }
                    };
                    rewardedAd.setListener(maxRewardedAdListener);
                    break;
                case "UNITY":
                    IUnityAdsLoadListener loadListener = new IUnityAdsLoadListener() {
                        @Override
                        public void onUnityAdsAdLoaded(String placementId) {

                        }

                        @Override
                        public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {

                        }
                    };
                    try {
                        UnityAds.load(idBackupReward, loadListener);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case "APPLOVIN-D":
                    incentivizedInterstitial = AppLovinIncentivizedInterstitial.create(idBackupReward, AppLovinSdk.getInstance(activity));
                    incentivizedInterstitial.preload(new AppLovinAdLoadListener() {
                        @Override
                        public void adReceived(AppLovinAd appLovinAd) {

                        }

                        @Override
                        public void failedToReceiveAd(int errorCode) {

                        }
                    });
                    break;
                case "STARTAPP":
                    rewardedVideo = new StartAppAd(activity);
                    rewardedVideo.setVideoListener(new VideoListener() {
                        @Override
                        public void onVideoCompleted() {
                            unlockreward = true;
                        }
                    });

                    rewardedVideo.loadAd(StartAppAd.AdMode.REWARDED_VIDEO, new AdEventListener() {
                        @Override
                        public void onReceiveAd(com.startapp.sdk.adsbase.Ad ad) {

                        }

                        @Override
                        public void onFailedToReceiveAd(com.startapp.sdk.adsbase.Ad ad) {

                        }
                    });
                    break;

                case "ADMOB":
                    AdRequest adRequest = new AdRequest.Builder()
                            .build();
                    RewardedAd.load(activity, idBackupReward,
                            adRequest, new RewardedAdLoadCallback() {
                                @Override
                                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                    if (onLoadRewardsAdmob != null) {
                                        onLoadRewardsAdmob.onAdFailedToLoad();
                                    }
                                    mRewardedAd = null;
                                }

                                @Override
                                public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                                    if (onLoadRewardsAdmob != null) {
                                        onLoadRewardsAdmob.onAdLoaded("");
                                    }
                                    mRewardedAd = rewardedAd;

                                }
                            });
                    break;
                case "ALIEN-M":
                    PropsAdsManagement.loadRewardedAds(activity, idBackupReward, new RewardedAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                            Log.i(TAG, "testing rewarded");
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error
                        }
                    });
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void LoadRewardUnity(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {
        IUnityAdsLoadListener loadListener = new IUnityAdsLoadListener() {
            @Override
            public void onUnityAdsAdLoaded(String placementId) {

            }

            @Override
            public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {

            }
        };
        try {
            UnityAds.load(idReward, loadListener);
        } catch (Exception e) {
            e.printStackTrace();
        }

        switch (selectBackupAds) {
            case "ADMOB":
                AdRequest adRequest = new AdRequest.Builder()
                        .build();
                RewardedAd.load(activity, idBackupReward,
                        adRequest, new RewardedAdLoadCallback() {
                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                if (onLoadRewardsAdmob != null) {
                                    onLoadRewardsAdmob.onAdFailedToLoad();
                                }
                                mRewardedAd = null;
                            }

                            @Override
                            public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                                if (onLoadRewardsAdmob != null) {
                                    onLoadRewardsAdmob.onAdLoaded("");
                                }
                                mRewardedAd = rewardedAd;

                            }
                        });
                break;
            case "FACEBOOK":
                rewardedVideoAdFan = new RewardedVideoAd(activity, idBackupReward);
                com.facebook.ads.RewardedVideoAdListener rewardedVideoAdListener2 = new com.facebook.ads.RewardedVideoAdListener() {

                    @Override
                    public void onError(Ad ad, com.facebook.ads.AdError adError) {

                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        // Rewarded video ad is loaded and ready to be displayed
                        Log.d(TAG, "Rewarded video ad is loaded and ready to be displayed!");
                    }

                    @Override
                    public void onAdClicked(Ad ad) {
                        // Rewarded video ad clicked
                        Log.d(TAG, "Rewarded video ad clicked!");
                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {
                        // Rewarded Video ad impression - the event will fire when the
                        // video starts playing
                        Log.d(TAG, "Rewarded video ad impression logged!");
                    }

                    @Override
                    public void onRewardedVideoCompleted() {
                        unlockreward = true;
                    }

                    @Override
                    public void onRewardedVideoClosed() {
                        // The Rewarded Video ad was closed - this can occur during the video
                        // by closing the app, or closing the end card.
                        Log.d(TAG, "Rewarded video ad closed!");
                    }
                };
                rewardedVideoAdFan.loadAd(
                        rewardedVideoAdFan.buildLoadAdConfig()
                                .withAdListener(rewardedVideoAdListener2)
                                .build());
                break;
            case "APPLOVIN-M":
                rewardedAd = MaxRewardedAd.getInstance(idBackupReward, activity);
                rewardedAd.loadAd();
                MaxRewardedAdListener maxRewardedAdListener = new MaxRewardedAdListener() {
                    @Override
                    public void onRewardedVideoStarted(MaxAd ad) {

                    }

                    @Override
                    public void onRewardedVideoCompleted(MaxAd ad) {
                        unlockreward = true;
                    }

                    @Override
                    public void onUserRewarded(MaxAd ad, MaxReward reward) {

                    }

                    @Override
                    public void onAdLoaded(MaxAd ad) {

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

                    }

                    @Override
                    public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                    }
                };
                rewardedAd.setListener(maxRewardedAdListener);
                break;
            case "UNITY":
                IUnityAdsLoadListener loadListener2 = new IUnityAdsLoadListener() {
                    @Override
                    public void onUnityAdsAdLoaded(String placementId) {

                    }

                    @Override
                    public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {

                    }
                };
                try {
                    UnityAds.load(idBackupReward, loadListener2);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case "APPLOVIN-D":
                incentivizedInterstitial = AppLovinIncentivizedInterstitial.create(idBackupReward, AppLovinSdk.getInstance(activity));
                incentivizedInterstitial.preload(new AppLovinAdLoadListener() {
                    @Override
                    public void adReceived(AppLovinAd appLovinAd) {
                        // A rewarded video was successfully received.
                    }

                    @Override
                    public void failedToReceiveAd(int errorCode) {
                        // A rewarded video failed to load.
                    }
                });
                break;
            case "STARTAPP":
                rewardedVideo = new StartAppAd(activity);
                rewardedVideo.setVideoListener(new VideoListener() {
                    @Override
                    public void onVideoCompleted() {
                        unlockreward = true;
                    }
                });

                rewardedVideo.loadAd(StartAppAd.AdMode.REWARDED_VIDEO, new AdEventListener() {
                    @Override
                    public void onReceiveAd(com.startapp.sdk.adsbase.Ad ad) {

                    }

                    @Override
                    public void onFailedToReceiveAd(com.startapp.sdk.adsbase.Ad ad) {

                    }
                });
                break;
            case "ALIEN-M":
                PropsAdsManagement.loadRewardedAds(activity, idBackupReward, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        Log.i(TAG, "testing rewarded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                    }
                });
                break;
            case "ALIEN-V":
                jamboxRewardedAd= MaxRewardedAd.getInstance(idBackupReward, activity);
                jamboxRewardedAd.loadAd();
                MaxRewardedAdListener jamboxRewardedAdListener = new MaxRewardedAdListener() {
                    @Override
                    public void onRewardedVideoStarted(MaxAd ad) {

                    }

                    @Override
                    public void onRewardedVideoCompleted(MaxAd ad) {
                        unlockreward = true;
                    }

                    @Override
                    public void onUserRewarded(MaxAd ad, MaxReward reward) {

                    }

                    @Override
                    public void onAdLoaded(MaxAd ad) {

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

                    }

                    @Override
                    public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                    }
                };
                jamboxRewardedAd.setListener(jamboxRewardedAdListener);
                break;

        }
    }

    public static void LoadRewardGoogleAds(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {
        try {
            AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();
            RewardedAd.load(activity, idReward,
                    adRequest, new RewardedAdLoadCallback() {
                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            if (onLoadRewardsGoogle != null) {
                                onLoadRewardsGoogle.onAdFailedToLoad();
                            }
                            mRewardedAd = null;
                        }

                        @Override
                        public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                            if (onLoadRewardsGoogle != null) {
                                onLoadRewardsGoogle.onAdFailedToLoad();
                            }
                            mRewardedAd = rewardedAd;

                        }
                    });
            switch (selectBackupAds) {
                case "FACEBOOK":
                    rewardedVideoAdFan = new RewardedVideoAd(activity, idBackupReward);
                    com.facebook.ads.RewardedVideoAdListener rewardedVideoAdListener2 = new com.facebook.ads.RewardedVideoAdListener() {

                        @Override
                        public void onError(Ad ad, com.facebook.ads.AdError adError) {

                        }

                        @Override
                        public void onAdLoaded(Ad ad) {
                            // Rewarded video ad is loaded and ready to be displayed
                            Log.d(TAG, "Rewarded video ad is loaded and ready to be displayed!");
                        }

                        @Override
                        public void onAdClicked(Ad ad) {
                            // Rewarded video ad clicked
                            Log.d(TAG, "Rewarded video ad clicked!");
                        }

                        @Override
                        public void onLoggingImpression(Ad ad) {
                            // Rewarded Video ad impression - the event will fire when the
                            // video starts playing
                            Log.d(TAG, "Rewarded video ad impression logged!");
                        }

                        @Override
                        public void onRewardedVideoCompleted() {
                            unlockreward = true;
                        }

                        @Override
                        public void onRewardedVideoClosed() {
                            // The Rewarded Video ad was closed - this can occur during the video
                            // by closing the app, or closing the end card.
                            Log.d(TAG, "Rewarded video ad closed!");
                        }
                    };
                    rewardedVideoAdFan.loadAd(
                            rewardedVideoAdFan.buildLoadAdConfig()
                                    .withAdListener(rewardedVideoAdListener2)
                                    .build());
                    break;
                case "APPLOVIN-M":
                    rewardedAd = MaxRewardedAd.getInstance(idBackupReward, activity);
                    rewardedAd.loadAd();
                    MaxRewardedAdListener maxRewardedAdListener = new MaxRewardedAdListener() {
                        @Override
                        public void onRewardedVideoStarted(MaxAd ad) {

                        }

                        @Override
                        public void onRewardedVideoCompleted(MaxAd ad) {
                            unlockreward = true;
                        }

                        @Override
                        public void onUserRewarded(MaxAd ad, MaxReward reward) {

                        }

                        @Override
                        public void onAdLoaded(MaxAd ad) {

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

                        }

                        @Override
                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                        }
                    };
                    rewardedAd.setListener(maxRewardedAdListener);
                    break;
                case "UNITY":
                    IUnityAdsLoadListener loadListener = new IUnityAdsLoadListener() {
                        @Override
                        public void onUnityAdsAdLoaded(String placementId) {

                        }

                        @Override
                        public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {

                        }
                    };
                    try {
                        UnityAds.load(idBackupReward, loadListener);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case "APPLOVIN-D":
                    incentivizedInterstitial = AppLovinIncentivizedInterstitial.create(idBackupReward, AppLovinSdk.getInstance(activity));
                    incentivizedInterstitial.preload(new AppLovinAdLoadListener() {
                        @Override
                        public void adReceived(AppLovinAd appLovinAd) {
                            // A rewarded video was successfully received.
                        }

                        @Override
                        public void failedToReceiveAd(int errorCode) {
                            // A rewarded video failed to load.
                        }
                    });
                    break;
                case "STARTAPP":
                    rewardedVideo = new StartAppAd(activity);
                    rewardedVideo.setVideoListener(new VideoListener() {
                        @Override
                        public void onVideoCompleted() {
                            unlockreward = true;
                        }
                    });

                    rewardedVideo.loadAd(StartAppAd.AdMode.REWARDED_VIDEO, new AdEventListener() {
                        @Override
                        public void onReceiveAd(com.startapp.sdk.adsbase.Ad ad) {

                        }

                        @Override
                        public void onFailedToReceiveAd(com.startapp.sdk.adsbase.Ad ad) {

                        }
                    });
                    break;
                case "ALIEN-M":
                    PropsAdsManagement.loadRewardedAds(activity, idBackupReward, new RewardedAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                            Log.i(TAG, "testing rewarded");
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error
                        }
                    });
                    break;
                case "ALIEN-V":
                     jamboxRewardedAd= MaxRewardedAd.getInstance(idBackupReward, activity);
                    jamboxRewardedAd.loadAd();
                    MaxRewardedAdListener jamboxRewardedAdListener = new MaxRewardedAdListener() {
                        @Override
                        public void onRewardedVideoStarted(MaxAd ad) {

                        }

                        @Override
                        public void onRewardedVideoCompleted(MaxAd ad) {
                            unlockreward = true;
                        }

                        @Override
                        public void onUserRewarded(MaxAd ad, MaxReward reward) {

                        }

                        @Override
                        public void onAdLoaded(MaxAd ad) {

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

                        }

                        @Override
                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                        }
                    };
                    jamboxRewardedAd.setListener(jamboxRewardedAdListener);
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void LoadRewardApplovinMax(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {
        try {
            rewardedAd = MaxRewardedAd.getInstance(idReward, activity);
            rewardedAd.loadAd();
            MaxRewardedAdListener maxRewardedAdListener = new MaxRewardedAdListener() {
                @Override
                public void onRewardedVideoStarted(MaxAd ad) {
                    if (onLoadRewardsApplovinMax != null) {
                        onLoadRewardsApplovinMax.onRewardedVideoStarted();
                    }
                }

                @Override
                public void onRewardedVideoCompleted(MaxAd ad) {
                    if (onLoadRewardsApplovinMax != null) {
                        onLoadRewardsApplovinMax.onRewardedVideoCompleted();
                    }
                    unlockreward = true;
                }

                @Override
                public void onUserRewarded(MaxAd ad, MaxReward reward) {
                    if (onLoadRewardsApplovinMax != null) {
                        onLoadRewardsApplovinMax.onUserRewarded();
                    }
                }

                @Override
                public void onAdLoaded(MaxAd ad) {
                    if (onLoadRewardsApplovinMax != null) {
                        onLoadRewardsApplovinMax.onAdLoaded();
                    }
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {
                    if (onLoadRewardsApplovinMax != null) {
                        onLoadRewardsApplovinMax.onAdDisplayed();
                    }
                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    if (onLoadRewardsApplovinMax != null) {
                        onLoadRewardsApplovinMax.onAdHidden();
                    }
                }

                @Override
                public void onAdClicked(MaxAd ad) {
                    if (onLoadRewardsApplovinMax != null) {
                        onLoadRewardsApplovinMax.onAdClicked();
                    }
                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    if (onLoadRewardsApplovinMax != null) {
                        onLoadRewardsApplovinMax.onAdLoadFailed();
                    }
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                    if (onLoadRewardsApplovinMax != null) {
                        onLoadRewardsApplovinMax.onAdDisplayFailed();
                    }
                }
            };
            rewardedAd.setListener(maxRewardedAdListener);
            switch (selectBackupAds) {
                case "FACEBOOK":
                    rewardedVideoAdFan = new RewardedVideoAd(activity, idBackupReward);
                    com.facebook.ads.RewardedVideoAdListener rewardedVideoAdListener2 = new com.facebook.ads.RewardedVideoAdListener() {

                        @Override
                        public void onError(Ad ad, com.facebook.ads.AdError adError) {

                        }

                        @Override
                        public void onAdLoaded(Ad ad) {
                            // Rewarded video ad is loaded and ready to be displayed
                            Log.d(TAG, "Rewarded video ad is loaded and ready to be displayed!");
                        }

                        @Override
                        public void onAdClicked(Ad ad) {
                            // Rewarded video ad clicked
                            Log.d(TAG, "Rewarded video ad clicked!");
                        }

                        @Override
                        public void onLoggingImpression(Ad ad) {
                            // Rewarded Video ad impression - the event will fire when the
                            // video starts playing
                            Log.d(TAG, "Rewarded video ad impression logged!");
                        }

                        @Override
                        public void onRewardedVideoCompleted() {
                            unlockreward = true;
                        }

                        @Override
                        public void onRewardedVideoClosed() {
                            // The Rewarded Video ad was closed - this can occur during the video
                            // by closing the app, or closing the end card.
                            Log.d(TAG, "Rewarded video ad closed!");
                        }
                    };
                    rewardedVideoAdFan.loadAd(
                            rewardedVideoAdFan.buildLoadAdConfig()
                                    .withAdListener(rewardedVideoAdListener2)
                                    .build());
                    break;
                case "ADMOB":
                case "GOOGLE-ADS":

                    AdRequest adRequest = new AdRequest.Builder()

                            .build();
                    RewardedAd.load(activity, idBackupReward,
                            adRequest, new RewardedAdLoadCallback() {
                                @Override
                                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                    mRewardedAd = null;
                                }

                                @Override
                                public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                                    mRewardedAd = rewardedAd;

                                }
                            });
                    break;
                case "UNITY":
                    IUnityAdsLoadListener loadListener = new IUnityAdsLoadListener() {
                        @Override
                        public void onUnityAdsAdLoaded(String placementId) {

                        }

                        @Override
                        public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {

                        }
                    };
                    try {
                        UnityAds.load(idBackupReward, loadListener);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case "APPLOVIN-D":
                    incentivizedInterstitial = AppLovinIncentivizedInterstitial.create(idBackupReward, AppLovinSdk.getInstance(activity));
                    incentivizedInterstitial.preload(new AppLovinAdLoadListener() {
                        @Override
                        public void adReceived(AppLovinAd appLovinAd) {
                            // A rewarded video was successfully received.
                        }

                        @Override
                        public void failedToReceiveAd(int errorCode) {
                            // A rewarded video failed to load.
                        }
                    });
                    break;
                case "STARTAPP":
                    rewardedVideo = new StartAppAd(activity);
                    rewardedVideo.setVideoListener(new VideoListener() {
                        @Override
                        public void onVideoCompleted() {
                            unlockreward = true;
                        }
                    });

                    rewardedVideo.loadAd(StartAppAd.AdMode.REWARDED_VIDEO, new AdEventListener() {
                        @Override
                        public void onReceiveAd(com.startapp.sdk.adsbase.Ad ad) {

                        }

                        @Override
                        public void onFailedToReceiveAd(com.startapp.sdk.adsbase.Ad ad) {

                        }
                    });
                    break;
                case "ALIEN-M":
                    PropsAdsManagement.loadRewardedAds(activity, idBackupReward, new RewardedAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                            Log.i(TAG, "testing rewarded");
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error
                        }
                    });
                    break;
                case "ALIEN-V":
                     jamboxRewardedAd= MaxRewardedAd.getInstance(idBackupReward, activity);
                    jamboxRewardedAd.loadAd();
                    MaxRewardedAdListener jamboxRewardedAdListener = new MaxRewardedAdListener() {
                        @Override
                        public void onRewardedVideoStarted(MaxAd ad) {

                        }

                        @Override
                        public void onRewardedVideoCompleted(MaxAd ad) {
                            unlockreward = true;
                        }

                        @Override
                        public void onUserRewarded(MaxAd ad, MaxReward reward) {

                        }

                        @Override
                        public void onAdLoaded(MaxAd ad) {

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

                        }

                        @Override
                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                        }
                    };
                    jamboxRewardedAd.setListener(jamboxRewardedAdListener);
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void LoadRewardApplovinDis(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {
        try {
            incentivizedInterstitial = AppLovinIncentivizedInterstitial.create(idReward, AppLovinSdk.getInstance(activity));
            incentivizedInterstitial.preload(new AppLovinAdLoadListener() {
                @Override
                public void adReceived(AppLovinAd appLovinAd) {
                    if (onLoadRewardsApplovinDiscovery != null) {
                        onLoadRewardsApplovinDiscovery.adReceived();
                    }
                }

                @Override
                public void failedToReceiveAd(int errorCode) {
                    if (onLoadRewardsApplovinDiscovery != null) {
                        onLoadRewardsApplovinDiscovery.failedToReceiveAd("");
                    }
                }
            });
            switch (selectBackupAds) {
                case "FACEBOOK":
                    rewardedVideoAdFan = new RewardedVideoAd(activity, idBackupReward);
                    com.facebook.ads.RewardedVideoAdListener rewardedVideoAdListener2 = new com.facebook.ads.RewardedVideoAdListener() {

                        @Override
                        public void onError(Ad ad, com.facebook.ads.AdError adError) {

                        }

                        @Override
                        public void onAdLoaded(Ad ad) {
                            // Rewarded video ad is loaded and ready to be displayed
                            Log.d(TAG, "Rewarded video ad is loaded and ready to be displayed!");
                        }

                        @Override
                        public void onAdClicked(Ad ad) {
                            // Rewarded video ad clicked
                            Log.d(TAG, "Rewarded video ad clicked!");
                        }

                        @Override
                        public void onLoggingImpression(Ad ad) {
                            // Rewarded Video ad impression - the event will fire when the
                            // video starts playing
                            Log.d(TAG, "Rewarded video ad impression logged!");
                        }

                        @Override
                        public void onRewardedVideoCompleted() {
                            unlockreward = true;
                        }

                        @Override
                        public void onRewardedVideoClosed() {
                            // The Rewarded Video ad was closed - this can occur during the video
                            // by closing the app, or closing the end card.
                            Log.d(TAG, "Rewarded video ad closed!");
                        }
                    };
                    rewardedVideoAdFan.loadAd(
                            rewardedVideoAdFan.buildLoadAdConfig()
                                    .withAdListener(rewardedVideoAdListener2)
                                    .build());
                    break;
                case "ADMOB":
                case "GOOGLE-ADS":
                    AdRequest adRequest = new AdRequest.Builder()

                            .build();
                    RewardedAd.load(activity, idBackupReward,
                            adRequest, new RewardedAdLoadCallback() {
                                @Override
                                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                    mRewardedAd = null;
                                }

                                @Override
                                public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                                    mRewardedAd = rewardedAd;

                                }
                            });
                    break;
                case "UNITY":
                    IUnityAdsLoadListener loadListener = new IUnityAdsLoadListener() {
                        @Override
                        public void onUnityAdsAdLoaded(String placementId) {

                        }

                        @Override
                        public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {

                        }
                    };
                    try {
                        UnityAds.load(idBackupReward, loadListener);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case "APPLOVIN-M":
                    rewardedAd = MaxRewardedAd.getInstance(idBackupReward, activity);
                    rewardedAd.loadAd();
                    MaxRewardedAdListener maxRewardedAdListener = new MaxRewardedAdListener() {
                        @Override
                        public void onRewardedVideoStarted(MaxAd ad) {

                        }

                        @Override
                        public void onRewardedVideoCompleted(MaxAd ad) {
                            unlockreward = true;
                        }

                        @Override
                        public void onUserRewarded(MaxAd ad, MaxReward reward) {

                        }

                        @Override
                        public void onAdLoaded(MaxAd ad) {

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

                        }

                        @Override
                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                        }
                    };
                    rewardedAd.setListener(maxRewardedAdListener);

                    break;
                case "STARTAPP":
                    rewardedVideo = new StartAppAd(activity);
                    rewardedVideo.setVideoListener(new VideoListener() {
                        @Override
                        public void onVideoCompleted() {
                            unlockreward = true;
                        }
                    });

                    rewardedVideo.loadAd(StartAppAd.AdMode.REWARDED_VIDEO, new AdEventListener() {
                        @Override
                        public void onReceiveAd(com.startapp.sdk.adsbase.Ad ad) {

                        }

                        @Override
                        public void onFailedToReceiveAd(com.startapp.sdk.adsbase.Ad ad) {

                        }
                    });
                    break;
                case "ALIEN-M":
                    PropsAdsManagement.loadRewardedAds(activity, idBackupReward, new RewardedAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                            Log.i(TAG, "testing rewarded");
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error
                        }
                    });
                    break;
                case "ALIEN-V":
                    jamboxRewardedAd= MaxRewardedAd.getInstance(idBackupReward, activity);
                    jamboxRewardedAd.loadAd();
                    MaxRewardedAdListener jamboxRewardedAdListener = new MaxRewardedAdListener() {
                        @Override
                        public void onRewardedVideoStarted(MaxAd ad) {

                        }

                        @Override
                        public void onRewardedVideoCompleted(MaxAd ad) {
                            unlockreward = true;
                        }

                        @Override
                        public void onUserRewarded(MaxAd ad, MaxReward reward) {

                        }

                        @Override
                        public void onAdLoaded(MaxAd ad) {

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

                        }

                        @Override
                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                        }
                    };
                    jamboxRewardedAd.setListener(jamboxRewardedAdListener);
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void LoadRewardMopub(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {

    }

    public static void LoadRewardIron(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {
    }

    public static void LoadRewardStartApp(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {
        try {
            rewardedVideo = new StartAppAd(activity);
            rewardedVideo.setVideoListener(new VideoListener() {
                @Override
                public void onVideoCompleted() {
                    if (onLoadRewardsStartApp != null) {
                        onLoadRewardsStartApp.onVideoCompleted();
                    }
                    unlockreward = true;
                }
            });

            rewardedVideo.loadAd(StartAppAd.AdMode.REWARDED_VIDEO, new AdEventListener() {
                @Override
                public void onReceiveAd(com.startapp.sdk.adsbase.Ad ad) {
                    if (onLoadRewardsStartApp != null) {
                        onLoadRewardsStartApp.onReceiveAd();
                    }

                }

                @Override
                public void onFailedToReceiveAd(com.startapp.sdk.adsbase.Ad ad) {
                    if (onLoadRewardsStartApp != null) {
                        onLoadRewardsStartApp.onFailedToReceiveAd();
                    }
                }
            });
            switch (selectBackupAds) {
                case "FACEBOOK":
                    rewardedVideoAdFan = new RewardedVideoAd(activity, idBackupReward);
                    com.facebook.ads.RewardedVideoAdListener rewardedVideoAdListener2 = new com.facebook.ads.RewardedVideoAdListener() {

                        @Override
                        public void onError(Ad ad, com.facebook.ads.AdError adError) {

                        }

                        @Override
                        public void onAdLoaded(Ad ad) {
                            // Rewarded video ad is loaded and ready to be displayed
                            Log.d(TAG, "Rewarded video ad is loaded and ready to be displayed!");
                        }

                        @Override
                        public void onAdClicked(Ad ad) {
                            // Rewarded video ad clicked
                            Log.d(TAG, "Rewarded video ad clicked!");
                        }

                        @Override
                        public void onLoggingImpression(Ad ad) {
                            // Rewarded Video ad impression - the event will fire when the
                            // video starts playing
                            Log.d(TAG, "Rewarded video ad impression logged!");
                        }

                        @Override
                        public void onRewardedVideoCompleted() {
                            unlockreward = true;
                        }

                        @Override
                        public void onRewardedVideoClosed() {
                            // The Rewarded Video ad was closed - this can occur during the video
                            // by closing the app, or closing the end card.
                            Log.d(TAG, "Rewarded video ad closed!");
                        }
                    };
                    rewardedVideoAdFan.loadAd(
                            rewardedVideoAdFan.buildLoadAdConfig()
                                    .withAdListener(rewardedVideoAdListener2)
                                    .build());
                    break;
                case "ADMOB":
                case "GOOGLE-ADS":
                    AdRequest adRequest = new AdRequest.Builder()

                            .build();
                    RewardedAd.load(activity, idBackupReward,
                            adRequest, new RewardedAdLoadCallback() {
                                @Override
                                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                    mRewardedAd = null;
                                }

                                @Override
                                public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                                    mRewardedAd = rewardedAd;

                                }
                            });
                    break;
                case "APPLOVIN-D":
                    incentivizedInterstitial = AppLovinIncentivizedInterstitial.create(idBackupReward, AppLovinSdk.getInstance(activity));
                    incentivizedInterstitial.preload(new AppLovinAdLoadListener() {
                        @Override
                        public void adReceived(AppLovinAd appLovinAd) {
                            // A rewarded video was successfully received.
                        }

                        @Override
                        public void failedToReceiveAd(int errorCode) {
                            // A rewarded video failed to load.
                        }
                    });
                    break;
                case "APPLOVIN-M":
                    rewardedAd = MaxRewardedAd.getInstance(idBackupReward, activity);
                    rewardedAd.loadAd();
                    MaxRewardedAdListener maxRewardedAdListener = new MaxRewardedAdListener() {
                        @Override
                        public void onRewardedVideoStarted(MaxAd ad) {

                        }

                        @Override
                        public void onRewardedVideoCompleted(MaxAd ad) {
                            unlockreward = true;
                        }

                        @Override
                        public void onUserRewarded(MaxAd ad, MaxReward reward) {

                        }

                        @Override
                        public void onAdLoaded(MaxAd ad) {

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

                        }

                        @Override
                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                        }
                    };
                    rewardedAd.setListener(maxRewardedAdListener);

                    break;

                case "UNITY":
                    IUnityAdsLoadListener loadListener = new IUnityAdsLoadListener() {
                        @Override
                        public void onUnityAdsAdLoaded(String placementId) {

                        }

                        @Override
                        public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {

                        }
                    };
                    try {
                        UnityAds.load(idBackupReward, loadListener);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case "ALIEN-M":
                    PropsAdsManagement.loadRewardedAds(activity, idBackupReward, new RewardedAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                            Log.i(TAG, "testing rewarded");
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error
                        }
                    });
                    break;
                case "ALIEN-V":
                     jamboxRewardedAd= MaxRewardedAd.getInstance(idBackupReward, activity);
                    jamboxRewardedAd.loadAd();
                    MaxRewardedAdListener jamboxRewardedAdListener = new MaxRewardedAdListener() {
                        @Override
                        public void onRewardedVideoStarted(MaxAd ad) {

                        }

                        @Override
                        public void onRewardedVideoCompleted(MaxAd ad) {
                            unlockreward = true;
                        }

                        @Override
                        public void onUserRewarded(MaxAd ad, MaxReward reward) {

                        }

                        @Override
                        public void onAdLoaded(MaxAd ad) {

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

                        }

                        @Override
                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                        }
                    };
                    jamboxRewardedAd.setListener(jamboxRewardedAdListener);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void LoadRewardWortise(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {

    }

    public static void ShowRewardAdmob(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {
        try {
            if (mRewardedAd != null) {
                Activity activityContext = activity;
                mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                    @Override
                    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                        if (onShowRewardsAdmob != null) {
                            onShowRewardsAdmob.onUserEarnedReward();
                        }
                        unlockreward = true;
                        LoadRewardAdmob(activity, selecBackuptAds, idReward, idBackupReward);
                    }
                });
            } else {
                switch (selecBackuptAds) {
                    case "APPLOVIN-M":
                        if (rewardedAd.isReady()) {
                            rewardedAd.showAd();
                        }
                        break;
                    case "UNITY":
                        IUnityAdsShowListener showListener = new IUnityAdsShowListener() {
                            @Override
                            public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
                                LoadRewardUnity(activity, selecBackuptAds, idBackupReward, idBackupReward);
                            }

                            @Override
                            public void onUnityAdsShowStart(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowClick(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
                                unlockreward = true;
                                LoadRewardUnity(activity, selecBackuptAds, idReward, idBackupReward);
                            }
                        };
                        UnityAds.show(activity, idReward, new UnityAdsShowOptions(), showListener);
                        break;
                    case "APPLOVIN-D":
                        if (incentivizedInterstitial != null) {
                            incentivizedInterstitial.show(activity, new AppLovinAdRewardListener() {
                                @Override
                                public void userRewardVerified(AppLovinAd ad, Map<String, String> response) {
                                    unlockreward = true;
                                }

                                @Override
                                public void userOverQuota(AppLovinAd ad, Map<String, String> response) {

                                }

                                @Override
                                public void userRewardRejected(AppLovinAd ad, Map<String, String> response) {

                                }

                                @Override
                                public void validationRequestFailed(AppLovinAd ad, int errorCode) {

                                }


                            }, null, new AppLovinAdDisplayListener() {
                                @Override
                                public void adDisplayed(AppLovinAd appLovinAd) {

                                }

                                @Override
                                public void adHidden(AppLovinAd appLovinAd) {
                                    incentivizedInterstitial.preload(null);
                                }
                            });
                        }
                        break;
                    case "STARTAPP":
                        if (rewardedVideo.isReady()) {
                            rewardedVideo.showAd();
                        }
                        break;
                    case "ALIEN-V":
                        if (jamboxRewardedAd.isReady()) {
                            jamboxRewardedAd.showAd();
                        }
                        break;
                    case "ALIEN-M":
                        if (PropsAdsManagement.getRewardedAds() != null) {
                            PropsAdsManagement.triggerRewardedAds(activity, new OnUserEarnedRewardListener() {
                                @Override
                                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                    unlockreward = true;
                                }
                            });
                        }
                        break;

                    case "ADMOB":
                        if (mRewardedAd2 != null) {
                            Activity activityContext = activity;
                            mRewardedAd2.show(activityContext, new OnUserEarnedRewardListener() {
                                @Override
                                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                    unlockreward = true;
                                }
                            });
                        }
                        break;
                    case "FACEBOOK":
                        if (rewardedVideoAdFan == null || !rewardedVideoAdFan.isAdLoaded()) {

                        } else {
                            rewardedVideoAdFan.show();
                        }

                        break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoadRewardAdmob(activity, selecBackuptAds, idReward, idBackupReward);
    }

    public static void ShowRewardFan(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {
        try {
            if (rewardedVideoAdFan == null || !rewardedVideoAdFan.isAdLoaded()) {
                switch (selecBackuptAds) {
                    case "FACEBOOK":
                        if (rewardedVideoAdFan2 == null || !rewardedVideoAdFan2.isAdLoaded()) {

                        } else {
                            rewardedVideoAdFan2.show();
                        }
                        break;
                    case "APPLOVIN-M":
                        if (rewardedAd.isReady()) {
                            rewardedAd.showAd();
                        }
                        break;
                    case "UNITY":
                        IUnityAdsShowListener showListener = new IUnityAdsShowListener() {
                            @Override
                            public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
                                LoadRewardUnity(activity, selecBackuptAds, idBackupReward, idBackupReward);
                            }

                            @Override
                            public void onUnityAdsShowStart(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowClick(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
                                unlockreward = true;
                                LoadRewardUnity(activity, selecBackuptAds, idReward, idBackupReward);
                            }
                        };
                        UnityAds.show(activity, idReward, new UnityAdsShowOptions(), showListener);
                        break;
                    case "APPLOVIN-D":
                        if (incentivizedInterstitial != null) {
                            incentivizedInterstitial.show(activity, new AppLovinAdRewardListener() {
                                @Override
                                public void userRewardVerified(AppLovinAd ad, Map<String, String> response) {
                                    unlockreward = true;
                                }

                                @Override
                                public void userOverQuota(AppLovinAd ad, Map<String, String> response) {

                                }

                                @Override
                                public void userRewardRejected(AppLovinAd ad, Map<String, String> response) {

                                }

                                @Override
                                public void validationRequestFailed(AppLovinAd ad, int errorCode) {

                                }


                            }, null, new AppLovinAdDisplayListener() {
                                @Override
                                public void adDisplayed(AppLovinAd appLovinAd) {

                                }

                                @Override
                                public void adHidden(AppLovinAd appLovinAd) {
                                    incentivizedInterstitial.preload(null);
                                }
                            });
                        }
                        break;
                    case "STARTAPP":
                        if (rewardedVideo.isReady()) {
                            rewardedVideo.showAd();
                        }
                        break;
                    case "ALIEN-V":
                        if (jamboxRewardedAd.isReady()) {
                            jamboxRewardedAd.showAd();
                        }
                        break;
                    case "ALIEN-M":
                        if (PropsAdsManagement.getRewardedAds() != null) {
                            PropsAdsManagement.triggerRewardedAds(activity, new OnUserEarnedRewardListener() {
                                @Override
                                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                    unlockreward = true;
                                }
                            });
                        }
                        break;

                    case "ADMOB":
                        if (mRewardedAd != null) {
                            Activity activityContext = activity;
                            mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                                @Override
                                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                    unlockreward = true;
                                }
                            });
                        }
                        break;
                }
            } else {
                rewardedVideoAdFan.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        LoadRewardFan(activity, selecBackuptAds, idReward, idBackupReward);
    }

    public static void ShowRewardGoogleAds(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {
        try {
            if (mRewardedAd != null) {
                Activity activityContext = activity;
                mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                    @Override
                    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                        if (onShowRewardsGoogle != null) {
                            onShowRewardsGoogle.onUserEarnedReward();
                        }
                        unlockreward = true;
                        LoadRewardGoogleAds(activity, selecBackuptAds, idReward, idBackupReward);
                    }
                });
            } else {
                switch (selecBackuptAds) {
                    case "APPLOVIN-M":
                        if (rewardedAd.isReady()) {
                            rewardedAd.showAd();
                        }
                        break;
                    case "UNITY":
                        IUnityAdsShowListener showListener = new IUnityAdsShowListener() {
                            @Override
                            public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
                                LoadRewardUnity(activity, selecBackuptAds, idBackupReward, idBackupReward);
                            }

                            @Override
                            public void onUnityAdsShowStart(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowClick(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
                                unlockreward = true;
                                LoadRewardUnity(activity, selecBackuptAds, idReward, idBackupReward);
                            }
                        };
                        UnityAds.show(activity, idReward, new UnityAdsShowOptions(), showListener);
                        break;
                    case "APPLOVIN-D":
                        if (incentivizedInterstitial != null) {
                            incentivizedInterstitial.show(activity, new AppLovinAdRewardListener() {
                                @Override
                                public void userRewardVerified(AppLovinAd ad, Map<String, String> response) {
                                    unlockreward = true;
                                }

                                @Override
                                public void userOverQuota(AppLovinAd ad, Map<String, String> response) {

                                }

                                @Override
                                public void userRewardRejected(AppLovinAd ad, Map<String, String> response) {

                                }

                                @Override
                                public void validationRequestFailed(AppLovinAd ad, int errorCode) {

                                }


                            }, null, new AppLovinAdDisplayListener() {
                                @Override
                                public void adDisplayed(AppLovinAd appLovinAd) {

                                }

                                @Override
                                public void adHidden(AppLovinAd appLovinAd) {
                                    incentivizedInterstitial.preload(null);
                                }
                            });
                        }
                        break;
                    case "STARTAPP":
                        if (rewardedVideo.isReady()) {
                            rewardedVideo.showAd();
                        }
                        break;
                    case "ALIEN-V":
                        if (jamboxRewardedAd.isReady()) {
                            jamboxRewardedAd.showAd();
                        }
                        break;
                    case "ALIEN-M":
                        if (PropsAdsManagement.getRewardedAds() != null) {
                            PropsAdsManagement.triggerRewardedAds(activity, new OnUserEarnedRewardListener() {
                                @Override
                                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                    unlockreward = true;
                                }
                            });
                        }
                        break;
                    case "FACEBOOK":
                        if (rewardedVideoAdFan == null || !rewardedVideoAdFan.isAdLoaded()) {

                        } else {
                            rewardedVideoAdFan.show();
                        }
                        break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoadRewardGoogleAds(activity, selecBackuptAds, idReward, idBackupReward);
    }

    public static void ShowRewardApplovinMax(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {
        try {
            if (rewardedAd.isReady()) {
                rewardedAd.showAd();
                LoadRewardApplovinMax(activity, selecBackuptAds, idReward, idBackupReward);
            } else {
                switch (selecBackuptAds) {
                    case "FACEBOOK":
                        if (rewardedVideoAdFan == null || !rewardedVideoAdFan.isAdLoaded()) {

                        } else {
                            rewardedVideoAdFan.show();
                        }
                        break;
                    case "GOOGLE-ADS":
                    case "ADMOB":
                        if (mRewardedAd != null) {
                            Activity activityContext = activity;
                            mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                                @Override
                                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                    unlockreward = true;
                                }
                            });
                        }
                        break;
                    case "UNITY":
                        IUnityAdsShowListener showListener = new IUnityAdsShowListener() {
                            @Override
                            public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
                                LoadRewardUnity(activity, selecBackuptAds, idBackupReward, idBackupReward);
                            }

                            @Override
                            public void onUnityAdsShowStart(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowClick(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
                                unlockreward = true;
                                LoadRewardUnity(activity, selecBackuptAds, idReward, idBackupReward);
                            }
                        };
                        UnityAds.show(activity, idReward, new UnityAdsShowOptions(), showListener);
                        break;
                    case "APPLOVIN-D":
                        if (incentivizedInterstitial != null) {
                            incentivizedInterstitial.show(activity, new AppLovinAdRewardListener() {
                                @Override
                                public void userRewardVerified(AppLovinAd ad, Map<String, String> response) {
                                    unlockreward = true;
                                }

                                @Override
                                public void userOverQuota(AppLovinAd ad, Map<String, String> response) {

                                }

                                @Override
                                public void userRewardRejected(AppLovinAd ad, Map<String, String> response) {

                                }

                                @Override
                                public void validationRequestFailed(AppLovinAd ad, int errorCode) {

                                }


                            }, null, new AppLovinAdDisplayListener() {
                                @Override
                                public void adDisplayed(AppLovinAd appLovinAd) {

                                }

                                @Override
                                public void adHidden(AppLovinAd appLovinAd) {
                                    incentivizedInterstitial.preload(null);
                                }
                            });
                        }
                        break;
                    case "STARTAPP":
                        if (rewardedVideo.isReady()) {
                            rewardedVideo.showAd();
                        }
                        break;
                    case "ALIEN-V":
                        if (jamboxRewardedAd.isReady()) {
                            jamboxRewardedAd.showAd();
                        }
                        break;
                    case "ALIEN-M":
                        if (PropsAdsManagement.getRewardedAds() != null) {
                            PropsAdsManagement.triggerRewardedAds(activity, new OnUserEarnedRewardListener() {
                                @Override
                                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                    unlockreward = true;
                                }
                            });
                        }
                        break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoadRewardApplovinMax(activity, selecBackuptAds, idReward, idBackupReward);
    }

    public static void ShowRewardApplovinDis(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {
        try {
            if (incentivizedInterstitial != null) {
                incentivizedInterstitial.show(activity, new AppLovinAdRewardListener() {
                    @Override
                    public void userRewardVerified(AppLovinAd ad, Map<String, String> response) {
                        if (onShowRewardsApplovinDiscovery != null) {
                            onShowRewardsApplovinDiscovery.userRewardVerified();
                        }
                        unlockreward = true;
                    }

                    @Override
                    public void userOverQuota(AppLovinAd ad, Map<String, String> response) {
                        if (onShowRewardsApplovinDiscovery != null) {
                            onShowRewardsApplovinDiscovery.userOverQuota();
                        }
                    }

                    @Override
                    public void userRewardRejected(AppLovinAd ad, Map<String, String> response) {
                        if (onShowRewardsApplovinDiscovery != null) {
                            onShowRewardsApplovinDiscovery.userRewardRejected();
                        }
                    }

                    @Override
                    public void validationRequestFailed(AppLovinAd ad, int errorCode) {
                        if (onShowRewardsApplovinDiscovery != null) {
                            onShowRewardsApplovinDiscovery.validationRequestFailed();
                        }
                    }


                }, null, new AppLovinAdDisplayListener() {
                    @Override
                    public void adDisplayed(AppLovinAd appLovinAd) {

                    }

                    @Override
                    public void adHidden(AppLovinAd appLovinAd) {
                        incentivizedInterstitial.preload(null);
                    }
                });
                LoadRewardApplovinDis(activity, selecBackuptAds, idReward, idBackupReward);
            } else {
                switch (selecBackuptAds) {
                    case "FACEBOOK":
                        if (rewardedVideoAdFan == null || !rewardedVideoAdFan.isAdLoaded()) {

                        } else {
                            rewardedVideoAdFan.show();
                        }
                        break;
                    case "GOOGLE-ADS":
                    case "ADMOB":
                        if (mRewardedAd != null) {
                            Activity activityContext = activity;
                            mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                                @Override
                                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                    unlockreward = true;
                                }
                            });
                        }
                        break;
                    case "UNITY":
                        IUnityAdsShowListener showListener = new IUnityAdsShowListener() {
                            @Override
                            public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
                                LoadRewardUnity(activity, selecBackuptAds, idBackupReward, idBackupReward);
                            }

                            @Override
                            public void onUnityAdsShowStart(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowClick(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
                                unlockreward = true;
                                LoadRewardUnity(activity, selecBackuptAds, idReward, idBackupReward);
                            }
                        };
                        UnityAds.show(activity, idReward, new UnityAdsShowOptions(), showListener);
                        break;
                    case "APPLOVIN-M":
                        if (rewardedAd.isReady()) {
                            rewardedAd.showAd();
                        }
                        break;
                    case "STARTAPP":
                        if (rewardedVideo.isReady()) {
                            rewardedVideo.showAd();
                        }
                        break;
                    case "ALIEN-V":
                        if (jamboxRewardedAd.isReady()) {
                            jamboxRewardedAd.showAd();
                        }
                        break;
                    case "ALIEN-M":
                        if (PropsAdsManagement.getRewardedAds() != null) {
                            PropsAdsManagement.triggerRewardedAds(activity, new OnUserEarnedRewardListener() {
                                @Override
                                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                    unlockreward = true;
                                }
                            });
                        }
                        break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoadRewardApplovinDis(activity, selecBackuptAds, idReward, idBackupReward);
    }

    public static void ShowRewardMopub(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {

    }

    public static void ShowRewardIron(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {
        try {
            LoadRewardIron(activity, selecBackuptAds, idReward, idBackupReward);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void ShowRewardUnity(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {
        IUnityAdsShowListener showListener = new IUnityAdsShowListener() {
            @Override
            public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
                LoadRewardUnity(activity, selecBackuptAds, idReward, idBackupReward);
                switch (selecBackuptAds) {
                    case "FACEBOOK":
                        if (rewardedVideoAdFan2 == null || !rewardedVideoAdFan2.isAdLoaded()) {

                        } else {
                            rewardedVideoAdFan2.show();
                        }
                        break;
                    case "APPLOVIN-M":
                        if (rewardedAd.isReady()) {
                            rewardedAd.showAd();
                        }
                        break;
                    case "UNITY":
                        IUnityAdsShowListener showListener = new IUnityAdsShowListener() {
                            @Override
                            public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
                                LoadRewardUnity(activity, selecBackuptAds, idBackupReward, idBackupReward);
                            }

                            @Override
                            public void onUnityAdsShowStart(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowClick(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
                                unlockreward = true;
                                LoadRewardUnity(activity, selecBackuptAds, idReward, idBackupReward);
                            }
                        };
                        UnityAds.show(activity, idReward, new UnityAdsShowOptions(), showListener);
                        break;
                    case "APPLOVIN-D":
                        if (incentivizedInterstitial != null) {
                            incentivizedInterstitial.show(activity, new AppLovinAdRewardListener() {
                                @Override
                                public void userRewardVerified(AppLovinAd ad, Map<String, String> response) {
                                    unlockreward = true;
                                }

                                @Override
                                public void userOverQuota(AppLovinAd ad, Map<String, String> response) {

                                }

                                @Override
                                public void userRewardRejected(AppLovinAd ad, Map<String, String> response) {

                                }

                                @Override
                                public void validationRequestFailed(AppLovinAd ad, int errorCode) {

                                }


                            }, null, new AppLovinAdDisplayListener() {
                                @Override
                                public void adDisplayed(AppLovinAd appLovinAd) {

                                }

                                @Override
                                public void adHidden(AppLovinAd appLovinAd) {
                                    incentivizedInterstitial.preload(null);
                                }
                            });
                        }
                        break;
                    case "STARTAPP":
                        if (rewardedVideo.isReady()) {
                            rewardedVideo.showAd();
                        }
                        break;
                    case "ALIEN-V":
                        if (jamboxRewardedAd.isReady()) {
                            jamboxRewardedAd.showAd();
                        }
                        break;
                    case "ALIEN-M":
                        if (PropsAdsManagement.getRewardedAds() != null) {
                            PropsAdsManagement.triggerRewardedAds(activity, new OnUserEarnedRewardListener() {
                                @Override
                                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                    unlockreward = true;
                                }
                            });
                        }
                        break;

                    case "ADMOB":
                        if (mRewardedAd != null) {
                            Activity activityContext = activity;
                            mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                                @Override
                                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                    unlockreward = true;
                                }
                            });
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
                unlockreward = true;
                LoadRewardUnity(activity, selecBackuptAds, idReward, idBackupReward);
            }
        };
        UnityAds.show(activity, idReward, new UnityAdsShowOptions(), showListener);
    }

    public static void ShowRewardStartApp(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {
        try {
            if (rewardedVideo.isReady()) {
                rewardedVideo.showAd();
                LoadRewardStartApp(activity, selecBackuptAds, idReward, idBackupReward);
            } else {
                switch (selecBackuptAds) {
                    case "FACEBOOK":
                        if (rewardedVideoAdFan == null || !rewardedVideoAdFan.isAdLoaded()) {

                        } else {
                            rewardedVideoAdFan.show();
                        }
                        break;
                    case "APPLOVIN-M":
                        if (rewardedAd.isReady()) {
                            rewardedAd.showAd();
                        }
                        break;
                    case "UNITY":
                        IUnityAdsShowListener showListener = new IUnityAdsShowListener() {
                            @Override
                            public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
                                LoadRewardUnity(activity, selecBackuptAds, idBackupReward, idBackupReward);
                            }

                            @Override
                            public void onUnityAdsShowStart(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowClick(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
                                unlockreward = true;
                                LoadRewardUnity(activity, selecBackuptAds, idReward, idBackupReward);
                            }
                        };
                        UnityAds.show(activity, idReward, new UnityAdsShowOptions(), showListener);
                        break;
                    case "APPLOVIN-D":
                        if (incentivizedInterstitial != null) {
                            incentivizedInterstitial.show(activity, new AppLovinAdRewardListener() {
                                @Override
                                public void userRewardVerified(AppLovinAd ad, Map<String, String> response) {
                                    unlockreward = true;
                                }

                                @Override
                                public void userOverQuota(AppLovinAd ad, Map<String, String> response) {

                                }

                                @Override
                                public void userRewardRejected(AppLovinAd ad, Map<String, String> response) {

                                }

                                @Override
                                public void validationRequestFailed(AppLovinAd ad, int errorCode) {

                                }


                            }, null, new AppLovinAdDisplayListener() {
                                @Override
                                public void adDisplayed(AppLovinAd appLovinAd) {

                                }

                                @Override
                                public void adHidden(AppLovinAd appLovinAd) {
                                    incentivizedInterstitial.preload(null);
                                }
                            });
                        }
                        break;
                    case "GOOGLE-ADS":
                    case "ADMOB":
                        if (mRewardedAd != null) {
                            Activity activityContext = activity;
                            mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                                @Override
                                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                    unlockreward = true;
                                }
                            });
                        }
                        break;
                    case "ALIEN-V":
                        if (jamboxRewardedAd.isReady()) {
                            jamboxRewardedAd.showAd();
                        }
                        break;
                    case "ALIEN-M":
                        if (PropsAdsManagement.getRewardedAds() != null) {
                            PropsAdsManagement.triggerRewardedAds(activity, new OnUserEarnedRewardListener() {
                                @Override
                                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                    unlockreward = true;
                                }
                            });
                        }
                        break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoadRewardStartApp(activity, selecBackuptAds, idReward, idBackupReward);
    }

    public static void ShowRewardAlienView(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {
        try {

            if (jamboxRewardedAd.isReady()) {
                jamboxRewardedAd.showAd();
            } else {
                switch (selecBackuptAds) {
                    case "FACEBOOK":
                        if (rewardedVideoAdFan.isAdLoaded()) {
                            rewardedVideoAdFan.show();
                        }
                        break;
                    case "APPLOVIN-M":
                        if (rewardedAd.isReady()) {
                            rewardedAd.showAd();
                        }
                        break;
                    case "UNITY":
                        IUnityAdsShowListener showListener = new IUnityAdsShowListener() {
                            @Override
                            public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
                                LoadRewardUnity(activity, selecBackuptAds, idBackupReward, idBackupReward);
                            }

                            @Override
                            public void onUnityAdsShowStart(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowClick(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
                                unlockreward = true;
                                LoadRewardUnity(activity, selecBackuptAds, idReward, idBackupReward);
                            }
                        };
                        UnityAds.show(activity, idReward, new UnityAdsShowOptions(), showListener);
                        break;
                    case "APPLOVIN-D":
                        if (incentivizedInterstitial != null) {
                            incentivizedInterstitial.show(activity, new AppLovinAdRewardListener() {
                                @Override
                                public void userRewardVerified(AppLovinAd ad, Map<String, String> response) {
                                    unlockreward = true;
                                }

                                @Override
                                public void userOverQuota(AppLovinAd ad, Map<String, String> response) {

                                }

                                @Override
                                public void userRewardRejected(AppLovinAd ad, Map<String, String> response) {

                                }

                                @Override
                                public void validationRequestFailed(AppLovinAd ad, int errorCode) {

                                }


                            }, null, new AppLovinAdDisplayListener() {
                                @Override
                                public void adDisplayed(AppLovinAd appLovinAd) {

                                }

                                @Override
                                public void adHidden(AppLovinAd appLovinAd) {
                                    incentivizedInterstitial.preload(null);
                                }
                            });
                        }
                        break;
                    case "GOOGLE-ADS":
                    case "ADMOB":
                        if (mRewardedAd != null) {
                            Activity activityContext = activity;
                            mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                                @Override
                                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                    unlockreward = true;
                                }
                            });
                        }
                        break;
                    case "ALIEN-M":
                        if (PropsAdsManagement.getRewardedAds() != null) {
                            PropsAdsManagement.triggerRewardedAds(activity, new OnUserEarnedRewardListener() {
                                @Override
                                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                    unlockreward = true;
                                }
                            });
                        }
                        break;
                    case "STARTAPP":
                        if (rewardedVideo.isReady()) {
                            rewardedVideo.showAd();
                        }
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        LoadRewardAlienView(activity, selecBackuptAds, idReward, idBackupReward);
    }

    public static void ShowRewardAlienMediation(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {
        try {
            if (PropsAdsManagement.getRewardedAds() != null) {
                PropsAdsManagement.triggerRewardedAds(activity, new OnUserEarnedRewardListener() {
                    @Override
                    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                        unlockreward = true;
                    }
                });
            } else {
                switch (selecBackuptAds) {
                    case "FACEBOOK":
                        if (rewardedVideoAdFan.isAdLoaded()) {
                            rewardedVideoAdFan.show();
                        }
                        break;
                    case "APPLOVIN-M":
                        if (rewardedAd.isReady()) {
                            rewardedAd.showAd();
                        }
                        break;
                    case "UNITY":
                        IUnityAdsShowListener showListener = new IUnityAdsShowListener() {
                            @Override
                            public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
                                LoadRewardUnity(activity, selecBackuptAds, idBackupReward, idBackupReward);
                            }

                            @Override
                            public void onUnityAdsShowStart(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowClick(String placementId) {

                            }

                            @Override
                            public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
                                unlockreward = true;
                                LoadRewardUnity(activity, selecBackuptAds, idReward, idBackupReward);
                            }
                        };
                        UnityAds.show(activity, idReward, new UnityAdsShowOptions(), showListener);
                        break;
                    case "APPLOVIN-D":
                        if (incentivizedInterstitial != null) {
                            incentivizedInterstitial.show(activity, new AppLovinAdRewardListener() {
                                @Override
                                public void userRewardVerified(AppLovinAd ad, Map<String, String> response) {
                                    unlockreward = true;
                                }

                                @Override
                                public void userOverQuota(AppLovinAd ad, Map<String, String> response) {

                                }

                                @Override
                                public void userRewardRejected(AppLovinAd ad, Map<String, String> response) {

                                }

                                @Override
                                public void validationRequestFailed(AppLovinAd ad, int errorCode) {

                                }


                            }, null, new AppLovinAdDisplayListener() {
                                @Override
                                public void adDisplayed(AppLovinAd appLovinAd) {

                                }

                                @Override
                                public void adHidden(AppLovinAd appLovinAd) {
                                    incentivizedInterstitial.preload(null);
                                }
                            });
                        }
                        break;
                    case "GOOGLE-ADS":
                    case "ADMOB":
                        if (mRewardedAd != null) {
                            Activity activityContext = activity;
                            mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                                @Override
                                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                    unlockreward = true;
                                }
                            });
                        }
                        break;
                    case "ALIEN-V":
                        if (jamboxRewardedAd.isReady()) {
                            jamboxRewardedAd.showAd();
                        }
                        break;
                    case "STARTAPP":
                        if (rewardedVideo.isReady()) {
                            rewardedVideo.showAd();
                        }
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoadRewardAlienMediation(activity, selecBackuptAds, idReward, idBackupReward);
    }

    public static void ShowRewardWortise(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {

    }

}
