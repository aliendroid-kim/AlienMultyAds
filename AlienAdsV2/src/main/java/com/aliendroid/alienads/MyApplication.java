package com.aliendroid.alienads;

import static com.aliendroid.sdkads.config.AppsConfig.ALIENSDKKEY;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

/*
import io.appmetrica.analytics.AppMetrica;
import io.appmetrica.analytics.AppMetricaConfig;

 */

public class MyApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    private static AlienOpenAds alienOpenAds;
    @SuppressLint("StaticFieldLeak")
    private static JamboxOpenAds jsmboxOpenAds;
    @SuppressLint("StaticFieldLeak")
    private static PropsOpenAds propsOpenAds;
    @SuppressLint("StaticFieldLeak")
    private static ApplovinOpenAds applovinOpenAds;

    Context context;
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
        /*
        AppMetricaConfig config = AppMetricaConfig
                .newConfigBuilder(ALIENSDKKEY)
                .withSessionTimeout(15)
                .withCrashReporting(true)
                .build();
        AppMetrica.activate(this, config);
        AppMetrica.enableActivityAutoTracking(this);

         */
        alienOpenAds = new AlienOpenAds(this);
        propsOpenAds = new PropsOpenAds(this);
        jsmboxOpenAds = new JamboxOpenAds(this);


    }
}