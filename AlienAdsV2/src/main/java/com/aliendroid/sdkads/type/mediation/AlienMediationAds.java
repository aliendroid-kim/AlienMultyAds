package com.aliendroid.sdkads.type.mediation;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.aliendroid.alienads.R;
import com.aliendroid.sdkads.interfaces.OnLoadBannerMediation;
import com.aliendroid.sdkads.interfaces.OnLoadInterstitialMediation;
import com.aliendroid.sdkads.interfaces.OnLoadNative;
import com.aliendroid.sdkads.interfaces.OnLoadRewardsMediation;
import com.aliendroid.sdkads.interfaces.OnShowInterstitial;
import com.aliendroid.sdkads.interfaces.OnShowRewards;
import com.aliendroid.sdkads.layout.NativeMediation;
import com.smaato.sdk.banner.ad.BannerAdSize;
import com.smaato.sdk.banner.widget.BannerError;
import com.smaato.sdk.banner.widget.BannerView;
import com.smaato.sdk.core.lifecycle.Lifecycling;
import com.smaato.sdk.core.repository.AdRequestParams;
import com.smaato.sdk.interstitial.EventListener;
import com.smaato.sdk.interstitial.Interstitial;
import com.smaato.sdk.interstitial.InterstitialAd;
import com.smaato.sdk.interstitial.InterstitialError;
import com.smaato.sdk.interstitial.InterstitialRequestError;
import com.smaato.sdk.nativead.NativeAd;
import com.smaato.sdk.nativead.NativeAdAssets;
import com.smaato.sdk.nativead.NativeAdError;
import com.smaato.sdk.nativead.NativeAdRenderer;
import com.smaato.sdk.nativead.NativeAdRequest;
import com.smaato.sdk.nativead.NativeAdView;
import com.smaato.sdk.rewarded.RewardedError;
import com.smaato.sdk.rewarded.RewardedInterstitial;
import com.smaato.sdk.rewarded.RewardedInterstitialAd;
import com.smaato.sdk.rewarded.RewardedRequestError;

