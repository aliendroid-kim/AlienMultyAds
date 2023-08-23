package com.aliendroid.sdkads.type.mediation;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.aliendroid.alienads.R;
import com.aliendroid.sdkads.interfaces.OnLoadBannerMediation;
import com.aliendroid.sdkads.interfaces.OnLoadInterstitialMediation;
import com.aliendroid.sdkads.interfaces.OnLoadNative;
import com.aliendroid.sdkads.interfaces.OnLoadRewardsMediation;
import com.aliendroid.sdkads.interfaces.OnShowInterstitial;
import com.aliendroid.sdkads.interfaces.OnShowRewards;
import com.aliendroid.sdkads.layout.NativeMediation;

public class AlienMediationAds {
    public static OnLoadInterstitialMediation onLoadInterstitialMediation;
    public static OnLoadBannerMediation onLoadBannerMediation;
    public static OnLoadRewardsMediation onLoadRewardsMediation;

    public static OnShowInterstitial onShowInterstitial;
    public static OnShowRewards onShowRewards;
    public static OnLoadNative onLoadNative;

    public static void SmallBanner(Activity activity, RelativeLayout layAds, String PlacementID){

    }

    public static void MediumBanner(Activity activity, RelativeLayout layAds, String PlacementID){


    }

    public static void BoardBanner(Activity activity, RelativeLayout layAds, String PlacementID){

    }

    public static void ScraperBanner(Activity activity, RelativeLayout layAds, String PlacementID){


    }

    public static void LoadInterstitial (Activity activity, String PlacementID) {


    }

    public static void ShowInterstitial (Activity activity) {


    }

    public static void LoadRewarded (String PlacementID) {
    }

    public static void ShowReward() {


    }

    public static void SmallNatives( Activity activity, RelativeLayout content, String PlacementID) {

    }

    public static void MediumNatives( Activity activity, RelativeLayout content, String PlacementID) {

    }

    public static void RectangleNatives( Activity activity, RelativeLayout content, String PlacementID) {

    }

}
