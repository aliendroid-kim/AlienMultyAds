package com.aliendroid.alienads;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
import com.props.adsmanager.PropsAdsManagement;
import com.startapp.sdk.ads.nativead.NativeAdDetails;
import com.startapp.sdk.ads.nativead.StartAppNativeAd;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AliendroidNative {
    public static BannerView unityBanner;
    private static NativeAd nativeAdProps;
    private static NativeAd nativeAd;
    private static NativeAd nativeAd2;
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
                if (onLoadSmallNativesAdmob != null) {
                    onLoadSmallNativesAdmob.onNativeAdLoaded();
                }
                if (nativeAd != null) {
                    nativeAd.destroy();
                }
                switch (selectAdsBackup) {
                    case "UNITY":
                        if (unityBanner !=null){
                            unityBanner.destroy();
                        }
                        break;

                    case "STARTAPP":
                        break;
                    case "FACEBOOK":
                        if (nativeBannerAd != null) {
                            nativeBannerAd.destroy();
                        }
                        break;
                    case "ALIEN-V":
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
                                        if (onLoadSmallNativesAdmob != null) {
                                            onLoadSmallNativesAdmob.onAdFailedToLoad("");
                                        }
                                        switch (selectAdsBackup) {
                                            case "UNITY":
                                                unityBanner = new BannerView(activity, idNativeBackup, new UnityBannerSize(320, 50));
                                                unityBanner.load();
                                                layNative.addView(unityBanner);
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
                                                String getNativeId = PropsAdsManagement.getNativeAdsId(idNativeBackup);
                                                AdLoader.Builder builder3 = new AdLoader.Builder(activity, getNativeId);
                                                builder3.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                                                    @Override
                                                    public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                                                        if (nativeAdProps != null) {
                                                            nativeAdProps.destroy();
                                                        }
                                                        nativeAdProps = nativeAds;
                                                        NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                                                .inflate(R.layout.admob_small_native, null);
                                                        populateNativeAdViewProps(nativeAds, adView);
                                                        layNative.removeAllViews();
                                                        layNative.addView(adView);
                                                    }
                                                });
                                                VideoOptions videoOptions2 = new VideoOptions.Builder()
                                                        .build();
                                                NativeAdOptions adOptions2 = new NativeAdOptions.Builder()
                                                        .setVideoOptions(videoOptions2)
                                                        .build();
                                                builder3.withNativeAdOptions(adOptions2);
                                                AdRequest request2 = new AdRequest.Builder()
                                                        .build();
                                                AdLoader adLoader2 =
                                                        builder3
                                                                .withAdListener(
                                                                        new AdListener() {
                                                                            @Override
                                                                            public void onAdFailedToLoad(LoadAdError loadAdError) {

                                                                            }
                                                                        })
                                                                .build();
                                                adLoader2.loadAd(request2);
                                                break;
                                            case "ALIEN-V":

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

    public static void SmallNativeAlien(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {
        String getNativeId = PropsAdsManagement.getNativeAdsId(nativeId);
        AdLoader.Builder builder2 = new AdLoader.Builder(activity, getNativeId);
        builder2.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
            @Override
            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                if (nativeAdProps != null) {
                    nativeAdProps.destroy();
                }
                nativeAdProps = nativeAds;
                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                        .inflate(R.layout.admob_small_native, null);
                populateNativeAdViewProps(nativeAds, adView);
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
                                            case "UNITY":
                                                unityBanner = new BannerView(activity, idNativeBackup, new UnityBannerSize(320, 50));
                                                unityBanner.load();
                                                layNative.addView(unityBanner);
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

                                                break;
                                        }
                                    }
                                })
                        .build();
        adLoader.loadAd(request);

    }

    public static void SmallNativeAlienView(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {
    }

    public static void SmallNativeMax(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {

    }

    public static void SmallNativeFan(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {
        nativeBannerAd = new NativeBannerAd(activity, nativeId);
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {
                if (onLoadSmallNativesFacebook != null) {
                    onLoadSmallNativesFacebook.onMediaDownloaded();
                }
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                if (onLoadSmallNativesFacebook != null) {
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
                    case "UNITY":
                        unityBanner = new BannerView(activity, idNativeBackup, new UnityBannerSize(320, 50));
                        unityBanner.load();
                        layNative.addView(unityBanner);
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
                    case "ALIEN-M":
                        String getNativeId = PropsAdsManagement.getNativeAdsId(idNativeBackup);
                        AdLoader.Builder builder3 = new AdLoader.Builder(activity, getNativeId);
                        builder3.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                                if (nativeAdProps != null) {
                                    nativeAdProps.destroy();
                                }
                                nativeAdProps = nativeAds;
                                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                        .inflate(R.layout.admob_small_native, null);
                                populateNativeAdViewProps(nativeAds, adView);
                                layNative.removeAllViews();
                                layNative.addView(adView);
                            }
                        });
                        VideoOptions videoOptions2 = new VideoOptions.Builder()
                                .build();
                        NativeAdOptions adOptions2 = new NativeAdOptions.Builder()
                                .setVideoOptions(videoOptions2)
                                .build();
                        builder3.withNativeAdOptions(adOptions2);
                        AdRequest request2 = new AdRequest.Builder()
                                .build();
                        AdLoader adLoader2 =
                                builder3
                                        .withAdListener(
                                                new AdListener() {
                                                    @Override
                                                    public void onAdFailedToLoad(LoadAdError loadAdError) {

                                                    }
                                                })
                                        .build();
                        adLoader2.loadAd(request2);
                        break;
                    case "ALIEN-V":

                        break;
                }
            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (onLoadSmallNativesFacebook != null) {
                    onLoadSmallNativesFacebook.onAdLoaded();
                }
                switch (selectAdsBackup) {
                    case "UNITY":
                        if (unityBanner !=null){
                            unityBanner.destroy();
                        }
                        break;
                    case "STARTAPP":
                        break;
                    case "ADMOB":
                        if (nativeAd != null) {
                            nativeAd.destroy();
                        }
                        break;
                    case "ALIEN-V":
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
                if (onLoadSmallNativesStartApp != null) {
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
                if (onLoadSmallNativesStartApp != null) {
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
                    case "UNITY":
                        unityBanner = new BannerView(activity, idNativeBackup, new UnityBannerSize(320, 50));
                        unityBanner.load();
                        layNative.addView(unityBanner);
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
                        String getNativeId = PropsAdsManagement.getNativeAdsId(idNativeBackup);
                        AdLoader.Builder builder3 = new AdLoader.Builder(activity, getNativeId);
                        builder3.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                                if (nativeAdProps != null) {
                                    nativeAdProps.destroy();
                                }
                                nativeAdProps = nativeAds;
                                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                        .inflate(R.layout.admob_small_native, null);
                                populateNativeAdViewProps(nativeAds, adView);
                                layNative.removeAllViews();
                                layNative.addView(adView);
                            }
                        });
                        VideoOptions videoOptions2 = new VideoOptions.Builder()
                                .build();
                        NativeAdOptions adOptions2 = new NativeAdOptions.Builder()
                                .setVideoOptions(videoOptions2)
                                .build();
                        builder3.withNativeAdOptions(adOptions2);
                        AdRequest request2 = new AdRequest.Builder()
                                .build();
                        AdLoader adLoader2 =
                                builder3
                                        .withAdListener(
                                                new AdListener() {
                                                    @Override
                                                    public void onAdFailedToLoad(LoadAdError loadAdError) {

                                                    }
                                                })
                                        .build();
                        adLoader2.loadAd(request2);
                        break;
                    case "ALIEN-V":

                        break;
                }
            }

        };
        startAppNativeAd.loadAd(adListener);
        layNative.addView(adViewNative);
    }

    public static void SmallNativeWortise(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup
    ) {
    }

    public static void MediumNativeStartApp(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {
        startAppNativeAd = new StartAppNativeAd(activity);
        View adViewNative = (View) activity.getLayoutInflater()
                .inflate(R.layout.startapp_medium_native, null);
        AdEventListener adListener = new AdEventListener() {
            @Override
            public void onReceiveAd(@NonNull com.startapp.sdk.adsbase.Ad ad) {
                if (onLoadMediumNativesStartApp != null) {
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
                if (onLoadMediumNativesStartApp != null) {
                    onLoadMediumNativesStartApp.onFailedToReceiveAd("");
                }
                switch (selectAdsBackup) {
                    case "ALIEN-V":

                        break;
                    case "ADMOB": {
                        AdLoader.Builder builder = new AdLoader.Builder(activity, idNativeBackup);
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
                    case "UNITY":
                        unityBanner = new BannerView(activity, idNativeBackup, new UnityBannerSize(300, 250));
                        unityBanner.load();
                        layNative.addView(unityBanner);
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
                        String getNativeId = PropsAdsManagement.getNativeAdsId(idNativeBackup);
                        AdLoader.Builder builder3 = new AdLoader.Builder(activity, getNativeId);
                        builder3.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                                if (nativeAdProps != null) {
                                    nativeAdProps.destroy();
                                }
                                nativeAdProps = nativeAds;
                                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                        .inflate(R.layout.admob_big_native, null);
                                populateNativeAdViewProps(nativeAds, adView);
                                layNative.removeAllViews();
                                layNative.addView(adView);
                            }
                        });
                        VideoOptions videoOptions2 = new VideoOptions.Builder()
                                .build();
                        NativeAdOptions adOptions2 = new NativeAdOptions.Builder()
                                .setVideoOptions(videoOptions2)
                                .build();
                        builder3.withNativeAdOptions(adOptions2);
                        AdRequest request2 = new AdRequest.Builder()
                                .build();
                        AdLoader adLoader2 =
                                builder3
                                        .withAdListener(
                                                new AdListener() {
                                                    @Override
                                                    public void onAdFailedToLoad(LoadAdError loadAdError) {

                                                    }
                                                })
                                        .build();
                        adLoader2.loadAd(request2);
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
                if (onLoadMediumNativesAdmob != null) {
                    onLoadMediumNativesAdmob.onNativeAdLoaded();
                }
                if (nativeAd != null) {
                    nativeAd.destroy();
                }
                switch (selectAdsBackup) {
                    case "STARTAPP":
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
                                        if (onLoadMediumNativesAdmob != null) {
                                            onLoadMediumNativesAdmob.onAdFailedToLoad("");
                                        }
                                        switch (selectAdsBackup) {
                                            case "UNITY":
                                                unityBanner = new BannerView(activity, idNativeBackup, new UnityBannerSize(300, 250));
                                                unityBanner.load();
                                                layNative.addView(unityBanner);
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
                                                String getNativeId = PropsAdsManagement.getNativeAdsId(idNativeBackup);
                                                AdLoader.Builder builder3 = new AdLoader.Builder(activity, getNativeId);
                                                builder3.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                                                    @Override
                                                    public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                                                        if (nativeAdProps != null) {
                                                            nativeAdProps.destroy();
                                                        }
                                                        nativeAdProps = nativeAds;
                                                        NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                                                .inflate(R.layout.admob_big_native, null);
                                                        populateNativeAdViewProps(nativeAds, adView);
                                                        layNative.removeAllViews();
                                                        layNative.addView(adView);
                                                    }
                                                });
                                                VideoOptions videoOptions2 = new VideoOptions.Builder()
                                                        .build();
                                                NativeAdOptions adOptions2 = new NativeAdOptions.Builder()
                                                        .setVideoOptions(videoOptions2)
                                                        .build();
                                                builder3.withNativeAdOptions(adOptions2);
                                                AdRequest request2 = new AdRequest.Builder()
                                                        .build();
                                                AdLoader adLoader2 =
                                                        builder3
                                                                .withAdListener(
                                                                        new AdListener() {
                                                                            @Override
                                                                            public void onAdFailedToLoad(LoadAdError loadAdError) {
                                                                            }
                                                                        })
                                                                .build();
                                                adLoader2.loadAd(request2);
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
    }

    public static void MediumNativeFan(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {
        nativeAdfan = new com.facebook.ads.NativeAd(activity, nativeId);
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {
                if (onLoadMediumNativesFacebook != null) {
                    onLoadMediumNativesFacebook.onMediaDownloaded();
                }
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                if (onLoadMediumNativesFacebook != null) {
                    onLoadMediumNativesFacebook.onError("");
                }
                switch (selectAdsBackup) {
                    case "ALIEN-V":

                        break;
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
                    case "UNITY":
                        unityBanner = new BannerView(activity, idNativeBackup, new UnityBannerSize(300, 250));
                        unityBanner.load();
                        layNative.addView(unityBanner);
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
                        String getNativeId = PropsAdsManagement.getNativeAdsId(idNativeBackup);
                        AdLoader.Builder builder3 = new AdLoader.Builder(activity, getNativeId);
                        builder3.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                                if (nativeAdProps != null) {
                                    nativeAdProps.destroy();
                                }
                                nativeAdProps = nativeAds;
                                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                        .inflate(R.layout.admob_big_native, null);
                                populateNativeAdViewProps(nativeAds, adView);
                                layNative.removeAllViews();
                                layNative.addView(adView);
                            }
                        });
                        VideoOptions videoOptions2 = new VideoOptions.Builder()
                                .build();
                        NativeAdOptions adOptions2 = new NativeAdOptions.Builder()
                                .setVideoOptions(videoOptions2)
                                .build();
                        builder3.withNativeAdOptions(adOptions2);
                        AdRequest request2 = new AdRequest.Builder()
                                .build();
                        AdLoader adLoader2 =
                                builder3
                                        .withAdListener(
                                                new AdListener() {
                                                    @Override
                                                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                                                    }
                                                })
                                        .build();
                        adLoader2.loadAd(request2);
                        break;
                }
            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (onLoadMediumNativesFacebook != null) {
                    onLoadMediumNativesFacebook.onAdLoaded();
                }
                switch (selectAdsBackup) {
                    case "ADMOB":
                        if (nativeAd != null) {
                            nativeAd.destroy();
                        }
                        break;
                    case "STARTAPP":
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

        String getNativeId = PropsAdsManagement.getNativeAdsId(nativeId);
        AdLoader.Builder builder3 = new AdLoader.Builder(activity, getNativeId);
        builder3.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
            @Override
            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                if (nativeAdProps != null) {
                    nativeAdProps.destroy();
                }
                nativeAdProps = nativeAds;
                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                        .inflate(R.layout.admob_big_native, null);
                populateNativeAdViewProps(nativeAds, adView);
                layNative.removeAllViews();
                layNative.addView(adView);
            }
        });
        VideoOptions videoOptions2 = new VideoOptions.Builder()
                .build();
        NativeAdOptions adOptions2 = new NativeAdOptions.Builder()
                .setVideoOptions(videoOptions2)
                .build();
        builder3.withNativeAdOptions(adOptions2);
        AdRequest request2 = new AdRequest.Builder()
                .build();
        AdLoader adLoader2 =
                builder3
                        .withAdListener(
                                new AdListener() {
                                    @Override
                                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                                        switch (selectAdsBackup) {
                                            case "UNITY":
                                                unityBanner = new BannerView(activity, idNativeBackup, new UnityBannerSize(300, 250));
                                                unityBanner.load();
                                                layNative.addView(unityBanner);
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

                                            case "ALIEN-V":

                                                break;
                                        }
                                    }
                                })
                        .build();
        adLoader2.loadAd(request2);

    }

    public static void MediumNativeAlienView(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {
    }

    public static void MediumNativeWortise(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {

    }

    public static void SmallNativeStartAppRectangle(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {
        startAppNativeAd = new StartAppNativeAd(activity);
        View adViewNative = (View) activity.getLayoutInflater()
                .inflate(R.layout.startapp_small_rectangle_native, null);
        AdEventListener adListener = new AdEventListener() {
            @Override
            public void onReceiveAd(@NonNull com.startapp.sdk.adsbase.Ad ad) {
                if (onLoadMediumNativesStartApp != null) {
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
                if (onLoadMediumNativesStartApp != null) {
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
                    case "UNITY":
                        unityBanner = new BannerView(activity, idNativeBackup, new UnityBannerSize(320, 50));
                        unityBanner.load();
                        layNative.addView(unityBanner);
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
                                inflateAd3(nativeBannerAd, activity, layNative);
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
                        String getNativeId = PropsAdsManagement.getNativeAdsId(idNativeBackup);
                        AdLoader.Builder builder3 = new AdLoader.Builder(activity, getNativeId);
                        builder3.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                                if (nativeAdProps != null) {
                                    nativeAdProps.destroy();
                                }
                                nativeAdProps = nativeAds;
                                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                        .inflate(R.layout.admob_small_rectangle_native, null);
                                populateNativeAdViewProps(nativeAds, adView);
                                layNative.removeAllViews();
                                layNative.addView(adView);
                            }
                        });
                        VideoOptions videoOptions2 = new VideoOptions.Builder()
                                .build();
                        NativeAdOptions adOptions2 = new NativeAdOptions.Builder()
                                .setVideoOptions(videoOptions2)
                                .build();
                        builder3.withNativeAdOptions(adOptions2);
                        AdRequest request2 = new AdRequest.Builder()
                                .build();
                        AdLoader adLoader2 =
                                builder3
                                        .withAdListener(
                                                new AdListener() {
                                                    @Override
                                                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                                                    }
                                                })
                                        .build();
                        adLoader2.loadAd(request2);
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
                if (onLoadMediumNativesAdmob != null) {
                    onLoadMediumNativesAdmob.onNativeAdLoaded();
                }
                if (nativeAd != null) {
                    nativeAd.destroy();
                }
                switch (selectAdsBackup) {
                    case "STARTAPP":
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
                                        if (onLoadMediumNativesAdmob != null) {
                                            onLoadMediumNativesAdmob.onAdFailedToLoad("");
                                        }
                                        switch (selectAdsBackup) {
                                            case "UNITY":
                                                unityBanner = new BannerView(activity, idNativeBackup, new UnityBannerSize(320, 50));
                                                unityBanner.load();
                                                layNative.addView(unityBanner);
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
                                                        inflateAd3(nativeBannerAd, activity, layNative);
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
                                                String getNativeId = PropsAdsManagement.getNativeAdsId(idNativeBackup);
                                                AdLoader.Builder builder3 = new AdLoader.Builder(activity, getNativeId);
                                                builder3.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                                                    @Override
                                                    public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                                                        if (nativeAdProps != null) {
                                                            nativeAdProps.destroy();
                                                        }
                                                        nativeAdProps = nativeAds;
                                                        NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                                                .inflate(R.layout.admob_small_rectangle_native, null);
                                                        populateNativeAdViewProps(nativeAds, adView);
                                                        layNative.removeAllViews();
                                                        layNative.addView(adView);
                                                    }
                                                });
                                                VideoOptions videoOptions2 = new VideoOptions.Builder()
                                                        .build();
                                                NativeAdOptions adOptions2 = new NativeAdOptions.Builder()
                                                        .setVideoOptions(videoOptions2)
                                                        .build();
                                                builder3.withNativeAdOptions(adOptions2);
                                                AdRequest request2 = new AdRequest.Builder()
                                                        .build();
                                                AdLoader adLoader2 =
                                                        builder3
                                                                .withAdListener(
                                                                        new AdListener() {
                                                                            @Override
                                                                            public void onAdFailedToLoad(LoadAdError loadAdError) {
                                                                            }
                                                                        })
                                                                .build();
                                                adLoader2.loadAd(request2);
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
    }

    public static void SmallNativeFanRectangle(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {
        nativeBannerAd = new NativeBannerAd(activity, nativeId);
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {
                if (onLoadMediumNativesFacebook != null) {
                    onLoadMediumNativesFacebook.onMediaDownloaded();
                }
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                if (onLoadMediumNativesFacebook != null) {
                    onLoadMediumNativesFacebook.onError("");
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
                                inflateAd3(nativeBannerAd2, activity, layNative);
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
                    case "UNITY":
                        unityBanner = new BannerView(activity, idNativeBackup, new UnityBannerSize(320, 50));
                        unityBanner.load();
                        layNative.addView(unityBanner);
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
                        String getNativeId = PropsAdsManagement.getNativeAdsId(idNativeBackup);
                        AdLoader.Builder builder3 = new AdLoader.Builder(activity, getNativeId);
                        builder3.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                                if (nativeAdProps != null) {
                                    nativeAdProps.destroy();
                                }
                                nativeAdProps = nativeAds;
                                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                        .inflate(R.layout.admob_small_rectangle_native, null);
                                populateNativeAdViewProps(nativeAds, adView);
                                layNative.removeAllViews();
                                layNative.addView(adView);
                            }
                        });
                        VideoOptions videoOptions2 = new VideoOptions.Builder()
                                .build();
                        NativeAdOptions adOptions2 = new NativeAdOptions.Builder()
                                .setVideoOptions(videoOptions2)
                                .build();
                        builder3.withNativeAdOptions(adOptions2);
                        AdRequest request2 = new AdRequest.Builder()
                                .build();
                        AdLoader adLoader2 =
                                builder3
                                        .withAdListener(
                                                new AdListener() {
                                                    @Override
                                                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                                                    }
                                                })
                                        .build();
                        adLoader2.loadAd(request2);

                        break;
                }
            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (onLoadMediumNativesFacebook != null) {
                    onLoadMediumNativesFacebook.onAdLoaded();
                }
                switch (selectAdsBackup) {
                    case "ADMOB":
                        if (nativeAd != null) {
                            nativeAd.destroy();
                        }
                        break;
                    case "STARTAPP":
                        break;
                }
                if (nativeBannerAd == null || nativeBannerAd != ad) {
                    return;
                }
                inflateAd3(nativeBannerAd, activity, layNative);
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

    public static void SmallNativeAlienRectangle(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {

        String getNativeId = PropsAdsManagement.getNativeAdsId(nativeId);
        AdLoader.Builder builder3 = new AdLoader.Builder(activity, getNativeId);
        builder3.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
            @Override
            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                if (nativeAdProps != null) {
                    nativeAdProps.destroy();
                }
                nativeAdProps = nativeAds;
                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                        .inflate(R.layout.admob_small_rectangle_native, null);
                populateNativeAdViewProps(nativeAds, adView);
                layNative.removeAllViews();
                layNative.addView(adView);
            }
        });
        VideoOptions videoOptions2 = new VideoOptions.Builder()
                .build();
        NativeAdOptions adOptions2 = new NativeAdOptions.Builder()
                .setVideoOptions(videoOptions2)
                .build();
        builder3.withNativeAdOptions(adOptions2);
        AdRequest request2 = new AdRequest.Builder()
                .build();
        AdLoader adLoader2 =
                builder3
                        .withAdListener(
                                new AdListener() {
                                    @Override
                                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                                        switch (selectAdsBackup) {
                                            case "UNITY":
                                                unityBanner = new BannerView(activity, idNativeBackup, new UnityBannerSize(320, 50));
                                                unityBanner.load();
                                                layNative.addView(unityBanner);
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
                                                        inflateAd3(nativeBannerAd, activity, layNative);
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
                                        }
                                    }
                                })
                        .build();
        adLoader2.loadAd(request2);

    }

    public static void SmallNativeWortiseRectangle(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {

    }
    private static void populateNativeAdViewProps(NativeAd nativeAd, NativeAdView adView) {
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

    public static void inflateAd3(NativeBannerAd nativeBannerAd, Activity activity, RelativeLayout layNative) {
        try {
            nativeBannerAd.unregisterView();
            nativeAdLayout = new NativeAdLayout(activity, null, 1);
            LayoutInflater inflater = LayoutInflater.from(activity);
            adView = (LinearLayout) inflater.inflate(R.layout.fan_small_rectangle_native, nativeAdLayout, false);
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
}
