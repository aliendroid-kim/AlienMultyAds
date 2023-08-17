package com.aliendroid.alienads;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.aliendroid.alienads.config.AppLovinCustomEventBanner;
import com.aliendroid.alienads.interfaces.banner.OnLoadBannerAdmob;
import com.aliendroid.alienads.interfaces.banner.OnLoadBannerAlienMediation;
import com.aliendroid.alienads.interfaces.banner.OnLoadBannerAlienView;
import com.aliendroid.alienads.interfaces.banner.OnLoadBannerApplovinDiscovery;
import com.aliendroid.alienads.interfaces.banner.OnLoadBannerApplovinMax;
import com.aliendroid.alienads.interfaces.banner.OnLoadBannerFacebook;
import com.aliendroid.alienads.interfaces.banner.OnLoadBannerGoogle;
import com.aliendroid.alienads.interfaces.banner.OnLoadBannerIronSource;
import com.aliendroid.alienads.interfaces.banner.OnLoadBannerStartApp;
import com.aliendroid.alienads.interfaces.banner.OnLoadBannerWortise;
import com.aliendroid.sdkads.config.InitializeAlienAds;
import com.aliendroid.sdkads.interfaces.OnLoadBannerMediation;
import com.aliendroid.sdkads.interfaces.OnLoadBannerView;
import com.aliendroid.sdkads.type.mediation.AlienMediationAds;
import com.aliendroid.sdkads.type.view.AlienViewAds;
import com.applovin.adview.AppLovinAdView;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdkUtils;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerAdView;
import com.ironsource.mediationsdk.ISBannerSize;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.startapp.sdk.ads.banner.Banner;
import com.startapp.sdk.ads.banner.BannerListener;


public class AliendroidBanner {

    public static MaxAdView adViewMax;
    public static AdView adViewAdmob;
    public static AdView adViewAdmob2;
    public static AdManagerAdView bannerGoogleAds;
    public static AppLovinAdView adViewDiscovery;
    public static IronSourceBannerLayout adViewIron;
    public static Banner startAppBanner;
    public static com.facebook.ads.AdView adViewFAN;
    public static com.facebook.ads.AdView adViewFAN2;
    public static OnLoadBannerAdmob onLoadBannerAdmob;
    public static OnLoadBannerGoogle onLoadBannerGoogle;
    public static OnLoadBannerFacebook onLoadBannerFacebook;
    public static OnLoadBannerApplovinDiscovery onLoadBannerApplovinDiscovery;
    public static OnLoadBannerApplovinMax onLoadBannerApplovinMax;
    public static OnLoadBannerStartApp onLoadBannerStartApp;
    public static OnLoadBannerIronSource onLoadBannerIronSource;
    public static OnLoadBannerAlienView onLoadBannerAlienView;
    public static OnLoadBannerAlienMediation onLoadBannerAlienMediation;
    public static OnLoadBannerWortise onLoadBannerWortise;

    public static void SmallBannerAdmob(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup, String Hpk1,
                                        String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                .build();
        adViewAdmob = new AdView(activity);
        adViewAdmob.setAdUnitId(idBanner);
        layAds.addView(adViewAdmob);
        AdSize adSize = getAdSize(activity);
        adViewAdmob.setAdSize(adSize);
        adViewAdmob.loadAd(request);
        adViewAdmob.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                if (onLoadBannerAdmob != null) {
                    onLoadBannerAdmob.onAdLoaded();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        if (adViewMax != null) {
                            adViewMax.destroy();
                        }
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "IRON":
                        if (adViewIron != null) {
                            adViewIron.isDestroyed();
                        }
                        break;
                    case "STARTAPP":
                        if (startAppBanner != null) {
                            startAppBanner.hideBanner();
                        }
                        break;
                    case "APPLOVIN-D":
                        if (adViewDiscovery != null) {
                            adViewDiscovery.destroy();
                        }
                        break;
                    case "FACEBOOK":
                        if (adViewFAN != null) {
                            adViewFAN.destroy();
                        }
                        break;
                    case "WORTISE":
                        break;
                }
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                if (onLoadBannerAdmob != null) {
                    onLoadBannerAdmob.onAdFailedToLoad("");
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        adViewMax = new MaxAdView(idBannerBackup, activity);
                        MaxAdViewAdListener listener = new MaxAdViewAdListener() {
                            @Override
                            public void onAdExpanded(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdExpanded();
                                }
                            }

                            @Override
                            public void onAdCollapsed(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdCollapsed();
                                }
                            }

                            @Override
                            public void onAdLoaded(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdLoaded();
                                }
                            }

