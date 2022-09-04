package com.kimganteng.myapplication;

import static com.kimganteng.myapplication.SettingsAlien.BackupBanner;
import static com.kimganteng.myapplication.SettingsAlien.BackupNatives;
import static com.kimganteng.myapplication.SettingsAlien.MainBanner;
import static com.kimganteng.myapplication.SettingsAlien.MainNatives;
import static com.kimganteng.myapplication.SettingsAlien.Select_Backup_Ads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.aliendroid.alienads.AliendroidBanner;
import com.aliendroid.alienads.AliendroidNative;

public class NativeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native);

        RelativeLayout laySmallAds = findViewById(R.id.laySmallNatives);
        AliendroidNative.SmallNativeStartApp(this, laySmallAds,Select_Backup_Ads,MainNatives,BackupNatives);

        RelativeLayout layMediumAds = findViewById(R.id.layMediumNatives);
        AliendroidNative.MediumNativeStartApp(this, layMediumAds,Select_Backup_Ads,MainNatives,BackupNatives);

    }
}