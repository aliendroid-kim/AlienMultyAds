package com.aliendroid.alienads;

import android.app.Application;

import com.onesignal.OneSignal;

public class AlienNotif {
    private static String ONESIGNAL_APP_ID = "";
    public static com.aliendroid.alienads.MyApplication application;

    public AlienNotif(com.aliendroid.alienads.MyApplication myApplication) {
        application = myApplication;
    }

    public static void LoadOneSignal(String apikey) {
        ONESIGNAL_APP_ID = apikey;
        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(application);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        // promptForPushNotifications will show the native Android notification permission prompt.
        // We recommend removing the following code and instead using an In-App Message to prompt for notification permission (See step 7)
        OneSignal.promptForPushNotifications();
    }
}
