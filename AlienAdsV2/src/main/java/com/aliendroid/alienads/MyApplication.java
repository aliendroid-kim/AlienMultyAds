package com.aliendroid.alienads;

import static com.aliendroid.sdkads.config.AppsConfig.ALIENSDKKEY;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.webkit.WebView;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.List;

import io.appmetrica.analytics.AppMetrica;
import io.appmetrica.analytics.AppMetricaConfig;

public class MyApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    private static AlienOpenAds alienOpenAds;
    @SuppressLint("StaticFieldLeak")
    private static JamboxOpenAds jsmboxOpenAds;
    @SuppressLint("StaticFieldLeak")
    private static PropsOpenAds propsOpenAds;
    @SuppressLint("StaticFieldLeak")
    private static ApplovinOpenAds applovinOpenAds;
    @SuppressLint("StaticFieldLeak")
    private static AlienNotif notif;
    static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        if (isMainProcess()) {
            alienOpenAds = new AlienOpenAds(this);
            propsOpenAds = new PropsOpenAds(this);
            jsmboxOpenAds = new JamboxOpenAds(this);
            notif = new AlienNotif(context,this);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                String processName = getProcessName();
                try {
                    WebView.setDataDirectorySuffix(processName);
                } catch (Throwable e) {
                    // ignore
                }
            }

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
        }



    }
    private boolean isMainProcess() {
        return getPackageName().equals(getProcessName());
    }
    public static String getProcessName() {
        int mypid = android.os.Process.myPid();
        ActivityManager manager = (ActivityManager)  context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        for(ActivityManager.RunningAppProcessInfo info : infos) {
            if (info.pid == mypid) {
                return info.processName;
            }
        }
        return null;
    }
}