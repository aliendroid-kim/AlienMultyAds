package com.aliendroid.alienads;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.applovin.sdk.AppLovinMediationProvider;
import com.applovin.sdk.AppLovinPrivacySettings;
import com.applovin.sdk.AppLovinSdk;
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
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.mediation.IImpressionListener;
import com.unity3d.mediation.IInitializationListener;
import com.unity3d.mediation.ImpressionData;
import com.unity3d.mediation.ImpressionEventPublisher;
import com.unity3d.mediation.InitializationConfiguration;
import com.unity3d.mediation.UnityMediation;
import com.unity3d.mediation.errors.SdkInitializationError;
import com.unity3d.mediation.logger.Logger;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;


public class AliendroidInitialize {
    private static IImpressionListener impressionListener;
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
                    AppLovinPrivacySettings.setHasUserConsent(true, activity);

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
            case "UNITY":
                InitializationConfiguration configuration = InitializationConfiguration.builder()
                        .setGameId(idInitialize)
                        .setInitializationListener(new IInitializationListener() {
                            @Override
                            public void onInitializationComplete() {
                                System.out.println("Unity Mediation is successfully initialized.");
                            }

                            @Override
                            public void onInitializationFailed(SdkInitializationError errorCode, String msg) {

                            }
                        }).build();

                UnityMediation.initialize(configuration);
                IUnityAdsInitializationListener unityInitilyze = new IUnityAdsInitializationListener() {
                    @Override
                    public void onInitializationComplete() {

                    }

                    @Override
                    public void onInitializationFailed(UnityAds.UnityAdsInitializationError error, String message) {

                    }
                };

                if (BuildConfig.DEBUG) {
                    UnityAds.initialize(activity, idInitialize, true, unityInitilyze );
                }


                impressionListener = new IImpressionListener() {
                    @Override
                    public void onImpression(@NonNull String adUnitId, @Nullable ImpressionData impressionData) {
                        if (impressionData == null) {
                            Logger.info("Data does not exist due to not enabling User-Level Reporting");
                        } else {
                            try {
                                Logger.info(impressionData.getJsonRepresentation().toString(2));
                            } catch (JSONException e) {
                                Logger.info("JSON could not be formatted with double indent.");
                            }
                        }
                    }
                };
                ImpressionEventPublisher.subscribe(impressionListener);

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
                AdSettings.setDataProcessingOptions(new String[]{});
                AppLovinSdk.getInstance(activity).setMediationProvider(AppLovinMediationProvider.MAX);
                AppLovinSdk sdk = AppLovinSdk.getInstance(activity);
                sdk.getSettings().setMuted(!sdk.getSettings().isMuted());
                break;
            case "MOPUB":

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
            case "UNITY":
                InitializationConfiguration configuration = InitializationConfiguration.builder()
                        .setGameId(idInitialize)
                        .setInitializationListener(new IInitializationListener() {
                            @Override
                            public void onInitializationComplete() {
                                System.out.println("Unity Mediation is successfully initialized.");
                            }

                            @Override
                            public void onInitializationFailed(SdkInitializationError errorCode, String msg) {

                            }
                        }).build();

                UnityMediation.initialize(configuration);

                IUnityAdsInitializationListener unityInitilyze = new IUnityAdsInitializationListener() {
                    @Override
                    public void onInitializationComplete() {

                    }

                    @Override
                    public void onInitializationFailed(UnityAds.UnityAdsInitializationError error, String message) {

                    }
                };

                if (BuildConfig.DEBUG) {
                    UnityAds.initialize(activity, idInitialize, true, unityInitilyze );
                }
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
            case "UNITY":
                InitializationConfiguration configuration = InitializationConfiguration.builder()
                        .setGameId(idInitialize)
                        .setInitializationListener(new IInitializationListener() {
                            @Override
                            public void onInitializationComplete() {
                                System.out.println("Unity Mediation is successfully initialized.");
                            }

                            @Override
                            public void onInitializationFailed(SdkInitializationError errorCode, String msg) {

                            }
                        }).build();

                UnityMediation.initialize(configuration);

                IUnityAdsInitializationListener unityInitilyze = new IUnityAdsInitializationListener() {
                    @Override
                    public void onInitializationComplete() {

                    }

                    @Override
                    public void onInitializationFailed(UnityAds.UnityAdsInitializationError error, String message) {

                    }
                };

                if (BuildConfig.DEBUG) {
                    UnityAds.initialize(activity, idInitialize, true, unityInitilyze );
                }
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
            case "UNITY":
                InitializationConfiguration configuration = InitializationConfiguration.builder()
                        .setGameId(idInitialize)
                        .setInitializationListener(new IInitializationListener() {
                            @Override
                            public void onInitializationComplete() {
                                System.out.println("Unity Mediation is successfully initialized.");
                            }

                            @Override
                            public void onInitializationFailed(SdkInitializationError errorCode, String msg) {

                            }
                        }).build();

