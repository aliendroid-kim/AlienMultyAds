package com.aliendroid.alienads;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.aliendroid.alienads.config.AppLovinCustomEventBanner;
import com.aliendroid.alienads.interfaces.natives.OnLoadMediumNativesAdmob;
import com.aliendroid.alienads.interfaces.natives.OnLoadMediumNativesAlien;
import com.aliendroid.alienads.interfaces.natives.OnLoadMediumNativesApplovinMax;
import com.aliendroid.alienads.interfaces.natives.OnLoadMediumNativesFacebook;
import com.aliendroid.alienads.interfaces.natives.OnLoadMediumNativesStartApp;
import com.aliendroid.alienads.interfaces.natives.OnLoadSmallNativesAdmob;
import com.aliendroid.alienads.interfaces.natives.OnLoadSmallNativesAlien;
import com.aliendroid.alienads.interfaces.natives.OnLoadSmallNativesApplovinMax;
import com.aliendroid.alienads.interfaces.natives.OnLoadSmallNativesFacebook;
import com.aliendroid.alienads.interfaces.natives.OnLoadSmallNativesStartApp;
import com.aliendroid.sdkads.interfaces.OnLoadNative;
import com.aliendroid.sdkads.type.mediation.AlienMediationAds;
import com.aliendroid.sdkads.type.view.AlienViewAds;
import com.applovin.adview.AppLovinAdView;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdRevenueListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.applovin.mediation.nativeAds.MaxNativeAdViewBinder;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdkUtils;
import com.bumptech.glide.Glide;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeBannerAd;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.ironsource.mediationsdk.ISBannerSize;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.IronSourceBannerLayout;

