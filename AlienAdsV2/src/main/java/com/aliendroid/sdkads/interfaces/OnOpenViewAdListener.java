package com.aliendroid.sdkads.interfaces;

public interface OnOpenViewAdListener {
    void onInterstitialAdLoaded();
    void onInterstitialAdClosed();
    void onInterstitialAdClicked();
    void onInterstitialAdFailedToLoad(String error);
}
