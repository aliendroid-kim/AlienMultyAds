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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aliendroid.alienads.AlienGDPR;
import com.aliendroid.alienads.AlienNotif;
import com.aliendroid.alienads.AlienPopup;
import com.aliendroid.alienads.AliendroidInitialize;
import com.aliendroid.alienads.AliendroidIntertitial;
import com.aliendroid.alienads.AliendroidReward;
import com.aliendroid.alienads.WortiseOpenAds;
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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    public static double longitude, latitude;
    public static String cityName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WortiseOpenAds.ShowOpenWortise(this);
        AppPromote.initializeAppPromote(this);
        InitializeAlienAds.LoadSDK();

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
        }

        AlienNotif.LoadOneSignal("535dc774-9fe3-44ae-839e-09e4133aebe9");
        AliendroidReward.LoadRewardFan(this,Select_Backup_Ads,MainRewards,BackupReward);
        AlienPopup.CpaOpenAds_Json(this, "https://aliendro.id/projek/Ads.json");
        TextView txtCode = findViewById(R.id.txtCode);

        TelephonyManager tm = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        String countryCodeValue = tm.getNetworkCountryIso();
        txtCode.setText("country = "+countryCodeValue);
    }

    public void BANNER(View view){
        Intent open = new Intent(MainActivity.this,BannerActivity.class);
        startActivity(open);

    }

    public void VIEWADS(View view){
        AlienPopup.CpaOpenAds_Json(this, "https://aliendro.id/projek/Ads.json");

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
        }

    }

    public void REWARD(View view){
        AliendroidReward.ShowRewardFan(MainActivity.this,Select_Backup_Ads,MainRewards,BackupReward);


    }

    public void onBackPressed(){
        finishAffinity();
        System.exit(0);
    }
}