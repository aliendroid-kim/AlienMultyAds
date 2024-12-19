package com.aliendroid.alienads;
import android.content.Context;
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
    }
}
