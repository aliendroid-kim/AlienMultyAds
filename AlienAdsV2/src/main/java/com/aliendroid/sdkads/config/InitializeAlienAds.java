package com.aliendroid.sdkads.config;

public class InitializeAlienAds {
    public static com.aliendroid.alienads.MyApplication application;

    public InitializeAlienAds(com.aliendroid.alienads.MyApplication myApplication) {
        application = myApplication;
    }

    public static void LoadSDK() {
        Connection.SDKMediation(application, AppsConfig.APPKEY);
    }

}