                            @Override
                            public void onAdDisplayed(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdDisplayed();
                                }
                            }

                            @Override
                            public void onAdHidden(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdHidden();
                                }
                            }

                            @Override
                            public void onAdClicked(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdClicked();
                                }
                            }

                            @Override
                            public void onAdLoadFailed(String adUnitId, MaxError error) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdLoadFailed();
                                }
                                layAds.setVisibility(View.GONE);
                            }

                            @Override
                            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdDisplayFailed();
                                }
                                layAds.setVisibility(View.GONE);
                            }
                        };
                        adViewMax.setListener(listener);
                        final boolean isTablet = AppLovinSdkUtils.isTablet(activity);
                        final int heightPx = AppLovinSdkUtils.dpToPx(activity, isTablet ? 90 : 50);
                        adViewMax.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightPx));
                        layAds.addView(adViewMax);
                        adViewMax.loadAd();
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "IRON":
                        adViewIron = IronSource.createBanner(activity, ISBannerSize.BANNER);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        layAds.addView(adViewIron, 0, layoutParams);
                        com.ironsource.mediationsdk.sdk.BannerListener listenerIron = new com.ironsource.mediationsdk.sdk.BannerListener() {
                            @Override
                            public void onBannerAdLoaded() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdLoaded();
                                }

                            }

                            @Override
                            public void onBannerAdLoadFailed(IronSourceError ironSourceError) {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdLoadFailed();
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onBannerAdClicked() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdClicked();
                                }
                            }

                            @Override
                            public void onBannerAdScreenPresented() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdScreenPresented();
                                }
                            }

                            @Override
                            public void onBannerAdScreenDismissed() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdScreenDismissed();
                                }
                            }

                            @Override
                            public void onBannerAdLeftApplication() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdLeftApplication();
                                }
                            }
                        };
                        adViewIron.setBannerListener(listenerIron);
                        IronSource.loadBanner(adViewIron, idBannerBackup);


                        break;
                    case "STARTAPP":
                        Banner startAppBanner = new Banner(activity, new BannerListener() {
                            @Override
                            public void onReceiveAd(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onReceiveAd();
                                }

                            }

                            @Override
                            public void onFailedToReceiveAd(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onFailedToReceiveAd("");
                                }
                                layAds.setVisibility(View.GONE);
                            }

                            @Override
                            public void onImpression(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onImpression();
                                }
                            }

                            @Override
                            public void onClick(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onClick();
                                }
                            }
                        });

                        RelativeLayout.LayoutParams bannerParameters =
                                new RelativeLayout.LayoutParams(
                                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                        bannerParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
                        layAds.addView(startAppBanner, bannerParameters);
                        break;
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                                .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5);
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", idBannerBackup);
                        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);

                        boolean isTablet2 = AppLovinSdkUtils.isTablet(activity);
                        AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
                        adViewDiscovery = new AppLovinAdView(adSize, activity);
                        AppLovinAdLoadListener loadListener = new AppLovinAdLoadListener() {
                            @Override
                            public void adReceived(AppLovinAd ad) {
                                if (onLoadBannerApplovinDiscovery != null) {
                                    onLoadBannerApplovinDiscovery.adReceived();
                                }

                            }

                            @Override
                            public void failedToReceiveAd(int errorCode) {
                                if (onLoadBannerApplovinDiscovery != null) {
                                    onLoadBannerApplovinDiscovery.failedToReceiveAd();
                                }
                                layAds.setVisibility(View.GONE);

                            }
                        };
                        adViewDiscovery.setAdLoadListener(loadListener);
                        layAds.addView(adViewDiscovery);
                        adViewDiscovery.loadNextAd();
                        break;
                    case "FACEBOOK":
                        adViewFAN = new com.facebook.ads.AdView(activity, idBannerBackup,
                                com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                        layAds.addView(adViewFAN);
                        com.facebook.ads.AdListener adListener = new com.facebook.ads.AdListener() {
                            @Override
                            public void onError(Ad ad, AdError adError) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onError();
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onAdLoaded();
                                }

                            }

                            @Override
                            public void onAdClicked(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onAdClicked();
                                }
                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onLoggingImpression();
                                }
                            }
                        };
                        adViewFAN.loadAd(adViewFAN.buildLoadAdConfig().withAdListener(adListener).build());
                        break;

                    case "ALIEN-V":
                        AlienViewAds.Banner(activity, layAds, idBannerBackup);
                        break;
                    case "ALIEN-M":
                        AlienMediationAds.SmallBanner(activity, layAds, idBannerBackup);
                        break;
                    case "ADMOB":
                        AdRequest request = new AdRequest.Builder()
                                .build();
                        adViewAdmob2 = new AdView(activity);
                        adViewAdmob2.setAdUnitId(idBannerBackup);
                        layAds.addView(adViewAdmob2);
                        AdSize adSizeAdmob = getAdSize(activity);
                        adViewAdmob2.setAdSize(adSizeAdmob);
                        adViewAdmob2.loadAd(request);
                        adViewAdmob2.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {

                            }

                            @Override
                            public void onAdFailedToLoad(LoadAdError adError) {
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAdOpened() {

                            }
                            @Override
                            public void onAdClicked() {

                            }
                            @Override
                            public void onAdClosed() {

                            }
                        });
                        break;
                    case "WORTISE":

                        break;
                }
            }

            @Override
            public void onAdOpened() {
                if (onLoadBannerAdmob != null) {
                    onLoadBannerAdmob.onAdOpened();
                }
            }

            @Override
            public void onAdClicked() {
                if (onLoadBannerAdmob != null) {
                    onLoadBannerAdmob.onAdClicked();
                }
            }

            @Override
            public void onAdClosed() {
                if (onLoadBannerAdmob != null) {
                    onLoadBannerAdmob.onAdClosed();
                }
            }
        });


    }

    public static void SmallBannerGoogleAds(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup) {

        AdManagerAdRequest adRequest =
                new AdManagerAdRequest.Builder()
                        .build();

        bannerGoogleAds = new AdManagerAdView(activity);
        bannerGoogleAds.setAdUnitId(idBanner);
        layAds.addView(bannerGoogleAds);
        AdSize adaptiveSize = getAdSize(activity);
        bannerGoogleAds.setAdSize(adaptiveSize);
        bannerGoogleAds.loadAd(adRequest);
        bannerGoogleAds.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                if (onLoadBannerGoogle != null) {
                    onLoadBannerGoogle.onAdLoaded();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        if (adViewMax != null) {
                            adViewMax.destroy();
                        }
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "IRON":
                        if (adViewIron != null) {
                            adViewIron.isDestroyed();
                        }
                        break;
                    case "STARTAPP":
                        if (startAppBanner != null) {
                            startAppBanner.hideBanner();
                        }
                        break;
                    case "APPLOVIN-D":
                        if (adViewDiscovery != null) {
                            adViewDiscovery.destroy();
                        }
                        break;
                    case "FACEBOOK":
                        if (adViewFAN != null) {
                            adViewFAN.destroy();
                        }
                        break;
                    case "WORTISE":

                        break;
                }
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                if (onLoadBannerGoogle != null) {
                    onLoadBannerGoogle.onAdFailedToLoad("");
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        adViewMax = new MaxAdView(idBannerBackup, activity);
                        MaxAdViewAdListener listener = new MaxAdViewAdListener() {
                            @Override
                            public void onAdExpanded(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdExpanded();
                                }
                            }

                            @Override
                            public void onAdCollapsed(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdCollapsed();
                                }
                            }

                            @Override
                            public void onAdLoaded(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdLoaded();
                                }

                            }

                            @Override
                            public void onAdDisplayed(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdDisplayed();
                                }
                            }

                            @Override
                            public void onAdHidden(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdHidden();
                                }
                            }

                            @Override
                            public void onAdClicked(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdClicked();
                                }
                            }

                            @Override
                            public void onAdLoadFailed(String adUnitId, MaxError error) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdLoadFailed();
                                }
                                layAds.setVisibility(View.GONE);
                            }

                            @Override
                            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdDisplayFailed();
                                }
                                layAds.setVisibility(View.GONE);
                            }
                        };
                        adViewMax.setListener(listener);
                        final boolean isTablet = AppLovinSdkUtils.isTablet(activity);
                        final int heightPx = AppLovinSdkUtils.dpToPx(activity, isTablet ? 90 : 50);
                        adViewMax.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightPx));
                        layAds.addView(adViewMax);
                        adViewMax.loadAd();
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "IRON":
                        adViewIron = IronSource.createBanner(activity, ISBannerSize.BANNER);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        layAds.addView(adViewIron, 0, layoutParams);
                        com.ironsource.mediationsdk.sdk.BannerListener listenerIron = new com.ironsource.mediationsdk.sdk.BannerListener() {
                            @Override
                            public void onBannerAdLoaded() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdLoaded();
                                }

                            }

                            @Override
                            public void onBannerAdLoadFailed(IronSourceError ironSourceError) {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdLoadFailed();
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onBannerAdClicked() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdClicked();
                                }
                            }

                            @Override
                            public void onBannerAdScreenPresented() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdScreenPresented();
                                }
                            }

                            @Override
                            public void onBannerAdScreenDismissed() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdScreenDismissed();
                                }
                            }

                            @Override
                            public void onBannerAdLeftApplication() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdLeftApplication();
                                }
                            }
                        };
                        adViewIron.setBannerListener(listenerIron);
                        IronSource.loadBanner(adViewIron, idBannerBackup);
                        break;
                    case "STARTAPP":
                        Banner startAppBanner = new Banner(activity, new BannerListener() {
                            @Override
                            public void onReceiveAd(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onReceiveAd();
                                }

                            }

                            @Override
                            public void onFailedToReceiveAd(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onFailedToReceiveAd("");
                                }
                                layAds.setVisibility(View.GONE);
                            }

                            @Override
                            public void onImpression(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onImpression();
                                }
                            }

                            @Override
                            public void onClick(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onClick();
                                }
                            }
                        });
                        RelativeLayout.LayoutParams bannerParameters =
                                new RelativeLayout.LayoutParams(
                                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                        bannerParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
                        layAds.addView(startAppBanner, bannerParameters);
                        break;
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", idBannerBackup);
                        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);

                        boolean isTablet2 = AppLovinSdkUtils.isTablet(activity);
                        AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
                        adViewDiscovery = new AppLovinAdView(adSize, activity);
                        AppLovinAdLoadListener loadListener = new AppLovinAdLoadListener() {
                            @Override
                            public void adReceived(AppLovinAd ad) {
                                if (onLoadBannerApplovinDiscovery != null) {
                                    onLoadBannerApplovinDiscovery.adReceived();
                                }

                            }

                            @Override
                            public void failedToReceiveAd(int errorCode) {
                                if (onLoadBannerApplovinDiscovery != null) {
                                    onLoadBannerApplovinDiscovery.failedToReceiveAd();
                                }
                                layAds.setVisibility(View.GONE);

                            }
                        };
                        adViewDiscovery.setAdLoadListener(loadListener);
                        layAds.addView(adViewDiscovery);
                        adViewDiscovery.loadNextAd();
                        break;
                    case "FACEBOOK":
                        adViewFAN = new com.facebook.ads.AdView(activity, idBannerBackup,
                                com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                        layAds.addView(adViewFAN);
                        com.facebook.ads.AdListener adListener = new com.facebook.ads.AdListener() {
                            @Override
                            public void onError(Ad ad, AdError adError) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onError();
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onAdLoaded();
                                }

                            }

                            @Override
                            public void onAdClicked(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onAdClicked();
                                }
                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onLoggingImpression();
                                }
                            }
                        };
                        adViewFAN.loadAd(adViewFAN.buildLoadAdConfig().withAdListener(adListener).build());
                        break;
                    case "ALIEN-V":
                        AlienViewAds.Banner(activity, layAds, idBannerBackup);
                        break;
                    case "ALIEN-M":
                        AlienMediationAds.SmallBanner(activity, layAds, idBannerBackup);
                        break;
                    case "WORTISE":

                        break;
                }
            }

            @Override
            public void onAdOpened() {
                if (onLoadBannerGoogle != null) {
                    onLoadBannerGoogle.onAdOpened();
                }
            }

            @Override
            public void onAdClicked() {
                if (onLoadBannerGoogle != null) {
                    onLoadBannerGoogle.onAdClicked();
                }
            }

            @Override
            public void onAdClosed() {
                if (onLoadBannerGoogle != null) {
                    onLoadBannerGoogle.onAdClosed();
                }
            }
        });


    }

    public static void SmallBannerFAN(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup) {
        adViewFAN = new com.facebook.ads.AdView(activity, idBanner,
                com.facebook.ads.AdSize.BANNER_HEIGHT_50);
        layAds.addView(adViewFAN);
        com.facebook.ads.AdListener adListener = new com.facebook.ads.AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                if (onLoadBannerFacebook != null) {
                    onLoadBannerFacebook.onError();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        adViewMax = new MaxAdView(idBannerBackup, activity);
                        MaxAdViewAdListener listener = new MaxAdViewAdListener() {
                            @Override
                            public void onAdExpanded(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdExpanded();
                                }
                            }

                            @Override
                            public void onAdCollapsed(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdCollapsed();
                                }
                            }

                            @Override
                            public void onAdLoaded(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdLoaded();
                                }
                            }

                            @Override
                            public void onAdDisplayed(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdDisplayed();
                                }
                            }

                            @Override
                            public void onAdHidden(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdHidden();
                                }
                            }

                            @Override
                            public void onAdClicked(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdClicked();
                                }
                            }

                            @Override
                            public void onAdLoadFailed(String adUnitId, MaxError error) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdLoadFailed();
                                }
                                layAds.setVisibility(View.GONE);
                            }

                            @Override
                            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdDisplayFailed();
                                }
                                layAds.setVisibility(View.GONE);
                            }
                        };
                        adViewMax.setListener(listener);
                        final boolean isTablet = AppLovinSdkUtils.isTablet(activity);
                        final int heightPx = AppLovinSdkUtils.dpToPx(activity, isTablet ? 90 : 50);
                        adViewMax.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightPx));
                        layAds.addView(adViewMax);
                        adViewMax.loadAd();
                        break;
                    case "MOPUB":
                    case "UNITY":
                        break;
                    case "FACEBOOK":
                        adViewFAN2 = new com.facebook.ads.AdView(activity, idBannerBackup,
                                com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                        layAds.addView(adViewFAN2);
                        com.facebook.ads.AdListener adListener = new com.facebook.ads.AdListener() {
                            @Override
                            public void onError(Ad ad, AdError adError) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onError();
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onAdLoaded();
                                }

                            }

                            @Override
                            public void onAdClicked(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onAdClicked();
                                }
                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onLoggingImpression();
                                }
                            }
                        };
                        adViewFAN2.loadAd(adViewFAN2.buildLoadAdConfig().withAdListener(adListener).build());
                        break;
                    case "IRON":
                        adViewIron = IronSource.createBanner(activity, ISBannerSize.BANNER);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        layAds.addView(adViewIron, 0, layoutParams);
                        com.ironsource.mediationsdk.sdk.BannerListener listenerIron = new com.ironsource.mediationsdk.sdk.BannerListener() {
                            @Override
                            public void onBannerAdLoaded() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdLoaded();
                                }

                            }

                            @Override
                            public void onBannerAdLoadFailed(IronSourceError ironSourceError) {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdLoadFailed();
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onBannerAdClicked() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdClicked();
                                }
                            }

                            @Override
                            public void onBannerAdScreenPresented() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdScreenPresented();
                                }
                            }

                            @Override
                            public void onBannerAdScreenDismissed() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdScreenDismissed();
                                }
                            }

                            @Override
                            public void onBannerAdLeftApplication() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdLeftApplication();
                                }
                            }
                        };
                        adViewIron.setBannerListener(listenerIron);
                        IronSource.loadBanner(adViewIron, idBannerBackup);
                        break;
                    case "STARTAPP":
                        Banner startAppBanner = new Banner(activity, new BannerListener() {
                            @Override
                            public void onReceiveAd(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onReceiveAd();
                                }

                            }

                            @Override
                            public void onFailedToReceiveAd(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onFailedToReceiveAd("");
                                }
                                layAds.setVisibility(View.GONE);
                            }

                            @Override
                            public void onImpression(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onImpression();
                                }
                            }

                            @Override
                            public void onClick(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onClick();
                                }
                            }
                        });
                        RelativeLayout.LayoutParams bannerParameters =
                                new RelativeLayout.LayoutParams(
                                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                        bannerParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
                        layAds.addView(startAppBanner, bannerParameters);
                        break;
                    case "ADMOB":
                        AdRequest request = new AdRequest.Builder()
                                .build();
                        adViewAdmob = new AdView(activity);
                        adViewAdmob.setAdUnitId(idBannerBackup);
                        layAds.addView(adViewAdmob);
                        AdSize adSizeAdmob = getAdSize(activity);
                        adViewAdmob.setAdSize(adSizeAdmob);
                        adViewAdmob.loadAd(request);
                        adViewAdmob.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdLoaded();
                                }

                            }

                            @Override
                            public void onAdFailedToLoad(LoadAdError adError) {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdFailedToLoad("");
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAdOpened() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdOpened();
                                }
                            }

                            @Override
                            public void onAdClicked() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdClicked();
                                }
                            }

                            @Override
                            public void onAdClosed() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdClosed();
                                }
                            }
                        });
                        break;
                    case "GOOGLE-ADS":
                        AdManagerAdRequest adRequest =
                                new AdManagerAdRequest.Builder()
                                        .build();

                        bannerGoogleAds = new AdManagerAdView(activity);
                        bannerGoogleAds.setAdUnitId(idBannerBackup);
                        layAds.addView(bannerGoogleAds);
                        AdSize adaptiveSize = getAdSize(activity);
                        bannerGoogleAds.setAdSize(adaptiveSize);
                        bannerGoogleAds.loadAd(adRequest);
                        bannerGoogleAds.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdLoaded();
                                }

                            }

                            @Override
                            public void onAdFailedToLoad(LoadAdError adError) {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdFailedToLoad("");
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAdOpened() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdOpened();
                                }
                            }

                            @Override
                            public void onAdClicked() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdClicked();
                                }
                            }

                            @Override
                            public void onAdClosed() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdClosed();
                                }
                            }
                        });
                        break;
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", idBannerBackup);
                        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);
                        boolean isTablet2 = AppLovinSdkUtils.isTablet(activity);
                        AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
                        adViewDiscovery = new AppLovinAdView(adSize, activity);
                        AppLovinAdLoadListener loadListener = new AppLovinAdLoadListener() {
                            @Override
                            public void adReceived(AppLovinAd ad) {
                                if (onLoadBannerApplovinDiscovery != null) {
                                    onLoadBannerApplovinDiscovery.adReceived();
                                }

                            }

                            @Override
                            public void failedToReceiveAd(int errorCode) {
                                if (onLoadBannerApplovinDiscovery != null) {
                                    onLoadBannerApplovinDiscovery.failedToReceiveAd();
                                }
                                layAds.setVisibility(View.GONE);

                            }
                        };
                        adViewDiscovery.setAdLoadListener(loadListener);
                        layAds.addView(adViewDiscovery);
                        adViewDiscovery.loadNextAd();
                        break;
                    case "ALIEN-V":
                        AlienViewAds.Banner(activity, layAds, idBannerBackup);
                        break;
                    case "ALIEN-M":
                        AlienMediationAds.SmallBanner(activity, layAds, idBannerBackup);
                        break;
                    case "WORTISE":

                        break;
                }
            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (onLoadBannerFacebook != null) {
                    onLoadBannerFacebook.onAdLoaded();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        if (adViewMax != null) {
                            adViewMax.destroy();
                        }
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "IRON":
                        if (adViewIron != null) {
                            adViewIron.isDestroyed();
                        }
                        break;
                    case "STARTAPP":
                        if (startAppBanner != null) {
                            startAppBanner.hideBanner();
                        }
                        break;
                    case "ADMOB":
                        if (adViewAdmob != null) {
                            adViewAdmob.destroy();
                        }
                        break;
                    case "GOOGLE-ADS":
                        if (bannerGoogleAds != null) {
                            bannerGoogleAds.destroy();
                        }
                        break;
                    case "APPLOVIN-D":
                        if (adViewDiscovery != null) {
                            adViewDiscovery.destroy();
                        }
                        break;
                    case "WORTISE":

                }
            }

            @Override
            public void onAdClicked(Ad ad) {
                if (onLoadBannerFacebook != null) {
                    onLoadBannerFacebook.onAdClicked();
                }
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                if (onLoadBannerFacebook != null) {
                    onLoadBannerFacebook.onLoggingImpression();
                }
            }
        };
        adViewFAN.loadAd(adViewFAN.buildLoadAdConfig().withAdListener(adListener).build());

    }

    public static void SmallBannerApplovinDisHPK(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup, String HPK1,
                                                 String HPK2, String HPK3, String HPK4, String HPK5) {

        AdRequest.Builder builder = new AdRequest.Builder().addKeyword(HPK1).addKeyword(HPK2).addKeyword(HPK3).addKeyword(HPK4).addKeyword(HPK5);
        Bundle bannerExtras = new Bundle();
        bannerExtras.putString("zone_id", idBanner);
        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);
        boolean isTablet2 = AppLovinSdkUtils.isTablet(activity);
        AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
        adViewDiscovery = new AppLovinAdView(adSize, activity);
        AppLovinAdLoadListener loadListener = new AppLovinAdLoadListener() {
            @Override
            public void adReceived(AppLovinAd ad) {
                if (onLoadBannerApplovinDiscovery != null) {
                    onLoadBannerApplovinDiscovery.adReceived();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        if (adViewMax != null) {
                            adViewMax.destroy();
                        }
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "IRON":
                        if (adViewIron != null) {
                            adViewIron.isDestroyed();
                        }
                        break;
                    case "STARTAPP":
                        if (startAppBanner != null) {
                            startAppBanner.hideBanner();
                        }
                        break;
                    case "ADMOB":
                        if (adViewAdmob != null) {
                            adViewAdmob.destroy();
                        }
                        break;

                    case "GOOGLE-ADS":
                        if (bannerGoogleAds != null) {
                            bannerGoogleAds.destroy();
                        }
                        break;
                    case "FACEBOOK":
                        if (adViewFAN != null) {
                            adViewFAN.destroy();
                        }
                        break;
                    case "WORTISE":

                        break;
                }
            }

            @Override
            public void failedToReceiveAd(int errorCode) {
                if (onLoadBannerApplovinDiscovery != null) {
                    onLoadBannerApplovinDiscovery.failedToReceiveAd();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        adViewMax = new MaxAdView(idBannerBackup, activity);
                        MaxAdViewAdListener listener = new MaxAdViewAdListener() {
                            @Override
                            public void onAdExpanded(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdExpanded();
                                }
                            }

                            @Override
                            public void onAdCollapsed(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdCollapsed();
                                }
                            }

                            @Override
                            public void onAdLoaded(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdLoaded();
                                }
                            }

                            @Override
                            public void onAdDisplayed(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdDisplayed();
                                }
                            }

                            @Override
                            public void onAdHidden(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdHidden();
                                }
                            }

                            @Override
                            public void onAdClicked(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdClicked();
                                }
                            }

                            @Override
                            public void onAdLoadFailed(String adUnitId, MaxError error) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdLoadFailed();
                                }
                                layAds.setVisibility(View.GONE);
                            }

                            @Override
                            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdDisplayFailed();
                                }
                                layAds.setVisibility(View.GONE);
                            }
                        };
                        adViewMax.setListener(listener);
                        final boolean isTablet = AppLovinSdkUtils.isTablet(activity);
                        final int heightPx = AppLovinSdkUtils.dpToPx(activity, isTablet ? 90 : 50);
                        adViewMax.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightPx));
                        layAds.addView(adViewMax);
                        adViewMax.loadAd();
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "IRON":
                        adViewIron = IronSource.createBanner(activity, ISBannerSize.BANNER);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        layAds.addView(adViewIron, 0, layoutParams);
                        com.ironsource.mediationsdk.sdk.BannerListener listenerIron = new com.ironsource.mediationsdk.sdk.BannerListener() {
                            @Override
                            public void onBannerAdLoaded() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdLoaded();
                                }

                            }

                            @Override
                            public void onBannerAdLoadFailed(IronSourceError ironSourceError) {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdLoadFailed();
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onBannerAdClicked() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdClicked();
                                }
                            }

                            @Override
                            public void onBannerAdScreenPresented() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdScreenPresented();
                                }
                            }

                            @Override
                            public void onBannerAdScreenDismissed() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdScreenDismissed();
                                }
                            }

                            @Override
                            public void onBannerAdLeftApplication() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdLeftApplication();
                                }
                            }
                        };
                        adViewIron.setBannerListener(listenerIron);
                        IronSource.loadBanner(adViewIron, idBannerBackup);
                        break;
                    case "STARTAPP":
                        Banner startAppBanner = new Banner(activity, new BannerListener() {
                            @Override
                            public void onReceiveAd(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onReceiveAd();
                                }

                            }

                            @Override
                            public void onFailedToReceiveAd(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onFailedToReceiveAd("");
                                }
                                layAds.setVisibility(View.GONE);
                            }

                            @Override
                            public void onImpression(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onImpression();
                                }
                            }

                            @Override
                            public void onClick(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onClick();
                                }
                            }
                        });
                        RelativeLayout.LayoutParams bannerParameters =
                                new RelativeLayout.LayoutParams(
                                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                        bannerParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
                        layAds.addView(startAppBanner, bannerParameters);
                        break;
                    case "ADMOB":
                        AdRequest request = new AdRequest.Builder()
                                .build();
                        adViewAdmob = new AdView(activity);
                        adViewAdmob.setAdUnitId(idBannerBackup);
                        layAds.addView(adViewAdmob);
                        AdSize adSizeAdmob = getAdSize(activity);
                        adViewAdmob.setAdSize(adSizeAdmob);
                        adViewAdmob.loadAd(request);
                        adViewAdmob.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdLoaded();
                                }

                            }

                            @Override
                            public void onAdFailedToLoad(LoadAdError adError) {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdFailedToLoad("");
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAdOpened() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdOpened();
                                }
                            }

                            @Override
                            public void onAdClicked() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdClicked();
                                }
                            }

                            @Override
                            public void onAdClosed() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdClosed();
                                }
                            }
                        });
                        break;
                    case "GOOGLE-ADS":
                        AdManagerAdRequest adRequest =
                                new AdManagerAdRequest.Builder()
                                        .build();

                        bannerGoogleAds = new AdManagerAdView(activity);
                        bannerGoogleAds.setAdUnitId(idBannerBackup);
                        layAds.addView(bannerGoogleAds);
                        AdSize adaptiveSize = getAdSize(activity);
                        bannerGoogleAds.setAdSize(adaptiveSize);
                        bannerGoogleAds.loadAd(adRequest);
                        bannerGoogleAds.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdLoaded();
                                }

                            }

                            @Override
                            public void onAdFailedToLoad(LoadAdError adError) {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdFailedToLoad("");
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAdOpened() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdOpened();
                                }
                            }

                            @Override
                            public void onAdClicked() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdClicked();
                                }
                            }

                            @Override
                            public void onAdClosed() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdClosed();
                                }
                            }
                        });
                        break;
                    case "FACEBOOK":
                        adViewFAN = new com.facebook.ads.AdView(activity, idBannerBackup,
                                com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                        layAds.addView(adViewFAN);
                        com.facebook.ads.AdListener adListener = new com.facebook.ads.AdListener() {
                            @Override
                            public void onError(Ad ad, AdError adError) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onError();
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onAdLoaded();
                                }

                            }

                            @Override
                            public void onAdClicked(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onAdClicked();
                                }
                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onLoggingImpression();
                                }
                            }
                        };
                        adViewFAN.loadAd(adViewFAN.buildLoadAdConfig().withAdListener(adListener).build());
                        break;
                    case "ALIEN-V":
                        AlienViewAds.Banner(activity, layAds, idBannerBackup);
                        break;
                    case "ALIEN-M":
                        AlienMediationAds.SmallBanner(activity, layAds, idBannerBackup);
                        break;
                    case "WORTISE":

                        break;
                }
            }
        };
        adViewDiscovery.setAdLoadListener(loadListener);
        layAds.addView(adViewDiscovery);
        adViewDiscovery.loadNextAd();

    }

    public static void SmallBannerApplovinDis(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup) {
        AdRequest.Builder builder = new AdRequest.Builder();
        Bundle bannerExtras = new Bundle();
        bannerExtras.putString("zone_id", idBanner);
        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);
        boolean isTablet2 = AppLovinSdkUtils.isTablet(activity);
        AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
        adViewDiscovery = new AppLovinAdView(adSize, activity);
        AppLovinAdLoadListener loadListener = new AppLovinAdLoadListener() {
            @Override
            public void adReceived(AppLovinAd ad) {
                if (onLoadBannerApplovinDiscovery != null) {
                    onLoadBannerApplovinDiscovery.adReceived();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        if (adViewMax != null) {
                            adViewMax.destroy();
                        }
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "IRON":
                        if (adViewIron != null) {
                            adViewIron.isDestroyed();
                        }
                        break;
                    case "STARTAPP":
                        if (startAppBanner != null) {
                            startAppBanner.hideBanner();
                        }
                        break;
                    case "ADMOB":
                        if (adViewAdmob != null) {
                            adViewAdmob.destroy();
                        }
                        break;
                    case "GOOGLE-ADS":
                        if (bannerGoogleAds != null) {
                            bannerGoogleAds.destroy();
                        }
                        break;
                    case "FACEBOOK":
                        if (adViewFAN != null) {
                            adViewFAN.destroy();
                        }
                        break;
                    case  "WORTISE" :

                        break;
                }
            }

            @Override
            public void failedToReceiveAd(int errorCode) {
                if (onLoadBannerApplovinDiscovery != null) {
                    onLoadBannerApplovinDiscovery.failedToReceiveAd();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        adViewMax = new MaxAdView(idBannerBackup, activity);
                        MaxAdViewAdListener listener = new MaxAdViewAdListener() {
                            @Override
                            public void onAdExpanded(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdExpanded();
                                }
                            }

                            @Override
                            public void onAdCollapsed(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdCollapsed();
                                }
                            }

                            @Override
                            public void onAdLoaded(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdLoaded();
                                }
                            }

                            @Override
                            public void onAdDisplayed(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdDisplayed();
                                }
                            }

                            @Override
                            public void onAdHidden(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdHidden();
                                }
                            }

                            @Override
                            public void onAdClicked(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdClicked();
                                }
                            }

                            @Override
                            public void onAdLoadFailed(String adUnitId, MaxError error) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdLoadFailed();
                                }
                                layAds.setVisibility(View.GONE);
                            }

                            @Override
                            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdDisplayFailed();
                                }
                                layAds.setVisibility(View.GONE);
                            }
                        };
                        adViewMax.setListener(listener);
                        final boolean isTablet = AppLovinSdkUtils.isTablet(activity);
                        final int heightPx = AppLovinSdkUtils.dpToPx(activity, isTablet ? 90 : 50);
                        adViewMax.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightPx));
                        layAds.addView(adViewMax);
                        adViewMax.loadAd();
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "IRON":
                        adViewIron = IronSource.createBanner(activity, ISBannerSize.BANNER);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        layAds.addView(adViewIron, 0, layoutParams);
                        com.ironsource.mediationsdk.sdk.BannerListener listenerIron = new com.ironsource.mediationsdk.sdk.BannerListener() {
                            @Override
                            public void onBannerAdLoaded() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdLoaded();
                                }

                            }

                            @Override
                            public void onBannerAdLoadFailed(IronSourceError ironSourceError) {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdLoadFailed();
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onBannerAdClicked() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdClicked();
                                }
                            }

                            @Override
                            public void onBannerAdScreenPresented() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdScreenPresented();
                                }
                            }

                            @Override
                            public void onBannerAdScreenDismissed() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdScreenDismissed();
                                }
                            }

                            @Override
                            public void onBannerAdLeftApplication() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdLeftApplication();
                                }
                            }
                        };
                        adViewIron.setBannerListener(listenerIron);
                        IronSource.loadBanner(adViewIron, idBannerBackup);
                        break;
                    case "STARTAPP":
                        Banner startAppBanner = new Banner(activity, new BannerListener() {
                            @Override
                            public void onReceiveAd(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onReceiveAd();
                                }

                            }

                            @Override
                            public void onFailedToReceiveAd(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onFailedToReceiveAd("");
                                }
                                layAds.setVisibility(View.GONE);
                            }

                            @Override
                            public void onImpression(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onImpression();
                                }
                            }

                            @Override
                            public void onClick(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onClick();
                                }
                            }
                        });
                        RelativeLayout.LayoutParams bannerParameters =
                                new RelativeLayout.LayoutParams(
                                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                        bannerParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
                        layAds.addView(startAppBanner, bannerParameters);
                        break;
                    case "ADMOB":
                        AdRequest request = new AdRequest.Builder()
                                .build();
                        adViewAdmob = new AdView(activity);
                        adViewAdmob.setAdUnitId(idBannerBackup);
                        layAds.addView(adViewAdmob);
                        AdSize adSizeAdmob = getAdSize(activity);
                        adViewAdmob.setAdSize(adSizeAdmob);
                        adViewAdmob.loadAd(request);
                        adViewAdmob.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdLoaded();
                                }

                            }

                            @Override
                            public void onAdFailedToLoad(LoadAdError adError) {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdFailedToLoad("");
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAdOpened() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdOpened();
                                }
                            }

                            @Override
                            public void onAdClicked() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdClicked();
                                }
                            }

                            @Override
                            public void onAdClosed() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdClosed();
                                }
                            }
                        });
                        break;
                    case "GOOGLE-ADS":
                        AdManagerAdRequest adRequest =
                                new AdManagerAdRequest.Builder()
                                        .build();

                        bannerGoogleAds = new AdManagerAdView(activity);
                        bannerGoogleAds.setAdUnitId(idBannerBackup);
                        layAds.addView(bannerGoogleAds);
                        AdSize adaptiveSize = getAdSize(activity);
                        bannerGoogleAds.setAdSize(adaptiveSize);
                        bannerGoogleAds.loadAd(adRequest);
                        bannerGoogleAds.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdLoaded();
                                }

                            }

                            @Override
                            public void onAdFailedToLoad(LoadAdError adError) {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdFailedToLoad("");
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAdOpened() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdOpened();
                                }
                            }

                            @Override
                            public void onAdClicked() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdClicked();
                                }
                            }

                            @Override
                            public void onAdClosed() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdClosed();
                                }
                            }
                        });
                        break;
                    case "FACEBOOK":
                        adViewFAN = new com.facebook.ads.AdView(activity, idBannerBackup,
                                com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                        layAds.addView(adViewFAN);
                        com.facebook.ads.AdListener adListener = new com.facebook.ads.AdListener() {
                            @Override
                            public void onError(Ad ad, AdError adError) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onError();
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onAdLoaded();
                                }

                            }

                            @Override
                            public void onAdClicked(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onAdClicked();
                                }
                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onLoggingImpression();
                                }
                            }
                        };
                        adViewFAN.loadAd(adViewFAN.buildLoadAdConfig().withAdListener(adListener).build());
                        break;
                    case "ALIEN-V":
                        AlienViewAds.Banner(activity, layAds, idBannerBackup);
                        break;
                    case "ALIEN-M":
                        AlienMediationAds.SmallBanner(activity, layAds, idBannerBackup);
                        break;
                    case "WORTISE":

                        break;
                }
            }
        };
        adViewDiscovery.setAdLoadListener(loadListener);
        layAds.addView(adViewDiscovery);
        adViewDiscovery.loadNextAd();

    }

    public static void SmallBannerApplovinMax(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup) {

        adViewMax = new MaxAdView(idBanner, activity);

        MaxAdViewAdListener listener = new MaxAdViewAdListener() {
            @Override
            public void onAdExpanded(MaxAd ad) {
                if (onLoadBannerApplovinMax != null) {
                    onLoadBannerApplovinMax.onAdExpanded();
                }
            }

            @Override
            public void onAdCollapsed(MaxAd ad) {
                if (onLoadBannerApplovinMax != null) {
                    onLoadBannerApplovinMax.onAdCollapsed();
                }
            }

            @Override
            public void onAdLoaded(MaxAd ad) {
                if (onLoadBannerApplovinMax != null) {
                    onLoadBannerApplovinMax.onAdLoaded();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-D":
                        if (adViewDiscovery != null) {
                            adViewDiscovery.destroy();
                        }
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "IRON":
                        if (adViewIron != null) {
                            adViewIron.isDestroyed();
                        }
                        break;
                    case "STARTAPP":
                        if (startAppBanner != null) {
                            startAppBanner.hideBanner();
                        }
                        break;
                    case "ADMOB":
                        if (adViewAdmob != null) {
                            adViewAdmob.destroy();
                        }
                        break;
                    case "GOOGLE-ADS":
                        if (bannerGoogleAds != null) {
                            bannerGoogleAds.destroy();
                        }
                        break;
                    case "FACEBOOK":
                        if (adViewFAN != null) {
                            adViewFAN.destroy();
                        }
                        break;
                    case  "WORTISE" :

                        break;
                }
            }

            @Override
            public void onAdDisplayed(MaxAd ad) {
                if (onLoadBannerApplovinMax != null) {
                    onLoadBannerApplovinMax.onAdDisplayed();
                }
            }

            @Override
            public void onAdHidden(MaxAd ad) {
                if (onLoadBannerApplovinMax != null) {
                    onLoadBannerApplovinMax.onAdHidden();
                }
            }

            @Override
            public void onAdClicked(MaxAd ad) {
                if (onLoadBannerApplovinMax != null) {
                    onLoadBannerApplovinMax.onAdClicked();
                }
            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {
                if (onLoadBannerApplovinMax != null) {
                    onLoadBannerApplovinMax.onAdLoadFailed();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", idBannerBackup);
                        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);
                        boolean isTablet2 = AppLovinSdkUtils.isTablet(activity);
                        AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
                        adViewDiscovery = new AppLovinAdView(adSize, activity);
                        AppLovinAdLoadListener loadListener = new AppLovinAdLoadListener() {
                            @Override
                            public void adReceived(AppLovinAd ad) {
                                if (onLoadBannerApplovinDiscovery != null) {
                                    onLoadBannerApplovinDiscovery.adReceived();
                                }

                            }

                            @Override
                            public void failedToReceiveAd(int errorCode) {
                                if (onLoadBannerApplovinDiscovery != null) {
                                    onLoadBannerApplovinDiscovery.failedToReceiveAd();
                                }
                                layAds.setVisibility(View.GONE);

                            }
                        };
                        adViewDiscovery.setAdLoadListener(loadListener);
                        layAds.addView(adViewDiscovery);
                        adViewDiscovery.loadNextAd();
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "IRON":
                        adViewIron = IronSource.createBanner(activity, ISBannerSize.BANNER);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        layAds.addView(adViewIron, 0, layoutParams);
                        com.ironsource.mediationsdk.sdk.BannerListener listenerIron = new com.ironsource.mediationsdk.sdk.BannerListener() {
                            @Override
                            public void onBannerAdLoaded() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdLoaded();
                                }

                            }

                            @Override
                            public void onBannerAdLoadFailed(IronSourceError ironSourceError) {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdLoadFailed();
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onBannerAdClicked() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdClicked();
                                }
                            }

                            @Override
                            public void onBannerAdScreenPresented() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdScreenPresented();
                                }
                            }

                            @Override
                            public void onBannerAdScreenDismissed() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdScreenDismissed();
                                }
                            }

                            @Override
                            public void onBannerAdLeftApplication() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdLeftApplication();
                                }
                            }
                        };
                        adViewIron.setBannerListener(listenerIron);
                        IronSource.loadBanner(adViewIron, idBannerBackup);
                        break;
                    case "STARTAPP":
                        Banner startAppBanner = new Banner(activity, new BannerListener() {
                            @Override
                            public void onReceiveAd(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onReceiveAd();
                                }

                            }

                            @Override
                            public void onFailedToReceiveAd(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onFailedToReceiveAd("");
                                }
                                layAds.setVisibility(View.GONE);
                            }

                            @Override
                            public void onImpression(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onImpression();
                                }
                            }

                            @Override
                            public void onClick(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onClick();
                                }
                            }
                        });
                        RelativeLayout.LayoutParams bannerParameters =
                                new RelativeLayout.LayoutParams(
                                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                        bannerParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
                        layAds.addView(startAppBanner, bannerParameters);
                        break;
                    case "ADMOB":
                        AdRequest request = new AdRequest.Builder()
                                .build();
                        adViewAdmob = new AdView(activity);
                        adViewAdmob.setAdUnitId(idBannerBackup);
                        layAds.addView(adViewAdmob);
                        AdSize adSizeAdmob = getAdSize(activity);
                        adViewAdmob.setAdSize(adSizeAdmob);
                        adViewAdmob.loadAd(request);
                        adViewAdmob.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdLoaded();
                                }

                            }

                            @Override
                            public void onAdFailedToLoad(LoadAdError adError) {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdFailedToLoad("");
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAdOpened() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdOpened();
                                }
                            }

                            @Override
                            public void onAdClicked() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdClicked();
                                }
                            }

                            @Override
                            public void onAdClosed() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdClosed();
                                }
                            }
                        });
                        break;
                    case "GOOGLE-ADS":
                        AdManagerAdRequest adRequest =
                                new AdManagerAdRequest.Builder()
                                        .build();

                        bannerGoogleAds = new AdManagerAdView(activity);
                        bannerGoogleAds.setAdUnitId(idBannerBackup);
                        layAds.addView(bannerGoogleAds);
                        AdSize adaptiveSize = getAdSize(activity);
                        bannerGoogleAds.setAdSize(adaptiveSize);
                        bannerGoogleAds.loadAd(adRequest);
                        bannerGoogleAds.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdLoaded();
                                }

                            }

                            @Override
                            public void onAdFailedToLoad(LoadAdError adError) {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdFailedToLoad("");
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAdOpened() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdOpened();
                                }
                            }

                            @Override
                            public void onAdClicked() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdClicked();
                                }
                            }

                            @Override
                            public void onAdClosed() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdClosed();
                                }
                            }
                        });
                        break;
                    case "FACEBOOK":
                        adViewFAN = new com.facebook.ads.AdView(activity, idBannerBackup,
                                com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                        layAds.addView(adViewFAN);
                        com.facebook.ads.AdListener adListener = new com.facebook.ads.AdListener() {
                            @Override
                            public void onError(Ad ad, AdError adError) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onError();
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onAdLoaded();
                                }

                            }

                            @Override
                            public void onAdClicked(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onAdClicked();
                                }
                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onLoggingImpression();
                                }
                            }
                        };
                        adViewFAN.loadAd(adViewFAN.buildLoadAdConfig().withAdListener(adListener).build());
                        break;
                    case "ALIEN-V":
                        AlienViewAds.Banner(activity, layAds, idBannerBackup);
                        break;
                    case "ALIEN-M":
                        AlienMediationAds.SmallBanner(activity, layAds, idBannerBackup);
                        break;
                    case "WORTISE":

                        break;
                }

            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                if (onLoadBannerApplovinMax != null) {
                    onLoadBannerApplovinMax.onAdDisplayFailed();
                }
            }
        };
        adViewMax.setListener(listener);
        final boolean isTablet = AppLovinSdkUtils.isTablet(activity);
        final int heightPx = AppLovinSdkUtils.dpToPx(activity, isTablet ? 90 : 50);
        adViewMax.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightPx));
        layAds.addView(adViewMax);
        adViewMax.loadAd();
    }

    public static void SmallBannerMopub(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup) {

    }

    public static void SmallBannerStartApp(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup) {
        Banner startAppBanner = new Banner(activity, new BannerListener() {
            @Override
            public void onReceiveAd(View view) {
                if (onLoadBannerStartApp != null) {
                    onLoadBannerStartApp.onReceiveAd();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-D":
                        if (adViewDiscovery != null) {
                            adViewDiscovery.destroy();
                        }
                        break;
                    case "APPLOVIN-M":
                        if (adViewMax != null) {
                            adViewMax.destroy();
                        }
                        break;
                    case "IRON":
                        if (adViewIron != null) {
                            adViewIron.isDestroyed();
                        }
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "ADMOB":
                        if (adViewAdmob != null) {
                            adViewAdmob.destroy();
                        }
                        break;
                    case "GOOGLE-ADS":
                        if (bannerGoogleAds != null) {
                            bannerGoogleAds.destroy();
                        }
                        break;
                    case "FACEBOOK":
                        if (adViewFAN != null) {
                            adViewFAN.destroy();
                        }
                        break;
                    case "WORTISE":

                        break;
                }
            }

            @Override
            public void onFailedToReceiveAd(View view) {
                if (onLoadBannerStartApp != null) {
                    onLoadBannerStartApp.onFailedToReceiveAd("");
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", idBannerBackup);
                        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);
                        boolean isTablet2 = AppLovinSdkUtils.isTablet(activity);
                        AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
                        adViewDiscovery = new AppLovinAdView(adSize, activity);
                        AppLovinAdLoadListener loadListener = new AppLovinAdLoadListener() {
                            @Override
                            public void adReceived(AppLovinAd ad) {
                                if (onLoadBannerApplovinDiscovery != null) {
                                    onLoadBannerApplovinDiscovery.adReceived();
                                }

                            }

                            @Override
                            public void failedToReceiveAd(int errorCode) {
                                if (onLoadBannerApplovinDiscovery != null) {
                                    onLoadBannerApplovinDiscovery.failedToReceiveAd();
                                }
                                layAds.setVisibility(View.GONE);

                            }
                        };
                        adViewDiscovery.setAdLoadListener(loadListener);
                        layAds.addView(adViewDiscovery);
                        adViewDiscovery.loadNextAd();
                        break;
                    case "APPLOVIN-M":
                        adViewMax = new MaxAdView(idBannerBackup, activity);
                        MaxAdViewAdListener listener = new MaxAdViewAdListener() {
                            @Override
                            public void onAdExpanded(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdExpanded();
                                }
                            }

                            @Override
                            public void onAdCollapsed(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdCollapsed();
                                }
                            }

                            @Override
                            public void onAdLoaded(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdLoaded();
                                }
                            }

                            @Override
                            public void onAdDisplayed(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdDisplayed();
                                }
                            }

                            @Override
                            public void onAdHidden(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdHidden();
                                }
                            }

                            @Override
                            public void onAdClicked(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdClicked();
                                }
                            }

                            @Override
                            public void onAdLoadFailed(String adUnitId, MaxError error) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdLoadFailed();
                                }
                                layAds.setVisibility(View.GONE);
                            }

                            @Override
                            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdDisplayFailed();
                                }
                                layAds.setVisibility(View.GONE);
                            }
                        };
                        adViewMax.setListener(listener);

                        final boolean isTablet = AppLovinSdkUtils.isTablet(activity);
                        final int heightPx = AppLovinSdkUtils.dpToPx(activity, isTablet ? 90 : 50);
                        adViewMax.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightPx));
                        layAds.addView(adViewMax);
                        adViewMax.loadAd();
                        break;
                    case "IRON":
                        adViewIron = IronSource.createBanner(activity, ISBannerSize.BANNER);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        layAds.addView(adViewIron, 0, layoutParams);
                        com.ironsource.mediationsdk.sdk.BannerListener listenerIron = new com.ironsource.mediationsdk.sdk.BannerListener() {
                            @Override
                            public void onBannerAdLoaded() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdLoaded();
                                }

                            }

                            @Override
                            public void onBannerAdLoadFailed(IronSourceError ironSourceError) {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdLoadFailed();
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onBannerAdClicked() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdClicked();
                                }
                            }

                            @Override
                            public void onBannerAdScreenPresented() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdScreenPresented();
                                }
                            }

                            @Override
                            public void onBannerAdScreenDismissed() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdScreenDismissed();
                                }
                            }

                            @Override
                            public void onBannerAdLeftApplication() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdLeftApplication();
                                }
                            }
                        };
                        adViewIron.setBannerListener(listenerIron);
                        IronSource.loadBanner(adViewIron, idBannerBackup);
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "ADMOB":
                        AdRequest request = new AdRequest.Builder()
                                .build();
                        adViewAdmob = new AdView(activity);
                        adViewAdmob.setAdUnitId(idBannerBackup);
                        layAds.addView(adViewAdmob);
                        AdSize adSizeAdmob = getAdSize(activity);
                        adViewAdmob.setAdSize(adSizeAdmob);
                        adViewAdmob.loadAd(request);
                        adViewAdmob.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdLoaded();
                                }

                            }

                            @Override
                            public void onAdFailedToLoad(LoadAdError adError) {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdFailedToLoad("");
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAdOpened() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdOpened();
                                }
                            }

                            @Override
                            public void onAdClicked() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdClicked();
                                }
                            }

                            @Override
                            public void onAdClosed() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdClosed();
                                }
                            }
                        });
                        break;
                    case "GOOGLE-ADS":
                        AdManagerAdRequest adRequest =
                                new AdManagerAdRequest.Builder()
                                        .build();

                        bannerGoogleAds = new AdManagerAdView(activity);
                        bannerGoogleAds.setAdUnitId(idBannerBackup);
                        layAds.addView(bannerGoogleAds);
                        AdSize adaptiveSize = getAdSize(activity);
                        bannerGoogleAds.setAdSize(adaptiveSize);
                        bannerGoogleAds.loadAd(adRequest);
                        bannerGoogleAds.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdLoaded();
                                }

                            }

                            @Override
                            public void onAdFailedToLoad(LoadAdError adError) {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdFailedToLoad("");
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAdOpened() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdOpened();
                                }
                            }

                            @Override
                            public void onAdClicked() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdClicked();
                                }
                            }

                            @Override
                            public void onAdClosed() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdClosed();
                                }
                            }
                        });
                        break;
                    case "FACEBOOK":
                        adViewFAN = new com.facebook.ads.AdView(activity, idBannerBackup,
                                com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                        layAds.addView(adViewFAN);
                        com.facebook.ads.AdListener adListener = new com.facebook.ads.AdListener() {
                            @Override
                            public void onError(Ad ad, AdError adError) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onError();
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onAdLoaded();
                                }

                            }

                            @Override
                            public void onAdClicked(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onAdClicked();
                                }
                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onLoggingImpression();
                                }
                            }
                        };
                        adViewFAN.loadAd(adViewFAN.buildLoadAdConfig().withAdListener(adListener).build());
                        break;
                    case "ALIEN-V":
                        AlienViewAds.Banner(activity, layAds, idBannerBackup);
                        break;
                    case "ALIEN-M":
                        AlienMediationAds.SmallBanner(activity, layAds, idBannerBackup);
                        break;
                    case "WORTISE":

                        break;
                }
            }

            @Override
            public void onImpression(View view) {
                if (onLoadBannerStartApp != null) {
                    onLoadBannerStartApp.onImpression();
                }
            }

            @Override
            public void onClick(View view) {
                if (onLoadBannerStartApp != null) {
                    onLoadBannerStartApp.onClick();
                }
            }
        });
        RelativeLayout.LayoutParams bannerParameters =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        bannerParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layAds.addView(startAppBanner, bannerParameters);
    }

    public static void SmallBannerIron(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup) {
        adViewIron = IronSource.createBanner(activity, ISBannerSize.BANNER);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        layAds.addView(adViewIron, 0, layoutParams);
        com.ironsource.mediationsdk.sdk.BannerListener listener = new com.ironsource.mediationsdk.sdk.BannerListener() {
            @Override
            public void onBannerAdLoaded() {
                if (onLoadBannerIronSource != null) {
                    onLoadBannerIronSource.onBannerAdLoaded();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-D":
                        if (adViewDiscovery != null) {
                            adViewDiscovery.destroy();
                        }
                        break;
                    case "APPLOVIN-M":
                        if (adViewMax != null) {
                            adViewMax.destroy();
                        }
                        break;
                    case "STARTAPP":
                        startAppBanner.hideBanner();
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "ADMOB":
                        if (adViewAdmob != null) {
                            adViewAdmob.destroy();
                        }
                        break;
                    case "GOOGLE-ADS":
                        if (bannerGoogleAds != null) {
                            bannerGoogleAds.destroy();
                        }
                        break;
                    case "FACEBOOK":
                        if (adViewFAN != null) {
                            adViewFAN.destroy();
                        }
                        break;
                    case "WORTISE":

                        break;
                }
            }

            @Override
            public void onBannerAdLoadFailed(IronSourceError ironSourceError) {
                if (onLoadBannerIronSource != null) {
                    onLoadBannerIronSource.onBannerAdLoadFailed();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", idBannerBackup);
                        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);
                        boolean isTablet2 = AppLovinSdkUtils.isTablet(activity);
                        AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
                        adViewDiscovery = new AppLovinAdView(adSize, activity);
                        layAds.addView(adViewDiscovery);
                        adViewDiscovery.loadNextAd();
                        break;
                    case "APPLOVIN-M":
                        adViewMax = new MaxAdView(idBannerBackup, activity);
                        MaxAdViewAdListener listener = new MaxAdViewAdListener() {
                            @Override
                            public void onAdExpanded(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdExpanded();
                                }
                            }

                            @Override
                            public void onAdCollapsed(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdCollapsed();
                                }
                            }

                            @Override
                            public void onAdLoaded(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdLoaded();
                                }
                            }

                            @Override
                            public void onAdDisplayed(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdDisplayed();
                                }
                            }

                            @Override
                            public void onAdHidden(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdHidden();
                                }
                            }

                            @Override
                            public void onAdClicked(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdClicked();
                                }
                            }

                            @Override
                            public void onAdLoadFailed(String adUnitId, MaxError error) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdLoadFailed();
                                }
                                layAds.setVisibility(View.GONE);
                            }

                            @Override
                            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdDisplayFailed();
                                }
                                layAds.setVisibility(View.GONE);
                            }
                        };
                        adViewMax.setListener(listener);
                        final boolean isTablet = AppLovinSdkUtils.isTablet(activity);
                        final int heightPx = AppLovinSdkUtils.dpToPx(activity, isTablet ? 90 : 50);
                        adViewMax.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightPx));
                        layAds.addView(adViewMax);
                        adViewMax.loadAd();
                        break;
                    case "STARTAPP":
                        Banner startAppBanner = new Banner(activity, new BannerListener() {
                            @Override
                            public void onReceiveAd(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onReceiveAd();
                                }

                            }

                            @Override
                            public void onFailedToReceiveAd(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onFailedToReceiveAd("");
                                }
                                layAds.setVisibility(View.GONE);
                            }

                            @Override
                            public void onImpression(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onImpression();
                                }
                            }

                            @Override
                            public void onClick(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onClick();
                                }
                            }
                        });
                        RelativeLayout.LayoutParams bannerParameters =
                                new RelativeLayout.LayoutParams(
                                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                        bannerParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
                        layAds.addView(startAppBanner, bannerParameters);
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "ADMOB":
                        AdRequest request = new AdRequest.Builder()
                                .build();
                        adViewAdmob = new AdView(activity);
                        adViewAdmob.setAdUnitId(idBannerBackup);
                        layAds.addView(adViewAdmob);
                        AdSize adSizeAdmob = getAdSize(activity);
                        adViewAdmob.setAdSize(adSizeAdmob);
                        adViewAdmob.loadAd(request);
                        adViewAdmob.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdLoaded();
                                }

                            }

                            @Override
                            public void onAdFailedToLoad(LoadAdError adError) {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdFailedToLoad("");
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAdOpened() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdOpened();
                                }
                            }

                            @Override
                            public void onAdClicked() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdClicked();
                                }
                            }

                            @Override
                            public void onAdClosed() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdClosed();
                                }
                            }
                        });
                        break;
                    case "GOOGLE-ADS":
                        AdManagerAdRequest adRequest =
                                new AdManagerAdRequest.Builder()
                                        .build();
                        bannerGoogleAds = new AdManagerAdView(activity);
                        bannerGoogleAds.setAdUnitId(idBannerBackup);
                        layAds.addView(bannerGoogleAds);
                        AdSize adaptiveSize = getAdSize(activity);
                        bannerGoogleAds.setAdSize(adaptiveSize);
                        bannerGoogleAds.loadAd(adRequest);
                        bannerGoogleAds.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdLoaded();
                                }

                            }

                            @Override
                            public void onAdFailedToLoad(LoadAdError adError) {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdFailedToLoad("");
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAdOpened() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdOpened();
                                }
                            }

                            @Override
                            public void onAdClicked() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdClicked();
                                }
                            }

                            @Override
                            public void onAdClosed() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdClosed();
                                }
                            }
                        });
                        break;
                    case "FACEBOOK":
                        adViewFAN = new com.facebook.ads.AdView(activity, idBannerBackup,
                                com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                        layAds.addView(adViewFAN);
                        com.facebook.ads.AdListener adListener = new com.facebook.ads.AdListener() {
                            @Override
                            public void onError(Ad ad, AdError adError) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onError();
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onAdLoaded();
                                }

                            }

                            @Override
                            public void onAdClicked(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onAdClicked();
                                }
                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onLoggingImpression();
                                }
                            }
                        };
                        adViewFAN.loadAd(adViewFAN.buildLoadAdConfig().withAdListener(adListener).build());
                        break;
                    case "ALIEN-V":
                        AlienViewAds.Banner(activity, layAds, idBannerBackup);
                        break;
                    case "ALIEN-M":
                        AlienMediationAds.SmallBanner(activity, layAds, idBannerBackup);
                        break;
                    case "WORTISE":

                        break;
                }
            }

            @Override
            public void onBannerAdClicked() {
                if (onLoadBannerIronSource != null) {
                    onLoadBannerIronSource.onBannerAdClicked();
                }
            }

            @Override
            public void onBannerAdScreenPresented() {
                if (onLoadBannerIronSource != null) {
                    onLoadBannerIronSource.onBannerAdScreenPresented();
                }
            }

            @Override
            public void onBannerAdScreenDismissed() {
                if (onLoadBannerIronSource != null) {
                    onLoadBannerIronSource.onBannerAdScreenDismissed();
                }
            }

            @Override
            public void onBannerAdLeftApplication() {
                if (onLoadBannerIronSource != null) {
                    onLoadBannerIronSource.onBannerAdLeftApplication();
                }
            }
        };
        adViewIron.setBannerListener(listener);
        IronSource.loadBanner(adViewIron, idBanner);
    }

    public static void SmallBannerUnity(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup) {

    }

    public static void SmallBannerAlienView(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup) {
        AlienViewAds.Banner(activity, layAds, idBanner);
        AlienViewAds.onLoadBannerView = new OnLoadBannerView() {
            @Override
            public void onBannerAdLoaded() {
                if (onLoadBannerAlienView != null) {
                    onLoadBannerAlienView.onBannerAdLoaded();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-D":
                        if (adViewDiscovery != null) {
                            adViewDiscovery.destroy();
                        }
                        break;
                    case "APPLOVIN-M":
                        if (adViewMax != null) {
                            adViewMax.destroy();
                        }
                        break;
                    case "STARTAPP":
                        startAppBanner.hideBanner();
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "ADMOB":
                        if (adViewAdmob != null) {
                            adViewAdmob.destroy();
                        }
                        break;
                    case "GOOGLE-ADS":
                        if (bannerGoogleAds != null) {
                            bannerGoogleAds.destroy();
                        }
                        break;
                    case "FACEBOOK":
                        if (adViewFAN != null) {
                            adViewFAN.destroy();
                        }
                        break;
                    case "IRON":
                        if (adViewIron != null) {
                            adViewIron.isDestroyed();
                        }
                        break;
                    case "WORTISE":

                        break;
                }
            }

            @Override
            public void onBannerAdClicked() {
                if (onLoadBannerAlienView != null) {
                    onLoadBannerAlienView.onBannerAdClicked();
                }
            }

            @Override
            public void onBannerAdFailedToLoad(String error) {
                if (onLoadBannerAlienView != null) {
                    onLoadBannerAlienView.onBannerAdFailedToLoad("");
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", idBannerBackup);
                        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);
                        boolean isTablet2 = AppLovinSdkUtils.isTablet(activity);
                        AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
                        adViewDiscovery = new AppLovinAdView(adSize, activity);
                        AppLovinAdLoadListener loadListener = new AppLovinAdLoadListener() {
                            @Override
                            public void adReceived(AppLovinAd ad) {
                                if (onLoadBannerApplovinDiscovery != null) {
                                    onLoadBannerApplovinDiscovery.adReceived();
                                }

                            }

                            @Override
                            public void failedToReceiveAd(int errorCode) {
                                if (onLoadBannerApplovinDiscovery != null) {
                                    onLoadBannerApplovinDiscovery.failedToReceiveAd();
                                }
                                layAds.setVisibility(View.GONE);

                            }
                        };
                        adViewDiscovery.setAdLoadListener(loadListener);
                        layAds.addView(adViewDiscovery);
                        adViewDiscovery.loadNextAd();
                        break;
                    case "APPLOVIN-M":
                        adViewMax = new MaxAdView(idBannerBackup, activity);
                        MaxAdViewAdListener listener = new MaxAdViewAdListener() {
                            @Override
                            public void onAdExpanded(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdExpanded();
                                }
                            }

                            @Override
                            public void onAdCollapsed(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdCollapsed();
                                }
                            }

                            @Override
                            public void onAdLoaded(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdLoaded();
                                }
                            }

                            @Override
                            public void onAdDisplayed(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdDisplayed();
                                }
                            }

                            @Override
                            public void onAdHidden(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdHidden();
                                }
                            }

                            @Override
                            public void onAdClicked(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdClicked();
                                }
                            }

                            @Override
                            public void onAdLoadFailed(String adUnitId, MaxError error) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdLoadFailed();
                                }
                                layAds.setVisibility(View.GONE);
                            }

                            @Override
                            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdDisplayFailed();
                                }
                                layAds.setVisibility(View.GONE);
                            }
                        };
                        adViewMax.setListener(listener);
                        final boolean isTablet = AppLovinSdkUtils.isTablet(activity);
                        final int heightPx = AppLovinSdkUtils.dpToPx(activity, isTablet ? 90 : 50);
                        adViewMax.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightPx));
                        layAds.addView(adViewMax);
                        adViewMax.loadAd();
                        break;
                    case "STARTAPP":
                        Banner startAppBanner = new Banner(activity, new BannerListener() {
                            @Override
                            public void onReceiveAd(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onReceiveAd();
                                }

                            }

                            @Override
                            public void onFailedToReceiveAd(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onFailedToReceiveAd("");
                                }
                                layAds.setVisibility(View.GONE);
                            }

                            @Override
                            public void onImpression(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onImpression();
                                }
                            }

                            @Override
                            public void onClick(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onClick();
                                }
                            }
                        });
                        RelativeLayout.LayoutParams bannerParameters =
                                new RelativeLayout.LayoutParams(
                                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                        bannerParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
                        layAds.addView(startAppBanner, bannerParameters);
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "ADMOB":
                        AdRequest request = new AdRequest.Builder()
                                .build();
                        adViewAdmob = new AdView(activity);
                        adViewAdmob.setAdUnitId(idBannerBackup);
                        layAds.addView(adViewAdmob);
                        AdSize adSizeAdmob = getAdSize(activity);
                        adViewAdmob.setAdSize(adSizeAdmob);
                        adViewAdmob.loadAd(request);
                        adViewAdmob.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdLoaded();
                                }

                            }

                            @Override
                            public void onAdFailedToLoad(LoadAdError adError) {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdFailedToLoad("");
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAdOpened() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdOpened();
                                }
                            }

                            @Override
                            public void onAdClicked() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdClicked();
                                }
                            }

                            @Override
                            public void onAdClosed() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdClosed();
                                }
                            }
                        });
                        break;
                    case "GOOGLE-ADS":
                        AdManagerAdRequest adRequest =
                                new AdManagerAdRequest.Builder()
                                        .build();

                        bannerGoogleAds = new AdManagerAdView(activity);
                        bannerGoogleAds.setAdUnitId(idBannerBackup);
                        layAds.addView(bannerGoogleAds);
                        AdSize adaptiveSize = getAdSize(activity);
                        bannerGoogleAds.setAdSize(adaptiveSize);
                        bannerGoogleAds.loadAd(adRequest);
                        bannerGoogleAds.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdLoaded();
                                }

                            }

                            @Override
                            public void onAdFailedToLoad(LoadAdError adError) {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdFailedToLoad("");
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAdOpened() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdOpened();
                                }
                            }

                            @Override
                            public void onAdClicked() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdClicked();
                                }
                            }

                            @Override
                            public void onAdClosed() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdClosed();
                                }
                            }
                        });
                        break;
                    case "FACEBOOK":
                        adViewFAN = new com.facebook.ads.AdView(activity, idBannerBackup,
                                com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                        layAds.addView(adViewFAN);
                        com.facebook.ads.AdListener adListener = new com.facebook.ads.AdListener() {
                            @Override
                            public void onError(Ad ad, AdError adError) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onError();
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onAdLoaded();
                                }

                            }

                            @Override
                            public void onAdClicked(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onAdClicked();
                                }
                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onLoggingImpression();
                                }
                            }
                        };
                        adViewFAN.loadAd(adViewFAN.buildLoadAdConfig().withAdListener(adListener).build());
                        break;
                    case "IRON":
                        adViewIron = IronSource.createBanner(activity, ISBannerSize.BANNER);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        layAds.addView(adViewIron, 0, layoutParams);
                        com.ironsource.mediationsdk.sdk.BannerListener listenerIron = new com.ironsource.mediationsdk.sdk.BannerListener() {
                            @Override
                            public void onBannerAdLoaded() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdLoaded();
                                }

                            }

                            @Override
                            public void onBannerAdLoadFailed(IronSourceError ironSourceError) {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdLoadFailed();
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onBannerAdClicked() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdClicked();
                                }
                            }

                            @Override
                            public void onBannerAdScreenPresented() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdScreenPresented();
                                }
                            }

                            @Override
                            public void onBannerAdScreenDismissed() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdScreenDismissed();
                                }
                            }

                            @Override
                            public void onBannerAdLeftApplication() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdLeftApplication();
                                }
                            }
                        };
                        adViewIron.setBannerListener(listenerIron);
                        IronSource.loadBanner(adViewIron, idBannerBackup);
                        break;
                    case "ALIEN-M":
                        AlienMediationAds.SmallBanner(activity, layAds, idBannerBackup);
                        break;
                    case "WORTISE":

                        break;
                }
            }
        };
    }

    public static void SmallBannerAlienMediation(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup) {
        AlienMediationAds.SmallBanner(activity, layAds, idBannerBackup);
        AlienMediationAds.onLoadBannerMediation = new OnLoadBannerMediation() {
            @Override
            public void onBannerAdLoaded() {
                if (onLoadBannerAlienMediation != null) {
                    onLoadBannerAlienMediation.onBannerAdLoaded();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-D":
                        if (adViewDiscovery != null) {
                            adViewDiscovery.destroy();
                        }
                        break;
                    case "APPLOVIN-M":
                        if (adViewMax != null) {
                            adViewMax.destroy();
                        }
                        break;
                    case "STARTAPP":
                        startAppBanner.hideBanner();
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "ADMOB":
                        if (adViewAdmob != null) {
                            adViewAdmob.destroy();
                        }
                        break;
                    case "GOOGLE-ADS":
                        if (bannerGoogleAds != null) {
                            bannerGoogleAds.destroy();
                        }
                        break;
                    case "FACEBOOK":
                        if (adViewFAN != null) {
                            adViewFAN.destroy();
                        }
                        break;
                    case "IRON":
                        if (adViewIron != null) {
                            adViewIron.isDestroyed();
                        }
                        break;
                    case "WORTISE" :

                        break;
                }
            }

            @Override
            public void onBannerAdClicked() {
                if (onLoadBannerAlienMediation != null) {
                    onLoadBannerAlienMediation.onBannerAdClicked();
                }
            }

            @Override
            public void onBannerAdFailedToLoad(String error) {
                if (onLoadBannerAlienMediation != null) {
                    onLoadBannerAlienMediation.onBannerAdFailedToLoad("");
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", idBannerBackup);
                        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);
                        boolean isTablet2 = AppLovinSdkUtils.isTablet(activity);
                        AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
                        adViewDiscovery = new AppLovinAdView(adSize, activity);
                        AppLovinAdLoadListener loadListener = new AppLovinAdLoadListener() {
                            @Override
                            public void adReceived(AppLovinAd ad) {
                                if (onLoadBannerApplovinDiscovery != null) {
                                    onLoadBannerApplovinDiscovery.adReceived();
                                }

                            }

                            @Override
                            public void failedToReceiveAd(int errorCode) {
                                if (onLoadBannerApplovinDiscovery != null) {
                                    onLoadBannerApplovinDiscovery.failedToReceiveAd();
                                }
                                layAds.setVisibility(View.GONE);

                            }
                        };
                        adViewDiscovery.setAdLoadListener(loadListener);
                        layAds.addView(adViewDiscovery);
                        adViewDiscovery.loadNextAd();
                        break;
                    case "APPLOVIN-M":
                        adViewMax = new MaxAdView(idBannerBackup, activity);
                        MaxAdViewAdListener listener = new MaxAdViewAdListener() {
                            @Override
                            public void onAdExpanded(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdExpanded();
                                }
                            }

                            @Override
                            public void onAdCollapsed(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdCollapsed();
                                }
                            }

                            @Override
                            public void onAdLoaded(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdLoaded();
                                }
                            }

                            @Override
                            public void onAdDisplayed(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdDisplayed();
                                }
                            }

                            @Override
                            public void onAdHidden(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdHidden();
                                }
                            }

                            @Override
                            public void onAdClicked(MaxAd ad) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdClicked();
                                }
                            }

                            @Override
                            public void onAdLoadFailed(String adUnitId, MaxError error) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdLoadFailed();
                                }
                                layAds.setVisibility(View.GONE);
                            }

                            @Override
                            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                                if (onLoadBannerApplovinMax != null) {
                                    onLoadBannerApplovinMax.onAdDisplayFailed();
                                }
                                layAds.setVisibility(View.GONE);
                            }
                        };
                        adViewMax.setListener(listener);
                        final boolean isTablet = AppLovinSdkUtils.isTablet(activity);
                        final int heightPx = AppLovinSdkUtils.dpToPx(activity, isTablet ? 90 : 50);
                        adViewMax.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightPx));
                        layAds.addView(adViewMax);
                        adViewMax.loadAd();
                        break;
                    case "STARTAPP":
                        Banner startAppBanner = new Banner(activity, new BannerListener() {
                            @Override
                            public void onReceiveAd(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onReceiveAd();
                                }

                            }

                            @Override
                            public void onFailedToReceiveAd(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onFailedToReceiveAd("");
                                }
                                layAds.setVisibility(View.GONE);
                            }

                            @Override
                            public void onImpression(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onImpression();
                                }
                            }

                            @Override
                            public void onClick(View view) {
                                if (onLoadBannerStartApp != null) {
                                    onLoadBannerStartApp.onClick();
                                }
                            }
                        });
                        RelativeLayout.LayoutParams bannerParameters =
                                new RelativeLayout.LayoutParams(
                                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                        bannerParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
                        layAds.addView(startAppBanner, bannerParameters);
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "ADMOB":
                        AdRequest request = new AdRequest.Builder()
                                .build();
                        adViewAdmob = new AdView(activity);
                        adViewAdmob.setAdUnitId(idBannerBackup);
                        layAds.addView(adViewAdmob);
                        AdSize adSizeAdmob = getAdSize(activity);
                        adViewAdmob.setAdSize(adSizeAdmob);
                        adViewAdmob.loadAd(request);
                        adViewAdmob.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdLoaded();
                                }

                            }

                            @Override
                            public void onAdFailedToLoad(LoadAdError adError) {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdFailedToLoad("");
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAdOpened() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdOpened();
                                }
                            }

                            @Override
                            public void onAdClicked() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdClicked();
                                }
                            }

                            @Override
                            public void onAdClosed() {
                                if (onLoadBannerAdmob != null) {
                                    onLoadBannerAdmob.onAdClosed();
                                }
                            }
                        });
                        break;
                    case "GOOGLE-ADS":
                        AdManagerAdRequest adRequest =
                                new AdManagerAdRequest.Builder()
                                        .build();
                        bannerGoogleAds = new AdManagerAdView(activity);
                        bannerGoogleAds.setAdUnitId(idBannerBackup);
                        layAds.addView(bannerGoogleAds);
                        AdSize adaptiveSize = getAdSize(activity);
                        bannerGoogleAds.setAdSize(adaptiveSize);
                        bannerGoogleAds.loadAd(adRequest);
                        bannerGoogleAds.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdLoaded();
                                }

                            }

                            @Override
                            public void onAdFailedToLoad(LoadAdError adError) {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdFailedToLoad("");
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAdOpened() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdOpened();
                                }
                            }

                            @Override
                            public void onAdClicked() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdClicked();
                                }
                            }

                            @Override
                            public void onAdClosed() {
                                if (onLoadBannerGoogle != null) {
                                    onLoadBannerGoogle.onAdClosed();
                                }
                            }
                        });
                        break;
                    case "FACEBOOK":
                        adViewFAN = new com.facebook.ads.AdView(activity, idBannerBackup,
                                com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                        layAds.addView(adViewFAN);
                        com.facebook.ads.AdListener adListener = new com.facebook.ads.AdListener() {
                            @Override
                            public void onError(Ad ad, AdError adError) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onError();
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onAdLoaded();
                                }

                            }

                            @Override
                            public void onAdClicked(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onAdClicked();
                                }
                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {
                                if (onLoadBannerFacebook != null) {
                                    onLoadBannerFacebook.onLoggingImpression();
                                }
                            }
                        };
                        adViewFAN.loadAd(adViewFAN.buildLoadAdConfig().withAdListener(adListener).build());
                        break;
                    case "IRON":
                        adViewIron = IronSource.createBanner(activity, ISBannerSize.BANNER);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        layAds.addView(adViewIron, 0, layoutParams);
                        com.ironsource.mediationsdk.sdk.BannerListener listenerIron = new com.ironsource.mediationsdk.sdk.BannerListener() {
                            @Override
                            public void onBannerAdLoaded() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdLoaded();
                                }

                            }

                            @Override
                            public void onBannerAdLoadFailed(IronSourceError ironSourceError) {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdLoadFailed();
                                }
                                layAds.setVisibility(View.GONE);

                            }

                            @Override
                            public void onBannerAdClicked() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdClicked();
                                }
                            }

                            @Override
                            public void onBannerAdScreenPresented() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdScreenPresented();
                                }
                            }

                            @Override
                            public void onBannerAdScreenDismissed() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdScreenDismissed();
                                }
                            }

                            @Override
                            public void onBannerAdLeftApplication() {
                                if (onLoadBannerIronSource != null) {
                                    onLoadBannerIronSource.onBannerAdLeftApplication();
                                }
                            }
                        };
                        adViewIron.setBannerListener(listenerIron);
                        IronSource.loadBanner(adViewIron, idBannerBackup);
                        break;
                    case "ALIEN-V":
                        AlienViewAds.Banner(activity, layAds, idBannerBackup);
                        break;
                    case "WORTISE":

                        break;
                }
            }
        };

    }

    public static void SmallBannerWortise(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup) {

    }

    private static AdSize getAdSize(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;
        int adWidth = (int) (widthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth);
    }


}
