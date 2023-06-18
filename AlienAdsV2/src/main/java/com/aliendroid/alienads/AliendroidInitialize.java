package com.aliendroid.alienads;

import android.app.Activity;
import android.util.Log;

import com.aliendroid.alienads.config.AudienceNetworkInitializeHelper;
import com.aliendroid.sdkads.config.AppPromote;
import com.aliendroid.sdkads.config.InitializeAlienAds;
import com.applovin.sdk.AppLovinMediationProvider;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.integration.IntegrationHelper;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;
import com.wortise.ads.WortiseSdk;


import java.util.Map;


public class AliendroidInitialize {

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
                AppLovinSdk.getInstance(activity).setMediationProvider("max");
                AppLovinSdk.initializeSdk(activity, new AppLovinSdk.SdkInitializationListener() {
                    @Override
                    public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {

                    }
                });
                break;
            case "MOPUB":
            case "UNITY":
                break;
            case "IRON":
                IronSource.init(activity, idInitialize, IronSource.AD_UNIT.OFFERWALL, IronSource.AD_UNIT.INTERSTITIAL, IronSource.AD_UNIT.REWARDED_VIDEO, IronSource.AD_UNIT.BANNER);
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
            case "APPLOVIN-D":
                AppLovinSdk.initializeSdk(activity);
                break;
            case "FACEBOOK":
                if (!AudienceNetworkAds.isInitialized(activity)) {
                    if (BuildConfig.DEBUG) {
                        AdSettings.turnOnSDKDebugger(activity);
                        AdSettings.setTestMode(true);
                    }
                    AudienceNetworkAds
                            .buildInitSettings(activity)
                            .withInitListener(new AudienceNetworkInitializeHelper())
                            .initialize();
                }
                break;
            case "ALIEN-V":
                AppPromote.initializeAppPromote(activity);
                break;
            case "ALIEN-M":
                InitializeAlienAds.LoadSDK();

