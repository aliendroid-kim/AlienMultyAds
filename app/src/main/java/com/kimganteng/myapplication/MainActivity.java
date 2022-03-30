package com.kimganteng.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.aliendroid.alienads.AlienOpenAds;
import com.aliendroid.alienads.AliendroidBanner;
import com.aliendroid.alienads.AliendroidInitialize;
import com.aliendroid.alienads.AliendroidNative;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlienOpenAds.LoadOpenAds("");
        RelativeLayout layAds = findViewById(R.id.layAds);
        RelativeLayout layNative = findViewById(R.id.layNative);
        AliendroidInitialize.SelectAdsApplovinMax(this,"","");
        AliendroidBanner.SmallBannerApplovinMax(this,layAds,"","123456789","");
        AliendroidNative.SmallNativeMax(this,layNative,"","123456789","");
    }
}