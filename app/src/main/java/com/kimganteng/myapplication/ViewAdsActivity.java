package com.kimganteng.myapplication;

import static com.kimganteng.myapplication.SettingsAlien.AppIDViewAds;
import static com.kimganteng.myapplication.SettingsAlien.BackupIntertitial;
import static com.kimganteng.myapplication.SettingsAlien.BackupReward;
import static com.kimganteng.myapplication.SettingsAlien.Select_Backup_Ads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.aliendroid.alienads.AliendroidBanner;
import com.aliendroid.alienads.AliendroidIntertitial;
import com.aliendroid.alienads.AliendroidReward;
import com.aliendroid.alienads.interfaces.banner.OnLoadBannerAlienView;
import com.aliendroid.alienads.interfaces.interstitial.load.OnLoadInterstitialAlienView;
import com.aliendroid.alienads.interfaces.interstitial.show.OnShowInterstitialAlienView;
import com.aliendroid.alienads.interfaces.rewards.load.OnLoadRewardsAlienView;
import com.aliendroid.alienads.interfaces.rewards.show.OnShowRewardsAlienView;
import com.aliendroid.sdkads.interfaces.OnLoadBannerView;
import com.aliendroid.sdkads.interfaces.OnOpenViewAdListener;
import com.aliendroid.sdkads.interfaces.OnShowInterstitialView;
import com.aliendroid.sdkads.type.view.AlienViewAds;

public class ViewAdsActivity extends AppCompatActivity {
      /*
      Implementation for Alien View Ads
       */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ads);
        AliendroidBanner.SmallBannerAlienView(ViewAdsActivity.this,findViewById(R.id.layAdsView),Select_Backup_Ads,AppIDViewAds,"");
        AliendroidBanner.onLoadBannerAlienView = new OnLoadBannerAlienView() {
            @Override
            public void onBannerAdLoaded() {

            }

            @Override
            public void onBannerAdClicked() {

            }

            @Override
            public void onBannerAdFailedToLoad(String error) {

            }
        };

        AliendroidIntertitial.LoadIntertitialAlienView(this,Select_Backup_Ads,AppIDViewAds,BackupIntertitial);
        AliendroidIntertitial.onLoadInterstitialAlienView = new OnLoadInterstitialAlienView() {
            @Override
            public void onInterstitialAdLoaded() {

            }

            @Override
            public void onInterstitialAdClosed() {

            }

            @Override
            public void onInterstitialAdClicked() {

            }

            @Override
            public void onInterstitialAdFailedToLoad() {

            }
        };

        AliendroidReward.LoadRewardAlienView(this,Select_Backup_Ads,AppIDViewAds,BackupReward);
        AliendroidReward.onLoadRewardsAlienView = new OnLoadRewardsAlienView() {
            @Override
            public void onRewardsAdLoaded() {

            }

            @Override
            public void onRewardsAdClosed() {

            }

            @Override
            public void onRewardsAdReward() {

            }

            @Override
            public void onRewardsAdClicked() {

            }

            @Override
            public void onRewardsAdFailedToLoad(String error) {

            }
        };

    }

    public void OPENADS(View view){
        AlienViewAds.OpenApp(ViewAdsActivity.this,AppIDViewAds);
        AlienViewAds.onOpenViewAdListener = new OnOpenViewAdListener() {
            @Override
            public void onInterstitialAdLoaded() {

            }

            @Override
            public void onInterstitialAdClosed() {

            }

            @Override
            public void onInterstitialAdClicked() {

            }

            @Override
            public void onInterstitialAdFailedToLoad(String error) {

            }
        };

    }

    public void INTERSTITIAL(View view){
        AliendroidIntertitial.ShowIntertitialAlienView(ViewAdsActivity.this, Select_Backup_Ads,AppIDViewAds, BackupIntertitial,0);
        AliendroidIntertitial.onShowInterstitialAlienView = new OnShowInterstitialAlienView() {
            @Override
            public void onAdSuccess() {

            }

            @Override
            public void onAdFailedShow() {

            }
        };
    }

    public void REWARDS(View view){
        AliendroidReward.ShowRewardAlienView(ViewAdsActivity.this, Select_Backup_Ads,AppIDViewAds, BackupReward);
        AliendroidReward.onShowRewardsAlienView = new OnShowRewardsAlienView() {
            @Override
            public void onAdSuccess() {

            }

            @Override
            public void onAdFailedShow() {

            }
        };

    }
}