package com.aliendroid.sdkads.interfaces;

public interface OnLoadNative {
    void onNativeAdLoaded();
    void onNativeAdClicked();
    void onNativeAdFailedToLoad(String error);
}
