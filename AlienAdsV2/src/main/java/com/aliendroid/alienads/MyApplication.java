package com.aliendroid.alienads;

import android.app.Application;
import android.util.Log;

import com.aliendroid.sdkads.config.AppsConfig;
import com.aliendroid.sdkads.config.InitializeAlienAds;
import com.applovin.sdk.AppLovinMediationProvider;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.flurry.android.FlurryAgent;
import com.flurry.android.FlurryPerformance;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MyApplication extends Application {
    private static AlienOpenAds alienOpenAds;
    private static InitializeAlienAds sdkads;
    private static AlienNotif notif;
    private ApplovinOpenAds appOpenManager;
    //Neptunus
    @Override
    public void onCreate() {
        super.onCreate();
        AppLovinSdk.getInstance(MyApplication.this).setMediationProvider(AppLovinMediationProvider.MAX);
        AppLovinSdk.getInstance(MyApplication.this).initializeSdk(config -> {

        });
        MobileAds.initialize(
                this,
                new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(InitializationStatus initializationStatus) {
                    }
                });


        AppLovinSdk.initializeSdk( this, new AppLovinSdk.SdkInitializationListener()
        {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
            {
                appOpenManager = new ApplovinOpenAds( MyApplication.this );
            }
        } );


        new FlurryAgent.Builder()
                .withDataSaleOptOut(false)
                .withCaptureUncaughtExceptions(true)
                .withIncludeBackgroundSessionsInMetrics(true)
                .withLogLevel(Log.VERBOSE)
                .withPerformanceMetrics(FlurryPerformance.ALL)
                .build(this, AppsConfig.ANALYSKEY);

        sdkads = new InitializeAlienAds(this);
        alienOpenAds = new AlienOpenAds(this);
        notif = new AlienNotif(this);

    }
}