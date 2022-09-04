package com.aliendroid.alienads.interfaces.interstitial.load;

public interface OnLoadInterstitialStartApp {
    void onReceiveAd();
    void onFailedToReceiveAd(String error);
}
