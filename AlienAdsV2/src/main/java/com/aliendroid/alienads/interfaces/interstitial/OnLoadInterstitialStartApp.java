package com.aliendroid.alienads.interfaces.interstitial;

public interface OnLoadInterstitialStartApp {
    void onReceiveAd();
    void onFailedToReceiveAd(String error);
}
