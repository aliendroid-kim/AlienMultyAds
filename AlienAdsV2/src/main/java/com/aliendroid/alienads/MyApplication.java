package com.aliendroid.alienads;

import static com.aliendroid.sdkads.config.AppsConfig.ALIENSDKKEY;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.aliendroid.sdkads.config.AppsConfig;
import com.aliendroid.sdkads.config.InitializeAlienAds;
import com.applovin.sdk.AppLovinMediationProvider;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
//import com.flurry.android.FlurryAgent;
//import com.flurry.android.FlurryPerformance;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import io.appmetrica.analytics.AppMetrica;
import io.appmetrica.analytics.AppMetricaConfig;

public class MyApplication extends Application {
    private static AlienOpenAds alienOpenAds;
    private static PropsOpenAds propsOpenAds;
    private static ApplovinOpenAds applovinOpenAds;
    private static InitializeAlienAds sdkads;
    private static AlienNotif notif;
    Context context;
    //Neptunus
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        MobileAds.initialize(
                this,
                new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(InitializationStatus initializationStatus) {
                    }
                });
        AppMetricaConfig config = AppMetricaConfig
                .newConfigBuilder(ALIENSDKKEY)
                .withSessionTimeout(15)
                .withCrashReporting(true)
                .build();
        AppMetrica.activate(this, config);
        AppMetrica.enableActivityAutoTracking(this);

        sdkads = new InitializeAlienAds(this);
        alienOpenAds = new AlienOpenAds(this);
        propsOpenAds = new PropsOpenAds(this);
        //applovinOpenAds = new ApplovinOpenAds(this);
        notif = new AlienNotif(context,this);

    }
}