                break;
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
            case "WORTISE":
                WortiseSdk.initialize(activity, idInitialize);
                break;
        }
    }

    public static void SelectAdsGoogleAds(Activity activity, String selectAdsBackup, String idInitialize) {
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
                AppLovinSdk.getInstance(activity).setMediationProvider("max");
                AppLovinSdk.initializeSdk(activity, new AppLovinSdk.SdkInitializationListener() {
                    @Override
                    public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                    }
                });
                break;
            case "MOPUB":
            case "UNITY":

                break;
            case "IRON":
                IronSource.init(activity, idInitialize, IronSource.AD_UNIT.OFFERWALL, IronSource.AD_UNIT.INTERSTITIAL, IronSource.AD_UNIT.REWARDED_VIDEO, IronSource.AD_UNIT.BANNER);
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
            case "APPLOVIN-D":
                AppLovinSdk.initializeSdk(activity);
                break;
            case "FACEBOOK":
                if (!AudienceNetworkAds.isInitialized(activity)) {
                    if (BuildConfig.DEBUG) {
                        AdSettings.turnOnSDKDebugger(activity);
                        AdSettings.setTestMode(true);
                    }

                    AudienceNetworkAds
                            .buildInitSettings(activity)
                            .withInitListener(new AudienceNetworkInitializeHelper())
                            .initialize();
                }
                break;
            case "ALIEN-V":
                AppPromote.initializeAppPromote(activity);
                break;
            case "ALIEN-M":
                InitializeAlienAds.LoadSDK();
                break;
            case "WORTISE":
                WortiseSdk.initialize(activity, idInitialize);
                break;
        }
    }

    public static void SelectAdsApplovinDis(Activity activity, String selectAdsBackup, String idInitialize) {
        AppLovinSdk.initializeSdk(activity);
        switch (selectAdsBackup) {
            case "APPLOVIN-M":
                AppLovinSdk.getInstance(activity).setMediationProvider("max");
                AppLovinSdk.initializeSdk(activity, new AppLovinSdk.SdkInitializationListener() {
                    @Override
                    public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                    }
                });
                break;
            case "MOPUB":
            case "UNITY":

                break;
            case "IRON":
                IronSource.init(activity, idInitialize, IronSource.AD_UNIT.OFFERWALL, IronSource.AD_UNIT.INTERSTITIAL, IronSource.AD_UNIT.REWARDED_VIDEO, IronSource.AD_UNIT.BANNER);
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
            case "ADMOB":
            case "GOOGLE-ADS":
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
            case "FACEBOOK":
                if (!AudienceNetworkAds.isInitialized(activity)) {
                    if (BuildConfig.DEBUG) {
                        AdSettings.turnOnSDKDebugger(activity);
                        AdSettings.setTestMode(true);
                    }
                    AudienceNetworkAds
                            .buildInitSettings(activity)
                            .withInitListener(new AudienceNetworkInitializeHelper())
                            .initialize();
                }
                break;
            case "ALIEN-V":
                AppPromote.initializeAppPromote(activity);
                break;
            case "ALIEN-M":
                InitializeAlienAds.LoadSDK();
                break;
            case "WORTISE":
                WortiseSdk.initialize(activity, idInitialize);
                break;
        }
    }

    public static void SelectAdsApplovinMax(Activity activity, String selectAdsBackup, String idInitialize) {
        AppLovinSdk.getInstance(activity).setMediationProvider("max");
        AppLovinSdk.initializeSdk(activity, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
            }
        });
        switch (selectAdsBackup) {
            case "APPLOVIN-D":
                AppLovinSdk.initializeSdk(activity);
                break;
            case "MOPUB":
            case "UNITY":

                break;
            case "IRON":
                IronSource.init(activity, idInitialize, IronSource.AD_UNIT.OFFERWALL, IronSource.AD_UNIT.INTERSTITIAL, IronSource.AD_UNIT.REWARDED_VIDEO, IronSource.AD_UNIT.BANNER);
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
            case "ADMOB":
            case "GOOGLE-ADS":
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
            case "FACEBOOK":
                if (!AudienceNetworkAds.isInitialized(activity)) {
                    if (BuildConfig.DEBUG) {
                        AdSettings.turnOnSDKDebugger(activity);
                        AdSettings.setTestMode(true);
                    }
                    AudienceNetworkAds
                            .buildInitSettings(activity)
                            .withInitListener(new AudienceNetworkInitializeHelper())
                            .initialize();
                }
                break;
            case "ALIEN-V":
                AppPromote.initializeAppPromote(activity);
                break;
            case "ALIEN-M":
                InitializeAlienAds.LoadSDK();
                break;
            case "WORTISE":
                WortiseSdk.initialize(activity, idInitialize);
                break;
        }
    }

    public static void SelectAdsMopub(Activity activity, String selectAdsBackup, String idInitialize, String idInitializeBackupAds) {

    }

    public static void SelectAdsStartApp(Activity activity, String selectAdsBackup, String idInitialize, String idInitializeBackupAds) {
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
                AppLovinSdk.getInstance(activity).setMediationProvider("max");
                AppLovinSdk.initializeSdk(activity, new AppLovinSdk.SdkInitializationListener() {
                    @Override
                    public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                    }
                });
                break;
            case "IRON":
                IronSource.init(activity, idInitializeBackupAds, IronSource.AD_UNIT.OFFERWALL, IronSource.AD_UNIT.INTERSTITIAL, IronSource.AD_UNIT.REWARDED_VIDEO, IronSource.AD_UNIT.BANNER);
                IntegrationHelper.validateIntegration(activity);
                break;
            case "MOPUB":
            case "UNITY":

                break;
            case "ADMOB":
            case "GOOGLE-ADS":
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
            case "FACEBOOK":
                if (!AudienceNetworkAds.isInitialized(activity)) {
                    if (BuildConfig.DEBUG) {
                        AdSettings.turnOnSDKDebugger(activity);
                        AdSettings.setTestMode(true);
                    }

                    AudienceNetworkAds
                            .buildInitSettings(activity)
                            .withInitListener(new AudienceNetworkInitializeHelper())
                            .initialize();
                }
                break;
            case "ALIEN-V":
                AppPromote.initializeAppPromote(activity);
                break;
            case "ALIEN-M":
                InitializeAlienAds.LoadSDK();
                break;
            case "WORTISE":
                WortiseSdk.initialize(activity, idInitializeBackupAds);
                break;
        }
    }

    public static void SelectAdsIron(Activity activity, String selectAdsBackup, String idInitialize, String idInitializeBackupAds) {
        IronSource.init(activity, idInitialize, IronSource.AD_UNIT.OFFERWALL, IronSource.AD_UNIT.INTERSTITIAL, IronSource.AD_UNIT.REWARDED_VIDEO, IronSource.AD_UNIT.BANNER);
        IntegrationHelper.validateIntegration(activity);
        switch (selectAdsBackup) {
            case "APPLOVIN-D":
                AppLovinSdk.initializeSdk(activity);
                break;
            case "APPLOVIN-M":
                AppLovinSdk.getInstance(activity).setMediationProvider("max");
                AppLovinSdk.initializeSdk(activity, new AppLovinSdk.SdkInitializationListener() {
                    @Override
                    public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                    }
                });
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
            case "UNITY":

                break;
            case "ADMOB":
            case "GOOGLE-ADS":
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
            case "FACEBOOK":
                if (!AudienceNetworkAds.isInitialized(activity)) {
                    if (BuildConfig.DEBUG) {
                        AdSettings.turnOnSDKDebugger(activity);
                        AdSettings.setTestMode(true);
                    }

                    AudienceNetworkAds
                            .buildInitSettings(activity)
                            .withInitListener(new AudienceNetworkInitializeHelper())
                            .initialize();
                }
                break;
            case "ALIEN-V":
                AppPromote.initializeAppPromote(activity);
                break;
            case "ALIEN-M":
                InitializeAlienAds.LoadSDK();
                break;
            case "WORTISE":
                WortiseSdk.initialize(activity, idInitializeBackupAds);
                break;
        }
    }

    public static void SelectAdsUnity(Activity activity, String selectAdsBackup, String idInitialize, String idInitializeBackupAds) {

    }

    public static void SelectAdsFAN(Activity activity, String selectAdsBackup, String idInitializeBackupAds) {
        if (!AudienceNetworkAds.isInitialized(activity)) {
            if (BuildConfig.DEBUG) {
                AdSettings.turnOnSDKDebugger(activity);
                AdSettings.setTestMode(true);
            }
            AudienceNetworkAds
                    .buildInitSettings(activity)
                    .withInitListener(new AudienceNetworkInitializeHelper())
                    .initialize();
        }
        switch (selectAdsBackup) {
            case "APPLOVIN-D":
                AppLovinSdk.initializeSdk(activity);
                break;
            case "APPLOVIN-M":
                AppLovinSdk.getInstance(activity).setMediationProvider("max");
                AppLovinSdk.initializeSdk(activity, new AppLovinSdk.SdkInitializationListener() {
                    @Override
                    public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                    }
                });
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
            case "UNITY":
                break;
            case "ADMOB":
            case "GOOGLE-ADS":
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
            case "IRON":
                IronSource.init(activity, idInitializeBackupAds, IronSource.AD_UNIT.OFFERWALL, IronSource.AD_UNIT.INTERSTITIAL, IronSource.AD_UNIT.REWARDED_VIDEO, IronSource.AD_UNIT.BANNER);
                IntegrationHelper.validateIntegration(activity);
                break;
            case "ALIEN-V":
                AppPromote.initializeAppPromote(activity);
                break;
            case "ALIEN-M":
                InitializeAlienAds.LoadSDK();
                break;
            case "FACEBOOK":
                if (!AudienceNetworkAds.isInitialized(activity)) {
                    if (BuildConfig.DEBUG) {
                        AdSettings.turnOnSDKDebugger(activity);
                        AdSettings.setTestMode(true);
                    }
                    AudienceNetworkAds
                            .buildInitSettings(activity)
                            .withInitListener(new AudienceNetworkInitializeHelper())
                            .initialize();
                }
                break;
            case "WORTISE":
                WortiseSdk.initialize(activity, idInitializeBackupAds);
                break;
        }
    }

    public static void SelectAdsAlienView(Activity activity, String selectAdsBackup, String idInitializeBackupAds) {
        AppPromote.initializeAppPromote(activity);
        switch (selectAdsBackup) {
            case "APPLOVIN-D":
                AppLovinSdk.initializeSdk(activity);
                break;
            case "APPLOVIN-M":
                AppLovinSdk.getInstance(activity).setMediationProvider("max");
                AppLovinSdk.initializeSdk(activity, new AppLovinSdk.SdkInitializationListener() {
                    @Override
                    public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                    }
                });
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
            case "UNITY":

                break;
            case "ADMOB":
            case "GOOGLE-ADS":
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
            case "IRON":
                IronSource.init(activity, idInitializeBackupAds, IronSource.AD_UNIT.OFFERWALL, IronSource.AD_UNIT.INTERSTITIAL, IronSource.AD_UNIT.REWARDED_VIDEO, IronSource.AD_UNIT.BANNER);
                IntegrationHelper.validateIntegration(activity);
                break;
            case "FACEBOOK":
                if (!AudienceNetworkAds.isInitialized(activity)) {
                    if (BuildConfig.DEBUG) {
                        AdSettings.turnOnSDKDebugger(activity);
                        AdSettings.setTestMode(true);
                    }
                    AudienceNetworkAds
                            .buildInitSettings(activity)
                            .withInitListener(new AudienceNetworkInitializeHelper())
                            .initialize();
                }
                break;
            case "ALIEN-M":
                InitializeAlienAds.LoadSDK();
                break;
            case "WORTISE":
                WortiseSdk.initialize(activity, idInitializeBackupAds);
                break;
        }
    }

    public static void SelectAdsAlienMediation(Activity activity, String selectAdsBackup, String idInitialize, String idInitializeBackupAds) {
        InitializeAlienAds.LoadSDK();
        switch (selectAdsBackup) {
            case "APPLOVIN-D":
                AppLovinSdk.initializeSdk(activity);
                break;
            case "APPLOVIN-M":
                AppLovinSdk.getInstance(activity).setMediationProvider("max");
                AppLovinSdk.initializeSdk(activity, new AppLovinSdk.SdkInitializationListener() {
                    @Override
                    public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                    }
                });
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
            case "UNITY":

                break;
            case "ADMOB":
            case "GOOGLE-ADS":
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
            case "IRON":
                IronSource.init(activity, idInitializeBackupAds, IronSource.AD_UNIT.OFFERWALL, IronSource.AD_UNIT.INTERSTITIAL, IronSource.AD_UNIT.REWARDED_VIDEO, IronSource.AD_UNIT.BANNER);
                IntegrationHelper.validateIntegration(activity);
                break;
            case "FACEBOOK":
                if (!AudienceNetworkAds.isInitialized(activity)) {
                    if (BuildConfig.DEBUG) {
                        AdSettings.turnOnSDKDebugger(activity);
                        AdSettings.setTestMode(true);
                    }

                    AudienceNetworkAds
                            .buildInitSettings(activity)
                            .withInitListener(new AudienceNetworkInitializeHelper())
                            .initialize();
                }
                break;
            case "ALIEN-V":
                AppPromote.initializeAppPromote(activity);
                break;
            case "WORTISE":
                WortiseSdk.initialize(activity, idInitializeBackupAds);
                break;
        }

    }

    public static void SelectAdsWortise(Activity activity, String selectAdsBackup, String idInitialize, String idInitializeBackupAds) {
        WortiseSdk.initialize(activity, idInitialize);
        switch (selectAdsBackup) {
            case "APPLOVIN-D":
                AppLovinSdk.initializeSdk(activity);
                break;
            case "APPLOVIN-M":
                AppLovinSdk.getInstance(activity).setMediationProvider("max");
                AppLovinSdk.initializeSdk(activity, new AppLovinSdk.SdkInitializationListener() {
                    @Override
                    public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                    }
                });
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
            case "UNITY":

                break;
            case "ADMOB":
            case "GOOGLE-ADS":
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
            case "IRON":
                IronSource.init(activity, idInitializeBackupAds, IronSource.AD_UNIT.OFFERWALL, IronSource.AD_UNIT.INTERSTITIAL, IronSource.AD_UNIT.REWARDED_VIDEO, IronSource.AD_UNIT.BANNER);
                IntegrationHelper.validateIntegration(activity);
                break;
            case "FACEBOOK":
                if (!AudienceNetworkAds.isInitialized(activity)) {
                    if (BuildConfig.DEBUG) {
                        AdSettings.turnOnSDKDebugger(activity);
                        AdSettings.setTestMode(true);
                    }

                    AudienceNetworkAds
                            .buildInitSettings(activity)
                            .withInitListener(new AudienceNetworkInitializeHelper())
                            .initialize();
                }
                break;
            case "ALIEN-V":
                AppPromote.initializeAppPromote(activity);
                break;
            case "ALIEN-M":
                InitializeAlienAds.LoadSDK();
                break;
        }

    }


}
