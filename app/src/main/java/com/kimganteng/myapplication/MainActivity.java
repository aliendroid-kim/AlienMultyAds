package com.kimganteng.myapplication;

import static com.kimganteng.myapplication.SettingsAlien.BackupIntertitial;
import static com.kimganteng.myapplication.SettingsAlien.BackupReward;
import static com.kimganteng.myapplication.SettingsAlien.Backup_Initialize;
import static com.kimganteng.myapplication.SettingsAlien.MainIntertitial;
import static com.kimganteng.myapplication.SettingsAlien.MainRewards;
import static com.kimganteng.myapplication.SettingsAlien.Main_Initialize;
import static com.kimganteng.myapplication.SettingsAlien.Select_Backup_Ads;
import static com.kimganteng.myapplication.SettingsAlien.Select_Main_Ads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;

import com.aliendroid.alienads.AlienGDPR;
import com.aliendroid.alienads.AlienGame;
import com.aliendroid.alienads.AlienNotif;
import com.aliendroid.alienads.AliendroidInitialize;
import com.aliendroid.alienads.AliendroidIntertitial;
import com.aliendroid.alienads.AliendroidReward;
import com.aliendroid.alienads.WortiseOpenAds;
import com.aliendroid.sdkads.config.AppPromote;


public class MainActivity extends AppCompatActivity {
    public static double longitude, latitude;
    public static String cityName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WortiseOpenAds.ShowOpenWortise(this);
        AppPromote.initializeAppPromote(this);
        AlienGDPR.loadGdpr(this,Select_Main_Ads,false);
        switch (Select_Main_Ads) {
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
            case "ALIEN-V":
                AliendroidInitialize.SelectAdsAlienView(this, Select_Backup_Ads, Backup_Initialize);
                break;
            case "UNITY":
                AliendroidInitialize.SelectAdsUnity(this, Select_Backup_Ads,Main_Initialize, Backup_Initialize);
                break;
        }

        switch (Select_Main_Ads) {
            case "ADMOB":
                AliendroidIntertitial.LoadIntertitialAdmob(MainActivity.this, Select_Backup_Ads, MainIntertitial, BackupIntertitial, ""
                        , "", "", "", "");
                break;
            case "APPLOVIN-M":
                AliendroidIntertitial.LoadIntertitialApplovinMax(MainActivity.this, Select_Backup_Ads, MainIntertitial, BackupIntertitial);
                break;
            case "APPLOVIN-D":
                AliendroidIntertitial.LoadIntertitialApplovinDis(MainActivity.this, Select_Backup_Ads, MainIntertitial, BackupIntertitial);
                break;
            case "FACEBOOK":
                AliendroidIntertitial.LoadIntertitialFAN(MainActivity.this, Select_Backup_Ads, MainIntertitial, BackupIntertitial);
                break;
            case "ALIEN-M":
                AliendroidIntertitial.LoadIntertitialAlienMediation(MainActivity.this, Select_Backup_Ads, MainIntertitial, BackupIntertitial);
                break;
            case "ALIEN-V":
                AliendroidIntertitial.LoadIntertitialAlienView(MainActivity.this, Select_Backup_Ads, MainIntertitial, BackupIntertitial);
                break;
            case "UNITY":
                AliendroidIntertitial.LoadIntertitialUnity(MainActivity.this, Select_Backup_Ads, MainIntertitial, BackupIntertitial);
                break;

        }

        AlienNotif.LoadOneSignal("24daffc1-92b9-4d7c-94e8-2cab382018f3");
        AliendroidReward.LoadRewardAlienView(this,Select_Backup_Ads,MainRewards,BackupReward);
        TextView txtCode = findViewById(R.id.txtCode);

        TelephonyManager tm = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        String countryCodeValue = tm.getNetworkCountryIso();
        txtCode.setText("country = "+countryCodeValue);
        AlienGame.LoadGame(MainActivity.this, "tes","tes","tes");
    }

    public void VIEWADS(View view){
        AlienGame.ShowGame(MainActivity.this, "9285717016");

    }

    public void BANNER(View view){
        Intent open = new Intent(MainActivity.this,BannerActivity.class);
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
        switch (Select_Main_Ads) {
            case "ADMOB":
                AliendroidIntertitial.ShowIntertitialAdmob(MainActivity.this, Select_Backup_Ads, MainIntertitial, BackupIntertitial, 0, "",
                        "", "", "", "");
                break;
            case "APPLOVIN-M":
                AliendroidIntertitial.ShowIntertitialApplovinMax(MainActivity.this, Select_Backup_Ads, MainIntertitial, BackupIntertitial, 0
                );
                break;
            case "APPLOVIN-D":
                AliendroidIntertitial.ShowIntertitialApplovinDis(MainActivity.this, Select_Backup_Ads, MainIntertitial, BackupIntertitial, 0);
                break;
            case "FACEBOOK":
                AliendroidIntertitial.ShowIntertitialFAN(MainActivity.this, Select_Backup_Ads, MainIntertitial, BackupIntertitial, 0);
                break;
            case "ALIEN-M":
                AliendroidIntertitial.ShowIntertitialAlienMediation(MainActivity.this, Select_Backup_Ads, MainIntertitial, BackupIntertitial, 0);
                break;
            case "ALIEN-V":
                AliendroidIntertitial.ShowIntertitialAlienView(MainActivity.this, Select_Backup_Ads, MainIntertitial, BackupIntertitial, 0);
                break;
            case "UNITY":
                AliendroidIntertitial.ShowIntertitialUnity(MainActivity.this, Select_Backup_Ads, MainIntertitial, BackupIntertitial, 0);
                break;
        }

    }

    public void REWARD(View view){
        AliendroidReward.ShowRewardAlienView(MainActivity.this,Select_Backup_Ads,MainRewards,BackupReward);
    }
    public void onBackPressed(){
        finishAffinity();
        System.exit(0);
    }
}