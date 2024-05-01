package com.kimganteng.myapplication;

import static com.kimganteng.myapplication.SettingsAlien.Backup_Initialize;
import static com.kimganteng.myapplication.SettingsAlien.Main_Initialize;
import static com.kimganteng.myapplication.SettingsAlien.Select_Backup_Ads;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.aliendroid.alienads.AlienOpenAds;
import com.aliendroid.alienads.AliendroidInitialize;
import com.aliendroid.alienads.JamboxOpenAds;


@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        switch (SettingsAlien.Select_Main_Ads) {
            case "ADMOB":
                AliendroidInitialize.SelectAdsAdmob(this, Select_Backup_Ads, Backup_Initialize);
                break;
            case "APPLOVIN-M":
                AliendroidInitialize.SelectAdsApplovinMax(this, Select_Backup_Ads, Backup_Initialize);
                break;
            case "APPLOVIN-D":
                AliendroidInitialize.SelectAdsApplovinDis(this, Select_Backup_Ads, Backup_Initialize);
                break;
            case "FACEBOOK":
                AliendroidInitialize.SelectAdsFAN(this, Select_Backup_Ads, Backup_Initialize);
                break;
            case "ALIEN-M":
                AliendroidInitialize.SelectAdsAlienMediation(this, Select_Backup_Ads,Main_Initialize, Backup_Initialize);
                break;
            case "UNITY":
                AliendroidInitialize.SelectAdsUnity(this, Select_Backup_Ads,Main_Initialize, Backup_Initialize);
                break;
        }

        JamboxOpenAds.LoadOpenAds("test",true, "ALIEN-V");
        JamboxOpenAds.AppOpenAdManager.showAdIfAvailable(SplashActivity.this, new JamboxOpenAds.OnShowAdCompleteListener() {
             @Override
             public void onShowAdComplete() {
                 startActivity(true);
             }
         });



    }

    private void startActivity(boolean useTime){
        if (useTime){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                }
            },1000*3);
        }else {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

    }


}
