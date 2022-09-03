package com.aliendroid.alienads.interfaces.interstitial;

public interface OnLoadInterstitialFacebook {
    void onInterstitialDisplayed();
    void onInterstitialDismissed();
    void onError();
    void onAdLoaded();
    void onAdClicked();
    void onLoggingImpression();
}
