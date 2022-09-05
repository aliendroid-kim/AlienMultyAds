package com.aliendroid.sdkads.config;

import android.util.Log;

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
}