                UnityMediation.initialize(configuration);

                IUnityAdsInitializationListener unityInitilyze = new IUnityAdsInitializationListener() {
                    @Override
                    public void onInitializationComplete() {

                    }

                    @Override
                    public void onInitializationFailed(UnityAds.UnityAdsInitializationError error, String message) {

                    }
                };

                if (BuildConfig.DEBUG) {
                    UnityAds.initialize(activity, idInitialize, true, unityInitilyze );
                }
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
            case "UNITY":
                InitializationConfiguration configuration = InitializationConfiguration.builder()
                        .setGameId(idInitializeBackupAds)
                        .setInitializationListener(new IInitializationListener() {
                            @Override
                            public void onInitializationComplete() {
                                System.out.println("Unity Mediation is successfully initialized.");
                            }

                            @Override
                            public void onInitializationFailed(SdkInitializationError errorCode, String msg) {

                            }
                        }).build();

                UnityMediation.initialize(configuration);

                IUnityAdsInitializationListener unityInitilyze = new IUnityAdsInitializationListener() {
                    @Override
                    public void onInitializationComplete() {

                    }

                    @Override
                    public void onInitializationFailed(UnityAds.UnityAdsInitializationError error, String message) {

                    }
                };

                if (BuildConfig.DEBUG) {
                    UnityAds.initialize(activity, idInitializeBackupAds, true, unityInitilyze );
                }
                break;
        }
    }

    public static void SelectAdsIron(Activity activity, String selectAdsBackup, String idInitialize, String idInitializeBackupAds) {
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
            case "UNITY":
                InitializationConfiguration configuration = InitializationConfiguration.builder()
                        .setGameId(idInitializeBackupAds)
                        .setInitializationListener(new IInitializationListener() {
                            @Override
                            public void onInitializationComplete() {
                                System.out.println("Unity Mediation is successfully initialized.");
                            }

                            @Override
                            public void onInitializationFailed(SdkInitializationError errorCode, String msg) {

                            }
                        }).build();

                UnityMediation.initialize(configuration);
                IUnityAdsInitializationListener unityInitilyze = new IUnityAdsInitializationListener() {
                    @Override
                    public void onInitializationComplete() {

                    }

                    @Override
                    public void onInitializationFailed(UnityAds.UnityAdsInitializationError error, String message) {

                    }
                };

                if (BuildConfig.DEBUG) {
                    UnityAds.initialize(activity, idInitializeBackupAds, true, unityInitilyze );
                }
                break;
        }
    }

    public static void SelectAdsUnity(Activity activity, String selectAdsBackup, String idInitialize, String idInitializeBackupAds) {
        InitializationConfiguration configuration = InitializationConfiguration.builder()
                .setGameId(idInitialize)
                .setInitializationListener(new IInitializationListener() {
                    @Override
                    public void onInitializationComplete() {
                        System.out.println("Unity Mediation is successfully initialized.");
                    }

                    @Override
                    public void onInitializationFailed(SdkInitializationError errorCode, String msg) {

                    }
                }).build();

        UnityMediation.initialize(configuration);
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
            case "IRON":
                IronSource.init(activity, idInitializeBackupAds);
                IntegrationHelper.validateIntegration(activity);
                break;
        }

        IUnityAdsInitializationListener unityInitilyze = new IUnityAdsInitializationListener() {
            @Override
            public void onInitializationComplete() {

            }

            @Override
            public void onInitializationFailed(UnityAds.UnityAdsInitializationError error, String message) {

            }
        };

        if (BuildConfig.DEBUG) {
            UnityAds.initialize(activity, idInitialize, true, unityInitilyze );
        }
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
                IronSource.init(activity, idInitializeBackupAds);
                IntegrationHelper.validateIntegration(activity);
                break;
            case "UNITY":
                InitializationConfiguration configuration = InitializationConfiguration.builder()
                        .setGameId(idInitializeBackupAds)
                        .setInitializationListener(new IInitializationListener() {
                            @Override
                            public void onInitializationComplete() {
                                System.out.println("Unity Mediation is successfully initialized.");
                            }

                            @Override
                            public void onInitializationFailed(SdkInitializationError errorCode, String msg) {

                            }
                        }).build();

                UnityMediation.initialize(configuration);
                if (BuildConfig.DEBUG) {
                    UnityAds.initialize(activity, idInitializeBackupAds, true, null);
                }

                break;
        }
    }


}
