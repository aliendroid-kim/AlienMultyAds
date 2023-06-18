package com.aliendroid.alienads;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.aliendroid.alienads.interfaces.open.OnLoadOpenAppWortise;
import com.wortise.ads.AdError;
import com.wortise.ads.appopen.AppOpenAd;

public class WortiseOpenAds {
    public static AppOpenAd mAppOpenAd;
    public static OnLoadOpenAppWortise onLoadOpenAppWortise;
    public static void LoadOpenWortise (Activity activity, String idOpen){
        mAppOpenAd = new AppOpenAd(activity, idOpen);
        mAppOpenAd.loadAd();
        mAppOpenAd.setAutoReload(true);
        mAppOpenAd.setOrientation(AppOpenAd.Orientation.PORTRAIT);
        mAppOpenAd.tryToShowAd(activity);
        mAppOpenAd.setListener(new AppOpenAd.Listener() {
            @Override
            public void onAppOpenFailed(@NonNull AppOpenAd appOpenAd, @NonNull AdError adError) {
            if (onLoadOpenAppWortise!=null){
                onLoadOpenAppWortise.onAppOpenFailed();
            }
            }

            @Override
            public void onAppOpenClicked(@NonNull AppOpenAd ad) {
                if (onLoadOpenAppWortise!=null){
                    onLoadOpenAppWortise.onAppOpenClicked();
                }
            }

            @Override
            public void onAppOpenDismissed(@NonNull AppOpenAd ad) {
                if (onLoadOpenAppWortise!=null){
                    onLoadOpenAppWortise.onAppOpenDismissed();
                }
            }

            @Override
            public void onAppOpenLoaded(@NonNull AppOpenAd ad) {
                if (onLoadOpenAppWortise!=null){
                    onLoadOpenAppWortise.onAppOpenLoaded();
                }
            }

            @Override
            public void onAppOpenShown(@NonNull AppOpenAd ad) {

            }
        });
    }

    public static void ShowOpenWortise (Activity activity){
        if (mAppOpenAd.isAvailable()) {
            mAppOpenAd.showAd(activity);
        }
    }
}
