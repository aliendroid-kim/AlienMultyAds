package com.aliendroid.samplealienads;



import static com.aliendroid.samplealienads.SettingAds.BACKUP_ADS;
import static com.aliendroid.samplealienads.SettingAds.INITIALIZE_SDK;
import static com.aliendroid.samplealienads.SettingAds.INITIALIZE_SDK_BACKUPADS;
import static com.aliendroid.samplealienads.SettingAds.SELECT_ADS;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;

import com.aliendroid.alienads.AlienOpenAds;
import com.aliendroid.alienads.AliendroidInitialize;


public class SplashActivity extends AppCompatActivity {
    private static final int REQUEST = 112;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        switch (SELECT_ADS) {
            case "ADMOB":
                AliendroidInitialize.SelectAdsAdmob(SplashActivity.this, BACKUP_ADS, INITIALIZE_SDK);
                break;
            case "APPLOVIN-D":
                AliendroidInitialize.SelectAdsApplovinDis(SplashActivity.this, BACKUP_ADS, INITIALIZE_SDK);
                break;
            case "APPLOVIN-M":
                AliendroidInitialize.SelectAdsApplovinMax(SplashActivity.this, BACKUP_ADS, INITIALIZE_SDK);
                break;
            case "MOPUB":
                AliendroidInitialize.SelectAdsMopub(SplashActivity.this, BACKUP_ADS, INITIALIZE_SDK, INITIALIZE_SDK_BACKUPADS);
                break;
            case "IRON":
                AliendroidInitialize.SelectAdsIron(SplashActivity.this, BACKUP_ADS, INITIALIZE_SDK, INITIALIZE_SDK_BACKUPADS);
                break;
            case "STARTAPP":
                AliendroidInitialize.SelectAdsStartApp(SplashActivity.this, BACKUP_ADS, INITIALIZE_SDK,INITIALIZE_SDK_BACKUPADS);
                break;
        }
        AlienOpenAds.LoadOpenAds(SettingAds.OPEN_ADS_ADMOB);
        new CountDownTimer(10000, 1000) {
                @Override
                public void onFinish() {

                        Intent intent = new Intent(getBaseContext(),MainActivity.class);
                        startActivity(intent);
                        finish();


                }
                @Override
                public void onTick(long millisUntilFinished) {

                }
            }.start();

    }


}
