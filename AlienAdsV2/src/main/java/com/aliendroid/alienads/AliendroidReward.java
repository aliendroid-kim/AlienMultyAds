package com.aliendroid.alienads;

import android.app.Activity;
import android.os.Bundle;

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
import com.aliendroid.sdkads.interfaces.OnLoadRewardsMediation;
import com.aliendroid.sdkads.interfaces.OnLoadRewardsView;
import com.aliendroid.sdkads.interfaces.OnShowInterstitialView;
import com.aliendroid.sdkads.interfaces.OnShowRewards;
import com.aliendroid.sdkads.interfaces.OnShowRewardsView;
import com.aliendroid.sdkads.type.mediation.AlienMediationAds;
import com.aliendroid.sdkads.type.view.AlienViewAds;
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
import com.google.ads.mediation.facebook.FacebookAdapter;
import com.google.ads.mediation.facebook.FacebookExtras;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.sdk.RewardedVideoListener;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.adlisteners.VideoListener;

import java.util.Map;

public class AliendroidReward {
    public static MaxRewardedAd rewardedAd;
    public static boolean unlockreward = false;
    public static AppLovinIncentivizedInterstitial incentivizedInterstitial;
    public static StartAppAd rewardedVideo;
    private static RewardedAd mRewardedAd;

    public static OnLoadRewardsAdmob onLoadRewardsAdmob;
    public static OnLoadRewardsStartApp onLoadRewardsStartApp;
    public static OnLoadRewardsGoogle onLoadRewardsGoogle;
    public static OnLoadRewardsApplovinDiscovery onLoadRewardsApplovinDiscovery;
    public static OnLoadRewardsApplovinMax onLoadRewardsApplovinMax;
    public static OnLoadRewardsIronSource onLoadRewardsIronSource;
    public static OnLoadRewardsMediation onLoadRewardsMediation;
    public static OnLoadRewardsAlienView onLoadRewardsAlienView;

    public static OnShowRewardsAdmob onShowRewardsAdmob;
    public static OnShowRewardsGoogle onShowRewardsGoogle;
    public static OnShowRewardsApplovinDiscovery onShowRewardsApplovinDiscovery;
    public static OnShowRewardsAlienMedition onShowRewardsAlienMedition;
    public static OnShowRewardsAlienView onShowRewardsAlienView;

