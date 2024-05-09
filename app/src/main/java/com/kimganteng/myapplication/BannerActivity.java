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
            case "ALIEN-V":
                AliendroidBanner.SmallBannerAlienView(this, laySmallAds, Select_Backup_Ads, MainBanner, BackupBanner);
                AliendroidMediumBanner.MediumBannerAlienView(this, layMediumAds, Select_Backup_Ads, MainBanner, BackupBanner);
                break;
            case "APPLOVIN-D":
                AliendroidBanner.SmallBannerApplovinDis(this, laySmallAds, Select_Backup_Ads, MainBanner, BackupBanner);
                AliendroidMediumBanner.MediumBannerApplovinDis(this, layMediumAds, Select_Backup_Ads, MainBanner, BackupBanner);
                break;
            case "FACEBOOK":
                AliendroidBanner.SmallBannerFAN(this, laySmallAds, Select_Backup_Ads, MainBanner, BackupBanner);
                AliendroidMediumBanner.MediumBannerFAN(this, layMediumAds, Select_Backup_Ads, MainBanner, BackupBanner);
                break;
            case "ALIEN-M":
                AliendroidBanner.SmallBannerAlienMediation(this, laySmallAds, Select_Backup_Ads, MainBanner, BackupBanner);
                AliendroidMediumBanner.MediumBannerAlienMediation(this, layMediumAds, Select_Backup_Ads, MainBanner, BackupBanner);
                break;
            case "UNITY":
                AliendroidBanner.SmallBannerUnity(this, laySmallAds, Select_Backup_Ads, MainBanner, BackupBanner);
                AliendroidMediumBanner.MediumBannerUnity(this, layMediumAds, Select_Backup_Ads, MainBanner, BackupBanner);
                break;
        }



    }
}