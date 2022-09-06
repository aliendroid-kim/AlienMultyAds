package com.aliendroid.sdkads.config;

import android.app.Activity;
import android.util.Log;

import com.flurry.android.FlurryAgent;
import com.flurry.android.FlurryPerformance;
import com.smaato.sdk.core.Config;
import com.smaato.sdk.core.SmaatoSdk;
import com.smaato.sdk.core.log.LogLevel;


public class InitializeAlienAds {
    public static com.aliendroid.alienads.MyApplication application;

    public InitializeAlienAds(com.aliendroid.alienads.MyApplication myApplication) {
        application = myApplication;
    }
    public static void LoadSDK (){
       Connection.SDKMediation(application, AppsConfig.APPKEY);
   }

   public static void Analytics(){
       new FlurryAgent.Builder()
               .withDataSaleOptOut(false)
               .withCaptureUncaughtExceptions(true)
               .withIncludeBackgroundSessionsInMetrics(true)
               .withLogLevel(Log.VERBOSE)
               .withPerformanceMetrics(FlurryPerformance.ALL)
               .build(application, AppsConfig.ANALYSKEY);
   }
}
