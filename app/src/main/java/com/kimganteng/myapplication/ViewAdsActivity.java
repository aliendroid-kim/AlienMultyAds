package com.kimganteng.myapplication;

import static com.kimganteng.myapplication.SettingsAlien.AppIDViewAds;
import static com.kimganteng.myapplication.SettingsAlien.BackupIntertitial;
import static com.kimganteng.myapplication.SettingsAlien.BackupReward;
import static com.kimganteng.myapplication.SettingsAlien.Select_Backup_Ads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.aliendroid.sdkads.config.AppPromote;
import com.aliendroid.sdkads.interfaces.OnLoadBannerView;
import com.aliendroid.sdkads.interfaces.OnLoadInterstitialView;
import com.aliendroid.sdkads.interfaces.OnOpenViewAdListener;
import com.aliendroid.sdkads.interfaces.OnShowInterstitialView;
import com.aliendroid.sdkads.interfaces.OnShowRewardsView;
import com.aliendroid.sdkads.type.view.AlienViewAds;

public class ViewAdsActivity extends AppCompatActivity {
      /*
      Implementation for Alien View Ads
       */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ads);
        AppPromote.initializeAppPromote(this);
        AlienViewAds.Banner(ViewAdsActivity.this,findViewById(R.id.layAdsView),AppIDViewAds);
        AlienViewAds.Interstitial(this,AppIDViewAds);
        AlienViewAds.RewardsAds(this,AppIDViewAds);

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
        AlienViewAds.ShowIntertitial();

    }

    public void REWARDS(View view){
        AlienViewAds.ShowRewards();
        AlienViewAds.onShowRewardsView = new OnShowRewardsView() {
            @Override
            public void onAdSuccess() {

            }

            @Override
            public void onAdFailedShow() {

            }
        };

    }
}