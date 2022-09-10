package com.aliendroid.sdkads.interfaces;

public interface OnLoadRewardsView {
    void onRewardsAdLoaded();
    void onRewardsAdClosed();
    void onRewardsAdClicked();
    void onRewardsAdFailedToLoad(String error);
}
