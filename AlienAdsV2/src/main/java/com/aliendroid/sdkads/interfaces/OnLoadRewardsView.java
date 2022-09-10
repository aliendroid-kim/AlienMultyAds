package com.aliendroid.sdkads.interfaces;

public interface OnLoadRewardsView {
    void onInterstitialAdLoaded();
    void onInterstitialAdClosed();
    void onInterstitialAdClicked();
    void onInterstitialAdFailedToLoad(String error);
}
