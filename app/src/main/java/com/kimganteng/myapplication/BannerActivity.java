package com.kimganteng.myapplication;

import static com.kimganteng.myapplication.SettingsAlien.BackupBanner;
import static com.kimganteng.myapplication.SettingsAlien.MainBanner;
import static com.kimganteng.myapplication.SettingsAlien.Select_Backup_Ads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aliendroid.alienads.AliendroidBanner;
import com.aliendroid.alienads.AliendroidMediumBanner;
import com.aliendroid.alienads.interfaces.banner.OnLoadBannerAdmob;

public class BannerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        /*
        Load Relativelayout from acitivty_banner.xml
         */
        RelativeLayout laySmallAds = findViewById(R.id.lay320x50);
        RelativeLayout layMediumAds = findViewById(R.id.lay300x250);

        switch (SettingsAlien.Select_Main_Ads) {
            case "ADMOB":
                AliendroidBanner.SmallBannerAdmob(this, laySmallAds, Select_Backup_Ads, MainBanner, BackupBanner,
                        "", "", "", "", "");
                AliendroidMediumBanner.MediumBannerAdmob(this, layMediumAds, Select_Backup_Ads, MainBanner, BackupBanner,
                        "", "", "", "", "");
                break;
            case "APPLOVIN-M":
                AliendroidBanner.SmallBannerApplovinMax(this, laySmallAds, Select_Backup_Ads, MainBanner, BackupBanner);
                AliendroidMediumBanner.MediumBannerApplovinMax(this, layMediumAds, Select_Backup_Ads, MainBanner, BackupBanner);
                break;
            case "APPLOVIN-D":
                AliendroidBanner.SmallBannerApplovinDis(this, laySmallAds, Select_Backup_Ads, MainBanner, BackupBanner);
                AliendroidMediumBanner.MediumBannerApplovinDis(this, layMediumAds, Select_Backup_Ads, MainBanner, BackupBanner);
                break;
        }



    }
}