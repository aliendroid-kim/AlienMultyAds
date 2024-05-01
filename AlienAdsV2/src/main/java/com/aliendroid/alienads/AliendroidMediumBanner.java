package com.aliendroid.alienads;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

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
import com.applovin.adview.AppLovinAdView;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
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
import com.props.adsmanager.PropsAdsManagement;
import com.startapp.sdk.ads.banner.Banner;
import com.startapp.sdk.ads.banner.BannerListener;
import com.startapp.sdk.ads.banner.Mrec;
import com.unity3d.services.banners.BannerErrorInfo;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;


public class AliendroidMediumBanner {
    public static MaxAdView jamboxBanner;
    public static MaxAdView adViewMax;
    public static AdView adViewAdmob;
    public static AdView adViewAdmob2;
    public static AdManagerAdView bannerGoogleAds;
    public static AppLovinAdView adViewDiscovery;
    public static Banner startAppBanner;
    public static Mrec startAppMrec;
    public static BannerView unityBanner;
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

    public static void MediumBannerAdmob(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup, String Hpk1,
                                         String Hpk2, String Hpk3, String Hpk4, String Hpk5) {

        AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                .build();
        adViewAdmob = new AdView(activity);
        adViewAdmob.setAdUnitId(idBanner);
        layAds.addView(adViewAdmob);
        adViewAdmob.setAdSize(AdSize.MEDIUM_RECTANGLE);
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
                    case "ALIEN-V":
                        if (jamboxBanner != null) {
                            jamboxBanner.destroy();
                        }
                        break;
                    case "UNITY":
                        if (unityBanner != null) {
                            unityBanner.destroy();
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

                }
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                if (onLoadBannerAdmob != null) {
                    onLoadBannerAdmob.onAdFailedToLoad("");
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        adViewMax = new MaxAdView(idBannerBackup, MaxAdFormat.MREC, activity);
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
                        adViewMax.stopAutoRefresh();
                        final int widthPx = AppLovinSdkUtils.dpToPx(activity, 300);
                        final int heightPx = AppLovinSdkUtils.dpToPx(activity, 250);
                        adViewMax.setLayoutParams(new ConstraintLayout.LayoutParams(widthPx, heightPx));
                        layAds.addView(adViewMax);
                        adViewMax.loadAd();
                        break;

                    case "UNITY":
                        unityBanner = new BannerView(activity, idBannerBackup, new UnityBannerSize(300, 250));
                        unityBanner.load();
                        layAds.addView(unityBanner);
                        break;
                    case "STARTAPP":
                        startAppMrec = new Mrec(activity, new BannerListener() {
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
                        layAds.addView(startAppMrec, bannerParameters);
                        break;
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                                .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5);
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", idBannerBackup);
                        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);
                        adViewDiscovery = new AppLovinAdView(AppLovinAdSize.MREC, activity);
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
                        ;
                        break;
                    case "FACEBOOK":
                        adViewFAN = new com.facebook.ads.AdView(activity, idBannerBackup,
                                com.facebook.ads.AdSize.RECTANGLE_HEIGHT_250);
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
                        jamboxBanner = new MaxAdView(idBannerBackup, MaxAdFormat.MREC, activity);
                        MaxAdViewAdListener listenerJambox = new MaxAdViewAdListener() {
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
                        jamboxBanner.setListener(listenerJambox);
                        jamboxBanner.stopAutoRefresh();
                        final int widthPxJambox = AppLovinSdkUtils.dpToPx(activity, 300);
                        final int heightPxJambox = AppLovinSdkUtils.dpToPx(activity, 250);
                        jamboxBanner.setLayoutParams(new ConstraintLayout.LayoutParams(widthPxJambox, heightPxJambox));
                        layAds.addView(jamboxBanner);
                        jamboxBanner.loadAd();
                        break;
                    case "ALIEN-M":
                        PropsAdsManagement propsAds = new PropsAdsManagement(activity);
                        AdView adView = propsAds.createBannerAdview("MEDIUM_RECTANGLE", idBannerBackup);
                        AdRequest adRequestProps = new AdRequest.Builder().build();
                        layAds.addView(adView);
                        adView.loadAd(adRequestProps);
                        break;
                    case "ADMOB":
                        AdRequest request = new AdRequest.Builder()
                                .build();
                        adViewAdmob2 = new AdView(activity);
                        adViewAdmob2.setAdUnitId(idBannerBackup);
                        layAds.addView(adViewAdmob2);
                        adViewAdmob2.setAdSize(AdSize.MEDIUM_RECTANGLE);
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
                    onLoadBannerAdmob.onAdClosed();
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

    public static void MediumBannerGoogleAds(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup) {

        AdManagerAdRequest adRequest =
                new AdManagerAdRequest.Builder()
                        .build();

        bannerGoogleAds = new AdManagerAdView(activity);
        bannerGoogleAds.setAdUnitId(idBanner);
        layAds.addView(bannerGoogleAds);
        bannerGoogleAds.setAdSize(AdSize.MEDIUM_RECTANGLE);
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
                    case "ALIEN-V":
                        if (jamboxBanner != null) {
                            jamboxBanner.destroy();
                        }
                        break;
                    case "UNITY":
                        if (unityBanner != null) {
                            unityBanner.destroy();
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


                }
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                if (onLoadBannerGoogle != null) {
                    onLoadBannerGoogle.onAdFailedToLoad("");
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        adViewMax = new MaxAdView(idBannerBackup, MaxAdFormat.MREC, activity);
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
                        adViewMax.stopAutoRefresh();
                        final int widthPx = AppLovinSdkUtils.dpToPx(activity, 300);
                        final int heightPx = AppLovinSdkUtils.dpToPx(activity, 250);
                        adViewMax.setLayoutParams(new ConstraintLayout.LayoutParams(widthPx, heightPx));
                        layAds.addView(adViewMax);
                        adViewMax.loadAd();
                        break;

                    case "UNITY":
                        unityBanner = new BannerView(activity, idBannerBackup, new UnityBannerSize(300, 250));
                        unityBanner.load();
                        layAds.addView(unityBanner);
                        break;
                    case "STARTAPP":
                        startAppMrec = new Mrec(activity, new BannerListener() {
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
                        layAds.addView(startAppMrec, bannerParameters);
                        break;
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", idBannerBackup);
                        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);
                        adViewDiscovery = new AppLovinAdView(AppLovinAdSize.MREC, activity);
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
                        ;
                        break;
                    case "FACEBOOK":
                        adViewFAN = new com.facebook.ads.AdView(activity, idBannerBackup,
                                com.facebook.ads.AdSize.RECTANGLE_HEIGHT_250);
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
                        jamboxBanner = new MaxAdView(idBannerBackup, MaxAdFormat.MREC, activity);
                        MaxAdViewAdListener listenerJambox = new MaxAdViewAdListener() {
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
                        jamboxBanner.setListener(listenerJambox);
                        jamboxBanner.stopAutoRefresh();
                        final int widthPxJambox = AppLovinSdkUtils.dpToPx(activity, 300);
                        final int heightPxJambox = AppLovinSdkUtils.dpToPx(activity, 250);
                        jamboxBanner.setLayoutParams(new ConstraintLayout.LayoutParams(widthPxJambox, heightPxJambox));
                        layAds.addView(jamboxBanner);
                        jamboxBanner.loadAd();
                        break;
                    case "ALIEN-M":
                        PropsAdsManagement propsAds = new PropsAdsManagement(activity);
                        AdView adView = propsAds.createBannerAdview("MEDIUM_RECTANGLE", idBannerBackup);
                        AdRequest adRequestProps = new AdRequest.Builder().build();
                        layAds.addView(adView);
                        adView.loadAd(adRequestProps);
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

    public static void MediumBannerFAN(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup) {
        adViewFAN = new com.facebook.ads.AdView(activity, idBanner,
                com.facebook.ads.AdSize.RECTANGLE_HEIGHT_250);
        layAds.addView(adViewFAN);
        com.facebook.ads.AdListener adListener = new com.facebook.ads.AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                if (onLoadBannerFacebook != null) {
                    onLoadBannerFacebook.onError();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        adViewMax = new MaxAdView(idBannerBackup, MaxAdFormat.MREC, activity);
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
                        adViewMax.stopAutoRefresh();
                        final int widthPx = AppLovinSdkUtils.dpToPx(activity, 300);
                        final int heightPx = AppLovinSdkUtils.dpToPx(activity, 250);
                        adViewMax.setLayoutParams(new ConstraintLayout.LayoutParams(widthPx, heightPx));
                        layAds.addView(adViewMax);
                        adViewMax.loadAd();
                        break;

                    case "UNITY":
                        unityBanner = new BannerView(activity, idBannerBackup, new UnityBannerSize(300, 250));
                        unityBanner.load();
                        layAds.addView(unityBanner);
                        break;
                    case "STARTAPP":
                        startAppMrec = new Mrec(activity, new BannerListener() {
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
                        layAds.addView(startAppMrec, bannerParameters);
                        break;
                    case "ADMOB":

                        AdRequest request = new AdRequest.Builder()
                                .build();
                        adViewAdmob = new AdView(activity);
                        adViewAdmob.setAdUnitId(idBannerBackup);
                        layAds.addView(adViewAdmob);
                        adViewAdmob.setAdSize(AdSize.MEDIUM_RECTANGLE);
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
                        bannerGoogleAds.setAdSize(AdSize.MEDIUM_RECTANGLE);
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
                        adViewDiscovery = new AppLovinAdView(AppLovinAdSize.MREC, activity);
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
                        ;
                        break;
                    case "ALIEN-V":
                        jamboxBanner = new MaxAdView(idBannerBackup, MaxAdFormat.MREC, activity);
                        MaxAdViewAdListener listenerJambox = new MaxAdViewAdListener() {
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
                        jamboxBanner.setListener(listenerJambox);
                        jamboxBanner.stopAutoRefresh();
                        final int widthPxJambox = AppLovinSdkUtils.dpToPx(activity, 300);
                        final int heightPxJambox = AppLovinSdkUtils.dpToPx(activity, 250);
                        jamboxBanner.setLayoutParams(new ConstraintLayout.LayoutParams(widthPxJambox, heightPxJambox));
                        layAds.addView(jamboxBanner);
                        jamboxBanner.loadAd();
                        break;
                    case "ALIEN-M":
                        PropsAdsManagement propsAds = new PropsAdsManagement(activity);
                        AdView adView = propsAds.createBannerAdview("MEDIUM_RECTANGLE", idBannerBackup);
                        AdRequest adRequestProps = new AdRequest.Builder().build();
                        layAds.addView(adView);
                        adView.loadAd(adRequestProps);
                        break;
                    case "FACEBOOK":
                        adViewFAN2 = new com.facebook.ads.AdView(activity, idBannerBackup,
                                com.facebook.ads.AdSize.RECTANGLE_HEIGHT_250);
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
                    case "ALIEN-V":
                        if (jamboxBanner != null) {
                            jamboxBanner.destroy();
                        }
                        break;
                    case "UNITY":
                        if (unityBanner != null) {
                            unityBanner.destroy();
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

    public static void MediumBannerApplovinDisHPK(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup, String HPK1,
                                                  String HPK2, String HPK3, String HPK4, String HPK5) {

        AdRequest.Builder builder = new AdRequest.Builder().addKeyword(HPK1).addKeyword(HPK2).addKeyword(HPK3).addKeyword(HPK4).addKeyword(HPK5);
        Bundle bannerExtras = new Bundle();
        bannerExtras.putString("zone_id", idBanner);
        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);
        adViewDiscovery = new AppLovinAdView(AppLovinAdSize.MREC, activity);
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
                    case "ALIEN-V":
                        if (jamboxBanner != null) {
                            jamboxBanner.destroy();
                        }
                        break;
                    case "UNITY":
                        if (unityBanner != null) {
                            unityBanner.destroy();
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

                }
            }

            @Override
            public void failedToReceiveAd(int errorCode) {
                if (onLoadBannerApplovinDiscovery != null) {
                    onLoadBannerApplovinDiscovery.failedToReceiveAd();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        adViewMax = new MaxAdView(idBannerBackup, MaxAdFormat.MREC, activity);
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
                        adViewMax.stopAutoRefresh();
                        final int widthPx = AppLovinSdkUtils.dpToPx(activity, 300);
                        final int heightPx = AppLovinSdkUtils.dpToPx(activity, 250);
                        adViewMax.setLayoutParams(new ConstraintLayout.LayoutParams(widthPx, heightPx));
                        layAds.addView(adViewMax);
                        adViewMax.loadAd();
                        break;

                    case "UNITY":
                        unityBanner = new BannerView(activity, idBannerBackup, new UnityBannerSize(300, 250));
                        unityBanner.load();
                        layAds.addView(unityBanner);
                        break;
                    case "STARTAPP":
                        startAppMrec = new Mrec(activity, new BannerListener() {
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
                        layAds.addView(startAppMrec, bannerParameters);
                        break;
                    case "ADMOB":

                        AdRequest request = new AdRequest.Builder()
                                .build();
                        adViewAdmob = new AdView(activity);
                        adViewAdmob.setAdUnitId(idBannerBackup);
                        layAds.addView(adViewAdmob);
                        adViewAdmob.setAdSize(AdSize.MEDIUM_RECTANGLE);
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
                        bannerGoogleAds.setAdSize(AdSize.MEDIUM_RECTANGLE);
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
                                com.facebook.ads.AdSize.RECTANGLE_HEIGHT_250);
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
                        jamboxBanner = new MaxAdView(idBannerBackup, MaxAdFormat.MREC, activity);
                        MaxAdViewAdListener listenerJambox = new MaxAdViewAdListener() {
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
                        jamboxBanner.setListener(listenerJambox);
                        jamboxBanner.stopAutoRefresh();
                        final int widthPxJambox = AppLovinSdkUtils.dpToPx(activity, 300);
                        final int heightPxJambox = AppLovinSdkUtils.dpToPx(activity, 250);
                        jamboxBanner.setLayoutParams(new ConstraintLayout.LayoutParams(widthPxJambox, heightPxJambox));
                        layAds.addView(jamboxBanner);
                        jamboxBanner.loadAd();
                        break;
                    case "ALIEN-M":
                        PropsAdsManagement propsAds = new PropsAdsManagement(activity);
                        AdView adView = propsAds.createBannerAdview("MEDIUM_RECTANGLE", idBannerBackup);
                        AdRequest adRequestProps = new AdRequest.Builder().build();
                        layAds.addView(adView);
                        adView.loadAd(adRequestProps);
                        break;
                }
            }
        };
        adViewDiscovery.setAdLoadListener(loadListener);
        layAds.addView(adViewDiscovery);
        adViewDiscovery.loadNextAd();

    }

    public static void MediumBannerApplovinDis(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup) {
        AdRequest.Builder builder = new AdRequest.Builder();
        Bundle bannerExtras = new Bundle();
        bannerExtras.putString("zone_id", idBanner);
        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);
        adViewDiscovery = new AppLovinAdView(AppLovinAdSize.MREC, activity);
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
                    case "ALIEN-V":
                        if (jamboxBanner != null) {
                            jamboxBanner.destroy();
                        }
                        break;
                    case "UNITY":
                        if (unityBanner != null) {
                            unityBanner.destroy();
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

                }
            }

            @Override
            public void failedToReceiveAd(int errorCode) {
                if (onLoadBannerApplovinDiscovery != null) {
                    onLoadBannerApplovinDiscovery.failedToReceiveAd();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        adViewMax = new MaxAdView(idBannerBackup, MaxAdFormat.MREC, activity);
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
                        adViewMax.stopAutoRefresh();
                        final int widthPx = AppLovinSdkUtils.dpToPx(activity, 300);
                        final int heightPx = AppLovinSdkUtils.dpToPx(activity, 250);
                        adViewMax.setLayoutParams(new ConstraintLayout.LayoutParams(widthPx, heightPx));
                        layAds.addView(adViewMax);
                        adViewMax.loadAd();
                        break;

                    case "UNITY":
                        unityBanner = new BannerView(activity, idBannerBackup, new UnityBannerSize(300, 250));
                        unityBanner.load();
                        layAds.addView(unityBanner);
                        break;
                    case "STARTAPP":
                        startAppMrec = new Mrec(activity, new BannerListener() {
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
                        layAds.addView(startAppMrec, bannerParameters);
                        break;
                    case "ADMOB":

                        AdRequest request = new AdRequest.Builder()
                                .build();
                        adViewAdmob = new AdView(activity);
                        adViewAdmob.setAdUnitId(idBannerBackup);
                        layAds.addView(adViewAdmob);
                        adViewAdmob.setAdSize(AdSize.MEDIUM_RECTANGLE);
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
                        bannerGoogleAds.setAdSize(AdSize.MEDIUM_RECTANGLE);
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
                                com.facebook.ads.AdSize.RECTANGLE_HEIGHT_250);
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
                        jamboxBanner = new MaxAdView(idBannerBackup, MaxAdFormat.MREC, activity);
                        MaxAdViewAdListener listenerJambox = new MaxAdViewAdListener() {
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
                        jamboxBanner.setListener(listenerJambox);
                        jamboxBanner.stopAutoRefresh();
                        final int widthPxJambox = AppLovinSdkUtils.dpToPx(activity, 300);
                        final int heightPxJambox = AppLovinSdkUtils.dpToPx(activity, 250);
                        jamboxBanner.setLayoutParams(new ConstraintLayout.LayoutParams(widthPxJambox, heightPxJambox));
                        layAds.addView(jamboxBanner);
                        jamboxBanner.loadAd();
                        break;
                    case "ALIEN-M":
                        PropsAdsManagement propsAds = new PropsAdsManagement(activity);
                        AdView adView = propsAds.createBannerAdview("MEDIUM_RECTANGLE", idBannerBackup);
                        AdRequest adRequestProps = new AdRequest.Builder().build();
                        layAds.addView(adView);
                        adView.loadAd(adRequestProps);
                        break;
                }
            }
        };
        adViewDiscovery.setAdLoadListener(loadListener);
        layAds.addView(adViewDiscovery);
        adViewDiscovery.loadNextAd();

    }

    public static void MediumBannerApplovinMax(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup) {
        adViewMax = new MaxAdView(idBanner, MaxAdFormat.MREC, activity);
        adViewMax.stopAutoRefresh();
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
                    case "ALIEN-V":
                        if (jamboxBanner != null) {
                            jamboxBanner.destroy();
                        }
                        break;
                    case "UNITY":
                        if (unityBanner != null) {
                            unityBanner.destroy();
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
                        adViewDiscovery = new AppLovinAdView(AppLovinAdSize.MREC, activity);
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
                        ;
                        break;

                    case "UNITY":
                        unityBanner = new BannerView(activity, idBannerBackup, new UnityBannerSize(300, 250));
                        unityBanner.load();
                        layAds.addView(unityBanner);
                        break;
                    case "STARTAPP":
                        startAppMrec = new Mrec(activity, new BannerListener() {
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
                        layAds.addView(startAppMrec, bannerParameters);
                        break;
                    case "ADMOB":

                        AdRequest request = new AdRequest.Builder()
                                .build();
                        adViewAdmob = new AdView(activity);
                        adViewAdmob.setAdUnitId(idBannerBackup);
                        layAds.addView(adViewAdmob);
                        adViewAdmob.setAdSize(AdSize.MEDIUM_RECTANGLE);
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
                        bannerGoogleAds.setAdSize(AdSize.MEDIUM_RECTANGLE);
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
                                com.facebook.ads.AdSize.RECTANGLE_HEIGHT_250);
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
                        jamboxBanner = new MaxAdView(idBannerBackup, MaxAdFormat.MREC, activity);
                        MaxAdViewAdListener listenerJambox = new MaxAdViewAdListener() {
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
                        jamboxBanner.setListener(listenerJambox);
                        jamboxBanner.stopAutoRefresh();
                        final int widthPxJambox = AppLovinSdkUtils.dpToPx(activity, 300);
                        final int heightPxJambox = AppLovinSdkUtils.dpToPx(activity, 250);
                        jamboxBanner.setLayoutParams(new ConstraintLayout.LayoutParams(widthPxJambox, heightPxJambox));
                        layAds.addView(jamboxBanner);
                        jamboxBanner.loadAd();
                        break;
                    case "ALIEN-M":
                        PropsAdsManagement propsAds = new PropsAdsManagement(activity);
                        AdView adView = propsAds.createBannerAdview("MEDIUM_RECTANGLE", idBannerBackup);
                        AdRequest adRequestProps = new AdRequest.Builder().build();
                        layAds.addView(adView);
                        adView.loadAd(adRequestProps);
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
        adViewMax.stopAutoRefresh();
        final int widthPx = AppLovinSdkUtils.dpToPx(activity, 300);
        final int heightPx = AppLovinSdkUtils.dpToPx(activity, 250);
        adViewMax.setLayoutParams(new ConstraintLayout.LayoutParams(widthPx, heightPx));
        layAds.addView(adViewMax);
        adViewMax.loadAd();
    }

    public static void MediumBannerMopub(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup) {


    }

    public static void MediumBannerStartApp(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup) {
        startAppMrec = new Mrec(activity, new BannerListener() {
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
                    case "ALIEN-V":
                        if (jamboxBanner != null) {
                            jamboxBanner.destroy();
                        }
                        break;
                    case "APPLOVIN-M":
                        if (adViewMax != null) {
                            adViewMax.destroy();
                        }
                        break;

                    case "UNITY":
                        if (unityBanner != null) {
                            unityBanner.destroy();
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
                        adViewDiscovery = new AppLovinAdView(AppLovinAdSize.MREC, activity);
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
                        ;
                        break;
                    case "APPLOVIN-M":
                        adViewMax = new MaxAdView(idBannerBackup, MaxAdFormat.MREC, activity);
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
                        adViewMax.stopAutoRefresh();
                        final int widthPx = AppLovinSdkUtils.dpToPx(activity, 300);
                        final int heightPx = AppLovinSdkUtils.dpToPx(activity, 250);
                        adViewMax.setLayoutParams(new ConstraintLayout.LayoutParams(widthPx, heightPx));
                        layAds.addView(adViewMax);
                        adViewMax.loadAd();
                        break;

                    case "UNITY":
                        unityBanner = new BannerView(activity, idBannerBackup, new UnityBannerSize(300, 250));
                        unityBanner.load();
                        layAds.addView(unityBanner);
                        break;
                    case "ADMOB":

                        AdRequest request = new AdRequest.Builder()
                                .build();
                        adViewAdmob = new AdView(activity);
                        adViewAdmob.setAdUnitId(idBannerBackup);
                        layAds.addView(adViewAdmob);
                        adViewAdmob.setAdSize(AdSize.MEDIUM_RECTANGLE);
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
                        bannerGoogleAds.setAdSize(AdSize.MEDIUM_RECTANGLE);
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
                                com.facebook.ads.AdSize.RECTANGLE_HEIGHT_250);
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
                        jamboxBanner = new MaxAdView(idBannerBackup, MaxAdFormat.MREC, activity);
                        MaxAdViewAdListener listenerJambox = new MaxAdViewAdListener() {
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
                        jamboxBanner.setListener(listenerJambox);
                        jamboxBanner.stopAutoRefresh();
                        final int widthPxJambox = AppLovinSdkUtils.dpToPx(activity, 300);
                        final int heightPxJambox = AppLovinSdkUtils.dpToPx(activity, 250);
                        jamboxBanner.setLayoutParams(new ConstraintLayout.LayoutParams(widthPxJambox, heightPxJambox));
                        layAds.addView(jamboxBanner);
                        jamboxBanner.loadAd();
                        break;
                    case "ALIEN-M":
                        PropsAdsManagement propsAds = new PropsAdsManagement(activity);
                        AdView adView = propsAds.createBannerAdview("MEDIUM_RECTANGLE", idBannerBackup);
                        AdRequest adRequestProps = new AdRequest.Builder().build();
                        layAds.addView(adView);
                        adView.loadAd(adRequestProps);
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
        layAds.addView(startAppMrec, bannerParameters);
    }

    public static void MediumBannerIron(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup) {
    }

    public static void MediumBannerUnity(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup) {
        unityBanner = new BannerView(activity, idBanner, new UnityBannerSize(300, 250));
        unityBanner.load();
        layAds.addView(unityBanner);
        unityBanner.setListener(new BannerView.IListener() {
            @Override
            public void onBannerLoaded(BannerView bannerAdView) {
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        if (adViewMax != null) {
                            adViewMax.destroy();
                        }
                        break;
                    case "ALIEN-V":
                        if (jamboxBanner != null) {
                            jamboxBanner.destroy();
                        }
                        break;
                    case "UNITY":
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

                    case "ADMOB":
                        if (adViewAdmob != null) {
                            adViewAdmob.destroy();
                        }
                        break;
                }
            }

            @Override
            public void onBannerShown(BannerView bannerAdView) {

            }

            @Override
            public void onBannerClick(BannerView bannerAdView) {

            }

            @Override
            public void onBannerFailedToLoad(BannerView bannerAdView, BannerErrorInfo errorInfo) {
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        adViewMax = new MaxAdView(idBannerBackup, MaxAdFormat.MREC, activity);
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
                        adViewMax.stopAutoRefresh();
                        final int widthPx = AppLovinSdkUtils.dpToPx(activity, 300);
                        final int heightPx = AppLovinSdkUtils.dpToPx(activity, 250);
                        adViewMax.setLayoutParams(new ConstraintLayout.LayoutParams(widthPx, heightPx));
                        layAds.addView(adViewMax);
                        adViewMax.loadAd();
                        break;

                    case "UNITY":
                        unityBanner = new BannerView(activity, idBannerBackup, new UnityBannerSize(300, 250));
                        unityBanner.load();
                        layAds.addView(unityBanner);
                        break;
                    case "STARTAPP":
                        startAppMrec = new Mrec(activity, new BannerListener() {
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
                        layAds.addView(startAppMrec, bannerParameters);
                        break;
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", idBannerBackup);
                        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);
                        adViewDiscovery = new AppLovinAdView(AppLovinAdSize.MREC, activity);
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
                        ;
                        break;
                    case "FACEBOOK":
                        adViewFAN = new com.facebook.ads.AdView(activity, idBannerBackup,
                                com.facebook.ads.AdSize.RECTANGLE_HEIGHT_250);
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
                        jamboxBanner = new MaxAdView(idBannerBackup, MaxAdFormat.MREC, activity);
                        MaxAdViewAdListener listenerJambox = new MaxAdViewAdListener() {
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
                        jamboxBanner.setListener(listenerJambox);
                        jamboxBanner.stopAutoRefresh();
                        final int widthPxJambox = AppLovinSdkUtils.dpToPx(activity, 300);
                        final int heightPxJambox = AppLovinSdkUtils.dpToPx(activity, 250);
                        jamboxBanner.setLayoutParams(new ConstraintLayout.LayoutParams(widthPxJambox, heightPxJambox));
                        layAds.addView(jamboxBanner);
                        jamboxBanner.loadAd();
                        break;
                    case "ALIEN-M":
                        PropsAdsManagement propsAds = new PropsAdsManagement(activity);
                        AdView adView = propsAds.createBannerAdview("MEDIUM_RECTANGLE", idBannerBackup);
                        AdRequest adRequestProps = new AdRequest.Builder().build();
                        layAds.addView(adView);
                        adView.loadAd(adRequestProps);
                        break;
                    case "ADMOB":
                        AdRequest request = new AdRequest.Builder()
                                .build();
                        adViewAdmob = new AdView(activity);
                        adViewAdmob.setAdUnitId(idBannerBackup);
                        layAds.addView(adViewAdmob);
                        adViewAdmob.setAdSize(AdSize.MEDIUM_RECTANGLE);
                        adViewAdmob.loadAd(request);
                        adViewAdmob.setAdListener(new AdListener() {
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
                }
            }

            @Override
            public void onBannerLeftApplication(BannerView bannerView) {

            }
        });
    }

    public static void MediumBannerAlienMediation(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup) {

        PropsAdsManagement propsAds = new PropsAdsManagement(activity);
        AdView adView = propsAds.createBannerAdview("MEDIUM_RECTANGLE", idBanner);
        AdRequest adRequest = new AdRequest.Builder().build();
        layAds.addView(adView);
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                switch (selectAdsBackup) {
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", idBannerBackup);
                        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);
                        adViewDiscovery = new AppLovinAdView(AppLovinAdSize.MREC, activity);
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
                        ;
                        break;
                    case "APPLOVIN-M":
                        adViewMax = new MaxAdView(idBannerBackup, MaxAdFormat.MREC, activity);
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
                        adViewMax.stopAutoRefresh();
                        final int widthPx = AppLovinSdkUtils.dpToPx(activity, 300);
                        final int heightPx = AppLovinSdkUtils.dpToPx(activity, 250);
                        adViewMax.setLayoutParams(new ConstraintLayout.LayoutParams(widthPx, heightPx));
                        layAds.addView(adViewMax);
                        adViewMax.loadAd();
                        break;
                    case "STARTAPP":
                        startAppMrec = new Mrec(activity, new BannerListener() {
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
                        layAds.addView(startAppMrec, bannerParameters);
                        break;

                    case "UNITY":
                        unityBanner = new BannerView(activity, idBannerBackup, new UnityBannerSize(300, 250));
                        unityBanner.load();
                        layAds.addView(unityBanner);
                        break;
                    case "ADMOB":

                        AdRequest request = new AdRequest.Builder()
                                .build();
                        adViewAdmob = new AdView(activity);
                        adViewAdmob.setAdUnitId(idBannerBackup);
                        layAds.addView(adViewAdmob);
                        adViewAdmob.setAdSize(AdSize.MEDIUM_RECTANGLE);
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
                        bannerGoogleAds.setAdSize(AdSize.MEDIUM_RECTANGLE);
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
                                com.facebook.ads.AdSize.RECTANGLE_HEIGHT_250);
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
                        jamboxBanner = new MaxAdView(idBannerBackup, MaxAdFormat.MREC, activity);
                        MaxAdViewAdListener listenerJambox = new MaxAdViewAdListener() {
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
                        jamboxBanner.setListener(listenerJambox);
                        jamboxBanner.stopAutoRefresh();
                        final int widthPxJambox = AppLovinSdkUtils.dpToPx(activity, 300);
                        final int heightPxJambox = AppLovinSdkUtils.dpToPx(activity, 250);
                        jamboxBanner.setLayoutParams(new ConstraintLayout.LayoutParams(widthPxJambox, heightPxJambox));
                        layAds.addView(jamboxBanner);
                        jamboxBanner.loadAd();
                        break;
                }
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
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
                    case "ALIEN-V":
                        if (jamboxBanner != null) {
                            jamboxBanner.destroy();
                        }
                        break;
                    case "UNITY":
                        if (unityBanner != null) {
                            unityBanner.destroy();
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

                }
            }
        });

    }

    public static void MediumBannerWotrise(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup) {

    }

    public static void MediumBannerAlienView(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup) {

        jamboxBanner = new MaxAdView(idBanner, MaxAdFormat.MREC, activity);
        MaxAdViewAdListener listenerJambox = new MaxAdViewAdListener() {
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
                    case "APPLOVIN-M":
                        if (adViewMax != null) {
                            adViewMax.destroy();
                        }
                        break;
                    case "STARTAPP":
                        startAppBanner.hideBanner();
                        break;
                    case "UNITY":
                        if (unityBanner != null) {
                            unityBanner.destroy();
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
                        adViewDiscovery = new AppLovinAdView(AppLovinAdSize.MREC, activity);
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
                        ;
                        break;
                    case "APPLOVIN-M":
                        adViewMax = new MaxAdView(idBannerBackup, MaxAdFormat.MREC, activity);
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
                        adViewMax.stopAutoRefresh();
                        final int widthPx = AppLovinSdkUtils.dpToPx(activity, 300);
                        final int heightPx = AppLovinSdkUtils.dpToPx(activity, 250);
                        adViewMax.setLayoutParams(new ConstraintLayout.LayoutParams(widthPx, heightPx));
                        layAds.addView(adViewMax);
                        adViewMax.loadAd();
                        break;
                    case "STARTAPP":
                        startAppMrec = new Mrec(activity, new BannerListener() {
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
                        layAds.addView(startAppMrec, bannerParameters);
                        break;

                    case "UNITY":
                        unityBanner = new BannerView(activity, idBannerBackup, new UnityBannerSize(300, 250));
                        unityBanner.load();
                        layAds.addView(unityBanner);
                        break;
                    case "ADMOB":

                        AdRequest request = new AdRequest.Builder()
                                .build();
                        adViewAdmob = new AdView(activity);
                        adViewAdmob.setAdUnitId(idBannerBackup);
                        layAds.addView(adViewAdmob);
                        adViewAdmob.setAdSize(AdSize.MEDIUM_RECTANGLE);
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
                        bannerGoogleAds.setAdSize(AdSize.MEDIUM_RECTANGLE);
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
                                com.facebook.ads.AdSize.RECTANGLE_HEIGHT_250);
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
                    case "ALIEN-M":
                        PropsAdsManagement propsAds = new PropsAdsManagement(activity);
                        AdView adView = propsAds.createBannerAdview("MEDIUM_RECTANGLE", idBannerBackup);
                        AdRequest adRequestProps = new AdRequest.Builder().build();
                        layAds.addView(adView);
                        adView.loadAd(adRequestProps);
                        break;
                }
            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                if (onLoadBannerApplovinMax != null) {
                    onLoadBannerApplovinMax.onAdDisplayFailed();
                }
                layAds.setVisibility(View.GONE);
            }
        };
        jamboxBanner.setListener(listenerJambox);
        jamboxBanner.stopAutoRefresh();
        final int widthPxJambox = AppLovinSdkUtils.dpToPx(activity, 300);
        final int heightPxJambox = AppLovinSdkUtils.dpToPx(activity, 250);
        jamboxBanner.setLayoutParams(new ConstraintLayout.LayoutParams(widthPxJambox, heightPxJambox));
        layAds.addView(jamboxBanner);
        jamboxBanner.loadAd();




    }


}
