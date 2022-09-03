package com.aliendroid.sdkads.interfaces;

public interface OnLoadBannerMediation {
    void onBannerAdLoaded();
    void onBannerAdClicked();
    void onBannerAdFailedToLoad(String error);
}
