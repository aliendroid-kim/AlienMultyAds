package com.aliendroid.sdkads.interfaces;

public interface OnLoadBannerView {
    void onBannerAdLoaded();
    void onBannerAdClicked();
    void onBannerAdFailedToLoad(String error);
}
