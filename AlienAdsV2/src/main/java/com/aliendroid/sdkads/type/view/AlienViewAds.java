package com.aliendroid.sdkads.type.view;

import android.app.Activity;
import android.widget.RelativeLayout;

import com.aliendroid.sdkads.interfaces.OnClosed;
import com.aliendroid.sdkads.interfaces.OnLoadBannerView;
import com.aliendroid.sdkads.interfaces.OnLoadInterstitialView;
import com.aliendroid.sdkads.interfaces.OnOpenViewAdListener;
import com.aliendroid.sdkads.interfaces.OnShowInterstitialView;
import com.aliendroid.sdkads.layout.BannerView;
import com.aliendroid.sdkads.layout.InterstitialView;
import com.aliendroid.sdkads.layout.OpenView;

public class AlienViewAds {
    public static OnLoadBannerView onLoadBannerView;
    public static OnOpenViewAdListener onOpenViewAdListener;
    public static OnLoadInterstitialView onLoadInterstitialView;
    public static InterstitialView interstitial;
    public static OpenView openView;
    public static BannerView banner;;
    public static OnShowInterstitialView onShowInterstitialView;
    public static void Banner (Activity activity, RelativeLayout layAds, String AppID) {
        banner = new BannerView(activity, AppID);
        banner.setOnBannerListener(new OnLoadBannerView() {
            @Override
            public void onBannerAdLoaded() {
                if (onLoadBannerView !=null){
                    onLoadBannerView.onBannerAdLoaded();
                }
            }

            @Override
            public void onBannerAdClicked() {
                if (onLoadBannerView !=null){
                    onLoadBannerView.onBannerAdClicked();
                }
            }

            @Override
            public void onBannerAdFailedToLoad(String error) {
                if (onLoadBannerView !=null){
                    onLoadBannerView.onBannerAdFailedToLoad("");
                }
            }
        });
        layAds.addView(banner);
    }

    public static void OpenApp (Activity activity, String AppID) {
        openView = new OpenView(activity,AppID);
        openView.setStyle(1);
        openView.setOnOpenViewAdListener(new OnOpenViewAdListener() {
            @Override
            public void onInterstitialAdLoaded() {
                if (onOpenViewAdListener!=null){
                    onOpenViewAdListener.onInterstitialAdLoaded();
                }
            }

            @Override
            public void onInterstitialAdClosed() {
                if (onOpenViewAdListener!=null){
                    onOpenViewAdListener.onInterstitialAdClosed();
                }
            }

            @Override
            public void onInterstitialAdClicked() {
                if (onOpenViewAdListener!=null){
                    onOpenViewAdListener.onInterstitialAdClicked();
                }
            }

            @Override
            public void onInterstitialAdFailedToLoad(String error) {
                if (onOpenViewAdListener!=null){
                    onOpenViewAdListener.onInterstitialAdFailedToLoad("");
                }

            }
        });

        if (openView.isAdLoaded()) {
            openView.show();
            openView.setOnAdClosed(new OnClosed() {
                @Override
                public void onAdClosed() {
                }
            });
        }
    }

    public static void Interstitial (Activity activity, String AppID) {
        interstitial = new InterstitialView(activity,AppID);
        interstitial.setStyle(1);
        interstitial.setOnInterstitialAdListener(new OnLoadInterstitialView() {
            @Override
            public void onInterstitialAdLoaded() {
                if (onLoadInterstitialView !=null){
                    onLoadInterstitialView.onInterstitialAdLoaded();
                }
            }

            @Override
            public void onInterstitialAdClosed() {
                if (onLoadInterstitialView !=null){
                    onLoadInterstitialView.onInterstitialAdClosed();
                }

            }

            @Override
            public void onInterstitialAdClicked() {
                if (onLoadInterstitialView !=null){
                    onLoadInterstitialView.onInterstitialAdClicked();
                }

            }

            @Override
            public void onInterstitialAdFailedToLoad(String error) {
                if (onLoadInterstitialView !=null){
                    onLoadInterstitialView.onInterstitialAdFailedToLoad("");
                }

            }
        });

    }

    public static void ShowIntertitial (){
        if (interstitial.isAdLoaded()) {
            interstitial.show();
            if (onShowInterstitialView !=null){
                onShowInterstitialView.onAdSuccess();
            }
        } else {
            if (onShowInterstitialView !=null){
                onShowInterstitialView.onAdFailedShow();
            }
        }
    }
}
