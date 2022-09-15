package com.kimganteng.myapplication;

import static com.kimganteng.myapplication.SettingsAlien.BackupIntertitial;
import static com.kimganteng.myapplication.SettingsAlien.BackupReward;
import static com.kimganteng.myapplication.SettingsAlien.MainIntertitial;
import static com.kimganteng.myapplication.SettingsAlien.MainRewards;
import static com.kimganteng.myapplication.SettingsAlien.Select_Backup_Ads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.aliendroid.alienads.AliendroidIntertitial;
import com.aliendroid.alienads.AliendroidReward;
import com.aliendroid.alienads.interfaces.interstitial.show.OnShowInterstitialAdmob;
import com.aliendroid.sdkads.config.AppPromote;
import com.aliendroid.sdkads.config.InitializeAlienAds;
import com.aliendroid.sdkads.type.mediation.AlienMediationAds;

public class MediationAdsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediation_ads);

        AlienMediationAds.SmallBanner(this,findViewById(R.id.layAdsView),SettingsAlien.BackupBanner);
        AlienMediationAds.LoadInterstitial(this,SettingsAlien.BackupIntertitial);
        AlienMediationAds.LoadRewarded(SettingsAlien.BackupReward);
        AlienMediationAds.MediumNatives(this,findViewById(R.id.layAlienNatives),SettingsAlien.BackupNatives);
    }

    public void INTERSTITIAL(View view){
      AlienMediationAds.ShowInterstitial(MediationAdsActivity.this);

    }

    public void REWARD(View view){
      AlienMediationAds.ShowReward();
    }
}