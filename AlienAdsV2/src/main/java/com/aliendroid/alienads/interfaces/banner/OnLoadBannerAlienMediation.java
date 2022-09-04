package com.aliendroid.alienads.interfaces.banner;

public interface OnLoadBannerAlienMediation {
    void onBannerAdLoaded();
    void onBannerAdClicked();
    void onBannerAdFailedToLoad(String error);
}
