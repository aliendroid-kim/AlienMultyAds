package com.kimganteng.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.aliendroid.alienads.AlienOpenAds;
import com.aliendroid.alienads.AliendroidBanner;
import com.aliendroid.alienads.AliendroidInitialize;
import com.aliendroid.alienads.AliendroidIntertitial;
import com.aliendroid.alienads.AliendroidNative;
import com.aliendroid.alienads.AliendroidReward;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlienOpenAds.LoadOpenAds("");
        RelativeLayout layAds = findViewById(R.id.layAds);
        RelativeLayout layNative = findViewById(R.id.layNative);
        AliendroidInitialize.SelectAdsUnity(this,"","3932965","");
        AliendroidBanner.SmallBannerUnity(this,layAds,"","bannerpintar","");
        //AliendroidNative.MediumNativeMax(this,layNative,"","ca-app-pub-3940256099942544/2247696110","");
        AliendroidReward.LoadRewardUnity(this,"","rewardedVideo","");
        AliendroidIntertitial.LoadIntertitialUnity(this,"","video","");

    }

    public void showreward(View view){
        //AliendroidIntertitial.ShowIntertitialUnity(MainActivity.this,"","video","",0);
        AliendroidReward.ShowRewardUnity(MainActivity.this,"","video","");
    }
}