    public static boolean SHOW_ALIEN_VIEW=false;
    public static void LoadRewardAdmob(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {
        try {
            Bundle extras = new FacebookExtras()
                    .setNativeBanner(true)
                    .build();
            AdRequest adRequest = new AdRequest.Builder()
                    .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                    .build();
            RewardedAd.load(activity, idReward,
                    adRequest, new RewardedAdLoadCallback() {
                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            if (onLoadRewardsAdmob!=null){
                                onLoadRewardsAdmob.onAdFailedToLoad();
                            }
                            mRewardedAd = null;
                        }

                        @Override
                        public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                            if (onLoadRewardsAdmob!=null){
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
                case "MOPUB":
                case "UNITY":
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
                case "IRON":
                    IronSource.setRewardedVideoListener(new RewardedVideoListener() {
                        @Override
                        public void onRewardedVideoAdOpened() {
                        }

                        @Override
                        public void onRewardedVideoAdClosed() {
                        }

                        @Override
                        public void onRewardedVideoAvailabilityChanged(boolean available) {
                        }

                        @Override
                        public void onRewardedVideoAdRewarded(Placement placement) {
                            unlockreward = true;
                        }

                        @Override
                        public void onRewardedVideoAdShowFailed(IronSourceError error) {
                        }

                        @Override
                        public void onRewardedVideoAdClicked(Placement placement) {
                        }

                        @Override
                        public void onRewardedVideoAdStarted() {
                        }

                        @Override
                        public void onRewardedVideoAdEnded() {
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

                case "ALIEN-M" :
                    AlienMediationAds.LoadRewarded(idBackupReward);
                    AlienMediationAds.onLoadRewardsMediation = new OnLoadRewardsMediation() {
                        @Override
                        public void onRewardsAdLoaded() {

                        }

                        @Override
                        public void onRewardsAdClosed() {

                        }

                        @Override
                        public void onRewardsAdReward() {
                            unlockreward = true;
                        }

                        @Override
                        public void onRewardsAdClicked() {

                        }

                        @Override
                        public void onRewardsAdFailedToLoad(String error) {

                        }
                    };
                    break;
                case "ALIEN-V":
                    AlienViewAds.RewardsAds(activity,idBackupReward);
                    AlienViewAds.onLoadRewardsView = new OnLoadRewardsView() {
                        @Override
                        public void onRewardsAdLoaded() {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdLoaded();
                            }
                        }

                        @Override
                        public void onRewardsAdClosed() {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdClosed();
                            }
                        }

                        @Override
                        public void onRewardsAdClicked() {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdClicked();
                            }
                        }

                        @Override
                        public void onRewardsAdFailedToLoad(String error) {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdFailedToLoad("");
                            }
                        }
                    };
                    break;


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void LoadRewardAlienMediation(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {
        try {
            AlienMediationAds.LoadRewarded(idReward);
            AlienMediationAds.onLoadRewardsMediation = new OnLoadRewardsMediation() {
                @Override
                public void onRewardsAdLoaded() {
                if (onLoadRewardsMediation!=null){
                    onLoadRewardsMediation.onRewardsAdLoaded();
                }
                }

                @Override
                public void onRewardsAdClosed() {
                    if (onLoadRewardsMediation!=null){
                        onLoadRewardsMediation.onRewardsAdClosed();
                    }
                }

                @Override
                public void onRewardsAdReward() {
                    if (onLoadRewardsMediation!=null){
                        onLoadRewardsMediation.onRewardsAdReward();
                    }
                    unlockreward= true;
                }

                @Override
                public void onRewardsAdClicked() {
                    if (onLoadRewardsMediation!=null){
                        onLoadRewardsMediation.onRewardsAdClicked();
                    }
                }

                @Override
                public void onRewardsAdFailedToLoad(String error) {
                    if (onLoadRewardsMediation!=null){
                        onLoadRewardsMediation.onRewardsAdFailedToLoad("");
                    }
                }
            };
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
                case "MOPUB":
                case "UNITY":
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
                case "IRON":
                    IronSource.setRewardedVideoListener(new RewardedVideoListener() {
                        @Override
                        public void onRewardedVideoAdOpened() {
                        }

                        @Override
                        public void onRewardedVideoAdClosed() {
                        }

                        @Override
                        public void onRewardedVideoAvailabilityChanged(boolean available) {
                        }

                        @Override
                        public void onRewardedVideoAdRewarded(Placement placement) {
                            unlockreward = true;
                        }

                        @Override
                        public void onRewardedVideoAdShowFailed(IronSourceError error) {
                        }

                        @Override
                        public void onRewardedVideoAdClicked(Placement placement) {
                        }

                        @Override
                        public void onRewardedVideoAdStarted() {
                        }

                        @Override
                        public void onRewardedVideoAdEnded() {
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

                case "ADMOB" :
                    Bundle extras = new FacebookExtras()
                            .setNativeBanner(true)
                            .build();
                    AdRequest adRequest = new AdRequest.Builder()
                            .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                            .build();
                    RewardedAd.load(activity, idBackupReward,
                            adRequest, new RewardedAdLoadCallback() {
                                @Override
                                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                    if (onLoadRewardsAdmob!=null){
                                        onLoadRewardsAdmob.onAdFailedToLoad();
                                    }
                                    mRewardedAd = null;
                                }

                                @Override
                                public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                                    if (onLoadRewardsAdmob!=null){
                                        onLoadRewardsAdmob.onAdLoaded("");
                                    }
                                    mRewardedAd = rewardedAd;

                                }
                            });
                    break;
                case "ALIEN-V":
                    AlienViewAds.RewardsAds(activity,idBackupReward);
                    AlienViewAds.onLoadRewardsView = new OnLoadRewardsView() {
                        @Override
                        public void onRewardsAdLoaded() {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdLoaded();
                            }
                        }

                        @Override
                        public void onRewardsAdClosed() {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdClosed();
                            }
                        }

                        @Override
                        public void onRewardsAdClicked() {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdClicked();
                            }
                        }

                        @Override
                        public void onRewardsAdFailedToLoad(String error) {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdFailedToLoad("");
                            }
                        }
                    };
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void LoadRewardAlienView(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {
        try {
            AlienViewAds.RewardsAds(activity,idReward);
            AlienViewAds.onLoadRewardsView = new OnLoadRewardsView() {
                @Override
                public void onRewardsAdLoaded() {
               if (onLoadRewardsAlienView!=null){
                   onLoadRewardsAlienView.onRewardsAdLoaded();
               }
                }

                @Override
                public void onRewardsAdClosed() {
                    if (onLoadRewardsAlienView!=null){
                        onLoadRewardsAlienView.onRewardsAdClosed();
                    }
                }

                @Override
                public void onRewardsAdClicked() {
                    if (onLoadRewardsAlienView!=null){
                        onLoadRewardsAlienView.onRewardsAdClicked();
                    }
                }

                @Override
                public void onRewardsAdFailedToLoad(String error) {
                    if (onLoadRewardsAlienView!=null){
                        onLoadRewardsAlienView.onRewardsAdFailedToLoad("");
                    }
                }
            };
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
                case "MOPUB":
                case "UNITY":
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
                case "IRON":
                    IronSource.setRewardedVideoListener(new RewardedVideoListener() {
                        @Override
                        public void onRewardedVideoAdOpened() {
                        }

                        @Override
                        public void onRewardedVideoAdClosed() {
                        }

                        @Override
                        public void onRewardedVideoAvailabilityChanged(boolean available) {
                        }

                        @Override
                        public void onRewardedVideoAdRewarded(Placement placement) {
                            unlockreward = true;
                        }

                        @Override
                        public void onRewardedVideoAdShowFailed(IronSourceError error) {
                        }

                        @Override
                        public void onRewardedVideoAdClicked(Placement placement) {
                        }

                        @Override
                        public void onRewardedVideoAdStarted() {
                        }

                        @Override
                        public void onRewardedVideoAdEnded() {
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

                case "ADMOB" :
                    Bundle extras = new FacebookExtras()
                            .setNativeBanner(true)
                            .build();
                    AdRequest adRequest = new AdRequest.Builder()
                            .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                            .build();
                    RewardedAd.load(activity, idBackupReward,
                            adRequest, new RewardedAdLoadCallback() {
                                @Override
                                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                    if (onLoadRewardsAdmob!=null){
                                        onLoadRewardsAdmob.onAdFailedToLoad();
                                    }
                                    mRewardedAd = null;
                                }

                                @Override
                                public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                                    if (onLoadRewardsAdmob!=null){
                                        onLoadRewardsAdmob.onAdLoaded("");
                                    }
                                    mRewardedAd = rewardedAd;

                                }
                            });
                    break;
                case "ALIEN-M" :
                    AlienMediationAds.LoadRewarded(idBackupReward);
                    AlienMediationAds.onLoadRewardsMediation = new OnLoadRewardsMediation() {
                        @Override
                        public void onRewardsAdLoaded() {

                        }

                        @Override
                        public void onRewardsAdClosed() {

                        }

                        @Override
                        public void onRewardsAdReward() {
                            unlockreward = true;
                        }

                        @Override
                        public void onRewardsAdClicked() {

                        }

                        @Override
                        public void onRewardsAdFailedToLoad(String error) {

                        }
                    };
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void LoadRewardUnity(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {

    }

    public static void LoadRewardGoogleAds(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {
        try {
            AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();
            RewardedAd.load(activity, idReward,
                    adRequest, new RewardedAdLoadCallback() {
                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            if (onLoadRewardsGoogle!=null){
                                onLoadRewardsGoogle.onAdFailedToLoad();
                            }
                            mRewardedAd = null;
                        }

                        @Override
                        public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                            if (onLoadRewardsGoogle!=null){
                                onLoadRewardsGoogle.onAdFailedToLoad();
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
                case "MOPUB":
                case "UNITY":

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
                case "IRON":
                    IronSource.setRewardedVideoListener(new RewardedVideoListener() {
                        @Override
                        public void onRewardedVideoAdOpened() {
                        }

                        @Override
                        public void onRewardedVideoAdClosed() {
                        }

                        @Override
                        public void onRewardedVideoAvailabilityChanged(boolean available) {
                        }

                        @Override
                        public void onRewardedVideoAdRewarded(Placement placement) {
                            unlockreward = true;
                        }

                        @Override
                        public void onRewardedVideoAdShowFailed(IronSourceError error) {
                        }

                        @Override
                        public void onRewardedVideoAdClicked(Placement placement) {
                        }

                        @Override
                        public void onRewardedVideoAdStarted() {
                        }

                        @Override
                        public void onRewardedVideoAdEnded() {
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
                case "ALIEN-M" :
                    AlienMediationAds.LoadRewarded(idBackupReward);
                    AlienMediationAds.onLoadRewardsMediation = new OnLoadRewardsMediation() {
                        @Override
                        public void onRewardsAdLoaded() {

                        }

                        @Override
                        public void onRewardsAdClosed() {

                        }

                        @Override
                        public void onRewardsAdReward() {
                            unlockreward = true;
                        }

                        @Override
                        public void onRewardsAdClicked() {

                        }

                        @Override
                        public void onRewardsAdFailedToLoad(String error) {

                        }
                    };
                    break;
                case "ALIEN-V":
                    AlienViewAds.RewardsAds(activity,idBackupReward);
                    AlienViewAds.onLoadRewardsView = new OnLoadRewardsView() {
                        @Override
                        public void onRewardsAdLoaded() {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdLoaded();
                            }
                        }

                        @Override
                        public void onRewardsAdClosed() {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdClosed();
                            }
                        }

                        @Override
                        public void onRewardsAdClicked() {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdClicked();
                            }
                        }

                        @Override
                        public void onRewardsAdFailedToLoad(String error) {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdFailedToLoad("");
                            }
                        }
                    };
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
                    if (onLoadRewardsApplovinMax!=null){
                        onLoadRewardsApplovinMax.onRewardedVideoStarted();
                    }
                }

                @Override
                public void onRewardedVideoCompleted(MaxAd ad) {
                    if (onLoadRewardsApplovinMax!=null){
                        onLoadRewardsApplovinMax.onRewardedVideoCompleted();
                    }
                    unlockreward = true;
                }

                @Override
                public void onUserRewarded(MaxAd ad, MaxReward reward) {
                    if (onLoadRewardsApplovinMax!=null){
                        onLoadRewardsApplovinMax.onUserRewarded();
                    }
                }

                @Override
                public void onAdLoaded(MaxAd ad) {
                    if (onLoadRewardsApplovinMax!=null){
                        onLoadRewardsApplovinMax.onAdLoaded();
                    }
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {
                    if (onLoadRewardsApplovinMax!=null){
                        onLoadRewardsApplovinMax.onAdDisplayed();
                    }
                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    if (onLoadRewardsApplovinMax!=null){
                        onLoadRewardsApplovinMax.onAdHidden();
                    }
                }

                @Override
                public void onAdClicked(MaxAd ad) {
                    if (onLoadRewardsApplovinMax!=null){
                        onLoadRewardsApplovinMax.onAdClicked();
                    }
                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    if (onLoadRewardsApplovinMax!=null){
                        onLoadRewardsApplovinMax.onAdLoadFailed();
                    }
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                    if (onLoadRewardsApplovinMax!=null){
                        onLoadRewardsApplovinMax.onAdDisplayFailed();
                    }
                }
            };
            rewardedAd.setListener(maxRewardedAdListener);
            switch (selectBackupAds) {
                case "ADMOB":
                case "GOOGLE-ADS":

                    Bundle extras = new FacebookExtras()
                            .setNativeBanner(true)
                            .build();
                    AdRequest adRequest = new AdRequest.Builder()
                            .addNetworkExtrasBundle(FacebookAdapter.class, extras)
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
                case "MOPUB":
                case "UNITY":

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
                case "IRON":
                    IronSource.setRewardedVideoListener(new RewardedVideoListener() {
                        @Override
                        public void onRewardedVideoAdOpened() {
                        }

                        @Override
                        public void onRewardedVideoAdClosed() {
                        }

                        @Override
                        public void onRewardedVideoAvailabilityChanged(boolean available) {
                        }

                        @Override
                        public void onRewardedVideoAdRewarded(Placement placement) {
                            unlockreward = true;
                        }

                        @Override
                        public void onRewardedVideoAdShowFailed(IronSourceError error) {
                        }

                        @Override
                        public void onRewardedVideoAdClicked(Placement placement) {
                        }

                        @Override
                        public void onRewardedVideoAdStarted() {
                        }

                        @Override
                        public void onRewardedVideoAdEnded() {
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
                case "ALIEN-M" :
                    AlienMediationAds.LoadRewarded(idBackupReward);
                    AlienMediationAds.onLoadRewardsMediation = new OnLoadRewardsMediation() {
                        @Override
                        public void onRewardsAdLoaded() {

                        }

                        @Override
                        public void onRewardsAdClosed() {

                        }

                        @Override
                        public void onRewardsAdReward() {
                            unlockreward = true;
                        }

                        @Override
                        public void onRewardsAdClicked() {

                        }

                        @Override
                        public void onRewardsAdFailedToLoad(String error) {

                        }
                    };
                    break;
                case "ALIEN-V":
                    AlienViewAds.RewardsAds(activity,idBackupReward);
                    AlienViewAds.onLoadRewardsView = new OnLoadRewardsView() {
                        @Override
                        public void onRewardsAdLoaded() {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdLoaded();
                            }
                        }

                        @Override
                        public void onRewardsAdClosed() {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdClosed();
                            }
                        }

                        @Override
                        public void onRewardsAdClicked() {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdClicked();
                            }
                        }

                        @Override
                        public void onRewardsAdFailedToLoad(String error) {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdFailedToLoad("");
                            }
                        }
                    };
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
                    if (onLoadRewardsApplovinDiscovery!=null){
                        onLoadRewardsApplovinDiscovery.adReceived();
                    }
                }

                @Override
                public void failedToReceiveAd(int errorCode) {
                    if (onLoadRewardsApplovinDiscovery!=null){
                        onLoadRewardsApplovinDiscovery.failedToReceiveAd("");
                    }
                }
            });
            switch (selectBackupAds) {
                case "ADMOB":
                case "GOOGLE-ADS":
                    Bundle extras = new FacebookExtras()
                            .setNativeBanner(true)
                            .build();
                    AdRequest adRequest = new AdRequest.Builder()
                            .addNetworkExtrasBundle(FacebookAdapter.class, extras)
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
                case "MOPUB":
                case "UNITY":

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
                case "IRON":
                    IronSource.setRewardedVideoListener(new RewardedVideoListener() {
                        @Override
                        public void onRewardedVideoAdOpened() {
                        }

                        @Override
                        public void onRewardedVideoAdClosed() {
                        }

                        @Override
                        public void onRewardedVideoAvailabilityChanged(boolean available) {
                        }

                        @Override
                        public void onRewardedVideoAdRewarded(Placement placement) {
                            unlockreward = true;
                        }

                        @Override
                        public void onRewardedVideoAdShowFailed(IronSourceError error) {
                        }

                        @Override
                        public void onRewardedVideoAdClicked(Placement placement) {
                        }

                        @Override
                        public void onRewardedVideoAdStarted() {
                        }

                        @Override
                        public void onRewardedVideoAdEnded() {
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
                case "ALIEN-M" :
                    AlienMediationAds.LoadRewarded(idBackupReward);
                    AlienMediationAds.onLoadRewardsMediation = new OnLoadRewardsMediation() {
                        @Override
                        public void onRewardsAdLoaded() {

                        }

                        @Override
                        public void onRewardsAdClosed() {

                        }

                        @Override
                        public void onRewardsAdReward() {
                            unlockreward = true;
                        }

                        @Override
                        public void onRewardsAdClicked() {

                        }

                        @Override
                        public void onRewardsAdFailedToLoad(String error) {

                        }
                    };
                    break;
                case "ALIEN-V":
                    AlienViewAds.RewardsAds(activity,idBackupReward);
                    AlienViewAds.onLoadRewardsView = new OnLoadRewardsView() {
                        @Override
                        public void onRewardsAdLoaded() {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdLoaded();
                            }
                        }

                        @Override
                        public void onRewardsAdClosed() {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdClosed();
                            }
                        }

                        @Override
                        public void onRewardsAdClicked() {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdClicked();
                            }
                        }

                        @Override
                        public void onRewardsAdFailedToLoad(String error) {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdFailedToLoad("");
                            }
                        }
                    };
                    AlienViewAds.onShowInterstitialView = new OnShowInterstitialView() {
                        @Override
                        public void onAdSuccess() {
                            unlockreward = true;
                        }

                        @Override
                        public void onAdFailedShow() {

                        }
                    };
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void LoadRewardMopub(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {

    }

    public static void LoadRewardIron(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {
        try {
            IronSource.setRewardedVideoListener(new RewardedVideoListener() {
                @Override
                public void onRewardedVideoAdOpened() {
                    if (onLoadRewardsIronSource!=null){
                        onLoadRewardsIronSource.onRewardedVideoAdOpened();
                    }
                }

                @Override
                public void onRewardedVideoAdClosed() {
                    if (onLoadRewardsIronSource!=null){
                        onLoadRewardsIronSource.onRewardedVideoAdClosed();
                    }
                }

                @Override
                public void onRewardedVideoAvailabilityChanged(boolean available) {
                    if (onLoadRewardsIronSource!=null){
                        onLoadRewardsIronSource.onRewardedVideoAvailabilityChanged();
                    }
                }

                @Override
                public void onRewardedVideoAdRewarded(Placement placement) {
                    if (onLoadRewardsIronSource!=null){
                        onLoadRewardsIronSource.onRewardedVideoAdRewarded();
                    }
                    unlockreward = true;
                }

                @Override
                public void onRewardedVideoAdShowFailed(IronSourceError error) {
                    if (onLoadRewardsIronSource!=null){
                        onLoadRewardsIronSource.onRewardedVideoAdShowFailed();
                    }
                    switch (selecBackuptAds) {
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
                        case "MOPUB":
                        case "UNITY":

                            break;
                        case "APPLOVIN-M":
                            if (rewardedAd.isReady()) {
                                rewardedAd.showAd();
                            }
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
                            rewardedVideo.showAd();
                            break;
                        case "ALIEN-V":
                            AlienViewAds.ShowRewards();
                            unlockreward = true;
                            break;
                        case "ALIEN-M":
                            AlienMediationAds.ShowReward();
                            break;

                    }
                }

                @Override
                public void onRewardedVideoAdClicked(Placement placement) {
                    if (onLoadRewardsIronSource!=null){
                        onLoadRewardsIronSource.onRewardedVideoAdClicked();
                    }
                }

                @Override
                public void onRewardedVideoAdStarted() {
                    if (onLoadRewardsIronSource!=null){
                        onLoadRewardsIronSource.onRewardedVideoAdStarted();
                    }
                }

                @Override
                public void onRewardedVideoAdEnded() {
                    if (onLoadRewardsIronSource!=null){
                        onLoadRewardsIronSource.onRewardedVideoAdEnded();
                    }
                }
            });
            switch (selecBackuptAds) {
                case "ADMOB":
                case "GOOGLE-ADS":
                    Bundle extras = new FacebookExtras()
                            .setNativeBanner(true)
                            .build();
                    AdRequest adRequest = new AdRequest.Builder()
                            .addNetworkExtrasBundle(FacebookAdapter.class, extras)
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
                case "MOPUB":
                case "UNITY":

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
                case "ALIEN-M" :
                    AlienMediationAds.LoadRewarded(idBackupReward);
                    AlienMediationAds.onLoadRewardsMediation = new OnLoadRewardsMediation() {
                        @Override
                        public void onRewardsAdLoaded() {

                        }

                        @Override
                        public void onRewardsAdClosed() {

                        }

                        @Override
                        public void onRewardsAdReward() {
                            unlockreward = true;
                        }

                        @Override
                        public void onRewardsAdClicked() {

                        }

                        @Override
                        public void onRewardsAdFailedToLoad(String error) {

                        }
                    };
                    break;
                case "ALIEN-V":
                    AlienViewAds.RewardsAds(activity,idBackupReward);
                    AlienViewAds.onLoadRewardsView = new OnLoadRewardsView() {
                        @Override
                        public void onRewardsAdLoaded() {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdLoaded();
                            }
                        }

                        @Override
                        public void onRewardsAdClosed() {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdClosed();
                            }
                        }

                        @Override
                        public void onRewardsAdClicked() {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdClicked();
                            }
                        }

                        @Override
                        public void onRewardsAdFailedToLoad(String error) {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdFailedToLoad("");
                            }
                        }
                    };
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void LoadRewardStartApp(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {
        try {
            rewardedVideo = new StartAppAd(activity);
            rewardedVideo.setVideoListener(new VideoListener() {
                @Override
                public void onVideoCompleted() {
                    if (onLoadRewardsStartApp!=null){
                        onLoadRewardsStartApp.onVideoCompleted();
                    }
                    unlockreward = true;
                }
            });

            rewardedVideo.loadAd(StartAppAd.AdMode.REWARDED_VIDEO, new AdEventListener() {
                @Override
                public void onReceiveAd(com.startapp.sdk.adsbase.Ad ad) {
                    if (onLoadRewardsStartApp!=null){
                        onLoadRewardsStartApp.onReceiveAd();
                    }

                }

                @Override
                public void onFailedToReceiveAd(com.startapp.sdk.adsbase.Ad ad) {
                    if (onLoadRewardsStartApp!=null){
                        onLoadRewardsStartApp.onFailedToReceiveAd();
                    }
                }
            });
            switch (selectBackupAds) {
                case "ADMOB":
                case "GOOGLE-ADS":
                    Bundle extras = new FacebookExtras()
                            .setNativeBanner(true)
                            .build();
                    AdRequest adRequest = new AdRequest.Builder()
                            .addNetworkExtrasBundle(FacebookAdapter.class, extras)
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
                case "MOPUB":

                case "UNITY":

                    break;
                case "IRON":
                    IronSource.setRewardedVideoListener(new RewardedVideoListener() {
                        @Override
                        public void onRewardedVideoAdOpened() {
                        }

                        @Override
                        public void onRewardedVideoAdClosed() {
                        }

                        @Override
                        public void onRewardedVideoAvailabilityChanged(boolean available) {
                        }

                        @Override
                        public void onRewardedVideoAdRewarded(Placement placement) {
                            unlockreward = true;
                        }

                        @Override
                        public void onRewardedVideoAdShowFailed(IronSourceError error) {
                        }

                        @Override
                        public void onRewardedVideoAdClicked(Placement placement) {
                        }

                        @Override
                        public void onRewardedVideoAdStarted() {
                        }

                        @Override
                        public void onRewardedVideoAdEnded() {
                        }
                    });
                    break;
                case "ALIEN-M" :
                    AlienMediationAds.LoadRewarded(idBackupReward);
                    AlienMediationAds.onLoadRewardsMediation = new OnLoadRewardsMediation() {
                        @Override
                        public void onRewardsAdLoaded() {

                        }

                        @Override
                        public void onRewardsAdClosed() {

                        }

                        @Override
                        public void onRewardsAdReward() {
                            unlockreward = true;
                        }

                        @Override
                        public void onRewardsAdClicked() {

                        }

                        @Override
                        public void onRewardsAdFailedToLoad(String error) {

                        }
                    };
                    break;
                case "ALIEN-V":
                    AlienViewAds.RewardsAds(activity,idBackupReward);
                    AlienViewAds.onLoadRewardsView = new OnLoadRewardsView() {
                        @Override
                        public void onRewardsAdLoaded() {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdLoaded();
                            }
                        }

                        @Override
                        public void onRewardsAdClosed() {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdClosed();
                            }
                        }

                        @Override
                        public void onRewardsAdClicked() {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdClicked();
                            }
                        }

                        @Override
                        public void onRewardsAdFailedToLoad(String error) {
                            if (onLoadRewardsAlienView!=null){
                                onLoadRewardsAlienView.onRewardsAdFailedToLoad("");
                            }
                        }
                    };
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void ShowRewardAdmob(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {
        try {
            if (mRewardedAd != null) {
                Activity activityContext = activity;
                mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                    @Override
                    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                        if (onShowRewardsAdmob!=null){
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
                    case "MOPUB":
                    case "UNITY":

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
                    case "IRON":
                        IronSource.showRewardedVideo(idBackupReward);
                        break;
                    case "STARTAPP":
                        if (rewardedVideo.isReady()) {
                            rewardedVideo.showAd();
                        }
                        break;
                    case "ALIEN-V":
                        AlienViewAds.ShowRewards();
                        AlienViewAds.onShowRewardsView = new OnShowRewardsView() {
                            @Override
                            public void onAdSuccess() {
                                if (onShowRewardsAlienView != null) {
                                    onShowRewardsAlienView.onAdSuccess();
                                }
                                unlockreward = true;
                            }

                            @Override
                            public void onAdFailedShow() {
                                if (onShowRewardsAlienView != null) {
                                    onShowRewardsAlienView.onAdSuccess();
                                }
                            }
                        };
                        break;
                    case "ALIEN-M":
                        AlienMediationAds.ShowReward();
                        break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoadRewardAdmob(activity, selecBackuptAds, idReward, idBackupReward);
    }

    public static void ShowRewardGoogleAds(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {
        try {
            if (mRewardedAd != null) {
                Activity activityContext = activity;
                mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                    @Override
                    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                        if (onShowRewardsGoogle!=null){
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
                    case "MOPUB":
                    case "UNITY":

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
                    case "IRON":
                        IronSource.showRewardedVideo(idBackupReward);
                        break;
                    case "STARTAPP":
                        if (rewardedVideo.isReady()) {
                            rewardedVideo.showAd();
                        }
                        break;
                    case "ALIEN-V":
                        AlienViewAds.ShowRewards();
                        AlienViewAds.onShowRewardsView = new OnShowRewardsView() {
                            @Override
                            public void onAdSuccess() {
                                if (onShowRewardsAlienView != null) {
                                    onShowRewardsAlienView.onAdSuccess();
                                }
                                unlockreward = true;
                            }

                            @Override
                            public void onAdFailedShow() {
                                if (onShowRewardsAlienView != null) {
                                    onShowRewardsAlienView.onAdSuccess();
                                }
                            }
                        };
                        break;
                    case "ALIEN-M":
                        AlienMediationAds.ShowReward();
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
                    case "MOPUB":
                    case "UNITY":

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
                    case "IRON":
                        IronSource.showRewardedVideo(idBackupReward);
                        break;
                    case "STARTAPP":
                        if (rewardedVideo.isReady()) {
                            rewardedVideo.showAd();
                        }
                        break;
                    case "ALIEN-V":
                        AlienViewAds.ShowRewards();
                        AlienViewAds.onShowRewardsView = new OnShowRewardsView() {
                            @Override
                            public void onAdSuccess() {
                                if (onShowRewardsAlienView != null) {
                                    onShowRewardsAlienView.onAdSuccess();
                                }
                                unlockreward = true;
                            }

                            @Override
                            public void onAdFailedShow() {
                                if (onShowRewardsAlienView != null) {
                                    onShowRewardsAlienView.onAdSuccess();
                                }
                            }
                        };
                        unlockreward=true;
                        break;
                    case "ALIEN-M":
                        AlienMediationAds.ShowReward();
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
                        if (onShowRewardsApplovinDiscovery!=null){
                            onShowRewardsApplovinDiscovery.userRewardVerified();
                        }
                        unlockreward = true;
                    }

                    @Override
                    public void userOverQuota(AppLovinAd ad, Map<String, String> response) {
                        if (onShowRewardsApplovinDiscovery!=null){
                            onShowRewardsApplovinDiscovery.userOverQuota();
                        }
                    }

                    @Override
                    public void userRewardRejected(AppLovinAd ad, Map<String, String> response) {
                        if (onShowRewardsApplovinDiscovery!=null){
                            onShowRewardsApplovinDiscovery.userRewardRejected();
                        }
                    }

                    @Override
                    public void validationRequestFailed(AppLovinAd ad, int errorCode) {
                        if (onShowRewardsApplovinDiscovery!=null){
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
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "APPLOVIN-M":
                        if (rewardedAd.isReady()) {
                            rewardedAd.showAd();
                        }
                        break;
                    case "IRON":
                        IronSource.showRewardedVideo(idBackupReward);
                        break;
                    case "STARTAPP":
                        if (rewardedVideo.isReady()) {
                            rewardedVideo.showAd();
                        }
                        break;
                    case "ALIEN-V":
                        AlienViewAds.ShowRewards();
                        AlienViewAds.onShowRewardsView = new OnShowRewardsView() {
                            @Override
                            public void onAdSuccess() {
                                if (onShowRewardsAlienView != null) {
                                    onShowRewardsAlienView.onAdSuccess();
                                }
                                unlockreward = true;
                            }

                            @Override
                            public void onAdFailedShow() {
                                if (onShowRewardsAlienView != null) {
                                    onShowRewardsAlienView.onAdSuccess();
                                }
                            }
                        };
                        unlockreward=true;
                        break;
                    case "ALIEN-M":
                        AlienMediationAds.ShowReward();
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
            IronSource.showRewardedVideo(idBackupReward);
            LoadRewardIron(activity, selecBackuptAds, idReward, idBackupReward);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void ShowRewardUnity(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {
    }

    public static void ShowRewardStartApp(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {
        try {
            if (rewardedVideo.isReady()) {
                rewardedVideo.showAd();
                LoadRewardStartApp(activity, selecBackuptAds, idReward, idBackupReward);
            } else {
                switch (selecBackuptAds) {
                    case "APPLOVIN-M":
                        if (rewardedAd.isReady()) {
                            rewardedAd.showAd();
                        }
                        break;
                    case "MOPUB":
                    case "UNITY":

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
                    case "IRON":
                        IronSource.showRewardedVideo(idBackupReward);
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
                        AlienViewAds.ShowRewards();
                        AlienViewAds.onShowRewardsView = new OnShowRewardsView() {
                            @Override
                            public void onAdSuccess() {
                                if (onShowRewardsAlienView != null) {
                                    onShowRewardsAlienView.onAdSuccess();
                                }
                                unlockreward = true;
                            }

                            @Override
                            public void onAdFailedShow() {
                                if (onShowRewardsAlienView != null) {
                                    onShowRewardsAlienView.onAdSuccess();
                                }
                            }
                        };
                        break;
                    case "ALIEN-M":
                        AlienMediationAds.ShowReward();
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
            AlienViewAds.ShowRewards();
            AlienViewAds.onShowRewardsView = new OnShowRewardsView() {
                @Override
                public void onAdSuccess() {
                    if (onShowRewardsAlienView!=null){
                        onShowRewardsAlienView.onAdSuccess();
                    }
                    unlockreward = true;
                }

                @Override
                public void onAdFailedShow() {
                    if (onShowRewardsAlienView!=null){
                        onShowRewardsAlienView.onAdSuccess();
                    }
                    switch (selecBackuptAds) {
                        case "APPLOVIN-M":
                            if (rewardedAd.isReady()) {
                                rewardedAd.showAd();
                            }
                            break;
                        case "MOPUB":
                        case "UNITY":

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
                        case "IRON":
                            IronSource.showRewardedVideo(idBackupReward);
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
                            AlienMediationAds.ShowReward();
                            break;
                        case "STARTAPP":
                            if (rewardedVideo.isReady()) {
                                rewardedVideo.showAd();
                            }
                            break;
                    }
                }
            };

        } catch (Exception e) {
            e.printStackTrace();
        }
        LoadRewardAlienView(activity, selecBackuptAds, idReward, idBackupReward);
    }

    public static void ShowRewardAlienMediation(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {
        try {
            AlienMediationAds.ShowReward();
            AlienMediationAds.onShowRewards = new OnShowRewards() {
                @Override
                public void onAdSuccess() {
                    if (onShowRewardsAlienMedition!=null){
                        onShowRewardsAlienMedition.onAdSuccess();
                    }
                    unlockreward = true;
                }

                @Override
                public void onAdFailedShow() {
                    if (onShowRewardsAlienMedition!=null){
                        onShowRewardsAlienMedition.onAdFailedShow();
                    }
                    switch (selecBackuptAds) {
                        case "APPLOVIN-M":
                            if (rewardedAd.isReady()) {
                                rewardedAd.showAd();
                            }
                            break;
                        case "MOPUB":
                        case "UNITY":

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
                        case "IRON":
                            IronSource.showRewardedVideo(idBackupReward);
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
                            AlienViewAds.ShowRewards();
                            AlienViewAds.onShowRewardsView = new OnShowRewardsView() {
                                @Override
                                public void onAdSuccess() {
                                    if (onShowRewardsAlienView != null) {
                                        onShowRewardsAlienView.onAdSuccess();
                                    }
                                    unlockreward = true;
                                }

                                @Override
                                public void onAdFailedShow() {
                                    if (onShowRewardsAlienView != null) {
                                        onShowRewardsAlienView.onAdSuccess();
                                    }
                                }
                            };
                            break;
                        case "STARTAPP":
                            if (rewardedVideo.isReady()) {
                                rewardedVideo.showAd();
                            }
                            break;
                    }
                }
            };

        } catch (Exception e) {
            e.printStackTrace();
        }
        LoadRewardAlienMediation(activity, selecBackuptAds, idReward, idBackupReward);
    }
}