import com.startapp.sdk.ads.nativead.NativeAdDetails;
import com.startapp.sdk.ads.nativead.StartAppNativeAd;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AliendroidNative {
    public static AppLovinAdView adViewDiscovery;
    public static IronSourceBannerLayout adViewIron;
    private static NativeAd nativeAd;
    private static NativeAd nativeAd2;
    private static MaxNativeAdLoader nativeAdLoader;
    private static MaxNativeAdView nativeAdView;
    private static MaxAd nativeAdMax;

    private static NativeAdLayout nativeAdLayout;
    private static LinearLayout adView;
    private static NativeBannerAd nativeBannerAd;
    private static NativeBannerAd nativeBannerAd2;
    public static com.facebook.ads.NativeAd nativeAdfan;
    public static com.facebook.ads.NativeAd nativeAdfan2;
    public static StartAppNativeAd startAppNativeAd;

    public static OnLoadSmallNativesAdmob onLoadSmallNativesAdmob;
    public static OnLoadSmallNativesApplovinMax onLoadSmallNativesApplovinMax;
    public static OnLoadSmallNativesFacebook onLoadSmallNativesFacebook;
    public static OnLoadSmallNativesStartApp onLoadSmallNativesStartApp;
    public static OnLoadSmallNativesAlien onLoadSmallNativesAlien;

    public static OnLoadMediumNativesAdmob onLoadMediumNativesAdmob;
    public static OnLoadMediumNativesApplovinMax onLoadMediumNativesApplovinMax;
    public static OnLoadMediumNativesFacebook onLoadMediumNativesFacebook;
    public static OnLoadMediumNativesStartApp onLoadMediumNativesStartApp;
    public static OnLoadMediumNativesAlien onLoadMediumNativesAlien;

    public static void SmallNativeAdmob(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup, String Hpk1,
                                        String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        AdLoader.Builder builder = new AdLoader.Builder(activity, nativeId);
        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
            @Override
            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                if (onLoadSmallNativesAdmob !=null){
                    onLoadSmallNativesAdmob.onNativeAdLoaded();
                }
                if (nativeAd != null) {
                    nativeAd.destroy();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        if (nativeAdMax != null) {
                            nativeAdLoader.destroy(nativeAdMax);
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

                        break;
                    case "APPLOVIN-D":
                        if (adViewDiscovery != null) {
                            adViewDiscovery.destroy();
                        }
                        break;
                    case "FACEBOOK":
                        if (nativeBannerAd != null) {
                            nativeBannerAd.destroy();
                        }
                        break;
                }
                nativeAd = nativeAds;
                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                        .inflate(R.layout.admob_small_native, null);
                populateNativeAdView(nativeAds, adView);
                layNative.removeAllViews();
                layNative.addView(adView);
            }

        });

        VideoOptions videoOptions = new VideoOptions.Builder()
                .build();
        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                .setVideoOptions(videoOptions)
                .build();
        builder.withNativeAdOptions(adOptions);
        AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                .build();
        AdLoader adLoader =
                builder
                        .withAdListener(
                                new AdListener() {
                                    @Override
                                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                                        if (onLoadSmallNativesAdmob !=null){
                                            onLoadSmallNativesAdmob.onAdFailedToLoad("");
                                        }
                                        switch (selectAdsBackup) {
                                            case "APPLOVIN-M":
                                                MaxNativeAdViewBinder binder = new MaxNativeAdViewBinder.Builder(R.layout.max_small_native)
                                                        .setTitleTextViewId(R.id.title_text_view)
                                                        .setBodyTextViewId(R.id.body_text_view)
                                                        .setAdvertiserTextViewId(R.id.advertiser_textView)
                                                        .setIconImageViewId(R.id.icon_image_view)
                                                        .setMediaContentViewGroupId(R.id.media_view_container)
                                                        .setOptionsContentViewGroupId(R.id.ad_options_view)
                                                        .setCallToActionButtonId(R.id.cta_button)
                                                        .build();
                                                nativeAdView = new MaxNativeAdView(binder, activity);

                                                nativeAdLoader = new MaxNativeAdLoader(idNativeBackup, activity);
                                                nativeAdLoader.setRevenueListener(new MaxAdRevenueListener() {
                                                    @Override
                                                    public void onAdRevenuePaid(MaxAd ad) {

                                                    }
                                                });
                                                nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
                                                    @Override
                                                    public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {

                                                        if (nativeAd != null) {
                                                            nativeAd.destroy();
                                                        }
                                                        if (nativeAdMax != null) {
                                                            nativeAdLoader.destroy(nativeAdMax);
                                                        }

                                                        nativeAdMax = ad;
                                                        layNative.removeAllViews();
                                                        layNative.addView(nativeAdView);
                                                    }

                                                    @Override
                                                    public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {

                                                    }

                                                    @Override
                                                    public void onNativeAdClicked(final MaxAd ad) {

                                                    }
                                                });

                                                nativeAdLoader.loadAd(nativeAdView);
                                                break;
                                            case "MOPUB":
                                            case "UNITY":

                                                break;
                                            case "IRON":
                                                adViewIron = IronSource.createBanner(activity, ISBannerSize.BANNER);
                                                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                                        FrameLayout.LayoutParams.WRAP_CONTENT);
                                                layNative.addView(adViewIron, 0, layoutParams);
                                                IronSource.loadBanner(adViewIron, idNativeBackup);
                                                break;
                                            case "STARTAPP":
                                                startAppNativeAd = new StartAppNativeAd(activity);
                                                View adViewNative = (View) activity.getLayoutInflater()
                                                        .inflate(R.layout.startapp_small_native, null);
                                                AdEventListener adListener = new AdEventListener() {
                                                    @Override
                                                    public void onReceiveAd(@NonNull com.startapp.sdk.adsbase.Ad ad) {
                                                        ArrayList ads = startAppNativeAd.getNativeAds();    // get NativeAds list
                                                        Iterator iterator = ads.iterator();
                                                        while (iterator.hasNext()) {
                                                            Log.d("MyApplication", iterator.next().toString());
                                                        }
                                                        NativeAdDetails adDetails = (NativeAdDetails) ads.get(0);
                                                        if (adDetails != null) {
                                                            TextView title = adViewNative.findViewById(R.id.ad_headline);
                                                            title.setText(adDetails.getTitle());
                                                            ImageView icon = adViewNative.findViewById(R.id.ad_app_icon);
                                                            Glide.with(activity).load(adDetails.getSecondaryImageUrl()).into(icon);
                                                            TextView description = adViewNative.findViewById(R.id.ad_body);
                                                            description.setText(adDetails.getDescription());
                                                            Button open = adViewNative.findViewById(R.id.ad_call_to_action);
                                                            open.setText(adDetails.isApp() ? "Install" : "Open");
                                                            adDetails.registerViewForInteraction(adViewNative);
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailedToReceiveAd(@Nullable com.startapp.sdk.adsbase.Ad ad) {

                                                    }

                                                };
                                                startAppNativeAd.loadAd(adListener);
                                                layNative.addView(adViewNative);
                                                break;
                                            case "APPLOVIN-D":
                                                AdRequest.Builder builder = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                                                        .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5);
                                                Bundle bannerExtras = new Bundle();
                                                bannerExtras.putString("zone_id", idNativeBackup);
                                                builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);

                                                boolean isTablet2 = AppLovinSdkUtils.isTablet(activity);
                                                AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
                                                adViewDiscovery = new AppLovinAdView(adSize, activity);
                                                layNative.addView(adViewDiscovery);
                                                adViewDiscovery.loadNextAd();
                                                break;
                                            case "FACEBOOK":
                                                nativeBannerAd = new NativeBannerAd(activity, idNativeBackup);
                                                NativeAdListener nativeAdListener = new NativeAdListener() {
                                                    @Override
                                                    public void onMediaDownloaded(Ad ad) {

                                                    }

                                                    @Override
                                                    public void onError(Ad ad, AdError adError) {

                                                    }

                                                    @Override
                                                    public void onAdLoaded(Ad ad) {
                                                        if (nativeBannerAd == null || nativeBannerAd != ad) {
                                                            return;
                                                        }
                                                        inflateAd(nativeBannerAd, activity, layNative);
                                                    }

                                                    @Override
                                                    public void onAdClicked(Ad ad) {

                                                    }

                                                    @Override
                                                    public void onLoggingImpression(Ad ad) {

                                                    }
                                                };
                                                nativeBannerAd.loadAd(
                                                        nativeBannerAd.buildLoadAdConfig()
                                                                .withAdListener(nativeAdListener)
                                                                .build());
                                                break;
                                            case "ALIEN-M":
                                                AlienMediationAds.SmallNatives(activity,layNative,idNativeBackup);
                                                break;
                                            case "ALIEN-V":
                                                AlienViewAds.Banner(activity,layNative,idNativeBackup);
                                                break;
                                            case "ADMOB":
                                                AdLoader.Builder builder2 = new AdLoader.Builder(activity, idNativeBackup);
                                                builder2.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                                                    @Override
                                                    public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {

                                                        if (nativeAd2 != null) {
                                                            nativeAd2.destroy();
                                                        }

                                                        nativeAd2 = nativeAds;
                                                        NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                                                .inflate(R.layout.admob_small_native, null);
                                                        populateNativeAdView(nativeAds, adView);
                                                        layNative.removeAllViews();
                                                        layNative.addView(adView);
                                                    }

                                                });
                                                VideoOptions videoOptions = new VideoOptions.Builder()
                                                        .build();

                                                NativeAdOptions adOptions = new NativeAdOptions.Builder()
                                                        .setVideoOptions(videoOptions)
                                                        .build();

                                                builder2.withNativeAdOptions(adOptions);



                                                AdRequest request = new AdRequest.Builder()
                                                        .build();
                                                AdLoader adLoader =
                                                        builder2
                                                                .withAdListener(
                                                                        new AdListener() {
                                                                            @Override
                                                                            public void onAdFailedToLoad(LoadAdError loadAdError) {

                                                                            }
                                                                        })
                                                                .build();
                                                adLoader.loadAd(request);
                                                break;
                                        }
                                    }
                                })
                        .build();
        adLoader.loadAd(request);

    }

    public static void SmallNativeMax(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {

        MaxNativeAdViewBinder binder = new MaxNativeAdViewBinder.Builder(R.layout.max_small_native)
                .setTitleTextViewId(R.id.title_text_view)
                .setBodyTextViewId(R.id.body_text_view)
                .setAdvertiserTextViewId(R.id.advertiser_textView)
                .setIconImageViewId(R.id.icon_image_view)
                .setMediaContentViewGroupId(R.id.media_view_container)
                .setOptionsContentViewGroupId(R.id.ad_options_view)
                .setCallToActionButtonId(R.id.cta_button)
                .build();
        nativeAdView = new MaxNativeAdView(binder, activity);

        nativeAdLoader = new MaxNativeAdLoader(nativeId, activity);
        nativeAdLoader.setRevenueListener(new MaxAdRevenueListener() {
            @Override
            public void onAdRevenuePaid(MaxAd ad) {

            }
        });
        nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                if (onLoadSmallNativesApplovinMax !=null){
                    onLoadSmallNativesApplovinMax.onNativeAdLoaded();
                }
                switch (selectAdsBackup) {
                    case "ADMOB":
                        if (nativeAd != null) {
                            nativeAd.destroy();
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

                        break;
                    case "APPLOVIN-D":
                        if (adViewDiscovery != null) {
                            adViewDiscovery.destroy();
                        }
                        break;
                    case "FACEBOOK":
                        if (nativeBannerAd != null) {
                            nativeBannerAd.destroy();
                        }
                        break;
                }

                if (nativeAdMax != null) {
                    nativeAdLoader.destroy(nativeAdMax);
                }
                nativeAdMax = ad;
                layNative.removeAllViews();
                layNative.addView(nativeAdView);
            }

            @Override
            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                if (onLoadSmallNativesApplovinMax !=null){
                    onLoadSmallNativesApplovinMax.onNativeAdLoadFailed("");
                }
                switch (selectAdsBackup) {
                    case "ADMOB":
                        AdLoader.Builder builder2 = new AdLoader.Builder(activity, idNativeBackup);
                        builder2.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {

                                if (nativeAd != null) {
                                    nativeAd.destroy();
                                }

                                nativeAd = nativeAds;
                                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                        .inflate(R.layout.admob_small_native, null);
                                populateNativeAdView(nativeAds, adView);
                                layNative.removeAllViews();
                                layNative.addView(adView);
                            }

                        });
                        VideoOptions videoOptions = new VideoOptions.Builder()
                                .build();

                        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                                .setVideoOptions(videoOptions)
                                .build();

                        builder2.withNativeAdOptions(adOptions);



                        AdRequest request = new AdRequest.Builder()
                                .build();
                        AdLoader adLoader =
                                builder2
                                        .withAdListener(
                                                new AdListener() {
                                                    @Override
                                                    public void onAdFailedToLoad(LoadAdError loadAdError) {

                                                    }
                                                })
                                        .build();
                        adLoader.loadAd(request);
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "IRON":
                        adViewIron = IronSource.createBanner(activity, ISBannerSize.BANNER);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        layNative.addView(adViewIron, 0, layoutParams);
                        IronSource.loadBanner(adViewIron, idNativeBackup);
                        break;
                    case "STARTAPP":
                        startAppNativeAd = new StartAppNativeAd(activity);
                        View adViewNative = (View) activity.getLayoutInflater()
                                .inflate(R.layout.startapp_small_native, null);
                        AdEventListener adListener = new AdEventListener() {
                            @Override
                            public void onReceiveAd(@NonNull com.startapp.sdk.adsbase.Ad ad) {
                                ArrayList ads = startAppNativeAd.getNativeAds();    // get NativeAds list
                                Iterator iterator = ads.iterator();
                                while (iterator.hasNext()) {
                                    Log.d("MyApplication", iterator.next().toString());
                                }
                                NativeAdDetails adDetails = (NativeAdDetails) ads.get(0);
                                if (adDetails != null) {
                                    TextView title = adViewNative.findViewById(R.id.ad_headline);
                                    title.setText(adDetails.getTitle());
                                    ImageView icon = adViewNative.findViewById(R.id.ad_app_icon);
                                    Glide.with(activity).load(adDetails.getSecondaryImageUrl()).into(icon);
                                    TextView description = adViewNative.findViewById(R.id.ad_body);
                                    description.setText(adDetails.getDescription());
                                    Button open = adViewNative.findViewById(R.id.ad_call_to_action);
                                    open.setText(adDetails.isApp() ? "Install" : "Open");
                                    adDetails.registerViewForInteraction(adViewNative);
                                }
                            }

                            @Override
                            public void onFailedToReceiveAd(@Nullable com.startapp.sdk.adsbase.Ad ad) {

                            }

                        };
                        startAppNativeAd.loadAd(adListener);
                        layNative.addView(adViewNative);
                        break;
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", idNativeBackup);
                        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);

                        boolean isTablet2 = AppLovinSdkUtils.isTablet(activity);
                        AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
                        adViewDiscovery = new AppLovinAdView(adSize, activity);
                        layNative.addView(adViewDiscovery);
                        adViewDiscovery.loadNextAd();
                        break;
                    case "FACEBOOK":
                        nativeBannerAd = new NativeBannerAd(activity, idNativeBackup);
                        NativeAdListener nativeAdListener = new NativeAdListener() {
                            @Override
                            public void onMediaDownloaded(Ad ad) {

                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (nativeBannerAd == null || nativeBannerAd != ad) {
                                    return;
                                }
                                inflateAd(nativeBannerAd, activity, layNative);
                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        };
                        nativeBannerAd.loadAd(
                                nativeBannerAd.buildLoadAdConfig()
                                        .withAdListener(nativeAdListener)
                                        .build());
                        break;
                    case "ALIEN-M":
                        AlienMediationAds.SmallNatives(activity,layNative,idNativeBackup);
                        break;
                    case "ALIEN-V":
                       AlienViewAds.Banner(activity,layNative,idNativeBackup);
                        break;
                }
            }

            @Override
            public void onNativeAdClicked(final MaxAd ad) {
                if (onLoadSmallNativesApplovinMax !=null){
                    onLoadSmallNativesApplovinMax.onNativeAdClicked();
                }
            }
        });

        nativeAdLoader.loadAd(nativeAdView);
    }


    public static void SmallNativeFan(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {
        nativeBannerAd = new NativeBannerAd(activity, nativeId);
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {
            if (onLoadSmallNativesFacebook !=null){
                onLoadSmallNativesFacebook.onMediaDownloaded();
            }
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                if (onLoadSmallNativesFacebook !=null){
                    onLoadSmallNativesFacebook.onError("");
                }
                switch (selectAdsBackup) {
                    case "FACEBOOK":
                        nativeBannerAd2 = new NativeBannerAd(activity, idNativeBackup);
                        NativeAdListener nativeAdListener = new NativeAdListener() {
                            @Override
                            public void onMediaDownloaded(Ad ad) {

                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (nativeBannerAd2 == null || nativeBannerAd2 != ad) {
                                    return;
                                }
                                inflateAd(nativeBannerAd2, activity, layNative);
                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        };
                        nativeBannerAd2.loadAd(
                                nativeBannerAd2.buildLoadAdConfig()
                                        .withAdListener(nativeAdListener)
                                        .build());
                        break;
                    case "ADMOB":
                        AdLoader.Builder builder2 = new AdLoader.Builder(activity, idNativeBackup);
                        builder2.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {

                                if (nativeAd != null) {
                                    nativeAd.destroy();
                                }

                                nativeAd = nativeAds;
                                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                        .inflate(R.layout.admob_small_native, null);
                                populateNativeAdView(nativeAds, adView);
                                layNative.removeAllViews();
                                layNative.addView(adView);
                            }

                        });
                        VideoOptions videoOptions = new VideoOptions.Builder()
                                .build();

                        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                                .setVideoOptions(videoOptions)
                                .build();

                        builder2.withNativeAdOptions(adOptions);



                        AdRequest request = new AdRequest.Builder()
                                .build();
                        AdLoader adLoader =
                                builder2
                                        .withAdListener(
                                                new AdListener() {
                                                    @Override
                                                    public void onAdFailedToLoad(LoadAdError loadAdError) {

                                                    }
                                                })
                                        .build();
                        adLoader.loadAd(request);
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "IRON":
                        adViewIron = IronSource.createBanner(activity, ISBannerSize.BANNER);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        layNative.addView(adViewIron, 0, layoutParams);
                        IronSource.loadBanner(adViewIron, idNativeBackup);
                        break;
                    case "STARTAPP":
                        startAppNativeAd = new StartAppNativeAd(activity);
                        View adViewNative = (View) activity.getLayoutInflater()
                                .inflate(R.layout.startapp_small_native, null);
                        AdEventListener adListener = new AdEventListener() {
                            @Override
                            public void onReceiveAd(@NonNull com.startapp.sdk.adsbase.Ad ad) {
                                ArrayList ads = startAppNativeAd.getNativeAds();    // get NativeAds list
                                Iterator iterator = ads.iterator();
                                while (iterator.hasNext()) {
                                    Log.d("MyApplication", iterator.next().toString());
                                }
                                NativeAdDetails adDetails = (NativeAdDetails) ads.get(0);
                                if (adDetails != null) {
                                    TextView title = adViewNative.findViewById(R.id.ad_headline);
                                    title.setText(adDetails.getTitle());
                                    ImageView icon = adViewNative.findViewById(R.id.ad_app_icon);
                                    Glide.with(activity).load(adDetails.getSecondaryImageUrl()).into(icon);
                                    TextView description = adViewNative.findViewById(R.id.ad_body);
                                    description.setText(adDetails.getDescription());
                                    Button open = adViewNative.findViewById(R.id.ad_call_to_action);
                                    open.setText(adDetails.isApp() ? "Install" : "Open");
                                    adDetails.registerViewForInteraction(adViewNative);
                                }
                            }

                            @Override
                            public void onFailedToReceiveAd(@Nullable com.startapp.sdk.adsbase.Ad ad) {

                            }

                        };
                        startAppNativeAd.loadAd(adListener);
                        layNative.addView(adViewNative);
                        break;
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", idNativeBackup);
                        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);

                        boolean isTablet2 = AppLovinSdkUtils.isTablet(activity);
                        AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
                        adViewDiscovery = new AppLovinAdView(adSize, activity);
                        layNative.addView(adViewDiscovery);
                        adViewDiscovery.loadNextAd();
                        break;
                    case "APPLOVIN-M":
                        MaxNativeAdViewBinder binder = new MaxNativeAdViewBinder.Builder(R.layout.max_small_native)
                                .setTitleTextViewId(R.id.title_text_view)
                                .setBodyTextViewId(R.id.body_text_view)
                                .setAdvertiserTextViewId(R.id.advertiser_textView)
                                .setIconImageViewId(R.id.icon_image_view)
                                .setMediaContentViewGroupId(R.id.media_view_container)
                                .setOptionsContentViewGroupId(R.id.ad_options_view)
                                .setCallToActionButtonId(R.id.cta_button)
                                .build();
                        nativeAdView = new MaxNativeAdView(binder, activity);
                        nativeAdLoader = new MaxNativeAdLoader(idNativeBackup, activity);
                        nativeAdLoader.setRevenueListener(new MaxAdRevenueListener() {
                            @Override
                            public void onAdRevenuePaid(MaxAd ad) {

                            }
                        });
                        nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
                            @Override
                            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                                if (nativeAdMax != null) {
                                    nativeAdLoader.destroy(nativeAdMax);
                                }
                                nativeAdMax = ad;
                                layNative.removeAllViews();
                                layNative.addView(nativeAdView);
                            }

                            @Override
                            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {

                            }

                            @Override
                            public void onNativeAdClicked(final MaxAd ad) {

                            }
                        });

                        nativeAdLoader.loadAd(nativeAdView);
                        break;
                    case "ALIEN-M":
                        AlienMediationAds.SmallNatives(activity,layNative,idNativeBackup);
                        break;
                    case "ALIEN-V":
                       AlienViewAds.Banner(activity,layNative,idNativeBackup);
                        break;
                }
            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (onLoadSmallNativesFacebook !=null){
                    onLoadSmallNativesFacebook.onAdLoaded();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        if (nativeAdMax != null) {
                            nativeAdLoader.destroy(nativeAdMax);
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
                        break;
                    case "APPLOVIN-D":
                        if (adViewDiscovery != null) {
                            adViewDiscovery.destroy();
                        }
                        break;
                    case "ADMOB":
                        if (nativeAd != null) {
                            nativeAd.destroy();
                        }
                        break;
                }
                if (nativeBannerAd == null || nativeBannerAd != ad) {
                    return;
                }
                inflateAd(nativeBannerAd, activity, layNative);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };
        nativeBannerAd.loadAd(
                nativeBannerAd.buildLoadAdConfig()
                        .withAdListener(nativeAdListener)
                        .build());
    }


    public static void SmallNativeStartApp(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {
        startAppNativeAd = new StartAppNativeAd(activity);
        View adViewNative = (View) activity.getLayoutInflater()
                .inflate(R.layout.startapp_small_native, null);
        AdEventListener adListener = new AdEventListener() {
            @Override
            public void onReceiveAd(@NonNull com.startapp.sdk.adsbase.Ad ad) {
                if (onLoadSmallNativesStartApp !=null){
                    onLoadSmallNativesStartApp.onReceiveAd();
                }
                ArrayList ads = startAppNativeAd.getNativeAds();    // get NativeAds list
                Iterator iterator = ads.iterator();
                while (iterator.hasNext()) {
                    Log.d("MyApplication", iterator.next().toString());
                }
                NativeAdDetails adDetails = (NativeAdDetails) ads.get(0);
                if (adDetails != null) {
                    TextView title = adViewNative.findViewById(R.id.ad_headline);
                    title.setText(adDetails.getTitle());
                    ImageView icon = adViewNative.findViewById(R.id.ad_app_icon);
                    Glide.with(activity).load(adDetails.getSecondaryImageUrl()).into(icon);
                    TextView description = adViewNative.findViewById(R.id.ad_body);
                    description.setText(adDetails.getDescription());
                    Button open = adViewNative.findViewById(R.id.ad_call_to_action);
                    open.setText(adDetails.isApp() ? "Install" : "Open");
                    adDetails.registerViewForInteraction(adViewNative);
                }
            }

            @Override
            public void onFailedToReceiveAd(@Nullable com.startapp.sdk.adsbase.Ad ad) {
                if (onLoadSmallNativesStartApp !=null){
                    onLoadSmallNativesStartApp.onFailedToReceiveAd("");
                }
                switch (selectAdsBackup) {
                    case "ADMOB":
                        AdLoader.Builder builder2 = new AdLoader.Builder(activity, idNativeBackup);
                        builder2.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {

                                if (nativeAd != null) {
                                    nativeAd.destroy();
                                }

                                nativeAd = nativeAds;
                                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                        .inflate(R.layout.admob_small_native, null);
                                populateNativeAdView(nativeAds, adView);
                                layNative.removeAllViews();
                                layNative.addView(adView);
                            }

                        });
                        VideoOptions videoOptions = new VideoOptions.Builder()
                                .build();

                        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                                .setVideoOptions(videoOptions)
                                .build();

                        builder2.withNativeAdOptions(adOptions);



                        AdRequest request = new AdRequest.Builder()
                                .build();
                        AdLoader adLoader =
                                builder2
                                        .withAdListener(
                                                new AdListener() {
                                                    @Override
                                                    public void onAdFailedToLoad(LoadAdError loadAdError) {

                                                    }
                                                })
                                        .build();
                        adLoader.loadAd(request);
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "IRON":
                        adViewIron = IronSource.createBanner(activity, ISBannerSize.BANNER);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        layNative.addView(adViewIron, 0, layoutParams);
                        IronSource.loadBanner(adViewIron, idNativeBackup);
                        break;
                    case "FACEBOOK":
                        nativeBannerAd = new NativeBannerAd(activity, idNativeBackup);
                        NativeAdListener nativeAdListener = new NativeAdListener() {
                            @Override
                            public void onMediaDownloaded(Ad ad) {

                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (nativeBannerAd == null || nativeBannerAd != ad) {
                                    return;
                                }
                                inflateAd(nativeBannerAd, activity, layNative);
                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        };
                        nativeBannerAd.loadAd(
                                nativeBannerAd.buildLoadAdConfig()
                                        .withAdListener(nativeAdListener)
                                        .build());
                        break;
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", idNativeBackup);
                        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);

                        boolean isTablet2 = AppLovinSdkUtils.isTablet(activity);
                        AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
                        adViewDiscovery = new AppLovinAdView(adSize, activity);
                        layNative.addView(adViewDiscovery);
                        adViewDiscovery.loadNextAd();
                        break;
                    case "APPLOVIN-M":
                        MaxNativeAdViewBinder binder = new MaxNativeAdViewBinder.Builder(R.layout.max_small_native)
                                .setTitleTextViewId(R.id.title_text_view)
                                .setBodyTextViewId(R.id.body_text_view)
                                .setAdvertiserTextViewId(R.id.advertiser_textView)
                                .setIconImageViewId(R.id.icon_image_view)
                                .setMediaContentViewGroupId(R.id.media_view_container)
                                .setOptionsContentViewGroupId(R.id.ad_options_view)
                                .setCallToActionButtonId(R.id.cta_button)
                                .build();
                        nativeAdView = new MaxNativeAdView(binder, activity);
                        nativeAdLoader = new MaxNativeAdLoader(idNativeBackup, activity);
                        nativeAdLoader.setRevenueListener(new MaxAdRevenueListener() {
                            @Override
                            public void onAdRevenuePaid(MaxAd ad) {

                            }
                        });
                        nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
                            @Override
                            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                                if (nativeAdMax != null) {
                                    nativeAdLoader.destroy(nativeAdMax);
                                }
                                nativeAdMax = ad;
                                layNative.removeAllViews();
                                layNative.addView(nativeAdView);
                            }

                            @Override
                            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {

                            }

                            @Override
                            public void onNativeAdClicked(final MaxAd ad) {

                            }
                        });

                        nativeAdLoader.loadAd(nativeAdView);
                        break;
                    case "ALIEN-M":
                        AlienMediationAds.SmallNatives(activity,layNative,idNativeBackup);
                        break;
                    case "ALIEN-V":
                       AlienViewAds.Banner(activity,layNative,idNativeBackup);
                        break;
                }
            }

        };
        startAppNativeAd.loadAd(adListener);
        layNative.addView(adViewNative);
    }

    public static void MediumNativeStartApp(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {
        startAppNativeAd = new StartAppNativeAd(activity);
        View adViewNative = (View) activity.getLayoutInflater()
                .inflate(R.layout.startapp_medium_native, null);
        AdEventListener adListener = new AdEventListener() {
            @Override
            public void onReceiveAd(@NonNull com.startapp.sdk.adsbase.Ad ad) {
                if (onLoadMediumNativesStartApp!=null){
                    onLoadMediumNativesStartApp.onReceiveAd();
                }
                ArrayList ads = startAppNativeAd.getNativeAds();    // get NativeAds list
                Iterator iterator = ads.iterator();
                while (iterator.hasNext()) {
                    Log.d("MyApplication", iterator.next().toString());
                }
                NativeAdDetails adDetails = (NativeAdDetails) ads.get(0);
                if (adDetails != null) {
                    TextView title = adViewNative.findViewById(R.id.ad_headline);
                    title.setText(adDetails.getTitle());
                    ImageView icon = adViewNative.findViewById(R.id.ad_app_icon);
                    Glide.with(activity).load(adDetails.getSecondaryImageUrl()).into(icon);
                    ImageView details = adViewNative.findViewById(R.id.imgDetail);
                    //Glide.with(activity).load(adDetails.getImageUrl()).centerCrop().fit().into(details);
                    Glide.with(activity).load(adDetails.getImageUrl()).into(details);
                    TextView description = adViewNative.findViewById(R.id.ad_body);
                    description.setText(adDetails.getDescription());
                    Button open = adViewNative.findViewById(R.id.ad_call_to_action);
                    open.setText(adDetails.isApp() ? "Install" : "Open");
                    adDetails.registerViewForInteraction(adViewNative);
                }
            }

            @Override
            public void onFailedToReceiveAd(@Nullable com.startapp.sdk.adsbase.Ad ad) {
                if (onLoadMediumNativesStartApp!=null){
                    onLoadMediumNativesStartApp.onFailedToReceiveAd("");
                }
                switch (selectAdsBackup) {
                    case "ADMOB": {
                        AdLoader.Builder builder = new AdLoader.Builder(activity,idNativeBackup);
                        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                                if (nativeAd != null) {
                                    nativeAd.destroy();
                                }
                                nativeAd = nativeAds;
                                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                        .inflate(R.layout.admob_big_native, null);
                                populateNativeAdView(nativeAds, adView);
                                layNative.removeAllViews();
                                layNative.addView(adView);
                            }


                        });

                        VideoOptions videoOptions = new VideoOptions.Builder()
                                .build();

                        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                                .setVideoOptions(videoOptions)
                                .build();

                        builder.withNativeAdOptions(adOptions);



                        AdRequest request = new AdRequest.Builder()
                                .build();
                        AdLoader adLoader =
                                builder
                                        .withAdListener(
                                                new AdListener() {
                                                    @Override
                                                    public void onAdFailedToLoad(LoadAdError loadAdError) {

                                                    }
                                                })
                                        .build();
                        adLoader.loadAd(request);
                        break;
                    }
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "IRON":
                        adViewIron = IronSource.createBanner(activity, ISBannerSize.RECTANGLE);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        layNative.addView(adViewIron, 0, layoutParams);
                        IronSource.loadBanner(adViewIron, idNativeBackup);
                        break;
                    case "FACEBOOK":
                        nativeAdfan = new com.facebook.ads.NativeAd(activity, idNativeBackup);
                        NativeAdListener nativeAdListener = new NativeAdListener() {
                            @Override
                            public void onMediaDownloaded(Ad ad) {

                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (nativeAdfan == null || nativeAdfan != ad) {
                                    return;
                                }
                                inflateAd2(nativeAdfan, activity, layNative);
                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        };

                        nativeAdfan.loadAd(
                                nativeAdfan.buildLoadAdConfig()
                                        .withAdListener(nativeAdListener)
                                        .build());
                        break;
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", idNativeBackup);
                        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);

                        adViewDiscovery = new AppLovinAdView(AppLovinAdSize.MREC, activity);
                        layNative.addView(adViewDiscovery);
                        adViewDiscovery.loadNextAd();
                        break;

                    case "APPLOVIN-M":
                        MaxNativeAdViewBinder binder = new MaxNativeAdViewBinder.Builder(R.layout.max_big_native)
                                .setTitleTextViewId(R.id.title_text_view)
                                .setBodyTextViewId(R.id.body_text_view)
                                .setAdvertiserTextViewId(R.id.advertiser_textView)
                                .setIconImageViewId(R.id.icon_image_view)
                                .setMediaContentViewGroupId(R.id.media_view_container)
                                .setOptionsContentViewGroupId(R.id.ad_options_view)
                                .setCallToActionButtonId(R.id.cta_button)
                                .build();
                        nativeAdView = new MaxNativeAdView(binder, activity);

                        nativeAdLoader = new MaxNativeAdLoader(idNativeBackup, activity);
                        nativeAdLoader.setRevenueListener(new MaxAdRevenueListener() {
                            @Override
                            public void onAdRevenuePaid(MaxAd ad) {

                            }
                        });
                        nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
                            @Override
                            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {

                                if (nativeAd != null) {
                                    nativeAd.destroy();
                                }
                                // Cleanup any pre-existing native ad to prevent memory leaks.
                                if (nativeAdMax != null) {
                                    nativeAdLoader.destroy(nativeAdMax);
                                }

                                // Save ad for cleanup.
                                nativeAdMax = ad;

                                // Add ad view to view.
                                layNative.removeAllViews();
                                layNative.addView(nativeAdView);
                            }

                            @Override
                            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {

                            }

                            @Override
                            public void onNativeAdClicked(final MaxAd ad) {

                            }
                        });

                        nativeAdLoader.loadAd(nativeAdView);
                        break;
                    case "ALIEN-M":
                        AlienMediationAds.SmallNatives(activity,layNative,idNativeBackup);
                        break;
                }
            }

        };
        startAppNativeAd.loadAd(adListener);
        layNative.addView(adViewNative);
    }

    public static void MediumNativeAdmob(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup, String Hpk1,
                                         String Hpk2, String Hpk3, String Hpk4, String Hpk5) {

        AdLoader.Builder builder = new AdLoader.Builder(activity, nativeId);
        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
            @Override
            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                if (onLoadMediumNativesAdmob!=null){
                    onLoadMediumNativesAdmob.onNativeAdLoaded();
                }
                if (nativeAd != null) {
                    nativeAd.destroy();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        if (nativeAdMax != null) {
                            nativeAdLoader.destroy(nativeAdMax);
                        }
                        break;
                    case "MOPUB":

                        break;
                    case "IRON":
                        if (adViewIron != null) {
                            adViewIron.isDestroyed();
                        }
                        break;
                    case "STARTAPP":
                        break;
                    case "APPLOVIN-D":
                        if (adViewDiscovery != null) {
                            adViewDiscovery.destroy();
                        }
                        break;
                    case "FACEBOOK":
                        if (nativeAdfan != null) {
                            nativeAdfan.destroy();
                        }
                        break;
                }
                nativeAd = nativeAds;
                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                        .inflate(R.layout.admob_big_native, null);
                populateNativeAdView(nativeAds, adView);
                layNative.removeAllViews();
                layNative.addView(adView);
            }


        });

        VideoOptions videoOptions = new VideoOptions.Builder()
                .build();

        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                .setVideoOptions(videoOptions)
                .build();

        builder.withNativeAdOptions(adOptions);

        AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                .build();
        AdLoader adLoader =
                builder
                        .withAdListener(
                                new AdListener() {
                                    @Override
                                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                                        if (onLoadMediumNativesAdmob!=null){
                                            onLoadMediumNativesAdmob.onAdFailedToLoad("");
                                        }
                                        switch (selectAdsBackup) {

                                            case "APPLOVIN-M": {
                                                MaxNativeAdViewBinder binder = new MaxNativeAdViewBinder.Builder(R.layout.max_big_native)
                                                        .setTitleTextViewId(R.id.title_text_view)
                                                        .setBodyTextViewId(R.id.body_text_view)
                                                        .setAdvertiserTextViewId(R.id.advertiser_textView)
                                                        .setIconImageViewId(R.id.icon_image_view)
                                                        .setMediaContentViewGroupId(R.id.media_view_container)
                                                        .setOptionsContentViewGroupId(R.id.ad_options_view)
                                                        .setCallToActionButtonId(R.id.cta_button)
                                                        .build();
                                                nativeAdView = new MaxNativeAdView(binder, activity);

                                                nativeAdLoader = new MaxNativeAdLoader(idNativeBackup, activity);
                                                nativeAdLoader.setRevenueListener(new MaxAdRevenueListener() {
                                                    @Override
                                                    public void onAdRevenuePaid(MaxAd ad) {

                                                    }
                                                });
                                                nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
                                                    @Override
                                                    public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {

                                                        if (nativeAd != null) {
                                                            nativeAd.destroy();
                                                        }
                                                        // Cleanup any pre-existing native ad to prevent memory leaks.
                                                        if (nativeAdMax != null) {
                                                            nativeAdLoader.destroy(nativeAdMax);
                                                        }

                                                        // Save ad for cleanup.
                                                        nativeAdMax = ad;

                                                        // Add ad view to view.
                                                        layNative.removeAllViews();
                                                        layNative.addView(nativeAdView);
                                                    }

                                                    @Override
                                                    public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {

                                                    }

                                                    @Override
                                                    public void onNativeAdClicked(final MaxAd ad) {

                                                    }
                                                });

                                                nativeAdLoader.loadAd(nativeAdView);
                                                break;
                                            }
                                            case "MOPUB":
                                            case "UNITY":

                                                break;
                                            case "IRON":
                                                adViewIron = IronSource.createBanner(activity, ISBannerSize.RECTANGLE);
                                                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                                        FrameLayout.LayoutParams.WRAP_CONTENT);
                                                layNative.addView(adViewIron, 0, layoutParams);
                                                IronSource.loadBanner(adViewIron, idNativeBackup);
                                                break;
                                            case "STARTAPP":
                                                startAppNativeAd = new StartAppNativeAd(activity);
                                                View adViewNative = (View) activity.getLayoutInflater()
                                                        .inflate(R.layout.startapp_medium_native, null);
                                                AdEventListener adListener = new AdEventListener() {
                                                    @Override
                                                    public void onReceiveAd(@NonNull com.startapp.sdk.adsbase.Ad ad) {
                                                        ArrayList ads = startAppNativeAd.getNativeAds();    // get NativeAds list
                                                        Iterator iterator = ads.iterator();
                                                        while (iterator.hasNext()) {
                                                            Log.d("MyApplication", iterator.next().toString());
                                                        }
                                                        NativeAdDetails adDetails = (NativeAdDetails) ads.get(0);
                                                        if (adDetails != null) {
                                                            TextView title = adViewNative.findViewById(R.id.ad_headline);
                                                            title.setText(adDetails.getTitle());
                                                            ImageView icon = adViewNative.findViewById(R.id.ad_app_icon);
                                                            Glide.with(activity).load(adDetails.getSecondaryImageUrl()).into(icon);
                                                            ImageView details = adViewNative.findViewById(R.id.imgDetail);
                                                            Glide.with(activity).load(adDetails.getImageUrl()).into(details);
                                                            TextView description = adViewNative.findViewById(R.id.ad_body);
                                                            description.setText(adDetails.getDescription());
                                                            Button open = adViewNative.findViewById(R.id.ad_call_to_action);
                                                            open.setText(adDetails.isApp() ? "Install" : "Open");
                                                            adDetails.registerViewForInteraction(adViewNative);
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailedToReceiveAd(@Nullable com.startapp.sdk.adsbase.Ad ad) {

                                                    }

                                                };
                                                startAppNativeAd.loadAd(adListener);
                                                layNative.addView(adViewNative);
                                                break;
                                            case "APPLOVIN-D":
                                                AdRequest.Builder builder = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                                                        .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5);
                                                Bundle bannerExtras = new Bundle();
                                                bannerExtras.putString("zone_id", idNativeBackup);
                                                builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);

                                                adViewDiscovery = new AppLovinAdView(AppLovinAdSize.MREC, activity);
                                                layNative.addView(adViewDiscovery);
                                                adViewDiscovery.loadNextAd();
                                                break;

                                            case "FACEBOOK":
                                                nativeAdfan = new com.facebook.ads.NativeAd(activity, idNativeBackup);
                                                NativeAdListener nativeAdListener = new NativeAdListener() {
                                                    @Override
                                                    public void onMediaDownloaded(Ad ad) {

                                                    }

                                                    @Override
                                                    public void onError(Ad ad, AdError adError) {

                                                    }

                                                    @Override
                                                    public void onAdLoaded(Ad ad) {
                                                        if (nativeAdfan == null || nativeAdfan != ad) {
                                                            return;
                                                        }
                                                        inflateAd2(nativeAdfan, activity, layNative);
                                                    }

                                                    @Override
                                                    public void onAdClicked(Ad ad) {

                                                    }

                                                    @Override
                                                    public void onLoggingImpression(Ad ad) {

                                                    }
                                                };

                                                nativeAdfan.loadAd(
                                                        nativeAdfan.buildLoadAdConfig()
                                                                .withAdListener(nativeAdListener)
                                                                .build());

                                                break;
                                            case "ALIEN-M":
                                                AlienMediationAds.MediumNatives(activity,layNative,idNativeBackup);
                                                break;
                                            case "ADMOB":
                                                AdLoader.Builder builder2 = new AdLoader.Builder(activity,idNativeBackup);
                                                builder2.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                                                    @Override
                                                    public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                                                        if (nativeAd2 != null) {
                                                            nativeAd2.destroy();
                                                        }
                                                        nativeAd2 = nativeAds;
                                                        NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                                                .inflate(R.layout.admob_big_native, null);
                                                        populateNativeAdView(nativeAds, adView);
                                                        layNative.removeAllViews();
                                                        layNative.addView(adView);
                                                    }


                                                });

                                                VideoOptions videoOptions = new VideoOptions.Builder()
                                                        .build();

                                                NativeAdOptions adOptions = new NativeAdOptions.Builder()
                                                        .setVideoOptions(videoOptions)
                                                        .build();

                                                builder2.withNativeAdOptions(adOptions);



                                                AdRequest request = new AdRequest.Builder()
                                                        .build();
                                                AdLoader adLoader =
                                                        builder2
                                                                .withAdListener(
                                                                        new AdListener() {
                                                                            @Override
                                                                            public void onAdFailedToLoad(LoadAdError loadAdError) {

                                                                            }
                                                                        })
                                                                .build();
                                                adLoader.loadAd(request);
                                                break;
                                        }
                                    }
                                })
                        .build();
        adLoader.loadAd(request);
    }

    public static void MediumNativeMax(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {

        MaxNativeAdViewBinder binder = new MaxNativeAdViewBinder.Builder(R.layout.max_big_native)
                .setTitleTextViewId(R.id.title_text_view)
                .setBodyTextViewId(R.id.body_text_view)
                .setAdvertiserTextViewId(R.id.advertiser_textView)
                .setIconImageViewId(R.id.icon_image_view)
                .setMediaContentViewGroupId(R.id.media_view_container)
                .setOptionsContentViewGroupId(R.id.ad_options_view)
                .setCallToActionButtonId(R.id.cta_button)
                .build();
        nativeAdView = new MaxNativeAdView(binder, activity);

        nativeAdLoader = new MaxNativeAdLoader(nativeId, activity);
        nativeAdLoader.setRevenueListener(new MaxAdRevenueListener() {
            @Override
            public void onAdRevenuePaid(MaxAd ad) {

            }
        });
        nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                if (onLoadMediumNativesApplovinMax!=null){
                    onLoadMediumNativesApplovinMax.onNativeAdLoaded();
                }
                switch (selectAdsBackup) {
                    case "ADMOB":
                        if (nativeAd != null) {
                            nativeAd.destroy();
                        }
                        break;
                    case "MOPUB":

                        break;
                    case "IRON":
                        if (adViewIron != null) {
                            adViewIron.isDestroyed();
                        }
                        break;
                    case "STARTAPP":

                        break;
                    case "APPLOVIN-D":
                        if (adViewDiscovery != null) {
                            adViewDiscovery.destroy();
                        }
                        break;
                    case "FACEBOOK":
                        if (nativeAdfan != null) {
                            nativeAdfan.destroy();
                        }
                        break;
                }
                if (nativeAdMax != null) {
                    nativeAdLoader.destroy(nativeAdMax);
                }
                nativeAdMax = ad;
                layNative.removeAllViews();
                layNative.addView(nativeAdView);
            }

            @Override
            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                if (onLoadMediumNativesApplovinMax!=null){
                    onLoadMediumNativesApplovinMax.onNativeAdLoadFailed("");
                }
                switch (selectAdsBackup) {
                    case "ADMOB":
                        AdLoader.Builder builder = new AdLoader.Builder(activity,idNativeBackup);
                        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                                if (nativeAd != null) {
                                    nativeAd.destroy();
                                }
                                nativeAd = nativeAds;
                                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                        .inflate(R.layout.admob_big_native, null);
                                populateNativeAdView(nativeAds, adView);
                                layNative.removeAllViews();
                                layNative.addView(adView);
                            }


                        });

                        VideoOptions videoOptions = new VideoOptions.Builder()
                                .build();

                        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                                .setVideoOptions(videoOptions)
                                .build();

                        builder.withNativeAdOptions(adOptions);



                        AdRequest request = new AdRequest.Builder()
                                .build();
                        AdLoader adLoader =
                                builder
                                        .withAdListener(
                                                new AdListener() {
                                                    @Override
                                                    public void onAdFailedToLoad(LoadAdError loadAdError) {

                                                    }
                                                })
                                        .build();
                        adLoader.loadAd(request);
                        break;

                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "IRON":
                        adViewIron = IronSource.createBanner(activity, ISBannerSize.RECTANGLE);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        layNative.addView(adViewIron, 0, layoutParams);
                        IronSource.loadBanner(adViewIron, idNativeBackup);
                        break;
                    case "STARTAPP":
                        startAppNativeAd = new StartAppNativeAd(activity);
                        View adViewNative = (View) activity.getLayoutInflater()
                                .inflate(R.layout.startapp_medium_native, null);
                        AdEventListener adListener = new AdEventListener() {
                            @Override
                            public void onReceiveAd(@NonNull com.startapp.sdk.adsbase.Ad ad) {
                                ArrayList ads = startAppNativeAd.getNativeAds();    // get NativeAds list
                                Iterator iterator = ads.iterator();
                                while (iterator.hasNext()) {
                                    Log.d("MyApplication", iterator.next().toString());
                                }
                                NativeAdDetails adDetails = (NativeAdDetails) ads.get(0);
                                if (adDetails != null) {
                                    TextView title = adViewNative.findViewById(R.id.ad_headline);
                                    title.setText(adDetails.getTitle());
                                    ImageView icon = adViewNative.findViewById(R.id.ad_app_icon);
                                    Glide.with(activity).load(adDetails.getSecondaryImageUrl()).into(icon);
                                    ImageView details = adViewNative.findViewById(R.id.imgDetail);
                                    Glide.with(activity).load(adDetails.getImageUrl()).into(details);
                                    TextView description = adViewNative.findViewById(R.id.ad_body);
                                    description.setText(adDetails.getDescription());
                                    Button open = adViewNative.findViewById(R.id.ad_call_to_action);
                                    open.setText(adDetails.isApp() ? "Install" : "Open");
                                    adDetails.registerViewForInteraction(adViewNative);
                                }
                            }

                            @Override
                            public void onFailedToReceiveAd(@Nullable com.startapp.sdk.adsbase.Ad ad) {

                            }

                        };
                        startAppNativeAd.loadAd(adListener);
                        layNative.addView(adViewNative);
                        break;
                    case "APPLOVIN-D":
                        AdRequest.Builder builder2 = new AdRequest.Builder();
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", idNativeBackup);
                        builder2.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);

                        adViewDiscovery = new AppLovinAdView(AppLovinAdSize.MREC, activity);
                        layNative.addView(adViewDiscovery);
                        adViewDiscovery.loadNextAd();
                        break;
                    case "ALIEN-M":
                        AlienMediationAds.MediumNatives(activity,layNative,idNativeBackup);
                        break;
                    case "FACEBOOK":
                        nativeAdfan = new com.facebook.ads.NativeAd(activity, idNativeBackup);
                        NativeAdListener nativeAdListener = new NativeAdListener() {
                            @Override
                            public void onMediaDownloaded(Ad ad) {

                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (nativeAdfan == null || nativeAdfan != ad) {
                                    return;
                                }
                                inflateAd2(nativeAdfan, activity, layNative);
                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        };

                        nativeAdfan.loadAd(
                                nativeAdfan.buildLoadAdConfig()
                                        .withAdListener(nativeAdListener)
                                        .build());

                        break;
                }
            }

            @Override
            public void onNativeAdClicked(final MaxAd ad) {
                if (onLoadMediumNativesApplovinMax!=null){
                    onLoadMediumNativesApplovinMax.onNativeAdClicked();
                }
            }
        });
        nativeAdLoader.loadAd(nativeAdView);

    }

    public static void MediumNativeFan(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {
        nativeAdfan = new com.facebook.ads.NativeAd(activity, nativeId);
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {
                if (onLoadMediumNativesFacebook!=null){
                    onLoadMediumNativesFacebook.onMediaDownloaded();
                }
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                if (onLoadMediumNativesFacebook!=null){
                    onLoadMediumNativesFacebook.onError("");
                }
                switch (selectAdsBackup) {
                    case "FACEBOOK":
                        nativeAdfan2 = new com.facebook.ads.NativeAd(activity, idNativeBackup);
                        NativeAdListener nativeAdListener = new NativeAdListener() {
                            @Override
                            public void onMediaDownloaded(Ad ad) {

                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (nativeAdfan2 == null || nativeAdfan2 != ad) {
                                    return;
                                }
                                inflateAd2(nativeAdfan2, activity, layNative);
                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        };

                        nativeAdfan2.loadAd(
                                nativeAdfan2.buildLoadAdConfig()
                                        .withAdListener(nativeAdListener)
                                        .build());
                        break;
                    case "APPLOVIN-M": {
                        MaxNativeAdViewBinder binder = new MaxNativeAdViewBinder.Builder(R.layout.max_big_native)
                                .setTitleTextViewId(R.id.title_text_view)
                                .setBodyTextViewId(R.id.body_text_view)
                                .setAdvertiserTextViewId(R.id.advertiser_textView)
                                .setIconImageViewId(R.id.icon_image_view)
                                .setMediaContentViewGroupId(R.id.media_view_container)
                                .setOptionsContentViewGroupId(R.id.ad_options_view)
                                .setCallToActionButtonId(R.id.cta_button)
                                .build();
                        nativeAdView = new MaxNativeAdView(binder, activity);

                        nativeAdLoader = new MaxNativeAdLoader(idNativeBackup, activity);
                        nativeAdLoader.setRevenueListener(new MaxAdRevenueListener() {
                            @Override
                            public void onAdRevenuePaid(MaxAd ad) {

                            }
                        });
                        nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
                            @Override
                            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                                if (nativeAd != null) {
                                    nativeAd.destroy();
                                }
                                if (nativeAdMax != null) {
                                    nativeAdLoader.destroy(nativeAdMax);
                                }
                                nativeAdMax = ad;
                                layNative.removeAllViews();
                                layNative.addView(nativeAdView);
                            }

                            @Override
                            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {

                            }

                            @Override
                            public void onNativeAdClicked(final MaxAd ad) {

                            }
                        });

                        nativeAdLoader.loadAd(nativeAdView);
                        break;
                    }
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "IRON":
                        adViewIron = IronSource.createBanner(activity, ISBannerSize.RECTANGLE);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        layNative.addView(adViewIron, 0, layoutParams);
                        IronSource.loadBanner(adViewIron, idNativeBackup);
                        break;
                    case "STARTAPP":
                        startAppNativeAd = new StartAppNativeAd(activity);
                        View adViewNative = (View) activity.getLayoutInflater()
                                .inflate(R.layout.startapp_medium_native, null);
                        AdEventListener adListener = new AdEventListener() {
                            @Override
                            public void onReceiveAd(@NonNull com.startapp.sdk.adsbase.Ad ad) {
                                ArrayList ads = startAppNativeAd.getNativeAds();    // get NativeAds list
                                Iterator iterator = ads.iterator();
                                while (iterator.hasNext()) {
                                    Log.d("MyApplication", iterator.next().toString());
                                }
                                NativeAdDetails adDetails = (NativeAdDetails) ads.get(0);
                                if (adDetails != null) {
                                    TextView title = adViewNative.findViewById(R.id.ad_headline);
                                    title.setText(adDetails.getTitle());
                                    ImageView icon = adViewNative.findViewById(R.id.ad_app_icon);
                                    Glide.with(activity).load(adDetails.getSecondaryImageUrl()).into(icon);
                                    ImageView details = adViewNative.findViewById(R.id.imgDetail);
                                    Glide.with(activity).load(adDetails.getImageUrl()).into(details);
                                    TextView description = adViewNative.findViewById(R.id.ad_body);
                                    description.setText(adDetails.getDescription());
                                    Button open = adViewNative.findViewById(R.id.ad_call_to_action);
                                    open.setText(adDetails.isApp() ? "Install" : "Open");
                                    adDetails.registerViewForInteraction(adViewNative);
                                }
                            }

                            @Override
                            public void onFailedToReceiveAd(@Nullable com.startapp.sdk.adsbase.Ad ad) {

                            }

                        };
                        startAppNativeAd.loadAd(adListener);
                        layNative.addView(adViewNative);
                        break;
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", idNativeBackup);
                        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);

                        adViewDiscovery = new AppLovinAdView(AppLovinAdSize.MREC, activity);
                        layNative.addView(adViewDiscovery);
                        adViewDiscovery.loadNextAd();
                        break;

                    case "ADMOB":
                        AdLoader.Builder builder2 = new AdLoader.Builder(activity, idNativeBackup);
                        builder2.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                                if (nativeAd != null) {
                                    nativeAd.destroy();
                                }
                                nativeAd = nativeAds;
                                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                        .inflate(R.layout.admob_big_native, null);
                                populateNativeAdView(nativeAds, adView);
                                layNative.removeAllViews();
                                layNative.addView(adView);
                            }


                        });

                        VideoOptions videoOptions = new VideoOptions.Builder()
                                .build();

                        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                                .setVideoOptions(videoOptions)
                                .build();

                        builder2.withNativeAdOptions(adOptions);



                        AdRequest request = new AdRequest.Builder()
                                .build();
                        AdLoader adLoader =
                                builder2
                                        .withAdListener(
                                                new AdListener() {
                                                    @Override
                                                    public void onAdFailedToLoad(LoadAdError loadAdError) {

                                                    }
                                                })
                                        .build();
                        adLoader.loadAd(request);

                        break;
                    case "ALIEN-M":
                        AlienMediationAds.MediumNatives(activity,layNative,idNativeBackup);
                        break;
                }
            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (onLoadMediumNativesFacebook!=null){
                    onLoadMediumNativesFacebook.onAdLoaded();
                }
                switch (selectAdsBackup) {
                    case "ADMOB":
                        if (nativeAd != null) {
                            nativeAd.destroy();
                        }
                        break;
                    case "MOPUB":

                        break;
                    case "IRON":
                        if (adViewIron != null) {
                            adViewIron.isDestroyed();
                        }
                        break;
                    case "STARTAPP":

                        break;
                    case "APPLOVIN-D":
                        if (adViewDiscovery != null) {
                            adViewDiscovery.destroy();
                        }
                        break;
                    case "APPLOVIN-M":
                        if (nativeAdMax != null) {
                            nativeAdLoader.destroy(nativeAdMax);
                        }
                        break;
                }
                if (nativeAdfan == null || nativeAdfan != ad) {
                    return;
                }
                inflateAd2(nativeAdfan, activity, layNative);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };

        nativeAdfan.loadAd(
                nativeAdfan.buildLoadAdConfig()
                        .withAdListener(nativeAdListener)
                        .build());

    }


    public static void MediumNativeAlien(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {
        AlienMediationAds.MediumNatives(activity,layNative,nativeId);
        AlienMediationAds.onLoadNative = new OnLoadNative() {
            @Override
            public void onNativeAdLoaded() {
                if(onLoadMediumNativesAlien!=null){
                    onLoadMediumNativesAlien.onNativeAdLoaded();
                }
            }

            @Override
            public void onNativeAdClicked() {
                if(onLoadMediumNativesAlien!=null){
                    onLoadMediumNativesAlien.onNativeAdClicked();
                }
            }

            @Override
            public void onNativeAdFailedToLoad(String error) {
                if(onLoadMediumNativesAlien!=null){
                    onLoadMediumNativesAlien.onNativeAdFailedToLoad();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-M": {
                        MaxNativeAdViewBinder binder = new MaxNativeAdViewBinder.Builder(R.layout.max_big_native)
                                .setTitleTextViewId(R.id.title_text_view)
                                .setBodyTextViewId(R.id.body_text_view)
                                .setAdvertiserTextViewId(R.id.advertiser_textView)
                                .setIconImageViewId(R.id.icon_image_view)
                                .setMediaContentViewGroupId(R.id.media_view_container)
                                .setOptionsContentViewGroupId(R.id.ad_options_view)
                                .setCallToActionButtonId(R.id.cta_button)
                                .build();
                        nativeAdView = new MaxNativeAdView(binder, activity);

                        nativeAdLoader = new MaxNativeAdLoader(idNativeBackup, activity);
                        nativeAdLoader.setRevenueListener(new MaxAdRevenueListener() {
                            @Override
                            public void onAdRevenuePaid(MaxAd ad) {

                            }
                        });
                        nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
                            @Override
                            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                                if (nativeAd != null) {
                                    nativeAd.destroy();
                                }
                                if (nativeAdMax != null) {
                                    nativeAdLoader.destroy(nativeAdMax);
                                }
                                nativeAdMax = ad;
                                layNative.removeAllViews();
                                layNative.addView(nativeAdView);
                            }

                            @Override
                            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {

                            }

                            @Override
                            public void onNativeAdClicked(final MaxAd ad) {

                            }
                        });

                        nativeAdLoader.loadAd(nativeAdView);
                        break;
                    }
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "IRON":
                        adViewIron = IronSource.createBanner(activity, ISBannerSize.RECTANGLE);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        layNative.addView(adViewIron, 0, layoutParams);
                        IronSource.loadBanner(adViewIron, idNativeBackup);
                        break;
                    case "STARTAPP":
                        startAppNativeAd = new StartAppNativeAd(activity);
                        View adViewNative = (View) activity.getLayoutInflater()
                                .inflate(R.layout.startapp_medium_native, null);
                        AdEventListener adListener = new AdEventListener() {
                            @Override
                            public void onReceiveAd(@NonNull com.startapp.sdk.adsbase.Ad ad) {
                                ArrayList ads = startAppNativeAd.getNativeAds();    // get NativeAds list
                                Iterator iterator = ads.iterator();
                                while (iterator.hasNext()) {
                                    Log.d("MyApplication", iterator.next().toString());
                                }
                                NativeAdDetails adDetails = (NativeAdDetails) ads.get(0);
                                if (adDetails != null) {
                                    TextView title = adViewNative.findViewById(R.id.ad_headline);
                                    title.setText(adDetails.getTitle());
                                    ImageView icon = adViewNative.findViewById(R.id.ad_app_icon);
                                    Glide.with(activity).load(adDetails.getSecondaryImageUrl()).into(icon);
                                    ImageView details = adViewNative.findViewById(R.id.imgDetail);
                                    Glide.with(activity).load(adDetails.getImageUrl()).into(details);
                                    TextView description = adViewNative.findViewById(R.id.ad_body);
                                    description.setText(adDetails.getDescription());
                                    Button open = adViewNative.findViewById(R.id.ad_call_to_action);
                                    open.setText(adDetails.isApp() ? "Install" : "Open");
                                    adDetails.registerViewForInteraction(adViewNative);
                                }
                            }

                            @Override
                            public void onFailedToReceiveAd(@Nullable com.startapp.sdk.adsbase.Ad ad) {

                            }

                        };
                        startAppNativeAd.loadAd(adListener);
                        layNative.addView(adViewNative);
                        break;
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", idNativeBackup);
                        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);

                        adViewDiscovery = new AppLovinAdView(AppLovinAdSize.MREC, activity);
                        layNative.addView(adViewDiscovery);
                        adViewDiscovery.loadNextAd();
                        break;

                    case "ADMOB":
                        AdLoader.Builder builder2 = new AdLoader.Builder(activity, idNativeBackup);
                        builder2.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                                if (nativeAd != null) {
                                    nativeAd.destroy();
                                }
                                nativeAd = nativeAds;
                                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                        .inflate(R.layout.admob_big_native, null);
                                populateNativeAdView(nativeAds, adView);
                                layNative.removeAllViews();
                                layNative.addView(adView);
                            }


                        });

                        VideoOptions videoOptions = new VideoOptions.Builder()
                                .build();

                        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                                .setVideoOptions(videoOptions)
                                .build();

                        builder2.withNativeAdOptions(adOptions);



                        AdRequest request = new AdRequest.Builder()
                                .build();
                        AdLoader adLoader =
                                builder2
                                        .withAdListener(
                                                new AdListener() {
                                                    @Override
                                                    public void onAdFailedToLoad(LoadAdError loadAdError) {

                                                    }
                                                })
                                        .build();
                        adLoader.loadAd(request);

                        break;
                    case "FACEBOOK":
                        nativeAdfan = new com.facebook.ads.NativeAd(activity, idNativeBackup);
                        NativeAdListener nativeAdListener = new NativeAdListener() {
                            @Override
                            public void onMediaDownloaded(Ad ad) {

                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (nativeAdfan == null || nativeAdfan != ad) {
                                    return;
                                }
                                inflateAd2(nativeAdfan, activity, layNative);
                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        };

                        nativeAdfan.loadAd(
                                nativeAdfan.buildLoadAdConfig()
                                        .withAdListener(nativeAdListener)
                                        .build());
                        break;
                }

            }
        };

    }

    public static void SmallNativeAlien(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {
        AlienMediationAds.SmallNatives(activity,layNative,nativeId);
        AlienMediationAds.onLoadNative = new OnLoadNative() {
            @Override
            public void onNativeAdLoaded() {
                if(onLoadSmallNativesAlien!=null){
                    onLoadSmallNativesAlien.onNativeAdLoaded();
                }
            }

            @Override
            public void onNativeAdClicked() {
                if(onLoadSmallNativesAlien!=null){
                    onLoadSmallNativesAlien.onNativeAdClicked();
                }
            }

            @Override
            public void onNativeAdFailedToLoad(String error) {
                if(onLoadSmallNativesAlien!=null){
                    onLoadSmallNativesAlien.onNativeAdFailedToLoad();
                }
                switch (selectAdsBackup) {
                    case "ADMOB":
                        AdLoader.Builder builder2 = new AdLoader.Builder(activity, idNativeBackup);
                        builder2.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {

                                if (nativeAd != null) {
                                    nativeAd.destroy();
                                }

                                nativeAd = nativeAds;
                                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                        .inflate(R.layout.admob_small_native, null);
                                populateNativeAdView(nativeAds, adView);
                                layNative.removeAllViews();
                                layNative.addView(adView);
                            }

                        });
                        VideoOptions videoOptions = new VideoOptions.Builder()
                                .build();

                        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                                .setVideoOptions(videoOptions)
                                .build();

                        builder2.withNativeAdOptions(adOptions);



                        AdRequest request = new AdRequest.Builder()
                                .build();
                        AdLoader adLoader =
                                builder2
                                        .withAdListener(
                                                new AdListener() {
                                                    @Override
                                                    public void onAdFailedToLoad(LoadAdError loadAdError) {

                                                    }
                                                })
                                        .build();
                        adLoader.loadAd(request);
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "IRON":
                        adViewIron = IronSource.createBanner(activity, ISBannerSize.BANNER);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        layNative.addView(adViewIron, 0, layoutParams);
                        IronSource.loadBanner(adViewIron, idNativeBackup);
                        break;
                    case "FACEBOOK":
                        nativeBannerAd = new NativeBannerAd(activity, idNativeBackup);
                        NativeAdListener nativeAdListener = new NativeAdListener() {
                            @Override
                            public void onMediaDownloaded(Ad ad) {

                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (nativeBannerAd == null || nativeBannerAd != ad) {
                                    return;
                                }
                                inflateAd(nativeBannerAd, activity, layNative);
                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        };
                        nativeBannerAd.loadAd(
                                nativeBannerAd.buildLoadAdConfig()
                                        .withAdListener(nativeAdListener)
                                        .build());
                        break;
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", idNativeBackup);
                        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);

                        boolean isTablet2 = AppLovinSdkUtils.isTablet(activity);
                        AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
                        adViewDiscovery = new AppLovinAdView(adSize, activity);
                        layNative.addView(adViewDiscovery);
                        adViewDiscovery.loadNextAd();
                        break;
                    case "APPLOVIN-M":
                        MaxNativeAdViewBinder binder = new MaxNativeAdViewBinder.Builder(R.layout.max_small_native)
                                .setTitleTextViewId(R.id.title_text_view)
                                .setBodyTextViewId(R.id.body_text_view)
                                .setAdvertiserTextViewId(R.id.advertiser_textView)
                                .setIconImageViewId(R.id.icon_image_view)
                                .setMediaContentViewGroupId(R.id.media_view_container)
                                .setOptionsContentViewGroupId(R.id.ad_options_view)
                                .setCallToActionButtonId(R.id.cta_button)
                                .build();
                        nativeAdView = new MaxNativeAdView(binder, activity);
                        nativeAdLoader = new MaxNativeAdLoader(idNativeBackup, activity);
                        nativeAdLoader.setRevenueListener(new MaxAdRevenueListener() {
                            @Override
                            public void onAdRevenuePaid(MaxAd ad) {

                            }
                        });
                        nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
                            @Override
                            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                                if (nativeAdMax != null) {
                                    nativeAdLoader.destroy(nativeAdMax);
                                }
                                nativeAdMax = ad;
                                layNative.removeAllViews();
                                layNative.addView(nativeAdView);
                            }

                            @Override
                            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {

                            }

                            @Override
                            public void onNativeAdClicked(final MaxAd ad) {

                            }
                        });

                        nativeAdLoader.loadAd(nativeAdView);
                        break;
                    case "STARTAPP":
                        startAppNativeAd = new StartAppNativeAd(activity);
                        View adViewNative = (View) activity.getLayoutInflater()
                                .inflate(R.layout.startapp_small_native, null);
                        AdEventListener adListener = new AdEventListener() {
                            @Override
                            public void onReceiveAd(@NonNull com.startapp.sdk.adsbase.Ad ad) {
                                ArrayList ads = startAppNativeAd.getNativeAds();    // get NativeAds list
                                Iterator iterator = ads.iterator();
                                while (iterator.hasNext()) {
                                    Log.d("MyApplication", iterator.next().toString());
                                }
                                NativeAdDetails adDetails = (NativeAdDetails) ads.get(0);
                                if (adDetails != null) {
                                    TextView title = adViewNative.findViewById(R.id.ad_headline);
                                    title.setText(adDetails.getTitle());
                                    ImageView icon = adViewNative.findViewById(R.id.ad_app_icon);
                                    Glide.with(activity).load(adDetails.getSecondaryImageUrl()).into(icon);
                                    TextView description = adViewNative.findViewById(R.id.ad_body);
                                    description.setText(adDetails.getDescription());
                                    Button open = adViewNative.findViewById(R.id.ad_call_to_action);
                                    open.setText(adDetails.isApp() ? "Install" : "Open");
                                    adDetails.registerViewForInteraction(adViewNative);
                                }
                            }

                            @Override
                            public void onFailedToReceiveAd(@Nullable com.startapp.sdk.adsbase.Ad ad) {

                            }

                        };
                        startAppNativeAd.loadAd(adListener);
                        layNative.addView(adViewNative);
                        break;
                    case "ALIEN-V":
                        AlienViewAds.Banner(activity,layNative,idNativeBackup);
                        break;
                }
            }
        };



    }

    //Rectangle
    public static void SmallNativeStartAppRectangle(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {
        startAppNativeAd = new StartAppNativeAd(activity);
        View adViewNative = (View) activity.getLayoutInflater()
                .inflate(R.layout.startapp_small_rectangle_native, null);
        AdEventListener adListener = new AdEventListener() {
            @Override
            public void onReceiveAd(@NonNull com.startapp.sdk.adsbase.Ad ad) {
                if (onLoadMediumNativesStartApp!=null){
                    onLoadMediumNativesStartApp.onReceiveAd();
                }
                ArrayList ads = startAppNativeAd.getNativeAds();    // get NativeAds list
                Iterator iterator = ads.iterator();
                while (iterator.hasNext()) {
                    Log.d("MyApplication", iterator.next().toString());
                }
                NativeAdDetails adDetails = (NativeAdDetails) ads.get(0);
                if (adDetails != null) {
                    TextView title = adViewNative.findViewById(R.id.ad_headline);
                    title.setText(adDetails.getTitle());
                    ImageView icon = adViewNative.findViewById(R.id.ad_app_icon);
                    Glide.with(activity).load(adDetails.getSecondaryImageUrl()).into(icon);
                    ImageView details = adViewNative.findViewById(R.id.imgDetail);
                    //Glide.with(activity).load(adDetails.getImageUrl()).centerCrop().fit().into(details);
                    Glide.with(activity).load(adDetails.getImageUrl()).into(details);
                    TextView description = adViewNative.findViewById(R.id.ad_body);
                    description.setText(adDetails.getDescription());
                    Button open = adViewNative.findViewById(R.id.ad_call_to_action);
                    open.setText(adDetails.isApp() ? "Install" : "Open");
                    adDetails.registerViewForInteraction(adViewNative);
                }
            }

            @Override
            public void onFailedToReceiveAd(@Nullable com.startapp.sdk.adsbase.Ad ad) {
                if (onLoadMediumNativesStartApp!=null){
                    onLoadMediumNativesStartApp.onFailedToReceiveAd("");
                }
                switch (selectAdsBackup) {
                    case "ADMOB":
                        AdLoader.Builder builder = new AdLoader.Builder(activity, idNativeBackup);
                        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                                if (nativeAd != null) {
                                    nativeAd.destroy();
                                }
                                nativeAd = nativeAds;
                                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                        .inflate(R.layout.admob_small_rectangle_native, null);
                                populateNativeAdView(nativeAds, adView);
                                layNative.removeAllViews();
                                layNative.addView(adView);
                            }


                        });

                        VideoOptions videoOptions = new VideoOptions.Builder()
                                .build();

                        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                                .setVideoOptions(videoOptions)
                                .build();

                        builder.withNativeAdOptions(adOptions);


                        AdRequest request = new AdRequest.Builder()
                                .build();
                        AdLoader adLoader =
                                builder
                                        .withAdListener(
                                                new AdListener() {
                                                    @Override
                                                    public void onAdFailedToLoad(LoadAdError loadAdError) {

                                                    }
                                                })
                                        .build();
                        adLoader.loadAd(request);
                        break;
                    case "MOPUB":
                    case "UNITY":
                        break;
                    case "IRON":
                        break;
                    case "FACEBOOK":
                        nativeAdfan = new com.facebook.ads.NativeAd(activity, idNativeBackup);
                        NativeAdListener nativeAdListener = new NativeAdListener() {
                            @Override
                            public void onMediaDownloaded(Ad ad) {

                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (nativeAdfan == null || nativeAdfan != ad) {
                                    return;
                                }
                                inflateAd3(nativeAdfan, activity, layNative);
                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        };

                        nativeAdfan.loadAd(
                                nativeAdfan.buildLoadAdConfig()
                                        .withAdListener(nativeAdListener)
                                        .build());
                        break;
                    case "APPLOVIN-D":
                        break;
                    case "APPLOVIN-M":
                        MaxNativeAdViewBinder binder = new MaxNativeAdViewBinder.Builder(R.layout.max_small_rectangle_native)
                                .setTitleTextViewId(R.id.title_text_view)
                                .setBodyTextViewId(R.id.body_text_view)
                                .setAdvertiserTextViewId(R.id.advertiser_textView)
                                .setIconImageViewId(R.id.icon_image_view)
                                .setMediaContentViewGroupId(R.id.media_view_container)
                                .setOptionsContentViewGroupId(R.id.ad_options_view)
                                .setCallToActionButtonId(R.id.cta_button)
                                .build();
                        nativeAdView = new MaxNativeAdView(binder, activity);

                        nativeAdLoader = new MaxNativeAdLoader(idNativeBackup, activity);
                        nativeAdLoader.setRevenueListener(new MaxAdRevenueListener() {
                            @Override
                            public void onAdRevenuePaid(MaxAd ad) {

                            }
                        });
                        nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
                            @Override
                            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {

                                if (nativeAd != null) {
                                    nativeAd.destroy();
                                }
                                // Cleanup any pre-existing native ad to prevent memory leaks.
                                if (nativeAdMax != null) {
                                    nativeAdLoader.destroy(nativeAdMax);
                                }

                                // Save ad for cleanup.
                                nativeAdMax = ad;

                                // Add ad view to view.
                                layNative.removeAllViews();
                                layNative.addView(nativeAdView);
                            }

                            @Override
                            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {

                            }

                            @Override
                            public void onNativeAdClicked(final MaxAd ad) {

                            }
                        });

                        nativeAdLoader.loadAd(nativeAdView);
                        break;
                    case "ALIEN-M":
                        AlienMediationAds.RectangleNatives(activity,layNative,idNativeBackup);
                        break;
                }
            }

        };
        startAppNativeAd.loadAd(adListener);
        layNative.addView(adViewNative);
    }

    public static void SmallNativeAdmobRectangle(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup, String Hpk1,
                                         String Hpk2, String Hpk3, String Hpk4, String Hpk5) {

        AdLoader.Builder builder = new AdLoader.Builder(activity, nativeId);
        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
            @Override
            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                if (onLoadMediumNativesAdmob!=null){
                    onLoadMediumNativesAdmob.onNativeAdLoaded();
                }
                if (nativeAd != null) {
                    nativeAd.destroy();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        if (nativeAdMax != null) {
                            nativeAdLoader.destroy(nativeAdMax);
                        }
                        break;
                    case "MOPUB":

                        break;
                    case "IRON":
                        if (adViewIron != null) {
                            adViewIron.isDestroyed();
                        }
                        break;
                    case "STARTAPP":
                        break;
                    case "APPLOVIN-D":
                        if (adViewDiscovery != null) {
                            adViewDiscovery.destroy();
                        }
                        break;
                    case "FACEBOOK":
                        if (nativeAdfan != null) {
                            nativeAdfan.destroy();
                        }
                        break;
                }
                nativeAd = nativeAds;
                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                        .inflate(R.layout.admob_small_rectangle_native, null);
                populateNativeAdView(nativeAds, adView);
                layNative.removeAllViews();
                layNative.addView(adView);
            }


        });

        VideoOptions videoOptions = new VideoOptions.Builder()
                .build();

        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                .setVideoOptions(videoOptions)
                .build();

        builder.withNativeAdOptions(adOptions);

        AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                .build();
        AdLoader adLoader =
                builder
                        .withAdListener(
                                new AdListener() {
                                    @Override
                                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                                        if (onLoadMediumNativesAdmob!=null){
                                            onLoadMediumNativesAdmob.onAdFailedToLoad("");
                                        }
                                        switch (selectAdsBackup) {

                                            case "APPLOVIN-M": {
                                                MaxNativeAdViewBinder binder = new MaxNativeAdViewBinder.Builder(R.layout.max_small_rectangle_native)
                                                        .setTitleTextViewId(R.id.title_text_view)
                                                        .setBodyTextViewId(R.id.body_text_view)
                                                        .setAdvertiserTextViewId(R.id.advertiser_textView)
                                                        .setIconImageViewId(R.id.icon_image_view)
                                                        .setMediaContentViewGroupId(R.id.media_view_container)
                                                        .setOptionsContentViewGroupId(R.id.ad_options_view)
                                                        .setCallToActionButtonId(R.id.cta_button)
                                                        .build();
                                                nativeAdView = new MaxNativeAdView(binder, activity);

                                                nativeAdLoader = new MaxNativeAdLoader(idNativeBackup, activity);
                                                nativeAdLoader.setRevenueListener(new MaxAdRevenueListener() {
                                                    @Override
                                                    public void onAdRevenuePaid(MaxAd ad) {

                                                    }
                                                });
                                                nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
                                                    @Override
                                                    public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {

                                                        if (nativeAd != null) {
                                                            nativeAd.destroy();
                                                        }
                                                        // Cleanup any pre-existing native ad to prevent memory leaks.
                                                        if (nativeAdMax != null) {
                                                            nativeAdLoader.destroy(nativeAdMax);
                                                        }

                                                        // Save ad for cleanup.
                                                        nativeAdMax = ad;

                                                        // Add ad view to view.
                                                        layNative.removeAllViews();
                                                        layNative.addView(nativeAdView);
                                                    }

                                                    @Override
                                                    public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {

                                                    }

                                                    @Override
                                                    public void onNativeAdClicked(final MaxAd ad) {

                                                    }
                                                });

                                                nativeAdLoader.loadAd(nativeAdView);
                                                break;
                                            }
                                            case "MOPUB":
                                            case "UNITY":

                                                break;
                                            case "IRON":

                                                break;
                                            case "STARTAPP":
                                                startAppNativeAd = new StartAppNativeAd(activity);
                                                View adViewNative = (View) activity.getLayoutInflater()
                                                        .inflate(R.layout.startapp_small_rectangle_native, null);
                                                AdEventListener adListener = new AdEventListener() {
                                                    @Override
                                                    public void onReceiveAd(@NonNull com.startapp.sdk.adsbase.Ad ad) {
                                                        ArrayList ads = startAppNativeAd.getNativeAds();    // get NativeAds list
                                                        Iterator iterator = ads.iterator();
                                                        while (iterator.hasNext()) {
                                                            Log.d("MyApplication", iterator.next().toString());
                                                        }
                                                        NativeAdDetails adDetails = (NativeAdDetails) ads.get(0);
                                                        if (adDetails != null) {
                                                            TextView title = adViewNative.findViewById(R.id.ad_headline);
                                                            title.setText(adDetails.getTitle());
                                                            ImageView icon = adViewNative.findViewById(R.id.ad_app_icon);
                                                            Glide.with(activity).load(adDetails.getSecondaryImageUrl()).into(icon);
                                                            ImageView details = adViewNative.findViewById(R.id.imgDetail);
                                                            Glide.with(activity).load(adDetails.getImageUrl()).into(details);
                                                            TextView description = adViewNative.findViewById(R.id.ad_body);
                                                            description.setText(adDetails.getDescription());
                                                            Button open = adViewNative.findViewById(R.id.ad_call_to_action);
                                                            open.setText(adDetails.isApp() ? "Install" : "Open");
                                                            adDetails.registerViewForInteraction(adViewNative);
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailedToReceiveAd(@Nullable com.startapp.sdk.adsbase.Ad ad) {

                                                    }

                                                };
                                                startAppNativeAd.loadAd(adListener);
                                                layNative.addView(adViewNative);
                                                break;
                                            case "APPLOVIN-D":
                                                break;
                                            case "FACEBOOK":
                                                nativeAdfan = new com.facebook.ads.NativeAd(activity, idNativeBackup);
                                                NativeAdListener nativeAdListener = new NativeAdListener() {
                                                    @Override
                                                    public void onMediaDownloaded(Ad ad) {

                                                    }

                                                    @Override
                                                    public void onError(Ad ad, AdError adError) {

                                                    }

                                                    @Override
                                                    public void onAdLoaded(Ad ad) {
                                                        if (nativeAdfan == null || nativeAdfan != ad) {
                                                            return;
                                                        }
                                                        inflateAd3(nativeAdfan, activity, layNative);
                                                    }

                                                    @Override
                                                    public void onAdClicked(Ad ad) {

                                                    }

                                                    @Override
                                                    public void onLoggingImpression(Ad ad) {

                                                    }
                                                };

                                                nativeAdfan.loadAd(
                                                        nativeAdfan.buildLoadAdConfig()
                                                                .withAdListener(nativeAdListener)
                                                                .build());

                                                break;
                                            case "ALIEN-M":
                                                AlienMediationAds.RectangleNatives(activity,layNative,idNativeBackup);
                                                break;
                                            case "ADMOB":
                                                AdLoader.Builder builder = new AdLoader.Builder(activity, idNativeBackup);
                                                builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                                                    @Override
                                                    public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                                                        if (nativeAd2 != null) {
                                                            nativeAd2.destroy();
                                                        }
                                                        nativeAd2 = nativeAds;
                                                        NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                                                .inflate(R.layout.admob_small_rectangle_native, null);
                                                        populateNativeAdView(nativeAds, adView);
                                                        layNative.removeAllViews();
                                                        layNative.addView(adView);
                                                    }


                                                });

                                                VideoOptions videoOptions = new VideoOptions.Builder()
                                                        .build();

                                                NativeAdOptions adOptions = new NativeAdOptions.Builder()
                                                        .setVideoOptions(videoOptions)
                                                        .build();

                                                builder.withNativeAdOptions(adOptions);


                                                AdRequest request = new AdRequest.Builder()
                                                        .build();
                                                AdLoader adLoader =
                                                        builder
                                                                .withAdListener(
                                                                        new AdListener() {
                                                                            @Override
                                                                            public void onAdFailedToLoad(LoadAdError loadAdError) {

                                                                            }
                                                                        })
                                                                .build();
                                                adLoader.loadAd(request);
                                                break;
                                        }
                                    }
                                })
                        .build();
        adLoader.loadAd(request);
    }

    public static void SmallNativeMaxRectangle(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {

        MaxNativeAdViewBinder binder = new MaxNativeAdViewBinder.Builder(R.layout.max_small_rectangle_native)
                .setTitleTextViewId(R.id.title_text_view)
                .setBodyTextViewId(R.id.body_text_view)
                .setAdvertiserTextViewId(R.id.advertiser_textView)
                .setIconImageViewId(R.id.icon_image_view)
                .setMediaContentViewGroupId(R.id.media_view_container)
                .setOptionsContentViewGroupId(R.id.ad_options_view)
                .setCallToActionButtonId(R.id.cta_button)
                .build();
        nativeAdView = new MaxNativeAdView(binder, activity);

        nativeAdLoader = new MaxNativeAdLoader(nativeId, activity);
        nativeAdLoader.setRevenueListener(new MaxAdRevenueListener() {
            @Override
            public void onAdRevenuePaid(MaxAd ad) {

            }
        });
        nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                if (onLoadMediumNativesApplovinMax!=null){
                    onLoadMediumNativesApplovinMax.onNativeAdLoaded();
                }
                switch (selectAdsBackup) {
                    case "ADMOB":
                        if (nativeAd != null) {
                            nativeAd.destroy();
                        }
                        break;
                    case "MOPUB":

                        break;
                    case "IRON":
                        if (adViewIron != null) {
                            adViewIron.isDestroyed();
                        }
                        break;
                    case "STARTAPP":

                        break;
                    case "APPLOVIN-D":
                        if (adViewDiscovery != null) {
                            adViewDiscovery.destroy();
                        }
                        break;
                    case "FACEBOOK":
                        if (nativeAdfan != null) {
                            nativeAdfan.destroy();
                        }
                        break;
                }
                if (nativeAdMax != null) {
                    nativeAdLoader.destroy(nativeAdMax);
                }
                nativeAdMax = ad;
                layNative.removeAllViews();
                layNative.addView(nativeAdView);
            }

            @Override
            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                if (onLoadMediumNativesApplovinMax!=null){
                    onLoadMediumNativesApplovinMax.onNativeAdLoadFailed("");
                }
                switch (selectAdsBackup) {
                    case "ADMOB": {
                        AdLoader.Builder builder = new AdLoader.Builder(activity,idNativeBackup);
                        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                                if (nativeAd != null) {
                                    nativeAd.destroy();
                                }
                                nativeAd = nativeAds;
                                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                        .inflate(R.layout.admob_small_rectangle_native, null);
                                populateNativeAdView(nativeAds, adView);
                                layNative.removeAllViews();
                                layNative.addView(adView);
                            }


                        });

                        VideoOptions videoOptions = new VideoOptions.Builder()
                                .build();

                        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                                .setVideoOptions(videoOptions)
                                .build();

                        builder.withNativeAdOptions(adOptions);



                        AdRequest request = new AdRequest.Builder()
                                .build();
                        AdLoader adLoader =
                                builder
                                        .withAdListener(
                                                new AdListener() {
                                                    @Override
                                                    public void onAdFailedToLoad(LoadAdError loadAdError) {

                                                    }
                                                })
                                        .build();
                        adLoader.loadAd(request);
                        break;
                    }
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "IRON":
                        break;
                    case "STARTAPP":
                        startAppNativeAd = new StartAppNativeAd(activity);
                        View adViewNative = (View) activity.getLayoutInflater()
                                .inflate(R.layout.startapp_small_rectangle_native, null);
                        AdEventListener adListener = new AdEventListener() {
                            @Override
                            public void onReceiveAd(@NonNull com.startapp.sdk.adsbase.Ad ad) {
                                ArrayList ads = startAppNativeAd.getNativeAds();    // get NativeAds list
                                Iterator iterator = ads.iterator();
                                while (iterator.hasNext()) {
                                    Log.d("MyApplication", iterator.next().toString());
                                }
                                NativeAdDetails adDetails = (NativeAdDetails) ads.get(0);
                                if (adDetails != null) {
                                    TextView title = adViewNative.findViewById(R.id.ad_headline);
                                    title.setText(adDetails.getTitle());
                                    ImageView icon = adViewNative.findViewById(R.id.ad_app_icon);
                                    Glide.with(activity).load(adDetails.getSecondaryImageUrl()).into(icon);
                                    ImageView details = adViewNative.findViewById(R.id.imgDetail);
                                    Glide.with(activity).load(adDetails.getImageUrl()).into(details);
                                    TextView description = adViewNative.findViewById(R.id.ad_body);
                                    description.setText(adDetails.getDescription());
                                    Button open = adViewNative.findViewById(R.id.ad_call_to_action);
                                    open.setText(adDetails.isApp() ? "Install" : "Open");
                                    adDetails.registerViewForInteraction(adViewNative);
                                }
                            }

                            @Override
                            public void onFailedToReceiveAd(@Nullable com.startapp.sdk.adsbase.Ad ad) {

                            }

                        };
                        startAppNativeAd.loadAd(adListener);
                        layNative.addView(adViewNative);
                        break;
                    case "APPLOVIN-D":

                        break;
                    case "ALIEN-M":
                        AlienMediationAds.RectangleNatives(activity,layNative,idNativeBackup);
                        break;
                    case "FACEBOOK":
                        nativeAdfan = new com.facebook.ads.NativeAd(activity, idNativeBackup);
                        NativeAdListener nativeAdListener = new NativeAdListener() {
                            @Override
                            public void onMediaDownloaded(Ad ad) {

                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (nativeAdfan == null || nativeAdfan != ad) {
                                    return;
                                }
                                inflateAd3(nativeAdfan, activity, layNative);
                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        };

                        nativeAdfan.loadAd(
                                nativeAdfan.buildLoadAdConfig()
                                        .withAdListener(nativeAdListener)
                                        .build());

                        break;
                }
            }

            @Override
            public void onNativeAdClicked(final MaxAd ad) {
                if (onLoadMediumNativesApplovinMax!=null){
                    onLoadMediumNativesApplovinMax.onNativeAdClicked();
                }
            }
        });
        nativeAdLoader.loadAd(nativeAdView);

    }

    public static void SmallNativeFanRectangle(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {
        nativeAdfan = new com.facebook.ads.NativeAd(activity, nativeId);
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {
                if (onLoadMediumNativesFacebook!=null){
                    onLoadMediumNativesFacebook.onMediaDownloaded();
                }
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                if (onLoadMediumNativesFacebook!=null){
                    onLoadMediumNativesFacebook.onError("");
                }
                switch (selectAdsBackup) {
                    case "FACEBOOK":
                        nativeAdfan2 = new com.facebook.ads.NativeAd(activity, idNativeBackup);
                        NativeAdListener nativeAdListener = new NativeAdListener() {
                            @Override
                            public void onMediaDownloaded(Ad ad) {

                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (nativeAdfan2 == null || nativeAdfan2 != ad) {
                                    return;
                                }
                                inflateAd3(nativeAdfan2, activity, layNative);
                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        };

                        nativeAdfan2.loadAd(
                                nativeAdfan2.buildLoadAdConfig()
                                        .withAdListener(nativeAdListener)
                                        .build());
                        break;
                    case "APPLOVIN-M": {
                        MaxNativeAdViewBinder binder = new MaxNativeAdViewBinder.Builder(R.layout.max_small_rectangle_native)
                                .setTitleTextViewId(R.id.title_text_view)
                                .setBodyTextViewId(R.id.body_text_view)
                                .setAdvertiserTextViewId(R.id.advertiser_textView)
                                .setIconImageViewId(R.id.icon_image_view)
                                .setMediaContentViewGroupId(R.id.media_view_container)
                                .setOptionsContentViewGroupId(R.id.ad_options_view)
                                .setCallToActionButtonId(R.id.cta_button)
                                .build();
                        nativeAdView = new MaxNativeAdView(binder, activity);

                        nativeAdLoader = new MaxNativeAdLoader(idNativeBackup, activity);
                        nativeAdLoader.setRevenueListener(new MaxAdRevenueListener() {
                            @Override
                            public void onAdRevenuePaid(MaxAd ad) {

                            }
                        });
                        nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
                            @Override
                            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                                if (nativeAd != null) {
                                    nativeAd.destroy();
                                }
                                if (nativeAdMax != null) {
                                    nativeAdLoader.destroy(nativeAdMax);
                                }
                                nativeAdMax = ad;
                                layNative.removeAllViews();
                                layNative.addView(nativeAdView);
                            }

                            @Override
                            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {

                            }

                            @Override
                            public void onNativeAdClicked(final MaxAd ad) {

                            }
                        });

                        nativeAdLoader.loadAd(nativeAdView);
                        break;
                    }
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "IRON":

                        break;
                    case "STARTAPP":
                        startAppNativeAd = new StartAppNativeAd(activity);
                        View adViewNative = (View) activity.getLayoutInflater()
                                .inflate(R.layout.startapp_small_rectangle_native, null);
                        AdEventListener adListener = new AdEventListener() {
                            @Override
                            public void onReceiveAd(@NonNull com.startapp.sdk.adsbase.Ad ad) {
                                ArrayList ads = startAppNativeAd.getNativeAds();    // get NativeAds list
                                Iterator iterator = ads.iterator();
                                while (iterator.hasNext()) {
                                    Log.d("MyApplication", iterator.next().toString());
                                }
                                NativeAdDetails adDetails = (NativeAdDetails) ads.get(0);
                                if (adDetails != null) {
                                    TextView title = adViewNative.findViewById(R.id.ad_headline);
                                    title.setText(adDetails.getTitle());
                                    ImageView icon = adViewNative.findViewById(R.id.ad_app_icon);
                                    Glide.with(activity).load(adDetails.getSecondaryImageUrl()).into(icon);
                                    ImageView details = adViewNative.findViewById(R.id.imgDetail);
                                    Glide.with(activity).load(adDetails.getImageUrl()).into(details);
                                    TextView description = adViewNative.findViewById(R.id.ad_body);
                                    description.setText(adDetails.getDescription());
                                    Button open = adViewNative.findViewById(R.id.ad_call_to_action);
                                    open.setText(adDetails.isApp() ? "Install" : "Open");
                                    adDetails.registerViewForInteraction(adViewNative);
                                }
                            }

                            @Override
                            public void onFailedToReceiveAd(@Nullable com.startapp.sdk.adsbase.Ad ad) {

                            }

                        };
                        startAppNativeAd.loadAd(adListener);
                        layNative.addView(adViewNative);
                        break;
                    case "APPLOVIN-D":

                        break;

                    case "ADMOB":
                        AdLoader.Builder builder2 = new AdLoader.Builder(activity, idNativeBackup);
                        builder2.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                                if (nativeAd != null) {
                                    nativeAd.destroy();
                                }
                                nativeAd = nativeAds;
                                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                        .inflate(R.layout.admob_small_rectangle_native, null);
                                populateNativeAdView(nativeAds, adView);
                                layNative.removeAllViews();
                                layNative.addView(adView);
                            }


                        });

                        VideoOptions videoOptions = new VideoOptions.Builder()
                                .build();

                        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                                .setVideoOptions(videoOptions)
                                .build();

                        builder2.withNativeAdOptions(adOptions);



                        AdRequest request = new AdRequest.Builder()
                                .build();
                        AdLoader adLoader =
                                builder2
                                        .withAdListener(
                                                new AdListener() {
                                                    @Override
                                                    public void onAdFailedToLoad(LoadAdError loadAdError) {

                                                    }
                                                })
                                        .build();
                        adLoader.loadAd(request);

                        break;
                    case "ALIEN-M":
                        AlienMediationAds.RectangleNatives(activity,layNative,idNativeBackup);
                        break;
                }
            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (onLoadMediumNativesFacebook!=null){
                    onLoadMediumNativesFacebook.onAdLoaded();
                }
                switch (selectAdsBackup) {
                    case "ADMOB":
                        if (nativeAd != null) {
                            nativeAd.destroy();
                        }
                        break;
                    case "MOPUB":

                        break;
                    case "IRON":
                        if (adViewIron != null) {
                            adViewIron.isDestroyed();
                        }
                        break;
                    case "STARTAPP":

                        break;
                    case "APPLOVIN-D":
                        if (adViewDiscovery != null) {
                            adViewDiscovery.destroy();
                        }
                        break;
                    case "APPLOVIN-M":
                        if (nativeAdMax != null) {
                            nativeAdLoader.destroy(nativeAdMax);
                        }
                        break;
                }
                if (nativeAdfan == null || nativeAdfan != ad) {
                    return;
                }
                inflateAd3(nativeAdfan, activity, layNative);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };

        nativeAdfan.loadAd(
                nativeAdfan.buildLoadAdConfig()
                        .withAdListener(nativeAdListener)
                        .build());

    }


    public static void SmallNativeAlienRectangle(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {
        AlienMediationAds.RectangleNatives(activity,layNative,nativeId);
        AlienMediationAds.onLoadNative = new OnLoadNative() {
            @Override
            public void onNativeAdLoaded() {
                if(onLoadMediumNativesAlien!=null){
                    onLoadMediumNativesAlien.onNativeAdLoaded();
                }
            }

            @Override
            public void onNativeAdClicked() {
                if(onLoadMediumNativesAlien!=null){
                    onLoadMediumNativesAlien.onNativeAdClicked();
                }
            }

            @Override
            public void onNativeAdFailedToLoad(String error) {
                if(onLoadMediumNativesAlien!=null){
                    onLoadMediumNativesAlien.onNativeAdFailedToLoad();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-M": {
                        MaxNativeAdViewBinder binder = new MaxNativeAdViewBinder.Builder(R.layout.max_small_rectangle_native)
                                .setTitleTextViewId(R.id.title_text_view)
                                .setBodyTextViewId(R.id.body_text_view)
                                .setAdvertiserTextViewId(R.id.advertiser_textView)
                                .setIconImageViewId(R.id.icon_image_view)
                                .setMediaContentViewGroupId(R.id.media_view_container)
                                .setOptionsContentViewGroupId(R.id.ad_options_view)
                                .setCallToActionButtonId(R.id.cta_button)
                                .build();
                        nativeAdView = new MaxNativeAdView(binder, activity);

                        nativeAdLoader = new MaxNativeAdLoader(idNativeBackup, activity);
                        nativeAdLoader.setRevenueListener(new MaxAdRevenueListener() {
                            @Override
                            public void onAdRevenuePaid(MaxAd ad) {

                            }
                        });
                        nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
                            @Override
                            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                                if (nativeAd != null) {
                                    nativeAd.destroy();
                                }
                                if (nativeAdMax != null) {
                                    nativeAdLoader.destroy(nativeAdMax);
                                }
                                nativeAdMax = ad;
                                layNative.removeAllViews();
                                layNative.addView(nativeAdView);
                            }

                            @Override
                            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {

                            }

                            @Override
                            public void onNativeAdClicked(final MaxAd ad) {

                            }
                        });

                        nativeAdLoader.loadAd(nativeAdView);
                        break;
                    }
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "IRON":
                        break;
                    case "STARTAPP":
                        startAppNativeAd = new StartAppNativeAd(activity);
                        View adViewNative = (View) activity.getLayoutInflater()
                                .inflate(R.layout.startapp_small_rectangle_native, null);
                        AdEventListener adListener = new AdEventListener() {
                            @Override
                            public void onReceiveAd(@NonNull com.startapp.sdk.adsbase.Ad ad) {
                                ArrayList ads = startAppNativeAd.getNativeAds();    // get NativeAds list
                                Iterator iterator = ads.iterator();
                                while (iterator.hasNext()) {
                                    Log.d("MyApplication", iterator.next().toString());
                                }
                                NativeAdDetails adDetails = (NativeAdDetails) ads.get(0);
                                if (adDetails != null) {
                                    TextView title = adViewNative.findViewById(R.id.ad_headline);
                                    title.setText(adDetails.getTitle());
                                    ImageView icon = adViewNative.findViewById(R.id.ad_app_icon);
                                    Glide.with(activity).load(adDetails.getSecondaryImageUrl()).into(icon);
                                    ImageView details = adViewNative.findViewById(R.id.imgDetail);
                                    Glide.with(activity).load(adDetails.getImageUrl()).into(details);
                                    TextView description = adViewNative.findViewById(R.id.ad_body);
                                    description.setText(adDetails.getDescription());
                                    Button open = adViewNative.findViewById(R.id.ad_call_to_action);
                                    open.setText(adDetails.isApp() ? "Install" : "Open");
                                    adDetails.registerViewForInteraction(adViewNative);
                                }
                            }

                            @Override
                            public void onFailedToReceiveAd(@Nullable com.startapp.sdk.adsbase.Ad ad) {

                            }

                        };
                        startAppNativeAd.loadAd(adListener);
                        layNative.addView(adViewNative);
                        break;
                    case "APPLOVIN-D":

                        break;

                    case "ADMOB":
                        AdLoader.Builder builder2 = new AdLoader.Builder(activity, idNativeBackup);
                        builder2.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                                if (nativeAd != null) {
                                    nativeAd.destroy();
                                }
                                nativeAd = nativeAds;
                                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                        .inflate(R.layout.admob_small_rectangle_native, null);
                                populateNativeAdView(nativeAds, adView);
                                layNative.removeAllViews();
                                layNative.addView(adView);
                            }


                        });

                        VideoOptions videoOptions = new VideoOptions.Builder()
                                .build();

                        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                                .setVideoOptions(videoOptions)
                                .build();

                        builder2.withNativeAdOptions(adOptions);



                        AdRequest request = new AdRequest.Builder()
                                .build();
                        AdLoader adLoader =
                                builder2
                                        .withAdListener(
                                                new AdListener() {
                                                    @Override
                                                    public void onAdFailedToLoad(LoadAdError loadAdError) {

                                                    }
                                                })
                                        .build();
                        adLoader.loadAd(request);

                        break;
                    case "FACEBOOK":
                        nativeAdfan = new com.facebook.ads.NativeAd(activity, idNativeBackup);
                        NativeAdListener nativeAdListener = new NativeAdListener() {
                            @Override
                            public void onMediaDownloaded(Ad ad) {

                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (nativeAdfan == null || nativeAdfan != ad) {
                                    return;
                                }
                                inflateAd3(nativeAdfan, activity, layNative);
                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        };

                        nativeAdfan.loadAd(
                                nativeAdfan.buildLoadAdConfig()
                                        .withAdListener(nativeAdListener)
                                        .build());
                        break;
                }

            }
        };

    }


    private static void populateNativeAdView(NativeAd nativeAd, NativeAdView adView) {
        adView.setMediaView((MediaView) adView.findViewById(R.id.ad_media));
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        adView.getMediaView().setMediaContent(nativeAd.getMediaContent());
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.GONE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.GONE);
        }
        adView.setNativeAd(nativeAd);
    }


    public static void inflateAd(NativeBannerAd nativeBannerAd, Activity activity, RelativeLayout layNative) {
        try {
            nativeBannerAd.unregisterView();
            nativeAdLayout = new NativeAdLayout(activity, null, 1);
            LayoutInflater inflater = LayoutInflater.from(activity);
            adView = (LinearLayout) inflater.inflate(R.layout.fan_small_native, nativeAdLayout, false);
            layNative.addView(adView);

            RelativeLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
            AdOptionsView adOptionsView = new AdOptionsView(activity, nativeBannerAd, nativeAdLayout);
            adChoicesContainer.removeAllViews();
            adChoicesContainer.addView(adOptionsView, 0);

            TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
            TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
            TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
            com.facebook.ads.MediaView nativeAdIconView = adView.findViewById(R.id.native_icon_view);
            Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

            nativeAdCallToAction.setText(nativeBannerAd.getAdCallToAction());
            nativeAdCallToAction.setVisibility(
                    nativeBannerAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
            nativeAdTitle.setText(nativeBannerAd.getAdvertiserName());
            nativeAdSocialContext.setText(nativeBannerAd.getAdSocialContext());
            sponsoredLabel.setText(nativeBannerAd.getSponsoredTranslation());

            List<View> clickableViews = new ArrayList<>();
            clickableViews.add(nativeAdTitle);
            clickableViews.add(nativeAdCallToAction);
            nativeBannerAd.registerViewForInteraction(adView, nativeAdIconView, clickableViews);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void inflateAd2(com.facebook.ads.NativeAd nativeAdfan, Activity activity, RelativeLayout layNative) {
        try {
            nativeAdfan.unregisterView();
            nativeAdLayout = new NativeAdLayout(activity, null, 1);
            LayoutInflater inflater = LayoutInflater.from(activity);
            adView = (LinearLayout) inflater.inflate(R.layout.fan_medium_native, nativeAdLayout, false);
            layNative.addView(adView);

            LinearLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
            AdOptionsView adOptionsView = new AdOptionsView(activity, nativeAdfan, nativeAdLayout);
            adChoicesContainer.removeAllViews();
            adChoicesContainer.addView(adOptionsView, 0);

            com.facebook.ads.MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
            TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
            com.facebook.ads.MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
            TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
            TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
            TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
            Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

            nativeAdTitle.setText(nativeAdfan.getAdvertiserName());
            nativeAdBody.setText(nativeAdfan.getAdBodyText());
            nativeAdSocialContext.setText(nativeAdfan.getAdSocialContext());
            nativeAdCallToAction.setVisibility(nativeAdfan.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
            nativeAdCallToAction.setText(nativeAdfan.getAdCallToAction());
            sponsoredLabel.setText(nativeAdfan.getSponsoredTranslation());

            List<View> clickableViews = new ArrayList<>();
            clickableViews.add(nativeAdTitle);
            clickableViews.add(nativeAdCallToAction);

            nativeAdfan.registerViewForInteraction(
                    adView, nativeAdMedia, nativeAdIcon, clickableViews);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void inflateAd3(com.facebook.ads.NativeAd nativeAdfan, Activity activity, RelativeLayout layNative) {
        try {
            nativeAdfan.unregisterView();
            nativeAdLayout = new NativeAdLayout(activity, null, 1);
            LayoutInflater inflater = LayoutInflater.from(activity);
            adView = (LinearLayout) inflater.inflate(R.layout.fan_small_rectangle_native, nativeAdLayout, false);
            layNative.addView(adView);

            LinearLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
            AdOptionsView adOptionsView = new AdOptionsView(activity, nativeAdfan, nativeAdLayout);
            adChoicesContainer.removeAllViews();
            adChoicesContainer.addView(adOptionsView, 0);

            com.facebook.ads.MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
            TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
            com.facebook.ads.MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
            TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
            TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
            TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
            Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

            nativeAdTitle.setText(nativeAdfan.getAdvertiserName());
            nativeAdBody.setText(nativeAdfan.getAdBodyText());
            nativeAdSocialContext.setText(nativeAdfan.getAdSocialContext());
            nativeAdCallToAction.setVisibility(nativeAdfan.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
            nativeAdCallToAction.setText(nativeAdfan.getAdCallToAction());
            sponsoredLabel.setText(nativeAdfan.getSponsoredTranslation());

            List<View> clickableViews = new ArrayList<>();
            clickableViews.add(nativeAdTitle);
            clickableViews.add(nativeAdCallToAction);

            nativeAdfan.registerViewForInteraction(
                    adView, nativeAdMedia, nativeAdIcon, clickableViews);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
