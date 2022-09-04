package com.kimganteng.myapplication;

import static com.kimganteng.myapplication.SettingsAlien.AppIDViewAds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

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

        AlienViewAds.Banner(ViewAdsActivity.this, findViewById(R.id.layAdsView), AppIDViewAds);
        AlienViewAds.onLoadBannerView = new OnLoadBannerView() {
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

        AlienViewAds.Interstitial(ViewAdsActivity.this,AppIDViewAds);
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
        AlienViewAds.onShowInterstitialView = new OnShowInterstitialView() {
            @Override
            public void onAdSuccess() {

            }

            @Override
            public void onAdFailedShow() {

            }
        };
    }
}