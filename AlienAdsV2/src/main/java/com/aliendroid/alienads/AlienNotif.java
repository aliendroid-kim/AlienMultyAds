package com.aliendroid.alienads;

import android.app.Application;
import android.content.Context;

import com.onesignal.Continue;
import com.onesignal.OneSignal;
import com.onesignal.debug.LogLevel;

//import com.onesignal.OneSignal;

public class AlienNotif {
    private static String ONESIGNAL_APP_ID = "";
    public static MyApplication application;
    static Context context;
    public AlienNotif(Context context, MyApplication myApplication) {
        application = myApplication;
        AlienNotif.context = context;
    }

    public static void LoadOneSignal(String apikey) {
        ONESIGNAL_APP_ID = apikey;
        OneSignal.getDebug().setLogLevel(LogLevel.VERBOSE);
        OneSignal.initWithContext(context, ONESIGNAL_APP_ID);
        OneSignal.getNotifications().requestPermission(true, Continue.with(r -> {
            if (r.isSuccess()) {
                if (r.getData()) {
                }
                else {
                }
            }
            else {

            }
        }));
    }
}
