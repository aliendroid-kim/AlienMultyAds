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
import androidx.constraintlayout.widget.ConstraintLayout;

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
import com.google.ads.mediation.facebook.FacebookAdapter;
import com.google.ads.mediation.facebook.FacebookExtras;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.ironsource.mediationsdk.ISBannerSize;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubView;
import com.startapp.sdk.ads.banner.Banner;
import com.startapp.sdk.ads.banner.BannerListener;
import com.startapp.sdk.ads.banner.Mrec;

public class AliendroidBanner {
    public static void SmallBanner(Activity activity, RelativeLayout layAds, String selectAds, String idBanner, String Hpk1,
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
                AdView adView;
                adView = new AdView(activity);
                adView.setAdUnitId(idBanner);
                layAds.addView(adView);
                AdSize adSize = getAdSize(activity);
                adView.setAdSize(adSize);
                adView.loadAd(request);
                break;

            case "APPLOVIN-M":
                MaxAdView adView2;
                adView2 = new MaxAdView(idBanner, activity);
                final boolean isTablet = AppLovinSdkUtils.isTablet(activity);
                final int heightPx = AppLovinSdkUtils.dpToPx(activity, isTablet ? 90 : 50);
                adView2.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightPx));
                layAds.addView(adView2);
                adView2.loadAd();
                break;

            case "MOPUB":
                MoPubView moPubView;
                moPubView = new MoPubView(activity);
                moPubView.setAdUnitId(idBanner);
                moPubView.setAutorefreshEnabled(false);
                layAds.addView(moPubView);
                moPubView.loadAd(MoPubView.MoPubAdSize.HEIGHT_50);
                break;
            case "IRON":
                IronSourceBannerLayout banner = IronSource.createBanner(activity, ISBannerSize.BANNER);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT);
                layAds.addView(banner, 0, layoutParams);
                IronSource.loadBanner(banner, idBanner);
                break;
            case "STARTAPP":
                Banner startAppBanner = new Banner(activity);
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
                bannerExtras.putString("zone_id", idBanner);
                builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);

                boolean isTablet3 = AppLovinSdkUtils.isTablet(activity);
                AppLovinAdSize adSize3 = isTablet3 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
                AppLovinAdView adView3 = new AppLovinAdView(adSize3, activity);
                layAds.addView(adView3);
                adView3.loadNextAd();

                break;
        }

    }

    public static void SmallBannerAdmob(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup, String Hpk1,
                                        String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        Bundle extras = new FacebookExtras()
                .setNativeBanner(true)
                .build();
        AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                .build();
        AdView adViewAdmob;
        adViewAdmob = new AdView(activity);
        adViewAdmob.setAdUnitId(idBanner);
        layAds.addView(adViewAdmob);
        AdSize adSize = getAdSize(activity);
        adViewAdmob.setAdSize(adSize);
        adViewAdmob.loadAd(request);
        adViewAdmob.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        MaxAdView adView;
                        adView = new MaxAdView(idBannerBackup, activity);
                        final boolean isTablet = AppLovinSdkUtils.isTablet(activity);
                        final int heightPx = AppLovinSdkUtils.dpToPx(activity, isTablet ? 90 : 50);
                        adView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightPx));
                        layAds.addView(adView);
                        adView.loadAd();
                        break;
                    case "MOPUB":
                        MoPubView moPubView;
                        moPubView = new MoPubView(activity);
                        moPubView.setAdUnitId(idBannerBackup);
                        moPubView.setAutorefreshEnabled(false);
                        layAds.addView(moPubView);
                        moPubView.loadAd(MoPubView.MoPubAdSize.HEIGHT_50);
                        break;
                    case "IRON":
                        IronSourceBannerLayout banner = IronSource.createBanner(activity, ISBannerSize.BANNER);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        layAds.addView(banner, 0, layoutParams);
                        IronSource.loadBanner(banner, idBannerBackup);
                        break;
                    case "STARTAPP":
                        Banner startAppBanner = new Banner(activity);
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
                        AppLovinAdView adView2 = new AppLovinAdView(adSize, activity);
                        layAds.addView(adView2);
                        adView2.loadNextAd();

                        break;
                }
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });


    }

    public static void SmallBannerApplovinDis(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup) {

        AdRequest.Builder builder = new AdRequest.Builder();
        Bundle bannerExtras = new Bundle();
        bannerExtras.putString("zone_id", idBanner);
        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);
        boolean isTablet2 = AppLovinSdkUtils.isTablet(activity);
        AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
        AppLovinAdView adViewApplovin = new AppLovinAdView(adSize, activity);
        AppLovinAdLoadListener loadListener = new AppLovinAdLoadListener() {
            @Override
            public void adReceived(AppLovinAd ad) {

            }

            @Override
            public void failedToReceiveAd(int errorCode) {
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        MaxAdView adView;
                        adView = new MaxAdView(idBannerBackup, activity);
                        final boolean isTablet = AppLovinSdkUtils.isTablet(activity);
                        final int heightPx = AppLovinSdkUtils.dpToPx(activity, isTablet ? 90 : 50);
                        adView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightPx));
                        layAds.addView(adView);
                        adView.loadAd();
                        break;
                    case "MOPUB":
                        MoPubView moPubView;
                        moPubView = new MoPubView(activity);
                        moPubView.setAutorefreshEnabled(false);
                        moPubView.setAdUnitId(idBannerBackup);
                        layAds.addView(moPubView);
                        moPubView.loadAd(MoPubView.MoPubAdSize.HEIGHT_50);
                        break;
                    case "IRON":
                        IronSourceBannerLayout banner = IronSource.createBanner(activity, ISBannerSize.BANNER);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        layAds.addView(banner, 0, layoutParams);
                        IronSource.loadBanner(banner, idBannerBackup);
                        break;
                    case "STARTAPP":
                        Banner startAppBanner = new Banner(activity);
                        RelativeLayout.LayoutParams bannerParameters =
                                new RelativeLayout.LayoutParams(
                                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                        bannerParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
                        layAds.addView(startAppBanner, bannerParameters);
                        break;
                    case "ADMOB":
                        Bundle extras = new FacebookExtras()
                                .setNativeBanner(true)
                                .build();
                        AdRequest request = new AdRequest.Builder()
                                .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                                .build();
                        AdView adViewAdmob;
                        adViewAdmob = new AdView(activity);
                        adViewAdmob.setAdUnitId(idBannerBackup);
                        layAds.addView(adViewAdmob);
                        AdSize adSizeAdmob = getAdSize(activity);
                        adViewAdmob.setAdSize(adSizeAdmob);
                        adViewAdmob.loadAd(request);
                        break;
                }
            }
        };
        adViewApplovin.setAdLoadListener(loadListener);
        layAds.addView(adViewApplovin);
        adViewApplovin.loadNextAd();

    }

    public static void SmallBannerApplovinMax(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup) {

        MaxAdView adViewMax;
        adViewMax = new MaxAdView(idBanner, activity);
        adViewMax.stopAutoRefresh();
        MaxAdViewAdListener listener = new MaxAdViewAdListener() {
            @Override
            public void onAdExpanded(MaxAd ad) {

            }

            @Override
            public void onAdCollapsed(MaxAd ad) {

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
                switch (selectAdsBackup) {
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", idBannerBackup);
                        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);
                        boolean isTablet2 = AppLovinSdkUtils.isTablet(activity);
                        AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
                        AppLovinAdView adViewApplovin = new AppLovinAdView(adSize, activity);
                        layAds.addView(adViewApplovin);
                        adViewApplovin.loadNextAd();
                        break;
                    case "MOPUB":
                        MoPubView moPubView;
                        moPubView = new MoPubView(activity);
                        moPubView.setAdUnitId(idBannerBackup);
                        moPubView.setAutorefreshEnabled(false);
                        layAds.addView(moPubView);
                        moPubView.loadAd(MoPubView.MoPubAdSize.HEIGHT_50);
                        break;
                    case "IRON":
                        IronSourceBannerLayout banner = IronSource.createBanner(activity, ISBannerSize.BANNER);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        layAds.addView(banner, 0, layoutParams);
                        IronSource.loadBanner(banner, idBannerBackup);
                        break;
                    case "STARTAPP":
                        Banner startAppBanner = new Banner(activity);
                        RelativeLayout.LayoutParams bannerParameters =
                                new RelativeLayout.LayoutParams(
                                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                        bannerParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
                        layAds.addView(startAppBanner, bannerParameters);
                        break;
                    case "ADMOB":
                        Bundle extras = new FacebookExtras()
                                .setNativeBanner(true)
                                .build();
                        AdRequest request = new AdRequest.Builder()
                                .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                                .build();
                        AdView adViewAdmob;
                        adViewAdmob = new AdView(activity);
                        adViewAdmob.setAdUnitId(idBannerBackup);
                        layAds.addView(adViewAdmob);
                        AdSize adSizeAdmob = getAdSize(activity);
                        adViewAdmob.setAdSize(adSizeAdmob);
                        adViewAdmob.loadAd(request);
                        break;
                }

            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {

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
        MoPubView moPubView;
        moPubView = new MoPubView(activity);
        moPubView.setAdUnitId(idBanner);
        moPubView.setAutorefreshEnabled(false);
        MoPubView.BannerAdListener listener = new MoPubView.BannerAdListener() {
            @Override
            public void onBannerLoaded(@NonNull MoPubView moPubView) {

            }

            @Override
            public void onBannerFailed(MoPubView moPubView, MoPubErrorCode moPubErrorCode) {
                switch (selectAdsBackup) {
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", idBannerBackup);
                        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);
                        boolean isTablet2 = AppLovinSdkUtils.isTablet(activity);
                        AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
                        AppLovinAdView adViewApplovin = new AppLovinAdView(adSize, activity);
                        layAds.addView(adViewApplovin);
                        adViewApplovin.loadNextAd();
                        break;
                    case "APPLOVIN-M":
                        MaxAdView adViewMax;
                        adViewMax = new MaxAdView(idBannerBackup, activity);
                        adViewMax.stopAutoRefresh();

                        final boolean isTablet = AppLovinSdkUtils.isTablet(activity);
                        final int heightPx = AppLovinSdkUtils.dpToPx(activity, isTablet ? 90 : 50);
                        adViewMax.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightPx));
                        layAds.addView(adViewMax);
                        adViewMax.loadAd();
                        break;
                    case "IRON":
                        IronSourceBannerLayout banner = IronSource.createBanner(activity, ISBannerSize.BANNER);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        layAds.addView(banner, 0, layoutParams);
                        IronSource.loadBanner(banner, idBannerBackup);
                        break;
                    case "STARTAPP":
                        Banner startAppBanner = new Banner(activity);
                        RelativeLayout.LayoutParams bannerParameters =
                                new RelativeLayout.LayoutParams(
                                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                        bannerParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
                        layAds.addView(startAppBanner, bannerParameters);
                        break;
                    case "ADMOB":
                        Bundle extras = new FacebookExtras()
                                .setNativeBanner(true)
                                .build();
                        AdRequest request = new AdRequest.Builder()
                                .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                                .build();
                        AdView adViewAdmob;
                        adViewAdmob = new AdView(activity);
                        adViewAdmob.setAdUnitId(idBannerBackup);
                        layAds.addView(adViewAdmob);
                        AdSize adSizeAdmob = getAdSize(activity);
                        adViewAdmob.setAdSize(adSizeAdmob);
                        adViewAdmob.loadAd(request);
                        break;
                }
            }

            @Override
            public void onBannerClicked(MoPubView moPubView) {

            }

            @Override
            public void onBannerExpanded(MoPubView moPubView) {

            }

            @Override
            public void onBannerCollapsed(MoPubView moPubView) {

            }
        };
        moPubView.setBannerAdListener(listener);
        layAds.addView(moPubView);
        moPubView.loadAd(MoPubView.MoPubAdSize.HEIGHT_50);

    }

    public static void SmallBannerStartApp(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup) {
        Banner startAppBanner = new Banner(activity, new BannerListener() {
            @Override
            public void onReceiveAd(View view) {

            }

            @Override
            public void onFailedToReceiveAd(View view) {
                switch (selectAdsBackup) {
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", idBannerBackup);
                        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);
                        boolean isTablet2 = AppLovinSdkUtils.isTablet(activity);
                        AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
                        AppLovinAdView adViewApplovin = new AppLovinAdView(adSize, activity);
                        layAds.addView(adViewApplovin);
                        adViewApplovin.loadNextAd();
                        break;
                    case "APPLOVIN-M":
                        MaxAdView adViewMax;
                        adViewMax = new MaxAdView(idBannerBackup, activity);
                        adViewMax.stopAutoRefresh();

                        final boolean isTablet = AppLovinSdkUtils.isTablet(activity);
                        final int heightPx = AppLovinSdkUtils.dpToPx(activity, isTablet ? 90 : 50);
                        adViewMax.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightPx));
                        layAds.addView(adViewMax);
                        adViewMax.loadAd();
                        break;
                    case "IRON":
                        IronSourceBannerLayout banner = IronSource.createBanner(activity, ISBannerSize.BANNER);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        layAds.addView(banner, 0, layoutParams);
                        IronSource.loadBanner(banner, idBannerBackup);
                        break;
                    case "MOPUB":
                        MoPubView moPubView;
                        moPubView = new MoPubView(activity);
                        moPubView.setAdUnitId(idBanner);
                        moPubView.setAutorefreshEnabled(false);
                        layAds.addView(moPubView);
                        moPubView.loadAd(MoPubView.MoPubAdSize.HEIGHT_50);
                        break;
                    case "ADMOB":
                        Bundle extras = new FacebookExtras()
                                .setNativeBanner(true)
                                .build();
                        AdRequest request = new AdRequest.Builder()
                                .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                                .build();
                        AdView adViewAdmob;
                        adViewAdmob = new AdView(activity);
                        adViewAdmob.setAdUnitId(idBannerBackup);
                        layAds.addView(adViewAdmob);
                        AdSize adSizeAdmob = getAdSize(activity);
                        adViewAdmob.setAdSize(adSizeAdmob);
                        adViewAdmob.loadAd(request);
                        break;
                }
            }

            @Override
            public void onImpression(View view) {

            }

            @Override
            public void onClick(View view) {

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
        IronSourceBannerLayout banner = IronSource.createBanner(activity, ISBannerSize.BANNER);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        layAds.addView(banner, 0, layoutParams);
        com.ironsource.mediationsdk.sdk.BannerListener listener = new com.ironsource.mediationsdk.sdk.BannerListener() {
            @Override
            public void onBannerAdLoaded() {

            }

            @Override
            public void onBannerAdLoadFailed(IronSourceError ironSourceError) {
                switch (selectAdsBackup) {
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", idBannerBackup);
                        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);
                        boolean isTablet2 = AppLovinSdkUtils.isTablet(activity);
                        AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
                        AppLovinAdView adViewApplovin = new AppLovinAdView(adSize, activity);
                        layAds.addView(adViewApplovin);
                        adViewApplovin.loadNextAd();
                        break;
                    case "APPLOVIN-M":
                        MaxAdView adViewMax;
                        adViewMax = new MaxAdView(idBannerBackup, activity);
                        adViewMax.stopAutoRefresh();

                        final boolean isTablet = AppLovinSdkUtils.isTablet(activity);
                        final int heightPx = AppLovinSdkUtils.dpToPx(activity, isTablet ? 90 : 50);
                        adViewMax.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightPx));
                        layAds.addView(adViewMax);
                        adViewMax.loadAd();
                        break;
                    case "STARTAPP":
                        Banner startAppBanner = new Banner(activity);
                        RelativeLayout.LayoutParams bannerParameters =
                                new RelativeLayout.LayoutParams(
                                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                        bannerParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
                        layAds.addView(startAppBanner, bannerParameters);
                        break;
                    case "MOPUB":
                        MoPubView moPubView;
                        moPubView = new MoPubView(activity);
                        moPubView.setAdUnitId(idBanner);
                        moPubView.setAutorefreshEnabled(false);
                        layAds.addView(moPubView);
                        moPubView.loadAd(MoPubView.MoPubAdSize.HEIGHT_50);
                        break;
                    case "ADMOB":
                        Bundle extras = new FacebookExtras()
                                .setNativeBanner(true)
                                .build();
                        AdRequest request = new AdRequest.Builder()
                                .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                                .build();
                        AdView adViewAdmob;
                        adViewAdmob = new AdView(activity);
                        adViewAdmob.setAdUnitId(idBannerBackup);
                        layAds.addView(adViewAdmob);
                        AdSize adSizeAdmob = getAdSize(activity);
                        adViewAdmob.setAdSize(adSizeAdmob);
                        adViewAdmob.loadAd(request);
                        break;
                }
            }

            @Override
            public void onBannerAdClicked() {

            }

            @Override
            public void onBannerAdScreenPresented() {

            }

            @Override
            public void onBannerAdScreenDismissed() {

            }

            @Override
            public void onBannerAdLeftApplication() {

            }
        };
        banner.setBannerListener(listener);
        IronSource.loadBanner(banner, idBanner);
    }


    public static void MediumBanner(Activity activity, RelativeLayout layAds, String selectAds, String idBanner, String Hpk1,
                                    String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        switch (selectAds) {
            case "ADMOB": {
                Bundle extras = new FacebookExtras()
                        .setNativeBanner(true)
                        .build();
                AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                        .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                        .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                        .build();
                AdView adView;
                adView = new AdView(activity);
                adView.setAdUnitId(idBanner);
                layAds.addView(adView);
                adView.setAdSize(AdSize.MEDIUM_RECTANGLE);
                adView.loadAd(request);
                break;
            }
            case "APPLOVIN-M": {
                MaxAdView adView;
                adView = new MaxAdView(idBanner, MaxAdFormat.MREC, activity);
                final int widthPx = AppLovinSdkUtils.dpToPx(activity, 300);
                final int heightPx = AppLovinSdkUtils.dpToPx(activity, 250);
                adView.setLayoutParams(new ConstraintLayout.LayoutParams(widthPx, heightPx));
                layAds.addView(adView);
                adView.loadAd();
                break;
            }
            case "MOPUB":
                MoPubView moPubView;
                moPubView = new MoPubView(activity);
                moPubView.setAdUnitId(idBanner);
                layAds.addView(moPubView);
                moPubView.loadAd(MoPubView.MoPubAdSize.HEIGHT_50);
                break;
            case "IRON":
                IronSourceBannerLayout banner = IronSource.createBanner(activity, ISBannerSize.RECTANGLE);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT);
                layAds.addView(banner, 0, layoutParams);
                IronSource.loadBanner(banner, idBanner);
                break;
            case "STARTAPP":
                Mrec startAppBanner = new Mrec(activity);
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
                bannerExtras.putString("zone_id", idBanner);
                builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);

                boolean isTablet2 = AppLovinSdkUtils.isTablet(activity);
                AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
                AppLovinAdView adView2 = new AppLovinAdView(adSize, activity);
                layAds.addView(adView2);
                adView2.loadNextAd();
                break;
        }
    }

    public static void MediumBannerAdmob(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup, String Hpk1,
                                         String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        Bundle extras = new FacebookExtras()
                .setNativeBanner(true)
                .build();
        AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                .build();
        AdView adView;
        adView = new AdView(activity);
        adView.setAdUnitId(idBanner);
        layAds.addView(adView);
        adView.setAdSize(AdSize.MEDIUM_RECTANGLE);
        adView.loadAd(request);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                switch (selectAdsBackup) {
                    case "APPLOVIN-M": {
                        MaxAdView adView;
                        //adView = new MaxAdView(APPLOVIN_BANNER, (Activity) MainActivity.this);
                        adView = new MaxAdView(idBannerBackup, MaxAdFormat.MREC, activity);
                        //AppLovinAdView adView = new AppLovinAdView(AppLovinAdSize.BANNER, context);
                        final int widthPx = AppLovinSdkUtils.dpToPx(activity, 300);
                        final int heightPx = AppLovinSdkUtils.dpToPx(activity, 250);
                        adView.setLayoutParams(new ConstraintLayout.LayoutParams(widthPx, heightPx));
                        layAds.addView(adView);
                        adView.loadAd();
                        break;
                    }
                    case "MOPUB":
                        MoPubView moPubView;
                        moPubView = new MoPubView(activity);
                        moPubView.setAdUnitId(idBannerBackup);
                        layAds.addView(moPubView);
                        moPubView.loadAd(MoPubView.MoPubAdSize.HEIGHT_50);
                        break;
                    case "IRON":
                        IronSourceBannerLayout banner = IronSource.createBanner(activity, ISBannerSize.RECTANGLE);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        layAds.addView(banner, 0, layoutParams);
                        IronSource.loadBanner(banner, idBannerBackup);
                        break;
                    case "STARTAPP":
                        Mrec startAppBanner = new Mrec(activity);
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
                        AppLovinAdView adView2 = new AppLovinAdView(adSize, activity);
                        layAds.addView(adView2);
                        adView2.loadNextAd();
                        break;
                }
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

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
