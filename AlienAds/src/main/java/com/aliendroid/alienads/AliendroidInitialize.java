package com.aliendroid.alienads;

import android.app.Activity;
import android.util.Log;

import com.applovin.sdk.AppLovinMediationProvider;
import com.applovin.sdk.AppLovinSdk;
import com.facebook.ads.AdSettings;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.integration.IntegrationHelper;
import com.mopub.common.MoPub;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.mobileads.FacebookBanner;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;

import java.util.HashMap;
import java.util.Map;


public class AliendroidInitialize {
    public static void SelectAds(Activity activity, String selectAds, String idInitialize) {
        switch (selectAds) {
            case "ADMOB":
                MobileAds.initialize(activity, new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(InitializationStatus initializationStatus) {
                        Map<String, AdapterStatus> statusMap = initializationStatus.getAdapterStatusMap();
                        for (String adapterClass : statusMap.keySet()) {
                            AdapterStatus status = statusMap.get(adapterClass);
                            Log.d("MyApp", String.format(
                                    "Adapter name: %s, Description: %s, Latency: %d",
                                    adapterClass, status.getDescription(), status.getLatency()));
                        }
                    }
                });
                break;
            case "APPLOVIN-M":
                AdSettings.setDataProcessingOptions(new String[]{});
                AppLovinSdk.getInstance(activity).setMediationProvider(AppLovinMediationProvider.MAX);
                AppLovinSdk sdk = AppLovinSdk.getInstance(activity);
                sdk.getSettings().setMuted(!sdk.getSettings().isMuted());

                break;
            case "MOPUB":
                Map<String, String> facebookBanner = new HashMap<>();
                facebookBanner.put("native_banner", "true");
                SdkConfiguration.Builder configBuilder = new SdkConfiguration.Builder(idInitialize);
                configBuilder.withMediatedNetworkConfiguration(FacebookBanner.class.getName(), facebookBanner);
                MoPub.initializeSdk(activity, configBuilder.build(), initSdkListener());
                break;
            case "IRON":
                IronSource.init(activity, idInitialize);
                IntegrationHelper.validateIntegration(activity);
                IronSource.setMetaData("Facebook_IS_CacheFlag","IMAGE");
                break;
            case "STARTAPP":
                StartAppSDK.init(activity, idInitialize, true);
                StartAppAd.disableSplash();
                break;
            case "APPLOVIN-D":
                AppLovinSdk.initializeSdk(activity);
                break;
        }
    }

    public static void SelectAdsAdmob(Activity activity, String selectAdsBackup, String idInitialize) {
        MobileAds.initialize(activity, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Map<String, AdapterStatus> statusMap = initializationStatus.getAdapterStatusMap();
                for (String adapterClass : statusMap.keySet()) {
                    AdapterStatus status = statusMap.get(adapterClass);
                    Log.d("MyApp", String.format(
                            "Adapter name: %s, Description: %s, Latency: %d",
                            adapterClass, status.getDescription(), status.getLatency()));
                }
            }
        });
        switch (selectAdsBackup) {
            case "APPLOVIN-M":
                AdSettings.setDataProcessingOptions(new String[]{});
                AppLovinSdk.getInstance(activity).setMediationProvider(AppLovinMediationProvider.MAX);
                AppLovinSdk sdk = AppLovinSdk.getInstance(activity);
                sdk.getSettings().setMuted(!sdk.getSettings().isMuted());
                break;
            case "MOPUB":
                Map<String, String> facebookBanner = new HashMap<>();
                facebookBanner.put("native_banner", "true");
                SdkConfiguration.Builder configBuilder = new SdkConfiguration.Builder(idInitialize);
                configBuilder.withMediatedNetworkConfiguration(FacebookBanner.class.getName(), facebookBanner);
                MoPub.initializeSdk(activity, configBuilder.build(), initSdkListener());
                break;
            case "IRON":
                IronSource.init(activity, idInitialize);
                IntegrationHelper.validateIntegration(activity);
                break;
            case "STARTAPP":
                StartAppSDK.init(activity, idInitialize, true);
                StartAppAd.disableSplash();
                StartAppSDK.setUserConsent(activity,
                        "pas",
                        System.currentTimeMillis(),
                        true);
                break;
            case "APPLOVIN-D" :
                AppLovinSdk.initializeSdk(activity);
                break;
        }
    }

    public static void SelectAdsApplovinDis(Activity activity, String selectAdsBackup, String idInitialize) {
        AppLovinSdk.initializeSdk(activity);
        switch (selectAdsBackup) {
            case "APPLOVIN-M":
                AdSettings.setDataProcessingOptions(new String[]{});
                AppLovinSdk.getInstance(activity).setMediationProvider(AppLovinMediationProvider.MAX);
                AppLovinSdk sdk = AppLovinSdk.getInstance(activity);
                sdk.getSettings().setMuted(!sdk.getSettings().isMuted());
                break;
            case "MOPUB":
                Map<String, String> facebookBanner = new HashMap<>();
                facebookBanner.put("native_banner", "true");
                SdkConfiguration.Builder configBuilder = new SdkConfiguration.Builder(idInitialize);
                configBuilder.withMediatedNetworkConfiguration(FacebookBanner.class.getName(), facebookBanner);
                MoPub.initializeSdk(activity, configBuilder.build(), initSdkListener());
                break;
            case "IRON":
                IronSource.init(activity, idInitialize);
                IntegrationHelper.validateIntegration(activity);
                break;
            case "STARTAPP":
                StartAppSDK.init(activity, idInitialize, true);
                StartAppAd.disableSplash();
                StartAppSDK.setUserConsent(activity,
                        "pas",
                        System.currentTimeMillis(),
                        true);
                break;
            case "ADMOB" :
                MobileAds.initialize(activity, new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(InitializationStatus initializationStatus) {
                        Map<String, AdapterStatus> statusMap = initializationStatus.getAdapterStatusMap();
                        for (String adapterClass : statusMap.keySet()) {
                            AdapterStatus status = statusMap.get(adapterClass);
                            Log.d("MyApp", String.format(
                                    "Adapter name: %s, Description: %s, Latency: %d",
                                    adapterClass, status.getDescription(), status.getLatency()));
                        }
                    }
                });
                break;
        }
    }

    public static void SelectAdsApplovinMax(Activity activity, String selectAdsBackup, String idInitialize) {
        AdSettings.setDataProcessingOptions(new String[]{});
        AppLovinSdk.getInstance(activity).setMediationProvider(AppLovinMediationProvider.MAX);
        AppLovinSdk sdk = AppLovinSdk.getInstance(activity);
        sdk.getSettings().setMuted(!sdk.getSettings().isMuted());
        switch (selectAdsBackup) {
            case "APPLOVIN-D":
                AppLovinSdk.initializeSdk(activity);
                break;
            case "MOPUB":
                Map<String, String> facebookBanner = new HashMap<>();
                facebookBanner.put("native_banner", "true");
                SdkConfiguration.Builder configBuilder = new SdkConfiguration.Builder(idInitialize);
                configBuilder.withMediatedNetworkConfiguration(FacebookBanner.class.getName(), facebookBanner);
                MoPub.initializeSdk(activity, configBuilder.build(), initSdkListener());
                break;
            case "IRON":
                IronSource.init(activity, idInitialize);
                IntegrationHelper.validateIntegration(activity);
                break;
            case "STARTAPP":
                StartAppSDK.init(activity, idInitialize, true);
                StartAppAd.disableSplash();
                StartAppSDK.setUserConsent(activity,
                        "pas",
                        System.currentTimeMillis(),
                        true);
                break;
            case "ADMOB" :
                MobileAds.initialize(activity, new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(InitializationStatus initializationStatus) {
                        Map<String, AdapterStatus> statusMap = initializationStatus.getAdapterStatusMap();
                        for (String adapterClass : statusMap.keySet()) {
                            AdapterStatus status = statusMap.get(adapterClass);
                            Log.d("MyApp", String.format(
                                    "Adapter name: %s, Description: %s, Latency: %d",
                                    adapterClass, status.getDescription(), status.getLatency()));
                        }
                    }
                });
                break;
        }
    }

    public static void SelectAdsMopub(Activity activity, String selectAdsBackup, String idInitialize, String idInitializeBackupAds) {
        Map<String, String> facebookBanner = new HashMap<>();
        facebookBanner.put("native_banner", "true");
        SdkConfiguration.Builder configBuilder = new SdkConfiguration.Builder(idInitialize);
        configBuilder.withMediatedNetworkConfiguration(FacebookBanner.class.getName(), facebookBanner);
        MoPub.initializeSdk(activity, configBuilder.build(), initSdkListener());
        switch (selectAdsBackup) {
            case "APPLOVIN-D":
                AppLovinSdk.initializeSdk(activity);
                break;
            case "APPLOVIN-M":
                AdSettings.setDataProcessingOptions(new String[]{});
                AppLovinSdk.getInstance(activity).setMediationProvider(AppLovinMediationProvider.MAX);
                AppLovinSdk sdk = AppLovinSdk.getInstance(activity);
                sdk.getSettings().setMuted(!sdk.getSettings().isMuted());
                break;
            case "IRON":
                IronSource.init(activity, idInitializeBackupAds);
                IntegrationHelper.validateIntegration(activity);
                break;
            case "STARTAPP":
                StartAppSDK.init(activity,idInitializeBackupAds, true);
                StartAppAd.disableSplash();
                StartAppSDK.setUserConsent(activity,
                        "pas",
                        System.currentTimeMillis(),
                        true);
                break;
            case "ADMOB" :
                MobileAds.initialize(activity, new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(InitializationStatus initializationStatus) {
                        Map<String, AdapterStatus> statusMap = initializationStatus.getAdapterStatusMap();
                        for (String adapterClass : statusMap.keySet()) {
                            AdapterStatus status = statusMap.get(adapterClass);
                            Log.d("MyApp", String.format(
                                    "Adapter name: %s, Description: %s, Latency: %d",
                                    adapterClass, status.getDescription(), status.getLatency()));
                        }
                    }
                });
                break;
        }
    }

    public static void SelectAdsStartApp(Activity activity, String selectAdsBackup, String idInitialize,String idInitializeBackupAds) {
        StartAppSDK.init(activity, idInitialize, true);
        StartAppAd.disableSplash();
        StartAppSDK.setUserConsent(activity,
                "pas",
                System.currentTimeMillis(),
                true);
        switch (selectAdsBackup) {
            case "APPLOVIN-D":
                AppLovinSdk.initializeSdk(activity);
                break;
            case "APPLOVIN-M":
                AdSettings.setDataProcessingOptions(new String[]{});
                AppLovinSdk.getInstance(activity).setMediationProvider(AppLovinMediationProvider.MAX);
                AppLovinSdk sdk = AppLovinSdk.getInstance(activity);
                sdk.getSettings().setMuted(!sdk.getSettings().isMuted());
                break;
            case "IRON":
                IronSource.init(activity, idInitializeBackupAds);
                IntegrationHelper.validateIntegration(activity);
                break;
            case "MOPUB":
                Map<String, String> facebookBanner = new HashMap<>();
                facebookBanner.put("native_banner", "true");
                SdkConfiguration.Builder configBuilder = new SdkConfiguration.Builder(idInitializeBackupAds);
                configBuilder.withMediatedNetworkConfiguration(FacebookBanner.class.getName(), facebookBanner);
                MoPub.initializeSdk(activity, configBuilder.build(), initSdkListener());
                break;
            case "ADMOB" :
                MobileAds.initialize(activity, new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(InitializationStatus initializationStatus) {
                        Map<String, AdapterStatus> statusMap = initializationStatus.getAdapterStatusMap();
                        for (String adapterClass : statusMap.keySet()) {
                            AdapterStatus status = statusMap.get(adapterClass);
                            Log.d("MyApp", String.format(
                                    "Adapter name: %s, Description: %s, Latency: %d",
                                    adapterClass, status.getDescription(), status.getLatency()));
                        }
                    }
                });
                break;
        }
    }

    public static void SelectAdsIron(Activity activity, String selectAdsBackup, String idInitialize,String idInitializeBackupAds) {
        IronSource.init(activity, idInitialize);
        IntegrationHelper.validateIntegration(activity);
        switch (selectAdsBackup) {
            case "APPLOVIN-D":
                AppLovinSdk.initializeSdk(activity);
                break;
            case "APPLOVIN-M":
                AdSettings.setDataProcessingOptions(new String[]{});
                AppLovinSdk.getInstance(activity).setMediationProvider(AppLovinMediationProvider.MAX);
                AppLovinSdk sdk = AppLovinSdk.getInstance(activity);
                sdk.getSettings().setMuted(!sdk.getSettings().isMuted());
                break;
            case "STARTAPP":
                StartAppSDK.init(activity, idInitializeBackupAds, true);
                StartAppAd.disableSplash();
                StartAppSDK.setUserConsent(activity,
                        "pas",
                        System.currentTimeMillis(),
                        true);
                break;
            case "MOPUB":
                Map<String, String> facebookBanner = new HashMap<>();
                facebookBanner.put("native_banner", "true");
                SdkConfiguration.Builder configBuilder = new SdkConfiguration.Builder(idInitializeBackupAds);
                configBuilder.withMediatedNetworkConfiguration(FacebookBanner.class.getName(), facebookBanner);
                MoPub.initializeSdk(activity, configBuilder.build(), initSdkListener());
                break;
            case "ADMOB" :
                MobileAds.initialize(activity, new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(InitializationStatus initializationStatus) {
                        Map<String, AdapterStatus> statusMap = initializationStatus.getAdapterStatusMap();
                        for (String adapterClass : statusMap.keySet()) {
                            AdapterStatus status = statusMap.get(adapterClass);
                            Log.d("MyApp", String.format(
                                    "Adapter name: %s, Description: %s, Latency: %d",
                                    adapterClass, status.getDescription(), status.getLatency()));
                        }
                    }
                });
                break;
        }
    }

    private static SdkInitializationListener initSdkListener() {
        return new SdkInitializationListener() {
            @Override
            public void onInitializationFinished() {
            }
        };
    }

}
