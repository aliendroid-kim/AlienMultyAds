package com.kimganteng.myapplication;

import static android.content.ContentValues.TAG;
import static com.kimganteng.myapplication.SettingsAlien.AppIDViewAds;
import static com.kimganteng.myapplication.SettingsAlien.BackupIntertitial;
import static com.kimganteng.myapplication.SettingsAlien.BackupReward;
import static com.kimganteng.myapplication.SettingsAlien.Backup_Initialize;
import static com.kimganteng.myapplication.SettingsAlien.MainIntertitial;
import static com.kimganteng.myapplication.SettingsAlien.MainRewards;
import static com.kimganteng.myapplication.SettingsAlien.Select_Backup_Ads;
import static com.kimganteng.myapplication.SettingsAlien.Select_Main_Ads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.aliendroid.alienads.AlienGDPR;
import com.aliendroid.alienads.AliendroidInitialize;
import com.aliendroid.alienads.AliendroidIntertitial;
import com.aliendroid.alienads.AliendroidReward;
import com.aliendroid.alienads.interfaces.interstitial.admob.OnFullScreenContentCallbackAdmob;
import com.aliendroid.alienads.interfaces.interstitial.load.OnLoadInterstitialAdmob;
import com.aliendroid.alienads.interfaces.interstitial.show.OnShowInterstitialAdmob;
import com.aliendroid.alienads.interfaces.rewards.load.OnLoadRewardsAdmob;
import com.aliendroid.alienads.interfaces.rewards.load.OnLoadRewardsApplovinMax;
import com.aliendroid.sdkads.config.AppPromote;
import com.aliendroid.sdkads.config.InitializeAlienAds;
import com.aliendroid.sdkads.config.QWERTY;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppPromote.initializeAppPromote(this);
        InitializeAlienAds.LoadSDK();
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                AdvertisingIdClient.Info idInfo = null;
                try {
                    idInfo = AdvertisingIdClient.getAdvertisingIdInfo(MainActivity.this);
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String advertId = null;
                try{
                    advertId = idInfo.getId();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }

                return advertId;
            }

            @Override
            protected void onPostExecute(String advertId) {
                Log.e("ID IKLAN",advertId);
            }

        };
        task.execute();
        AlienGDPR.loadGdpr(this,Select_Main_Ads,true);

        if (Select_Main_Ads.equals("ADMOB")){
            AliendroidInitialize.SelectAdsAdmob(this,Select_Backup_Ads,Backup_Initialize);
        } else if (Select_Main_Ads.equals("APPLOVIN-M")){
            AliendroidInitialize.SelectAdsApplovinMax(this,Select_Backup_Ads,Backup_Initialize);
        }else if (Select_Main_Ads.equals("APPLOVIN-D")){
            AliendroidInitialize.SelectAdsApplovinDis(this,Select_Backup_Ads,Backup_Initialize);
        }

        if (Select_Main_Ads.equals("ADMOB")) {
            AliendroidIntertitial.LoadIntertitialAdmob(MainActivity.this,Select_Backup_Ads,MainIntertitial,BackupIntertitial,""
            ,"","","","");

        } else if (Select_Main_Ads.equals("APPLOVIN-M")){
            AliendroidIntertitial.LoadIntertitialApplovinMax(MainActivity.this,Select_Backup_Ads,MainIntertitial,BackupIntertitial);
        }else if (Select_Main_Ads.equals("APPLOVIN-D")){
            AliendroidIntertitial.LoadIntertitialApplovinDis(MainActivity.this,Select_Backup_Ads,MainIntertitial,BackupIntertitial);
        }


        AliendroidReward.LoadRewardApplovinMax(this,Select_Backup_Ads,MainRewards,BackupReward);
        AliendroidReward.onLoadRewardsAdmob = new OnLoadRewardsAdmob() {
            @Override
            public void onAdFailedToLoad() {

            }

            @Override
            public void onAdLoaded(String error) {

            }
        };

    }

    public void BANNER(View view){
        Intent open = new Intent(MainActivity.this,BannerActivity.class);
        startActivity(open);

    }

    public void VIEWADS(View view){
        Intent open = new Intent(MainActivity.this,ViewAdsActivity.class);
        startActivity(open);

    }

    public void NATIVES(View view){
        Intent open = new Intent(MainActivity.this,NativeActivity.class);
        startActivity(open);

    }


    public void MEDIATION(View view){
        Intent open = new Intent(MainActivity.this,MediationAdsActivity.class);
        startActivity(open);

    }

    public void INTERSTITIAL(View view){
        if (Select_Main_Ads.equals("ADMOB")){
            AliendroidIntertitial.ShowIntertitialAdmob(MainActivity.this,Select_Backup_Ads,MainIntertitial,BackupIntertitial,0,"",
                    "","","","");
        } else if (Select_Main_Ads.equals("APPLOVIN-M")){
            AliendroidIntertitial.ShowIntertitialApplovinMax(MainActivity.this,Select_Backup_Ads,MainIntertitial,BackupIntertitial,0
                  );
        }else if (Select_Main_Ads.equals("APPLOVIN-D")){
            AliendroidIntertitial.ShowIntertitialApplovinDis(MainActivity.this,Select_Backup_Ads,MainIntertitial,BackupIntertitial,0
            );
        }

    }

    public void REWARD(View view){
        AliendroidReward.ShowRewardAdmob(MainActivity.this,Select_Backup_Ads,MainRewards,BackupReward);


    }

    public void onBackPressed(){
        finishAffinity();
        System.exit(0);
    }
}