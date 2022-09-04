package com.aliendroid.alienads.interfaces.banner;

public interface OnLoadBannerAlienView {
    void onBannerAdLoaded();
    void onBannerAdClicked();
    void onBannerAdFailedToLoad(String error);
}
