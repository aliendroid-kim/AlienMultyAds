package com.kimganteng.myapplication;

import static com.kimganteng.myapplication.SettingsAlien.AppIDMediationAds;
import static com.kimganteng.myapplication.SettingsAlien.AppIDViewAds;
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
import com.aliendroid.alienads.interfaces.open.OnLoadOpenAppAdmob;
import com.aliendroid.alienads.interfaces.open.OnShowOpenAppAdmob;
import com.aliendroid.sdkads.config.AppPromote;
import com.aliendroid.sdkads.config.InitializeAlienAds;
import com.aliendroid.sdkads.interfaces.OnOpenViewAdListener;
import com.aliendroid.sdkads.type.view.AlienViewAds;


@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        /*
        Aliendroid have 2 ads (view and mediation)
        type view ads = Banner, Interstitial and Open Ads
        type mediation Ads = Banner, Interstitial, Rewards and Natives
         */
        /*
        Initilize for Alien Mediation Ads
         */
        InitializeAlienAds.LoadSDK();
        AliendroidInitialize.SelectAdsApplovinMax(this,Select_Backup_Ads,Backup_Initialize);
        if (SettingsAlien.Select_Open_Ads.equals("1")){
            AlienOpenAds.LoadOpenAds("ca-app-pub-3940256099942544/3419835294",true);
            AlienOpenAds.onLoadOpenAppAdmob = new OnLoadOpenAppAdmob() {
                @Override
                public void onAdLoaded() {
                    AlienOpenAds.onShowOpenAppAdmob = new OnShowOpenAppAdmob() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent() {

                        }

                        @Override
                        public void onAdShowedFullScreenContent() {

                        }
                    };
                }

                @Override
                public void onAdFailedToLoad() {
                    startActivity(true);
                }
            };
        } else {
            startActivity(true);
        }


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