public class AlienMediationAds {
    public static OnLoadInterstitialMediation onLoadInterstitialMediation;
    public static OnLoadBannerMediation onLoadBannerMediation;
    public static OnLoadRewardsMediation onLoadRewardsMediation;
    public static InterstitialAd interstitialAds = null;
    public static RewardedInterstitialAd rewardedInterstitialAds=null;
    public static OnShowInterstitial onShowInterstitial;
    public static OnShowRewards onShowRewards;
    public static OnLoadNative onLoadNative;
    public static NativeAdRequest request;
    public static void SmallBanner(Activity activity, RelativeLayout layAds, String PlacementID){
        try {
            BannerView banner = new BannerView(activity);
            layAds.addView(banner);
            banner.loadAd(PlacementID, BannerAdSize.XX_LARGE_320x50);
            banner.setEventListener(new BannerView.EventListener() {
                @Override
                public void onAdLoaded(@NonNull BannerView bannerView) {
                    if (onLoadBannerMediation!=null){
                        onLoadBannerMediation.onBannerAdLoaded();
                    }
                }

                @Override
                public void onAdFailedToLoad(@NonNull BannerView bannerView, @NonNull BannerError bannerError) {

                    if (onLoadBannerMediation!=null){
                        onLoadBannerMediation.onBannerAdFailedToLoad("");
                    }
                    SmallBanner(activity,layAds,PlacementID);
                }

                @Override
                public void onAdImpression(@NonNull BannerView bannerView) {

                }

                @Override
                public void onAdClicked(@NonNull BannerView bannerView) {
                    if (onLoadBannerMediation!=null){
                        onLoadBannerMediation.onBannerAdClicked();
                    }
                }

                @Override
                public void onAdTTLExpired(@NonNull BannerView bannerView) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void MediumBanner(Activity activity, RelativeLayout layAds, String PlacementID){
        try {
            BannerView banner = new BannerView(activity);
            layAds.addView(banner);
            banner.loadAd(PlacementID, BannerAdSize.MEDIUM_RECTANGLE_300x250);
            banner.setEventListener(new BannerView.EventListener() {
                @Override
                public void onAdLoaded(@NonNull BannerView bannerView) {
                    if (onLoadBannerMediation!=null){
                        onLoadBannerMediation.onBannerAdLoaded();
                    }
                }

                @Override
                public void onAdFailedToLoad(@NonNull BannerView bannerView, @NonNull BannerError bannerError) {

                    if (onLoadBannerMediation!=null){
                        onLoadBannerMediation.onBannerAdFailedToLoad("");
                    }
                    SmallBanner(activity,layAds,PlacementID);
                }

                @Override
                public void onAdImpression(@NonNull BannerView bannerView) {

                }

                @Override
                public void onAdClicked(@NonNull BannerView bannerView) {
                    if (onLoadBannerMediation!=null){
                        onLoadBannerMediation.onBannerAdClicked();
                    }
                }

                @Override
                public void onAdTTLExpired(@NonNull BannerView bannerView) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void BoardBanner(Activity activity, RelativeLayout layAds, String PlacementID){
        try {
            BannerView banner = new BannerView(activity);
            layAds.addView(banner);
            banner.loadAd(PlacementID, BannerAdSize.LEADERBOARD_728x90);
            banner.setEventListener(new BannerView.EventListener() {
                @Override
                public void onAdLoaded(@NonNull BannerView bannerView) {
                    if (onLoadBannerMediation!=null){
                        onLoadBannerMediation.onBannerAdLoaded();
                    }
                }

                @Override
                public void onAdFailedToLoad(@NonNull BannerView bannerView, @NonNull BannerError bannerError) {

                    if (onLoadBannerMediation!=null){
                        onLoadBannerMediation.onBannerAdFailedToLoad("");
                    }
                    SmallBanner(activity,layAds,PlacementID);
                }

                @Override
                public void onAdImpression(@NonNull BannerView bannerView) {

                }

                @Override
                public void onAdClicked(@NonNull BannerView bannerView) {
                    if (onLoadBannerMediation!=null){
                        onLoadBannerMediation.onBannerAdClicked();
                    }
                }

                @Override
                public void onAdTTLExpired(@NonNull BannerView bannerView) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void ScraperBanner(Activity activity, RelativeLayout layAds, String PlacementID){
        try {
            BannerView banner = new BannerView(activity);
            layAds.addView(banner);
            banner.loadAd(PlacementID, BannerAdSize.SKYSCRAPER_120x600);
            banner.setEventListener(new BannerView.EventListener() {
                @Override
                public void onAdLoaded(@NonNull BannerView bannerView) {
                    if (onLoadBannerMediation!=null){
                        onLoadBannerMediation.onBannerAdLoaded();
                    }
                }

                @Override
                public void onAdFailedToLoad(@NonNull BannerView bannerView, @NonNull BannerError bannerError) {

                    if (onLoadBannerMediation!=null){
                        onLoadBannerMediation.onBannerAdFailedToLoad("");
                    }
                    SmallBanner(activity,layAds,PlacementID);
                }

                @Override
                public void onAdImpression(@NonNull BannerView bannerView) {

                }

                @Override
                public void onAdClicked(@NonNull BannerView bannerView) {
                    if (onLoadBannerMediation!=null){
                        onLoadBannerMediation.onBannerAdClicked();
                    }
                }

                @Override
                public void onAdTTLExpired(@NonNull BannerView bannerView) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void LoadInterstitial (Activity activity, String PlacementID) {
        try {
            EventListener eventListener = new EventListener() {
                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                    interstitialAds = interstitialAd;
                    if (onLoadInterstitialMediation!=null){
                        onLoadInterstitialMediation.onInterstitialAdLoaded();
                    }

                }

                @Override
                public void onAdFailedToLoad(@NonNull InterstitialRequestError interstitialRequestError) {

                    if (onLoadInterstitialMediation!=null){
                        onLoadInterstitialMediation.onInterstitialAdFailedToLoad("");
                    }
                }

                @Override
                public void onAdError(@NonNull InterstitialAd interstitialAd, @NonNull InterstitialError interstitialError) {

                }

                @Override
                public void onAdOpened(@NonNull InterstitialAd interstitialAd) {

                }

                @Override
                public void onAdClosed(@NonNull InterstitialAd interstitialAd) {
                    if (onLoadInterstitialMediation!=null){
                        onLoadInterstitialMediation.onInterstitialAdClosed();
                    }
                }

                @Override
                public void onAdClicked(@NonNull InterstitialAd interstitialAd) {
                    if (onLoadInterstitialMediation!=null){
                        onLoadInterstitialMediation.onInterstitialAdClicked();
                    }

                }

                @Override
                public void onAdImpression(@NonNull InterstitialAd interstitialAd) {


                }

                @Override
                public void onAdTTLExpired(@NonNull InterstitialAd interstitialAd) {

                }
            };
            Interstitial.loadAd(PlacementID,eventListener, AdRequestParams.builder().build());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void ShowInterstitial (Activity activity) {
        if (interstitialAds!=null){
            interstitialAds.setBackgroundColor(0xff000000);
            interstitialAds.showAd(activity);
            if (onShowInterstitial !=null){
                onShowInterstitial.onAdSuccess();
            }
        } else {
            if (onShowInterstitial !=null){
                onShowInterstitial.onAdFailedShow();
            }
        }

    }

    public static void LoadRewarded (String PlacementID) {
        com.smaato.sdk.rewarded.EventListener eventListenerRewards = new com.smaato.sdk.rewarded.EventListener() {
            @Override
            public void onAdLoaded(@NonNull RewardedInterstitialAd rewardedInterstitialAd) {
                rewardedInterstitialAds = rewardedInterstitialAd;
                if (onLoadRewardsMediation!=null){
                    onLoadRewardsMediation.onRewardsAdLoaded();
                }

            }

            @Override
            public void onAdFailedToLoad(@NonNull RewardedRequestError rewardedRequestError) {
                if (onLoadRewardsMediation!=null){
                    onLoadRewardsMediation.onRewardsAdFailedToLoad("");
                }
            }

            @Override
            public void onAdError(@NonNull RewardedInterstitialAd rewardedInterstitialAd, @NonNull RewardedError rewardedError) {

            }

            @Override
            public void onAdClosed(@NonNull RewardedInterstitialAd rewardedInterstitialAd) {
                if (onLoadRewardsMediation!=null){
                    onLoadRewardsMediation.onRewardsAdClosed();
                }
            }

            @Override
            public void onAdClicked(@NonNull RewardedInterstitialAd rewardedInterstitialAd) {
                if (onLoadRewardsMediation!=null){
                    onLoadRewardsMediation.onRewardsAdClicked();
                }

            }

            @Override
            public void onAdStarted(@NonNull RewardedInterstitialAd rewardedInterstitialAd) {

            }

            @Override
            public void onAdReward(@NonNull RewardedInterstitialAd rewardedInterstitialAd) {
                if (onLoadRewardsMediation!=null){
                    onLoadRewardsMediation.onRewardsAdReward();
                }
            }

            @Override
            public void onAdTTLExpired(@NonNull RewardedInterstitialAd rewardedInterstitialAd) {

            }
        };

        RewardedInterstitial.loadAd(PlacementID,eventListenerRewards, AdRequestParams.builder().build());
    }

    public static void ShowReward() {
        if (rewardedInterstitialAds!=null){
            rewardedInterstitialAds.setCloseButtonEnabled(true);
            rewardedInterstitialAds.showAd();
            if (onShowRewards !=null){
                onShowRewards.onAdSuccess();
            }
        } else {
            if (onShowRewards !=null){
                onShowRewards.onAdFailedShow();
            }
        }

    }

    public static void SmallNatives( Activity activity, RelativeLayout content, String PlacementID) {
        NativeAd.Listener listener = new NativeAd.Listener() {
            @Override
            public void onAdLoaded(@NonNull NativeAd nativeAd, @NonNull NativeAdRenderer nativeAdRenderer) {
               try {
                   View adView = (View) activity.getLayoutInflater()
                           .inflate(R.layout.alien_small_native, null);
                   NativeAdView nativeAdView = new NativeMediation(adView) ;
                   nativeAdRenderer.renderInView(nativeAdView);
                   nativeAdRenderer.registerForImpression(adView);
                   nativeAdRenderer.registerForClicks(nativeAdView.ctaView());
                   content.addView(adView);

                   if (onLoadNative !=null){
                       onLoadNative.onNativeAdLoaded();
                   }

               } catch (Exception e) {
                   e.printStackTrace();
               }

            }

            @Override
            public void onAdFailedToLoad(@NonNull NativeAd nativeAd, @NonNull NativeAdError nativeAdError) {
                if (onLoadNative !=null){
                    onLoadNative.onNativeAdFailedToLoad("");
                }
            }

            @Override
            public void onAdImpressed(@NonNull NativeAd nativeAd) {

            }

            @Override
            public void onAdClicked(@NonNull NativeAd nativeAd) {
                if (onLoadNative !=null){
                    onLoadNative.onNativeAdClicked();
                }
            }

            @Override
            public void onTtlExpired(@NonNull NativeAd nativeAd) {

            }
        };

        NativeAd.loadAd(Lifecycling.of(activity), NativeAdRequest.builder().adSpaceId(PlacementID).build(), listener);


    }

    public static void MediumNatives( Activity activity, RelativeLayout content, String PlacementID) {
        NativeAd.Listener listener = new NativeAd.Listener() {
            @Override
            public void onAdLoaded(@NonNull NativeAd nativeAd, @NonNull NativeAdRenderer nativeAdRenderer) {
               try {
                   View adView = (View) activity.getLayoutInflater()
                           .inflate(R.layout.alien_big_native, null);
                   NativeAdView nativeAdView = new NativeMediation(adView) ;
                   nativeAdRenderer.renderInView(nativeAdView);
                   nativeAdRenderer.registerForImpression(adView);
                   nativeAdRenderer.registerForClicks(nativeAdView.ctaView());
                   content.addView(adView);

                   if (onLoadNative !=null){
                       onLoadNative.onNativeAdLoaded();
                   }
               } catch (Exception e) {
                   e.printStackTrace();
               }


            }

            @Override
            public void onAdFailedToLoad(@NonNull NativeAd nativeAd, @NonNull NativeAdError nativeAdError) {
                if (onLoadNative !=null){
                    onLoadNative.onNativeAdFailedToLoad("");
                }
            }

            @Override
            public void onAdImpressed(@NonNull NativeAd nativeAd) {

            }

            @Override
            public void onAdClicked(@NonNull NativeAd nativeAd) {
                if (onLoadNative !=null){
                    onLoadNative.onNativeAdClicked();
                }
            }

            @Override
            public void onTtlExpired(@NonNull NativeAd nativeAd) {

            }
        };

        NativeAd.loadAd(Lifecycling.of(activity), NativeAdRequest.builder().adSpaceId(PlacementID).build(), listener);


    }

}
