package com.aliendroid.alienads;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.mopub.common.MoPubReward;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubRewardedAdListener;
import com.mopub.mobileads.MoPubRewardedAds;

import java.util.Set;

public class AliendroidReward {
    private static RewardedAd mRewardedAd;
    public static MaxRewardedAd rewardedAd;
    public static boolean unlockreward = false;
    public static MoPubRewardedAdListener rewardedAdListener;

    public static void LoadReward (Activity activity, String selectAds, String idReward){
        if (selectAds.equals("ADMOB")){
            AdRequest adRequest = new AdRequest.Builder().build();
            RewardedAd.load(activity, idReward,
                    adRequest, new RewardedAdLoadCallback() {
                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            mRewardedAd = null;
                        }

                        @Override
                        public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                            mRewardedAd = rewardedAd;

                        }
                    });

        } else if (selectAds.equals("APPLOVIN-M")){
            rewardedAd = MaxRewardedAd.getInstance(idReward, activity);
            rewardedAd.loadAd();
            MaxRewardedAdListener maxRewardedAdListener = new MaxRewardedAdListener() {
                @Override
                public void onRewardedVideoStarted(MaxAd ad) {

                }

                @Override
                public void onRewardedVideoCompleted(MaxAd ad) {
                    unlockreward = true;
                }

                @Override
                public void onUserRewarded(MaxAd ad, MaxReward reward) {

                }

                @Override
                public void onAdLoaded(MaxAd ad) {

                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {

                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {

                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                }
            };
            rewardedAd.setListener(maxRewardedAdListener);
        } else if (selectAds.equals("MOPUB")){
            MoPubRewardedAds.loadRewardedAd(idReward);
        }
    }

    public static void ShowReward(Activity activity, String selectAds, String idReward){
        if (selectAds.equals("ADMOB")){
            if (mRewardedAd != null) {
                Activity activityContext = activity;
                mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                    @Override
                    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                        unlockreward = true;
                        LoadReward(activity,selectAds, idReward);
                    }
                });
            } else {
                LoadReward(activity,selectAds, idReward);
            }
        } else if (selectAds.equals("APPLOVIN-M")){
            if (rewardedAd.isReady()) {
                rewardedAd.showAd();
                LoadReward(activity,selectAds, idReward);
            } else {
                LoadReward(activity,selectAds, idReward);
            }
        } else if (selectAds.equals("MOPUB")){
            MoPubRewardedAds.showRewardedAd(idReward);
            rewardedAdListener = new MoPubRewardedAdListener() {
                @Override
                public void onRewardedAdLoadSuccess(String adUnitId) {
                    // Called when the ad for the given adUnitId has loaded. At this point you should be able to call MoPubRewardedAds.showRewardedAd() to show the ad.
                }

                @Override
                public void onRewardedAdLoadFailure(String adUnitId, MoPubErrorCode errorCode) {
                    LoadReward(activity,selectAds, idReward);
                }

                @Override
                public void onRewardedAdStarted(String adUnitId) {
                    // Called when a rewarded ad starts playing.
                }

                @Override
                public void onRewardedAdShowError(String adUnitId, MoPubErrorCode errorCode) {
                    //  Called when there is an error while attempting to show the ad.
                }

                @Override
                public void onRewardedAdClicked(@NonNull String adUnitId) {
                    //  Called when a rewarded ad is clicked.
                }

                @Override
                public void onRewardedAdClosed(String adUnitId) {
                    // Called when a rewarded ad is closed. At this point your application should resume.
                }

                @Override
                public void onRewardedAdCompleted(Set<String> adUnitIds, MoPubReward reward) {
                   unlockreward = true;
                   LoadReward(activity,selectAds, idReward);
                }
            };
            MoPubRewardedAds.setRewardedAdListener(rewardedAdListener);
        }

    }


}
